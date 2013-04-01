package gui;


import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import exceptions.FileDialogException;
import exceptions.SimulationException;
import exceptions.TruthTableException;
import exceptions.WindowDialogException;

import main.Composer;
import main.Simulator;

import util.OpenFileDialog;
import util.SaveFileDialog;

/**
 * The main frame class.
 * 
 * @author Jim Stanev
 */
public class Window extends JFrame{

	
	
	private static final long serialVersionUID = 1L;
	
	//Menu icons
	Icon newIconButton = new ImageIcon(getClass().getResource("menuIcons/new.gif"));
	Icon openIconButton = new ImageIcon(getClass().getResource("menuIcons/open.gif"));
	Icon saveIconButton = new ImageIcon(getClass().getResource("menuIcons/save.gif"));
	Icon simulateIconButton = new ImageIcon(getClass().getResource("menuIcons/simulate.gif"));
	Icon truthTableIconButton = new ImageIcon(getClass().getResource("menuIcons/truthTable.gif"));
	Icon aboutIconButton = new ImageIcon(getClass().getResource("menuIcons/about.gif"));
	Icon exitIconButton = new ImageIcon(getClass().getResource("menuIcons/exit.gif"));
	
	//Components icons
	Icon bufferIconButton = new ImageIcon(getClass().getResource("componentsIcons/buffer.gif"));
	Icon notIconButton = new ImageIcon(getClass().getResource("componentsIcons/not.gif"));
	Icon andIconButton = new ImageIcon(getClass().getResource("componentsIcons/and.gif"));
	Icon nandIconButton = new ImageIcon(getClass().getResource("componentsIcons/nand.gif"));
	Icon orIconButton = new ImageIcon(getClass().getResource("componentsIcons/or.gif"));
	Icon norIconButton = new ImageIcon(getClass().getResource("componentsIcons/nor.gif"));
	Icon xorIconButton = new ImageIcon(getClass().getResource("componentsIcons/xor.gif"));
	Icon xnorIconButton = new ImageIcon(getClass().getResource("componentsIcons/xnor.gif"));
	Icon switchIconButton = new ImageIcon(getClass().getResource("componentsIcons/switch.gif"));
	Icon ledIconButton = new ImageIcon(getClass().getResource("componentsIcons/led.gif"));
	Icon gateIconButton = new ImageIcon(getClass().getResource("componentsIcons/extra.gif"));
	Icon probeIconButton = new ImageIcon(getClass().getResource("componentsIcons/probe.gif"));
	
	//Edit icons
	Icon editIconButton = new ImageIcon(getClass().getResource("editIcons/edit.gif"));
	Icon deleteIconButton = new ImageIcon(getClass().getResource("editIcons/delete.gif"));
	
	//Switch icons
	Icon switchButtonIconButton = new ImageIcon(getClass().getResource("componentsIcons/switchButton.gif"));
	
	
	//Initial board
	private int columns = 2,rows = 2;
	
	private CustomJPanel board;
	private CustomJToolBar switchToolBar;
	private CustomSwitchJButton[] switchToolBarButton;
	
	private Composer composer;
	private Simulator simulator;
	
	private CustomJButton bufferButton;
	private CustomJButton notButton;
	private CustomJButton andButton;
	private CustomJButton nandButton;
	private CustomJButton orButton;
	private CustomJButton norButton;
	private CustomJButton xorButton;
	private CustomJButton xnorButton;
	private CustomJButton swithButton;
	private CustomJButton ledButton;
	private CustomJButton probeButton;
	
	//private CustomJButton editButton;
	private CustomJButton deleteButton;
	
