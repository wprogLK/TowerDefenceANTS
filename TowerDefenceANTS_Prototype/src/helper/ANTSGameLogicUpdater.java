package helper;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public  class ANTSGameLogicUpdater extends Thread
{
	private static ArrayList<ANTSIModel> models = new ArrayList<ANTSIModel>();
	private static long timeBetweenTwoTicks = 100; //in ms
	private  Thread gameLogic;
	
	public ANTSGameLogicUpdater()
	{
		
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
	
	public  void execute()
	{
		this.gameLogic = new Thread(this);
		this.gameLogic.start();
	}
	
	public void exit()
	{
		this.gameLogic.interrupt();
	}

	@Override
	public void run() 
	{
		Thread t = Thread.currentThread();
		
		try 
		{
			while(t== this.gameLogic)
			{
				updateAll();
				
				Thread.sleep(timeBetweenTwoTicks);
			} 
		}
		catch (InterruptedException e) 
		{
			
		}
		
		
//		try
//		{
//			for(ANTSIModel currentModel:models)
//			{
//				currentModel.update();
////				System.out.println("Model VIEW: " +currentModel.toString() );//+ " is finish: " + currentModel.isFinish());
//			}
//		}
//		catch(ConcurrentModificationException e)
//		{
//			
//		}
		
	}
	
	private  void updateAll()
	{
		for(int i=0; i<models.size(); i++)
		{
			ANTSIModel model = models.get(i);
			model.update();
		}
		
		//System.out.println("Update done");
	}

}
