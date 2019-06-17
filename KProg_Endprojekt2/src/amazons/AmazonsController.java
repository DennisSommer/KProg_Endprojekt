package amazons;

import java.awt.event.*;
import java.util.*;
import main.MainController;

public class AmazonsController extends MainController  {

	AmazonsModel model;
	AmazonsView view;
	ArrayList<Player> players = new ArrayList<Player>();
	
	
	
	
	// TODO Bewegunseinschränkungen für die Spielsteine und Schüsse festlegen (Zuerst Horizontal/Vertikal danach Diagonal)
	
	public AmazonsController(AmazonsView view) {
		model = new AmazonsModel();
		this.view = view;
		
	}
	
	
	@SuppressWarnings("unused")
	private void tenByTenField() {
		System.out.println("TenByTenField Initiated");
		initGame(500, 500, 10);
	}
	
	@SuppressWarnings("unused")
	private void sixBySixField() {
		System.out.println("SixBySixField Initiated");
		initGame(300, 300, 6);
	}
	
	private void initGame(int width, int height, int mapSize) {
		view.resetWindow(width, height);
		model.setMapSize(mapSize);
		view.buildMap(model.getMapSize(), model.getMap());
		initPlayers();
		setPiecesOnBoard();
		model.setActionToDo(1);
	}
	
	private void initPlayers() {
		for(int i = 1; i<=2; i++) {
			Player player = new Player(i, model.getPieceAmount());
			players.add(player);
			if(player.isHisTurn()) {
				model.setTurningPlayer(player);
				
			} else if(!player.isHisTurn()) {
				model.setIdlingPlayer(player);
			}
		}
	}
	
	private void setPiecesOnBoard() {
		int playerPiecesWhite = 0;
		int playerPiecesBlack = 0;
		GamePiece piece;
		for(int i = 0; i < model.getMap().length; i++) {
			for(int j = 0; j < model.getMap()[i].length; j++) {
				if(model.getMap()[i][j] == 1) {
					piece = players.get(model.getMap()[i][j]-1).getPlayerPieces().get(playerPiecesWhite);
					piece.setOccupiedTile(model.getGameTileMap()[i][j]);
					piece.getOccupiedTile().remove(0);
					piece.getOccupiedTile().add(piece);
					piece.getOccupiedTile().setOccupyingPiece(piece);
					playerPiecesWhite++;
				} else if(model.getMap()[i][j] == 2) {
					piece = players.get(model.getMap()[i][j]-1).getPlayerPieces().get(playerPiecesBlack);
					piece.setOccupiedTile(model.getGameTileMap()[i][j]);
					piece.getOccupiedTile().remove(0);
					piece.getOccupiedTile().add(piece);
					piece.getOccupiedTile().setOccupyingPiece(piece);
					playerPiecesBlack++;
				}
			}	
		}
		view.getContentPane().revalidate();
	}
	
	private void movePieceToClickedTile() {
		
		GamePiece piece = model.getPieceToMove();
		GameTile newTile = model.getClickedTile();
		GameTile oldTile = piece.getOccupiedTile();
		
		oldTile.setTaken(false);
		oldTile.setOccupyingPiece(null);
		oldTile.remove(piece);
		oldTile.addEmptyLabel();
		oldTile.repaint();

		piece.setOccupiedTile(newTile);
		
		newTile.remove(newTile.getEmptyLabel());
		newTile.add(piece);
		newTile.setOccupyingPiece(piece);
		newTile.setTaken(true);
		newTile.repaint();
		
		piece.getPlayer().setHisTurn(false);
		piece.getPlayer().setHasShot(true);
		model.setActionToDo(3);
		
	}
	
	private void setTileOnFire() {
		
		GameTile clickedTile = model.getClickedTile();
		
		clickedTile.setOnFire(true);
		clickedTile.remove(clickedTile.getEmptyLabel());
		clickedTile.add(new ShotFieldImage());
		view.getContentPane().revalidate();
		
	}
	
	private void switchPlayerTurns() {
		Player turningPlayer = model.getTurningPlayer();
		Player idlingPlayer = model.getIdlingPlayer();
		
		idlingPlayer.setHisTurn(true);
		
		model.setIdlingPlayer(turningPlayer);
		model.setTurningPlayer(idlingPlayer);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		methodInvoke(e.getActionCommand());
	}

	@Override
	public void mouseClicked(MouseEvent ocE) {
		
		
		model.setClickedTile(ocE.getComponent());
		
		switch(model.getActionToDo()) {
		case 1:
			if(model.getClickedTile().isTaken() && model.getClickedTile().getOccupyingPiece().getPlayer().isHisTurn()) {
				model.setPieceToMove(model.getClickedTile());
				model.setActionToDo(2);
			}
			System.out.println("Selecting Piece of Player: " + model.getPieceToMove().getPlayer().getPlayerNumber());
			break;
		case 2:
			if(!model.getClickedTile().isTaken() && !model.getClickedTile().isOnFire()) {
				movePieceToClickedTile();
			} else if(model.getClickedTile().isTaken()){
				if(model.getClickedTile().getOccupyingPiece().getPlayer() == model.getTurningPlayer()) {
					model.setPieceToMove(model.getClickedTile());
				}
			} 
			System.out.println("Moving Piece to Clicked Tile");
			break;
		case 3:
			if(!model.getClickedTile().isTaken()) {
				setTileOnFire();
				switchPlayerTurns();
				model.setActionToDo(1);
			}
			System.out.println("Setting Clicked Tile on Fire and Switching Player Turns");
			System.out.println("Player in Action: " + model.getTurningPlayer().getPlayerNumber());
			System.out.println("Player thats Idling: " + model.getIdlingPlayer().getPlayerNumber());
			break;
		default:
			System.out.println("Out of Action range!!");
			break;
		}
		
	}
}
