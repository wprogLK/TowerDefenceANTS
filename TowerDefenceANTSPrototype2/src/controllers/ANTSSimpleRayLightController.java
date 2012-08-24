package controllers;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import basics.ANTSFactory;

import views.ANTSSimpleRayLightView;
import views.ANTSSimpleRayLightView;

import models.ANTSSimpleRayLightModel;
import models.ANTSSimpleRayLightModel;

public class ANTSSimpleRayLightController extends ANTSAbstractController implements ANTSIController{

	private ANTSSimpleRayLightModel model;
	private ANTSSimpleRayLightView view;
	
	public ANTSSimpleRayLightController(double[] center, double velocity, double angle, Color sourceColor, ANTSFactory factory) 
	{
		this.model = new ANTSSimpleRayLightModel(center, velocity, angle, sourceColor, factory);
		this.view = new ANTSSimpleRayLightView(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
	}
	
	///////////
	//GETTERS//
	///////////
	
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSSimpleRayLightView getView()
	{
		return this.view;
	}
	
	public ANTSIView getIView()
	{
		return this.view;
	}

	///////////
	//SPECIAL//
	///////////
	
	public void addAngle(double angle, double[] center) 
	{
		this.model.addAngle(angle, center);
		
	}
}
