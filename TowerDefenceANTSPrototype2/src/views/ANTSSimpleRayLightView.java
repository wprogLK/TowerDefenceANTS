package views;

import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import basics.ANTSDevelopment.ANTSDebug;
import basics.ANTSDevelopment.ANTSStream;

import models.ANTSSimpleRayLightModel;
import models.ANTSSimpleRayLightModel;

public class ANTSSimpleRayLightView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleRayLightModel model;
	private	Line2D.Double ray;
	
	public ANTSSimpleRayLightView(ANTSSimpleRayLightModel m)
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
		
		this.ray = new Line2D.Double(0,0,length,0);
	}
	
	@Override
	public void paint(Graphics2D g2d) 
	{		
		g2d.setColor(this.model.getColor());

		AffineTransform aT = this.model.getMatrix();
		
		this.shape = aT.createTransformedShape(this.ray);
		
		g2d.draw(this.shape);
		
		this.paintBounds(g2d);
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		g2d.setColor(this.model.getColor());
		
		this.model.update(interpolation);

		AffineTransform aT = this.model.getInterpolationMatrix();

		this.shape = aT.createTransformedShape(this.ray);

		g2d.draw(this.shape);
		
		this.paintBounds(g2d);
	}
	

}
