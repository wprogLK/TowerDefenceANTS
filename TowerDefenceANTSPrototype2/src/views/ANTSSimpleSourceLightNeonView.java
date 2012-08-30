package views;

import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import models.ANTSSimpleSourceLightModel;
import models.ANTSSimpleSourceLightModel;
import models.ANTSSimpleSourceLightNeonModel;

public class ANTSSimpleSourceLightNeonView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleSourceLightNeonModel model;
//	private Ellipse2D circle;
	private Rectangle2D rectangle;
	
	public ANTSSimpleSourceLightNeonView(ANTSSimpleSourceLightNeonModel m) 
	{
		super();
		
		this.model = m;
		
		this.createRectangle();
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
	
	private void createRectangle()
	{
		AffineTransform aT = this.model.getMatrix();
		
		this.rectangle = new Rectangle2D.Double(0, 0, this.model.getLength(), this.model.getHeight());
		this.shape = aT.createTransformedShape(this.rectangle);
	}
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		this.createRectangle();			//TODO CHECK IF position and radius has changed
		
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
		
		//Debug rotate a point:
		
		AffineTransform atC = this.model.getCenter();
		AffineTransform atM = this.model.getMatrix();
		
		Rectangle2D cRec = new Rectangle2D.Double(0,0,1,1);	//center
		Rectangle2D pPreRec = new Rectangle2D.Double(10,10,1,1);	//previous point
		
		Point2D pPrev = new Point2D.Double(10, 10);
		Point2D pAfter = new Point2D.Double(10,10);
		
		atM.transform(pPrev,pAfter );
//		at.transform(ptSrc, ptDst)
		
		Rectangle2D pAfterRec = new Rectangle2D.Double(pAfter.getX(),pAfter.getY(),10,10);
		
		Shape sCenter = atC.createTransformedShape(cRec);
		Shape sPrevPoint = atM.createTransformedShape(pPreRec);
//		Shape sAfterPoint = at.createTransformedShape(pAfterRec);
		
		g2d.setColor(Color.BLUE);
		g2d.draw(sCenter);
		g2d.draw(sPrevPoint);
		
		g2d.setColor(Color.RED);
		g2d.draw(pAfterRec);
		
		//-------------
		
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
	
	
public Point2D rotatePoint(Point2D punkt, Point2D ursprung, double rot)
{
		double x = punkt.getX();
		double y = punkt.getY();
	
		x-=ursprung.getX();
        y-=ursprung.getY();
        
        double radius=Math.sqrt(punkt.getX()*punkt.getX()+punkt.getY()*punkt.getY());
        
        rot+=Math.atan2(punkt.getY(), punkt.getX()) * 180 / Math.PI;
        
       // double radWinkel = rot / 180.0 * Math.PI;
        double radWinkel = Math.toRadians(rot);
        
        Point2D.Double point = new Point2D.Double();
        
        point.x = Math.cos(radWinkel) * radius + ursprung.getX();
        point.y = Math.sin(radWinkel) * radius + ursprung.getY();
        
        return point;
    }
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		this.paint(g2d);
	}
	

}
