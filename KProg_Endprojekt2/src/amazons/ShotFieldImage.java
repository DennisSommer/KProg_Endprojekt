package amazons;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ShotFieldImage extends JLabel{
	
	public ShotFieldImage() {
		this.setIcon(new ImageIcon(getClass().getResource("shot_field.png")));
	}
	
	
}
