package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;

import basics.ANTSFactory;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import views.ANTSSimpleSourceLight2View;
import views.ANTSSimpleSourceLightView;

import models.ANTSSimpleSourceLight2Model;
import models.ANTSSimpleSourceLightModel;

public class ANTSSimpleSourceLight2Controller extends ANTSAbstractController implements ANTSIController
{
	private ANTSSimpleSourceLight2Model model;
	private ANTSSimpleSourceLight2View view;
	
	public ANTSSimpleSourceLight2Controller(double posX, double posY, double radius, Color color, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleSourceLight2Model(posX,posY,radius,color,isMouseListener, factory);
		this.view = new ANTSSimpleSourceLight2View(this.model);
		
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
	
	public ANTSSimpleSourceLight2View getView()
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
		this.model.setPosition((int) (e.getX()-this.model.getRadius()/2), (int) (e.getY()-this.model.getRadius()/2));
	}
}
