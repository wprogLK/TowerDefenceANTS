package models.medium;

import interfaces.ANTSIModel;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import basics.ANTSFactory;

public class ANTSSimpleMediumModel extends ANTSAbstractMediumModel implements ANTSIModel 
{
	private AffineTransform matrix;
	
	private double height = 100;
	private double width = 100;
	
	private Point2D.Double pointA;
	private Point2D.Double pointB;
	private Point2D.Double pointC;
	private Point2D.Double pointD;
	
	private double[] intersectionPointRay;
	private double[] intersectionPointBox;

	private double[] theIntersectionPoint;
	
	public ANTSSimpleMediumModel(double posX, double posY, double height, double width, double refractionIndex, boolean isMouseListener, ANTSFactory factory)
	{
		super(true, refractionIndex, factory);
		
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.height = height;
		this.width = width;
		
		this.pointA = new Point2D.Double(posX, posY);
		this.pointB = new Point2D.Double(posX, posY+height);
		this.pointC = new Point2D.Double(posX+width, posY+height);
		this.pointD = new Point2D.Double(posX+width, posY);
		
		this.isMouseListener = isMouseListener;
		
		this.intersectionPointBox = new double[2];
		this.intersectionPointBox[0] = -1;
		this.intersectionPointBox[1] = -1;
		
		this.intersectionPointRay = new double[2];
		this.intersectionPointRay[0] = -1;
		this.intersectionPointRay[1] = -1;
		
		this.theIntersectionPoint = new double[2];
		this.theIntersectionPoint[0] = -1;
		this.theIntersectionPoint[1] = -1;
	}
	
	///////////
	//Getters//
	///////////

	@Override
	public boolean isDragged()
	{
		return this.isDragged;
	}
	
	public double getPosX()
	{
		return this.matrix.getTranslateX();
	}

	public double getPosY()
	{
		return this.matrix.getTranslateY();
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
		
	@Override
	public String toString()
	{
		return "Model simple medium";
	}
	
	/**
	 * first point is startPoint, second point is endPoint
	 * @return
	 */
	public Point2D.Double[] getVectorA()
	{
		Point2D.Double[] vector = {this.pointA,this.pointD};
		return vector;
	}
	
	/**
	 * first point is startPoint, second point is endPoint
	 * @return
	 */
	public Point2D.Double[] getVectorB()
	{
		Point2D.Double[] vector = {this.pointD,this.pointC};
		return vector;
	}
	
	/**
	 * first point is startPoint, second point is endPoint
	 * @return
	 */
	public Point2D.Double[] getVectorC()
	{
		Point2D.Double[] vector = {this.pointC,this.pointB};
		return vector;
	}
	
	/**
	 * first point is startPoint, second point is endPoint
	 * @return
	 */
	public Point2D.Double[] getVectorD()
	{
		Point2D.Double[] vector = {this.pointB,this.pointA};
		return vector;
	}
	
	public double[] getIntersectionPointRay()
	{
		return this.intersectionPointRay;
	}
	
	
	public double[] getIntersectionPointBox()
	{
		return this.intersectionPointBox;
	}
	
	public double[] getTheIntersectionPoint()
	{
		return this.theIntersectionPoint;
	}
	///////////
	//Setters//
	///////////
	
	public void setPosition(int x, int y)
	{
		this.matrix.setToTranslation(x, y);
	}
	
	//Only for debugging
	public void setTheIntersectionPoint(double[] closestIntersectionPoint) 
	{
		this.theIntersectionPoint = closestIntersectionPoint;
	}
	
	///////////
	//Special//
	///////////
	
	@Override
	public void update()
	{

	}

	public void setIntersectionPoint(double[] intersectionPointBox, double[] intersectionPointRay) 
	{
		this.intersectionPointBox = intersectionPointBox;
		this.intersectionPointRay = intersectionPointRay;
	}


}
