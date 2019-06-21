package amazons;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * 
 * @author Dennis Sommer(200925)
 *
 */
@SuppressWarnings("serial")
public class GameTile extends JPanel {

	private boolean isOnFire;
	private boolean isTaken;
	private GamePiece occupyingPiece;
	private JLabel emptyLabel;
	private Point position;
	private Map<Integer, GamePiece> visitors;
	
	public GameTile(int takenByPiece) {
		super();
		setLayout(new GridLayout(1,1));
		setOccupyingPiece(null);
		setOnFire(false);
		setTaken(takenByPiece);
		addEmptyLabel();
	}

	public boolean isOnFire() {
		return isOnFire;
	}

	public void setOnFire(boolean isOnFire) {
		this.isOnFire = isOnFire;
	}

	public boolean isTaken() {
		return isTaken;
	}

	private void setTaken(int takenBy) {
		switch(takenBy) {
		case 0:
			this.isTaken = false;
			break;
		case 1:
			this.isTaken = true;
			break;
		case 2:
			this.isTaken = true;
			break;
		default:
			break;
		}
	}
	
	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

	public GamePiece getOccupyingPiece() {
		return occupyingPiece;
	}

	public void setOccupyingPiece(GamePiece occupyingPiece) {
		this.occupyingPiece = occupyingPiece;
	}
	
	public JLabel getEmptyLabel() {
		return this.emptyLabel;
	}

	public void addEmptyLabel() {
		this.emptyLabel = new JLabel();
		this.add(this.emptyLabel);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Map<Integer, GamePiece> getVisitors() {
		return visitors;
	}

	public void setVisitors(Map<Integer, GamePiece> visitors) {
		this.visitors = visitors;
	}
	
	public void setVisitors(GamePiece visitor, int turn) {
		this.visitors.put(turn, visitor);
	}
}
