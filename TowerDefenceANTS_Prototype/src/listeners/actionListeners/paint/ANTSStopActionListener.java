package listeners.actionListeners.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

public class ANTSStopActionListener extends ANTSPaintActionListenerAbstract implements ActionListener {

	private static ANTSPaintActionListenerAbstract listener;
	
	public static final ANTSPaintActionListenerAbstract getInstance()
	{
		if(listener == null)
		{
			listener = new ANTSStopActionListener();
		}
		
		return listener;
	}
	
	@Override
	protected void setState() 
	{
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
	
		window.stopAnimation();
	}

}
