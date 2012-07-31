package controllers;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import basics.ANTSFactory;

import views.ANTSSimpleRayLightView;
import models.ANTSSimpleRayLightModel;

public class ANTSSimpleRayLightController extends ANTSAbstractController implements ANTSIController{

	private ANTSSimpleRayLightModel model;
	private ANTSSimpleRayLightView view;
	
	public ANTSSimpleRayLightController(AffineTransform sourceMatrix, double velocity, double angle, Color sourceColor, ANTSFactory factory) 
	{
		this.model = new ANTSSimpleRayLightModel(sourceMatrix, velocity, angle, sourceColor, factory);
		this.view = new ANTSSimpleRayLightView(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
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
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////

}
