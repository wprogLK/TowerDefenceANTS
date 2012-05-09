/**
 * 
 */
package controllers.basics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.basics.ANTSGameModel;
import model.basics.ANTSSimpleRayLightModel;
import model.basics.ANTSSimpleSourceLightModel;
import view.basics.ANTSGameView;
import view.basics.ANTSSimpleRayLightView;
import view.basics.ANTSSimpleSourceLightView;
import helper.ANTSGameLogicUpdater;
import interfaces.ANTSIController;
import interfaces.ANTSIView;

/**
 * @author lukas
 *
 */
public class ANTSSimpleRayLightController implements ANTSIController
{
	private ANTSSimpleRayLightView view;
	private ANTSSimpleRayLightModel model;
	
	
	public ANTSSimpleRayLightController(ANTSSimpleSourceLightModel lightSourceModel, double startAngle)
	{
		this.model = new ANTSSimpleRayLightModel(lightSourceModel,startAngle);
		this.view = new ANTSSimpleRayLightView(this.model);
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
