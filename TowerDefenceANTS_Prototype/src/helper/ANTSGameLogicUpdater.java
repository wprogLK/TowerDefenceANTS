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
			System.out.println("a: inner gameLogicUpdater start");
			for(ANTSIModel currentModel:models)
			{
				currentModel.update();
//				System.out.println("Model VIEW: " +currentModel.toString() );//+ " is finish: " + currentModel.isFinish());
			}
			System.out.println("a: inner gameLogicUpdater end");
		}
		catch(ConcurrentModificationException e)
		{
			System.out.println("Error in gameLogicUpdater!");
		}
		
	}

}
