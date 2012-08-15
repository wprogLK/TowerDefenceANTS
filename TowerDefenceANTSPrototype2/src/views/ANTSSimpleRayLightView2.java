package views;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import basics.ANTSDevelopment.ANTSStream;

import models.ANTSSimpleRayLightModel;
import models.ANTSSimpleRayLightModel2;

public class ANTSSimpleRayLightView2 extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleRayLightModel2 model;
	private	Line2D.Double ray;
	
	public ANTSSimpleRayLightView2(ANTSSimpleRayLightModel2 m)
	{
		super();
		this.model = m;
		this.setupRay();
	}
	
	///////////
	//Getters//
	///////////

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
	
	private void setupRay() 
	{
		double length = this.model.getLength();
		double x = this.model.getMatrix().getTranslateX();
		double y = this.model.getMatrix().getTranslateY();
		
		this.ray = new Line2D.Double(0,0,0+length,0);
	}
	
	@Override
	public void paint(Graphics2D g2d) 
	{		
		g2d.setColor(this.model.getColor());
		
		Rectangle2D.Double rec = new Rectangle2D.Double(0,0,1,1);
		
		Shape s= this.model.getMatrix().createTransformedShape(rec);
		
		g2d.draw(s);
		
		AffineTransform aT = this.model.getMatrix();
		
		this.shape = aT.createTransformedShape(this.ray);
		
		g2d.draw(this.shape);
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		if(interpolation == 0)
		{
			paint(g2d);
		}
		else
		{
			ANTSStream.printDebug("NOT normal paint");
			g2d.setColor(this.model.getColor());
			
			this.model.update(interpolation);
			
			Rectangle2D.Double rec = new Rectangle2D.Double(0,0,1,1);
			
			Shape s= this.model.getMatrix().createTransformedShape(rec);
			
			g2d.draw(s);
			
			AffineTransform aT = this.model.getInterpolationMatrix();
			
			this.shape = aT.createTransformedShape(this.ray);
			
			g2d.draw(this.shape);
		}
	}
	
}
