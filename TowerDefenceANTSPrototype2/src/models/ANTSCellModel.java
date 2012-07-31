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
	
	private double defaultHeight = 60;
	
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
	
	private AffineTransform matrixNew;
	
	private boolean mouseEntered;
	
	
	public ANTSCellModel(double cellHeight, double cellAngleInDegree, int cellNrX, int cellNrY, int shiftHalf, int xGridOffset, int yGridOffset, ANTSFactory factory )
	{
		super(factory);
		
		init(cellHeight, cellAngleInDegree, cellNrX, cellNrY, xGridOffset, yGridOffset);
		
		this.matrix = new AffineTransform();
		this.matrixNew = new AffineTransform();
		
		this.calc();
		
		this.shift = shiftHalf;
		
		this.absolutePosX = this.cellWidth * cellNrX + this.gridOffsetX;
		this.absolutePosY = this.cellHeight * cellNrY+ this.gridOffsetY;
		
		
		this.absolutePosY = this.cellHeight*this.relativePosY/2;
		this.absolutePosX+=this.cellWidth/2*this.shift;
		
		this.matrixNew.rotate(Math.toRadians(45));
//		this.matrix.setToTranslation(this.absolutePosX, this.absolutePosY);
//		this.matrixNew.setToTranslation(this.absolutePosX+this.gridOffsetX*2, this.absolutePosY);
		this.matrixNew.translate(this.absolutePosX-this.gridOffsetX, this.absolutePosY);
		
		double xFactor = this.cellHeight/this.cellWidth/2;
		double yFactor = 0.0;
		
		this.isMouseListener = true;
		
		
		this.matrixNew.shear(xFactor, yFactor);
		this.mouseEntered =false;
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
		return "(" + this.getCoordAbs()[0] + "|" + this.getCoordAbs()[1] + ")";
	}

	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public AffineTransform getMatrixNew()
	{
		return this.matrixNew;
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
	
	public AffineTransform getMatrixForLineA()
	{
//		return this.calculateMatrix(-1,30, -this.boxWidth/2, 0);
		return this.calculateMatrix(-1,this.angle, -this.cellWidth/2, 0);
	}
	
	public AffineTransform getMatrixForLineB()
	{
		return this.calculateMatrix(1,this.angle, -this.cellWidth/2, -this.cellHeight);
//		return this.calculateMatrix(1,30, -this.boxWidth/2, -this.boxHeight);
	}
	
	public AffineTransform getMatrixForLineC()
	{
		return this.calculateMatrix(-1,180+this.angle, -this.cellWidth/2, -this.cellHeight);
//		return this.calculateMatrix(-1,210, -this.boxWidth/2, -this.boxHeight);
	}
	
	public AffineTransform getMatrixForLineD()
	{
		//return this.calculateMatrix(1,210, -this.boxWidth/2, 0);
		return this.calculateMatrix(1,180+this.angle, -this.cellWidth/2, 0);
	}
	
	private AffineTransform calculateMatrix(int sign, double angleDegree, double startPointXOffset, double startPointYOffset)
	{
		double angleInRadian = Math.toRadians(angleDegree);
		
		AffineTransform matrixLine = new AffineTransform(matrix);
		matrixLine.translate(this.cellWidth, this.cellHeight);
		matrixLine.translate(startPointXOffset, startPointYOffset);
		matrixLine.rotate(sign*angleInRadian, 0, 0);
		
		return matrixLine;
	}
	
	public double[][] getPoints()
	{
		double[][] points = new double[4][2];
		
		int offset = 100;
		
		double aX =  this.absolutePosX +  + this.cellWidth/2 + offset;
		double aY = this.absolutePosY +offset;
		
		points[0][0] = aX;
		points[0][1] = aY;
		
		double bX = this.absolutePosX+offset;
		double bY = this.absolutePosY - this.cellHeight/2+offset;
		
		points[1][0] = bX;
		points[1][1] = bY;
		
		double cX = this.absolutePosX  + this.cellWidth/2+offset;
		double cY = this.absolutePosY - this.cellHeight+offset;
		
		points[2][0] = cX;
		points[2][1] = cY;
		
		double dX = this.absolutePosX  + this.cellWidth+offset;
		double dY = this.absolutePosY - this.cellHeight/2+offset;
		
		points[3][0] = dX;
		points[3][1] = dY;
		
		
		return points;
//			double xA = aT.getTranslateX()+this.model.getWidth()/2;
//			double yA = aT.getTranslateY();
//			
//			//B
//			
//			double xB = aT.getTranslateX()-this.model.getWidth()/2;
//			double yB = aT.getTranslateY()+this.model.getHeight()/2;
	}
	
	
	
	public double getLineLength()
	{
		return this.lineLength;
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
