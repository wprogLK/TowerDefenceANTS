package views;

import java.awt.Color;
import java.awt.Graphics2D;

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
	public void paint(Graphics2D g2d, float interpolation)
	{
		//TODO
		g2d.setColor(Color.BLUE);
//		System.out.println("Paint Cell with interpolation of " + interpolation + (int) this.model.getAbsolutPosX() + " "+(int) this.model.getAbsolutPosY() +" "+ (int) this.model.getWidth() +" "+ (int) this.model.getHeight());
		g2d.fillRect((int) this.model.getAbsolutPosX()+5, (int) this.model.getAbsolutPosY()+5, (int) this.model.getWidth()-5, (int) this.model.getHeight()-5); //Only for testing
//		g2d.fillRect(40,40,40,40);
//		g2d.drawRect(40,40,40,40);
	
	}
}
