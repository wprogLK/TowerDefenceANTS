package views;

import java.awt.Color;
import java.awt.Graphics2D;

import interfaces.ANTSIView;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controllers.ANTSCellController;

import models.ANTSGridModel;

public class ANTSGridView extends ANTSAbstractView implements ANTSIView
{
	private ANTSGridModel model;
	
	public ANTSGridView(ANTSGridModel m) 
	{
		super();
		
		this.popupMenu = new JPopupMenu("Grid popupMenu: Grid");
		this.popupMenu.add(new JMenuItem("A popup menu item of Grid"));
		
		this.model = m;
		
	}
		
	public String toString()
	{
		return "Grid view";
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	@Override
	public void paint(Graphics2D g2d)
	{
		this.paint(g2d, 0);		//TODO Test this maybe it's 1 instead of 0
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 	//TODO: Check this, if first y and then x or the other way
	{
		for(int y = 0; y < this.model.getMaxCellY(); y++)
		{
			for(int x = 0; x < this.model.getMaxCellX(); x++)
			{
				ANTSCellController controller = model.getCellControllerAt(x, y);
				controller.getView().paint(g2d, interpolation);
			}
		}
	}
}