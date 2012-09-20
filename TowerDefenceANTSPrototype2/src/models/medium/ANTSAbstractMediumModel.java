package models.medium;

import interfaces.ANTSIModel;
import interfaces.medium.ANTSIMediumModel;

import java.util.ArrayList;

import models.ANTSAbstractModel;

import controllers.ANTSSimpleRayLightController;

import basics.ANTSFactory;

public abstract class ANTSAbstractMediumModel extends ANTSAbstractModel implements ANTSIModel, ANTSIMediumModel
{
	private double refractionIndex;
	
	private ArrayList<ANTSSimpleRayLightController> rays;
	 
	public ANTSAbstractMediumModel(boolean isCollisionDetection, double refractionIndex, ANTSFactory factory)
	{
		super(factory);
		
		this.refractionIndex = refractionIndex;
		this.isCollisionDetected = isCollisionDetection;
		
		this.rays = new ArrayList<ANTSSimpleRayLightController>();
	}
	
	///////////
	//SETTERS//
	///////////
	
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
	
	///////////
	//Special//
	///////////

	@Override
	public final void addRay(ANTSSimpleRayLightController rayLightController) {
		if(!this.containsRay(rayLightController))
		{
			this.rays.add(rayLightController);
		}
	}

	@Override
	public final void removeRay(ANTSSimpleRayLightController rayLightController) 
	{	
		if(this.containsRay(rayLightController))
		{
			this.rays.remove(rayLightController);
		}
	}
}
