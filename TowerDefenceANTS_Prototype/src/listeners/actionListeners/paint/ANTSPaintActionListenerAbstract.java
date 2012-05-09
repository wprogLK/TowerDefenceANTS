package listeners.actionListeners.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

import view.basics.ANTSWindow;

public abstract class ANTSPaintActionListenerAbstract implements ActionListener
{
	protected static ANTSWindow window; 
	protected static ANTSStateEnum currentState;

	public static void setWindow(ANTSWindow w)
	{
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.setState();

		this.window.repaint();
	}


	protected abstract void setState();
	
	public static ANTSStateEnum getState()
	{
		return currentState;
	}
}
