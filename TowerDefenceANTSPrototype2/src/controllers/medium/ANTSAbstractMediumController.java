package controllers.medium;

import java.awt.geom.Point2D;
import java.util.ArrayList;

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
	
	@Override
	public void setRefractionIndex(double refractionIndex)
	{
		this.model.setRefractionIndex(refractionIndex);
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
	
	protected final double[] getClosestIntersectionPoint(ANTSIRayController ray, ArrayList<Point2D.Double> intersectionPoints) 
	{
		if(!intersectionPoints.isEmpty())
		{
			Point2D.Double closestPoint = intersectionPoints.get(0);
			double closestDistance = getDistanceBetweenRayAndIntersectionPoint(ray, closestPoint);
					
			for(Point2D.Double currentPoint:intersectionPoints)
			{
				double currentDistance = getDistanceBetweenRayAndIntersectionPoint(ray, currentPoint);
						
				if(closestDistance>currentDistance)
				{
					closestPoint=currentPoint;
					closestDistance = currentDistance;
				}
			}
					
				double[] point = new double[2];
				point[0] = closestPoint.x;
				point[1] = closestPoint.y;
					
				return point;
		}
		else
		{
			double[] point = new double[2];
			point[0] = -1;
			point[1] = -1;
					
			return point;
		}
	}
	
	protected final double getDistanceBetweenRayAndIntersectionPoint(ANTSIRayController ray, Point2D.Double point) 
	{
		double[] posRay = new double[2];
		
		posRay[0] = ray.getModel().getMatrix().getTranslateX();
		posRay[1] = ray.getModel().getMatrix().getTranslateY();
		
		double distance = Point2D.distance(point.getX(), point.getY(), posRay[0], posRay[1]);
		
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
