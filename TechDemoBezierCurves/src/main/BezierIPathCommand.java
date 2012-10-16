package main;
import java.awt.Graphics2D;
import java.util.ArrayList;


public interface BezierIPathCommand
{
	public double[] calculateIntersectionPoint(double[][] rayVector);
	public ArrayList<double[]> calculatePossibleIntersectionPoints(double[][] rayVector);
	public boolean contains(double[][] rayVector);
	public double[] getEndPoint();
	
	//Only for testing and debugging
	public void paintSingleSegment(Graphics2D g2d);
	
}
