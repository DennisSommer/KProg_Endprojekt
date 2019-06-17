package amazons;

import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class GamePiece extends JLabel {

	
	private boolean isMoveable;
	private Player player;
	private Color pieceColor;
	private GameTile occupiedTile;
	private String imageURL;
	
	//TODO Move function/Shot Function
	
	//TODO Check where on map
	
	//TODO Check cornered/not moveable
	
	public GamePiece(Player player) {
		//this.setPreferredSize(new Dimension(50,50));
		setMoveable(true);
		setPlayer(player);
		setPieceColor(player.getPlayerColor());
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
}
