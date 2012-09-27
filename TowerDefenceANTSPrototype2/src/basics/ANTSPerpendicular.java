package basics;

import basics.ANTSDevelopment.ANTSStream;
import enums.ANTSDirectionEnum;

public class ANTSPerpendicular 
{
	/**
	 * the start point is the intersection point
	 */
	private double[] start;
	
	/**
	 * the end point is inside the medium for example the center if the medium is a circle
	 */
	private double[] end;
	
	private double[] directionVectorOutToInSide;
	private double[] directionVectorInToOutSide;
	
	private double angleInToOutSide;
	private double angleOutToInSide;
	
	public ANTSPerpendicular(double[] start, double[] end) 
	{
		this.start = start;
		this.end = end;
		
		this.directionVectorOutToInSide = new double[2];
		this.directionVectorInToOutSide = new double[2];
		
		this.calculateDirectionVectors();
		
		this.calculateAngleInToOutSide();
		this.calculateAngleOutToInSide();
		
		this.classInvariant();
	}
	
	private void classInvariant()
	{
		assert(this.angleInToOutSide>=0 && this.angleInToOutSide<360);
		assert(this.angleOutToInSide>=0 && this.angleOutToInSide<360);
	}
	
	/**
	 * the calculated angle is the angle of the direction vector inside to outside!
	 * angle is between 0° and 359,99°;
	 */
	private void calculateAngleInToOutSide() 
	{
		//Rotationzentrum in den 0-Punkt transformieren und intersectionPoint transformieren
		double x = start[0]-end[0];		
		double y = start[1]-end[1];
		
		double antiClockWiseAngle = Math.toDegrees(Math.atan2(y, x));
		
		if(antiClockWiseAngle<=0)
		{
			this.angleInToOutSide = 360+antiClockWiseAngle;
		}
		else
		{
			this.angleInToOutSide = antiClockWiseAngle;
		}
		
		this.angleInToOutSide = ANTSUtility.angleBetween0And359Degree(this.angleInToOutSide); //angle is between 0° and 359,99°;
	}
	
	/**
	 * the calculated angle is the angle of the direction vector outside to inside!
	 * angle is between 0° and 359,99°;
	 */
	private void calculateAngleOutToInSide() 
	{
		//Rotationzentrum in den 0-Punkt transformieren und intersectionPoint transformieren
		double x = end[0]-start[0];		
		double y = end[1]-start[1];
		
		double antiClockWiseAngle = Math.toDegrees(Math.atan2(y, x));
		
		if(antiClockWiseAngle<=0)
		{
			this.angleOutToInSide = 360+antiClockWiseAngle;
		}
		else
		{
			this.angleOutToInSide = antiClockWiseAngle;
		}
		
		this.angleOutToInSide = ANTSUtility.angleBetween0And359Degree(this.angleOutToInSide); //angle is <360°;
	}

	private void calculateDirectionVectors() 
	{
		this.directionVectorOutToInSide[0] = end[0] - start[0];
		this.directionVectorOutToInSide[1] = end[1] - start[1];
		
		this.directionVectorInToOutSide[0] = start[0] - end[0];
		this.directionVectorInToOutSide[1] = start[1] - end[1];
	}
	
	///////////
	//GETTERS//
	///////////
	
	/**
	 * return: get the standard direction vector which points into the medium
	 */
	public double[] getDirectionVectorOutToInSide()
	{
		return this.directionVectorOutToInSide;
	}
	
	/**
	 * return: get the standard direction vector which points outside the medium
	 */
	public double[] getDirectionVectorInToOutSide()
	{
		return this.directionVectorInToOutSide;
	}
	
	/**
	 * the calculated angle is the angle of the direction vector inside to outside!
	 * 
	 * the angle is between 0° and <360°
	 */
	public double getAngleInToOutSide()
	{
		this.classInvariant();
		return this.angleInToOutSide;
	}
	
	/**
	 * the calculated angle is the angle of the direction vector outside to inside!
	 * 
	 * the angle is between 0° and <360°
	 */
	public double getAngleOutToInSide()
	{
		this.classInvariant();
		return this.angleOutToInSide;
	}
	
	public double getAngle(ANTSDirectionEnum direction)
	{
		switch(direction)
		{
			case IN:
			{
				return this.getAngleOutToInSide();
			}
			case OUT:
			{
				return this.getAngleInToOutSide();
			}
			default:
			{
				ANTSStream.printErr("unknown direction in getAngle() in " + this.getClass());
				return -1;
			}
		}
	}
	
	public double[] getDirectionVector(ANTSDirectionEnum direction)
	{
		switch(direction)
		{
			case IN:
			{
				return this.getDirectionVectorOutToInSide();
			}
			case OUT:
			{
				return this.getDirectionVectorInToOutSide();
			}
			default:
			{
				ANTSStream.printErr("unknown direction in getDirectionVector() in " + this.getClass());
				double[] d = {-1,-1};
				return d;
			}
		}
	}
}
