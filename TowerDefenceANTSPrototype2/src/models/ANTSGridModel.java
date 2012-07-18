package models;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import controllers.ANTSCellController;

import basics.ANTSDriver;

public class ANTSGridModel extends ANTSAbstractModel implements ANTSIModel 
{
	
	private ANTSCellController[][] grid;		//"[x][y]"
	
	private int xCells;
	private int yCells;
	
	public ANTSGridModel(int xCells, int yCells)
	{
		this.isMouseListener = true;
		this.initCells(xCells,yCells);
		
		this.xCells = xCells;
		this.yCells = yCells;
		
	}
	
	private void initCells(int xCells, int yCells)
	{
		this.grid = new  ANTSCellController[xCells][yCells];
		
		for(int x = 0; x<xCells; x++)
		{
			for(int y = 0; y<yCells; y++)
			{
				ANTSCellController c = new ANTSCellController(x,y);
				this.grid[x][y] = c;
				ANTSDriver.addToCanvas(c.getView());
				ANTSDriver.addComponents(c);
				
			}
		}
		
		System.out.println("initCells done!");
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
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
	//SPECIAL//
	///////////
	
	
}
