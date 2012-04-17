/**
 * 
 */
package controllers.basics;

import model.basics.ANTSGameModel;
import view.basics.ANTSGameView;
import interfaces.ANTSIController;

/**
 * @author lukas
 *
 */
public class ANTSGameController implements ANTSIController
{
	private ANTSGameView view;
	private ANTSGameModel model;
	
	public ANTSGameController(ANTSGameView view, ANTSGameModel model)
	{
		this.view = view;
		this.model = model;
	}
}
