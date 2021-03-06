package models;

import interfaces.ANTSIModel;

import controllers.ANTSCellController;

import basics.ANTSFactory;

public class ANTSGridModel extends ANTSAbstractModel implements ANTSIModel 
{
	private ANTSCellController[][] grid;		//"[x][y]"
	
	private int xCells;	
	private int yCells;
	
	private int xOffset = 300;
	private int yOffset = 120;
	
	private double height;
	private double width;
	
	private double cellAngleInDegree = 30;
	private double cellHeight = 120;
	private double cellWidth;
	
	public ANTSGridModel(int xCells, int yCells, ANTSFactory factory)
	{
		super(factory);
		
		this.isMouseListener = true;
		this.initCells(xCells,yCells);
		
		this.xCells = xCells;
		this.yCells = yCells;
		
		ANTSCellModel cellModel = (ANTSCellModel) grid[0][0].getModel();
		
		this.cellWidth = cellModel.getWidth();
		
		double xOffset = 0;
		double yOffset = 0;
		
		if(yCells>1)
		{
			xOffset = this.cellWidth/2;
		}
		
		if(yCells>1)
		{
			yOffset = this.cellHeight/2;
		}
		
		this.width = this.cellWidth*xCells+xOffset;
		this.height = this.cellHeight*yCells/2+yOffset;
	}
	
	private void initCells(int xCells, int yCells)
	{
		this.grid = new  ANTSCellController[xCells][yCells];
		
		for(int x = 0; x<xCells; x++)
		{
			for(int y = 0; y<yCells; y++)
			{
				int shiftHalf = -1*(((y+1)%2)-1); // 0 or 1
				ANTSCellController c = this.factory.createCell(this.cellHeight, this.cellAngleInDegree,x,y,shiftHalf, this.xOffset, this.yOffset);
				this.grid[x][y] = c;
			}
		}
	}
	
	///////////
	//Getters//
	///////////
	
	public double getOffsetX()
	{
		return this.xOffset ;
	}
	
	public double getOffsetY()
	{
		return this.yOffset;
	}
	
	public int getPosX()
	{
		return this.xOffset;
	}
	
	public int getPosY()
	{
		return (int) (this.yOffset-this.cellHeight/2);
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public double getWidth()
	{
		return this.width;
	}
		
	public String toString()
	{
		return "GridModel";
	}
	
	public int getMaxCellX()
	{
		return this.xCells;
	}
	
	public int getMaxCellY()
	{
		return this.yCells;
	}
	
	public ANTSCellController getCellControllerAt(int x, int y)
	{
		return this.grid[x][y];
	}
	
	///////////
	//Setters//
	///////////
	
	
	///////////
	//Special//
	///////////
}
