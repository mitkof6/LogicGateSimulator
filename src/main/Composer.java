package main;

import java.lang.reflect.InvocationTargetException;

import components.*;


/**
 * This class is responsible for the circuit composition.
 * 
 * @author Jim Stanev
 *
 */
public class Composer {
	
	
	private Pin[][] board;
	private int rows, columns;
	private int[] ledPosition;
	private String netList;
	
	/**
	 * Constructor.
	 * 
	 * @param rows the number of rows
	 * @param columns the number of columns
	 */
	public Composer(int rows, int columns){
		this.netList = "";
		this.rows = rows;
		this.columns = columns;
		this.board = new Pin[rows][columns];
		ledPosition = new int[rows];
		for(int i = 0;i<rows;i++){
			ledPosition[i] = 0;
		}
		
		//Initialize
		for(int i = 0;i<rows;i++){
			for(int j = 0;j<columns;j++){
				board[i][j] = new Pin();
			}
		}
	}
	
	/**
	 * This method defines the id of the component to add. Initialize the component and add it into the
	 * board.
	 * 
	 * @param line
	 */
	public void add(String line){
		String[] tempArray = line.split(",");
		netList += line+"\n";
		try {
			Component temp = (Component) Class.forName("components."+tempArray[0]).getConstructor().newInstance();
			
			if(temp instanceof SinglePort){
				((SinglePort) temp).setIndexA(Integer.parseInt(tempArray[3]));
				board[Integer.parseInt(tempArray[2])][Integer.parseInt(tempArray[1])].setComponent(temp);
			}else if(temp instanceof Switch){
				board[Integer.parseInt(tempArray[1])][0].setComponent(temp);
			}else if(temp instanceof Led){
				ledPosition[Integer.parseInt(tempArray[1])] = 1;
			}else if(temp instanceof DualPort){
				((DualPort) temp).setIndexA(Integer.parseInt(tempArray[3]));
				((DualPort) temp).setIndexB(Integer.parseInt(tempArray[4]));
				board[Integer.parseInt(tempArray[2])][Integer.parseInt(tempArray[1])].setComponent(temp);
			}
			
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * The rows getter method.
	 * 
	 * @return the number of rows
	 */
	public int getRows(){
		return this.rows;
	}
	
	/**
	 * The columns getter method.
	 * 
	 * @return the number of columns
	 */
	public int getColumns(){
		return this.columns;
	}
	
	/**
	 * The board getter method.
	 * 
	 * @return the board
	 */
	public Pin[][] getBoard(){
		return this.board;
	}
	
	/**
	 * This method gets the led position state.
	 * 
	 * @param i the row index of the led 
	 * @return if led exist
	 */
	public int containsLed(int i){
		return this.ledPosition[i];
	}
	
	/**
	 * The net list getter method.
	 * 
	 * @return the net list of the circuit
	 */
	public String getNetList(){

		return this.netList;
		
	}
	
	/**
	 * The delete component method. It deletes a component from the board.
	 * 
	 * @param i the row index
	 * @param j the column index
	 * @return if success
	 */
	public String deletePin(int i, int j){
		if(board[i][j].getComponent()!=null){
			String name = board[i][j].getComponent().getClass().getSimpleName();
			String[] tempNetList = netList.split("\n");
			netList = "";
			for(int index = 0;index<tempNetList.length;index++){
				if(name.equals("Switch")||name.equals("Led")){
					if(tempNetList[index].contains(name+","+i)){
						continue;
					}
				}else{
					if(tempNetList[index].contains(name+","+j+","+i)){
						continue;
					}
				}
				netList += tempNetList[index]+"\n";
				
			}
			board[i][j].setComponent(null);
			return name;
		}else{
			if(j==(columns-1)){
				this.ledPosition[i] = 0;
				return "Led";
			}
			return null;
		}
		
	}
	
	/**
	 * This method check if a component exist.
	 * 
	 * @param i the row index
	 * @param j the column index
	 * @return true or false
	 */
	public boolean contains(int i,int j){
		if(board[i][j].getComponent()==null){
			return false;
		}else{
			return true;
		}
	}

}
