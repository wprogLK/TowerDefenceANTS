package controllers;

import interfaces.ANTSIModel;
import views.ANTSSimpleSourceLightView;
import models.ANTSSimpleSourceLightModel;

public class ANTSSimpleSourceLightController {

	private ANTSSimpleSourceLightModel model;
	private ANTSSimpleSourceLightView view;
	
	public ANTSSimpleSourceLightController() 
	{
		this.model = new ANTSSimpleSourceLightModel();
		this.view = new ANTSSimpleSourceLightView(this.model);
	}
	
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSSimpleSourceLightView getView()
	{
		return this.view;
	}

}
