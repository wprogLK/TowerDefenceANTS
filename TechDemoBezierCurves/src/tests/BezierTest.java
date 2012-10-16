package tests;



import java.util.ArrayList;

import main.BezierIPath;
import main.BezierPath;

import org.junit.Test;

public class BezierTest 
{
	@Test
	public void threeIntersectionPointsWithCubic()
	{
		double[] startPointRay ={150,110}; //{150,110};
		double[] endPointRay = {160,200};//{300,170};//{160,200};
		double[][] rayVector = {startPointRay,endPointRay};
		
		BezierIPath p = new BezierPath();
		p.addMoveTo(100, 100);
		p.addCurveTo(150, 150, 200, 250, 150, 70);
		
		p.calculateIntersectionPoint(rayVector);
		ArrayList<double[]> possibleIntersectionPoints = p.calculatePossibleIntersectionPoints(rayVector);
		
		System.out.println("size (three) was = " +possibleIntersectionPoints.size() );
		
		assert(possibleIntersectionPoints.size()==3);
	}
	
	@Test
	public void oneIntersectionPointsWithCubic()
	{
		double[] startPointRay ={150,110};
		double[] endPointRay = {300,170};
		double[][] rayVector = {startPointRay,endPointRay};
		
		BezierIPath p = new BezierPath();
		p.addMoveTo(100, 100);
		p.addCurveTo(150, 150, 200, 250, 300, 50);
		
		p.calculateIntersectionPoint(rayVector);
		ArrayList<double[]> possibleIntersectionPoints = p.calculatePossibleIntersectionPoints(rayVector);
		
		System.out.println("size (one) was = " +possibleIntersectionPoints.size() );
		
		assert(possibleIntersectionPoints.size()==1);
	}
}
