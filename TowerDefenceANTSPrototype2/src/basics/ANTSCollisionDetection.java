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
	
	
	public ANTSCollisionDetection(int height, int width, int cellsX, int cellsY)
	{
		this.height = height;
		this.width = width;
		
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
	
	public ANTSCollisionDetection(int height, int width)
	{
		this.height = height;
		this.width = width;
		
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
		int hashValue = (int) Math.floor((pos * (sizeHashTable - 1)) / max);
		
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
					
					this.checkOutOfRange(xHash,controller,iterator);
					this.checkOutOfRange(yHash,controller,iterator);
					
//					ANTSStream.printDebug(controller + " hashValue " + xHash + " | " + yHash);
					if(xHash != cellX || yHash!= cellY)
					{
//						ANTSStream.printDebug("change " + controller + " hashValue " + xHash + " | " + yHash);
						
						iterator.remove();
						this.hashTable[xHash][yHash].add(controller);
					}
				}
			}
		}
	}
	
	private void checkOutOfRange(int yHash, ANTSIController controller,
			ListIterator<ANTSIController> iterator) {
		// TODO Auto-generated method stub
		
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
		
		this.hashTable[xHash][yHash].add(c);
//		ANTSStream.print("Controller " + c + " added to hashMap at " + xHash + " | " + yHash);
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
