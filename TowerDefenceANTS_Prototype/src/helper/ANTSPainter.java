package helper;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import enums.ANTSStateEnum;

public  class ANTSPainter  extends Thread
{
	private static ArrayList<ANTSIView> views = new ArrayList<ANTSIView>();
	private static boolean finishPainting;
	private static Graphics2D g2d;
	private Thread painter;
	private int timeBetweenTwoTicks = 1000;
	
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
	
	public static void isFinishPainting()
	{
		System.out.println("IS FINISH: " + finishPainting);
	}
	
	public  void execute()
	{
		this.painter = new Thread(this);
		this.painter.start();
	}
	
	public void exit()
	{
		this.painter.interrupt();
	}

	@Override
	public void run() 
	{
		Thread t = Thread.currentThread();
		
		try {
			while(t== this.painter)
			{
				paintAll();
				
					Thread.sleep(timeBetweenTwoTicks);
				} 
				
			}
		catch (InterruptedException e) 
		{
			
		}
	}
	
	private void paintAll()
	{
		for(int i=0; i<views.size(); i++)
		{
			ANTSIView view = views.get(i);
			view.paint(g2d);
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
