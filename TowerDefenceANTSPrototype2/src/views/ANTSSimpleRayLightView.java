package views;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import models.ANTSSimpleRayLightModel;

public class ANTSSimpleRayLightView implements ANTSIView
{
	private ANTSSimpleRayLightModel model;
	private	Line2D.Double ray;
	
	public ANTSSimpleRayLightView(ANTSSimpleRayLightModel m)
	{
		this.model = m;
		this.setupRay();
	}
	
	private void setupRay() 
	{
		double length = this.model.getLength();
		double x = this.model.getMatrix().getTranslateX();
		double y = this.model.getMatrix().getTranslateY();
		
		this.ray = new Line2D.Double(x,y,x+length,y);
	}
	
	public boolean pointIsIn(double x, double y)
	{
		return false;	//TODO
	}

	@Override
	public void paint(Graphics2D g2d) 
	{
		g2d.setColor(this.model.getColor());
		
		AffineTransform aT = this.model.getMatrix();
		Shape s = aT.createTransformedShape(this.ray);
		
		g2d.draw(s);
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
