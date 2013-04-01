package main;

import components.DualPort;
import components.SinglePort;

/**
 * This class is responsible for the simulation analysis.
 * 
 * @author Jim Stanev
 *
 */
public class Simulator{
	
	private int rows, columns;
	private Pin[][] board;
	private boolean state;
	
	/**
	 * Empty constructor.
	 */
	public Simulator(){
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param board the initial board
	 */
	public Simulator(Pin[][] board){
		this.rows = board.length;
		this.columns = board[0].length;
		this.board = board;
		this.state = false;	
	}
	
	/**
	 * This method is calculation the state of the nodes, starting from the input
	 * nodes.
	 */
	public void simulate(){
		for(int j = 0;j<columns;j++){
			for(int i = 0;i<rows;i++){
				if(board[i][j].getComponent()!=null){
					if(board[i][j].getComponent() instanceof components.Switch){
						board[i][j].getComponent().compute();
						board[i][j].setState(board[i][j].getComponent().getOut());
					}else if(board[i][j].getComponent() instanceof components.SinglePort){
						int tempIndexA = ((SinglePort) board[i][j].getComponent()).getIndexA();
						board[i][j].getComponent().setA(board[tempIndexA][j-1].getState());
						board[i][j].getComponent().compute();
						board[i][j].setState(board[i][j].getComponent().getOut());
					}else{
						int tempIndexA = ((DualPort) board[i][j].getComponent()).getIndexA();
						int tempIndexB = ((DualPort) board[i][j].getComponent()).getIndexB();
						board[i][j].getComponent().setA(board[tempIndexA][j-1].getState());
						((DualPort) board[i][j].getComponent()).setB(board[tempIndexB][j-1].getState());
						board[i][j].getComponent().compute();
						board[i][j].setState(board[i][j].getComponent().getOut());
					}
				}
			}
		}
		this.state = true;
		
	}
	
	/**
	 * This method returns the state of a node.
	 * 
	 * @param i the row index
	 * @param j the column index
	 * @return the state of the node
	 */
	public boolean probe(int i, int j){
		return board[i][j].getState();
	}
	
	/**
	 * This method sets the state of the switch.
	 * 
	 * @param i the row index of the switch
	 * @param state the state of the switch
	 */
	public void setSwitch(int i, boolean state){
		board[i][0].getComponent().setA(state);
	}
	
	/**
	 * This method gets the state of simulation.
	 * 
	 * @return true if simulation finished of false if not
	 */
	public boolean getState(){
		return this.state;
	}
}
