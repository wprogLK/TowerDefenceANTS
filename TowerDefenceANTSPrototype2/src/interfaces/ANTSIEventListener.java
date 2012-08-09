package interfaces;

import java.util.EventListener;

public interface ANTSIEventListener extends EventListener
{
	public void doEvent(ANTSIEvent event);
}
