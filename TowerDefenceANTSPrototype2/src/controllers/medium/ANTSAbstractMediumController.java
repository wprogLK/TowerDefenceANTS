package controllers.medium;

import java.awt.geom.Point2D;

import basics.ANTSPerpendicular;
import basics.ANTSDevelopment.ANTSStream;

import controllers.ANTSAbstractController;

import interfaces.ANTSIRayController;
import interfaces.medium.ANTSIMediumController;
import interfaces.medium.ANTSIMediumModel;

public abstract class ANTSAbstractMediumController extends ANTSAbstractController implements ANTSIMediumController
{
	protected ANTSIMediumModel model;

	///////////
	//GETTERS//
	///////////
	
	@Override
	public double getRefractionIndex() 
	{
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
	public double[] calculateIntersectionPoint(ANTSIRayController ray) 
	{
		ANTSStream.printErr("Error: no valid intersectionPoint possible");	//TODO 
		return null;
	}
	
	@Override
	public ANTSPerpendicular calculatePerpendicular(ANTSIRayController ray)
	{
		ANTSStream.printErr("Error: no valid ANTSPerpendicular possible");	//TODO 
		return null;
	}
	
	protected final double[] getClosestIntersectionPoint(ANTSIRayController ray, double[] point1, double[] point2) 
	{
		double distancePoint1 = getDistanceBetweenRayAndIntersectionPoint(ray, point1);
		double distancePoint2 = getDistanceBetweenRayAndIntersectionPoint(ray, point2);
		
		if(distancePoint1<=distancePoint2)
		{
			return point1;
		}
		else
		{
			return point2;
		}
	}
	
	protected final double getDistanceBetweenRayAndIntersectionPoint(ANTSIRayController ray, double[] point) 
	{
		double[] posRay = new double[2];
		
		posRay[0] = ray.getModel().getMatrix().getTranslateX();
		posRay[1] = ray.getModel().getMatrix().getTranslateY();
		
		double distance = Point2D.distance(point[0], point[1], posRay[0], posRay[1]);
		
		return distance;
	}

	protected final double pow(double v)
	{
		return Math.pow(v, 2);
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
}
