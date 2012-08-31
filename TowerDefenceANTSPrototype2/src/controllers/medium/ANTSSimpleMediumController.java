package controllers.medium;

import java.awt.event.MouseEvent;
import java.util.Arrays;

import controllers.ANTSSimpleRayLightController;

import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIRayController;
import interfaces.ANTSIView;
import interfaces.medium.ANTSIMediumController;

import views.medium.ANTSSimpleMediumView;

import models.medium.ANTSSimpleMediumModel;

public class ANTSSimpleMediumController extends ANTSAbstractMediumController implements ANTSIMediumController
{
	private ANTSSimpleMediumModel model;
	private ANTSSimpleMediumView view;
	
	public ANTSSimpleMediumController(double posX, double posY, double height, double width, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleMediumModel(posX,posY,height,width,isMouseListener, factory);
		this.view = new ANTSSimpleMediumView(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
		this.setModel(this.model);
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
	
	@Override
	public double getRefractionIndex() {
		return this.model.getRefractionIndex();
	}
	
	///////////
	//SPECIAL//
	///////////
	
	protected void calculatePlumbAngle(ANTSIController c)
	{
		ANTSStream.printDebug("calc plumb angle");
		if(Arrays.asList(c.getClass().getInterfaces()).contains(ANTSIRayController.class))
		{
			ANTSIRayController ray = (ANTSIRayController) c;
			this.view.calculatePlumbAngle(ray);
		}
		

	}
	
//	
//	
//	@Override
//	public void removeCollisionRay(ANTSIController c)
//	{
//		if(c.getClass().equals(ANTSSimpleRayLightController.class))
//		{
//			ANTSSimpleRayLightController rayLightController = (ANTSSimpleRayLightController) c;
//			
//			this.model.removeRay(rayLightController);	//TODO refractionIndex
//		}
//		else
//		{
//			ANTSStream.printDebug("it's not a ray");
//		}
//	}
//	
//	
//	@Override
//	public boolean doesCollideWith(ANTSIController c)
//	{
//		if(this.iview.doesCollideWith(c.getIView().getShape()))
//		{
//			if(c.getClass().equals(ANTSSimpleRayLightController.class))
//			{
//				this.handleCollision(c);
//				return true;
//			}
//		}
//		
//			return false;
//		
//	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
//	@Override
//	public void mouseLeftClickedANTS(MouseEvent e) 
//	{
//	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	
	public void mouseDraggedANTS(MouseEvent e) 
	{
		this.model.setPosition((int) (e.getX()-this.model.getWidth()/2), (int) (e.getY()-this.model.getHeight()/2));
	}

	
}
