package views.medium;

import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import views.ANTSAbstractView;

import models.medium.ANTSSimpleMediumModel;

public class ANTSSimpleMediumView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleMediumModel model;
	
	public ANTSSimpleMediumView(ANTSSimpleMediumModel m) 
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
			
//			this.paintIntersectionPoints(g2d);
			this.paintTheIntersectionPoint(g2d);
			this.paintCenter(g2d);
	}

	private void paintCenter(Graphics2D g2d) 
	{
		Rectangle2D center = new Rectangle2D.Double(this.model.getCenter()[0], this.model.getCenter()[1] , 5, 5);
		
		g2d.setColor(Color.blue);
		g2d.fill(center);
	}

	private void paintTheIntersectionPoint(Graphics2D g2d)
	{
		g2d.setColor(Color.blue);
		
		double[] intersectionPoint = this.model.getTheIntersectionPoint();
		
		Rectangle2D.Double recIntersectionPoint = new Rectangle2D.Double(intersectionPoint[0], intersectionPoint[1], 5, 5);
		
		g2d.draw(recIntersectionPoint);
	}

	private void paintIntersectionPoints(Graphics2D g2d) 
	{
		g2d.setColor(Color.blue);
		
		double[] intersectionPointRay = this.model.getIntersectionPointRay();
		double[] intersectionPointBox = this.model.getIntersectionPointBox();
		
		Rectangle2D.Double recPointRay = new Rectangle2D.Double(intersectionPointRay[0], intersectionPointRay[1], 5, 5);
		Rectangle2D.Double recPointBox = new Rectangle2D.Double(intersectionPointBox[0], intersectionPointBox[1], 5, 5);
		
		g2d.draw(recPointBox);
		g2d.draw(recPointRay);
		
		g2d.drawString("BoxPoint", (int) intersectionPointBox[0],(int) intersectionPointBox[1]);
		g2d.drawString("RayPoint", (int) intersectionPointRay[0],(int) intersectionPointRay[1]);
	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		this.paint(g2d);
	}

	public void setIntersectionPoint(double[] intersectionPointBox,
			double[] intersectionPointRay) {
		// TODO Auto-generated method stub
		
	}
}
