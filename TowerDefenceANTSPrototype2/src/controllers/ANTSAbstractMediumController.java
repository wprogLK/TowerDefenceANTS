package controllers;

import java.awt.event.MouseEvent;

import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIController;
import interfaces.ANTSIMediumController;
import interfaces.ANTSIMediumModel;

public class ANTSAbstractMediumController extends ANTSAbstractController implements ANTSIMediumController
{
	protected ANTSIMediumModel model;
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public double getRefractionIndex() {
		return this.model.getRefractionIndex();
	}
	
	/////////////
	//SETTERS//
	///////////
	
	public void setModel(ANTSIMediumModel m)
	{
		this.model = m;
	}
	
	///////////
	//SPECIAL//
	///////////
	
	@Override
	public final void handleCollision(ANTSIController c)
	{
		if(c.getClass().equals(ANTSSimpleRayLightController.class))
		{
			ANTSSimpleRayLightController rayLightController = (ANTSSimpleRayLightController) c;
			
			rayLightController.addAngle(this.model.getAngle(1));	//TODO refractionIndex
		}
		else
		{
			ANTSStream.printDebug("it's not a ray");
		}
	}
	
	@Override
	public final void addCollisionRay(ANTSIController c)
	{
		if(c.getClass().equals(ANTSSimpleRayLightController.class))
		{
			ANTSSimpleRayLightController rayLightController = (ANTSSimpleRayLightController) c;
			
			rayLightController.setCurrentMedium(this);
			this.model.addRay(rayLightController);	//TODO refractionIndex
			
		}
		else
		{
			ANTSStream.printDebug("it's not a ray");
		}
	}
	
	@Override
	public final void removeCollisionRay(ANTSIController c)
	{
		if(c.getClass().equals(ANTSSimpleRayLightController.class))
		{
			ANTSSimpleRayLightController rayLightController = (ANTSSimpleRayLightController) c;
			
			this.model.removeRay(rayLightController);	//TODO refractionIndex
		}
		else
		{
			ANTSStream.printDebug("it's not a ray");
		}
	}
	
	
	@Override
	public final boolean doesCollideWith(ANTSIController c)
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
	

	
}
