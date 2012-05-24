package views;

import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
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
		super(true);
		
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
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		this.createCircle();			//TODO CHECK IF position and radius has changed
		AffineTransform aT = this.model.getMatrix();
		Shape shape = aT.createTransformedShape(circle);
		this.setBounds(shape.getBounds());

		if(this.isDragged)
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
				1f, //Limit für Join
				dash_array, //Strichelung
				0 //offset in Pixeln f. Strichelung
				);
			g2d.setStroke(dashStroke);
			g2d.draw(shape.getBounds2D());
			
			g2d.setStroke(new BasicStroke());
		}
		
		
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
		//Only an example
		this.model.switchLight();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
	
		
	}
	
	
	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		super.mouseDragged(e);

		this.model.setPosition(e.getX()/2, e.getY()/2);	//TODO: Important: test this! is it always /2 ? 
	}
	
		
}
