package basics;

import interfaces.ANTSIController;

import java.util.LinkedList;
import java.util.ListIterator;

import basics.ANTSDevelopment.ANTSDebug;
import basics.ANTSDevelopment.ANTSStream;

public class ANTSCollisionDetection 
{
	private int height;
	private int width;
	
	private final int defaultCellsX = 100;
	private final int defaultCellsY = 100;
	
	private int cellsX;
	private int cellsY;
	
	private LinkedList<ANTSIController>[][] hashTable;
	private ANTSFactory factory;
	
	public ANTSCollisionDetection(int height, int width, int cellsX, int cellsY, ANTSFactory antsFactory)
	{
		this.height = height;
		this.width = width;
		
		this.factory = antsFactory;
		
		this.cellsX = cellsX;
		this.cellsY = cellsY;
		
		this.hashTable = new LinkedList[cellsX][cellsY];
		
		for(int x=0; x<this.hashTable.length; x++)
		{
			for(int y = 0; y < this.hashTable[x].length; y++)
			{
				this.hashTable[x][y] = new LinkedList<ANTSIController>();
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
		
		this.hashTable = new LinkedList[cellsX][cellsY];
		
		for(int x=0; x<this.hashTable.length; x++)
		{
			for(int y = 0; y < this.hashTable[x].length; y++)
			{
				this.hashTable[x][y] = new LinkedList<ANTSIController>();
			}
		}
	}
	
	
	public int calculateHash(double pos, int sizeHashTable, int max)
	{
		int hashValue= (int) Math.floor((pos * (sizeHashTable - 1)) / max);
		
		if(hashValue<0)
		{	
			return 0;			//TODO object left/is leaving the window
		}
		
		if(hashValue>=sizeHashTable)
		{
			return sizeHashTable-1;			//TODO object left/is leaving the window
		}
		else
		{
			return hashValue;
		}
	}
	
	public void update()
	{
//		ANTSStream.printDebug("start update in CD");
		this.updateHashMap();
		this.lookingForCollision();
		this.resteAllUpdatedModels();
	}

	private void updateHashMap() 
	{
		for(int cellX=0; cellX < this.cellsX; cellX++)
		{
			for(int cellY = 0; cellY < this.cellsY; cellY++)
			{
				ListIterator<ANTSIController> iterator = this.hashTable[cellX][cellY].listIterator();
				while(iterator.hasNext())
				{
					ANTSIController controller = iterator.next();
					
					if(!controller.getModel().isAlreadyUpdated())
					{
						controller.update();
					}
				
					int xHash = this.calculateHash(controller.getPosX(), this.cellsX, this.width);
					int yHash = this.calculateHash(controller.getPosY(), this.cellsY, this.height);
					
//					boolean isOutOfRangeX = this.isOutOfRange(xHash, this.cellsX);
//					boolean isOutOfRangeY = this.isOutOfRange(yHash, this.cellsY);
					
					boolean isOutOfRange = this.isOutOfRangeN(controller);
					
					if(isOutOfRange)//isOutOfRangeX || isOutOfRangeY)
					{
//						ANTSStream.printDebug("out of range" + width + " h  " + height);
						iterator.remove();
						this.factory.removeController(controller);
					}
					else
					{
						if(xHash != cellX || yHash!= cellY)
						{
//							ANTSStream.printDebug("change " + controller + " hashValue " + xHash + " | " + yHash);
							
							iterator.remove();
							this.hashTable[xHash][yHash].add(controller);
						}
					}
				}
			}
		}
	}
	
	private boolean isOutOfRangeN(ANTSIController controller) {
		if(controller.getPosX()<0 || controller.getPosX()>=this.width || controller.getPosY()<0 || controller.getPosY()>=this.height)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	private boolean isOutOfRange(int hashValue, int max) 
	{
		if(hashValue<0 || hashValue>=max)
		{	
			return true;			//TODO object left/is leaving the window
		}
		else
		{
			return false;
		}
	}

	private void lookingForCollision() 
	{
		// TODO Auto-generated method stub
//		ANTSStream.printDebug("TODO: looking for collision");
	}

	public void addController(ANTSIController c) 
	{
		int xHash = this.calculateHash(c.getPosX(), this.cellsX, this.width);
		int yHash = this.calculateHash(c.getPosY(), this.cellsY, this.height);
		
		boolean isOutOfRangeX = this.isOutOfRange(xHash, this.cellsX);
		boolean isOutOfRangeY = this.isOutOfRange(yHash, this.cellsY);
		
		if(!(isOutOfRangeX || isOutOfRangeY))
		{
			this.hashTable[xHash][yHash].add(c);
		}
		
		
		
	
	}
	
	private void resteAllUpdatedModels()
	{
		for(int cellX=0; cellX < this.cellsX; cellX++)
		{
			for(int cellY = 0; cellY < this.cellsY; cellY++)
			{
				ListIterator<ANTSIController> iterator = this.hashTable[cellX][cellY].listIterator();
				while(iterator.hasNext())
				{
					ANTSIController controller = iterator.next();
					controller.getModel().setIsAlreadyUpdated(false);
					
				}
			}
		}
	}
	
	

	
	
	
}
