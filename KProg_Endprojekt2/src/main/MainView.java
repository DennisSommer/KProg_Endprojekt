package main;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private MainController controller;
	private JDesktopPane deskPane;
	
	public static void main(String[]args) {
		
		MainView main = new MainView();
		main.setVisible(true);
		
	}
	
	public MainView() {
		controller = new MainController();
		controller.mainView = this;
		this.setupWindow();
		
		ButtonView bView = new ButtonView(this);
		
		bView.setupWindow();
		bView.setupPanel();
		bView.setVisible(true);
		
		
	}
	
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
	
	private class ButtonView extends JFrame {
		
		MainView main;
		
		public ButtonView(MainView view) {
			main = view;
		}
		
		private void setupWindow() {
			this.setTitle("Game Chooser");
			this.setSize(100, 145);
			this.setLocation((int) (main.getWidth()/3-5), main.getHeight()/3-this.getHeight());
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		
		private void setupPanel() {
			
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			
			JButton maxButton = new JButton("Max");
			maxButton.addActionListener(controller);
			maxButton.setActionCommand("startMax");
			
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.ipadx = 20;
			constraints.ipady = 10;
			
			mainPanel.add(maxButton, constraints);
			
			JButton goLButton = new JButton("Game Of Life");
			goLButton.addActionListener(controller);
			goLButton.setActionCommand("startGameOfLife");
			
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2,0,0,0);
			
			mainPanel.add(goLButton, constraints);
			
			JButton amazonsButton = new JButton("Amazons"); 
			amazonsButton.addActionListener(controller);
			amazonsButton.setActionCommand("startAmazons");
			
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 2;
			
			
			mainPanel.add(amazonsButton, constraints);
			
			this.add(mainPanel);
			
		}
		
	}
	
}
