package helper;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public  class ANTSGameLogicUpdater extends Thread
{
	private static ArrayList<ANTSIModel> models = new ArrayList<ANTSIModel>();
	
	public static void addModel(ANTSIModel model)
	{
//		System.out.println("ADD MODEL" + model.toString());
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
	

	@Override
	public void run() 
	{
		try
		{
			Thread.sleep(40);
			
//			System.out.println("a: inner gameLogicUpdater start");
			for(int i=0; i<models.size(); i++)
			{
				ANTSIModel model = models.get(i);
				model.update();
			}
//			System.out.println("a: inner gameLogicUpdater end");
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
