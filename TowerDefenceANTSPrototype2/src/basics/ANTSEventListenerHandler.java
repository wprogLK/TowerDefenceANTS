package basics;

import interfaces.ANTSIEvent;
import interfaces.ANTSIEventListener;

import javax.swing.event.EventListenerList;

import basics.ANTSDevelopment.ANTSStream;

public class ANTSEventListenerHandler {
	
	private EventListenerList listenerList;
	
	public ANTSEventListenerHandler()
	{
		this.listenerList = new EventListenerList();
	}
	
	////////////////////
	//Listener methods//
	////////////////////
	
	public void addEventListener(ANTSIEventListener listener)
	{
		ANTSIEventListener[] es = this.listenerList.getListeners(listener.getClass());
		
		ANTSStream.printDebug("listener " +es.length );
		
		this.listenerList.add(ANTSIEventListener.class, listener);
	}
	
	public void removeEventListener(ANTSIEventListener listener)
	{
		this.listenerList.remove(ANTSIEventListener.class, listener);
	}
	
	public void fireEvent(ANTSIEvent event)
	{
		Object[] listeners = this.listenerList.getListenerList();
		for(int i=0; i<listeners.length;i+=2)
		{
			if(listeners[i] == ANTSIEventListener.class)
			{
				((ANTSIEventListener) listeners[i+1]).doEvent(event);
			}
		}
	}
}
