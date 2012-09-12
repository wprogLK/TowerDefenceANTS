package basics;

import interfaces.ANTSIController;

import java.util.Arrays;

public class ANTSUtility 
{
	public static boolean implementsInterface(ANTSIController c, Object ANTSInterface)
	{
		return Arrays.asList(c.getClass().getInterfaces()).contains(ANTSInterface);
	}
}
