package main;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;


public interface BezierIPath extends Shape
{
	/**
	 * 
	 * @param rayVector: rayVector[0][0,1] startPoint , rayVector[1][0,1] endPoint
	 * @return
	 */
	public double[] calculateIntersectionPoint(double[][] rayVector);
	
	public ArrayList<double[]> calculatePossibleIntersectionPoints(double[][] rayVector);
	public boolean contains(double[] directionVector);
	
	void addCurveTo(double x1, double y1, double x2, double y2, double x3, double y3);
	void addQuadTo(double x1, double y1, double x2, double y2);
	void addClose();
	void addLineTo(double x, double y);
	void addMoveTo(double x, double y);
	//Only for debugging and testing
	public void drawSingleSegments(Graphics2D g2d);
}
