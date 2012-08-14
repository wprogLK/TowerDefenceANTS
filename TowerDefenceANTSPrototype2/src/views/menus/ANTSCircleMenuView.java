package views.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;

import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuView;

import controllers.menus.ANTSMenuItemCircleController;


import views.ANTSAbstractView;

import models.menus.ANTSCircleMenuModel;

public class ANTSCircleMenuView extends ANTSAbstractView implements ANTSIView, ANTSIMenuView
{
	private ANTSCircleMenuModel model;
	
	public ANTSCircleMenuView(ANTSCircleMenuModel m) 
	{
		super();
		
		this.model = m;
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
		AffineTransform aT = this.model.getMatrix();
		
		g2d.setColor(Color.black);
		
		if(this.model.getMouseEntered())
		{
			g2d.setColor(Color.blue);
			Ellipse2D circle = new Ellipse2D.Double(-this.model.getRadius()/2, -this.model.getRadius()/2, this.model.getRadius(), this.model.getRadius());
			this.shape = aT.createTransformedShape(circle);
			g2d.setColor(Color.cyan);
			
			g2d.draw(shape);
			
			this.paintMenuItems(g2d, interpolation);
		}
	}
	
	private void paintMenuItems(Graphics2D g2d, float interpolation)
	{
		 Iterator<ANTSMenuItemCircleController> itemIterator = this.model.getIteratorMenuItems();
		 
		 while(itemIterator.hasNext())
		 {
			 ANTSMenuItemCircleController c = itemIterator.next();
			 c.getIView().paint(g2d, interpolation);
		 }
	}
}
