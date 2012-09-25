package basics;

import interfaces.ANTSIController;

import java.util.Arrays;

import com.sun.org.apache.xml.internal.security.utils.JavaUtils;

import sun.misc.JavaUtilJarAccess;

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
	
	public static double angleBetween0And359Degree(double angle)
	{
		double angleBetween = -1;
		
		if(angle>=0)
		{
			angleBetween = angle % 360;
		}
		else
		{
			angleBetween  = 360-(Math.abs(angle) % 360);
		}
	
		assert(angleBetween<360 && angleBetween>=0);
		
		return angleBetween; //angle is <360°;
	}
}
