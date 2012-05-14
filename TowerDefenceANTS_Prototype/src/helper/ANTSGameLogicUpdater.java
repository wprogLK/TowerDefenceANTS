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
	
	public ANTSGameLogicUpdater(ANTSWindow win)
	{
		this.windowReady = false;
		this.win = win;
		this.on = false;
		this.ready = true;
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
	
	@Override
	public void run() 
	{
		this.on();
		
		try
		{
			while(this.on)
			{
				System.out.println("LOGIC: " + this.win.isReady());
				
				while((!this.win.isReady() || !this.painter.isReady()) && this.on)//this.windowReady) OLD
				{
//					try
//					{
						Thread.sleep(1);
//					} 
//					catch (InterruptedException e) 
//					{
//						e.printStackTrace();
//						this.off();
//					}
				}
				
				try
				{
					this.ready = false;
					for(int i = 0; i<models.size(); i++)
					{
						models.get(i).update();
					}
					System.out.println("Update done");
					this.ready = true;
				}
				catch(ConcurrentModificationException e)
				{
					
				}
				
//				try
//				{
					Thread.sleep(1);
//				} 
//				catch (InterruptedException e) 
//				{
//					e.printStackTrace();
//					this.off();
//				}
			}
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			this.off();
		}

	
		
	}
	
	public boolean isReady()
	{
		return this.ready;
	}

}
