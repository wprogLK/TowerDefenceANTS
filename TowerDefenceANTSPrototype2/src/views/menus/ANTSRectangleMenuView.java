package views.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuView;

import controllers.menus.ANTSMenuItemCircleController;
import controllers.menus.ANTSMenuItemRectangleController;


import views.ANTSAbstractView;

import models.menus.ANTSCircleMenuModel;
import models.menus.ANTSRectangleMenuModel;

public class ANTSRectangleMenuView extends ANTSAbstractView implements ANTSIView, ANTSIMenuView
{
	private ANTSRectangleMenuModel model;
	
	public ANTSRectangleMenuView(ANTSRectangleMenuModel m) 
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

		Rectangle2D.Double rec = new Rectangle2D.Double(0, 0, this.model.getMinWidth(), this.fontSize*this.model.getMaxIndexMenuItem());
		
		this.shape = aT.createTransformedShape(rec);
		
		g2d.setColor(Color.black);
		
		if(this.model.getMouseEntered())
		{
			g2d.draw(this.shape);	//only for debugging
			this.paintMenuItems(g2d, interpolation);
		}
		
		
	}
	
	private void paintMenuItems(Graphics2D g2d, float interpolation)
	{
		 Iterator<ANTSMenuItemRectangleController> itemIterator = this.model.getIteratorMenuItems();
		 
		 while(itemIterator.hasNext())
		 {
			 ANTSMenuItemRectangleController c = itemIterator.next();
			 c.getIView().paint(g2d, interpolation);
		 }
	}
}
