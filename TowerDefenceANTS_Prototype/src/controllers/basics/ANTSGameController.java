/**
 * 
 */
package controllers.basics;

import model.basics.ANTSGameModel;
import view.basics.ANTSGameView;
import interfaces.ANTSIController;
import interfaces.ANTSIView;

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
	
	public ANTSGameController()
	{
		this.model = new ANTSGameModel();
		this.view = new ANTSGameView(this.model);
	}

	@Override
	public ANTSIView getView() 
	{
		return this.view;
	}
}
