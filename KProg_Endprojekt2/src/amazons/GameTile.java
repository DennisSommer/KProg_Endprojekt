package amazons;

import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameTile extends JPanel {

	private boolean isOnFire;
	private boolean isTaken;
	private GamePiece occupyingPiece;
	private JLabel emptyLabel;
	
	public GameTile(int takenByPiece) {
		super();
		setLayout(new GridLayout(1,1));
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
}
