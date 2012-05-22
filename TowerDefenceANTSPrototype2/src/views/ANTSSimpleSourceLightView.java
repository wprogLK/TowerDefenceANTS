package views;

import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import models.ANTSSimpleSourceLightModel;

public class ANTSSimpleSourceLightView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleSourceLightModel model;
	private Ellipse2D circle;
	
	public ANTSSimpleSourceLightView(ANTSSimpleSourceLightModel m) 
	{
		this.addMouseListener(this);
		this.model = m;
		this.createCircle();
		this.setIgnoreRepaint(true);
	}
	
	private void createCircle()
	{
		AffineTransform aT = this.model.getMatrix();
		double radius = this.model.getRadius();
		
		this.circle = new Ellipse2D.Double(aT.getTranslateX()-(radius/2), aT.getTranslateY()-(radius/2), radius, radius);
	}
	
//	public boolean pointIsIn(double x, double y)
//	{
//		System.out.println("----------------------------------");
//		System.out.println("INPUT: X " + x + " Y " + y);
//		System.out.println(this.model.toString());
//		
//		System.out.println("----------------------------------");
//	
//		return this.circle.contains(x, y);
//	}

	@Override
	public void paint(Graphics2D g2d) 
	{
		this.createCircle();			//TODO CHECK IF position and radius has changed
		AffineTransform aT = this.model.getMatrix();
		Shape shape = aT.createTransformedShape(circle);
		Rectangle2D box= shape.getBounds2D();	//TODO Only for debug
		this.setBounds(shape.getBounds());
		g2d.setColor(Color.GRAY);//TODO Only for debug
		g2d.draw(box);//TODO Only for debug
		
		if(this.model.isOn())
		{
			g2d.setColor(this.model.getColor());
			g2d.fill(shape);
		}
		else
		{
			g2d.setColor(Color.black);
			g2d.draw(shape);
		}
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		//interpolation not needed for this view!
		this.paint(g2d);
	}
	
	public String toString()
	{
		return "SOURCE light " + this.model.getPosX() + " " + this.model.getPosY() + " " + this.model.getColor();
	}
	
	/////////
	//MOUSE//
	/////////
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		System.out.println("MOUSE CLICKED");	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
	System.out.println("entered me: " + toString());
		
	}
	
	
	@Override
	public void mouseExited(MouseEvent e) 
	{
		System.out.println("exited me: " + toString());
	}
		
}
