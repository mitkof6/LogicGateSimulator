package gui;

import javax.swing.JToolBar;

/**
 * A custom JToolBar component.
 * 
 * @author Jim Stanev
 */
public class CustomJToolBar extends JToolBar{
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * @param name the name of the tool bar
	 * @param orientation the orientation (horizontal, vertical)
	 */
	public CustomJToolBar(String name,int orientation){
		super(name,orientation);
	}
}
