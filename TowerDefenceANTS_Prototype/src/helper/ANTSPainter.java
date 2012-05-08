package helper;

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
	

	@Override
	public void run() 
	{
		finishPainting = false;
//			System.out.println("b: inner painter start");
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0; i<views.size();i++)
			{
				ANTSIView view = views.get(i);
				view.paint(g2d);
			}
			
		
//			System.out.println("b: inner painter end");
		finishPainting = true;
		
		
	}

	public static void setPaintState(ANTSStateEnum state) {
		for(ANTSIView currentView:views)
		{
			currentView.setPaintState(state);
		}
		
	}

}
