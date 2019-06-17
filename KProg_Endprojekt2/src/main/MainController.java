package main;

import java.awt.event.*;
import java.lang.reflect.*;
import max.*;
import gameOfLife.*;
import amazons.*;


public class MainController implements ActionListener, MouseListener{

	private int amazonsCounter;
	public MainView mainView;
	
	public MainController() {
		amazonsCounter = 1;
	}

	@SuppressWarnings("unused")
	private void startMax() {
		MaxView maxView = new MaxView(mainView);
		mainView.add(maxView);
		System.out.println("Max Gestartet");
	}
	
	@SuppressWarnings("unused")
	private void startGameOfLife() {
		LifeView goLView = new LifeView();
		System.out.println("Game of Life gestartet");
	}
	
	@SuppressWarnings("unused")
	private void startAmazons() {
		AmazonsView amazonsView = new AmazonsView(mainView, amazonsCounter);
		amazonsCounter++;
		mainView.add(amazonsView);
		System.out.println("Amazons Gestartet");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		methodInvoke(e.getActionCommand());
	}
	
	protected void methodInvoke(String command) {
		try {
			Method method = this.getClass().getDeclaredMethod(command);
			method.setAccessible(true);
			method.invoke(this);
			method.setAccessible(false);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
