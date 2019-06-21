package amazons;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import main.MainView;

/**
 * 
 * @author Dennis Sommer(200925)
 *
 */
@SuppressWarnings("serial")
public class AmazonsView extends JInternalFrame {

	private MainView view; //Main View
	private AmazonsController amazonsController; //Controller of the Amazons Game
	private AmazonsModel model; //Model of the Amazons game
	
	/** Constructor that creates the View and Starts the whole cycle
	 * 
	 * @param view to be attached to
	 * @param counter how many of this games have already been attached
	 */
	public AmazonsView(MainView view, int counter) {
		super("Amazons " + counter,true, true, false, true);
		this.view = view;
		amazonsController = new AmazonsController(this);
		this.model = amazonsController.model;
		initFieldChooser();
		initFieldChooserButtons();
	}
	
	/** Initiates the window that determines the size of the Gamefield
	 * 
	 */
	private void initFieldChooser() {
		this.setSize(200,200);
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setLocation(view.getWidth()/2-this.getWidth()/2,view.getHeight()/2-this.getHeight()/2);
		this.setLayout(new GridLayout(2,1));
		this.setVisible(true);
		
	}
	
	/** Initiates and attaches the Buttons to the panel
	 * 
	 */
	private void initFieldChooserButtons() {
		//Create buttons
		JButton tenField = new JButton("10x10"); 
		JButton sixField = new JButton("6x6");
		
		//Add actionListener to buttons
		tenField.addActionListener(amazonsController);
		sixField.addActionListener(amazonsController);
		
		//Tell the buttons what to do when they are activated
		tenField.setActionCommand("tenByTenField");
		sixField.setActionCommand("sixBySixField");
		
		//Add the Buttons to the View
		this.add(tenField);
		this.add(sixField);
	}

	/** Resizes the window to given Dimension and relocates it
	 * 
	 * @param width to be resized to
	 * @param height to be resized to
	 */
	protected void resetWindow(int width, int height) {
		
		this.setSize(width, height); //resize the window
		this.setLocation(view.getWidth()/2-this.getWidth()/2, view.getHeight()/2-this.getHeight()/2); //relocate the Window to middle of the mainView
		
		Container ct = this.getContentPane();
		ct.removeAll(); //Clear the Container for further recalibration
		
	}
	/** Builds the Map on which the game is to be Displayed
	 * 
	 * @param mapSize size of the Quadratic Gamefield
	 * @param mapArray array with Information on where to put the GamePieces at buildup
	 */
	protected void buildMap(int mapSize, int[][] mapArray) {
		System.out.println("Building Map");
		GameTile[][] map =  model.getGameTileMap(); //Fetch the models Gametile map
		
		int fieldCounter = 0; //Position counter for rows  
		int tileCounter = 0;  //Position counter for columns
		
		final Color tileColorLight = new Color(254, 206, 160); //Light tile Color
		final Color tileColorDark = new Color(211, 138, 69); //Dark tile Color
		
		boolean patterner = false; //needed for the Checkered look
		
		Container ct = this.getContentPane(); //Get the Container
		ct.setLayout(new GridLayout(mapSize, mapSize)); //reset the layout to variable GridLayout
		
		for(GameTile[] field : map) { //for each row in map 
			for(GameTile tile : field) { // for each column in map
				tile = new GameTile(mapArray[fieldCounter][tileCounter]); //Create new tile
				tile.setPosition(new Point(fieldCounter, tileCounter)); //Tell tile where it is positioned
				tile.setVisitors(new HashMap<Integer, GamePiece>()); //Create the visitors list for the Tile
				tile.addMouseListener(amazonsController); //add action listener to tile
				if(!patterner) {
					tile.setBackground(tileColorDark); //Give tile dark color
					patterner = true;
				} else if(patterner) {
					tile.setBackground(tileColorLight); //Give tile light color
					patterner = false;
				} 
				map[fieldCounter][tileCounter] = tile; //add tile to the Map
				ct.add(tile); //add tile to the View
				tileCounter++; //increase the column
				
			}
			tileCounter = 0; //reset column
			fieldCounter++; //increase the row+
			//Reevaluate the patterner for Checkered look
			if(patterner) { 
				patterner = false;
			} else if(!patterner) {
				patterner = true;
			}
			
		}
		model.setGameTileMap(map); //Give model the newly created Map
		fieldCounter = 0; //reset row
		ct.revalidate(); //Tell view to show the new Layout
	}
	
	
}
