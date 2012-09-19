package views.lens;


import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import basics.ANTSDevelopment.ANTSStream;

import views.ANTSAbstractView;

import models.lens.ANTSSimpleLensModel;

public class ANTSSimpleLensView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleLensModel model;
	
	public ANTSSimpleLensView(ANTSSimpleLensModel m) 
	{
		super(m);
		this.model = m;
	}
	
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "Simple medium view"; 
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
		double radius = this.model.getRadius();
		
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, radius, radius);
		
		AffineTransform aT = this.model.getMatrix();
		this.shape = aT.createTransformedShape(circle);
	}	
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		
		this.updateShape(-1);
		
		if(this.model.isDragged())
		{
			//Only an example
			
			g2d.setColor(Color.GRAY);

			float[] dash_array=new float[4];
			dash_array[0]=5; //sichtbar
			dash_array[1]=20; //unsichtbar
			
			BasicStroke dashStroke = new BasicStroke( 
				5f, //Breite
				BasicStroke.CAP_SQUARE, //End Style
				BasicStroke.JOIN_ROUND, //Join Style
				1f, //Limit f�r Join
				dash_array, //Strichelung
				0 //offset in Pixeln f. Strichelung
				);
			
			g2d.setStroke(dashStroke);
			g2d.draw(shape.getBounds2D());
			
			g2d.setStroke(new BasicStroke());
		}
		
		
			g2d.setColor(Color.GREEN);
			g2d.fill(shape);
			this.paintBounds(g2d);
			
			this.paintTheIntersectionPoint(g2d);
			this.paintAngle(g2d);
	}

	/*
	 * For debugging 
	 */
	private void paintAngle(Graphics2D g2d) 
	{
		double[] point = this.model.getThePointOfIntersection();
		
		if(point[0] != Double.POSITIVE_INFINITY && point[1] != Double.POSITIVE_INFINITY )
		{
			double[] center = this.model.getCenter();
			
			double radius = this.model.getRadius()/2;
			
			double[] extendedPoint = new double[2];
			double t = 10;
			
			extendedPoint[0] = center[0] + t*(point[0]-center[0]);
			extendedPoint[1] = center[1] + t*(point[1]-center[1]);
			
			Line2D.Double lineAngleZero = new Line2D.Double(center[0], center[1], center[0]+radius*t, center[1]);
			Line2D.Double lineAngleIntersetion = new Line2D.Double(center[0], center[1],extendedPoint[0], extendedPoint[1]);
			
			g2d.setColor(Color.BLUE);
			
			g2d.draw(lineAngleIntersetion);
			g2d.draw(lineAngleZero);
		}
	}
	
	private void paintTheIntersectionPoint(Graphics2D g2d) 
	{
		double[] point = this.model.getThePointOfIntersection();
	
		
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
