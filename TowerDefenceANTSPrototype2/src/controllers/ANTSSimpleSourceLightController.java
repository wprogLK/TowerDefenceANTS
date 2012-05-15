package controllers;

import java.awt.Color;

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
	
	public ANTSSimpleSourceLightController(double posX, double posY, double radius, Color color)
	{
		this.model = new ANTSSimpleSourceLightModel(posX,posY,radius,color);
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
