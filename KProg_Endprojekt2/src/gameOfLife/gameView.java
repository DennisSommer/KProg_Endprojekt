package gameOfLife;

import javax.swing.*;
import java.awt.*;
import java.util.*;


@SuppressWarnings("serial")
public class gameView extends JFrame{
	
	private Controller controller;
	public int col;
	public int span;
	private ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	static JPanel[][] gamePanels;
	gameMenuView gameMenu;
	
	public gameView(int row, int col, int titleNumber) {
		this.controller = new Controller();
		controller.gameView = this;
		controller.frameGroup.add(this);
		this.col = row;
		this.span = col;
		this.setTitle("Game of Life " + titleNumber);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.gameMenu = new gameMenuView(this, controller);
		initPanelLayout();
		
	}
	
	public gameView(gameView parentView) {
		this.controller =  parentView.controller;
		controller.frameGroup.add(this);
		this.col = parentView.col;
		this.span = parentView.span;
		this.setTitle(parentView.getTitle() + " copy");
		this.setSize(parentView.getWidth(), parentView.getHeight());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.gameMenu = new gameMenuView(this, controller);
		initPanelLayout();

	} 

	private void initPanelLayout() {
		JPanel mainPanel = new JPanel(new GridLayout(col, span));
		gamePanels = new JPanel[col][span];
		for(int c = 0; c < col; c++){
			for(int r = 0; r < span; r++){
				gamePanels[c][r] = new JPanel(new FlowLayout());
				gamePanels[c][r].setMinimumSize(new Dimension(20,20));
				gamePanels[c][r].setBackground(Color.GRAY);
				gamePanels[c][r].setBorder(BorderFactory.createLineBorder(Color.WHITE));
				panelList.add(gamePanels[c][r]);
				mainPanel.add(gamePanels[c][r]);
			}
		}
		this.getContentPane().add(mainPanel);
		new Model(col,span);
	}

	public static void updatePanels(int[][] state){
		for(int c = 0; c < state.length; c++){
			for(int r = 0; r < state[0].length; r++){
				if(state[c][r] == 1){
					gamePanels[c][r].setBackground(Color.RED);
				}else{
					gamePanels[c][r].setBackground(Color.GRAY);
				}
			}
		}
	}
}