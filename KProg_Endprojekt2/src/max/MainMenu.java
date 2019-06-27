package max;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.*;


@SuppressWarnings("serial")
public class MainMenu extends JFrame{
	public MainMenu() {
		initUI();
		
	}
	
	public void initUI() {
		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Main Menu");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initPanel();
		
		this.setVisible(true);
	}
	
	public void initPanel() {
		JPanel mainPanel = new JPanel();
		
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
		innerPanel.setBorder(new EmptyBorder(50,0,150,0));
		
		JButton startButton = new JButton("New Game");
		JButton loadButton = new JButton("Load Game");
		JButton saveButton = new JButton("Save Game");
		
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		startButton.addActionListener(new OpenG());
		loadButton.addActionListener(new OpenL());
		saveButton.addActionListener(new OpenS());
		
		innerPanel.add(startButton);
		innerPanel.add(Box.createVerticalStrut(10));
		innerPanel.add(loadButton);
		innerPanel.add(Box.createVerticalStrut(10));
		innerPanel.add(saveButton);
		mainPanel.add(innerPanel);
		this.add(mainPanel);
	}
	//anonymous classes
	public class OpenG implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new Max();
		}
	}
	public class OpenL implements ActionListener{
		public void actionPerformed(ActionEvent e){ }
	}
	public class OpenS implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				FileOutputStream fos = new FileOutputStream("max.xml");
				XMLEncoder encoder = new XMLEncoder(fos);
				encoder.setExceptionListener(new ExceptionListener() {
					@Override
					public void exceptionThrown(Exception ess) {
						ess.printStackTrace();
					}
				});
				encoder.writeObject(Max.class);
				encoder.close();
				fos.close();
			} catch(IOException es){
				es.printStackTrace();
			}
		}
	}
}
