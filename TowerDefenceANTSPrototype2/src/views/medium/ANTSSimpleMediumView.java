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
		return "medium";
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
//		this.prepareDetectionLines();
		
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
			
//			g2d.setColor(Color.BLACK);
//			for(Shape s: this.detectionLines)
//			{
//				g2d.draw(s);
//			}
	}
	
//	private void prepareDetectionLines() 
//	{
//		this.detectionLines.clear();
//		
//		Line2D lineUp = new Line2D.Double(0,0, this.model.getWidth(),0);
//		Line2D lineDown = new Line2D.Double(0,this.model.getHeight(), this.model.getWidth(),this.model.getHeight());
//		Line2D lineRight = new Line2D.Double(this.model.getWidth(),0, this.model.getWidth(),this.model.getHeight());
//		Line2D lineLeft = new Line2D.Double(0,0, 0,this.model.getHeight());
//		
//		AffineTransform aT = this.model.getMatrix();
//		
//		Shape up = aT.createTransformedShape(lineUp);
//		Shape down = aT.createTransformedShape(lineDown);
//		Shape right = aT.createTransformedShape(lineRight);
//		Shape left = aT.createTransformedShape(lineLeft);
//		
//		this.detectionLines.add(up);
//		this.detectionLines.add(right);
//		this.detectionLines.add(down);
//		this.detectionLines.add(left);
//		
//	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		this.paint(g2d);
	}
	
	/*
	 * at the moment with a very simple method without rotated medium!
	 *TODO: with rotated medium
	 */
	public double calculatePlumbAngle(ANTSIRayController ray)	//TODO test this!! 
	{
		double angle;
		
		if(ray.getAngle()<0)
		{
			angle = 360 + ray.getAngle();
		}
		else
		{
			angle = ray.getAngle();
		}
		//TODO case if ray.getAngle>360 and <-360
		
		if((315<=angle && angle<45))
		{
			ANTSStream.printDebug("area A");
			return 0;
		}
		else if((45<=angle && angle<135))
		{
			ANTSStream.printDebug("area B");
			return 90;
		}
		else if((135<=angle && angle<225))
		{
			ANTSStream.printDebug("area C");
			return 180;
		}
		else if((225<=angle && angle<315))
		{
			ANTSStream.printDebug("area D");
			return 270;
		}
		else
		{
			ANTSStream.printDebug("no idea what happened except the angle of the ray was " + angle);
			return 0;
		
//		if((315<=ray.getAngle() && ray.getAngle()<45) || ((-45>=ray.getAngle() && ray.getAngle()<=0) || (-315>=ray.getAngle() && ray.getAngle()>-360)))
//		{
//			ANTSStream.printDebug("area A");
//			return 0;
//		}
//		else if((45<=ray.getAngle() && ray.getAngle()<135) || (-45>=ray.getAngle() && ray.getAngle()>-135))
//		{
//			ANTSStream.printDebug("area B");
//			return 90;
//		}
//		else if((135<=ray.getAngle() && ray.getAngle()<225) || (-135>=ray.getAngle() && ray.getAngle()>-225))
//		{
//			ANTSStream.printDebug("area C");
//			return 180;
//		}
//		else if((225<=ray.getAngle() && ray.getAngle()<315) || (-225>=ray.getAngle() && ray.getAngle()>-315))
//		{
//			ANTSStream.printDebug("area D");
//			return 270;
//		}
//		else
//		{
//			ANTSStream.printDebug("no idea what happened except the angle of the ray was " + ray.getAngle());
//			return 0;
		}
		
		
//		int[] angles = { 90, 180, 270, 0};
//		
//		for(int i = 0; i<4; i++)
//		{
//			Shape s = this.detectionLines.get(i);
//			
////			if(s.intersects(ray.getIView().getShape().getBounds2D()))
//			if(ray.doesCollideWith(medium))
//			{
//				return angles[i];
//			}
//		}
//		
//		System.out.println("no angle found");
//		return 0;
	}


}
