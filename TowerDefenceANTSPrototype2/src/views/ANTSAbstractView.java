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

public abstract class ANTSAbstractView extends  Container implements ANTSIView 
{
	protected JPopupMenu popupMenu;
	
	private static ANTSAbstractView emptyView = new ANTSAbstractView() 
	{
		private void doIt()
		{
			
			System.out.println("do it empty");
			this.popupMenu = new JPopupMenu("Simple popupMenu: EMPTY");
			this.popupMenu.add(new JMenuItem("A popup menu item EMPTY"));
		}
		
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
	
		doIt();
	}
	
	private void doIt()
	{
		System.out.println("do it not empty! do it abstract");
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
}
