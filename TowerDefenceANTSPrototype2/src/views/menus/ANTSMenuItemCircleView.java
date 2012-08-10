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
	
	private static int fontSize = 12;
	private static Font font = new Font("Courier New", Font.PLAIN, fontSize );
	private static Color fontColor = Color.BLACK;
	private static int[] startPos = {fontSize, fontSize};
	private static int[] currentPos = {10, 10};
	
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
		AffineTransform aT = this.parentModel.getMatrix();
		
		g2d.setColor(Color.black);
		
		
		if(this.model.getMouseEntered())
		{
			g2d.setColor(Color.blue);
		}
		else
		{
			g2d.setColor(Color.red);
		}
		
		int maxNumberOfItems = this.parentModel.getMaxIndexMenuItem();
		
		double segmentDegree = 360/(maxNumberOfItems);
		
		
		double start = segmentDegree*(this.model.getIndex()-1);
		
		Arc2D.Double arc = new Arc2D.Double(-this.parentModel.getRadius()/2, -this.parentModel.getRadius()/2, this.parentModel.getRadius(),  this.parentModel.getRadius(), -start-segmentDegree, -segmentDegree, Arc2D.PIE);
		
//		System.out.println("r " + this.parentModel.getRadius());
		this.shape = aT.createTransformedShape(arc);
		
//		if(this.model.getIndex()==4)
		{
			g2d.fill(shape);
		}
	
		
		AffineTransform t = new AffineTransform();
		double angleInRadian = Math.toRadians(start);
		
		t.rotate(angleInRadian,aT.getTranslateX(),aT.getTranslateY());
		
		t.translate(aT.getTranslateX(), aT.getTranslateY());
		
		g2d.setColor(this.fontColor);
		g2d.setFont(this.font);
		
		
		Double[] centerCircle = {aT.getTranslateX(),aT.getTranslateY()};
		
//		this.drawText(start, g2d, centerCircle);
		this.paintRec(start, g2d, centerCircle);
		
		
	}

//	private void drawText(double angle, Graphics2D g2d, Double[] centerCircle) 
//	{
//		double xOffset = 0;
//		double yOffset = 0;
//		
//		double width = 100;
//		double height = 20;
//		
//		AffineTransform t = new AffineTransform();
//		t.translate(centerCircle[0], centerCircle[1]);
//		Rectangle2D.Double rec = new Rectangle2D.Double(-this.parentModel.getRadius()/2, -this.parentModel.getRadius()/2, width, height);
//		Shape s = t.createTransformedShape(rec);
//		
//		g2d.setColor(Color.BLACK);
////		g2d.draw(s);
//		
////		ANTSStream.printDebug(String.valueOf(180-angle) + "index: " +this.model.getIndex() );
//		t.rotate(-Math.toRadians(angle));
//		s = t.createTransformedShape(rec);
//		
//		Rectangle2D.Double rec2 = new Rectangle2D.Double(s.getBounds2D().getCenterX(),s.getBounds2D().getCenterY(),10,10);
//			
//		s = t.createTransformedShape(rec);
//		
//	
//		g2d.draw(rec2);
//		
////
//	
//	
////		g2d.draw(s);
//	
//		t.rotate(-Math.toRadians(10),s.getBounds2D().getCenterX(),s.getBounds2D().getCenterY());
//
//		s = t.createTransformedShape(rec);
//		g2d.draw(s);
//		
//	}
	
	private void paintRec(double angle, Graphics2D g2d, Double[] centerCircle)
	{
		
//		System.out.println("x: " + (centerCircle[0]) + " y: "+ (centerCircle[1]));
		if(this.model.getMouseEntered())
		{
			g2d.setColor(Color.blue);
		}
		else
		{
			g2d.setColor(Color.red);
		}
		
		double xCenter = centerCircle[0]-(centerCircle[0]-403.923484541);
		double yCenter = centerCircle[1]-(centerCircle[1]-180);
		
		g2d.drawRect((int) xCenter, (int) yCenter, 1, 1);	//center
		
		AffineTransform aT = new AffineTransform();
		aT.rotate(Math.toRadians(angle), xCenter, yCenter);
		
		Rectangle2D.Double  rec = new Rectangle2D.Double(250, 250, 150, 50);
//		System.out.println("new x: " + (xCenter) + " y: "+ (yCenter));
		
		Shape s  = aT.createTransformedShape(rec);
		
		AffineTransform aT2 = new AffineTransform();
		aT2.rotate(Math.toRadians(180-angle), s.getBounds2D().getCenterX(), s.getBounds2D().getCenterY());
		
		Shape s2 = aT2.createTransformedShape(s);
		g2d.draw(s2);
		
		g2d.drawString(this.model.getText(),(int) s2.getBounds2D().getMinX(),(int) s2.getBounds2D().getMinY());
		
//		AffineTransform before =g2d.getTransform();
//		aT2.concatenate(aT);
//		aT2.rotate(Math.toRadians(180), s.getBounds2D().getCenterX(), s.getBounds2D().getCenterY());
//		g2d.setTransform(aT2);
//		
//		g2d.drawString(this.model.getText(), 250,250);
//		g2d.setTransform(before);
	}
	
}
