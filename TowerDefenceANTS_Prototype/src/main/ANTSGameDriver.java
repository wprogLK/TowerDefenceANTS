/**
 * 
 */
package main;

import interfaces.ANTSIView;
import controllers.basics.ANTSGameController;
import controllers.basics.ANTSWindowController;
/**
 * @author Lukas
 *
 */
public class ANTSGameDriver 
{
	private ANTSWindowController windowController;	//The "main" controller
	private ANTSGameController gameController;
	
	private ANTSIView startView;
	
	public ANTSGameDriver()
	{
		this.windowController = new ANTSWindowController();
		this.gameController = new ANTSGameController();
		
		this.setupStartView();
	}
	public void startGame()
	{
		this.windowController.showWindow();
	}
	
	private void setupStartView()
	{
		this.startView = this.gameController.getView();
		this.windowController.setupView(this.startView);
	}

}
