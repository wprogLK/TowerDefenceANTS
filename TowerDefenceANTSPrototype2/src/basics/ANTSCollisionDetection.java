package basics;

import interfaces.ANTSIController;
import interfaces.medium.ANTSIMediumController;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import basics.ANTSDevelopment.ANTSDebug;
import basics.ANTSDevelopment.ANTSStream;

import controllers.ANTSSimpleRayLightController;

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
				LinkedList<ANTSIController> colliderRays = new LinkedList<ANTSIController>();
				
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
				
				for(ANTSIMediumController colliderObject : this.hashMap[cellX][cellY].getObjects())
				{
					for(ANTSIController colliderRay : colliderRays)
					{
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
		private LinkedList<ANTSIController> rays;
		private LinkedList<ANTSIMediumController> medium;
		
		private int x;
		private int y;
		
		private double height;
		private double width;
		
		private Shape shape;
		
		public ANTSHashMapCell(int x, int y, double height, double width)
		{
			this.rays = new LinkedList<ANTSIController>();
			this.medium = new LinkedList<ANTSIMediumController>();

			this.x = (int) (x*width);
			this.y = (int) (y*height);
			this.height = height;
			this.width = width;
			
			this.shape = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
			
		}
		
		public LinkedList<ANTSIController> getRays() 
		{
			return this.rays;
		}
		
		public LinkedList<ANTSIMediumController> getObjects() 
		{
			return this.medium;
		}

		public void add(ANTSIController c)
		{
			if(c.getClass().equals(ANTSSimpleRayLightController.class))
			{
				this.rays.add(c);
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
			if(c.getClass().equals(ANTSSimpleRayLightController.class))
			{
				return this.rays.remove(c);
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
