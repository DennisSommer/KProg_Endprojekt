package amazons;

import java.awt.Component;
import java.util.*;

public class AmazonsModel extends Observable {

	
	private int[][] map;
	private int[][] mapSizeTen = 	{
										{0,0,0,2,0,0,2,0,0,0},
										{0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0},
										{2,0,0,0,0,0,0,0,0,2},
										{0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0},
										{1,0,0,0,0,0,0,0,0,1},
										{0,0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0,0},
										{0,0,0,1,0,0,1,0,0,0},
									};
	private int[][] mapSizeSix = 	{
										{0,0,2,0,0,0},
										{0,0,0,0,0,0},
										{0,0,0,0,0,1},
										{1,0,0,0,0,0},
										{0,0,0,0,0,0},
										{0,0,0,2,0,0},
									};
	private GameTile[][] gameTileMap;
	private GamePiece pieceToMove;
	private GameTile clickedTile;
	private Player turningPlayer;
	private Player idlingPlayer;
	private int actionToDo;
	private int pieceAmount;
	
	public AmazonsModel() {
		
	}
	
	public void setMapSize(int mapSize) {
		switch(mapSize) {
		case 6:
			gameTileMap = new GameTile[mapSize][mapSize];
			this.map = mapSizeSix;
			setPieceAmount(2);
			break;
		case 10:
			gameTileMap = new GameTile[mapSize][mapSize];
			this.map = mapSizeTen;
			setPieceAmount(4);
			break;
		default:
			break;
		}
	}
	
	public int[][] getMap() {
		return map;
	}

	public int getMapSize() {
		return map[0].length;
	}

	public GameTile[][] getGameTileMap() {
		return gameTileMap;
	}

	public void setGameTileMap(GameTile[][] gameTileMap) {
		this.gameTileMap = gameTileMap;
	}

	private void setPieceAmount(int pieceAmount) {
		this.pieceAmount = pieceAmount;
	}
	
	public int getPieceAmount() {
		return pieceAmount;
	}

	public Player getTurningPlayer() {
		return turningPlayer;
	}

	public void setTurningPlayer(Player turningPlayer) {
		this.turningPlayer = turningPlayer;
	}
	
	public Player getIdlingPlayer() {
		return idlingPlayer;
	}
	
	public void setIdlingPlayer(Player idlingPlayer) {
		this.idlingPlayer = idlingPlayer;
	}

	public int getActionToDo() {
		return actionToDo;
	}

	public void setActionToDo(int actionToDo) {
		this.actionToDo = actionToDo;
	}

	public GameTile getClickedTile() {
		return clickedTile;
	}
	
	public void setClickedTile(Component eventTile) {
		for(GameTile[] field : getGameTileMap()) {
			for(GameTile tile : field) {
				if(eventTile == tile) {
					this.clickedTile = tile;
				}
			}
		}
	}

	public GamePiece getPieceToMove() {
		return pieceToMove;
	}
	
	public void setPieceToMove(GameTile tileWithPiece) {
		this.pieceToMove = tileWithPiece.getOccupyingPiece();
	}
}
