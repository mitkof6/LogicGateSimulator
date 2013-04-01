package gui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * This class is responsible for the truth table output JFrame.
 * 
 * @author Jim Stanev
 *
 */
class TruthTableJFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	
	/**
	 * Constructor
	 */
	public TruthTableJFrame(){
		super("Truth Table");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.gif")));
		this.setBounds(1000, 200, 0, 0);	
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		this.add(textArea);

	}
	
	/**
	 * The text area setter.
	 * 
	 * @param line the string to be appended.
	 */
	public void setTextArea(String line){
		textArea.append(line);
	}
	
	
	
}
