import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
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
			this.paintDebugPoint(g2d, startPoint, "startPoint");
			this.paintDebugPoint(g2d, point1, "point1");
			this.paintDebugPoint(g2d, point2, "point2");
			this.paintDebugPoint(g2d, endPoint, "endPoint");
		}
		
		private void paintDebugPoint(Graphics2D g2d, double[] point, String s)
		{
			g2d.fill(new Rectangle2D.Double(point[0], point[1], 5, 5));
			g2d.drawString(s, (int) point[0], (int) point[1]);
		}
		
		@Override
		public double[] calculateIntersectionPoint(double[][] rayVector) 
		{
			/*
			 * RayVector:
			 * 	A:=startPoint =(a_x,a_y) = (a,g)
			 * 	B:=endPoint = (b_x,b_y)
			 * 	dR:=B-A = (dR_x,dR_y) = (b,h)
			 * 
			 * 	P_R:= A+t*dR
			 * 
			 * Cubic:
			 *  P_0:=startPoint
			 *  P_1,P_2 points
			 *  P_3:=endPoint
			 *  D:=endPoints = (d_x,d_y)
			 *
			 *  C:= (-P_0+3*P_1-3*P_2+P_3) = (c,i)
			 *  D:= (3*P_0-6*P_1+3*P_2) = (d,j)
			 *  E:= (-3*P_0+3*P_1) = (e,k)
			 *	F:= P_0 = (f,l)
			 *
			 *	P_C = C*s^3+D*s^2+E*s+F
			 *
			 * Equation:
			 * (I)  a + t*b = c*s^3 + d*s^2 +e*s+f
			 * (II) g+t*h = i*s^3 + j*s^2 + l*s + l
			 * 
			 * 
			 * Calculation:
			 * 
			 * 	COMMENT: everything with ' is a double letter(ex. A' is in the code AA)!
			 * 
			 * 	(I)  -> t'  = (c*s^3+d*s^2+e*s+f-a)/b
			 * 	(II) -> t'' = (i*s^3+j*s^2+k*s+l-g)/h
			 * 
			 *  t' == t'' & simplify -> s^3(h*c-b*i)+s^2(h*d-b*j)+s(h*e-b*k)+h*f-h*a+b*g-b*l=0
			 * 
			 *  A':= h*c-b*i
			 *  B':= h*d-b*j
			 *  C':= h*e-b*k
			 *  D':= h*f-h*a+b*g-b*l
			 *  
			 *  A'!=0 ->
			 *  a':= B'/A'
			 *  b':= C'/A'
			 *  c':=D'/A'
			 *  
			 *  s = alpha*z+beta; alpha := 1; beta :=-a'/3
			 *  
			 *  p:= b'-(a'^2)/3
			 *  q:= (2*a'^2)/27 -(a'*b')/3+c'
			 *  
			 *  Det:= (q/2)^2 + (p/3)^3
			 *  
			 *  u:= thirdRoot(-q/2+sqrt(Det))
			 *  v:= thirdRoot(-q/2-sqrt(Det))
			 *  
			 *  case 1: Det>0: 1 real solution 2 complex
			 *  	z_1 := u+v
			 *  	z_(2,3) := .....
			 *  case 2: Det=0: 3 real solutions
			 *  	u=v ->  z_1 = 2u = thirdRoot(-4*q) = 3q/p
			 *  			z_(2,3) = -u = thirdRoot(q/2) = -3q/2p
			 *  	p=q=0 -> z = 0 is the only solution!
			 *  case 3: Det<0 3 real solutions 
			 *  	u,v complex konj. to each other -> z = u+v = u + �(u) = 2Re(u)
			 *  	.
			 *  	.
			 *  	.
			 *  	.
			 *  	z_2 = ....
			 *  	z_1 = ....
			 *  	z_3 = ....
			 *  
			 */	
			
			System.out.println("try to calculate intersection point with a curve");
			
			//RAY:
			double[] A = rayVector[0];
			double[] B = rayVector[1];
			
			double a = A[0];
			double g = A[1];
			
			double[] dR = {B[0]-A[0],B[1]-A[1]};
			
			double b = dR[0];
			double h = dR[1];
			
			//CUBIC:
			
			double[] P_0 = this.startPoint;
			double[] P_1 = this.point1;
			double[] P_2 = this.point2;
			double[] P_3 = this.endPoint;
			
			double[] C = new double[2];
			double[] D = new double[2];
			double[] E = new double[2];
			double[] F = new double[2];
			
				for(int i=0; i<2;i++)
				{
					C[i] = -1*P_0[i]+3*P_1[i]-3*P_2[i]+P_3[i];
					D[i] = 3*P_0[i]-6*P_1[i]+3*P_2[i];
					E[i] = -3*P_0[i]+3*P_1[i];
					F[i] = P_0[i];
				}
			
			double c = C[0];
			double i = C[1];
			
			double d = D[0];
			double j = D[1];
			
			double e = D[0];
			double k = D[1];
			
			double f = F[0];
			double l = F[1];
			
			//EQUATION:
			
			double AA = h*c-b*i;
			double BB = h*d-b*j;
			double CC = h*e-b*k;
			double DD = h*f-h*a+b*g-b*l;
			
			assert(AA!=0);
//			
			double aa = BB/AA;
			double bb = CC/AA;
			double cc = DD/AA;
			
			System.out.println("AA= " + AA);	
			
			//NEW CALC:
			double p = (3*AA*CC-pow2(BB))/(9*pow2(AA));
			
			double X = (2*pow3(BB))/(27*pow3(AA));
			double Y = (BB*CC)/(3*pow2(AA));
			double Z = DD/AA;
			
			double q = 0.5*(X-Y+Z);//(1/2)*((2*pow3(BB))/(27*pow3(AA))-(BB*CC)/(3*pow2(AA))+(DD/AA));
			
			double det = pow2(q)+pow3(p);
			
			double[] z = new double[3];
			
			if(p<0 && det<=0)
			{
				double r = Math.signum(q)*Math.sqrt(-p);
				double s = Math.acos(q/pow3(r));
				
				z[0] = -2*r*Math.cos(s/3)-b/(3*AA);
				z[1] = 2*r*Math.cos((Math.PI-s)/3)-b/(3*a);
				z[2] = 2*r*Math.cos((Math.PI+s)/3)-b/(3*a);
			}
			
			//ReSub:
			
			double[] s = new double[3];
			double[] t = new double[3];
			double[] tt = new double[3];
			double[][] intersectionPointsRay = new double[3][2];
			double[][] intersectionPointsCub = new double[3][2];
//			
			for(int index = 0; index<3; index++)
			{
				s[index] = z[index] - aa/3;//(BB)/(3*AA);

				//Check:
				double S = s[index];
				double sum = pow3(S)+aa*pow2(S)+bb*S+cc;
//				System.out.println("sum should be = 0; sum is = " + sum);	
				
				t[index] = (c*pow3(s[index])+d*pow2(s[index])+e*s[index]+f-a)/b;
				tt[index] = (i*pow3(s[index])+j*pow2(s[index])+k*s[index]+l-g)/h;
				
				intersectionPointsRay[index][0] = a + t[index]*b;
				intersectionPointsRay[index][1] = g + t[index]*h;
				
				intersectionPointsCub[index][0] = c*pow3(s[index])+d*pow2(s[index])+e*s[index]+f;
				intersectionPointsCub[index][1] = i*pow3(s[index])+j*pow2(s[index])+k*s[index]+l;
			}
			
			BezierMain.setPointToDraw(intersectionPointsCub[1]);
			return intersectionPointsRay[1];
			
			//			
//			double[] t = new double[3];
//			double[] tt = new double[3];
//			
//			double x_r[] = new double[3];
//			double y_r[] = new double[3];
//			
//			double x_q[] = new double[3];
//			double y_q[] = new double[3];
//			
//			double[][] intersectionPoint = new double[2][2]; //first ray, second cubic
//			
//			for(int index = 0; index<3; index++)
//			{
//				t[index] = (c*pow3(s[index])+d*pow2(s[index])+e*s[index]+f-a)/b;
//				tt[index] = (i*pow3(s[index])+j*pow2(s[index])+k*s[index]+l-g)/h;
//				
//				System.out.println("t = " + t[index] + " tt = " + tt[index]);
//				System.out.println("s = " + s[index]);
//				
//				x_r[index] = a + t[index]*b;
//				y_r[index] = g + t[index]*h;
//				
////				s[index] = 0.5;
//				
//				x_q[index] = c*pow3(s[index])+d*pow2(s[index])+e*s[index]+f;
//				y_q[index] = i*pow3(s[index])+j*pow2(s[index])+k*s[index]+l;
//				
//				double checkQ = pow3(s[index])*AA+pow2(s[index])*BB+s[index]*CC+DD;
//				System.out.println("checkQ should be 0: " + checkQ);
//				
//				double checkQ2 = pow3(s[index])+pow2(s[index])*aa+s[index]*bb+cc;
//				System.out.println("checkQ2 should be 0: " + checkQ2);
//				
//				System.out.println("P_R=[ " + x_r[index] + " | " + y_r[index] +" ]");
//				System.out.println("P_Q=[ " + x_q[index] + " | " + y_q[index] +" ]");
//				
//				if(index==0)///s[index]<=1 && s[index]>=0)
//				{
//					System.out.println("Set point: s = " + s[index] + " index = " + index);
//				
//					intersectionPoint[0][0] = x_r[index];
//					intersectionPoint[0][1] = y_r[index];
//					
//					intersectionPoint[1][0] = x_q[index];
//					intersectionPoint[1][1] = y_q[index];
//				}
//			}
//			
//			BezierMain.setPointToDraw(intersectionPoint[1]);
//			
//			return intersectionPoint[0];
//		}
			

			
			
	}
		
