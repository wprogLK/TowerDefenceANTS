package basics;

import interfaces.ANTSIController;
import interfaces.ANTSIRayController;
import interfaces.ANTSIView;
import interfaces.medium.ANTSIMediumController;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import controllers.lens.ANTSSimpleLensController;

import basics.ANTSDevelopment.ANTSStream;

public class ANTSCollisionDetection 
{
	private int height;
	private int width;
	
	private final int defaultCellsX = 2;		//TODO: IMPORTANT: If you have big objects the number of cells should be small! Otherwise the detection is not really working
	private final int defaultCellsY = 2;
	
	private int cellsX;
	private int cellsY;
	
	private ANTSHashMapCell[][] hashMap;
	
	private ANTSFactory factory;
	
	public ANTSCollisionDetection(int height, int width, int cellsX, int cellsY, ANTSFactory antsFactory)
	{
		this.height = height;
		this.width = width;
		
		this.factory = antsFactory;
		
		this.cellsX = cellsX;
		this.cellsY = cellsY;
		
		double cellHeight = this.height/cellsY;
		double cellWidth = this.width/cellsX;
		
		this.hashMap = new ANTSHashMapCell[cellsX][cellsY];
		
		for(int x=0; x<this.hashMap.length; x++)
		{
			for(int y = 0; y < this.hashMap[x].length; y++)
			{
				this.hashMap[x][y] = new ANTSHashMapCell(x,y,cellHeight,cellWidth);
			}
		}
	}
	
	public ANTSCollisionDetection(int height, int width, ANTSFactory antsFactory)
	{
		this.height = height;
		this.width = width;
		
		this.factory = antsFactory;
		
		this.cellsX = defaultCellsX;
		this.cellsY = defaultCellsY;
		
		double cellHeight = this.height/cellsY;
		double cellWidth = this.width/cellsX;
		
		this.hashMap = new ANTSHashMapCell[cellsX][cellsY];
		
		for(int x=0; x<this.hashMap.length; x++)
		{
			for(int y = 0; y < this.hashMap[x].length; y++)
			{
				this.hashMap[x][y] = new ANTSHashMapCell(x,y,cellHeight,cellWidth);
			}
		}
	}
	
	
	public int calculateHash(double pos, int sizeHashTable, int max)
	{
		return (int) Math.floor((pos * (sizeHashTable - 1)) / max);
	}
	
	public void update()
	{
		this.updateHashMap();
		this.lookingForCollision();
		this.factory.resetUpdate();
	}

	private void updateHashMap() 
	{
		for(int cellX=0; cellX < this.cellsX; cellX++)
		{
			for(int cellY = 0; cellY < this.cellsY; cellY++)
			{
				ListIterator<ANTSIController> iterator = this.hashMap[cellX][cellY].getIteratorAll();
				
				while(iterator.hasNext())
				{
					ANTSIController controller = iterator.next();
					
					if(!controller.getModel().isAlreadyUpdated())
					{
						controller.update();
					}
				
					int xHash = this.calculateHash(controller.getPosX(), this.cellsX, this.width);
					int yHash = this.calculateHash(controller.getPosY(), this.cellsY, this.height);
					
					boolean isOutOfRangeX = this.isOutOfRange(xHash, this.cellsX);
					boolean isOutOfRangeY = this.isOutOfRange(yHash, this.cellsY);
					
					if(isOutOfRangeX || isOutOfRangeY)
					{
						this.hashMap[cellX][cellY].remove(controller);
						
						iterator.remove();
						this.factory.removeController(controller);
					}
					else
					{
						if(xHash != cellX || yHash!= cellY)
						{
							this.hashMap[cellX][cellY].remove(controller);
							
							iterator.remove();
							this.hashMap[xHash][yHash].add(controller);
						}
					}
				}
			}
		}
	}
	
	private boolean isOutOfRange(int hashValue, int max) 
	{
		if(hashValue<0 || hashValue>=max)
		{	
			return true;
		}
		else
		{
			return false;
		}
	}

