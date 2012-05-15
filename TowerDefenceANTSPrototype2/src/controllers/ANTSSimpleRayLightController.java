package controllers;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import views.ANTSSimpleRayLightView;
import models.ANTSSimpleRayLightModel;

public class ANTSSimpleRayLightController {

	private ANTSSimpleRayLightModel model;
	private ANTSSimpleRayLightView view;
	
	public ANTSSimpleRayLightController(AffineTransform sourceMatrix, double velocity, double angle, Color sourceColor) 
	{
		this.model = new ANTSSimpleRayLightModel(sourceMatrix, velocity, angle, sourceColor);
		this.view = new ANTSSimpleRayLightView(this.model);
	}
	
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSSimpleRayLightView getView()
	{
		return this.view;
	}

}
