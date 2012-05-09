/**
 * 
 */
package controllers.basics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import listeners.actionListeners.paint.ANTSOnlyDrawActionListener;
import model.basics.ANTSGameModel;
import view.basics.ANTSGameView;
import helper.ANTSGameLogicUpdater;
import helper.ANTSPainter;
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
	
	public ANTSGameController()
	{
		this.model = new ANTSGameModel();
		this.view = new ANTSGameView(this.model);
		
		this.createBulb();
	}
	
	private void createBulb()
	{
		ANTSSimpleSourceLightController simpleSourceController = new ANTSSimpleSourceLightController();
	}
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public ANTSIView getView() 
	{
		return this.view;
	}
}