	private void lookingForCollision() 
	{
		for(int cellX = 0; cellX<this.cellsX; cellX++)
		{
			for(int cellY = 0; cellY<this.cellsY; cellY++)
			{
				LinkedList<ANTSIRayController> colliderRays = new LinkedList<ANTSIRayController>();
				LinkedList<ANTSIMediumController> colliderMedium = new LinkedList<ANTSIMediumController>();
				
				colliderRays.addAll(this.hashMap[cellX][cellY].getRays());
				colliderMedium.addAll(this.hashMap[cellX][cellY].getObjects());
				
				// Lower Cell
				if (cellY > 0) {
					colliderMedium.addAll(this.hashMap[cellX][cellY - 1].getObjects());

					// Lower right cell
					if (cellX < this.cellsX - 1) {
						colliderMedium.addAll(this.hashMap[cellX + 1][cellY - 1].getObjects());
					}

					// Lower left cell
					if (cellX > 0) {
						colliderMedium.addAll(this.hashMap[cellX - 1][cellY - 1].getObjects());
					}
				}

				// Upper Cell
				if (cellY < this.cellsY - 1) {
					colliderMedium.addAll(this.hashMap[cellX][cellY + 1].getObjects());

					// Upper right cell
					if (cellX < this.cellsX - 1) {
						colliderMedium.addAll(this.hashMap[cellX + 1][cellY + 1].getObjects());
					}

					// Upper left cell
					if (cellX > 0) {
						colliderMedium.addAll(this.hashMap[cellX - 1][cellY + 1].getObjects());
					}
				}

				// Left Cell
				if (cellX > 0) {
					colliderMedium.addAll(this.hashMap[cellX - 1][cellY].getObjects());
				}
				// Right Cell
				if (cellX < this.cellsX - 1) {
					colliderMedium.addAll(this.hashMap[cellX + 1][cellY].getObjects());
				}
				
				for(ANTSIRayController ray : colliderRays)
				{
					boolean noCollisionWithNonStandardMedium = true;
					
					for(ANTSIMediumController medium : colliderMedium)//this.hashMap[cellX][cellY].getObjects())
					{
						if(this.checkForCollision(ray,medium))
						{
							this.calculateAngle(ray, medium);
							noCollisionWithNonStandardMedium = false;
							break;
						}
					}
					
					if(noCollisionWithNonStandardMedium)
					{
						this.calculateAngle(ray, this.factory.createStandardMediumController());
					}
				}
			}
		}
	}
	
	public void calculateAngle(ANTSIRayController ray, ANTSIMediumController mediumIn)
	{
		double angle =  0;
		
		this.checkLens(ray,mediumIn);
		
		if(!ray.setCurrentMedium(mediumIn))
		{
			//for debugging
			
			if(mediumIn.equals(this.factory.createStandardMediumController()))	//TODO: only for debugging
			{
				ray.addAngle(-angle);
			}
			else
			{
				ray.addAngle(angle);
			}
		}
	}
	
	private void checkLens(ANTSIRayController ray, ANTSIMediumController mediumIn)
	{
		if(mediumIn.getClass().equals(ANTSSimpleLensController.class))
		{
			double[] intersectionPoint = calculateIntersectionPoint(ray,mediumIn);
			
			double phiLot = this.getPhiLot(intersectionPoint,mediumIn);	
			double angleBetween = this.getAngleBetween(phiLot,intersectionPoint,mediumIn,ray);
		}
		else
		{
			
		}
	}
	
//OLD
//	private double getAlphaLot(double[] intersectionPoint, ANTSIMediumController mediumIn) 
//	{
//		ANTSSimpleLensController lensController = (ANTSSimpleLensController) mediumIn;
//		
//		double R = lensController.getRadius()/2.0;
//		double[] M = lensController.getCenter();
//		
//		double x = intersectionPoint[0];
//		
//		double phiRad = Math.cosh((x-M[0])/R);
//		double phiDeg = Math.toDegrees(phiRad);
//		
//		ANTSStream.printDebug("phiDeg = " + phiDeg);
//		return 0;
//	}
	
