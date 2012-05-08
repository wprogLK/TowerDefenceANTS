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
		
//		ANTSGameLogicUpdater.addModel(model); //TODO
//		this.setupListeners();
	}

//	private void setupListeners()
//	{
//		view.addButtonSwitchListener(new SwitchListener());
//	}
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public ANTSIView getView() 
	{
		return this.view;
	}
	
	////////////////////////////////
	//INNER CLASSES: THE LISTENERS// 
	////////////////////////////////
	
//	class SwitchListener implements ActionListener
//	{
//		@Override
//		public void actionPerformed(ActionEvent e) 
//		{
//			//model.switchLight();
//		
//			view.refresh();
//		}
//		
//	}
}
