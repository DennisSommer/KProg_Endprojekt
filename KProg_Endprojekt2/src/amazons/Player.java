package amazons;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	private boolean hisTurn;
	private boolean hasShot;
	
	private Color playerColor;
	private GamePiece gamePiece;
	private ArrayList<GamePiece> playerPieces;
	
	private int playerNumber;
	
	
	
	public Player(int playerNumber, int pieceAmount) {
		setPlayerNumber(playerNumber);
		setHisTurn(playerNumber);
		setHasShot(false);
		setPlayerColor(playerNumber);
		setPlayerPieces(pieceAmount);
		
	}

	private void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	private void setPlayerColor(int playerNumber) {
		switch(playerNumber) {
		case 1:
			playerColor = Color.white;
			break;
		case 2:
			playerColor = Color.black;
			break;
		default:
			break;
		}
	}

	private void setPlayerPieces(int pieceNumber) {
		playerPieces = new ArrayList<GamePiece>();
		for(int i = 1; i <= pieceNumber; i++) {
			gamePiece = new GamePiece(this);
			playerPieces.add(gamePiece);
			
		}
	}
	
	public ArrayList<GamePiece> getPlayerPieces(){
		return playerPieces;
	}

	public boolean isHisTurn() {
		return hisTurn;
	}

	public void setHisTurn(boolean hisTurn) {
		this.hisTurn = hisTurn;
	}

	private void setHisTurn(int playerNumber) {
		if(playerNumber == 1) {
			this.hisTurn = true;
		} else if(playerNumber == 2) {
			this.hisTurn = false;
		}
	}

	public boolean isHasShot() {
		return hasShot;
	}

	public void setHasShot(boolean hasShot) {
		this.hasShot = hasShot;
	}
	
}
