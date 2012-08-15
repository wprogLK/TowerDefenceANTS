package controllers;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import basics.ANTSFactory;

import views.ANTSSimpleRayLightView;
import views.ANTSSimpleRayLightView2;

import models.ANTSSimpleRayLightModel;
import models.ANTSSimpleRayLightModel2;

public class ANTSSimpleRayLightController2 extends ANTSAbstractController implements ANTSIController{

	private ANTSSimpleRayLightModel2 model;
	private ANTSSimpleRayLightView2 view;
	
	public ANTSSimpleRayLightController2(double[] center, double velocity, double angle, Color sourceColor, ANTSFactory factory) 
	{
		this.model = new ANTSSimpleRayLightModel2(center, velocity, angle, sourceColor, factory);
		this.view = new ANTSSimpleRayLightView2(this.model);
		
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
	
	public ANTSSimpleRayLightView2 getView()
	{
		return this.view;
	}
	
	public ANTSIView getIView()
	{
		return this.view;
	}
}
