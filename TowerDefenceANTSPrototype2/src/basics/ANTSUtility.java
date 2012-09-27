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
	
	public static double computeAngleBetweenTwoDirectionVectors(double[] directionVectorA, double[] directionVectorB)
	{
		double dotProduct = directionVectorB[0]*directionVectorA[0] + directionVectorB[1]*directionVectorA[1];
		double lengthVectorB = Math.sqrt(pow(directionVectorB[0]) + pow(directionVectorB[1]) );
		double lengthVectorA = Math.sqrt(pow(directionVectorA[0]) +pow(directionVectorA[1]) );
		
		double cosAlpha = dotProduct/(lengthVectorB*lengthVectorA);
		double angle = Math.toDegrees(Math.acos(cosAlpha));				
		
		return angle;
	}
	
	public static double pow(double v)
	{
		return Math.pow(v, 2);
	}
	
	public static double computeAngleOfOneVector(double[] directionVector)
	{
		double[] xAxisVector = {500,0};
		
		return computeAngleBetweenTwoDirectionVectors(directionVector, xAxisVector);
	}
}
