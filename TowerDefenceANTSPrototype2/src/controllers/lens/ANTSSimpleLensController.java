package controllers.lens;

import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import controllers.medium.ANTSAbstractMediumController;

import basics.ANTSFactory;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import interfaces.medium.ANTSIMediumController;

import views.lens.ANTSSimpleLensView;

import models.lens.ANTSSimpleLensModel;

public class ANTSSimpleLensController extends ANTSAbstractMediumController implements ANTSIMediumController
{
	private ANTSSimpleLensModel model;
	private ANTSSimpleLensView view;
	
	public ANTSSimpleLensController(double posX, double posY, double radius, double refractionIndex, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleLensModel(posX,posY,radius, refractionIndex, isMouseListener, factory);
		this.view = new ANTSSimpleLensView(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
		this.setModel(this.model);
	}
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	@Override
	public ANTSIView getIView()
	{
		return this.view;
	}
	
	@Override
	public double getRefractionIndex() 
	{
		return this.model.getRefractionIndex();
	}
	
	public double getRadius()
	{
		return this.model.getRadius();
	}
	
	public double[] getCenter() 
	{
		return this.model.getCenter();
	}
	
	
	///////////
	//SETTERS//
	///////////
	
	public void setPointOfIntersection(double x_1, double y_1, double x_2, double y_2) 
	{
		this.model.setPointOfIntersection(x_1, y_1, x_2, y_2);
	}
	
	///////////
	//SPECIAL//
	///////////
	

	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	public void mouseDraggedANTS(MouseEvent e) 
	{
		this.model.setPosition((int) (e.getX()-this.model.getWidth()/2), (int) (e.getY()-this.model.getHeight()/2));
	}






}
