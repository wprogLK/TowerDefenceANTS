package views.menus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuView;

import views.ANTSAbstractView;

import models.menus.ANTSCircleMenuModel;
import models.menus.ANTSMenuItemCircleModel;

public class ANTSMenuItemCircleView extends ANTSAbstractView implements ANTSIView, ANTSIMenuView
{
	private ANTSCircleMenuModel parentModel;
	private ANTSMenuItemCircleModel model;
	private final double offsetAngle = 0;

	private Double[] centerCircle;
	private double rotateAngleDeg;
	private double segmentAngleDeg;
	
	public ANTSMenuItemCircleView(ANTSMenuItemCircleModel model, ANTSCircleMenuModel parentModel) 
	{
		super(model);
		
		this.model = model;
		this.parentModel = parentModel;
		this.centerCircle = new Double[2];
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
	
	@Override
	protected void updateShape(float interpolation) 
	{
//		double recWidth = this.parentModel.getRadius()/2-20;
//		double recHeight = 25;
		
		
		AffineTransform aT = this.parentModel.getMatrix();
		this.centerCircle[0] = aT.getTranslateX();
		this.centerCircle[1] = aT.getTranslateY();
		
		int maxNumbersOfItems = this.parentModel.getMaxIndexMenuItem();
		this.segmentAngleDeg = -360/((double) maxNumbersOfItems);
		
		this.rotateAngleDeg = segmentAngleDeg*(this.model.getIndex()-1)+this.offsetAngle;
		
		Arc2D.Double arc = new Arc2D.Double(centerCircle[0], centerCircle[1], this.parentModel.getRadius(),  this.parentModel.getRadius(), 0, segmentAngleDeg, Arc2D.PIE);
		
		arc.setArcByCenter(centerCircle[0], centerCircle[1], this.parentModel.getRadius()/2, 90, segmentAngleDeg, 2);
		
		AffineTransform rT = new AffineTransform();
		rT.rotate(Math.toRadians(rotateAngleDeg), centerCircle[0], centerCircle[1]);
		
		Shape rArc = rT.createTransformedShape(arc);

		this.shape = rArc;
	}	
	
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
		this.paint(g2d, -1);
	}
	
	@Override
	public boolean doPaintDirect()
	{
		return false;
	}
	
	
	@Override
	public void paint(Graphics2D g2d, float interpolation)
	{
		this.updateShape(interpolation);
		
		//TODO: refactoring this (similar code like updateShape)
		
		double recWidth = this.parentModel.getRadius()/2-20;
		double recHeight = 25;
		
		g2d.setColor(Color.black);
		
		AffineTransform aT = this.parentModel.getMatrix();
		this.centerCircle[0] = aT.getTranslateX();
		this.centerCircle[1] = aT.getTranslateY();
		
		int maxNumbersOfItems = this.parentModel.getMaxIndexMenuItem();
		this.segmentAngleDeg = -360/((double) maxNumbersOfItems);
		
		this.rotateAngleDeg = segmentAngleDeg*(this.model.getIndex()-1)+this.offsetAngle;
		
		Arc2D.Double arc = new Arc2D.Double(centerCircle[0], centerCircle[1], this.parentModel.getRadius(),  this.parentModel.getRadius(), 0, segmentAngleDeg, Arc2D.PIE);
		Rectangle2D.Double rec = new Rectangle2D.Double(centerCircle[0]+20, centerCircle[1]-this.parentModel.getRadius()/2-recHeight, recWidth, recHeight);
		
		arc.setArcByCenter(centerCircle[0], centerCircle[1], this.parentModel.getRadius()/2, 90, segmentAngleDeg, 2);
		
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
}
