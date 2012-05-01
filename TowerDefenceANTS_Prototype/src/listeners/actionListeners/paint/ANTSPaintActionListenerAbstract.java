package listeners.actionListeners.paint;

import interfaces.ANTSIDrawActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

import view.basics.ANTSWindow;

public abstract class ANTSPaintActionListenerAbstract implements ActionListener
{
	protected static ANTSWindow window; 
	//protected static ANTSPaintActionListenerAbstract listener;
	protected static ANTSStateEnum currentState;

	
	public static void setWindow(ANTSWindow w)
	{
		window = w;
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) 
	{
		this.setState();
		//currentState = ANTSStateEnum.basic;

		this.window.repaint();
	}


	protected abstract void setState();
	
	public static ANTSStateEnum getState()
	{
		return currentState;
	}
	
//	protected abstract void doIt();
	
	
}
