package controllers.medium;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import basics.ANTSFactory;
import basics.ANTSPerpendicular;

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
	
	private Point2D.Double invalidIntersectionPoint;
	
	public ANTSSimpleMediumController(double posX, double posY, double height, double width, double refractionIndex, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleMediumModel(posX,posY,height,width, refractionIndex, isMouseListener, factory);
		this.view = new ANTSSimpleMediumView(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
		this.setModel(this.model);
		
		this.invalidIntersectionPoint = new Point2D.Double(-1, -1);
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
		ArrayList<Point2D.Double[]> vectors = new ArrayList<Point2D.Double[]>();
		ArrayList<Point2D.Double> intersectionPoints = new ArrayList<Point2D.Double>();
		
		vectors.add(this.model.getVectorA());
		vectors.add(this.model.getVectorB());
		vectors.add(this.model.getVectorC());
		vectors.add(this.model.getVectorD());
		
		for(int index = 0; index<vectors.size();index++)
		{
			Point2D.Double intersectionPoint = calculateCurrentIntersectionPoint(ray,vectors.get(index));
			
			intersectionPoints.add(intersectionPoint);
		}
		
		double[] intersectionPoint = this.getClosestIntersectionPoint(ray, intersectionPoints);
		
		this.model.setTheIntersectionPoint(intersectionPoint);
			
		return intersectionPoint;
	}

	private Double calculateCurrentIntersectionPoint(ANTSIRayController ray, Double[] currentVector) 
	{
		Point2D.Double startVectorBox = currentVector[0];
		Point2D.Double endVectorBox = currentVector[1];
		
		Point2D.Double startVectorRay = ray.getVector()[0];
		Point2D.Double endVectorRay = ray.getVector()[1];
		
		Point2D.Double directionVectorBox = new Point2D.Double(endVectorBox.x-startVectorBox.x,endVectorBox.y-startVectorBox.y);
		Point2D.Double directionVectorRay = new Point2D.Double(endVectorRay.x-startVectorRay.x,endVectorRay.y-startVectorRay.y);
		
		double a = startVectorRay.x;
		double b = directionVectorRay.x;
		double e = startVectorRay.y;
		double f = directionVectorRay.y;
		
		double c = startVectorBox.x;
		double d = directionVectorBox.x;
		double g = startVectorBox.y;
		double h = directionVectorBox.y;
		
		double r = -(a*h-c*h-d*(e-g))/(b*h-d*f);
		double s = -(a*f-b*(e-g)-c*f)/(b*h-d*f);
			
		double[] intersectionPointRay = new double[2];
		double[] intersectionPointBox =  new double[2];
		
		intersectionPointBox[0] = startVectorBox.x+s*directionVectorBox.x;	
		intersectionPointBox[1] = startVectorBox.y+s*directionVectorBox.y;		
		
		intersectionPointRay[0] = startVectorRay.x+r*directionVectorRay.x;	
		intersectionPointRay[1] = startVectorRay.y+r*directionVectorRay.y;	
		
		if(roundScale2(intersectionPointBox[0])==roundScale2(intersectionPointRay[0]) && roundScale2(intersectionPointBox[1])==roundScale2(intersectionPointRay[1]))
		{
			this.model.setIntersectionPoint(intersectionPointBox,intersectionPointRay);
			
			Point2D.Double intersectionPoint = new Point2D.Double(intersectionPointBox[0], intersectionPointBox[1]);
			
			if(s>=0 && s<=1)
			{
//				ANTSStream.print("OK!");
				return intersectionPoint;
			}
			else
			{
//				ANTSStream.print("Wrong: rValid " + rValid + " sValid " + sValid );
//				ANTSStream.print("INVALID POINT!"); //TODO
				return this.invalidIntersectionPoint; 
			}
		}
		else
		{
//			ANTSStream.print("TOTAL INVALID POINT!"); //TODO
			return this.invalidIntersectionPoint;
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
		double[] pseudoCenterForIntersectionPoint = this.getPseudoCenter(intersectionPoint);
		
		return new ANTSPerpendicular(intersectionPoint, pseudoCenterForIntersectionPoint);
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
		
		if(y==vecA[0].y)
		{
			//intersection on vecA

			center[0] = x;
			center[1] = y + centerOffset; 
		}
		else if(y==vecC[0].y)
		{
			//intersection on vecC
			
			center[0] = x;
			center[1] = y - centerOffset;
		}
		else if(x==vecD[0].x)
		{
			//intersection on vecD
			
			center[0] = x + centerOffset;
			center[1] = y;
		}
		else if(x==vecB[0].x)
		{
			//intersection on vecB
			
			center[0] = x - centerOffset;
			center[1] = y;
		}
		else
		{
//			ANTSStream.printErr("Error: Unknown case in method getPseudoCenter() !");
			
			center[0] = -1;
			center[1] = -1;
		}
		
		this.model.setCenter(center);
		
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
