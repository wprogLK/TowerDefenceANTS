package views.lens;

import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import views.ANTSAbstractView;

import models.lens.ANTSSimpleLensModel;
import models.sourceLight.ANTSSimpleSourceLightModel;

public class ANTSSimpleLensView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleLensModel model;
	private Ellipse2D circle;
	
	public ANTSSimpleLensView(ANTSSimpleLensModel m) 
	{
		super();
		
		this.model = m;
		
		this.createCircle();
	}
	
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "lens " + this.model.getPosX() + " " + this.model.getPosY() + " " + this.model.getColor();
	}

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
	
	private void createCircle()
	{
		AffineTransform aT = this.model.getMatrix();
		double radius = this.model.getRadius();
		
		this.circle = new Ellipse2D.Double(0, 0, radius, radius);
		this.shape = aT.createTransformedShape(circle);
	}
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		this.createCircle();			//TODO CHECK IF position and radius has changed
		
		if(this.model.isDragged())
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
				1f, //Limit f�r Join
				dash_array, //Strichelung
				0 //offset in Pixeln f. Strichelung
				);
			
			g2d.setStroke(dashStroke);
			g2d.draw(shape.getBounds2D());
			
			g2d.setStroke(new BasicStroke());
		}
		
		
			g2d.setColor(this.model.getColor());
			g2d.fill(shape);
		
		this.paintBounds(g2d);
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		this.paint(g2d);
	}
	

}
