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
	private ANTSWindow window;
	private ANTSGameController gameController;
	private ArrayList<ANTSIModel> models;
	
	public ANTSDriver()
	{
		this.gameController = new ANTSGameController(this);
		
		this.window = new ANTSWindow();
		this.window.setVisible(true);
		
		this.models = new ArrayList<ANTSIModel>();
		
		this.createSimpleSourceLight(); //Only for testing
	}

	//Views
	
	@Override
	public void addView(JPanel v) 
	{
		this.window.add(v);
	}

	//Model
	
	@Override
	public void addModel(ANTSIModel m) 
	{
		if(!this.models.contains(m))
		{
			this.models.add(m);
		}
	}
	
	//Create new objects
	
	public void createSimpleSourceLight()
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController();
		this.addModel(c.getModel());
		this.addView(c.getView());
	}
}
