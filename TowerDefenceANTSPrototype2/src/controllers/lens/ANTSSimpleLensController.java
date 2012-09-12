package controllers.lens;

import java.awt.Color;
import java.awt.event.MouseEvent;

import controllers.ANTSAbstractController;

import basics.ANTSFactory;

import interfaces.ANTSIController;

import views.lens.ANTSSimpleLensView;
import models.lens.ANTSSimpleLensModel;

public class ANTSSimpleLensController extends ANTSAbstractController implements ANTSIController
{
	private ANTSSimpleLensModel model;
	private ANTSSimpleLensView view;
	
	public ANTSSimpleLensController(double posX, double posY, double radius, Color color, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleLensModel(posX,posY,radius,color,isMouseListener, factory);
		this.view = new ANTSSimpleLensView(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
	}
	
	///////////
	//GETTERS//
	///////////
	
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	@Override
	public void mouseDraggedANTS(MouseEvent e) 
	{
		this.model.setPosition((int) (e.getX()-this.model.getRadius()/2), (int) (e.getY()-this.model.getRadius()/2));
	}
}
