package listeners.actionListeners.paint;

import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

public class ANTSOnlyDrawActionListener extends ANTSPaintActionListenerAbstract implements ActionListener {


	public static final ANTSPaintActionListenerAbstract getInstance()
	{
		if(listener == null)
		{
			listener = new ANTSOnlyDrawActionListener();
		}
		
		return listener;
	}
	
	@Override
	protected void setState() {
		System.out.println("SET STATE...");
		
		this.window.setState(ANTSStateEnum.draw);
		
	}

}
