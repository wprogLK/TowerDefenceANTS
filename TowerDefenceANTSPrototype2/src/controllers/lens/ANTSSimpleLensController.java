package controllers.lens;

import java.awt.Color;
import java.awt.event.MouseEvent;

import controllers.ANTSAbstractController;

import basics.ANTSFactory;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import views.lens.ANTSSimpleLensView;
import views.sourceLight.ANTSSimpleSourceLightView;

import models.lens.ANTSSimpleLensModel;
import models.sourceLight.ANTSSimpleSourceLightModel;

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
//		this.model.setPosition(e.getX()/2, e.getY()/2);	// /2 because of the the center of the source is in the center of the ellipse
//		this.model.setPosition((int) (e.getX()-this.model.getRadius()), (int) (e.getY()-this.model.getRadius()));
		this.model.setPosition((int) (e.getX()-this.model.getRadius()/2), (int) (e.getY()-this.model.getRadius()/2));
	}
}
