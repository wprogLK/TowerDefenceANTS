package controllers.lens;

import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import controllers.medium.ANTSAbstractMediumController;

import basics.ANTSDevelopment.ANTSStream;
import basics.ANTSFactory;
import basics.ANTSPerpendicular;

import interfaces.ANTSIModel;
import interfaces.ANTSIRayController;
import interfaces.ANTSIView;

import interfaces.medium.ANTSIMediumController;

import views.lens.ANTSSimpleLensView;
import views.lens.ANTSSimplePrismView;

import models.lens.ANTSSimpleLensModel;
import models.lens.ANTSSimplePrismModel;

public class ANTSSimplePrismController extends ANTSAbstractMediumController implements ANTSIMediumController
{
	private ANTSSimplePrismModel model;
	private ANTSSimplePrismView view;
	
	public ANTSSimplePrismController(double posX, double posY, double height, double width, double heightOfCorner, double angleOfCorner ,double angle, double refractionIndex, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimplePrismModel(posX, posY, height, width, heightOfCorner, angleOfCorner, angle, refractionIndex, isMouseListener, factory);
		this.view = new ANTSSimplePrismView(this.model);
		
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

	public double[] getCenter() 
	{
		return this.model.getCenter();
	}
	
	
	///////////
	//SETTERS//
	///////////
	
	///////////
	//SPECIAL//
	///////////
	
	@Override
	public double[] calculateIntersectionPoint(ANTSIRayController ray) 
	{
//		double R = this.model.getRadius()/2.0;
//		
//		double[] M = this.model.getCenter();
//		
//		Point2D.Double[] vec = ray.getVector();
//		
//		double x_a = vec[0].x;
//		double y_a = vec[0].y;
//		
//		double x_b = vec[1].x;
//		double y_b = vec[1].y;
//		
//		double x_m = M[0];
//		double y_m = M[1];
//	
//		double G = 2*(-pow(x_a)+x_a*x_b+x_a*x_m-x_b*x_m-pow(y_a)+y_a*y_b+y_a*y_m-y_b*y_m);
//		double F = pow(x_a)-2*x_a*x_b+pow(x_b)+pow(y_a)-2*y_a*y_b+pow(y_b);
//		double S = pow(R)-pow(x_a)+2*x_a*x_m-pow(x_m)-pow(y_a)+2*y_a*y_m-pow(y_m);
//		
//		double b = G;
//		double a = F;
//		double c = - S;
//		
//		double D = Math.pow(b, 2)-4*a*c;
//		
//		double t_1,t_2;
//		
//		if(D>0)
//		{
//			t_1 = (-b+Math.sqrt(D))/(2.0*a);
//			t_2 = (-b-Math.sqrt(D))/(2.0*a);
//		}
//		else if(D==0)
//		{
//			t_1 = -b/(2.0*a);
//			t_2 = -b/(2.0*a);
//		}
//		else if(D<0)
//		{
//			ANTSStream.printDebug("ERROR: Complex solution!!"); //TODO
//			
//			t_1=Double.POSITIVE_INFINITY;
//			t_2=Double.POSITIVE_INFINITY;
//		}
//		else
//		{
//			ANTSStream.printDebug("ERROR: Unkown case"); //TODO
//			t_1=Double.POSITIVE_INFINITY;
//			t_2=Double.POSITIVE_INFINITY;
//		}
//		
//		double x_1 = x_a+t_1*(x_b-x_a);
//		double x_2 = x_a+t_2*(x_b-x_a);
//		
//		double y_1 = y_a+t_1*(y_b-y_a);
//		double y_2 = y_a+t_2*(y_b-y_a);
//		
//		this.model.setPointsOfIntersection(x_1,y_1,x_2,y_2);
//		
//		Point2D.Double point1 = new Point2D.Double(x_1,y_1);
//		Point2D.Double point2 = new Point2D.Double(x_2,y_2);
//		
//		ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
//		
//		points.add(point1);
//		points.add(point2);
//		
//		double[] closestPoint = this.getClosestIntersectionPoint(ray,points);
//		this.model.setThePointOfIntersection(closestPoint);
//		
//		return closestPoint;
		
		return null;
	}
	
	@Override
	public ANTSPerpendicular calculatePerpendicular(ANTSIRayController ray)
	{
		double[] intersectionPoint = this.calculateIntersectionPoint(ray);
		return new ANTSPerpendicular(intersectionPoint, this.model.getCenter());
	}

	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	public void mouseDraggedANTS(MouseEvent e) 
	{
//		this.model.setPosition((int) (e.getX()-this.model.getWidth()/2), (int) (e.getY()-this.model.getHeight()/2));
	}






}
