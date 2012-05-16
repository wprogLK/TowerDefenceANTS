package views;

import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import models.ANTSSimpleSourceLightModel;

public class ANTSSimpleSourceLightView implements ANTSIView
{
	private ANTSSimpleSourceLightModel model;
	
	private JPanel panel;
	
	public ANTSSimpleSourceLightView(ANTSSimpleSourceLightModel m) 
	{
		this.model = m;
	}

	@Override
	public void paint(Graphics2D g2d) 
	{
		AffineTransform aT = this.model.getMatrix();
		double radius = this.model.getRadius();
		
		Ellipse2D circle = new Ellipse2D.Double(aT.getTranslateX()-(radius/2), aT.getTranslateY()-(radius/2), radius, radius);
		Shape shape = aT.createTransformedShape(circle);
	
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

	@Override
	public JPanel getPanel()
	{
		return this.panel;
	}
}
