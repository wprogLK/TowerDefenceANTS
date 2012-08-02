package views;

import interfaces.ANTSIView;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public abstract class ANTSAbstractView  implements ANTSIView
{
	protected JPopupMenu popupMenu;
	protected boolean isMouseOver;
	
	private static ANTSAbstractView emptyView = new ANTSAbstractView() 
	{
		@Override
		public boolean isMouseListener() 
		{
			return false;
		}
	};
	
	public ANTSAbstractView()
	{
		//TODO

		this.popupMenu = new JPopupMenu("Simple popupMenu: ABSTRACT");
		this.popupMenu.add(new JMenuItem("A popup menu item abstract"));
		
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);	//shows the popupmenu in the foreground of the canvas!
	
		this.isMouseOver = false;
	}
	
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		// TODO Auto-generated method stub
	}
	
	//////////////
	//POPUP MENU//
	//////////////
	@Override
	public void showPopupMenu(Component component,int x, int y)
	{
		this.popupMenu.show(component, x, y);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public JPopupMenu getPopupMenu()
	{
		return this.popupMenu;
	}
	
	public final static ANTSIView getEmptyView()
	{
		return emptyView;
	}
	
	public String toString()
	{
		return "abstract view";
	}
	
	@Override
	public boolean doPaintDirect()
	{
		return true;
	}
	
	@Override
	public boolean containsPoint(int x, int y)
	{
		return false;
	}
	
	@Override
	public boolean isMouseOver()
	{
		return this.isMouseOver;
	}
}
