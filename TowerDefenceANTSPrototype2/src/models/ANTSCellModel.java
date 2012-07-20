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
	
	private int offsetX;
	private int offsetY;
	
	public static double offsetCellX = 0;	//Space "between" to cells	//don't know why it is 44
	private static final int offsetCellY = 0;	//Space "between" to cells
	
	private double absolutePosX; //pos in pix
	private double absolutePosY; //pos in pix
	
	private double defaultHeight = 30;
	private double defaultWidth = 30;
	
	/**
	 * in degrees!
	 */
	private double defaultAngle = 30;	//Angle between x-Axis and "line"
	
	/**
	 * in degrees!
	 */
	private double angle;

	private double angleIn;
	
	private double cellHeight;
	private double cellWidth;
	
	private double a;
	private double b;
	private double c;
	
	private double lineLength;
	
	private AffineTransform matrix;
	
	
	
	public ANTSCellModel(int cellNrX, int cellNrY, int shiftHalf, int xOffset, int yOffset )
	{
		init(cellNrX, cellNrY, xOffset, yOffset);
		
		
		
		
	
		
		this.shiftY(cellNrY);
		
		this.lineLength = Math.sqrt(this.cellHeight/2*this.cellHeight/2+this.cellWidth/2*this.cellWidth/2); //don't ask me why it's only + this.cellWidth (and not + cellWidth²) 
		this.lineLength = this.cellHeight/2*1/Math.sin(30);
		this.matrix = new AffineTransform();
		
		this.calc();
		this.absolutePosX = this.cellWidth * cellNrX + this.offsetY;
		this.absolutePosY = this.cellHeight * cellNrY +this.offsetY;
		this.matrix.setToTranslation(this.absolutePosX, this.absolutePosY);
		
	}
	
	private void calc() 
	{
		this.c = this.b/Math.sin(angleIn);
		this.a = Math.sqrt(this.c*this.c-b*b);//Math.sqrt((b*b)/(Math.sin(angleIn)*Math.sin(angleIn)) - (b*b));
		
		this.cellWidth = a*2;
		this.lineLength = c;
		
		this.offsetCellX = a;
	}

	private void shiftY(int cellNrY) 	//TODO this is a working solution, but not the best one. Try to find a better solution!
	{
//		if(cellNrY<=1)
//		{
//			//do nothing
//		}
//		else if(cellNrY<=3)
//		{
//			this.absolutePosY-=this.cellHeight*1;
//		}
//		else if(cellNrY<=5)
//		{
//			this.absolutePosY-=this.cellHeight*2;
//		}
//		else if(cellNrY<=7)
//		{
//			this.absolutePosY-=this.cellHeight*3;
//		}
//		else if(cellNrY<=9)
//		{
//			this.absolutePosY-=this.cellHeight*4;
//		}
		
	}

	private void init(int cellNrX, int cellNrY, int xOffset, int yOffset)
	{
		this.isMouseListener = true;
		
		this.views = new ArrayList<ANTSIView>();
		this.models = new ArrayList<ANTSIModel>();
		this.controllers = new ArrayList<ANTSIController>();
		
		this.cellHeight = this.defaultHeight;
		this.cellWidth = this.defaultWidth;
		
		this.cellHeight = this.cellHeight;
		this.cellWidth = this.cellWidth;
		
		this.angle = this.defaultAngle;
		this.angleIn = Math.toRadians(angle);//this.defaultAngle;
		
		this.relativePosX = cellNrX;
		this.relativePosY = cellNrY;
		
		this.offsetX = xOffset;
		this.offsetY = yOffset;
		
		this.b = this.cellHeight/2;
		
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
		return " Cell (" + this.getCoord()[0] + " | " + this.getCoord()[1] + " ) ";
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
