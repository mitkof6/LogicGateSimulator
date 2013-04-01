package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Simulator;

/**
 * This class is responsible for the components panel.
 * 
 * @author Jim Stanev
 */
public class CustomJPanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;
	private int rows,columns;
	private int columnSpace = 150;
	private int columnWidth = 30;
	private int nodeSpace = 75;
	private int nodeWidth = 20;
	private int offsetX = 40;
	private int offsetY = 100;
	private int componentWidth = 70;
	private int componentHeight = 70;
	private int componentOffsetX = columnSpace/3;
	@SuppressWarnings("unused")
	private int componentOffsetY = nodeWidth/3;
	private int newHeight, newWidth;
	
	private HashMap<String, JLabel> componentsHashMap;
	
	private Point[][] coordinates;
	
	/**
	 * Constructor.
	 * 
	 * @param rows number of rows of the circuit
	 * @param columns number of columns of the circuit
	 * @param frame the parent frame
	 */
	public CustomJPanel(int rows,int columns,JFrame frame){
		//Initialization
		this.setLayout(null);
		this.rows = rows;
		this.columns = columns;
		componentsHashMap = new HashMap<String, JLabel>();
		coordinates = new Point[rows][columns];
		
		newHeight = (rows+1)*(nodeSpace+nodeWidth)+offsetX-(rows<=2?0:40);
		newWidth = columns*(columnSpace+columnWidth)+offsetY;
		
		frame.setSize(newWidth>620?newWidth:620, newHeight>325?newHeight:325);
		
		
		
		for(int j = 0;j<columns;j++){
	    	for(int i = 0;i<rows;i++){
	    		coordinates[i][j] = new Point(j*columnSpace+5+offsetY, nodeSpace*i+offsetX);
	    	}
	    }
		

	}
	
	/**
	 * Paint the layout of the board.
	 */
	public void paintComponent(Graphics g){
	    int height = getHeight();
	    
	    for(int j = 0;j<columns;j++){
	    	g.setColor(Color.GRAY);
	    	g.fillRect(j*columnSpace+offsetY, 0, columnWidth, height);
	    	for(int i = 0;i<rows;i++){
	    		g.setColor(Color.BLACK);
	    		g.fillRect((int)coordinates[i][j].getX(), (int)coordinates[i][j].getY(), nodeWidth, nodeWidth);
	    		g.setColor(Color.WHITE);
	    		g.drawString(String.format("%d,%d", i,j), (int)coordinates[i][j].getX()+2, (int)coordinates[i][j].getY()+13);
	    	}
	    	
	    } 
	    
	   
	}
	
	/**
	 * Add a component to the panel.
	 * 
	 * @param ID the id of the component
	 */
	public void addComponent(String ID){
				
		String[] temp = ID.split(",");
		
		JLabel component = new JLabel(this.getIcon(temp[0]));
		Rectangle rec = null;
		
		Graphics g = this.getGraphics();
		g.setColor(Color.RED);
		
		
		if(temp[0].contains("Switch")){
			rec = new Rectangle((int) coordinates[Integer.parseInt(temp[1])][0].getX()-componentWidth-componentOffsetX/2,
					(int) coordinates[Integer.parseInt(temp[1])][0].getY()-componentHeight/3, componentWidth, componentHeight);
			component.setBounds(rec);
			component.setName(ID);
			g.drawLine((int) coordinates[Integer.parseInt(temp[1])][0].getX(),
					(int) coordinates[Integer.parseInt(temp[1])][0].getY()+10,
					(int) coordinates[Integer.parseInt(temp[1])][0].getX()-40,
					(int) coordinates[Integer.parseInt(temp[1])][0].getY()+10);
		}else if(temp[0].contains("Led")){
			rec = new Rectangle((int) coordinates[Integer.parseInt(temp[1])][columns-1].getX()+componentWidth/2,
					(int) coordinates[Integer.parseInt(temp[1])][columns-1].getY()-componentHeight/4-5,
					componentWidth, componentHeight);
			component.setBounds(rec);
			component.setName(ID);
			g.drawLine((int) coordinates[Integer.parseInt(temp[1])][columns-1].getX()+10,
					(int) coordinates[Integer.parseInt(temp[1])][columns-1].getY()+10,
					(int) coordinates[Integer.parseInt(temp[1])][columns-1].getX()+40,
					(int) coordinates[Integer.parseInt(temp[1])][columns-1].getY()+10);
		}else if(temp[0].contains("Buffer")||temp[0].contains("Not")){
			rec = new Rectangle((int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])-1].getX()+componentWidth,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])-1].getY()-componentHeight/4-5,
					componentWidth, componentHeight);
			component.setBounds(rec);
			component.setName(temp[0]+","+temp[2]+","+temp[1]);
			g.drawLine((int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getX(),
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getY()+10,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getX()-40,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getY()+10);
			g.drawLine((int) coordinates[Integer.parseInt(temp[3])][Integer.parseInt(temp[1])-1].getX()+10,
					(int) coordinates[Integer.parseInt(temp[3])][Integer.parseInt(temp[1])-1].getY()+10,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getX()-90,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getY()+10);
			
		}else{
			rec = new Rectangle((int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])-1].getX()+componentWidth,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])-1].getY()-componentHeight/4-5,
					componentWidth, componentHeight);
			component.setBounds(rec);
			component.setName(temp[0]+","+temp[2]+","+temp[1]);
			g.drawLine((int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getX(),
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getY()+10,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getX()-40,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getY()+10);
			g.drawLine((int) coordinates[Integer.parseInt(temp[3])][Integer.parseInt(temp[1])-1].getX()+10,
					(int) coordinates[Integer.parseInt(temp[3])][Integer.parseInt(temp[1])-1].getY()+10,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getX()-90,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getY()-10);
			g.drawLine((int) coordinates[Integer.parseInt(temp[4])][Integer.parseInt(temp[1])-1].getX()+10,
					(int) coordinates[Integer.parseInt(temp[4])][Integer.parseInt(temp[1])-1].getY()+10,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getX()-90,
					(int) coordinates[Integer.parseInt(temp[2])][Integer.parseInt(temp[1])].getY()+20);
			
		}
		
		
		componentsHashMap.put(component.getName(), component);
		
		this.add(component);
		this.repaint(rec);
		
	}
	
	/**
	 * Prints the boolean state of every node.
	 * 
	 * @param simulator the simulation reference
	 */
	public void probeTable(Simulator simulator){
		Graphics g = this.getGraphics();
		g.setColor(Color.GREEN);
		for(int i = 0;i<rows;i++){
			for(int j = 0;j<columns;j++){
				g.drawString(String.format("%s", (simulator.probe(i, j)? "True":"False")), (int)coordinates[i][j].getX(), (int)coordinates[i][j].getY());
			}
		}
		
	}
	
	/**
	 * Remove component from the panel.
	 * 
	 * @param ID the id of the component
	 */
	public void removeComponent(String ID){
		Component component = (Component) componentsHashMap.remove(ID);
		if(component==null){
			return;
		}
		this.remove(component);
		this.setSize(this.getWidth()+1,this.getHeight()+1);
	}
	
	/**
	 * Finds the icon of a given object.
	 * 
	 * @param name the name of the object
	 * @return the icon corresponding to the name
	 */
	private Icon getIcon(String name){
		Icon icon = new ImageIcon(getClass().getResource("componentsOnBoard/"+name+".gif"));
		componentHeight = icon.getIconHeight();
		componentWidth = icon.getIconWidth();
		return icon;
	}
}