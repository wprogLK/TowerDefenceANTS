package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import views.ANTSSimpleAntView;
import views.ANTSSimpleSourceLightView;
import models.ANTSSimpleAntModel;
import models.ANTSSimpleSourceLightModel;

public class ANTSSimpleAntController extends ANTSAbstractController implements ANTSIController
{
	private ANTSSimpleAntModel model;
	private ANTSSimpleAntView view;
	
	public ANTSSimpleAntController() 
	{
		this.model = new ANTSSimpleAntModel();
		this.view = new ANTSSimpleAntView(this.model);
		
		this.setIModel(this.model);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSSimpleAntView getView()
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
//		this.model.switchLight();
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
//		this.model.setPosition(e.getX()/2, e.getY()/2);	//TODO: Important: test this! is it always /2 ? 
	}
}
