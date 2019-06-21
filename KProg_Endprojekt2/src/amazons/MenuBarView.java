package amazons;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * @author Dennis Sommer(200925)
 *
 */
public class MenuBarView {

		AmazonsController controller;
		public MenuBarView(AmazonsView view,AmazonsController controller) {
			this.controller = controller;
			view.setJMenuBar(initMenuBar());
			 
		}
		
		private JMenuBar initMenuBar() {
			JMenuBar menuBar;
			JMenuItem menuItem;
			
			
			
			String[] menuList = {"Spiel", "Spielzug"};
			String[][] menuItemList =
				{
					{"Speichern", "Laden"}, 
					{"Zurücksetzen", "Alle ansehen"}
				};
			String[][] itemActionList = 
				{
					{"safeGame", "loadGame"},
					{"undoLastTurn", "replayAllTurns"}
				};
			
			
			menuBar = new JMenuBar();
			
			int iteration = 0;
			
			for(String menuString: menuList) {
				JMenu menu = createMenu(menuString);
				menuBar.add(menu);
				for(int item = 0; item < menuItemList[iteration].length; item++) {
					menuItem = createMenuItem(menuItemList[iteration][item], itemActionList[iteration][item]);
					menu.add(menuItem);
				}
				iteration++;
			}
	        
			return menuBar;
		}
		
		private JMenuItem createMenuItem(String item, String itemAction) {
			JMenuItem menuItem;
			
			menuItem = new JMenuItem(item);
			menuItem.addActionListener(controller);
			menuItem.setActionCommand(itemAction);
			return menuItem;
		}
		
		private JMenu createMenu(String menuString) {
			JMenu menu;
			
			menu = new JMenu(menuString);
			
			return menu; 
		}
}