	/**
	 * Constructor.
	 */
	public Window(){
		//Settings
		super("Logic Gate Simulator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.gif")));
		this.setBounds(100, 100, 620, 325);
		
		//Handlers
		MenuButtonsHandler menuButtonHandler = new MenuButtonsHandler();
		ComponentButtonHandler componetButtonHandler = new ComponentButtonHandler();
		EditButtonHandler editButtonHandler = new EditButtonHandler();
		
		//Menu tool bar
		CustomJToolBar menuToolBar = new CustomJToolBar(null,JToolBar.HORIZONTAL);
		CustomJButton newButton = new CustomJButton("New",newIconButton,menuButtonHandler);
		CustomJButton openButton = new CustomJButton("Open",openIconButton,menuButtonHandler);
		CustomJButton saveButton = new CustomJButton("Save",saveIconButton,menuButtonHandler);
		CustomJButton simulateButton = new CustomJButton("Simulate",simulateIconButton,menuButtonHandler);
		CustomJButton tableButton = new CustomJButton("Truth table",truthTableIconButton,menuButtonHandler);
		CustomJButton aboutButton = new CustomJButton("About",aboutIconButton,new About());
		CustomJButton exitButton = new CustomJButton("Exit",exitIconButton,menuButtonHandler);
		menuToolBar.add(newButton);
		menuToolBar.add(openButton);
		menuToolBar.add(saveButton);
		menuToolBar.add(simulateButton);
		menuToolBar.add(tableButton);
		menuToolBar.add(aboutButton);
		menuToolBar.add(exitButton);
		this.add(menuToolBar,BorderLayout.NORTH);
		
		//Components tool bar
		CustomJToolBar componentToolBar = new CustomJToolBar(null,JToolBar.HORIZONTAL);
		bufferButton = new ComponentJButton("",bufferIconButton,componetButtonHandler);
		notButton = new ComponentJButton("",notIconButton,componetButtonHandler);
		andButton = new ComponentJButton("",andIconButton,componetButtonHandler);
		nandButton = new ComponentJButton("",nandIconButton,componetButtonHandler);
		orButton = new ComponentJButton("",orIconButton,componetButtonHandler);
		norButton = new ComponentJButton("",norIconButton,componetButtonHandler);
		xorButton = new ComponentJButton("",xorIconButton,componetButtonHandler);
		xnorButton = new ComponentJButton("",xnorIconButton,componetButtonHandler);
		swithButton = new ComponentJButton("",switchIconButton,componetButtonHandler);
		ledButton = new ComponentJButton("",ledIconButton,componetButtonHandler);
		probeButton = new ComponentJButton("",probeIconButton,componetButtonHandler);
		componentToolBar.add(bufferButton);
		componentToolBar.add(notButton);
		componentToolBar.add(andButton);
		componentToolBar.add(nandButton);
		componentToolBar.add(orButton);
		componentToolBar.add(norButton);
		componentToolBar.add(xorButton);
		componentToolBar.add(xnorButton);
		componentToolBar.add(swithButton);
		componentToolBar.add(ledButton);
		componentToolBar.add(probeButton);
		this.add(componentToolBar,BorderLayout.SOUTH);
		
		//Edit tool bar
		CustomJToolBar editToolBar = new CustomJToolBar(null,JToolBar.VERTICAL);
		//editButton = new CustomJButton("",editIconButton,editButtonHandler);
		deleteButton = new CustomJButton("",deleteIconButton,editButtonHandler);
		//editToolBar.add(editButton);
		editToolBar.add(deleteButton);
		this.add(editToolBar,BorderLayout.EAST);
		
		//Panel
		setBoardPanel(rows,columns);
	
	}
	
	/**
	 * Sets the components board. 
	 * 
	 * @param rows the rows of the board
	 * @param cols the columns of the board
	 */
	private void setBoardPanel(int rows,int cols){
		
		//Remove
		if(board!=null){
			this.remove(board);
			this.remove(switchToolBar);
		}
		
		//Switch tool bar
		SwitchButtonHandler switchButtonHandler = new SwitchButtonHandler();
		switchToolBar = new CustomJToolBar(null,JToolBar.VERTICAL);
		switchToolBarButton = new CustomSwitchJButton[rows];
		for(int i = 0;i<rows;i++){
			switchToolBarButton[i] = new CustomSwitchJButton(String.format("%d", i), switchButtonIconButton,switchButtonHandler);
			switchToolBar.add(switchToolBarButton[i]);
		}
		this.add(switchToolBar,BorderLayout.WEST);
		
		//Board
		this.board = new CustomJPanel(rows, cols, this);
		this.add(board,BorderLayout.CENTER);
		this.rows = rows;
		this.columns = cols;
		
		//Composer
		composer = new Composer(this.rows, this.columns);
		
		setSize(getWidth()+1,getHeight()+1);
	}
	
	/**
	 * A method for simulating the circuit.
	 */
	private void simulate(){
		//Initialize
		simulator = new Simulator(composer.getBoard());

		//Get the switch state
		for(int i = 0;i<switchToolBarButton.length;i++){
			if(composer.contains(i, 0)){
				simulator.setSwitch(i,switchToolBarButton[i].getState());
			}
			
		}
		
		//Simulate
		simulator.simulate();
		
		//Get the result
		for(int i = 0;i<rows;i++){
			if(composer.containsLed(i)==1){
				board.removeComponent("LedOff,"+i);
				board.removeComponent("LedOn,"+i);
				if(simulator.probe(i, columns-1)==true){
					board.addComponent("LedOn,"+i);
				}else{
					board.addComponent("LedOff,"+i);
				}
			}
		}
	}
	
	/**
	 * The boarder panel getter.
	 * 
	 * @return the board panel
	 */
	public CustomJPanel getBoardPanel(){
		return this.board;
	}
	
	/**
	 * This inner class is responsible for the menu button handler.
	 * 
	 * @author Jim Stanev
	 *
	 */
	private class MenuButtonsHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command=="New"){
				newBoard();
			}else if(command=="Open"){
				open();
			}else if(command=="Save"){
				save();
			}else if(command=="Simulate"){
				simulate();
			}else if(command=="Truth table"){
				try {
					truthTableSimulate();
				} catch (TruthTableException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "TruthTable error", JOptionPane.ERROR_MESSAGE);
				}
			}else if(command=="Exit"){
				System.exit(0);
			}
			
		}
		
		/**
		 * New board method. Constructs new panel for the components.
		 */
		private void newBoard(){
			CustomJOptionPane dimensionsPane;
			try {
				dimensionsPane = new CustomJOptionPane();
				int rows = dimensionsPane.getRows();
				int columns = dimensionsPane.getColumns();
				setBoardPanel(rows, columns);
			} catch (WindowDialogException e1) {
				
			}
		}
		
		/**
		 * Open method. It opens a circuit from a file.
		 */
		private void open(){
			OpenFileDialog file;
			String path;
			try {
				file = new OpenFileDialog();
				path = file.getFile();
			} catch (FileDialogException e2) {
				return;
			}
			
			Scanner inputStream;
			try {
				inputStream = new Scanner(new File(path));
				
				//Set dimensions of board
				String dimensions = inputStream.nextLine();
				String[] tempArray = dimensions.split(",");
				setBoardPanel(Integer.parseInt(tempArray[1]), Integer.parseInt(tempArray[2]));
				composer = new Composer(Integer.parseInt(tempArray[1]),Integer.parseInt(tempArray[2]));
				String line;
				while(inputStream.hasNext()){
					line = inputStream.nextLine();
					composer.add(line);
					if(line.contains("Switch")){
						line = line.substring(0, 6)+"Off"+line.substring(6);
					}else if(line.contains("Led")){
						line = line.substring(0, 3)+"Off"+line.substring(3);
					}
					board.addComponent(line);
					
				}
				setSize(getWidth()+1,getHeight()+1);
				inputStream.close();
			} catch (FileNotFoundException e1) {
				
			}
		}
		
		/**
		 * Save method. It saves a design to a file.
		 */
		private void save(){
			String netList = "Board,"+rows+","+columns+"\n";
			netList += composer.getNetList();
			
			SaveFileDialog file;
			String path;
			try {
				file = new SaveFileDialog();
				path = file.getFile();
			} catch (FileDialogException e2) {
				return;
			}

			try {
				
				FileWriter fileWriter = new FileWriter(new File(path));
				fileWriter.write(netList);
				fileWriter.close();
			} catch (IOException e1) {

			}
		}
		
		/**
		 * This method is responsible for getting the truth table of the circuit.
		 * 
		 * @throws TruthTableException
		 */
		private void truthTableSimulate() throws TruthTableException{
			
			simulator = new Simulator(composer.getBoard());
			TruthTableJFrame truthTableJFrame = new TruthTableJFrame();
			
			String inputSwitch = "";
			
			int switchCounter = 0;
			for(int i = 0;i<switchToolBarButton.length;i++){
				if(composer.contains(i, 0)){
					switchCounter++;
					inputSwitch += i;
				}
			}
			truthTableJFrame.setTextArea("INPUT\tOUTPUT\n");
			truthTableJFrame.setTextArea(inputSwitch+"\t");
			
			String dashes = "-------";
			
			for(int i = 0;i<switchToolBarButton.length;i++){
				if(composer.containsLed(i)==1){
					truthTableJFrame.setTextArea(i+"\t");
					dashes += "----------------------------";
				}
			}
			
			if(switchCounter==0){
				throw new TruthTableException("now input switch");
			}
			
			truthTableJFrame.setTextArea("\n"+dashes+"\n");
			
			for(int i = 0;i<Math.pow(2, switchCounter);i++){
				String set = Integer.toString(i, 2);
				
				while(set.length()<switchCounter){
					set = "0"+set;
				}
				
				
				truthTableJFrame.setTextArea(set+"\t");
				for(int j = 0;j<switchToolBarButton.length;j++){
					if(composer.contains(j, 0)){
						simulator.setSwitch(j,set.charAt(j)=='0'?false:true);
					}
				}
				
				simulator.simulate();
				int counter = 0;
				if(simulator.getState()==true){
					for(int z = 0;z<rows;z++){
						if(composer.containsLed(z)==1){
							counter++;
							truthTableJFrame.setTextArea(simulator.probe(z, columns-1)+"\t");
						}
					}
				}
				if(counter==0){
					throw new TruthTableException("not output led");
				}
				truthTableJFrame.setTextArea("\n"+dashes+"\n");
			}
			
			truthTableJFrame.setVisible(true);
			truthTableJFrame.pack();
		}
		
		
		/**
		 * Inner helper class. It is responsible for the user dialogs.
		 * 
		 * @author Jim Stanev
		 *
		 */
		private class CustomJOptionPane{

			private JTextField rows;
			private JTextField columns;

			/**
			 * Constructor.
			 * 
			 * @throws WindowDialogException if there is a problem with the dialog
			 */
			public  CustomJOptionPane() throws WindowDialogException{
				rows = new JTextField(5);
			    columns = new JTextField(5);

			    JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("Rows:"));
			    myPanel.add(rows);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Columns:"));
			    myPanel.add(columns);

			    int result = JOptionPane.showConfirmDialog(null, myPanel,"Please give the board dimensions", JOptionPane.OK_CANCEL_OPTION);
			    if(result==JOptionPane.CLOSED_OPTION||result==JOptionPane.CANCEL_OPTION||this.getRows()<1||this.getColumns()<2){
			    	result = JOptionPane.CLOSED_OPTION;
			    	throw new WindowDialogException("Menu Dialog: "+result);
			    }
			    
			}
			
			/**
			 * The rows getter.
			 * 
			 * @return the number of rows
			 */
			public int getRows(){
				return Integer.parseInt(rows.getText());
			}
			
			/**
			 * The column getter.
			 * 
			 * @return the number of columns
			 */
			public int getColumns(){
				return Integer.parseInt(columns.getText());
			}
			
		}
		
		

	}

	/**
	 * This inner class is responsible for the component button handler and key listener.
	 * 
	 * @author Jim Stanev
	 *
	 */
	private class ComponentButtonHandler implements ActionListener, KeyListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==bufferButton){
				addSingleGate("Buffer");
			}else if(e.getSource()==notButton){
				addSingleGate("Not");
			}else if(e.getSource()==andButton){
				addDualGate("And");
			}else if(e.getSource()==nandButton){
				addDualGate("Nand");
			}else if(e.getSource()==orButton){
				addDualGate("Or");
			}else if(e.getSource()==norButton){
				addDualGate("Nor");
			}else if(e.getSource()==xorButton){
				addDualGate("Xor");
			}else if(e.getSource()==xnorButton){
				addDualGate("Xnor");
			}else if(e.getSource()==swithButton){
				addComponent("Switch");
			}else if(e.getSource()==ledButton){
				addComponent("Led");
			}else if(e.getSource()==probeButton){
				try {
					probe();
				} catch (SimulationException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Simulation error", JOptionPane.ERROR_MESSAGE);
				}
			}	
		}
		
		/**
		 * This methods adds a dual gate to the panel and informs the composer.
		 * 
		 * @param gate the id of the gate
		 */
		private void addDualGate(String gate){
			DualGateJOptionPane gatePane;
			try {
				gatePane = new DualGateJOptionPane(gate);
			} catch (WindowDialogException e) {
				return;
			}
			
			if(composer.contains(gatePane.getIndexOut(), gatePane.getColumn())){
				return;
			}
			
			String component = gate+","+gatePane.getColumn()+","+gatePane.getIndexOut()+","+gatePane.getIndexA()+","
				+gatePane.getIndexB();
			
			composer.add(component);
			board.addComponent(component);
		}
		
		/**
		 * This method adds a single gate to the panel and informs the composer.
		 * 
		 * @param gate the id of the gate
		 */
		private void addSingleGate(String gate){
			SingleGateJOptionPane gatePane;
			try {
				gatePane = new SingleGateJOptionPane(gate);
			} catch (WindowDialogException e) {
				return;
			}
			if(composer.contains(gatePane.getIndexOut(), gatePane.getColumn())){
				return;
			}
			String component = gate+","+gatePane.getColumn()+","+gatePane.getIndexOut()+","+gatePane.getIndexA();
			
			composer.add(component);
			board.addComponent(component);
		}
		
		/**
		 * This method adds a component to the panel (switch, led) and informs the composer.
		 * 
		 * @param component the component's id
		 */
		private void addComponent(String component){
			ComponentJOptionPane componentPane;
			try {
				componentPane = new ComponentJOptionPane(component);
			} catch (WindowDialogException e) {
				return;
			}
			
			String composerComponent = component+","+componentPane.getIndexOut();
			String boardComponent;
			
			if(component.equals("Switch")){
				if(composer.contains(componentPane.getIndexOut(), 0)==true){
					return;
				}
				
				if(switchToolBarButton[componentPane.getIndexOut()].getState()==false){
					boardComponent = component+"Off"+","+componentPane.getIndexOut();
				}else{
					boardComponent = component+"On"+","+componentPane.getIndexOut();
				}
			}else{
				if(composer.containsLed(componentPane.getIndexOut())==1){
					return;
				}
				boardComponent = component+"Off"+","+componentPane.getIndexOut();
			}
			composer.add(composerComponent);
			board.addComponent(boardComponent);
		}
		
		/**
		 * This method method is responsible for outputting the boolean state of every node.
		 * @throws SimulationException 
		 */
		private void probe() throws SimulationException{
			if(simulator==null||simulator.getState()==false){
				throw new SimulationException("simulation not initialized");
			}
			simulator.simulate();
			board.probeTable(simulator);
		}
		
		/**
		 * This inner class is responsible for the dual gate dialog.
		 * 
		 * @author Jim Stanev
		 *
		 */
		private class DualGateJOptionPane extends SingleGateJOptionPane{
			
			protected JTextField indexB;
			
			/**
			 * Constructor.
			 * 
			 * @param gate the id of the gate for the title
			 * @throws WindowDialogException if there is a problem with the dialog
			 */
			public  DualGateJOptionPane(String gate) throws WindowDialogException{
				
				column = new JTextField(5);
			    indexA = new JTextField(5);
			    indexB = new JTextField(5);
			    indexOut = new JTextField(5);

			    JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("Column:"));
			    myPanel.add(column);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Pin A:"));
			    myPanel.add(indexA);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Pin B:"));
			    myPanel.add(indexB);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Pin Out:"));
			    myPanel.add(indexOut);
			    
			    int result = JOptionPane.showConfirmDialog(null, myPanel,"Please give the "+gate+" configurations", JOptionPane.OK_CANCEL_OPTION);
			    if(result==JOptionPane.CLOSED_OPTION||result==JOptionPane.CANCEL_OPTION||this.getColumn()<1||this.getColumn()>=columns||this.getIndexA()<0||
			    		this.getIndexA()>=rows||this.getIndexB()<0||this.getIndexB()>=rows||
			    		this.getIndexOut()<0||this.getIndexOut()>=rows){
			    	result = JOptionPane.CANCEL_OPTION;
			    	throw new WindowDialogException("Dual Gate Component Dialog: "+result);
			    }
			}
			
			/**
			 * The index B getter.
			 * 
			 * @return the index of pin B
			 */
			public int getIndexB(){
				return Integer.parseInt(indexB.getText());
			}
	
		
		}
		
		/**
		 * This inner class is responsible for the single gate dialog.
		 * 
		 * @author Jim Stanev
		 *
		 */
		private class SingleGateJOptionPane{
			
			protected JTextField column;
			protected JTextField indexA;
			protected JTextField indexOut;
			
			/**
			 * Empty constructor.
			 */
			public SingleGateJOptionPane(){
				
			}
			
			/**
			 * Constructor.
			 * 
			 * @param gate the id of the gate for the title
			 * @throws WindowDialogException if there is a problem with the dialog
			 */
			public  SingleGateJOptionPane(String gate) throws WindowDialogException{
				
				column = new JTextField(5);
			    indexA = new JTextField(5);
			    indexOut = new JTextField(5);

			    JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("Column:"));
			    myPanel.add(column);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Pin A:"));
			    myPanel.add(indexA);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Pin Out:"));
			    myPanel.add(indexOut);
			    
			    int result = JOptionPane.showConfirmDialog(null, myPanel,"Please give the "+gate+" configurations", JOptionPane.OK_CANCEL_OPTION);
			    
			    if(result==JOptionPane.CLOSED_OPTION||result==JOptionPane.CANCEL_OPTION||this.getColumn()<1||this.getColumn()>=columns||this.getIndexA()<0||
			    		this.getIndexA()>=rows||this.getIndexOut()<0||this.getIndexOut()>=rows){
			    	result = JOptionPane.CANCEL_OPTION;
			    	throw new WindowDialogException("Single Gate Component Dialog: "+result);
			    	
			    }
			}
			
			/**
			 * The column getter.
			 * 
			 * @return the index of the column
			 */
			public int getColumn(){
				return Integer.parseInt(column.getText());
			}
			
			/**
			 * The index A getter.
			 * 
			 * @return the index of A
			 */
			public int getIndexA(){
				return Integer.parseInt(indexA.getText());
			}
			
			/**
			 * The index Out getter.
			 * 
			 * @return the index Out
			 */
			public int getIndexOut(){
				return Integer.parseInt(indexOut.getText());
			}
			
		}
		
		/**
		 * This inner class is responsible for the components dialog.
		 * 
		 * @author Jim Stanev
		 *
		 */
		private class ComponentJOptionPane{
			
			private JTextField indexOut;
			
			/**
			 * Constructor.
			 * 
			 * @param component the component id for the title
			 * @throws WindowDialogException if there is a problem with the dialog
			 */
			public ComponentJOptionPane(String component) throws WindowDialogException{
				indexOut = new JTextField(5);
				
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Pin Out:"));
			    myPanel.add(indexOut);
			    
			    int result = JOptionPane.showConfirmDialog(null, myPanel,"Please give the "+component+" configurations", JOptionPane.OK_CANCEL_OPTION);
			    if(result==JOptionPane.CLOSED_OPTION||result==JOptionPane.CANCEL_OPTION||this.getIndexOut()<0||this.getIndexOut()>=rows){
			    	result = JOptionPane.CANCEL_OPTION;
			    	throw new WindowDialogException("Component Dialog: "+result);
			    }
			}
			
			/**
			 * The index Out getter.
			 * 
			 * @return the index Out
			 */
			public int getIndexOut(){
				return Integer.parseInt(indexOut.getText());
			}
		}

		@Override
		public void keyPressed(KeyEvent event) {
			if(event.getKeyCode()==KeyEvent.VK_1){
				addSingleGate("Buffer");
			}else if(event.getKeyCode()==KeyEvent.VK_2){
				addSingleGate("Not");
			}else if(event.getKeyCode()==KeyEvent.VK_3){
				addDualGate("And");
			}else if(event.getKeyCode()==KeyEvent.VK_4){
				addDualGate("Nand");
			}else if(event.getKeyCode()==KeyEvent.VK_5){
				addDualGate("Or");
			}else if(event.getKeyCode()==KeyEvent.VK_6){
				addDualGate("Nor");
			}else if(event.getKeyCode()==KeyEvent.VK_7){
				addDualGate("Xor");
			}else if(event.getKeyCode()==KeyEvent.VK_8){
				addDualGate("Xnor");
			}else if(event.getKeyCode()==KeyEvent.VK_9){
				addComponent("Switch");
			}else if(event.getKeyCode()==KeyEvent.VK_0){
				addComponent("Led");
			}	
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void keyTyped(KeyEvent event) {

		}
	}
	
	/**
	 * This inner class is responsible for the switch button handler.
	 * 
	 * @author Jim Stanev
	 *
	 */
	private class SwitchButtonHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			CustomSwitchJButton temp = (CustomSwitchJButton) e.getSource();
			
			if(composer.contains(Integer.parseInt(temp.getText()), 0)==false){
				return;
			}
			temp.setState();
			if(temp.getState()==true){
				board.removeComponent("SwitchOff,"+temp.getText());
				board.addComponent("SwitchOn,"+temp.getText());
			}else{
				board.removeComponent("SwitchOn,"+temp.getText());
				board.addComponent("SwitchOff,"+temp.getText());
			}
			
		}
		
	}

	/**
	 * This inner class is responsible for the edit buttons handler.
	 * 
	 * @author Jim Stanev
	 *
	 */
	private class EditButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==deleteButton){
				delete();
			}
		}
		
		/*
		 * The delete method. It deletes a component from the board.
		 */
		private void delete(){
			EditJOptionPane editPane;
			try {
				editPane = new EditJOptionPane();
			} catch (WindowDialogException e1) {
				return;
			}
		
			String name = composer.deletePin(editPane.getRowIndex(), editPane.getColumnIndex());
			if(name!=null){
				if(name.equals("Switch")){
					board.removeComponent("SwitchOff,"+editPane.getRowIndex());
					board.removeComponent("SwitchOn,"+editPane.getRowIndex());
				}else if(name.equals("Led")){
					board.removeComponent("LedOff,"+editPane.getRowIndex());
					board.removeComponent("LedOn,"+editPane.getRowIndex());
				}else{
					board.removeComponent(name+","+editPane.getRowIndex()+","+editPane.getColumnIndex());
				}
			}
		}
		
		/**
		 * This inner class is responsible for the edit dialog.
		 * 
		 * @author mitkof6
		 *
		 */
		private class EditJOptionPane{
			
			private JTextField columnIndex, rowIndex;
			
			/**
			 * Constructor.
			 * 
			 * @throws WindowDialogException if there is a problem with the dialog
			 */
			public EditJOptionPane() throws WindowDialogException{
				rowIndex = new JTextField(5);
				columnIndex = new JTextField(5);
				
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Row:"));
			    myPanel.add(rowIndex);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    
			    myPanel.add(new JLabel("Column:"));
			    myPanel.add(columnIndex);
			    
			    int result = JOptionPane.showConfirmDialog(null, myPanel,"Please give the cordinates of the component", JOptionPane.OK_CANCEL_OPTION);
			    if(result==JOptionPane.CLOSED_OPTION||result==JOptionPane.CANCEL_OPTION||this.getRowIndex()<0||this.getRowIndex()>=rows||
			    		this.getColumnIndex()<0||this.getColumnIndex()>=columns){
			    	result = JOptionPane.CANCEL_OPTION;
			    	throw new WindowDialogException("Edit Dialog: "+result);
			    }
			}
			
			/**
			 * The row index getter.
			 * 
			 * @return the index of the row to be deleted
			 */
			public int getRowIndex(){
				return Integer.parseInt(rowIndex.getText());
			}
			
			/**
			 * The column index getter.
			 * 
			 * @return the index of the column to be deleted
			 */
			public int getColumnIndex(){
				return Integer.parseInt(columnIndex.getText());
			}	
		}
	}
}