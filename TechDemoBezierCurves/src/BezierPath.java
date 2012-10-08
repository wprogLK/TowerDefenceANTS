import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
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
	public double[] calculateIntersectionPoint(double[][] rayVector)
	{
		double[] intersectionPoint = new double[2];
		
		for(BezierIPathCommand command : this.segments)
		{
			if(!command.getClass().equals(MoveToCommand.class)) //Because moveTo Command has no visible line, and no intersectionPoint, so skip it
			{
//				if(command.contains(rayVector))	//Check first if directionVector intersects current bounds2D of the current command
//				{
					 intersectionPoint = command.calculateIntersectionPoint(rayVector);
//				}
				
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
	
	@Override
	public void drawSingleSegments(Graphics2D g2d)
	{
		for(BezierIPathCommand command : this.segments)
		{
			command.paintSingleSegment(g2d);
		}
	}
	
	////////////
	//COMMANDS//
	////////////
	
	private abstract class BezierCommand implements BezierIPathCommand
	{
		protected final double[] startPoint;
		protected final double[] endPoint;
		
		protected GeneralPath pathOfOneSegment;
		
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
		
		@Override
		public final boolean contains(double[][] rayVector) 
		{
			if(pathOfOneSegment != null)
			{
				return this.pathOfOneSegment.getBounds2D().contains(rayVector[1][0], rayVector[1][1]); //endPoint of ray must be inside the bounds! //TODO Check and test this!!! DEBUG: it's not working if the ray is too long!
			}
			else
			{
				return false; //TODO
			}
		}
		
		protected void initGeneralPath(double[] startPointOfSegment)
		{
			this.pathOfOneSegment = new GeneralPath();
			this.pathOfOneSegment.moveTo(startPointOfSegment[0], startPointOfSegment[1]);
		}
		
		public final void paintSingleSegment(Graphics2D g2d)
		{
			if(pathOfOneSegment!=null)
			{
				g2d.setColor(Color.BLUE);
				g2d.draw(pathOfOneSegment);
				
				g2d.setColor(Color.RED);
				g2d.draw(pathOfOneSegment.getBounds2D());
			}
		}
		
		
		@Override
		public double[] calculateIntersectionPoint(double[][] rayVector) 
		{
			return null;
		}
	}
	
	private class MoveToCommand extends BezierCommand implements BezierIPathCommand
	{
		public MoveToCommand(double[] endPoint)
		{
			super(null,endPoint);
		}
		
		@Override
		public double[] calculateIntersectionPoint(double[][] rayVector) 
		{
			//DO NOTHING!
			return null;
		}
	}
	
	private class CloseCommand  extends LineToCommand implements BezierIPathCommand //TODO: is closePath always a simple line? Test this
	{	
		public CloseCommand(double[] startPoint,double[] endPoint)
		{
			super(startPoint, endPoint);
			
//			this.initGeneralPath(startPoint);
//			this.pathOfOneSegment.lineTo(endPoint[0], endPoint[1]); 
		}
	}		
	
	private class QuadToCommand  extends BezierCommand implements BezierIPathCommand
	{
		private double[] point1;
		
		public QuadToCommand(double[] startPoint, double[] point1, double[] endPoint)
		{
			super(startPoint,endPoint);
			
			this.point1 = point1;
			
			this.initGeneralPath(startPoint);
			this.pathOfOneSegment.quadTo(point1[0], point1[1], endPoint[0], endPoint[1]);
		}
		
		@Override
		public double[] calculateIntersectionPoint(double[][] rayVector) 
		{
			// TODO Auto-generated method stub
			return null;
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
			
			this.initGeneralPath(startPoint);
			this.pathOfOneSegment.curveTo(point1[0], point1[1],point2[0], point2[1] ,endPoint[0], endPoint[1]);
		}
		
		@Override
		public double[] calculateIntersectionPoint(double[][] rayVector) 
		{
			return null;
		}
	}
	
	private class LineToCommand  extends BezierCommand implements BezierIPathCommand
	{
		public LineToCommand(double[] startPoint, double[] endPoint)
		{
			super(startPoint,endPoint);
			
			this.initGeneralPath(startPoint);
			this.pathOfOneSegment.lineTo(endPoint[0], endPoint[1]);
		}
		
		@Override
		public double[] calculateIntersectionPoint(double[][] rayVector)  //TODO Test this!
		{
			/*
			 * RayVector:
			 * 	A:=startPoint =(a_x,a_y) = (a,e)
			 * 	B:=endPoint = (b_x,_y)
			 * 	dR:=B-A = (dR_x,dR_y) = (b,f)
			 * 
			 * 	P_R:= A+t*dR
			 * 
			 * line:
			 *  C:=startPoint = (c_x,c_y) = (c,g)
			 *  D:=endPoints = (d_x,d_y)
			 *  dL:=D-C = (dL_x,dL_y) = (d,h)
			 *  
			 *  P_L:= C+s*dL
			 *  
			 *  Equation:
			 *  a+t*b=c+s*d
			 *  e+t*f=g+s*h
			 */
			
			//RAY:
			double[] A = rayVector[0];
			double[] B = rayVector[1];
			
			double a = A[0];
			double e = A[1];
			
			double b_x = B[0];
			double b_y = B[1];
			
			double[] dR = {(b_x-a),(b_y-e)};
			double b = dR[0];
			double f = dR[1];
			
			//Line:
			double[] C = this.startPoint;
			double[] D = this.endPoint;
			
			double c = C[0];
			double g = C[1];
			
			double d_x = D[0];
			double d_y = D[1];
			
			double[] dL = {(d_x-c),(d_y-g)};
			double d = dL[0];
			double h = dL[1];
			
			
			//Equation solved:
			double s = (-a*f+b*e-b*g+c*f)/(b*h-d*f);
			double t = (-a+c+d*s)/(b);
			
			assert(s>=0 && s<=1);
			assert(t>=0 && t<=1);
			
			double[] intersectionPoint = {(a+t*b), (e+t*f)}; //TODO check this
			
			return intersectionPoint;
		}
	}
}
