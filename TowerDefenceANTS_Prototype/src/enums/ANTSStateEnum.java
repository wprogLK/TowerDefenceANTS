package enums;

public enum ANTSStateEnum {
	
	basic,draw, animate, empty;	//basic means default
	private static ANTSStateEnum current;
	public static void setCurrentState(ANTSStateEnum c)
	{
		current = c;
	}
	
	public static ANTSStateEnum getCurrentState()
	{
		return current;
	}
}