	private double getAngleBetween(double phiLot, double[] intersectionPoint, ANTSIMediumController mediumIn, ANTSIRayController ray) 
	{
		
		//TODO Debug this!
		
		ANTSSimpleLensController lensController = (ANTSSimpleLensController) mediumIn;
		double[] centerLens = lensController.getCenter();
		
		
		double[] vecLot = new double[2];
		double[] vecRay = new double[2];
		
		vecLot[0] = intersectionPoint[0] - centerLens[0];
		vecLot[1] = intersectionPoint[1] - centerLens[1];
		
//		vecRay[0] = intersectionPoint[0] - ray.getVector()[0].x;
//		vecRay[1] = intersectionPoint[1] - ray.getVector()[0].y;
		
		vecRay[0] = ray.getVector()[1].x - ray.getVector()[0].x;
		vecRay[1] = ray.getVector()[1].y - ray.getVector()[0].y;
		
		double angleRad = Math.cosh((vecLot[0]*vecRay[0]+vecLot[1]*vecRay[1])/(Math.sqrt(pow(vecLot[0])+pow(vecLot[1]))*Math.sqrt(pow(vecRay[0])+pow(vecRay[1]))));
		
		double angleDeg = Math.toDegrees(angleRad);
		
		ANTSStream.printDebug("angle is " + angleDeg);
		ANTSStream.printDebug("phiLot is " + phiLot);
		ANTSStream.printDebug("angleRay is " + ray.getAngle());
		
		return angleDeg;
		
//		Possible solution sometimes angle is NaN
//		ANTSSimpleLensController lensController = (ANTSSimpleLensController) mediumIn;
//		
//		double[] centerLens = lensController.getCenter();
//		Point2D.Double[] vectorRay = ray.getVector();
//		
//		double mLot = (intersectionPoint[1]-centerLens[1])/(intersectionPoint[0]-centerLens[0]);
//		double mRay = (vectorRay[1].y-vectorRay[0].y)/(vectorRay[1].x-vectorRay[0].x);
//		
//		double angleRad = Math.tanh((mLot-mRay)/(1+mLot*mRay));
//		
//		
//		double angleDeg = Math.toDegrees(angleRad);
//		
//		ANTSStream.printDebug("angle is " + angleDeg);
//		
//		return angleDeg;
	}

	private double getPhiLot(double[] intersectionPoint, ANTSIMediumController mediumIn) 
	{
		ANTSSimpleLensController lensController = (ANTSSimpleLensController) mediumIn;
		
		double R = lensController.getRadius()/2.0;
		double[] M = lensController.getCenter();
		double x_m = M[0];
		double y_m = M[1];
		
		double x = intersectionPoint[0];
		double y = intersectionPoint[1];
		
		String caseString = "";
		
		double anglePhiRad = 0;
		double anglePhiDeg = 0;
		
		double angleBetaDeg = 0;	//TODO: only for debugging
		double angleOffsetDeg = 0;	//TODO: only for debugging
		
		double angleGreenDeg = 0;
		
		//CASE A
		if(x>=x_m && y<=y_m)
		{
			caseString = "Case A";
			
			double deltaY = y_m-y;
			double deltaX = x-x_m;
			
			anglePhiRad = Math.sinh(deltaX/R);
			anglePhiDeg = Math.toDegrees(anglePhiRad);
			
			angleGreenDeg = anglePhiDeg;
			
			angleOffsetDeg = 270;
		}
		//CASE B
		else if(x>=x_m && y>=y_m)
		{
			caseString = "Case B";
			
			double deltaY = y-y_m;
			double deltaX = x-x_m;
			
			anglePhiRad = Math.sinh(deltaX/R);
			anglePhiDeg = Math.toDegrees(anglePhiRad);
			
			angleGreenDeg = 90-anglePhiDeg;
			
			angleOffsetDeg = 0;
		}
		//CASE C
		else if(x<x_m && y>=y_m)
		{
			caseString = "Case C";
			
			double deltaY = y-y_m;
			double deltaX = x_m-x;
			
			anglePhiRad = Math.sinh(deltaX/R);
			anglePhiDeg = Math.toDegrees(anglePhiRad);
			
			angleGreenDeg = anglePhiDeg;
			
			angleOffsetDeg = 90;
		}
		//CASE D
		else if(x<x_m && y<y_m)
		{
			caseString = "Case D";
			
			double deltaY = y_m-y;
			double deltaX = x_m-x;
			
			anglePhiRad = Math.sinh(deltaX/R);
			anglePhiDeg = Math.toDegrees(anglePhiRad);
			
			angleGreenDeg = 90-anglePhiDeg;
			
			angleOffsetDeg = 180;
		}
		//CASE UNKNOWN
		else
		{
			caseString = "Something strange happend: Unkown case";
			ANTSStream.printDebug(caseString);
		}
		
		angleBetaDeg = angleGreenDeg + angleOffsetDeg;
		
//		ANTSStream.printDebug(caseString + " Angle phi' in degree is " + anglePhiDeg);
//		ANTSStream.printDebug(" Angle beta in degree is " + angleBetaDeg);
//		ANTSStream.printDebug(" Angle green in degree is " + angleGreenDeg);
		
		return anglePhiDeg;
	}


