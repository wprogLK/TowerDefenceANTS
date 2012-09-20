package controllers.medium;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

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
//		Point2D.Double[][] vectors = new Point2D.Double[4][2];
//		vectors[0]=this.model.getVectorA();
//		vectors[1]=this.model.getVectorB();
//		vectors[2]=this.model.getVectorC();
//		vectors[3]=this.model.getVectorD();
		
		ArrayList<Point2D.Double> intersectionPoints = new ArrayList<Point2D.Double>();
		
//		for(Point2D.Double[] currentVector:vectors)
//		{
			ANTSStream.print("A");

			Point2D.Double currentIntersectionPoint = calculateCurrentIntersectionPoint(ray,this.model.getVectorA());
			
			if(currentIntersectionPoint!=null)
			{
				intersectionPoints.add(currentIntersectionPoint);
			}
//		}
			ANTSStream.print("B");

			currentIntersectionPoint = calculateCurrentIntersectionPoint(ray,this.model.getVectorB());
			
			if(currentIntersectionPoint!=null)
			{
				intersectionPoints.add(currentIntersectionPoint);
			}
			
			ANTSStream.print("C");

			currentIntersectionPoint = calculateCurrentIntersectionPoint(ray,this.model.getVectorC());
			
			if(currentIntersectionPoint!=null)
			{
				intersectionPoints.add(currentIntersectionPoint);
			}
			
			ANTSStream.print("D");

			currentIntersectionPoint = calculateCurrentIntersectionPoint(ray,this.model.getVectorD());
			
			if(currentIntersectionPoint!=null)
			{
				intersectionPoints.add(currentIntersectionPoint);
			}
		
			double[] closestIntersectionPoint = this.getClosestIntersectionPoint(ray, intersectionPoints);
			this.model.setTheIntersectionPoint(closestIntersectionPoint);
			
		return closestIntersectionPoint;
	}
	
	protected double[] getClosestIntersectionPoint(ANTSIRayController ray, ArrayList<Point2D.Double> intersectionPoints) 
	{
		Point2D.Double closestPoint = intersectionPoints.get(0);
		double closestDistance = getDistanceBetweenRayAndIntersectionPoint(ray, closestPoint);
		
		for(Point2D.Double currentPoint:intersectionPoints)
		{
			double currentDistance = getDistanceBetweenRayAndIntersectionPoint(ray, currentPoint);
			
			if(closestDistance>currentDistance)
			{
				closestPoint=currentPoint;
			}
		}
		
		double[] point = new double[2];
		point[0] = closestPoint.x;
		point[1] = closestPoint.y;
		
		return point;
	}
	
	protected double getDistanceBetweenRayAndIntersectionPoint(ANTSIRayController ray, Point2D.Double point) 
	{
		double[] posRay = new double[2];
		
		posRay[0] = ray.getModel().getMatrix().getTranslateX();
		posRay[1] = ray.getModel().getMatrix().getTranslateY();
		
		double distance = Point2D.distance(point.x, point.y, posRay[0], posRay[1]);
		
		return distance;
	}
	
	private Double calculateCurrentIntersectionPoint(ANTSIRayController ray, Double[] currentVector) 
	{
		Point2D.Double startVectorBox = currentVector[0];
		Point2D.Double endVectorBox = currentVector[1];
		
		Point2D.Double startVectorRay = ray.getVector()[0];
		Point2D.Double endVectorRay = ray.getVector()[1];
		
		Point2D.Double directionVectorBox = new Point2D.Double(endVectorBox.x-startVectorBox.x,endVectorBox.y-startVectorBox.y);
		Point2D.Double directionVectorRay = new Point2D.Double(endVectorRay.x-startVectorRay.x,endVectorRay.y-startVectorRay.y);
		
		double a = endVectorRay.x;
		double b = directionVectorRay.x;
		double e = endVectorRay.y;
		double f = directionVectorRay.y;
		
		double c = endVectorBox.x;
		double d = directionVectorBox.x;
		double g = endVectorBox.y;
		double h = directionVectorBox.y;
		
		double r = -(a*h-c*h-d*(e-g))/(b*h-d*f);
		double s = -(a*f-b*(e-g)-c*f)/(b*h-d*f);
		
		double[] intersectionPointRay = new double[2];
		double[] intersectionPointBox =  new double[2];
		
		intersectionPointBox[0] = endVectorBox.x+s*directionVectorBox.x;	
		intersectionPointBox[1] = endVectorBox.y+s*directionVectorBox.y;		
		
		intersectionPointRay[0] = endVectorRay.x+r*directionVectorRay.x;	
		intersectionPointRay[1] = endVectorRay.y+r*directionVectorRay.y;	
		
//		ANTSStream.print("s = " + s + " r = " + r);
		
//		ANTSStream.print("[" + intersectionPointRay[0] + " | " + intersectionPointRay[1] + " ] == [" + intersectionPointBox[0] + " | " + intersectionPointBox[1] + " ]");
//		ANTSStream.print("ROUND [" + roundScale2(intersectionPointRay[0]) + " | " + roundScale2(intersectionPointRay[1]) + " ] == [" + roundScale2(intersectionPointBox[0]) + " | " + roundScale2(intersectionPointBox[1]) + " ]");
		if(roundScale2(intersectionPointBox[0])==roundScale2(intersectionPointRay[0]) && roundScale2(intersectionPointBox[1])==roundScale2(intersectionPointRay[1]))
		{
//			ANTSStream.print("SAME = [" + intersectionPointBox[0] + " | " + intersectionPointBox[1] + " ]");
			this.model.setIntersectionPoint(intersectionPointBox,intersectionPointRay);
			
			Point2D.Double intersectionPoint = new Point2D.Double(intersectionPointBox[0], intersectionPointBox[1]);
			
//			boolean xAxis = (intersectionPoint.x>=this.model.getVectorA()[0].x) && (intersectionPoint.x<=this.model.getVectorA()[1].x);
//			boolean yAxis = (intersectionPoint.y>=this.model.getVectorB()[0].y) && (intersectionPoint.x<=this.model.getVectorB()[1].y);
			
			boolean xAxis = (intersectionPoint.x>=this.model.getMatrix().getTranslateX()) && (intersectionPoint.x<=this.model.getMatrix().getTranslateX()+this.model.getWidth());
			boolean yAxis = (intersectionPoint.y>=this.model.getMatrix().getTranslateY()) && (intersectionPoint.x<=this.model.getMatrix().getTranslateY()+this.model.getHeight());
			
			//TODO CHECK THIS!!!! BUG
			
			if(xAxis && yAxis)
			{
				ANTSStream.print("OK! (xAxis = " + xAxis + " yAxis = " + yAxis);
				ANTSStream.print("--------------------------------------------------------");
				return intersectionPoint;
			}
			else
			{
				ANTSStream.print("intersection point out of rectangle! (xAxis = " + xAxis + " yAxis = " + yAxis);
				ANTSStream.print("--------------------------------------------------------");
				return null;
			}
		
		}
		else
		{
			return null;
		}
	}

	private double roundScale2( double d )
	{
	    return Math.round( d * 10000000 ) / 10000000.;
	}
	
	@Override
	public ANTSPerpendicular calculatePerpendicular(ANTSIRayController ray)
	{
		double[] intersectionPoint = calculateIntersectionPoint(ray);
		double[] pseudoCenterForIntersetionPoint = this.getPseudoCenter(intersectionPoint);
		
		return new ANTSPerpendicular(intersectionPoint, pseudoCenterForIntersetionPoint);
	}

	
	private double[] getPseudoCenter(double[] intersectionPoint) 
	{
		double x = intersectionPoint[0];
		double y = intersectionPoint[1];
		
		Point2D.Double[] vecA = this.model.getVectorA();
		Point2D.Double[] vecB = this.model.getVectorB();
		Point2D.Double[] vecC = this.model.getVectorC();
		Point2D.Double[] vecD = this.model.getVectorD();
		
		double[] center = new double[2];
		double centerOffset = 10;
		
		String message = "intersectionPoint on vector ";
		
		if(y==vecA[0].y)
		{
			//intersection on vecA
			message+="A";
			
			center[0] = x;
			center[1] = y + centerOffset; 
		}
		else if(y==vecC[0].y)
		{
			//intersection on vecC
			message+="C";
			
			center[0] = x;
			center[1] = y - centerOffset;
		}
		else if(x==vecD[0].x)
		{
			//intersection on vecD
			message+="D";
			
			center[0] = x + centerOffset;
			center[1] = y;
		}
		else if(x==vecB[0].x)
		{
			//intersection on vecB
			message+="B";
			
			center[0] = x - centerOffset;
			center[1] = y;
		}
		else
		{
			ANTSStream.printErr("Error: Unknown case in method getPseudoCenter() !");
			
			return null;
		}
		
		ANTSStream.print(message);
		
		return center;
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
