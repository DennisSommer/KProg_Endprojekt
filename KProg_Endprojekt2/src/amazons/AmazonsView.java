package amazons;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import main.MainView;

@SuppressWarnings("serial")
public class AmazonsView extends JInternalFrame {

	private MainView view;
	private AmazonsController amazonsController;
	private AmazonsModel model;
	
	public AmazonsView(MainView view, int counter) {
		super("Amazons " + counter,true, true, false, true);
		this.view = view;
		amazonsController = new AmazonsController(this);
		this.model = amazonsController.model;
		initFieldChooser();
		initFieldChooserButtons();
	}
	
	private void initFieldChooser() {
		this.setSize(200,200);
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setLocation(view.getWidth()/2-this.getWidth()/2,view.getHeight()/2-this.getHeight()/2);
		this.setLayout(new GridLayout(2,1));
		this.setVisible(true);
		
	}
	
	private void initFieldChooserButtons() {
		JButton tenField = new JButton("10x10");
		JButton sixField = new JButton("6x6");
		
		tenField.addActionListener(amazonsController);
		sixField.addActionListener(amazonsController);
		
		tenField.setActionCommand("tenByTenField");
		sixField.setActionCommand("sixBySixField");
		
		this.add(tenField);
		this.add(sixField);
	}

	protected void resetWindow(int width, int height) {
		
		this.setSize(width, height);
		this.setLocation(view.getWidth()/2-this.getWidth()/2, view.getHeight()/2-this.getHeight()/2);
		
		Container ct = this.getContentPane();
		ct.removeAll();
		
	}
	
	protected void buildMap(int mapSize, int[][] mapArray) {
		System.out.println("Building Map");
		GameTile[][] map =  model.getGameTileMap();
		
		int fieldCounter = 0;
		int tileCounter = 0;
		
		final Color tileColorLight = new Color(254, 206, 160);
		final Color tileColorDark = new Color(211, 138, 69);
		
		boolean patterner = false;
		
		Container ct = this.getContentPane();
		ct.setLayout(new GridLayout(mapSize, mapSize));
		
		for(GameTile[] field : map) {
			for(GameTile tile : field) {	
				tile = new GameTile(mapArray[fieldCounter][tileCounter]);
				tile.setPosition(new Point(fieldCounter, tileCounter));
				tile.setVisitors(new HashMap<Integer, GamePiece>());
				tile.addMouseListener(amazonsController);
				if(!patterner) {
					tile.setBackground(tileColorDark);
					patterner = true;
				} else if(patterner) {
					tile.setBackground(tileColorLight);
					patterner = false;
				} 
				map[fieldCounter][tileCounter] = tile;
				ct.add(tile);
				tileCounter++;
				
			}
			tileCounter = 0;
			fieldCounter++;
			if(patterner) {
				patterner = false;
			} else if(!patterner) {
				patterner = true;
			}
			
		}
		model.setGameTileMap(map);
		fieldCounter = 0;
		ct.revalidate();
	}
	
	
}
