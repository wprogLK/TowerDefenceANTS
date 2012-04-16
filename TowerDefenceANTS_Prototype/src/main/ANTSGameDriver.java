/**
 * 
 */
package main;

import view.basics.ANTSGameMainWindow;

/**
 * @author Lukas
 *
 */
public class ANTSGameDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		startGame();
	}
	
	public static void startGame()
	{
		ANTSGameMainWindow window = new ANTSGameMainWindow();
		window.repaint();
	}

}
