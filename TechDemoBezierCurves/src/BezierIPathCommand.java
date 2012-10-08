import java.awt.Graphics2D;


public interface BezierIPathCommand
{
	public double[] calculateIntersectionPoint(double[][] rayVector);
	public boolean contains(double[][] rayVector);
	public double[] getEndPoint();
	
	//Only for testing and debugging
	public void paintSingleSegment(Graphics2D g2d);
	
}
