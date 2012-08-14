package views.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;

import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuView;

import views.ANTSAbstractView;

import models.menus.ANTSCircleMenuModel;
import models.menus.ANTSMenuItemCircleModel;

public class ANTSMenuItemCircleView extends ANTSAbstractView implements ANTSIView, ANTSIMenuView
{
	private ANTSCircleMenuModel parentModel;
	private ANTSMenuItemCircleModel model;
	private final double offsetAngle = 45;
	
	private static int fontSize = 12;
	private static Font font = new Font("Courier New", Font.PLAIN, fontSize );
	private static Color fontColor = Color.BLACK;
	private static int[] startPos = {fontSize, fontSize};
	private static int[] currentPos = {10, 10};
	
	//for the correct position
	private double dx = 403.9230484541;	
	private double dy = 180;
	
	
	public ANTSMenuItemCircleView(ANTSMenuItemCircleModel model, ANTSCircleMenuModel parentModel) 
	{
		super();
		
		this.model = model;
		this.parentModel = parentModel;
	}
		
	///////////
	//Getters//
	///////////
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	
	public String toString()
	{
		return "Cell view";
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	@Override
	public void paint(Graphics2D g2d)
	{
		this.paint(g2d, 0);
	}
	
	@Override
	public boolean doPaintDirect()
	{
		return false;
	}
	
	
	@Override
	public void paint(Graphics2D g2d, float interpolation)
	{
		double recWidth = 150;
		double recHeight = 50;
		
		g2d.setColor(Color.black);
		
		AffineTransform aT = this.parentModel.getMatrix();
		Double[] centerCircle = {aT.getTranslateX(),aT.getTranslateY()};
		
		int maxNumbersOfItems = this.parentModel.getMaxIndexMenuItem();
		double segmentAngleDeg = -360/((double) maxNumbersOfItems);
		
		double rotateAngleDeg = segmentAngleDeg*(this.model.getIndex()-1)+this.offsetAngle;
		
		Arc2D.Double arc = new Arc2D.Double(centerCircle[0], centerCircle[1], this.parentModel.getRadius(),  this.parentModel.getRadius(), 0, segmentAngleDeg, Arc2D.PIE);
		Rectangle2D.Double rec = new Rectangle2D.Double(centerCircle[0]-recWidth/2, centerCircle[1]-this.parentModel.getRadius()/2, recWidth, recHeight);
		
		arc.setArcByCenter(centerCircle[0], centerCircle[1], this.parentModel.getRadius()/2, 90, segmentAngleDeg, 2);
		
		g2d.draw(arc);
		g2d.draw(rec);
	
		AffineTransform rT = new AffineTransform();
		rT.rotate(Math.toRadians(rotateAngleDeg), centerCircle[0], centerCircle[1]);
		
		Shape rArc = rT.createTransformedShape(arc);
		Shape rRec = rT.createTransformedShape(rec);
		
		g2d.setColor(Color.BLUE);
		
		this.shape = rArc;
		
		if(this.model.getMouseEntered())
		{
				g2d.setColor(Color.blue);
		}
		else
		{
			g2d.setColor(Color.red);
		}
		
		g2d.fill(rArc);
		
		g2d.setColor(Color.black);
		g2d.draw(rArc);
//		g2d.draw(rRec);
		
		AffineTransform rRecT = new AffineTransform();
		rRecT.rotate(Math.toRadians(180-rotateAngleDeg), rRec.getBounds2D().getCenterX(), rRec.getBounds2D().getCenterY());
		
		Shape rRec2 = rRecT.createTransformedShape(rRec);
		
		if(this.model.getMouseEntered())
		{
				g2d.setColor(Color.blue);
		}
		else
		{
			g2d.setColor(Color.red);
		}
		
		g2d.fill(rRec2);
		
		g2d.setColor(Color.black);
		g2d.draw(rRec2);
		
		g2d.drawString(this.model.getText(),(int) rRec2.getBounds2D().getMinX()+10,(int) rRec2.getBounds2D().getCenterY()+5);
	}
	
	
//	@Override
//	public void paint(Graphics2D g2d, float interpolation)
//	{
//		AffineTransform aT = this.parentModel.getMatrix();
//		
//		g2d.setColor(Color.black);
//		
//		
//		if(this.model.getMouseEntered())
//		{
//			g2d.setColor(Color.blue);
//		}
//		else
//		{
//			g2d.setColor(Color.red);
//		}
//		
//		int maxNumberOfItems = this.parentModel.getMaxIndexMenuItem();
//		
//		double segmentDegree = 360/(maxNumberOfItems);
//		
//		
//		double start = segmentDegree*(this.model.getIndex()-1)+this.offsetAngle;
//		
//		Arc2D.Double arc = new Arc2D.Double(-this.parentModel.getRadius()/2, -this.parentModel.getRadius()/2, this.parentModel.getRadius(),  this.parentModel.getRadius(), -start-segmentDegree, -segmentDegree, Arc2D.PIE);
//		
//		this.shape = aT.createTransformedShape(arc);
//		
//		g2d.fill(shape);
//	
//		
//		g2d.setColor(this.fontColor);
//		g2d.setFont(this.font);
//		
//		Double[] centerCircle = {aT.getTranslateX(),aT.getTranslateY()};
//		
////		this.paintRec(start-this.offsetAngle , g2d, centerCircle);
//		
//		
//	}

//	private void paintRec(double angle, Graphics2D g2d, Double[] centerCircle)
//	{
//		if(this.model.getMouseEntered())
//		{
//			g2d.setColor(Color.blue);
//		}
//		else
//		{
//			g2d.setColor(Color.red);
//		}
//		
//		double xCenter = centerCircle[0]-(centerCircle[0]-403.923484541);
//		double yCenter = centerCircle[1]-(centerCircle[1]-180);
//		
//		//g2d.drawRect((int) xCenter, (int) yCenter, 1, 1);	//center
//		
//		AffineTransform aT = new AffineTransform();
//		aT.rotate(Math.toRadians(angle), xCenter, yCenter);
//		
//		Rectangle2D.Double  rec = new Rectangle2D.Double(250, 250, 150, 50);
//		
//		Shape s  = aT.createTransformedShape(rec);
//		
//		AffineTransform aT2 = new AffineTransform();
//		aT2.rotate(Math.toRadians(180-angle), s.getBounds2D().getCenterX(), s.getBounds2D().getCenterY());
//		
//		Shape s2 = aT2.createTransformedShape(s);
//		
//		AffineTransform t3 = new AffineTransform();
//		t3.translate(centerCircle[0]-dx, centerCircle[1]-dy);
//		
//		Shape s3 = t3.createTransformedShape(s2);
//		
//		
//		if(this.model.getMouseEntered())
//		{
//			g2d.setColor(Color.blue);
//		}
//		else
//		{
//			g2d.setColor(Color.red);
//		}
//		
//		g2d.fill(s3);
//		g2d.setColor(Color.BLACK);
//		g2d.draw(s3);
//		g2d.drawString(this.model.getText(),(int) s3.getBounds2D().getMinX()+10,(int) s3.getBounds2D().getCenterY()+5);
//	}
	
}
