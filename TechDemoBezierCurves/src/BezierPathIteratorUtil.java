import java.awt.font.NumericShaper;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BezierPathIteratorUtil 
{

	public BezierPathIteratorUtil() 
	{
		
	}
	
	public static void foo(PathIterator iterator)
	{
		double[] prevPoint = {Double.NaN,Double.NaN}; //The endpoint of the previous segment
		double[] startPoint = {Double.NaN,Double.NaN}; //The startpoint of the first segment
		
		double[] currentPoints = {Double.NaN,Double.NaN,Double.NaN,Double.NaN, Double.NaN,Double.NaN};
		
		boolean start = true;
		
		while(!iterator.isDone())
		{
			int type = iterator.currentSegment(currentPoints);
			
			if(start)
			{
				startPoint[0] = currentPoints[0];
				startPoint[1] = currentPoints[1];
				
				prevPoint[0] = currentPoints[0];
				prevPoint[1] = currentPoints[1];
				
				start = false;
			}

			analyzeCurrentSegment(type,currentPoints,prevPoint);
			
			resetCurrentPoints(currentPoints);
			
			iterator.next();
		}
		
		System.out.println("The startPoint of the path was [" + startPoint[0] + " | " + startPoint[1] + " ] ");
		System.out.println("Path complete analyzed!");
	}
	
	private static void resetCurrentPoints(double[] currentPoints)
	{
		for(double point : currentPoints)
		{
			point = Double.NaN;
		}
	}

	/**
	 * 
	 * @param type
	 * @param currentPoints
	 * @param prevPoint: the endPoint of the previous segment
	 */
	private static void analyzeCurrentSegment(int type, double[] currentPoints, double[] prevPoint) 
	{
		double[][] currentValidPoints = getCurrentValidPoints(currentPoints,prevPoint,type);
		
		printAllPoints(currentValidPoints, "current Valid points: ");

		switch(type)
		{
			case(PathIterator.SEG_MOVETO):
			{
				System.out.println("MoveTo (T = " + type +")");

				//no analysis necessary because it's only a gap, so no intersection point!
				
				break;
			}
			case(PathIterator.SEG_LINETO):
			{
				System.out.println("LineTo (T = " + type +")");
				analyzeLine(currentValidPoints);
				
				break;
			}
			case(PathIterator.SEG_QUADTO):
			{
				System.out.println("QuadTo (T = " + type +")");
				analyzeQuadratic(currentValidPoints);
				
				break;
			}
			case(PathIterator.SEG_CUBICTO):
			{
				System.out.println("CubicTo (T = " + type +")");
				
				break;
			}
			case(PathIterator.SEG_CLOSE):
			{
				System.out.println("Close (T = " + type +")");
				
				break;
			}
			default:
			{
				System.out.println("Error: Unknown segment type! The type value is " + type);
				System.exit(1);	//TODO
			}
		}
		
		int length = currentValidPoints.length;
		
		prevPoint[0] = currentValidPoints[length-1][0];
		prevPoint[1] = currentValidPoints[length-1][1];
		
		System.out.println("the new prev point is [ " + prevPoint[0] + " | " + prevPoint[1] + " ]");
	}

	private static double[][] getCurrentValidPoints(double[] currentPoints, double[] prevPoint, int type) 
	{
		int numberOfCurrentPoints;
		
		double[] headPoint = new double[2];	//The prevPoint
		double[] tailPoint = new double[2];	//The startPointOfPath (only necessary if type is SEG_CLOSE)
		
		boolean validHeadPoint = false;
		boolean validTailPoint = false;
		
		double[][] validPoints;
		int numberOfValidPoints = 0;
	
		if(type == PathIterator.SEG_MOVETO)
		{
			numberOfCurrentPoints = 1; 	//TODO: WARNING: prevPoint and point in currentPoints are the same! only one is needed!
			//TODO is it always the start point? What happen if moveTo, then for example lineTo and then again moveTo and then lineTo and at last closeTo? (To which moveTo refers now closeTo?) 
			//TODO setTail! & validTailPoint = true
			//TODO numberOfValidPoints++;
		}
		else if(type == PathIterator.SEG_CLOSE)
		{
			numberOfCurrentPoints = 0;
			headPoint = prevPoint;
			numberOfValidPoints = numberOfValidPoints + 1;	//TODO +2 not +1 because of the tail
			//TODO setTail!
			validHeadPoint = true;
		}
		else
		{
			assert(type>0 && type<4);
			
			numberOfCurrentPoints = type;
			headPoint = prevPoint;
			numberOfValidPoints ++;
			
			validHeadPoint = true;
		}
		
		int sizeOfValidPoints = numberOfCurrentPoints + numberOfValidPoints;
		int index = 0;
		
		validPoints = new double[sizeOfValidPoints][2];
		
		if(validHeadPoint)
		{
			validPoints[index][0] = headPoint[0];
			validPoints[index][1] = headPoint[1];
			
			index ++;
		}

		for(int i = 0; i<numberOfCurrentPoints*2; i=i+2) //TODO CHECK THIS!
		{	
			validPoints[index][0] = currentPoints[i];
			validPoints[index][1] = currentPoints[i+1];
			
			index ++;
		}
		
		if(validTailPoint)
		{
			validPoints[index][0] = tailPoint[0];
			validPoints[index][1] = tailPoint[1];
			
			index ++;
		}
		
		return validPoints;
	}
	
	private static void printAllPoints(double[][] currentValidPoints, String string) 
	{
		System.out.println(string);
		
		for(int i = 0; i<currentValidPoints.length; i++)
		{
			System.out.println("point [ " + currentValidPoints[i][0] + " | " + currentValidPoints[i][1]+" ]");
		}
		
	}

	private static void analyzeLine(double[][] currentPoints)
	{
//		double t = 1.2;
//		
//		double[] point0 = prevPoint;
//		double[] point1 = currentPoints;
//		
//		double x = (1-t)*point0[0]+t*point1[0];
//		double y = (1-t)*point0[1]+t*point1[1];
//		
//		double[] pointC = {x,y};
//		
//		BezierMain.setPointToDraw(pointC);
//		
//		prevPoint = point1;
	}
	
	private static void analyzeQuadratic(double[][] currentPoints)
	{
//		double t = 1.2;
//		
//		double[] point0 = prevPoint;
//		double[] point1 = currentPoints;
//		
//		double x = (1-t)*point0[0]+t*point1[0];
//		double y = (1-t)*point0[1]+t*point1[1];
//		
//		double[] pointC = {x,y};
//		
//		BezierMain.setPointToDraw(pointC);
		
//		System.out.println("cu " + currentPoints.length);
		
	}
	
	private static int factorialIterative(int number)
	{
		if(number>16)
		{
			System.out.println("Error: The input value of " + number + " is to big!");
			System.exit(1);
		}
		
		int result = 1;
		
		for(int i = 1; i<=number;i++)
		{
			result = result * i;
		}
		
		return result;
	}
	
//	private static int factorialRecursive(int number)
//	{
//		if(number>16)
//		{
//			System.out.println("Error: The input value of " + number + " is to big!");
//			System.exit(1);
//		}
//		
//		if(number == 0)
//		{
//			return 1;
//		}
//		else
//		{
//			return factorialRecursive(number-1)*number;
//		}
//	}
	
	
	public static int binomialCoefficientDirect(int n, int k)
	{
		if(n<k)
		{
			System.out.println("Error: n was less than k!");
			
			System.exit(1);
		}
		
		return (factorialIterative(n)/(factorialIterative(k)*factorialIterative(n-k)));
	}

	public static int binomialCoefficientRecursive(int n, int k) //Source of the pseudocode: http://de.wikipedia.org/wiki/Binomialkoeffizient
	{
		int result;
		
		if(n<k)
		{
			System.out.println("Error: n was less than k!");
			
			System.exit(1);
		}
		
		if(k==0)
		{
			return 1;
		}
		else if(2*k>n)
		{
			result = binomialCoefficientRecursive(n, n-k);
		}
		else
		{
			result = n-k+1;
			
			for(int i = 2; i<=k; i++)
			{
				result = result*(n-k+i);
				result = result/i;
			}
		}
		
		return result;
	}
	
	public static int binom(int n, int k) //Source code :http://dl.dornea.nu/svn/studium/sose2010/ti2/u3/Hilfe_Ueb3_Aufg6.pdf
	{
		// 1.Rekursionsanker
		if(k == 0 || k == n) return 1;
		// 2.Rekursionsanker
		else if(k == 1) return n;
		// Rekursionsschritt
		else 
			return binom(n-1,k) + binom(n-1,k-1);
	}

}
