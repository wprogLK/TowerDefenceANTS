package models;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Set;

import basics.ANTSDriver;
import basics.ANTSFactory;

public class ANTSCellModel extends ANTSAbstractModel implements ANTSIModel 
{
	private ArrayList<ANTSIController> controllers;
	
	private int relativePosX;	//nr of the cell
	private int relativePosY;	//nr of the cell
	
	private int gridOffsetX =60;
	private int gridOffsetY = 60;
	
	private double absolutePosX; //pos in pix
	private double absolutePosY; //pos in pix
	
	private int shift;
	
	/**
	 * in degrees!
	 */
	private double defaultAngle = 30;	//Angle between x-Axis and "line"
	
	/**
	 * in degrees!
	 */
	private double angle;
	
	private double cellHeight;
	private double cellWidth;
	
	private double lineLength;
	
	private AffineTransform matrix;
	
	private boolean mouseEntered;
	
	
	public ANTSCellModel(double cellHeight, double cellAngleInDegree, int cellNrX, int cellNrY, int shiftHalf, int xGridOffset, int yGridOffset, ANTSFactory factory )
	{
		super(factory);
		
		init(cellHeight, cellAngleInDegree, cellNrX, cellNrY, xGridOffset, yGridOffset);
		
		this.matrix = new AffineTransform();
		
		this.calc();
		
		this.shift = shiftHalf;
		
		this.absolutePosX = this.cellWidth * cellNrX + this.gridOffsetX;
		this.absolutePosY = this.cellHeight * cellNrY+ this.gridOffsetY;
		
		this.absolutePosY = this.cellHeight*this.relativePosY/2 +this.gridOffsetY*2;
		this.absolutePosX+=this.cellWidth/2*this.shift;
		
		this.isMouseListener = true;
		
		this.mouseEntered =false;
		
		this.addMenu(factory.createCircleMenu(absolutePosX+this.cellWidth/2, absolutePosY-cellHeight/2, 250)); //only for teseting
	}
	

	private void calc() 
	{
		double angleInRadian = Math.toRadians(this.angle);
		
		double halfeCellHeight = this.cellHeight/2;
		
		this.lineLength = halfeCellHeight/Math.sin(angleInRadian);
		this.cellWidth = 2*Math.sqrt(this.lineLength*this.lineLength-halfeCellHeight*halfeCellHeight);
	}


	private void init(double cellHeight, double cellAngleInDegree, int cellNrX, int cellNrY, int xGridOffset, int yGridOffset)
	{
		this.isMouseListener = false;
		
		this.controllers = new ArrayList<ANTSIController>();
		
		this.cellHeight = cellHeight;
		this.cellWidth = cellHeight;
		
		this.angle = cellAngleInDegree;
		
		this.relativePosX = cellNrX;
		this.relativePosY = cellNrY;
		
		this.gridOffsetX = xGridOffset;
		this.gridOffsetY = yGridOffset;
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public int[] getCoordRel()
	{
		int[] coord = {this.relativePosX, this.relativePosY};
		return coord;
	}
	
	public double[] getCoordAbs()
	{
		double[] coord = {this.absolutePosX, this.absolutePosY};
		return coord;
	}
	
	public String toString()
	{
		return "(" + this.getCoordRel()[0] + "|" + this.getCoordRel()[1] + ")";
	}

	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public double getWidth()
	{
		return this.cellWidth;
	}
	
	public double getBoxWidth()
	{
		return this.cellHeight;
	}
	
	public double getBoxHeight()
	{
		return this.cellHeight;
	}
	
	public double getHeight()
	{
		return this.cellHeight;
	}
	
	public double[][] getPoints()
	{
		double[][] points = new double[4][2];
		
		
		double aX =  this.absolutePosX +  + this.cellWidth/2;
		double aY = this.absolutePosY;
		
		points[0][0] = aX;
		points[0][1] = aY;
		
		double bX = this.absolutePosX;
		double bY = this.absolutePosY - this.cellHeight/2;
		
		points[1][0] = bX;
		points[1][1] = bY;
		
		double cX = this.absolutePosX  + this.cellWidth/2;
		double cY = this.absolutePosY - this.cellHeight;
		
		points[2][0] = cX;
		points[2][1] = cY;
		
		double dX = this.absolutePosX  + this.cellWidth;
		double dY = this.absolutePosY - this.cellHeight/2;
		
		points[3][0] = dX;
		points[3][1] = dY;
		
		
		return points;
	}
	
	public void setMouseEntered(boolean entered)
	{
		this.mouseEntered = entered;
	}
	
	public boolean getMouseEntered()
	{
		return this.mouseEntered;
	}
	
	///////////
	//SPECIAL//
	///////////

	public void addController(ANTSIController controller)
	{
		if(!this.controllers.contains(controller))
		{
			this.controllers.add(controller);
		}
	}
	
}
