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

public class ANTSCellModel extends ANTSAbstractModel implements ANTSIModel 
{
	private ArrayList<ANTSIView> views;
	private ArrayList<ANTSIModel> models;
	private ArrayList<ANTSIController> controllers;
	
	private int relativePosX;	//nr of the cell
	private int relativePosY;	//nr of the cell
	
	private int gridOffsetX =60;
	private int gridOffsetY = 60;
	
	private double absolutePosX; //pos in pix
	private double absolutePosY; //pos in pix
	
	private double defaultHeight = 60;
	private double defaultWidth = 60;
	
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
	
	
	
	public ANTSCellModel(int cellNrX, int cellNrY, int shiftHalf, int xGridOffset, int yGridOffset )
	{
		init(cellNrX, cellNrY, xGridOffset, yGridOffset);
		
		this.matrix = new AffineTransform();
		
		this.calc();
		
		this.shift = shiftHalf;
		
		this.absolutePosX = this.cellWidth * cellNrX + this.gridOffsetX;
		this.absolutePosY = this.cellHeight * cellNrY+ this.gridOffsetY;
		
		
		this.absolutePosY = this.cellHeight*this.relativePosY/2;
		this.absolutePosX+=this.cellWidth/2*this.shift;
		
		
		this.matrix.setToTranslation(this.absolutePosX, this.absolutePosY);
		
	}
	

	private void calc() 
	{
		double angleInRadian = Math.toRadians(this.angle);
		
		double halfeCellHeight = this.cellHeight/2;
		
		this.lineLength = halfeCellHeight/Math.sin(angleInRadian);
		this.cellWidth = 2*Math.sqrt(this.lineLength*this.lineLength-halfeCellHeight*halfeCellHeight);
		
	}


	private void init(int cellNrX, int cellNrY, int xGridOffset, int yGridOffset)
	{
		this.isMouseListener = false;
		
		this.views = new ArrayList<ANTSIView>();
		this.models = new ArrayList<ANTSIModel>();
		this.controllers = new ArrayList<ANTSIController>();
		
		this.cellHeight = this.defaultHeight;
		this.cellWidth = this.defaultWidth;
		
		this.angle = this.defaultAngle;
		
		this.relativePosX = cellNrX;
		this.relativePosY = cellNrY;
		
		this.gridOffsetX = xGridOffset;
		this.gridOffsetY = yGridOffset;
		
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public int[] getCoord()
	{
		int[] coord = {this.relativePosX, this.relativePosY};
		return coord;
	}
	
	public String toString()
	{
		return "(" + this.getCoord()[0] + "|" + this.getCoord()[1] + ") shift: " + this.shift;
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
		return this.cellWidth;
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
	
//	private double[] rotatePoint(int sign, int angleDegree, double startPointXOffset, double startPointYOffset)
//	{
//		double angleInRadian = Math.toRadians(angleDegree);
//		AffineTransform matrixLine = new AffineTransform(matrix);
//		
//		matrixLine.translate(startPointXOffset + this.absolutePosX-this.offsetX, startPointYOffset + this.absolutePosY-this.offsetY);
//		matrixLine.translate(this.lineLength, 0);
//		matrixLine.rotate(sign*angleInRadian, 0, 0);
//		
//		double[] point = {matrixLine.getTranslateX(), matrixLine.getTranslateY()};
//		
//		return point;
//	}
//	
//	public double[] getPointA()
//	{
//		double [] p = {this.matrix.getTranslateX() - this.width/2, this.matrix.getTranslateY() - 0};
//		return p;
////		return this.calculateMatrix(-1,30, -this.width/2, 0);
//	}
//	
//	public double[] getPointB()
//	{
//		return this.rotatePoint(1, 30,  -this.width/2, -this.height);
//		//return this.calculateMatrix(1,30, -this.width/2, -this.height);
//	}
//	
//	public double[] getPointC()
//	{
//		double [] p = {this.matrix.getTranslateX() - this.width/2, this.matrix.getTranslateY() - this.height};
//		return p;
////		return this.calculateMatrix(-1,210, -this.width/2, -this.height);
//	}
//	
//	public double[] getPointD()
//	{
//		return this.rotatePoint(-1,150, -this.width/2, 0);
////		return this.calculateMatrix(-1,150, -this.width/2, 0);
//	}
	
	public double getLineLength()
	{
		return this.lineLength;
	}
	
	///////////
	//SPECIAL//
	///////////
	public void addView(ANTSIView iView) 
	{
		if(!this.views.contains(iView))
		{
			this.views.add(iView);
		}
	}

	public void addModel(ANTSIModel model) 
	{
		if(!this.models.contains(model))
		{
			this.models.add(model);
		}
	}

	public void addController(ANTSIController controller)
	{
		if(!this.controllers.contains(controller))
		{
			this.controllers.add(controller);
		}
	}
	
}
