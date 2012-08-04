package views.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuView;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import views.ANTSAbstractView;

import basics.ANTSDriver;
import basics.ANTSDevelopment.ANTSStream;

import models.ANTSCellModel;
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
//			Ellipse2D circle = new Ellipse2D.Double(-this.model.getRadius()/2, -this.model.getRadius()/2, this.model.getRadius(), this.model.getRadius());
//			this.shape = aT.createTransformedShape(circle);
//			g2d.draw(this.shape);
//			g2d.setColor(Color.black);
		}
		else
		{
		
		}
		
		int maxNumberOfItems = this.parentModel.getMaxIndexMenuItem();
		
		AffineTransform matrix = (AffineTransform) this.parentModel.getMatrix().clone();
		
		double segmentDegree = 360/(maxNumberOfItems*4);
		
		new Arc2D.Double(5, 10, 150, 150, 60, 150, Arc2D.OPEN);
		
		Arc2D.Double arc = new Arc2D.Double(0, 0, this.parentModel.getRadius(),  this.parentModel.getRadius(), segmentDegree*this.model.getIndex(), segmentDegree*(this.model.getIndex()+1), Arc2D.PIE);
		
		g2d.draw(arc);
		System.out.println("item paint");
	}
}