	private double[] calculateIntersectionPoint(ANTSIRayController ray,ANTSIMediumController mediumIn) 
	{
		ANTSSimpleLensController lensController = (ANTSSimpleLensController) mediumIn;
		
		double R = lensController.getRadius()/2.0;
		double[] M = lensController.getCenter();
		
		Point2D.Double[] vec = ray.getVector();
		
		double x_a = vec[0].x;
		double y_a = vec[0].y;
		
		double x_b = vec[1].x;
		double y_b = vec[1].y;
		
		double x_m = M[0];
		double y_m = M[1];
	
		double G = 2*(-pow(x_a)+x_a*x_b+x_a*x_m-x_b*x_m-pow(y_a)+y_a*y_b+y_a*y_m-y_b*y_m);
		double F = pow(x_a)-2*x_a*x_b+pow(x_b)+pow(y_a)-2*y_a*y_b+pow(y_b);
		double S = pow(R)-pow(x_a)+2*x_a*x_m-pow(x_m)-pow(y_a)+2*y_a*y_m-pow(y_m);
		
		double b = G;
		double a = F;
		double c = - S;
		
		double D = Math.pow(b, 2)-4*a*c;
		
		double t_1,t_2;
		
		if(D>0)
		{
			t_1 = (-b+Math.sqrt(D))/(2.0*a);
			t_2 = (-b-Math.sqrt(D))/(2.0*a);
		}
		else if(D==0)
		{
			t_1 = -b/(2.0*a);
			t_2 = -b/(2.0*a);
		}
		else if(D<0)
		{
			ANTSStream.printDebug("Complex solution");
			
			t_1=Double.POSITIVE_INFINITY;
			t_2=Double.POSITIVE_INFINITY;
		}
		else
		{
			ANTSStream.printDebug("Unkown case");
			t_1=Double.POSITIVE_INFINITY;
			t_2=Double.POSITIVE_INFINITY;
		}
		
		double x_1 = x_a+t_1*(x_b-x_a);
		double x_2 = x_a+t_2*(x_b-x_a);
		
		double y_1 = y_a+t_1*(y_b-y_a);
		double y_2 = y_a+t_2*(y_b-y_a);
		
		lensController.setPointsOfIntersection(x_1,y_1,x_2,y_2);
		
		double[] point1 = {x_1,y_1};
		double[] point2 = {x_2,y_2};
		
		double[] closestPoint = getClosestIntersectionPoint(ray,point1,point2);
		lensController.setThePointOfIntersection(closestPoint);
		
		return closestPoint;
	}
	
	private double[] getClosestIntersectionPoint(ANTSIRayController ray, double[] point1, double[] point2) 
	{
		double distancePoint1 = getDistanceBetweenRayAndIntersectionPoint(ray, point1);
		double distancePoint2 = getDistanceBetweenRayAndIntersectionPoint(ray, point2);
		
		if(distancePoint1<=distancePoint2)
		{
			return point1;
		}
		else
		{
			return point2;
		}
	}

