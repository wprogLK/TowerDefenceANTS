/**
 * 
 */
package basics;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import listeners.ANTSUpdateListener;

import interfaces.ANTSIDriver;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import controllers.ANTSGameController;
import controllers.ANTSSimpleRayLightController;
import controllers.ANTSSimpleSourceLightController;

/**
 * @author Lukas
 *
 */
public class ANTSDriver extends Thread implements ANTSIDriver 
{
	private static ANTSWindow window;
	private static ANTSGameController gameController;
	private static ArrayList<ANTSIModel> models;
	
	public ANTSDriver()
	{	
		window = new ANTSWindow();
		window.setVisible(true);
		
		models = new ArrayList<ANTSIModel>();
		
		this.initAllListeners();
		
		this.createGame();
		createSimpleSourceLight(); //Only for testing
		createSimpleSourceLight2();
	}
	
	private void initAllListeners() 
	{
		ANTSUpdateListener.setDriver(this);
	}

	//Views
	
	private static void addView(ANTSIView v)
	{
		window.addView(v);
	}

	//Model
	
	private static void addModel(ANTSIModel m) 
	{
		if(!models.contains(m))
		{
			models.add(m);
		}
	}
	
	//Create new objects
	
	public static void createSimpleSourceLight()
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController(200,200,60,Color.yellow);
		addModel(c.getModel());
		addView(c.getView());
	}
	
	public static void createSimpleSourceLight2()
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController(20,20,20,Color.blue);
		addModel(c.getModel());
		addView(c.getView());
	}
	
	public static void createSimpleRayLight(AffineTransform matrix, double velocity, double angle, Color color)
	{
		ANTSSimpleRayLightController c = new ANTSSimpleRayLightController(matrix,10,angle,color);
		addModel(c.getModel());
		addView(c.getView());
	}
	
	private void createGame()
	{
		gameController = new ANTSGameController();
		addModel(gameController.getModel());
		addView(gameController.getView());
	}
//	
//	public void start()
//	{
//		
//	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try {
				ANTSUpdaterGameLogic updaterGameLogic = new ANTSUpdaterGameLogic(models); 
				ANTSUpdaterGraphic updaterGraphic = new ANTSUpdaterGraphic(window);
			
				updaterGameLogic.start();
			
				updaterGameLogic.join();
				updaterGraphic.start();
				updaterGraphic.join();
				
			}
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	//////////////////
	//INNER CLASSES://
	//////////////////
	
	private class ANTSUpdaterGameLogic extends Thread
	{
		private ArrayList<ANTSIModel> models;
		
		public ANTSUpdaterGameLogic(ArrayList<ANTSIModel> m)
		{
			this.models = m;
		}
		
		@Override
		public void run() 
		{
			//TODO other way
//			while(true)
//			{
				this.updateAllModels();
				
				try 
				{
					Thread.sleep(400); //100
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
//			}
		}
		
		private void updateAllModels()
		{
			for(int i = 0; i<this.models.size();i++)
			{
				ANTSIModel m = this.models.get(i);
				m.update();
			}
		}
	}
	
	private class ANTSUpdaterGraphic extends Thread
	{
		private ANTSWindow window;
		
		public ANTSUpdaterGraphic(ANTSWindow w)
		{
			this.window = w;
		}
		
		@Override
		public void run() 
		{
			//TODO other way
//			while(true)
//			{
				this.window.repaint();

				try 
				{
					Thread.sleep(115);	//150
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
//			}
		}
		
	}
}
