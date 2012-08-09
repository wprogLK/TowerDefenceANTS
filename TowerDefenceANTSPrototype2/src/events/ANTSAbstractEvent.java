package events;

import interfaces.ANTSIEvent;

import java.util.EventObject;

public class ANTSAbstractEvent extends EventObject implements ANTSIEvent{
	public ANTSAbstractEvent(Object source)
	{
		super(source);
	}
}
