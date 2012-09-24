package basics;

import interfaces.ANTSIController;

import java.util.Arrays;

public class ANTSUtility 
{
	public static boolean implementsInterface(ANTSIController c, Object ANTSInterface)
	{
		return Arrays.asList(c.getClass().getInterfaces()).contains(ANTSInterface);
	}
	
	public static double roundScale2( double d )
	{
	    return Math.round( d * 10000000 ) / 10000000.;
	}
}
