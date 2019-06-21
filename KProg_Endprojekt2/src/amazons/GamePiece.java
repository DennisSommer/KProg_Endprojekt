package amazons;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * 
 * @author Dennis Sommer(200925)
 *
 */
@SuppressWarnings("serial")
public class GamePiece extends JLabel {

	
	private boolean isMoveable;
	private int pieceNumber;
	private Player player;
	private Color pieceColor;
	private GameTile occupiedTile;
	private ArrayList<GameTile> movementRange;
	private String imageURL;
	
	//TODO Check cornered/not moveable
	
	public GamePiece(Player player) {
		setMoveable(true);
		setPlayer(player);
		setPieceColor(player.getPlayerColor());
		setMovementRange(new ArrayList<GameTile>());
		setImage();
	}
	
	public void setImage() {
		setImageURL();
		this.setIcon(new ImageIcon(getClass().getResource(getImageURL())));
	}

	public boolean isMoveable() {
		return isMoveable;
	}

	public void setMoveable(boolean isMoveable) {
		this.isMoveable = isMoveable;
	}

	public Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}

	public Color getPieceColor() {
		return pieceColor;
	}

	private void setPieceColor(Color color) {
		pieceColor = color;
	}

	public GameTile getOccupiedTile() {
		return occupiedTile;
	}

	public void setOccupiedTile(GameTile occupiedTile) {
		this.occupiedTile = occupiedTile;
	}

	public String getImageURL() {
		return imageURL;
	}

	private void setImageURL() {
		if(getPieceColor() == Color.black) {
			this.imageURL = "figure_black.png";
		} else if(getPieceColor() == Color.white) {
			this.imageURL = "figure_white.png";
		}
		
	}

	public int getPieceNumber() {
		return pieceNumber;
	}

	public void setPieceNumber(int pieceNumber) {
		this.pieceNumber = pieceNumber;
	}

	public ArrayList<GameTile> getMovementRange() {
		return movementRange;
	}

	public void setMovementRange(GameTile movementRange) {
		this.movementRange.add(movementRange);
	}
	
	public void setMovementRange(ArrayList<GameTile> movementRange) {
		this.movementRange = movementRange;
	}
}
