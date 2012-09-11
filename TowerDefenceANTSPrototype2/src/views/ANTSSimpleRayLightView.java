package views;

import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import basics.ANTSDevelopment.ANTSDebug;
import basics.ANTSDevelopment.ANTSStream;

import models.ANTSSimpleRayLightModel;
import models.ANTSSimpleRayLightModel;

public class ANTSSimpleRayLightView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleRayLightModel model;
	private	Line2D.Double ray;
	private Line2D.Double line;
	
	public ANTSSimpleRayLightView(ANTSSimpleRayLightModel m)
	{
		super();
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
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	
	public double[] getCenter()
	{
		this.updateShape(-1);
		
		double[] center = new double[2];
		
		center[0] = this.shape.getBounds2D().getCenterX();
		center[1] = this.shape.getBounds2D().getCenterY();
		
		return center;
	}

	@Override
	protected void updateShape(float interpolation) 
	{
		AffineTransform aT = new AffineTransform();
		
		if(interpolation<0)
		{
			//no interpolation
			aT = this.model.getMatrix();
		}
		else
		{
			//iterpolation
			aT = this.model.getInterpolationMatrix();
		}
		
		double length = this.model.getLength();
		
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
		
//		paintCenter(g2d);
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		g2d.setColor(this.model.getColor());
		
		this.model.update(interpolation);

		this.updateShape(interpolation);

		g2d.draw(this.shape);
		
		this.paintBounds(g2d);
		
//		paintCenter(g2d);
	}
	
	private void paintCenter(Graphics2D g2d)
	{
		double[] c = this.getCenter();
		Rectangle2D rec = new Rectangle2D.Double(c[0], c[1], 5, 5);
		
		g2d.setColor(Color.red);
		g2d.draw(rec);
	}
}
