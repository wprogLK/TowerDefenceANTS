package views;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.awt.Shape;

public abstract class ANTSAbstractView  implements ANTSIView
{
	protected boolean isMouseOver;
	protected Shape shape;
	protected static int fontSize = 12;
	
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
		this.isMouseOver = false;
	}
	
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		this.paint(g2d, 0);
	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	///////////
	//Getters//
	///////////
	
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
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	
	
}