//OLD
//		@Override
//		public double[] calculateIntersectionPoint(double[][] rayVector) 
//		{
//			/*
//			 * RayVector:
//			 * 	A:=startPoint =(a_x,a_y) = (a,g)
//			 * 	B:=endPoint = (b_x,b_y)
//			 * 	dR:=B-A = (dR_x,dR_y) = (b,h)
//			 * 
//			 * 	P_R:= A+t*dR
//			 * 
//			 * Cubic:
//			 *  P_0:=startPoint
//			 *  P_1,P_2 points
//			 *  P_3:=endPoint
//			 *  D:=endPoints = (d_x,d_y)
//			 *
//			 *  C:= (-P_0+3*P_1-3*P_2+P_3) = (c,i)
//			 *  D:= (3*P_0-6*P_1+3*P_2) = (d,j)
//			 *  E:= (-3*P_0+3*P_1) = (e,k)
//			 *	F:= P_0 = (f,l)
//			 *
//			 *	P_C = C*s^3+D*s^2+E*s+F
//			 *
//			 * Equation:
//			 * (I)  a + t*b = c*s^3 + d*s^2 +e*s+f
//			 * (II) g+t*h = i*s^3 + j*s^2 + l*s + l
//			 * 
//			 * 
//			 * Calculation:
//			 * 
//			 * 	COMMENT: everything with ' is a double letter(ex. A' is in the code AA)!
//			 * 
//			 * 	(I)  -> t'  = (c*s^3+d*s^2+e*s+f-a)/b
//			 * 	(II) -> t'' = (i*s^3+j*s^2+k*s+l-g)/h
//			 * 
//			 *  t' == t'' & simplify -> s^3(h*c-b*i)+s^2(h*d-b*j)+s(h*e-b*k)+h*f-h*a+b*g-b*l=0
//			 * 
//			 *  A':= h*c-b*i
//			 *  B':= h*d-b*j
//			 *  C':= h*e-b*k
//			 *  D':= h*f-h*a+b*g-b*l
//			 *  
//			 *  A'!=0 ->
//			 *  a':= B'/A'
//			 *  b':= C'/A'
//			 *  c':=D'/A'
//			 *  
//			 *  s = alpha*z+beta; alpha := 1; beta :=-a'/3
//			 *  
//			 *  p:= b'-(a'^2)/3
//			 *  q:= (2*a'^2)/27 -(a'*b')/3+c'
//			 *  
//			 *  Det:= (q/2)^2 + (p/3)^3
//			 *  
//			 *  u:= thirdRoot(-q/2+sqrt(Det))
//			 *  v:= thirdRoot(-q/2-sqrt(Det))
//			 *  
//			 *  case 1: Det>0: 1 real solution 2 complex
//			 *  	z_1 := u+v
//			 *  	z_(2,3) := .....
//			 *  case 2: Det=0: 3 real solutions
//			 *  	u=v ->  z_1 = 2u = thirdRoot(-4*q) = 3q/p
//			 *  			z_(2,3) = -u = thirdRoot(q/2) = -3q/2p
//			 *  	p=q=0 -> z = 0 is the only solution!
//			 *  case 3: Det<0 3 real solutions 
//			 *  	u,v complex konj. to each other -> z = u+v = u + �(u) = 2Re(u)
//			 *  	.
//			 *  	.
//			 *  	.
//			 *  	.
//			 *  	z_2 = ....
//			 *  	z_1 = ....
//			 *  	z_3 = ....
//			 *  
//			 */	
//			
//			System.out.println("try to calculate intersection point with a curve");
//			
//			//RAY:
//			double[] A = rayVector[0];
//			double[] B = rayVector[1];
//			
//			double a = A[0];
//			double g = A[1];
//			
//			double[] dR = {B[0]-A[0],B[1]-A[1]};
//			
//			double b = dR[0];
//			double h = dR[1];
//			
//			//CUBIC:
//			
//			double[] P_0 = this.startPoint;
//			double[] P_1 = this.point1;
//			double[] P_2 = this.point2;
//			double[] P_3 = this.endPoint;
//			
//			double[] C = new double[2];
//			double[] D = new double[2];
//			double[] E = new double[2];
//			double[] F = new double[2];
//			
//				for(int i=0; i<2;i++)
//				{
//					C[i] = -1*P_0[i]+3*P_1[i]-3*P_2[i]+P_3[i];
//					D[i] = 3*P_0[i]-6*P_1[i]+3*P_2[i];
//					E[i] = -3*P_0[i]+3*P_1[i];
//					F[i] = P_0[i];
//				}
//			
//			double c = C[0];
//			double i = C[1];
//			
//			double d = D[0];
//			double j = D[1];
//			
//			double e = D[0];
//			double k = D[1];
//			
//			double f = F[0];
//			double l = F[1];
//			
//			//EQUATION:
//			
//			double AA = h*c-b*i;
//			double BB = h*d-b*j;
//			double CC = h*e-b*k;
//			double DD = h*f-h*a+b*g-b*l;
//			
////			assert(AA!=0);
////			
////			double aa = BB/AA;
////			double bb = CC/AA;
////			double cc = DD/AA;
////			
////			double alpha = 1;
////			double beta = -aa/3;
////			
////			double p = bb-pow2(aa)/3;
////			double q = (2*pow2(aa)/27-((aa*bb)/3)+cc);
////			
////			System.out.println("q= " + q + " p = "+ p);
////			
////			double det = pow2(q/2)+pow3(p/3);
////			
////			double u = Math.cbrt(-(q/2)+Math.sqrt(det));
////			double v = Math.cbrt(-(q/2)-Math.sqrt(det));
////			
////			//Cases:
////			
////			double[] zs = new double[3];
////			
////			if(det>0)
////			{
////				System.out.println("case 1: det>0");
////				
////				double z_1 = u+v;
////				zs[0] = z_1;
////			}
////			else if(det == 0)
////			{
////				System.out.println("case 2: det==0");
////				
////				if(u == v)
////				{
////					System.out.println("u==v");
////					
////					double z_11 = 2*u;
////					double z_12 = Math.cbrt(-4*q);
////					double z_13 = 3*q/p;
////					
////					assert(z_11==z_12 && z_12==z_13); //TODO check this
////					
////					zs[0] = z_11;
////					
////					System.out.println("z_11 = " + z_11 + "\nz_12 = "+ z_12 + "\nz_13 = " + z_13);
////					
////					double z_21 = -u;
////					double z_22 = Math.cbrt(q/2);
////					double z_23 = -3*q/(2*p);
////					
////					assert(z_21==z_22 && z_22==z_23); //TODO check this
////					
////					zs[1] = z_21;
////					
////					System.out.println("z_21 = " + z_21 + "\nz_22 = "+ z_22 + "\nz_23 = " + z_23);
////				}
////				
////				if(p==0 && q==0)
////				{
////					System.out.println("p==q==0");
////					
////					double z_1=0;
////					
////					zs[0] = z_1;
////					
////				}
////			}
////			else if(det<0)
////			{
////				System.out.println("case 3: det<0");
////				
////				//u, v complex conj. to each other //TODO: implement this condition!
////
////				double z_2 = -1*Math.sqrt(-4*p/3)*Math.cos((1/3)*Math.acos((-q/2)*Math.sqrt(-27/pow3(p)))+Math.PI/3);	//TODO Check this!
////				double z_1 = Math.sqrt(-4*p/3)*Math.cos((1/3)*Math.acos((-q/2)*Math.sqrt(-27/pow3(p)))); //TODO Check this!
////				double z_3 = -1*Math.sqrt(-4*p/3)*Math.cos((1/3)*Math.acos((-q/2)*Math.sqrt(-27/pow3(p)))-Math.PI/3);	//TODO Check this!
////
////				zs[1] = z_2;
////				zs[0] = z_1;
////				zs[2] = z_3;
////				
////				System.out.println("z_2 = " + z_2 + "\nz_1 = "+ z_1 + "\nz_3 = " + z_3);
////			}
////			else
////			{
////				System.out.println("impossible case! should not happen! (in CurveToCommand)"); //TODO
////			}
////			
////			///ReSub:
////			
////			double[] s = new double[3];
////			
////			for(int index = 0; index<3; index++)
////			{
////				s[index] = zs[index] - aa/3;//(BB)/(3*AA);
////				
////				double checkZ = pow3(zs[index])+p*zs[index]+q;
////				System.out.println("-------- checkZ = 0 it is =" + checkZ); //TODO
////			}
////			
////			double[] t = new double[3];
////			double[] tt = new double[3];
////			
////			double x_r[] = new double[3];
////			double y_r[] = new double[3];
////			
////			double x_q[] = new double[3];
////			double y_q[] = new double[3];
////			
////			double[][] intersectionPoint = new double[2][2]; //first ray, second cubic
////			
////			for(int index = 0; index<3; index++)
////			{
////				t[index] = (c*pow3(s[index])+d*pow2(s[index])+e*s[index]+f-a)/b;
////				tt[index] = (i*pow3(s[index])+j*pow2(s[index])+k*s[index]+l-g)/h;
////				
////				System.out.println("t = " + t[index] + " tt = " + tt[index]);
////				System.out.println("s = " + s[index]);
////				
////				x_r[index] = a + t[index]*b;
////				y_r[index] = g + t[index]*h;
////				
//////				s[index] = 0.5;
////				
////				x_q[index] = c*pow3(s[index])+d*pow2(s[index])+e*s[index]+f;
////				y_q[index] = i*pow3(s[index])+j*pow2(s[index])+k*s[index]+l;
////				
////				double checkQ = pow3(s[index])*AA+pow2(s[index])*BB+s[index]*CC+DD;
////				System.out.println("checkQ should be 0: " + checkQ);
////				
////				double checkQ2 = pow3(s[index])+pow2(s[index])*aa+s[index]*bb+cc;
////				System.out.println("checkQ2 should be 0: " + checkQ2);
////				
////				System.out.println("P_R=[ " + x_r[index] + " | " + y_r[index] +" ]");
////				System.out.println("P_Q=[ " + x_q[index] + " | " + y_q[index] +" ]");
////				
////				if(index==0)///s[index]<=1 && s[index]>=0)
////				{
////					System.out.println("Set point: s = " + s[index] + " index = " + index);
////				
////					intersectionPoint[0][0] = x_r[index];
////					intersectionPoint[0][1] = y_r[index];
////					
////					intersectionPoint[1][0] = x_q[index];
////					intersectionPoint[1][1] = y_q[index];
////				}
////			}
////			
////			BezierMain.setPointToDraw(intersectionPoint[1]);
////			
////			return intersectionPoint[0];
////		}
//			
//			//NEW CALC:
//			double p = (3*AA*CC-pow2(BB))/(9*pow2(AA));
//			double q = (1/2)*((2*pow3(BB))/(27*pow3(AA))-(BB*CC)/(3*pow2(AA))+(DD/AA));
//			
//			double det = pow2(q)+pow3(p);
//			
//			if(p<0 && det<=0)
//			{
//				double r = Math.signum(q)*Math.sqrt(-p);
//				double s = Math.acos(q/pow3(r));
//				
//				double z_1 = -2*r*Math.cos(s/3)-b/(3*AA);
//				double z_2 = 2*r*Math.cos((Math.PI-s)/3)-b/(3*a);
//				double z_3 = 2*r*Math.cos((Math.PI+s)/3)-b/(3*a);
//			}
//			
//			
//	}
	
	private double pow2(double n)
	{
		return Math.pow(n, 2);
	}
	
	private double pow3(double n)
	{
		return Math.pow(n, 3);
	}
	
	
}
}
