package gameOfLife;
import javax.swing.*;

@SuppressWarnings("serial")
public class View extends JFrame {
	
	protected Controller controller;
	private internalView internalFrameView;
	private JDesktopPane deskPane;

	public View() {
		
		initMainView();
		this.controller = new Controller();
		internalFrameView = new internalView(this, controller);
		this.add(internalFrameView);
	}
	
	public void initMainView() {
		
		this.deskPane = new JDesktopPane();
		this.deskPane.setDesktopManager(new DefaultDesktopManager());
		this.setContentPane(deskPane);
		this.setTitle("Game of Life");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