	private double getDistanceBetweenRayAndIntersectionPoint(ANTSIRayController ray, double[] point) 
	{
		double[] posRay = new double[2];
		
		posRay[0] = ray.getModel().getMatrix().getTranslateX();
		posRay[1] = ray.getModel().getMatrix().getTranslateY();
		
		double distance = Point2D.distance(point[0], point[1], posRay[0], posRay[1]);
		
		return distance;
	}

	private double pow(double v)
	{
		return Math.pow(v, 2);
	}
	
	public boolean checkForCollision(ANTSIRayController ray, ANTSIMediumController medium)
	{
		ANTSIView rayView = ray.getIView();
		ANTSIView mediumView = medium.getIView();
		
		Shape rayShape = rayView.getShape();
		Shape mediumShape = mediumView.getShape();
		
		return rayShape.intersects(mediumShape.getBounds2D());
	}

	public void addController(ANTSIController c) 
	{
		int xHash = this.calculateHash(c.getPosX(), this.cellsX, this.width);
		int yHash = this.calculateHash(c.getPosY(), this.cellsY, this.height);
		
		boolean isOutOfRangeX = this.isOutOfRange(xHash, this.cellsX);
		boolean isOutOfRangeY = this.isOutOfRange(yHash, this.cellsY);
		
		if(!(isOutOfRangeX || isOutOfRangeY))
		{
			this.hashMap[xHash][yHash].add(c);
		}
	}
	
	public void paintDetectionGrid(Graphics2D g2d)
	{
		if(this.factory.getDevelopmentController().isShowDetectionGrid())
		{
			for(int cellX = 0; cellX<this.cellsX; cellX++)
			{
				for(int cellY = 0; cellY<this.cellsY; cellY++)
				{
					this.hashMap[cellX][cellY].paint(g2d);
				}
			}
		}
	}
	
	private class ANTSHashMapCell
	{
		private LinkedList<ANTSIRayController> rays;
		private LinkedList<ANTSIMediumController> medium;
		
		private int x;
		private int y;
		
		private double height;
		private double width;
		
		private Shape shape;
		
		public ANTSHashMapCell(int x, int y, double height, double width)
		{
			this.rays = new LinkedList<ANTSIRayController>();
			this.medium = new LinkedList<ANTSIMediumController>();

			this.x = (int) (x*width);
			this.y = (int) (y*height);
			this.height = height;
			this.width = width;
			
			this.shape = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
		}
		
		public LinkedList<ANTSIRayController> getRays() 
		{
			return this.rays;
		}
		
		public LinkedList<ANTSIMediumController> getObjects() 
		{
			return this.medium;
		}

		public void add(ANTSIController c)
		{
			if(ANTSUtility.implementsInterface(c, ANTSIRayController.class))
			{
				this.rays.add((ANTSIRayController) c);
			}
			else if(ANTSUtility.implementsInterface(c, ANTSIMediumController.class))
			{
				this.medium.add((ANTSIMediumController) c);
			}
			else
			{
				ANTSStream.printErr("The controller " + c.toString() + " couldn't add to the collision dedection unit"); //TODO
			}
		}
		
		public boolean remove(ANTSIController c)
		{
			if(ANTSUtility.implementsInterface(c, ANTSIRayController.class))
			{
				return this.rays.remove((ANTSIRayController) c);
			}
			else if(ANTSUtility.implementsInterface(c, ANTSIMediumController.class))
			{
				return this.medium.remove((ANTSIMediumController) c);
			}
			else
			{
				ANTSStream.printErr("The controller " + c.toString() + " couldn't add to the collision dedection unit"); //TODO
				return false;
			}
		}
		
		public ListIterator<ANTSIController> getIteratorAll()
		{
			LinkedList<ANTSIController> total = new LinkedList<ANTSIController>();
			total.addAll(this.medium);
			total.addAll(this.rays);
			
			return total.listIterator();
		}
		
		public void paint(Graphics2D g2d)
		{
			if(!this.medium.isEmpty())
			{
				g2d.setColor(Color.RED);
			}
			else
			{
				g2d.setColor(Color.BLUE);
			}
		
			g2d.draw(this.shape);
		}
	}
}
