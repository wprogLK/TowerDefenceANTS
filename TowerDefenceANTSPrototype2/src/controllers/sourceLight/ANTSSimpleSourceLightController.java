package controllers.sourceLight;

import java.awt.Color;
import java.awt.event.MouseEvent;

import controllers.ANTSAbstractController;

import basics.ANTSFactory;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import views.sourceLight.ANTSSimpleSourceLightView;

import models.sourceLight.ANTSSimpleSourceLightModel;

public class ANTSSimpleSourceLightController extends ANTSAbstractController implements ANTSIController
{
	private ANTSSimpleSourceLightModel model;
	private ANTSSimpleSourceLightView view;
	
	public ANTSSimpleSourceLightController(double posX, double posY, double radius, Color color, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleSourceLightModel(posX,posY,radius,color,isMouseListener, factory);
		this.view = new ANTSSimpleSourceLightView(this.model);
		
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