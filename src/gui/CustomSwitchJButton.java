package gui;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.Icon;

/**
 * A custom JButton for the switch buttons. Extends CustomJButton.
 * 
 * @author Jim Stanev
 */
public class CustomSwitchJButton extends CustomJButton {

	
	private static final long serialVersionUID = 1L;

	private boolean state;
	
	/**
	 * Constructor.
	 * 
	 * @param name the name of the button
	 * @param icon the icon of the button
	 * @param handler the handler of the button
	 */
	public CustomSwitchJButton(String name,Icon icon, ActionListener handler){
		super(name,icon,handler);
		this.state = false;
	}
	
	/**
	 * Sets the state of the button.
	 */
	public void setState(){
		if(this.state==false){
			this.state = true;
			this.setForeground(Color.GREEN);
		}else{
			this.state = false;
			this.setForeground(Color.GRAY);
		}
	}
	
	/**
	 * The state getter.
	 * 
	 * @return the state of the button
	 */
	public boolean getState(){
		return this.state;
	}
}
