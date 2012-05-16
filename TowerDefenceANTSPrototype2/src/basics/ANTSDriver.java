/**
 * 
 */
package basics;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

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
	
	private final int TICKS_PER_SECOND = 25;
	private final int SKIP_TICKS = 1000/TICKS_PER_SECOND;
	private final int MAX_FRAMESKIP = 5;
	
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
	
	@Override
	public void run()
	{
		long nextGameTick = System.currentTimeMillis();
		int loops;
		float interpolation;
		
		boolean gameIsRunning = true;
		
		while(gameIsRunning)
		{
			loops = 0;
			while(System.currentTimeMillis() >nextGameTick && loops<MAX_FRAMESKIP)
			{
				this.updateModels();
				
				nextGameTick +=SKIP_TICKS;
				loops++;
			}
			
			interpolation =(System.currentTimeMillis() + SKIP_TICKS - nextGameTick) /(SKIP_TICKS); //TODO Float
			this.paint(interpolation);
		}
	}
	
	private void updateModels()
	{
		for(int i = 0; i<models.size();i++)
		{
			ANTSIModel m = models.get(i);
			m.update();
		}
	}
	
	private void paint(float interpolation)
	{
		window.repaint();	//TODO implement the interpolation
	}
}
