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
		
		Arc2D.Double arc = new Arc2D.Double(-this.parentModel.getRadius()/2, -this.parentModel.getRadius()/2, this.parentModel.getRadius(),  this.parentModel.getRadius(), start, segmentDegree, Arc2D.PIE);
		
		
		this.shape = aT.createTransformedShape(arc);
		g2d.fill(shape);
		
		AffineTransform t = new AffineTransform();
		double angleInRadian = Math.toRadians(start);
		
		t.rotate(angleInRadian,aT.getTranslateX(),aT.getTranslateY());
		
		t.translate(aT.getTranslateX(), aT.getTranslateY());
		
		g2d.setColor(this.fontColor);
		g2d.setFont(this.font);
		
		AffineTransform before = g2d.getTransform();
		
		g2d.drawLine(0, 0, (int) t.getTranslateX(), (int) t.getTranslateY());
		
		g2d.setTransform(t);
		g2d.drawString(this.model.getText(),(int)0,(int) 0);
		g2d.setTransform(before);
		
	}
	
}
