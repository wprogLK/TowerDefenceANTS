import java.awt.geom.PathIterator;
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
		
		double[] currentPoints = {Double.NaN,Double.NaN,Double.NaN,Double.NaN};
		
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
			printPoints(currentPoints, " currentPoint = ");
			printPoints(prevPoint, " prevPoint = ");
			analyzeCurrentSegment(type,currentPoints,prevPoint);
			
			System.out.println("The prevPoint is [" + prevPoint[0] + " | " + prevPoint[1] + " ] ");
			
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

	private static void printPoints(double[] currentPoints, String message)
	{
		for(int i = 0; i*2<currentPoints.length/2;i=i+2)
		{
			System.out.println(message + " [ " + currentPoints[i] + " | " + currentPoints[i+1] +" ]" );
		}
	}
	
	private static void analyzeCurrentSegment(int type, double[] currentPoints, double[] prevPoint) 
	{
		double[] currentValidPoints = Arrays.copyOfRange(currentPoints, 0, type*2);
		
		switch(type)
		{
			case(PathIterator.SEG_MOVETO):
			{
				analyzeMove(currentValidPoints,prevPoint);
				
				break;
			}
			case(PathIterator.SEG_LINETO):
			{
				analyzeLine(currentValidPoints,prevPoint);
				
				break;
			}
			case(PathIterator.SEG_QUADTO):
			{
				analyzeQuadratic(currentValidPoints,prevPoint);
				break;
			}
			case(PathIterator.SEG_CUBICTO):
			{
			
				
				break;
			}
			case(PathIterator.SEG_CLOSE):
			{
				break;
			}
			default:
			{
				System.out.println("Error: Unknown segment type! The type value is " + type);
				System.exit(1);	//TODO
			}
		}
		
		int length = currentPoints.length;
		int numberOfPoints = length/2;
		
		prevPoint[0] = currentPoints[length-2];
		prevPoint[1] = currentPoints[length-1];
	}

	private static void analyzeMove(double[] currentPoints, double[] prevPoint)
	{
		prevPoint = currentPoints;
	}
	
	private static void analyzeLine(double[] currentPoints, double[] prevPoint)
	{
		double t = 1.2;
		
		double[] point0 = prevPoint;
		double[] point1 = currentPoints;
		
		double x = (1-t)*point0[0]+t*point1[0];
		double y = (1-t)*point0[1]+t*point1[1];
		
		double[] pointC = {x,y};
		
		BezierMain.setPointToDraw(pointC);
		
		prevPoint = point1;
	}
	
	private static void analyzeQuadratic(double[] currentPoints, double[] prevPoint)
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
		
		System.out.println("cu " + currentPoints.length);
		
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
