package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;

import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIController;
import interfaces.ANTSIMediumController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import views.ANTSSimpleMediumView;

import models.ANTSSimpleMediumModel;

public class ANTSSimpleMediumController extends ANTSAbstractController implements ANTSIMediumController
{
	private ANTSSimpleMediumModel model;
	private ANTSSimpleMediumView view;
	
	public ANTSSimpleMediumController(double posX, double posY, double height, double width, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleMediumModel(posX,posY,height,width,isMouseListener, factory);
		this.view = new ANTSSimpleMediumView(this.model);
		
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
	
	public ANTSSimpleMediumView getView()
	{
		return this.view;
	}

	public ANTSIView getIView()
	{
		return this.view;
	}
	
	
	
	///////////
	//SPECIAL//
	///////////
	
	@Override
	public void handleCollision(ANTSIController c)
	{
		if(c.getClass().equals(ANTSSimpleRayLightController.class))
		{
			ANTSSimpleRayLightController rayLightController = (ANTSSimpleRayLightController) c;
			
			rayLightController.addAngle(this.model.getAngle(1));
		}
		else
		{
			ANTSStream.printDebug("it's not a ray");
		}
	}
	
	@Override
	public void addCollisionRay(ANTSIController c)
	{
		if(c.getClass().equals(ANTSSimpleRayLightController.class))
		{
			ANTSSimpleRayLightController rayLightController = (ANTSSimpleRayLightController) c;
			
			this.model.addRay(rayLightController,1);	//TODO refractionIndex
		}
		else
		{
			ANTSStream.printDebug("it's not a ray");
		}
	}
	
	
	@Override
	public boolean doesCollideWith(ANTSIController c)
	{
		if(this.iview.doesCollideWith(c.getIView().getShape()))
		{
			if(c.getClass().equals(ANTSSimpleRayLightController.class))
			{
				this.handleCollision(c);
				return true;
			}
		}
		
			return false;
		
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	@Override
	public void mouseLeftClickedANTS(MouseEvent e) 
	{
	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	@Override
	public void mouseDraggedANTS(MouseEvent e) 
	{
		this.model.setPosition((int) (e.getX()-this.model.getWidth()/2), (int) (e.getY()-this.model.getHeight()/2));
	}
}
