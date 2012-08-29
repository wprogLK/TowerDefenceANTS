package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import basics.ANTSFactory;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import views.ANTSSimpleSourceLightNeonView;
import views.ANTSSimpleSourceLightView;

import models.ANTSSimpleSourceLightModel;
import models.ANTSSimpleSourceLightNeonModel;

public class ANTSSimpleSourceLightNeonController extends ANTSAbstractController implements ANTSIController
{
	private ANTSSimpleSourceLightNeonModel model;
	private ANTSSimpleSourceLightNeonView view;
	
	public ANTSSimpleSourceLightNeonController(double posX, double posY, double length, Color color, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleSourceLightNeonModel(posX,posY,length,color,isMouseListener, factory);
		this.view = new ANTSSimpleSourceLightNeonView(this.model);
		
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
	
	public ANTSSimpleSourceLightNeonView getView()
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
	public void mouseLeftClickedANTS(MouseEvent e) 
	{
		this.model.switchLight();
	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	@Override
	public void mouseDraggedANTS(MouseEvent e) 
	{
//		this.model.setPosition(e.getX()/2, e.getY()/2);	// /2 because of the the center of the source is in the center of the ellipse
//		this.model.setPosition((int) (e.getX()-this.model.getRadius()), (int) (e.getY()-this.model.getRadius()));
		this.model.setPosition((int) (e.getX()-this.model.getLength()/2), (int) (e.getY()-this.model.getLength()/4));
	}
	
	@Override
	public void mouseWheelMovedANTS(MouseWheelEvent e)
	{
		this.model.rotate(e.getWheelRotation());
		
	}
	
	
	{
		
	}
}
