package gui;

import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Custom JButton.
 * 
 * @author Jim Stanev
 */
public class CustomJButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * 
	 * @param name the name of the button
	 * @param icon	the icon of the button
	 * @param handler the action handler of the button
	 */
	public CustomJButton(String name,Icon icon, ActionListener handler){
		super(name,icon);

		this.addActionListener(handler);
		this.setName(name);
	}

}
