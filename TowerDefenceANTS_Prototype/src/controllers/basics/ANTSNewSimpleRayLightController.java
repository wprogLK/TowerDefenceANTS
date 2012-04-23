/**
 * 
 */
package controllers.basics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.basics.ANTSGameModel;
import model.basics.ANTSNewSimpleRayLightModel;
import model.basics.ANTSSimpleRayLightModel;
import model.basics.ANTSSimpleSourceLightModel;
import view.basics.ANTSGameView;
import view.basics.ANTSNewSimpleRayLightView;
import view.basics.ANTSSimpleRayLightView;
import view.basics.ANTSSimpleSourceLightView;
import interfaces.ANTSIController;
import interfaces.ANTSIView;

/**
 * @author lukas
 *
 */
public class ANTSNewSimpleRayLightController implements ANTSIController
{
	private ANTSNewSimpleRayLightView view;
	private ANTSNewSimpleRayLightModel model;
	
	
	public ANTSNewSimpleRayLightController(ANTSSimpleSourceLightModel lightSourceModel, double startAngle)
	{
		this.model = new ANTSNewSimpleRayLightModel(lightSourceModel,startAngle);
		this.view = new ANTSNewSimpleRayLightView(this.model);
		
		this.setupListeners();
	}

	private void setupListeners()
	{
		view.addButtonSwitchListener(new SwitchListener());
	}
	
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
	
	class SwitchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//model.switchLight();
		
			view.refresh();
		}
		
	}
}
