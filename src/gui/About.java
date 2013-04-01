package gui;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * About frame.
 * 
 * @author Jim Stanev
 */
public class About extends JFrame implements ActionListener{
	
	
	
	private static final long serialVersionUID = 1L;
	private JLabel[] message = new JLabel[6];
	
	public About(){
		super("About");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(6, 1, 1, 1));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.gif")));
		this.setBounds(500, 300, 0, 0);
		
		
		//Message
		message[0] = new JLabel("    Logic Gate Simulator v1.1   ");
		message[0].setFont(new Font("Courier New", Font.BOLD, 13));
		message[1] = new JLabel("");
		message[2] = new JLabel("      University of Patra     ");
		message[2].setFont(new Font("Courier New", 1, 13));
		message[3] = new JLabel("     Object-Oriented Course     ");
		message[3].setFont(new Font("Courier New", 1, 13));
		message[4] = new JLabel("");
		message[5] = new JLabel("   E-Mail: jimstanev@gmail.com  ");
		message[5].setFont(new Font("Courier New", Font.ITALIC, 13));
		for(int i = 0;i<6;i++){
			add(message[i]);
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		About about = new About();
		about.setVisible(true);
		about.pack();
		
	}
	
	


}
