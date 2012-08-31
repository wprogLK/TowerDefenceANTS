package controllers;

import interfaces.ANTSIModel;
import interfaces.ANTSIRayController;
import interfaces.ANTSIView;
import interfaces.medium.ANTSIMediumController;

import java.awt.Color;

import basics.ANTSFactory;

import views.ANTSSimpleRayLightView;

import models.ANTSSimpleRayLightModel;

public class ANTSSimpleRayLightController extends ANTSAbstractController implements ANTSIRayController{

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
	
	@Override
	public double getRefractionIndex() 
	{
		return this.model.getCurrentMedium().getRefractionIndex();
	}
	
	//////////
	//SETTER//
	//////////
	
	@Override
	public void setCurrentMedium(ANTSIMediumController c) 
	{
		this.model.setCurrentMedium(c);
	}

	///////////
	//SPECIAL//
	///////////
	
	public void addAngle(double angle) 
	{
		this.model.addAngle(angle);
		
	}



}
