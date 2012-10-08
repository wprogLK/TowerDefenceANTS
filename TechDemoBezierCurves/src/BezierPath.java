import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Stack;

public class BezierPath extends Path2D.Double implements BezierIPath
{
	private ArrayList<BezierIPathCommand> segments;
	
	Stack<double[]> endPoints = new Stack<double[]>();
	double[] startPointOfPath = new double[2];
	
	public BezierPath() 
	{
		this.segments = new ArrayList<BezierIPathCommand>();
	}
	
	@Override
	public void addMoveTo(double x, double y)
	{
		double[] point = {x,y};
		
		BezierIPathCommand command = new MoveToCommand(point);
		this.segments.add(command);

		if(this.startPointOfPath==null)
		{
			this.startPointOfPath = point; //TODO and else?
		}
		
		this.moveTo(x, y);
	}
	
	@Override
	public void addLineTo(double x, double y)
	{
		double[] point = {x,y};
		
		BezierIPathCommand command = new LineToCommand(point);
		this.segments.add(command);
		
		this.lineTo(x, y);
	}
	
	@Override
	public void addClose()
	{
		BezierIPathCommand command = new CloseCommand();
		this.segments.add(command);
		
		this.closePath();
	}
	
	@Override
	public void addCurveTo(double x1,double y1, double x2, double y2, double x3, double y3)
	{
		double[] point1 = {x1,y1};
		double[] point2 = {x2,y2};
		double[] point3 = {x3,y3};
		
		BezierIPathCommand command = new CurveToCommand(point1,point2,point3);
		this.segments.add(command);
		
		this.curveTo(x1, y1, x2, y2, x3, y3);
		
	}
	
	@Override
	public void addQuadTo(double x1,double y1, double x2, double y2)
	{
		double[] point1 = {x1,y1};
		double[] point2 = {x2,y2};
		
		BezierIPathCommand command = new QuadToCommand(point1,point2);
		this.segments.add(command);
		
		this.quadTo(x1, y1, x2, y2);
	}

	@Override
	public double[] calculateIntersectionPoint(double[] directionVector)
	{
		double[] intersectionPoint = new double[2];
		
		for(BezierIPathCommand command : this.segments)
		{
			if(!command.getClass().equals(MoveToCommand.class)) //Because moveTo Command has no visible line, and no intersectionPoint, so skip it
			{
				if(command.contains(directionVector))	//Check first if directionVector intersects current "curve" in the bounds2D
				{
					 intersectionPoint = command.calculateIntersectionPoint(directionVector);
				}
				
				if(isIntersectionPointValid(intersectionPoint))
				{
					return intersectionPoint;
				}
			}
		}
		
		return null; //TODO
	}

	private boolean isIntersectionPointValid(double[] intersectionPoint) 
	{
		return intersectionPoint != null; //TODO
	}

	@Override
	public boolean contains(double[] directionVector) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 
	 * @param points: The last point [size-1][0] and [size-1][1] is the endPoint
	 */
	private void addEndpoint(double[][] points)
	{
		double[] endPoint = new double[2];
		
		assert(points.length>0);
		
		endPoint[0] = points[points.length-1][0];
		endPoint[1] = points[points.length-1][1];
		
		this.endPoints.add(endPoint);
	}
	////////////
	//COMMANDS//
	////////////
	
	
	private class MoveToCommand implements BezierIPathCommand
	{
		public MoveToCommand(double[] point)
		{

		}
		
		@Override
		public double[] calculateIntersectionPoint(double[] directionVector) 
		{
			//DO NOTHING!
			return null;
		}

		@Override
		public boolean contains(double[] directionVector) 
		{
			//DO NOTHING!
			return false;
		}
		
		@Override
		public double[] getStartPoint()
		{
			//TODO
			return null;
		}
		
		@Override
		public double[] getEndPoint()
		{
			//TODO
			return null;
		}
	}
	
	private class CloseCommand implements BezierIPathCommand
	{
		public CloseCommand()
		{

		}
		
		@Override
		public double[] calculateIntersectionPoint(double[] directionVector) 
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean contains(double[] directionVector) 
		{
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public double[] getStartPoint()
		{
			//TODO
			return null;
		}
		
		@Override
		public double[] getEndPoint()
		{
			//TODO
			return null;
		}
	}
	
	private class QuadToCommand implements BezierIPathCommand
	{
		private double[] point1;
		private double[] endpoint;
		
		public QuadToCommand(double[] point1, double[] point2)
		{
			this.point1 = point1;
			this.endpoint = point2;
		}
		
		@Override
		public double[] calculateIntersectionPoint(double[] directionVector) 
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean contains(double[] directionVector) 
		{
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public double[] getStartPoint()
		{
			//TODO
			return null;
		}
		
		@Override
		public double[] getEndPoint()
		{
			//TODO
			return null;
		}
	}
	
	private class CurveToCommand implements BezierIPathCommand
	{
		private double[] endpoint;
		private double[] point1;
		private double[] point2;
		
		
		public CurveToCommand(double[] point1, double[] point2, double[] point3)
		{
			this.point1 = point1;
			this.point2 = point2;
			this.endpoint = point3;
		}
		
		@Override
		public double[] calculateIntersectionPoint(double[] directionVector) 
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean contains(double[] directionVector) 
		{
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public double[] getStartPoint()
		{
			//TODO
			return null;
		}
		
		@Override
		public double[] getEndPoint()
		{
			//TODO
			return null;
		}
	}
	
	private class LineToCommand implements BezierIPathCommand
	{
		private double[] endPoint;
		
		public LineToCommand(double[] point)
		{
			this.endPoint = point;
		}
		
		@Override
		public double[] calculateIntersectionPoint(double[] directionVector) 
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean contains(double[] directionVector) 
		{
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public double[] getStartPoint()
		{
			//TODO
			return null;
		}
		
		@Override
		public double[] getEndPoint()
		{
			//TODO
			return null;
		}
	}
}
