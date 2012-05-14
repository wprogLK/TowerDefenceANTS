package helper;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import view.basics.ANTSWindow;

public  class ANTSGameLogicUpdater extends Thread
{
	private static ArrayList<ANTSIModel> models = new ArrayList<ANTSIModel>();
	private boolean windowReady;
	private ANTSWindow win;
	private boolean on;
	private boolean ready;
	private ANTSPainter painter;
	private boolean running;
	
	public ANTSGameLogicUpdater(ANTSWindow win)
	{
		this.windowReady = false;
		this.win = win;
		this.on = false;
		this.ready = true;
		this.running = false;
	}
	
	public static void addModel(ANTSIModel model)
	{
		if(!models.contains(models))
		{
			models.add(model);
		}
	}
	
	public static void removeModel(ANTSIModel model)
	{
		if(models.contains(model))
		{
			models.remove(model);
		}
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
	
	public void setPainter(ANTSPainter p)
	{
		this.painter = p;
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
					//System.out.println("LOGIC: " + this.win.isReady());
					
					while((!this.win.isReady() || !this.painter.isReady()) && this.on)//this.windowReady) OLD
					{
							Thread.sleep(1);
					}
					
					try
					{
						this.ready = false;
						for(int i = 0; i<models.size(); i++)
						{
							models.get(i).update();
						}
						//System.out.println("Update done");
						this.ready = true;
					}
					catch(ConcurrentModificationException e)
					{
						
					}
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
	
	public boolean isReady()
	{
		return this.ready;
	}

}
