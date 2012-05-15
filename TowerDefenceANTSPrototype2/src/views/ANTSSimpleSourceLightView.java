package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import models.ANTSSimpleSourceLightModel;

public class ANTSSimpleSourceLightView extends JPanel
{
	private ANTSSimpleSourceLightModel model;
	
	public ANTSSimpleSourceLightView(ANTSSimpleSourceLightModel m) 
	{
		this.model = m;
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g);

		Graphics2D g2d = (Graphics2D) g;
		
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
}
