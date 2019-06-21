package amazons;

import java.awt.event.*;
import java.util.*;

import main.MainController;

public class AmazonsController extends MainController  {

	AmazonsModel model;
	AmazonsView view;

	public AmazonsController(AmazonsView view) {
		model = new AmazonsModel();
		this.view = view;		
	}
	
	@SuppressWarnings("unused")
	private void tenByTenField() {
		System.out.println("TenByTenField Initiated");
		initGame(10);
	}
	
	@SuppressWarnings("unused")
	private void sixBySixField() {
		System.out.println("SixBySixField Initiated");
		initGame(6);
	}
	
	
	private void initGame(int mapSize) {
		view.resetWindow(47*mapSize, 47*mapSize + 30);
		model.setMapSize(mapSize);
		new MenuBarView(view, this);
		view.buildMap(model.getMapSize(), model.getMap());
		initPlayers();
		setPiecesOnBoard();
		buildMapArray();
		model.setActionToDo(1);
	}
	
	private void initPlayers() {
		model.setPlayers(new ArrayList<Player>());
		for(int i = 1; i<=2; i++) {
			Player player = new Player(i, model.getPieceAmount());
			model.getPlayers().add(player);
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
		
		for(int i = 0; i < model.getMap().length; i++) {
			for(int j = 0; j < model.getMap()[i].length; j++) {
				if(model.getMap()[i][j] != 0) {
					switch(model.getMap()[i][j]) {
					case 1:
						setPiece(model.getPlayers().get(model.getMap()[i][j]-1), playerPiecesWhite, model.getGameTileMap()[i][j]);
						playerPiecesWhite++;
						break;
					case 2:
						setPiece(model.getPlayers().get(model.getMap()[i][j]-1), playerPiecesBlack, model.getGameTileMap()[i][j]);
						playerPiecesBlack++;
						break;
					default:
						break;
					}
				}
			}	
		}
		view.getContentPane().revalidate();
	}
	
	private void setPiece(Player player, int pieceNumber, GameTile tile) {
		GamePiece piece = player.getPlayerPieces().get(pieceNumber);
		piece.setPieceNumber(pieceNumber);
		piece.setOccupiedTile(tile);
		piece.getOccupiedTile().remove(0);
		piece.getOccupiedTile().add(piece);
		piece.getOccupiedTile().setOccupyingPiece(piece);
		piece.getOccupiedTile().setVisitors(piece, model.getMapChanges().size());
		piece.getOccupiedTile().repaint();
		pieceMovementRange(piece);
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
		newTile.setVisitors(piece, model.getMapChanges().size());
		newTile.setTaken(true);
		newTile.repaint();
		
		
		piece.getPlayer().setHisTurn(false);
		
		model.getMap()[oldTile.getPosition().x][oldTile.getPosition().y] = 0;
		model.getMap()[newTile.getPosition().x][newTile.getPosition().y] = piece.getPlayer().getPlayerNumber();
		model.setActionToDo(3);
		
	}
	
	private void pieceMovementRange(GamePiece piece) {
		
		int row; //Current Row in GameTileMap
		int column; //Current Column in GameTileMap
		int tileNumerator; //Number of Tiles from direction that have been saved in Array
		int diagUp; //Current Diagonal tile starting from the most bottom part of the Map
		int diagDown; //Current Diagonal tile starting from the most upper part of the Map
		int caseNumber; //Current Number the SwitchCase is Using to fill the Array Sorted out
		int segmentSize; //Size of the last Segment that was filled into the Array
		int[] segmentFlags = new int[4];
		
		piece.setMovementRange(new ArrayList<GameTile>());
		
		
		for(row = 0; row < model.getGameTileMap().length; row++) {
			for(column = 0; column < model.getGameTileMap()[row].length; column++) {
				GameTile tile = model.getGameTileMap()[row][column];
				if(tile.isTaken()) {
					if(tile.getOccupyingPiece() == piece) {
						for(segmentSize = 0, caseNumber = 0; caseNumber < 4; caseNumber++) {
							for(tileNumerator = 0, diagUp = row+column, diagDown=row-column; tileNumerator < model.getGameTileMap().length; tileNumerator++, diagUp--, diagDown++) {
								switch(caseNumber) {
								case 0:
									piece.setMovementRange(model.getGameTileMap()[row][tileNumerator]);
									segmentSize++;
									break;
								case 1:
									piece.setMovementRange(model.getGameTileMap()[tileNumerator][column]);
									segmentSize++;
									break;
								case 2:
									if(diagUp < model.getMapSize() && diagUp >= 0) {
										piece.setMovementRange(model.getGameTileMap()[diagUp][tileNumerator]);
										segmentSize++;
									}
									break;
								case 3:
									if(diagDown < model.getMapSize() && diagDown >= 0) {
										piece.setMovementRange(model.getGameTileMap()[diagDown][tileNumerator]);
										segmentSize++;
									}
									break;
								default:
									break;
								}	
							}
							segmentFlags[caseNumber] = segmentSize;
						}
						piece.setMovementRange(splitMovementArrayToSegmentsAndClean(piece, segmentFlags));
						break;
					}
				} 
			}
		}
	}
	
	private ArrayList<GameTile> splitMovementArrayToSegmentsAndClean(GamePiece piece, int[] segmentSize){
		ArrayList<GameTile> cleanedArray = new ArrayList<GameTile>();
		
		for(int i = 0, k = 0; i < 4; i++) {
			ArrayList<GameTile> segment = new ArrayList<GameTile>();
			for(int j = k; j < segmentSize[i]; j++, k++ ) {
				segment.add(piece.getMovementRange().get(j));
			}
			cleanedArray.addAll(cleanSegment(segment, piece));
		}
		
		return cleanedArray;
	}
	
	private ArrayList<GameTile> cleanSegment(ArrayList<GameTile> segmentToClean, GamePiece piece){
		ArrayList<GameTile> cleanedSegment = new ArrayList<GameTile>();
		
		boolean hadPieceToMove = false;
		
		for(int i = 0; i < segmentToClean.size(); i++) {
			GameTile tile = segmentToClean.get(i);
			
			if(tile.getOccupyingPiece() == piece) {
				hadPieceToMove = true;
			}
			if(tile.isTaken() || tile.isOnFire()) {
				if(hadPieceToMove && tile.getOccupyingPiece() != piece) {
					break;
				} else if(!hadPieceToMove) {
					cleanedSegment.removeAll(cleanedSegment);
				}
			} else {
				cleanedSegment.add(tile);
			}
		}
		return cleanedSegment;
	}
	
	private void setTileOnFire(GameTile tile) {
		
		tile.setOnFire(true);
		tile.remove(tile.getEmptyLabel());
		tile.add(new ShotFieldImage());
		tile.repaint();
		model.getMap()[tile.getPosition().x][tile.getPosition().y] = 3;
		view.getContentPane().revalidate();
	}
	
	private void switchPlayerTurns() {
		Player turningPlayer = model.getTurningPlayer();
		Player idlingPlayer = model.getIdlingPlayer();
		
		idlingPlayer.setHisTurn(true);
		
		model.setIdlingPlayer(turningPlayer);
		model.setTurningPlayer(idlingPlayer);
	}
	
	private void buildMapArray() {
		int[][] map = new int[model.getMapSize()][model.getMapSize()];
		
		for(int i = 0; i < model.getMapSize(); i++) {
			for(int j = 0; j < model.getMapSize(); j++) {
				GameTile tile = model.getGameTileMap()[i][j];
				if(tile.isTaken()) {
					map[i][j] = tile.getOccupyingPiece().getPlayer().getPlayerNumber();
				} else if(tile.isOnFire()) {
					map[i][j] = 3;
				} else {
					map[i][j] = 0;
				}
			}
		}
		
		model.setMapChanges(map);
	}
	
	@SuppressWarnings("unused")
	private void safeGame() {
		System.out.println("Spiel wurde Gespeichert");   
	}
	
	
	@SuppressWarnings("unused")
	private void loadGame() {
		System.out.println("Spiel wird geladen");
	}

	@SuppressWarnings("unused")
	private void replayAllTurns() throws InterruptedException {
		System.out.println("Alle bisherigen Spielzüge werden abgespielt");
		Timer timer = new Timer();
		ArrayList<Integer> targetTurns = new ArrayList<Integer>();
		ArrayList<Integer> currentTurns = new ArrayList<Integer>();
		
		for(int targetTurn = 0, currentTurn = -1; targetTurn < model.getMapChanges().size(); targetTurn++, currentTurn++) {
			//resetBoardWithDelay(targetTurn, currentTurn, timer);
			//System.out.println("Aussen" + currentTurn + " " + targetTurn);
			targetTurns.add(targetTurn);
			currentTurns.add(currentTurn);
		}
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				resetBoardWithDelay(targetTurns, currentTurns);
				if(targetTurns.size() == 0) {
					timer.cancel();
				}
			}
		}, 5*100, 2*1000);
	}
	
	private void resetBoardWithDelay(ArrayList<Integer> targetTurns, ArrayList<Integer> currentTurns) {
		
		
		if(currentTurns.get(0) == -1) {
			resetBoard(targetTurns.get(0), model.getMapChanges().size() -1);
			targetTurns.remove(0);
			currentTurns.remove(0);
		} else {
			resetBoard(targetTurns.get(0), currentTurns.get(0));
			targetTurns.remove(0);
			currentTurns.remove(0);
		}
	}
	
	@SuppressWarnings("unused")
	private void undoLastTurn() {
		Map<Integer, int[][]> mapChanges = model.getMapChanges();
		
		resetBoard(mapChanges.size() -2, mapChanges.size()-1);
		
		mapChanges.remove(mapChanges.size() -1);
		model.setMapChanges(mapChanges);
	}
	

	private void resetBoard(int targetTurn, int currentTurn) {
		int[][] currentMapping = model.getMapChanges().get(currentTurn);
		int[][] targetMapping = model.getMapChanges().get(targetTurn);
		ArrayList<GameTile> targetMappingTileList = new ArrayList<GameTile>();
		ArrayList<Integer> mappingMethodList = new ArrayList<Integer>();
		ArrayList<GamePiece> pieceList = new ArrayList<GamePiece>();
	
		for(int i = 0; i < model.getMapSize(); i++) {
			for(int j = 0; j < model.getMapSize(); j++) {
				if(targetMapping[i][j] != currentMapping[i][j]) {
					targetMappingTileList.add(model.getGameTileMap()[i][j]);
					pieceList.add(model.getGameTileMap()[i][j].getVisitors().get(targetTurn));
					mappingMethodList.add(targetMapping[i][j]);
				}
			}
		}
		for(int i = 0; i < targetMappingTileList.size(); i++) {
			recalibrateTile(targetMappingTileList.get(i), pieceList.get(i), mappingMethodList.get(i));
		}
	}
	
	private void recalibrateTile(GameTile tile, GamePiece piece, int method) {
		
		if(method == 0) {
			cleanTile(tile);
		} else if(method == 1 || method == 2) {
			tile.setTaken(true);
			tile.setOccupyingPiece(piece);
			tile.removeAll();
			tile.add(piece); 
			tile.repaint();
			piece.setOccupiedTile(tile);
			pieceMovementRange(piece);
		} else if(method == 3) {
			setTileOnFire(tile);
		}
		
		if(model.getActionToDo() == 1) {
			switchPlayerTurns();
			model.setActionToDo(3);
		} else {
			model.setActionToDo(model.getActionToDo()-1);
		}
	}
	
	private void cleanTile(GameTile tile) {
		tile.setTaken(false);
		tile.setOnFire(false);
		tile.setOccupyingPiece(null);
		tile.removeAll();
		tile.addEmptyLabel();
		tile.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent ocE) {
		
		model.setClickedTile(ocE.getComponent());
		
		switch(model.getActionToDo()) {
		case 1:
			if(model.getClickedTile().isTaken()) {
				if(model.getClickedTile().getOccupyingPiece().getPlayer().isHisTurn()) {
					model.setPieceToMove(model.getClickedTile());
					pieceMovementRange(model.getPieceToMove());
					model.setActionToDo(2);
					System.out.println("Selecting Piece of Player: " + model.getPieceToMove().getPlayer().getPlayerNumber());
				}
			}
			break;
		case 2:
			if(model.getPieceToMove().getMovementRange().contains(model.getClickedTile())) {
				movePieceToClickedTile();
				pieceMovementRange(model.getPieceToMove());
				buildMapArray();
				System.out.println("Moving Piece to Clicked Tile");
			}else if(model.getClickedTile().isTaken()) { 
				if(model.getClickedTile().getOccupyingPiece().getPlayer() == model.getTurningPlayer()) {
				model.setPieceToMove(model.getClickedTile());
				System.out.println("Switching Piece for Player: " + model.getPieceToMove().getPlayer().getPlayerNumber());
				}
			}
			break;
		case 3:
			if(model.getPieceToMove().getMovementRange().contains(model.getClickedTile())) {
				setTileOnFire(model.getClickedTile());
				buildMapArray();
				switchPlayerTurns();
				model.setActionToDo(1);

				System.out.println("Setting Clicked Tile on Fire and Switching Player Turns");
				System.out.println("Player in Action: " + model.getTurningPlayer().getPlayerNumber());
				System.out.println("Player thats Idling: " + model.getIdlingPlayer().getPlayerNumber());
			}
			break;
		default:
			System.out.println("Out of Action range!!");
			break;
		}
	}
}
