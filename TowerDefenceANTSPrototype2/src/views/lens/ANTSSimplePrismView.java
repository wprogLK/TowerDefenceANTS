package views.lens;


import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import basics.ANTSDevelopment.ANTSStream;

import views.ANTSAbstractView;

import models.lens.ANTSSimpleLensModel;
import models.lens.ANTSSimplePrismModel;

public class ANTSSimplePrismView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimplePrismModel model;
	
	public ANTSSimplePrismView(ANTSSimplePrismModel m) 
	{
		super(m);
		this.model = m;
	}
	
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "Simple prism view"; 
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	

	@Override
	protected void updateShape(float interpolation) 
	{
//		double radius = this.model.getRadius();
//		
//		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, radius, radius);
//		
//		AffineTransform aT = this.model.getMatrix();
//		this.shape = aT.createTransformedShape(circle);
		
		Point2D[] points = this.model.getPoints();
		
		Path2D p2D = new Path2D.Double();
		GeneralPath gP = new GeneralPath();

		p2D.moveTo(points[0].getX(),points[0].getY());
		gP.moveTo(points[0].getX(),points[0].getY());

		for(int i = 1; i>3; i++)
		{
			double x = points[i].getX();
			double y = points[i].getY();
			p2D.lineTo(x, y);
			gP.lineTo(x, y);
		}
		
		p2D.closePath();
		gP.closePath();
		
		PathIterator it=gP.getPathIterator(new AffineTransform());
		
		this.shape = this.model.getMatrix().createTransformedShape(p2D);
		
	}	
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		this.updateShape(-1);
		
		Point2D[] points = this.model.getPoints();
		
		Path2D p2D = new Path2D.Double();
		GeneralPath gP = new GeneralPath();

		p2D.moveTo(points[0].getX(),points[0].getY());
		gP.moveTo(points[0].getX(),points[0].getY());

		for(int i = 1; i<3; i++)
		{
			double x = points[i].getX();
			double y = points[i].getY();
			p2D.lineTo(x, y);
			gP.lineTo(x, y);
		}
		
		p2D.closePath();
		gP.closePath();
		
		PathIterator it=gP.getPathIterator(new AffineTransform());
		
		g2d.draw(p2D);
//	 
	}

	/*
	 * For debugging 
	 */
	private void paintAngle(Graphics2D g2d) 
	{
//		double[] point = this.model.getIntersectionPoint();
//		
//		if(point[0] != Double.POSITIVE_INFINITY && point[1] != Double.POSITIVE_INFINITY )
//		{
//			double[] center = this.model.getCenter();
//			
//			double radius = this.model.getRadius()/2;
//			
//			double[] extendedPoint = new double[2];
//			double t = 10;
//			
//			extendedPoint[0] = center[0] + t*(point[0]-center[0]);
//			extendedPoint[1] = center[1] + t*(point[1]-center[1]);
//			
//			Line2D.Double lineAngleZero = new Line2D.Double(center[0], center[1], center[0]+radius*t, center[1]);
//			Line2D.Double lineAngleIntersetion = new Line2D.Double(center[0], center[1],extendedPoint[0], extendedPoint[1]);
//			
//			g2d.setColor(Color.BLUE);
//			
//			g2d.draw(lineAngleIntersetion);
//			g2d.draw(lineAngleZero);
//		}
	}
	
	private void paintTheIntersectionPoint(Graphics2D g2d) 
	{
		double[] point = this.model.getIntersectionPoint();
	
		
		if(point[0] != Double.POSITIVE_INFINITY && point[1] != Double.POSITIVE_INFINITY )
		{
			this.paintPoint(point,g2d);	
		}
	}

	private void paintPoint(double[] point,Graphics2D g2d) 
	{
		Rectangle2D rec = new Rectangle2D.Double(point[0], point[1], 5, 5);
		
		g2d.setColor(Color.BLUE);
		
		g2d.fill(rec);
		
	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		this.paint(g2d);
	}
}
