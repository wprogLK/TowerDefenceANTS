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
public class ANTSDriver implements ANTSIDriver
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
	
	public void update()
	{
		for(int i = 0; i<models.size(); i++)
		{
			ANTSIModel m = models.get(i);
			m.update();
		}
		
		window.repaint();
	}
}
