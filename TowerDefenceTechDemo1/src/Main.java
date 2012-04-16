import view.ViewGame;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		startGame();

	}
	
	public static void startGame()
	{
		ViewGame view = new ViewGame();
		view.repaint();
	}

}
