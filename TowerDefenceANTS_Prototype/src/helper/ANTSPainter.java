package helper;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import controllers.basics.ANTSGameController;

import view.basics.ANTSWindow;

import enums.ANTSStateEnum;

public  class ANTSPainter  extends Thread
{
	private static ArrayList<ANTSIView> views = new ArrayList<ANTSIView>();
	private static Graphics2D g2d;
	private boolean windowReady;
	private ANTSWindow win;
	private boolean on;
	private ANTSGameLogicUpdater log;
	private boolean ready;
	private boolean running;
	
	public ANTSPainter(ANTSWindow win, ANTSGameLogicUpdater log)
	{
		this.windowReady = false;
		this.win = win;
		this.on = false;
		this.log = log;
		this.ready = true;
		this.running = false;
		this.interrupt();
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
	
	public boolean getRunning()
	{
		return this.running;
	}
	
	@Override
	public void run() 
	{
		this.on();
		this.running = true;
		while(true)
		{
			try
			{
				while(this.on)
				{
					while(!this.log.isReady() && this.on)
					{
							Thread.sleep(1);
					}
					
					while(!this.win.isReady() && this.on)
					{
							Thread.sleep(1);
					}
					this.ready = false;
					this.win.repaint();
		
					try
					{
						for(int i = 0; i<views.size(); i++)
						{
							views.get(i).paint(g2d);
						}
						
						//System.out.println("Paint");
					}
					catch(ConcurrentModificationException e)
					{
						
					}
					this.ready = true;
					
					Thread.sleep(1);
				}
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
				this.off();
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
	
	public boolean isReady()
	{
		return this.ready;
	}
}
