package listeners.actionListeners.paint;

import interfaces.ANTSIDrawActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

import view.basics.ANTSWindow;

public abstract class ANTSPaintActionListenerAbstract implements ActionListener
{
	protected static ANTSWindow window; 
	protected static ANTSPaintActionListenerAbstract listener;

	
	public static void setWindow(ANTSWindow w)
	{
		window = w;
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) 
	{
		System.out.println("ANTSActionListener: SOURCE: " + e.getSource() + " COMMAND: " + e.getActionCommand());
		this.setState();
		this.window.repaint();
	}


	protected abstract void setState();
	
	
}
