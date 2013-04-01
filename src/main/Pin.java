package main;

import components.Component;

/**
 * This class is a data representation of a node of the board.
 * 
 * @author Jim Stanev
 *
 */
public class Pin {
	
	public  Component component;
	public boolean state;
	
	/**
	 * Empty constructor.
	 */
	public Pin(){
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param com the component of the pin
	 */
	public Pin(Component com){
		this.component = com;
	}
	
	/**
	 * The component setter method.
	 * 
	 * @param com sets the component of the pin
	 */
	public void setComponent(Component com){
		this.component = com;
	}
	
	/**
	 * The component getter method.
	 * 
	 * @return the component of the pin
	 */
	public Component getComponent(){
		return this.component;
	}
	
	/**
	 * The state setter method.
	 * 
	 * @param state the state of the pin
	 */
	public void setState(boolean state){
		this.state = state;
	}
	
	/**
	 * The state getter method.
	 * 
	 * @return gets the state of the pin
	 */
	public boolean getState(){
		return this.state;
	}

}
