package views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import interfaces.ANTSIView;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import basics.ANTSDriver;
import basics.ANTSDevelopment.ANTSStream;

import models.ANTSCellModel;
import models.ANTSCircleMenuModel;

public class ANTSCircleMenuView extends ANTSAbstractView implements ANTSIView
{
	private ANTSCircleMenuModel model;
	
	public ANTSCircleMenuView(ANTSCircleMenuModel m) 
	{
		super();
		
		this.model = m;
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
		AffineTransform aT = this.model.getMatrix();
		
		g2d.setColor(Color.black);
		
		if(this.model.getMouseEntered())
		{
			g2d.setColor(Color.blue);
			Ellipse2D circle = new Ellipse2D.Double(-this.model.getRadius()/2, -this.model.getRadius()/2, this.model.getRadius(), this.model.getRadius());
			this.shape = aT.createTransformedShape(circle);
			g2d.draw(this.shape);
			g2d.setColor(Color.black);
			ANTSStream.print("draw menu");
		}
	}	 
}
