package max;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import main.MainView;

@SuppressWarnings("serial")
public class MaxView extends JInternalFrame {

	private JPanel panel;
	private MainView view;
		
	public MaxView(MainView view) {
		super("Max", true, true, false, true);
		this.view = view;
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
		
		
	}
	
	
}
