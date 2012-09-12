package models.medium;

import interfaces.ANTSIModel;
import interfaces.medium.ANTSIMediumModel;

import java.util.ArrayList;

import models.ANTSAbstractModel;

import controllers.ANTSSimpleRayLightController;

import basics.ANTSDevelopment.ANTSStream;
import basics.ANTSFactory;

public abstract class ANTSAbstractMediumModel extends ANTSAbstractModel implements ANTSIModel, ANTSIMediumModel
{
	private double refractionIndex = 1;
	protected double angleOfPlumb;
	
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
	
	public void setPlumbAngle(double angle)
	{
		this.angleOfPlumb = angle;
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
	public final double getAngle(ANTSSimpleRayLightController rayLightController)		//TODO: check and test this!
	{
		double alpha_1 = this.angleOfPlumb-rayLightController.getAngle();
		
		double sin = Math.sin(alpha_1)*rayLightController.getRefractionIndex()/this.refractionIndex;
//		return Math.sinh(sin);	//TODO
		ANTSStream.printDebug("plum angle " + this.angleOfPlumb);
		return 45;
	}
	
	public double getPlumbAngle()
	{
		return this.angleOfPlumb;
	}
	
	///////////
	//Special//
	///////////

	@Override
	public final void addRay(ANTSSimpleRayLightController rayLightController) {
		if(!this.containsRay(rayLightController))
		{
			this.rays.add(rayLightController);
			rayLightController.addAngle(this.getAngle(rayLightController));
		}
	}

	@Override
	public final void removeRay(ANTSSimpleRayLightController rayLightController) 
	{	
		if(this.containsRay(rayLightController))
		{
			this.rays.remove(rayLightController);
			rayLightController.addAngle(-this.getAngle(rayLightController));	//TODO check this
		}
	}
}
