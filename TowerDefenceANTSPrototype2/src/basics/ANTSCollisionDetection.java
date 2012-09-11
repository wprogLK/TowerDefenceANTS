package basics;

import interfaces.ANTSIController;
import interfaces.ANTSIRayController;
import interfaces.ANTSIView;
import interfaces.medium.ANTSIMediumController;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import basics.ANTSDevelopment.ANTSDebug;
import basics.ANTSDevelopment.ANTSStream;

import controllers.ANTSSimpleRayLightController;
import controllers.medium.ANTSStandardMediumController;

public class ANTSCollisionDetection 
{
	private int height;
	private int width;
	
	private final int defaultCellsX = 4;		//TODO: IMPORTANT: If you have big objects the number of cells should be small! Otherwise the detection is not really working
	private final int defaultCellsY = 4;
	
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
				
				colliderRays.addAll(this.hashMap[cellX][cellY].getRays());

				// Lower Cell
				if (cellY > 0) {
					colliderRays.addAll(this.hashMap[cellX][cellY - 1].getRays());

					// Lower right cell
					if (cellX < this.cellsX - 1) {
						colliderRays.addAll(this.hashMap[cellX + 1][cellY - 1].getRays());
					}

					// Lower left cell
					if (cellX > 0) {
						colliderRays.addAll(this.hashMap[cellX - 1][cellY - 1].getRays());
					}
				}

				// Upper Cell
				if (cellY < this.cellsY - 1) {
					colliderRays.addAll(this.hashMap[cellX][cellY + 1].getRays());

					// Upper right cell
					if (cellX < this.cellsX - 1) {
						colliderRays.addAll(this.hashMap[cellX + 1][cellY + 1].getRays());
					}

					// Upper left cell
					if (cellX > 0) {
						colliderRays.addAll(this.hashMap[cellX - 1][cellY + 1].getRays());
					}
				}

				// Left Cell
				if (cellX > 0) {
					colliderRays.addAll(this.hashMap[cellX - 1][cellY].getRays());
				}
				// Right Cell
				if (cellX < this.cellsX - 1) {
					colliderRays.addAll(this.hashMap[cellX + 1][cellY].getRays());
				}
				
				for(ANTSIMediumController medium : this.hashMap[cellX][cellY].getObjects())
				{
					for(ANTSIRayController ray : colliderRays)
					{
						
						if(this.checkForCollision(ray,medium))
						{
							ANTSStream.printDebug("yes");
							
							this.calculateAngle(ray, medium);
						}
						else
						{
							this.calculateAngle(ray, this.factory.createStandardMediumController());
//							ANTSStream.printDebug("no");
						}
//						if(colliderObject.doesCollideWith(colliderRay))
//						{
//							colliderObject.addCollisionRay(colliderRay);
//						}
//						else
//						{
//							colliderObject.removeCollisionRay(colliderRay);
//						}
						
						//TODO
					}
				}
			}
		}
	}
	
	public void calculateAngle(ANTSIRayController ray, ANTSIMediumController mediumIn)
	{
		ANTSIMediumController mediumOut;
		double angle = 45;
		
		if(!ray.setCurrentMedium(mediumIn))
		{
			if(mediumIn.equals(this.factory.createStandardMediumController()))	//TODO: only for debugging
			{
				ANTSStream.printDebug("MINUS");
				ray.addAngle(-angle);
			}
			else
			{
				ray.addAngle(angle);
			}
			
		}
	}
	
	public boolean checkForCollision(ANTSIRayController ray, ANTSIMediumController medium)
	{
		ANTSIView rayView = ray.getIView();
		ANTSIView mediumView = medium.getIView();
		
		Shape rayShape = rayView.getShape();
		Shape mediumShape = mediumView.getShape();
		
		double[] centerRay = ray.getCenter();
		
		ANTSStream.printDebug("Medium "  + mediumView.toString());
		
//		return mediumShape.contains(centerRay[0], centerRay[1]);//rayShape.intersects(mediumShape.getBounds2D());
		
		double maxXMedium = mediumShape.getBounds2D().getMaxX();
		double minXMedium = mediumShape.getBounds2D().getMinX();
		double maxYMedium = mediumShape.getBounds2D().getMaxY();
		double minYMedium = mediumShape.getBounds2D().getMinY();
//		
//		ANTSStream.printDebug("Medium: max x " + maxXMedium +" |  min x " + minXMedium + " || max y  " + maxYMedium + " | min y " + minYMedium);
//		ANTSStream.printDebug("Ray : x " + centerRay[0] + " y " + centerRay[1]);
		boolean xValue;
		boolean yValue;
		
		xValue = centerRay[0]>=minXMedium && centerRay[0]<=maxXMedium;
		yValue = centerRay[1]>=minYMedium && centerRay[1]<=maxYMedium;
		
//		ANTSStream.printDebug("xVal " + xValue + " yVal " + yValue + " Result: " +( xValue && yValue));
			
		if(!(xValue && yValue ) && ANTSDebug.getStopIfNoCollision())
		{
//			System.exit(0);
			this.factory.stopGame();
		}
		
		return xValue && yValue;
	
	
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
		if(ANTSDebug.isShowDetectionGrid())
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
			if(Arrays.asList(c.getClass().getInterfaces()).contains(ANTSIRayController.class))
			{
				this.rays.add((ANTSIRayController) c);
			}
			else if(Arrays.asList(c.getClass().getInterfaces()).contains(ANTSIMediumController.class))
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
			if(Arrays.asList(c.getClass().getInterfaces()).contains(ANTSIRayController.class))
			{
				return this.rays.remove((ANTSIRayController) c);
			}
			else if(Arrays.asList(c.getClass().getInterfaces()).contains(ANTSIMediumController.class))
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
