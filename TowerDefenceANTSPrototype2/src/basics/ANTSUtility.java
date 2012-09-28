package basics;

import interfaces.ANTSIController;

import java.util.Arrays;

import basics.ANTSDevelopment.ANTSStream;

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
		
		return angleBetween;
	}
	
	/**
	 * 
	 * @param directionVectorA
	 * @param directionVectorB
	 * @return always an angle between 180° & 0°
	 */
	public static double computeAngleBetweenTwoRealDirectionVectors(double[] directionVectorA, double[] directionVectorB)
	{
		double dotProduct = directionVectorB[0]*directionVectorA[0] + directionVectorB[1]*directionVectorA[1];
		double lengthVectorB = Math.sqrt(pow(directionVectorB[0]) + pow(directionVectorB[1]) );
		double lengthVectorA = Math.sqrt(pow(directionVectorA[0]) +pow(directionVectorA[1]) );
		
		double cosAlpha = dotProduct/(lengthVectorB*lengthVectorA);
		
		double angle = Math.toDegrees(Math.acos(cosAlpha));				
		
		if(angle>180)	//TODO Test this
		{
			angle = 360-angle;
			ANTSStream.print("------------------------------reduce angle "+(angle+360) + " -> "+ angle +" -----------------------------------------");
		}

		assert(angle<=180 && angle>=0);
		
		return angle;
	}
	
	public static double pow(double v)
	{
		return Math.pow(v, 2);
	}
	
	public static double computeAngleOfOneVector(double[] directionVector)
	{
		double[] xAxisVector = {500,0};
		
		double angle = computeAngleBetweenTwoRealDirectionVectors(directionVector, xAxisVector);
		
//with java coordinate system		
//			|
//			|
//		C	|	D
//			|
//   --------------------> x
//			|
//			|
//		B	|	A
//			|
//			v
//			y
		
		if(directionVector[0]>=0 && directionVector[1]>=0)
		{
//			ANTSStream.print("quadrant A");
		}
		else if(directionVector[0]>=0 && directionVector[1]<0)
		{
//			ANTSStream.print("quadrant D");
			angle = 360-angle;
		}
		else if(directionVector[0]<0 && directionVector[1]<0)
		{
//			ANTSStream.print("quadrant C");
			angle = 360-angle;
		}
		else if(directionVector[0]<0 && directionVector[1]>=0)
		{
//			ANTSStream.print("quadrant B");
		}
		else
		{
			ANTSStream.print("ERROR: unknown quadrant");
		}
		
//		ANTSStream.print("DIRECTIONVector: " + directionVector[0] + " | " + directionVector[1]);
		
		
		return angleBetween0And359Degree(angle);
		
	}
	
	public static double[] computeDirectionVector(double[] startPoint, double[] endPoint)
	{
		double[] directionVector ={endPoint[0]-startPoint[0],endPoint[1]-startPoint[1]};
		
		return directionVector;
	}
	
	public static double angleBetweenToAngles(double angleA, double angleB)	//TODO Test this!
	{
		double max = Math.max(angleA, angleB);
		double min = Math.min(angleA, angleB);
		
		double diff = -1;
		
		if((max-min)>180 && min<180)
		{
			diff = (360-max)+min;
		}
		else
		{
			diff = max - min;
		}
		
		assert(diff>=0 && diff<=180);
		
		return diff;
	}
}
