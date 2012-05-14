/**
 * 
 */
package basics;

import java.util.ArrayList;

import javax.swing.JPanel;

import interfaces.ANTSIDriver;
import interfaces.ANTSIModel;
import controllers.ANTSGameController;
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
		gameController = new ANTSGameController(this);
		
		window = new ANTSWindow();
		window.setVisible(true);
		
		models = new ArrayList<ANTSIModel>();
		
		createSimpleSourceLight(); //Only for testing
	}

	//Views
	
	private static void addView(JPanel v) 
	{
		window.add(v);
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
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController();
		addModel(c.getModel());
		addView(c.getView());
	}
}
