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
	
	private int offsetX = 60;
	private int offsetY = 60;
	
	private int offsetCellX = 45;
	private int offsetCellY = 0;
	
	private double absolutePosX; //pos in pix
	private double absolutePosY; //pos in pix
	
	private double defaultHeight = 60;
	private double defaultWidth = 60;
	
	/**
	 * in degrees!
	 */
	private double defaultAngle = 30;	//Angle between x-Axis and "line"
	
	/**
	 * in degrees!
	 */
	private double angle;

	private double height;
	private double width;
	
	private double lineLength;
	private double lineLengthOffset = 24;	
	
	private AffineTransform matrix;
	
	public ANTSCellModel(int cellNrX, int cellNrY) 
	{
		this.isMouseListener = true;
		
		this.views = new ArrayList<ANTSIView>();
		this.models = new ArrayList<ANTSIModel>();
		this.controllers = new ArrayList<ANTSIController>();
		
		this.height = this.defaultHeight;
		this.width = this.defaultWidth;
		this.angle = this.defaultAngle;
		
		this.relativePosX = cellNrX;
		this.relativePosY = cellNrY;
		
		this.absolutePosX = this.width * cellNrX + this.offsetX + this.offsetCellX * cellNrX;
		this.absolutePosY = this.height * cellNrY + this.offsetY + this.offsetCellY * cellNrY;
		
		this.lineLength = Math.sqrt(this.height*this.height+this.width*this.width)-this.lineLengthOffset;
		
		this.matrix = new AffineTransform();
		this.matrix.setToTranslation(this.absolutePosX, this.absolutePosY);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public String toString()
	{
		return "CellModel";
	}

	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public AffineTransform getMatrixForLineA()
	{
		return this.calculateMatrix(-1,30, -this.width/2, 0);
	}
	
	public AffineTransform getMatrixForLineB()
	{
		return this.calculateMatrix(1,30, -this.width/2, -this.height);
	}
	
	public AffineTransform getMatrixForLineC()
	{
		return this.calculateMatrix(-1,210, -this.width/2, -this.height);
	}
	
	public AffineTransform getMatrixForLineD()
	{
		return this.calculateMatrix(-1,150, -this.width/2, 0);
	}
	
	private AffineTransform calculateMatrix(int sign, int angleDegree, double startPointXOffset, double startPointYOffset)
	{
		double angleInRadian = Math.toRadians(angleDegree);
		
		AffineTransform matrixLine = new AffineTransform(matrix);
		matrixLine.translate(this.width, this.height);
		
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
