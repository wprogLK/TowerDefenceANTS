
public interface BezierIPathCommand
{
	public double[] calculateIntersectionPoint(double[] directionVector);
	public boolean contains(double[] directionVector);
	public double[] getEndPoint();
	public double[] getStartPoint();
	
}
