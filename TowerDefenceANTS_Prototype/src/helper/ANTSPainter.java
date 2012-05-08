package helper;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;

import view.basics.ANTSWindow;

import enums.ANTSStateEnum;

public  class ANTSPainter  extends Thread
{
	private static ArrayList<ANTSIView> views = new ArrayList<ANTSIView>();
	private static boolean finishPainting;
	private static Graphics2D g2d;
	private Thread painter;
	private int timeBetweenTwoTicks = 3;
	private ANTSWindow window;
	
	public ANTSPainter(ANTSWindow win)
	{
		this.window = win;
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
				
				clear();
				
//				
				g2d = (Graphics2D) this.window.getCurrentMainPanel().getGraphics();
				paintAll();
				System.out.println("B: FISNISH");
				
				Thread.sleep(timeBetweenTwoTicks);
				this.window.repaint();
				Thread.sleep(1);
			} 
				
			}
		catch (InterruptedException e) 
		{
			
		}
	}
	
	private synchronized void paintAll()
	{
		for(int i=0; i<views.size(); i++)
		{
			ANTSIView view = views.get(i);
			view.paint(g2d);
		}
		System.out.println("A: PAINTED");
	}

	public static void setPaintState(ANTSStateEnum state) 
	{
		for(ANTSIView currentView:views)
		{
			currentView.setPaintState(state);
		}
	}
	
	private void clear()
	{
//		g2d=(Graphics2D) this.window.getGraphics();
		g2d = (Graphics2D) this.window.getCurrentMainPanel().getGraphics();
		
//		this.window.repaint();
	}

}
