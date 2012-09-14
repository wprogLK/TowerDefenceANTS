package views;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import basics.ANTSDevelopment.ANTSStream;

import controllers.ANTSDevelopmentController;

public abstract class ANTSAbstractView  implements ANTSIView
{
	protected boolean isMouseOver;
	protected Shape shape;
	protected static int fontSize = 12;
	protected ANTSDevelopmentController developmentController;
	
	private static ANTSAbstractView emptyView = new ANTSAbstractView(null) 
	{
		@Override
		public boolean isMouseListener() 
		{
			return false;
		}

		@Override
		protected void updateShape(float interpolation) 
		{
			
		}
	};
	
	public ANTSAbstractView(ANTSIModel m)
	{
		this.isMouseOver = false;
		
		if(m!=null)
		{
			this.developmentController = m.getDevelopmentController();
		}
	}
	
//	public ANTSAbstractView()
//	{
//		this.isMouseOver = false;
//	}
	
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		this.paint(g2d, 0);
	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		
	}
	
	public final void paintBounds(Graphics2D g2d)
	{
		if(this.developmentController.isShowBounds())
		{
			g2d.setColor(Color.BLUE);
			
			g2d.draw(this.shape.getBounds2D());
		}
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
	public Shape getShape()
	{
		this.updateShape(-1);
		
		return this.shape;
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
	
	/**
	 * 
	 * @param interpolation if the interpolation is -1, it's definitely not an interpolation
	 */
	protected void updateShape(float interpolation)
	{
		
	}
}
