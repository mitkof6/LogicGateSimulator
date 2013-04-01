package gui;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.Icon;

/**
 * Custom JButton for componets menu.
 * 
 * @author Jim Stanev
 */
public class ComponentJButton extends CustomJButton{

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */
	public ComponentJButton(String name, Icon icon, Object handler) {
		super(name, icon, (ActionListener) handler);
		this.addKeyListener((KeyListener) handler);
	}

}
