package controllers;

import interfaces.ANTSIModel;
import interfaces.ANTSIRayController;
import interfaces.ANTSIView;
import interfaces.medium.ANTSIMediumController;

import java.awt.Color;
import java.awt.geom.Point2D;

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
	
	@Override
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSSimpleRayLightView getView()
	{
		return this.view;
	}
	
	@Override
	public ANTSIView getIView()
	{
		return this.view;
	}
	
	@Override
	public double getRefractionIndex() 
	{
		return this.model.getCurrentMedium().getRefractionIndex();
	}
	
	@Override
	public Point2D.Double[] getVector()
	{
		return this.model.getVector();
	}
	
	//////////
	//SETTER//
	//////////
	
	@Override
	public boolean setCurrentMedium(ANTSIMediumController c) 
	{
		boolean sameMedium = this.model.getCurrentMedium().equals(c);
		this.model.setCurrentMedium(c);
		
		return sameMedium;
	}

	///////////
	//SPECIAL//
	///////////
	
	@Override
	public void update() 
	{
		this.updateCenter();
		
		this.model.update();
		this.model.setIsAlreadyUpdated(true);
	}
	
	private void updateCenter() 
	{
		this.model.setCenter(this.view.getCenter());
	}

	public void addAngle(double angle) 
	{
		this.model.addAngle(angle);
	}

	@Override
	public double getAngle() 
	{
		return this.model.getAngle();
	}

	@Override
	public double[] getCenter()
	{
		return this.model.getCenter();
	}
}
