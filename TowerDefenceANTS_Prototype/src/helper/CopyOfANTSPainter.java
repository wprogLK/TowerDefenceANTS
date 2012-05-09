package helper;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import controllers.basics.ANTSGameController;

import view.basics.ANTSWindow;

import enums.ANTSStateEnum;

public  class CopyOfANTSPainter  extends Thread
{
	private static ArrayList<ANTSIView> views = new ArrayList<ANTSIView>();
	private static Graphics2D g2d;
	private boolean windowReady;
	private ANTSWindow win;
	private boolean on;
	private ANTSGameLogicUpdater log;
	private boolean ready;
	
	public CopyOfANTSPainter(ANTSWindow win, ANTSGameLogicUpdater log)
	{
		this.windowReady = false;
		this.win = win;
		this.on = false;
		this.log = log;
		this.ready = true;
	}
	
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
	
	public void setWindowReady(boolean value)
	{
		this.windowReady = value;
	}
	
	public void on()
	{
		this.on = true;
	}
	
	public void off()
	{
		this.on = false;
	}
	
	@Override
	public void run() 
	{
		this.on();
		
		while(true)
		{
			//TODO
			//Repaint after it!
			while(this.log.isReady())
			{
				this.win.repaint();
				
				while(this.win.isReady())
				{
					try
					{
						Thread.sleep(1);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
		
			
//			while(!this.win.isReady())
//			{
//				try
//				{
//					Thread.sleep(1);
//				} 
//				catch (InterruptedException e) 
//				{
//					e.printStackTrace();
//				}
//			}

			try
			{
				for(int i = 0; i<views.size(); i++)
				{
					views.get(i).paint(g2d);
				}
				
				System.out.println("Paint");
			}
			catch(ConcurrentModificationException e)
			{
				
			}
			
			
			try
			{
				Thread.sleep(1);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
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
