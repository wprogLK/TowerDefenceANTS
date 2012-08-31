package models.medium;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import interfaces.medium.ANTSIMediumModel;

import java.util.ArrayList;

import models.ANTSAbstractModel;

import controllers.ANTSSimpleRayLightController;

import basics.ANTSFactory;

public abstract class ANTSAbstractMediumModel extends ANTSAbstractModel implements ANTSIModel, ANTSIMediumModel
{
	private double refractionIndex = 1;
	
	private ArrayList<ANTSSimpleRayLightController> rays;
	
	public ANTSAbstractMediumModel(boolean isCollisionDetection ,ANTSFactory factory)
	{
		super(factory);
		
		this.isCollisionDetected = isCollisionDetection;
		
		this.rays = new ArrayList<ANTSSimpleRayLightController>();
	}
	
	///////////
	//Getters//
	///////////
	
	@Override
	public final double getRefractionIndex()
	{
		return this.refractionIndex;
	}
	
	public String toString()
	{
		return "Model absract medium";
	}
	
	@Override
	public final boolean containsRay(ANTSSimpleRayLightController c)
	{
		return this.rays.contains(c);
	}
	
	@Override
	public final double getAngle(double refractionIndexOtherMedium)
	{
		//TODO
		return 45;
	}
	
	
	///////////
	//Special//
	///////////

	@Override
	public final void addRay(ANTSSimpleRayLightController rayLightController) {
		if(!this.containsRay(rayLightController))
		{
			this.rays.add(rayLightController);
			rayLightController.addAngle(this.getAngle(rayLightController.getRefractionIndex()));
		}
	}

	@Override
	public final void removeRay(ANTSSimpleRayLightController rayLightController) 
	{	
		if(this.containsRay(rayLightController))
		{
			this.rays.remove(rayLightController);
			rayLightController.addAngle(-this.getAngle(rayLightController.getRefractionIndex()));	//TODO check this
		}
	}
}
