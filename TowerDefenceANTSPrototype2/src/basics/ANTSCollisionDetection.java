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
import java.sql.PreparedStatement;
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
	
	private enum direction {IN, OUT; }
	
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
		ANTSIMediumController mediumOut = ray.getCurrentMedium();
		
		direction direction = null;
		ANTSIMediumController medium;
		
		if(mediumIn.equals(this.factory.createStandardMediumController()))
		{
			medium = mediumOut;
			direction = direction.OUT;
		}
		else
		{
			medium = mediumIn;
			direction = direction.IN;
		}
		
		if(!mediumOut.equals(mediumIn))
		{
			ANTSPerpendicular perpendicular = medium.calculatePerpendicular(ray);
			
			//TODO uncomment this again
			double angleBetweenRayPerpendicular = this.calculateAngleBetweenRayPerpendiuclar(perpendicular, ray, direction);
			
			double angle = this.calculateSnell(angleBetweenRayPerpendicular,mediumIn.getRefractionIndex(),mediumOut.getRefractionIndex());
			
			this.changeAngle(perpendicular, angle, ray,mediumIn.getRefractionIndex(),mediumOut.getRefractionIndex(),direction);
			
			ray.setCurrentMedium(mediumIn);
			
			this.calculateCriticalAngle(mediumIn.getRefractionIndex(),mediumOut.getRefractionIndex());
		}
	}
	
	private void changeAngle(ANTSPerpendicular perpendicular, double angleToAdd, ANTSIRayController ray, double refractionIndexIn, double refractionIndexOut, direction direction)
	{
		double perpendicularAngle = 0;
		double rayAngle = ray.getAngle();
		
		switch(direction)
		{
			case IN:
			{
				perpendicularAngle = perpendicular.getAngleOutToInSide();
				break;
			}
			case OUT:
			{
				perpendicularAngle = perpendicular.getAngleInToOutSide();
				break;
			}
		}
		
		ANTSStream.printDebug("----------------------------------------------------------------------");
		ANTSStream.printDebug("perpendicularAngle = " + perpendicularAngle + " direction " + direction);
		
		double newRayAngle = 0;
		
		if(perpendicularAngle>rayAngle)
		{
			ANTSStream.printDebug("situation B");
			newRayAngle = perpendicularAngle-angleToAdd;
		}
		else
		{
			ANTSStream.printDebug("situation A");
			newRayAngle = perpendicularAngle+angleToAdd;
		}
		
		ANTSStream.printDebug("newRayAngle = " + newRayAngle + "angleToAdd = " + angleToAdd);
		ray.setAngle(newRayAngle);
		
		if(refractionIndexIn>refractionIndexOut)
		{
			ANTSStream.printDebug("Brechung ZUM Lot: \nangle= " + angleToAdd);
		}
		else
		{
			ANTSStream.printDebug("Brechung VOM Lot: \nangle= " + angleToAdd);
		}
	}

	private double calculateSnell(double angleIncoming, double refractionIndexMediumIn, double refractionIndexMediumOut) 
	{
		double angleIncomingRad = Math.toRadians(angleIncoming);
		
		double factor = (Math.sin(angleIncomingRad)*refractionIndexMediumOut)/refractionIndexMediumIn;
		double angle = Math.toDegrees(Math.asin(factor));
		double criticalAngle = this.calculateCriticalAngle(refractionIndexMediumIn, refractionIndexMediumOut);
		
		if(refractionIndexMediumIn<refractionIndexMediumOut && angleIncoming>=criticalAngle)
		{
			ANTSStream.printErr("'Error': TOTAL REFLECTION! \n phi_1 = " + angleIncoming + "\t critical angle = " + criticalAngle);	//TODO: Implement what happen if there is a total reflection
		}

		return angle;
	}

	/**
	 * Calculates the critical angle for a total reflection
	 * @param refractionIndexMediumIn
	 * @param refractionIndexMediumOut
	 * @return
	 */
	private double calculateCriticalAngle(double refractionIndexMediumIn, double refractionIndexMediumOut)
	{
		if(refractionIndexMediumIn<refractionIndexMediumOut)
		{
			double angleCrit = Math.toDegrees(Math.asin(refractionIndexMediumIn/refractionIndexMediumOut));
			
			return angleCrit;
		}
		else
		{
			return -1;
		}
	}
	
	private double calculateAngleBetweenRayPerpendiuclar(ANTSPerpendicular perpendicular, ANTSIRayController ray, direction direction) //TODO CHECK THIS!
	{
		double[] directionVecRay = new double[2];
		double[] directionVecPerpendicular = new double[2];
		
		switch(direction)
		{
			case IN:
			{
				directionVecPerpendicular = perpendicular.getDirectionVectorOutToInSide();
				break;	
			}
			case OUT:
			{
				directionVecPerpendicular = perpendicular.getDirectionVectorInToOutSide();
				break;	
			}
		}
		
		Point2D.Double[] pointsOfRay = ray.getVector();
		Point2D.Double startPointRay = pointsOfRay[0];
		Point2D.Double endPointRay = pointsOfRay[1];
		
		directionVecRay[0] = endPointRay.getX()-startPointRay.getX();
		directionVecRay[1] = endPointRay.getY()-startPointRay.getY();

		double dotProduct = directionVecPerpendicular[0]*directionVecRay[0]+directionVecPerpendicular[1]*directionVecRay[1];
		double lengthPerpendicular = Math.sqrt(pow(directionVecPerpendicular[0]) +pow(directionVecPerpendicular[1]) );
		double lengthRay = Math.sqrt(pow(directionVecRay[0]) +pow(directionVecRay[1]) );
		
		double cosAlpha = dotProduct/(lengthPerpendicular*lengthRay);
		double angle = Math.toDegrees(Math.acos(cosAlpha));				
		
		return angle;
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
		
		return rayShape.intersects(mediumShape.getBounds2D());			//TODO: mediumShape.intersects(rayShape.getBounds2D()) oder so �hnlich (da sonst kollision ungenau ist) 
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
