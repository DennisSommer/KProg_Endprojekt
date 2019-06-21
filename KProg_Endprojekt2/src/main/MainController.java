package main;

import java.awt.event.*;
import java.lang.reflect.*;
import max.*;
import gameOfLife.*;
import amazons.*;

/**
 * 
 * @author Dennis Sommer(200925)
 *
 */
public class MainController implements ActionListener, MouseListener{

	private int amazonsCounter;
	public MainView mainView;
	
	public MainController() {
		amazonsCounter = 1;
	}

	/**
	 * Starts the maxView class
	 * Method is invoked by ActionEvent
	 */
	@SuppressWarnings("unused")
	private void startMax() {
		MaxView maxView = new MaxView(mainView);
		mainView.add(maxView);
		System.out.println("Max Gestartet");
	}
	/**
	 * Starts the goLView class
	 * Method is invoked by ActionEvent
	 */
	@SuppressWarnings("unused")
	private void startGameOfLife() {
		LifeView goLView = new LifeView();
		System.out.println("Game of Life gestartet");
	}
	/**
	 * Starts the amazonsView class
	 * Method is invoked by ActionEvent
	 */
	@SuppressWarnings("unused")
	private void startAmazons() {
		AmazonsView amazonsView = new AmazonsView(mainView, amazonsCounter);
		amazonsCounter++;
		mainView.add(amazonsView);
		System.out.println("Amazons Gestartet");
	}
	
	/** Invokes the given method by String inside the Controller
	 * 
	 * @param command is the String name of the method to invoke
	 */
	protected void methodInvoke(String command) {
		try {
			Method method = this.getClass().getDeclaredMethod(command); // Safe the Method that should be invoked
			method.setAccessible(true); //Set the Accessibility for the Method to true (with this private methods can be invoked)
			method.invoke(this); //Invoke the method
			method.setAccessible(false); //Set the Accessibility for the Method back to false (not needed but safer in my opinion)
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Calls the methodInvoke method with the given action Command of the pressed Button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		methodInvoke(e.getActionCommand());
	}
	
	/**
	 * Not used but needed for MouseListener implementation
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	/**
	 * Not used but needed for MouseListener implementation
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	/**
	 * Not used but needed for MouseListener implementation
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Not used but needed for MouseListener implementation
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	/**
	 * Not used but needed for MouseListener implementation
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
