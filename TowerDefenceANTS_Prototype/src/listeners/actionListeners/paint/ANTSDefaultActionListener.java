package listeners.actionListeners.paint;

import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

public class ANTSDefaultActionListener extends ANTSPaintActionListenerAbstract implements ActionListener {


	public static final ANTSPaintActionListenerAbstract getInstance()
	{
		if(listener == null)
		{
			listener = new ANTSDefaultActionListener();
		}
		
		return listener;
	}
	
	@Override
	protected void setState() 
	{
		ANTSStateEnum.setCurrentState(ANTSStateEnum.basic);
	}

}
