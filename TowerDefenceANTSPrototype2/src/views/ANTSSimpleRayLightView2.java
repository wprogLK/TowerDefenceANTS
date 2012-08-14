package views;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

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
		
		this.ray = new Line2D.Double(x,y,x+length,y);
	}
	
	@Override
	public void paint(Graphics2D g2d) 
	{		

	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		g2d.setColor(this.model.getColor());
		
		this.model.update(interpolation);
		
		AffineTransform aT = this.model.getInterpolationMatrix();
		
		Shape s = aT.createTransformedShape(this.ray);
		
		g2d.draw(s);
	}
	
}
