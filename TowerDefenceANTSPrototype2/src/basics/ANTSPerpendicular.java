package basics;

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
	}
	
	/**
	 * the calculated angle is the angle of the direction vector inside to outside!
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
	}
	
	/**
	 * the calculated angle is the angle of the direction vector outside to inside!
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
	 */
	public double getAngleInToOutSide()
	{
		return this.angleInToOutSide;
	}
	
	/**
	 * the calculated angle is the angle of the direction vector outside to inside!
	 */
	public double getAngleOutToInSide()
	{
		return this.angleOutToInSide;
	}
}
