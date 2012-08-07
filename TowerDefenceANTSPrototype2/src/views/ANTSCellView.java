package views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.Iterator;

import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuController;

import models.ANTSCellModel;

public class ANTSCellView extends ANTSAbstractView implements ANTSIView
{
	private ANTSCellModel model;
	
	public ANTSCellView(ANTSCellModel m) 
	{
		super();
		
		this.model = m;
	}
		
	///////////
	//Getters//
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
	public boolean doPaintDirect()
	{
		return false;
	}
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	
	@Override
	public void paint(Graphics2D g2d)
	{
		this.paint(g2d, 0);
	}
	
	
	@Override
	public void paint(Graphics2D g2d, float interpolation)
	{
		AffineTransform aT = this.model.getMatrix();
		
		g2d.setColor(Color.black);
		
		if(this.model.getMouseEntered())
		{
			g2d.setColor(Color.blue);
		}
		
		GeneralPath g = new GeneralPath();
		double[][] points = this.model.getPoints();
		g.moveTo(points[0][0],points[0][1]);
		
		for(int x = 0; x<4; x++)
		{
			g.lineTo(points[x][0],points[x][1]);
		}
		
		g.closePath();
		
		this.shape = aT.createTransformedShape(g);
		
		g2d.draw(this.shape);
		g2d.setColor(Color.black);
		
		this.drawMenu(g2d,interpolation);
	}

	private void drawMenu(Graphics2D g2d, float interpolation) {
		Iterator<ANTSIMenuController> menuIterator = this.model.getMenuIterator();
		
		while(menuIterator.hasNext())
		{
			ANTSIMenuController c = menuIterator.next();
			c.getIView().paint(g2d, interpolation);
		}
		
	}	 
	
	
	
}
