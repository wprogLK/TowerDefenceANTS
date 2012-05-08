package helper;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public  class ANTSPainter  extends Thread
{
	private static ArrayList<ANTSIView> views = new ArrayList<ANTSIView>();
	private static boolean finishPainting;
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
	
//	public static void paint(Graphics2D g2d)
//	{
//		finishPainting = false;
//		try
//		{
//			for(ANTSIView currentView:views)
//			{
//					currentView.paint(g2d);
//			}
//		finishPainting = true;
//		}
//		catch(ConcurrentModificationException e)
//		{
//			
//		}
//	}
	
	public static void isFinishPainting()
	{
		System.out.println("IS FINISH: " + finishPainting);
	}
	

	@Override
	public void run() 
	{
		finishPainting = false;
		try
		{
			
			for(ANTSIView currentView:views)
			{
					currentView.paint(g2d);
//					System.out.println("CURRENT VIEW: " +currentView.toString() + " is finish: " + currentView.isFinish());
			}
		finishPainting = true;
		}
		catch(ConcurrentModificationException e)
		{
			
		}
		
	}

}
