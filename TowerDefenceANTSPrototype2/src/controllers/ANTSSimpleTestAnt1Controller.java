package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import views.ANTSSimpleSourceLightView;
import views.ANTSSimpleTestAnt1View;
import models.ANTSSimpleSourceLightModel;
import models.ANTSSimpleTestAnt1Model;

public class ANTSSimpleTestAnt1Controller extends ANTSAbstractController implements ANTSIController
{
	private ANTSSimpleTestAnt1Model model;
	private ANTSSimpleTestAnt1View view;
	
	public ANTSSimpleTestAnt1Controller() 
	{
		this.model = new ANTSSimpleTestAnt1Model();
		this.view = new ANTSSimpleTestAnt1View(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
	}
	
	public ANTSSimpleTestAnt1Controller(double posX, double posY, Color color)
	{
		this.model = new ANTSSimpleTestAnt1Model(posX,posY,color);
		this.view = new ANTSSimpleTestAnt1View(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
	}
	
	public ANTSSimpleTestAnt1Controller(double posX, double posY, int width, int height, Color color, boolean isMouseListener)
	{
		this.model = new ANTSSimpleTestAnt1Model(posX, posY, width, height, color, isMouseListener);
		this.view = new ANTSSimpleTestAnt1View(this.model);
		
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
	
	public ANTSSimpleTestAnt1View getView()
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
	public void mouseClickedANTS(MouseEvent e) 
	{
		//Only an example
		this.model.switchLight();
		System.out.println("ANTS CLICKED");
	}	
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	@Override
	public void mouseDraggedANTS(MouseEvent e) 
	{
		this.model.setPosition(e.getX(), e.getY());	//TODO: Important: test this! is it always /2 ?  //Here not /2 because the center is in the left top corner!
	}
}
