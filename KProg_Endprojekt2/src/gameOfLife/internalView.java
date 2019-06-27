package gameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


@SuppressWarnings("serial")
public class internalView extends JInternalFrame {
	
	protected Controller controller;
	protected View view;
	private JPanel panel;
	private ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
	private int gameCounter;
	
	public internalView(View view, Controller controller) {
		super("Game Definer", false, true, false, true);
		controller.frame = this;
		this.view = view;
		this.controller = controller;
		initInternalView();
		
	}

	private void initInternalView() {
		panel = new JPanel();
		this.setSize(200,200);
		this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		this.setLocation(view.getWidth()/2-this.getWidth()/2,view.getHeight()/2-this.getHeight()/2);
		this.setLayout(new GridLayout(3,1));
		this.setVisible(true);
		panel.setBackground(Color.black);
		
		
		addTextfieldPanel("Spalten", new JLabel(), new JTextField(8));
		addTextfieldPanel("Zeilen", new JLabel(), new JTextField(8));
		addButtonPanel(new JButton("Start new Life"), true);
		
	}

	private void addTextfieldPanel(String label, JLabel jLabel, JTextField jTextField) {
			JPanel panel = new JPanel();
			JPanel flowPanel = new JPanel();
			
			flowPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 18));
			panel.setLayout(new GridLayout(1,2));
			
			jLabel.setText(label);
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			
			textFieldList.add(jTextField);
			panel.add(jLabel);
			flowPanel.add(jTextField);
			panel.add(flowPanel);
			this.add(panel);
	}

	private void addButtonPanel(JButton jButton, boolean bool) {
		JPanel panel = new JPanel();
		
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					gameCounter++;
					new gameView(Integer.parseInt(textFieldList.get(0).getText()), Integer.parseInt(textFieldList.get(1).getText()), gameCounter);
				} catch(Exception ex) {
					controller.warningWindow();
					gameCounter--;
					ex.printStackTrace();
				}
			}
		});
		
		panel.add(jButton);
		this.add(panel);
	}
}
