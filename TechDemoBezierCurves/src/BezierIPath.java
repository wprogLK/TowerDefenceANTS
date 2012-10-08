import java.awt.Shape;


public interface BezierIPath extends Shape
{
	public double[] calculateIntersectionPoint(double[] directionVector);
	public boolean contains(double[] directionVector);
	
	void addCurveTo(double x1, double y1, double x2, double y2, double x3, double y3);
	void addQuadTo(double x1, double y1, double x2, double y2);
	void addClose();
	void addLineTo(double x, double y);
	void addMoveTo(double x, double y);
}
