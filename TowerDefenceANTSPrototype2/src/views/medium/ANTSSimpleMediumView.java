package views.medium;

import interfaces.ANTSIController;
import interfaces.ANTSIRayController;
import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import basics.ANTSDevelopment.ANTSStream;

import views.ANTSAbstractView;


import models.medium.ANTSSimpleMediumModel;

public class ANTSSimpleMediumView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleMediumModel model;
//	private ArrayList<Shape> detectionLines;
	
	public ANTSSimpleMediumView(ANTSSimpleMediumModel m) 
	{
		super();
		this.model = m;
//		this.detectionLines = new ArrayList<Shape>();
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
		Rectangle2D.Double rec = new Rectangle2D.Double(0, 0, this.model.getWidth(), this.model.getHeight());
		AffineTransform aT = this.model.getMatrix();
		this.shape = aT.createTransformedShape(rec);
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
			
			this.paintLines(g2d);
	}

	private void paintLines(Graphics2D g2d) {
		double x = this.model.getMatrix().getTranslateX();
		double y = this.model.getMatrix().getTranslateY();
		
		Line2D line = new Line2D.Double(x-50,299,x+this.model.getWidth()+50,299);
		
		g2d.draw(line);
		
	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		this.paint(g2d);
	}
}
