package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import views.ANTSSimpleSourceLightView;
import models.ANTSSimpleSourceLightModel;

public class ANTSSimpleSourceLightController extends ANTSAbstractController implements ANTSIController
{
	private ANTSSimpleSourceLightModel model;
	private ANTSSimpleSourceLightView view;
	
	public ANTSSimpleSourceLightController() 
	{
		this.model = new ANTSSimpleSourceLightModel();
		this.view = new ANTSSimpleSourceLightView(this.model);
	
		this.setIModel(this.model);
	}
	
	public ANTSSimpleSourceLightController(double posX, double posY, double radius, Color color)
	{
		this.model = new ANTSSimpleSourceLightModel(posX,posY,radius,color);
		this.view = new ANTSSimpleSourceLightView(this.model);
		
		this.setIModel(this.model);
	}
	
	public ANTSSimpleSourceLightController(double posX, double posY, double radius, Color color, boolean isMouseListener)
	{
		this.model = new ANTSSimpleSourceLightModel(posX,posY,radius,color,isMouseListener);
		this.view = new ANTSSimpleSourceLightView(this.model);
		
		this.setIModel(this.model);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSSimpleSourceLightView getView()
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
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//Only an example
		this.model.switchLight();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		this.model.setDragged(false);
	}
	
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		this.model.setDragged(true);
		this.model.setPosition(e.getX()/2, e.getY()/2);	//TODO: Important: test this! is it always /2 ? 
	}
}
