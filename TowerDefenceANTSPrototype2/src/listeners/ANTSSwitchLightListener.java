package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.ANTSSimpleSourceLightModel;

import basics.ANTSDriver;

public class ANTSSwitchLightListener implements ActionListener
{
	private static ANTSSwitchLightListener me;
	private static ANTSDriver driver;
	
	private static ANTSSimpleSourceLightModel model;
	
	public ANTSSwitchLightListener() 
	{
		// TODO
	}
	
	public static void setDriver(ANTSDriver d)
	{
		driver = d;
	}
	
	public static ANTSSwitchLightListener getInstance()
	{
		if(me == null)
		{
			me = new ANTSSwitchLightListener(); 
		}
		
		return me;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(model != null)
		{
			model.switchLight();
		}
		else
		{
			System.out.println("no model found");
		}
	}
	
	public static void addLight(ANTSSimpleSourceLightModel m)
	{
		model = m;
	}

}
