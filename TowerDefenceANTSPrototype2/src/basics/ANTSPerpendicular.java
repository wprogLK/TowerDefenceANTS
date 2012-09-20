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
	
	public ANTSPerpendicular(double[] start, double[] end) 
	{
		this.start = start;
		this.end = end;
		
		this.directionVectorOutToInSide = new double[2];
		this.directionVectorInToOutSide = new double[2];
		
		this.calculateDirectionVectors();
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
}
