package helper;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import enums.ANTSStateEnum;

public  class ANTSPainter  extends Thread
{
	private static ArrayList<ANTSIView> views = new ArrayList<ANTSIView>();
	private static Graphics2D g2d;
	
	public static void addView(ANTSIView view)
	{
		if(!views.contains(view))
		{
			views.add(view);
		}
	}
	
	public static void removeView(ANTSIView view)
	{
		if(views.contains(view))
		{
			views.remove(view);
		}
	}
	
	public static void setGraphics(Graphics2D g2)
	{
		g2d = g2;
	}
	
	@Override
	public void run() 
	{
		try
		{
			for(int i = 0; i<views.size(); i++)
			{
				views.get(i).paint(g2d);
			}
		}
		catch(ConcurrentModificationException e)
		{
			
		}
	}

	public static void setPaintState(ANTSStateEnum state)
	{
		for(ANTSIView currentView:views)
		{
			currentView.setPaintState(state);
		}
	}
}
