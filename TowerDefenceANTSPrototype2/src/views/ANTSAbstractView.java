package views;

import interfaces.ANTSIView;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public abstract class ANTSAbstractView  implements ANTSIView
{
	protected boolean isMouseOver;
	protected Shape shape;
	
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

	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
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
		if(this.shape!=null)
		{
			if(this.shape.contains(x,y))
			{
				this.isMouseOver = true;
				return true;
			}
			else
			{
				this.isMouseOver =false;
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean isMouseOver()
	{
		return this.isMouseOver;
	}
}
