package views.lens;


import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import views.ANTSAbstractView;

import models.lens.ANTSSimpleLensModel;

public class ANTSSimpleLensView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleLensModel model;
	
	public ANTSSimpleLensView(ANTSSimpleLensModel m) 
	{
		super(m);
		this.model = m;
	}
	
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "Simple medium view"; 
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
	

	@Override
	protected void updateShape(float interpolation) 
	{
		double radius = this.model.getRadius();
		
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, radius, radius);
		
		AffineTransform aT = this.model.getMatrix();
		this.shape = aT.createTransformedShape(circle);
	}	
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		
		this.updateShape(-1);
		
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
		
		
			g2d.setColor(Color.GREEN);
			g2d.fill(shape);
			this.paintBounds(g2d);
	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		this.paint(g2d);
	}
}
