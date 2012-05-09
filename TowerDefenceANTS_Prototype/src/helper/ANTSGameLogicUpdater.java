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
			for(ANTSIModel currentModel:models)
			{
				currentModel.update();
			}
		}
		catch(ConcurrentModificationException e)
		{
			
		}
		
	}

}
