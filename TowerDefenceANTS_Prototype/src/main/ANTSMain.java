/**
 * 
 */
package main;

/**
 * @author Lukas
 *
 */
public class ANTSMain {

	private static ANTSGameDriver gameDriver;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		gameDriver=new ANTSGameDriver();
		gameDriver.startGame();
	}

}
