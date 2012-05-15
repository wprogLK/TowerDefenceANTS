package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import basics.ANTSDriver;

public class ANTSUpdateListener implements ActionListener
{
	private static ANTSUpdateListener me;
	private static ANTSDriver driver;
	
	public ANTSUpdateListener() 
	{
		// TODO
	}
	
	public static void setDriver(ANTSDriver d)
	{
		driver = d;
	}
	
	public static ANTSUpdateListener getInstance()
	{
		if(me == null)
		{
			me = new ANTSUpdateListener(); 
		}
		
		return me;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
//		driver.update();
	}

}
