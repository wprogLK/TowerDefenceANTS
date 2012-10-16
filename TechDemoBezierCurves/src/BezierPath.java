import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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
			
			this.paintDebug(g2d);
		}
		
		protected void paintDebug(Graphics2D g2d)
		{
			
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
	
	//cubic bezierCurve (n=3) (4 Points)
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
		
		protected void paintDebug(Graphics2D g2d)
		{
//			this.paintDebugPoint(g2d, startPoint, "startPoint");
//			this.paintDebugPoint(g2d, point1, "point1");
//			this.paintDebugPoint(g2d, point2, "point2");
//			this.paintDebugPoint(g2d, endPoint, "endPoint");
		}
		
		private void paintDebugPoint(Graphics2D g2d, double[] point, String s)
		{
			g2d.fill(new Rectangle2D.Double(point[0], point[1], 5, 5));
			g2d.drawString(s, (int) point[0], (int) point[1]);
		}
		
		@Override
		public double[] calculateIntersectionPoint(double[][] rayVector) 
		{
			//RAY:
				//StartPoint
					double a_x = rayVector[0][0];
					double a_y = rayVector[0][1];
				//EndPoint
					double b_x = rayVector[1][0];
					double b_y = rayVector[1][1];
			//CUBIC:
				//StartPoint
					double p_0_x = startPoint[0];
					double p_0_y = startPoint[1];
				//HelperPoint1
					double p_1_x = point1[0];	
					double p_1_y = point1[1];
				//HelperPoint2
					double p_2_x = point2[0];	
					double p_2_y = point2[1];
				//EndPoint
					double p_3_x = endPoint[0];	
					double p_3_y = endPoint[1];
				
				double c_x = -p_0_x+3*p_1_x-3*p_2_x+p_3_x;
				double c_y = -p_0_y+3*p_1_y-3*p_2_y+p_3_y;
				
				double d_x = 3*p_0_x-6*p_1_x+3*p_2_x;
				double d_y = 3*p_0_y-6*p_1_y+3*p_2_y;
				
				double e_x = -3*p_0_x+3*p_1_x;
				double e_y = -3*p_0_y+3*p_1_y;
				
			//Equation:
				double F = b_x*c_y-a_x*c_y+c_x*a_y-c_x*b_y;
				double G = d_y*b_x-d_y*a_x+d_x*a_y-d_x*b_y;
				double H = e_y*b_x-e_y*a_x+e_x*a_y-e_x*b_y;
				double I = b_x*p_0_y-p_0_y*a_x-b_x*a_y-p_0_x*b_y+p_0_x*a_y+a_x*b_y;
				
				/*0 = (t³F+t²G+tH+I)/(b_x-a_x)
				 * -> 0 = t³F+t²G+tH+I
				 */
				double[] t = this.solveCubicEquation(F, G, H, I);
				
				double[] s = new double[3];
			//	double[][] intersectionPointCub = new double[3][2];
			//	double[][] intersectionPointRay = new double[3][2];
				
				ArrayList<double[]> intersectionPointCub = new ArrayList<double[]>();
				ArrayList<double[]> intersectionPointRay = new ArrayList<double[]>();
				
				
				for(int i = 0; i<3; i++)
				{
					s[i] = (pow3(t[i])*c_x+pow2(t[i])*d_x+t[i]*e_x+p_0_x-a_x)/(b_x-a_x);	//TODO ATTENTION: division 0 !!!
					
					if(t[i]<=1 && t[i]>=0 && s[i]<=1 && s[i]>=0)
					{
						double[] tmpIntersectionPointCub = new double[2];
						tmpIntersectionPointCub[0] = c_x*pow3(t[i])+d_x*pow2(t[i])+e_x*t[i]+p_0_x; 
						tmpIntersectionPointCub[1] = c_y*pow3(t[i])+d_y*pow2(t[i])+e_y*t[i]+p_0_y; 

						intersectionPointCub.add(tmpIntersectionPointCub);
						
						double[] tmpIntersectionPointRay = new double[2];
						tmpIntersectionPointRay[0] = a_x+s[i]*(b_x-a_x);
						tmpIntersectionPointRay[1] = a_y+s[i]*(b_y-a_y);

						intersectionPointRay.add(tmpIntersectionPointRay);
					}
					
					System.out.println("i = " + i + " t = " + t[i]  + " s = " + s[i]);
				}
				
				double[] intersectionPoint = getClosestIntersectionPoint(intersectionPointCub,rayVector);
				
				
			//	BezierMain.setPointToDraw(intersectionPointCub[2]);
				//BezierMain.setPointToDraw(intersectionPointRay[2]);
				
				//TODO
				
				//return intersectionPointCub[1];
				//return intersectionPointRay[1];
				return intersectionPoint;
		}
		
		/**
		 * calculates the closest intersection point to the endPoint of the ray
		 * @param intersectionPoints
		 * @param rayVector
		 * @return
		 */
		private double[] getClosestIntersectionPoint(ArrayList<double[]> intersectionPoints, double[][] rayVector) 
		{
			int i = 0;
			double distance = java.lang.Double.MAX_VALUE;
			double[] currentClosestPoint = new double[2];
			
			do
			{
				double tmpDistance = Point2D.distance(rayVector[1][0], rayVector[1][1], intersectionPoints.get(i)[0], intersectionPoints.get(i)[1]);
			
				if(tmpDistance<distance)
				{
					currentClosestPoint=intersectionPoints.get(i);
					distance = tmpDistance;
				
				}
				i++;
				
			}while(i<intersectionPoints.size()-1);
			
			return currentClosestPoint;
		}

		private double[] solveCubicEquation(double a, double b, double c, double d)
		{
			double p = (3*a*c-pow2(b))/(9*pow2(a));
			double q = 0.5*(((2*pow3(b))/(27*pow3(a)))-((b*c)/(3*pow2(a)))+(d/a));
			double det = pow2(q) + pow3(p);
			
			if(p<0 && det<=0)
			{
				double r = Math.signum(q)*Math.sqrt(-p);
				double s = Math.acos(q/pow3(r));
				
				double[] solution = new double[3];
				
				solution[0] = -2*r*Math.cos(s/3)-b/(3*a);
				solution[1] = 2*r*Math.cos((Math.PI-s)/3)-b/(3*a);
				solution[2] = 2*r*Math.cos((Math.PI+s)/3)-b/(3*a);
				
				return solution;
			}
			else
			{
				System.out.println("Error: no solution!"); //TODO
				return null;
			}
				
		}
	}
	
	private double pow2(double n)
	{
		return Math.pow(n, 2);
	}
	
	private double pow3(double n)
	{
		return Math.pow(n, 3);
	}
}

