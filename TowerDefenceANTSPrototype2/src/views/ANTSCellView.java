package views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import interfaces.ANTSIView;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import basics.ANTSDriver;

import models.ANTSCellModel;

public class ANTSCellView extends ANTSAbstractView implements ANTSIView
{
	private ANTSCellModel model;
	
	public ANTSCellView(ANTSCellModel m) 
	{
		super();
		
		this.popupMenu = new JPopupMenu("Cell popupMenu: Cell");
		this.popupMenu.add(new JMenuItem("A popup menu item Cell"));
		
		this.model = m;
		
		ANTSDriver.addToCanvas(this);
	}
		
	public String toString()
	{
		return "Cell view";
	}
	
	private void doIt()
	{
		System.out.println("do it not empty! do it cell");
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
		//TODO
		g2d.setColor(Color.BLUE);
	
		Rectangle2D rec = new Rectangle2D.Double(0, 0, model.getWidth(), model.getHeight());
		AffineTransform aT = this.model.getMatrix();
		Shape shape = aT.createTransformedShape(rec);
		this.setBounds(shape.getBounds());
		g2d.fill(shape);
		g2d.setColor(Color.white);
		g2d.draw(shape.getBounds());
		
		//g2d.draw(shape);
	}
}
