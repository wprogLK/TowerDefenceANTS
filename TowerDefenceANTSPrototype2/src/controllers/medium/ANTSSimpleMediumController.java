package controllers.medium;

import java.awt.event.MouseEvent;

import basics.ANTSFactory;
import basics.ANTSPerpendicular;
import basics.ANTSDevelopment.ANTSStream;

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
	
	public ANTSSimpleMediumController(double posX, double posY, double height, double width, double refractionIndex, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleMediumModel(posX,posY,height,width, refractionIndex, isMouseListener, factory);
		this.view = new ANTSSimpleMediumView(this.model);
		
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
	
	public ANTSSimpleMediumView getView()
	{
		return this.view;
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
	
	///////////
	//SPECIAL//
	///////////
	
	@Override
	public double[] calculateIntersectionPoint(ANTSIRayController ray) 
	{
		ANTSStream.printDebug("Error: at the moment there's not a valid method to calculate the intersection point with a simple medium");	//TODO 
		System.exit(1);
			
		return null;
	}
	
	@Override
	public ANTSPerpendicular calculatePerpendicular(ANTSIRayController ray)
	{
		ANTSStream.printDebug("Error: at the moment there's not a valid method to calculate the perpendicular at the intersection point with a simple medium");	//TODO 
		System.exit(1);
			
		return null;
	}

	
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
