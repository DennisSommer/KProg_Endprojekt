package main;

import javax.swing.*;
import java.awt.*;

/** Main class that is initiated by startup
 * 
 * @author Dennis Sommer(200925)
 * 
 */
@SuppressWarnings("serial")
public class MainView extends JFrame {

	private MainController controller;
	private JDesktopPane deskPane;
	
	public static void main(String[]args) {
		
		MainView main = new MainView();
		main.setVisible(true);
		
	}
	/**
	 * Creates Main Window for the Games and instantiates the Button Window
	 */
	public MainView() {
		controller = new MainController();
		controller.mainView = this;
		this.setupWindow();
		
		ButtonView bView = new ButtonView(this);
		
		bView.setupWindow();
		bView.setupPanel();
		bView.setVisible(true);
		
		
	}
	
	/**
	 * Does the Setup for the Main Window with its default propertys
	 */
	private void setupWindow() {
		this.deskPane = new JDesktopPane();
		this.deskPane.setDesktopManager(new DefaultDesktopManager());
		this.setContentPane(deskPane);
		this.setTitle("Games Window");
		this.setSize(1000, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 *Anonymous Class to setup the Button Window
	 */
	private class ButtonView extends JFrame {
		
		MainView main; //MainView to be added to
		
		public ButtonView(MainView view) {
			main = view;
		}
		
		/**
		 * Setup for the Button Window
		 */
		private void setupWindow() {
			this.setTitle("Game Chooser");
			this.setSize(100, 145);
			this.setLocation((int) (main.getWidth()/3-5), main.getHeight()/3-this.getHeight());
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		
		/**
		 * Creates the Panel in which the Buttons with the correct Games attached to
		 */
		private void setupPanel() {
			
			JPanel mainPanel = new JPanel(); //Panel to attach Buttons
			mainPanel.setLayout(new GridBagLayout()); //Setup Layout
			GridBagConstraints constraints = new GridBagConstraints(); //Set Layout Constraints
			
			JButton maxButton = new JButton("Max"); //Create First Button
			maxButton.addActionListener(controller); //Add the ActionListener to the Button
			maxButton.setActionCommand("startMax"); //Tell the button what it should do
			
			//Give new Constraints
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.ipadx = 20;
			constraints.ipady = 10;
			
			mainPanel.add(maxButton, constraints); //add button and constraints to panel
			
			JButton goLButton = new JButton("Game Of Life"); //Create second button
			goLButton.addActionListener(controller); //add actionListener to button
			goLButton.setActionCommand("startGameOfLife"); //tell the button what to do
			
			//Give new Constraints
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2,0,0,0);
			
			mainPanel.add(goLButton, constraints); //add button and constraints to panel
			
			JButton amazonsButton = new JButton("Amazons"); //Create third button
			amazonsButton.addActionListener(controller); //add actionListener to button
			amazonsButton.setActionCommand("startAmazons"); //tell the button what to do
			
			//Give new Constraints
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 2;
			
			
			mainPanel.add(amazonsButton, constraints);//add button and constraints to panel
			
			this.add(mainPanel);//add panel to frame
			
		}
		
	}
	
}
