package controllers;

import views.ANTSGameView;
import views.ANTSSimpleRayLightView;
import models.ANTSGameModel;
import interfaces.ANTSIDriver;
import interfaces.ANTSIModel;

public class ANTSGameController {

	private ANTSIDriver driver;
	private ANTSGameModel model;
	private ANTSGameView view;
	
	public ANTSGameController(ANTSIDriver d)
	{
		this.driver = d;
		
		this.model = new ANTSGameModel();
		this.view = new ANTSGameView(this.model);
	}
	
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSGameView getView()
	{
		return this.view;
	}

}
