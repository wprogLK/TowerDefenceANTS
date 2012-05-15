package listeners.actionListeners.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

public class ANTSPrintStatsListener extends ANTSPaintActionListenerAbstract implements ActionListener {

	private static ANTSPaintActionListenerAbstract listener;
	
	public static final ANTSPaintActionListenerAbstract getInstance()
	{
		if(listener == null)
		{
			listener = new ANTSPrintStatsListener();
		}
		
		return listener;
	}
	
	@Override
	protected void setState() {
	
//		super.currentState = ANTSStateEnum.draw;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		this.window.stopAnimation();
	//	this.window.showStats();
		
	}

}
