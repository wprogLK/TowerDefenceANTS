import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Stack;

public class BezierPath extends Path2D.Double implements BezierIPath
{
	private ArrayList<BezierIPathCommand> segments;
	
	//Stack<double[]> endPoints = new Stack<double[]>(); //EndPoint of a segment
	double[] currentEndPointOfPreviousSegment;
	double[] startPointOfPath = new double[2];
	
	public BezierPath() 
	{
		double[] currentEndPointOfPreviousSegment = new double[2];
		this.segments = new ArrayList<BezierIPathCommand>();
	}
	
	@Override
	public void addMoveTo(double x, double y)
	{
		this.specialPreAndPostConditionIsEmpty();
		
		double[] point = {x,y};
		
		BezierIPathCommand command = new MoveToCommand(point);
		this.segments.add(command);

		if(this.startPointOfPath==null)
		{
			this.startPointOfPath = point; //TODO and else?
		}
		
		this.currentEndPointOfPreviousSegment=point;
		
		this.moveTo(x, y);
		
		preAndPostConditionEndPos();
	}
	
	@Override
	public void addLineTo(double x, double y)
	{
		this.preAndPostConditionEndPos();
		
		double[] endPoint = {x,y};
		double[] startPoint = this.currentEndPointOfPreviousSegment;
		
		BezierIPathCommand command = new LineToCommand(startPoint,endPoint);
		this.segments.add(command);

		this.currentEndPointOfPreviousSegment = endPoint;
		
		this.lineTo(x, y);
		
		this.preAndPostConditionEndPos();
	}
	
	@Override
	public void addClose()
	{
		this.preAndPostConditionEndPos();
		
		double[] startPoint = this.currentEndPointOfPreviousSegment;
		double[] endPoint = this.startPointOfPath;
		
		BezierIPathCommand command = new CloseCommand(startPoint,endPoint);
		this.segments.add(command);
		
		this.currentEndPointOfPreviousSegment = null;
		this.startPointOfPath = null;
		
		this.closePath();
		
		this.specialPreAndPostConditionIsEmpty();
	}
	
	@Override
	public void addCurveTo(double x1,double y1, double x2, double y2, double x3, double y3)
	{
		this.preAndPostConditionEndPos();
		
		double[] startPoint = this.currentEndPointOfPreviousSegment;
		double[] point1 = {x1,y1};
		double[] point2 = {x2,y2};
		double[] endPoint = {x3,y3};
		
		BezierIPathCommand command = new CurveToCommand(startPoint,point1,point2,endPoint);
		this.segments.add(command);
		
		this.currentEndPointOfPreviousSegment = endPoint;
		
		this.curveTo(x1, y1, x2, y2, x3, y3);
		
		this.preAndPostConditionEndPos();
	}
	
	@Override
	public void addQuadTo(double x1,double y1, double x2, double y2)
	{
		this.preAndPostConditionEndPos();
		
		double[] startPoint = this.currentEndPointOfPreviousSegment;
		double[] point1 = {x1,y1};
		double[] endPoint = {x2,y2};
		
		BezierIPathCommand command = new QuadToCommand(startPoint, point1,endPoint);
		this.segments.add(command);
		
		this.currentEndPointOfPreviousSegment = endPoint;
		
		this.quadTo(x1, y1, x2, y2);
		
		this.preAndPostConditionEndPos();
	}
	
	private void preAndPostConditionEndPos()
	{
//		assert(this.endPoints.size()==1);
		assert(this.currentEndPointOfPreviousSegment != null);	//TODO check this
	}
	
	private void specialPreAndPostConditionIsEmpty() 
	{
		assert(this.currentEndPointOfPreviousSegment == null);	//TODO check this
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
	
//	private void addEndpoint(double[] endpoint)
//	{
//		assert(endpoint!=null);
//		
//		this.endPoints.add(endpoint);
//	}
	////////////
	//COMMANDS//
	////////////
	
	
	private abstract class BezierCommand implements BezierIPathCommand
	{
		protected final double[] startPoint;
		protected final double[] endPoint;
		
		public BezierCommand(double[] startPoint, double[] endPoint)
		{
			assert(endPoint!=null);
			
			this.startPoint = startPoint;
			this.endPoint = endPoint;
		}
		
		@Override
		public final double[] getEndPoint()
		{
			return this.endPoint;
		}
	}
	
	private class MoveToCommand extends BezierCommand implements BezierIPathCommand
	{
		public MoveToCommand(double[] endPoint)
		{
			super(null,endPoint);
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
	}
	
	private class CloseCommand  extends BezierCommand implements BezierIPathCommand
	{	
		public CloseCommand(double[] startPoint,double[] endPoint)
		{
			super(startPoint, endPoint);
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
	}		
	
	private class QuadToCommand  extends BezierCommand implements BezierIPathCommand
	{
		private double[] point1;
		
		public QuadToCommand(double[] startPoint, double[] point1, double[] endPoint)
		{
			super(startPoint,endPoint);
			
			this.point1 = point1;
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
	}
	
	private class CurveToCommand  extends BezierCommand implements BezierIPathCommand
	{
		private double[] point1;
		private double[] point2;
		
		public CurveToCommand(double[] startPoint,double[] point1, double[] point2, double[] endPoint)
		{
			super(startPoint,endPoint);
			
			this.point1 = point1;
			this.point2 = point2;
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
	}
	
	private class LineToCommand  extends BezierCommand implements BezierIPathCommand
	{
		public LineToCommand(double[] startPoint, double[] endPoint)
		{
			super(startPoint,endPoint);
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
	}
}
