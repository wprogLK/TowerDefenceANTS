package views;

import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import models.ANTSSimpleRayLightModel;

public class ANTSSimpleRayLightView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleRayLightModel model;
	private	Line2D.Double ray;
	
	public ANTSSimpleRayLightView(ANTSSimpleRayLightModel m)
	{
		super(m);
		this.model = m;
		this.updateShape(-1);
	}
	
	///////////
	//Getters//
	///////////

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	public double[] getCenter()
	{
		this.updateShape(-1);
		
		double[] center = new double[2];
		
		center[0] = this.shape.getBounds2D().getCenterX();
		center[1] = this.shape.getBounds2D().getCenterY();
		
		return center;
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
		AffineTransform aT = this.model.getMatrix();
		
		double length = this.model.getLength();
		
//		Point2D.Double[] vec =	this.model.getVector();
//		
//		this.ray = new Line2D.Double(vec[0].x,vec[0].y,vec[1].x,vec[1].y);
//		
//		this.shape = this.ray;
		
		this.ray = new Line2D.Double(0,0,length,0);
		
		this.shape = aT.createTransformedShape(this.ray);
	}	
	
	@Override
	public void paint(Graphics2D g2d) 
	{		
		g2d.setColor(this.model.getColor());

		this.updateShape(-1);
		
		g2d.draw(this.shape);

		this.paintBounds(g2d);
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		g2d.setColor(this.model.getColor());
		
		this.model.update(interpolation);

		this.updateShape(interpolation);

		g2d.draw(this.shape);
		
		this.paintBounds(g2d);
	}
}
