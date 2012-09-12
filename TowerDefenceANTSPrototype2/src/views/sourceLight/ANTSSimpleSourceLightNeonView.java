package views.sourceLight;

import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import views.ANTSAbstractView;

import models.sourceLight.ANTSSimpleSourceLightNeonModel;

public class ANTSSimpleSourceLightNeonView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleSourceLightNeonModel model;
	private Rectangle2D rectangle;
	
	public ANTSSimpleSourceLightNeonView(ANTSSimpleSourceLightNeonModel m) 
	{
		super();
		
		this.model = m;
		
		this.updateShape(-1);
	}
	
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "SOURCE light " + this.model.getPosX() + " " + this.model.getPosY() + " " + this.model.getColor();
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
		AffineTransform aT = this.model.getMatrix();
		
		this.rectangle = new Rectangle2D.Double(0, 0, this.model.getLength(), this.model.getHeight());
		this.shape = aT.createTransformedShape(this.rectangle);
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
		
		this.paintBounds(g2d);
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		this.paint(g2d);
	}
}
