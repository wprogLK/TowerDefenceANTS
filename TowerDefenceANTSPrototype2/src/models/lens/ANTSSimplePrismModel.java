package models.lens;

import interfaces.ANTSIModel;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import models.medium.ANTSAbstractMediumModel;

import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

public class ANTSSimplePrismModel extends ANTSAbstractMediumModel implements ANTSIModel 
{
	private AffineTransform matrix;
	
	private double height = 100;
	private double width = 100;
	
	private double heightOfCorner;
	private double angle;
	private double angleOfCorner;
	
	private Point2D[] points;
	/*
	 * Only for debbuging and testing
	 */
	private double[] intersectionPoint;
	
	public ANTSSimplePrismModel(double posX, double posY, double height, double width, double heightOfCorner, double angleOfCorner, double angle, double refractionIndex, boolean isMouseListener, ANTSFactory factory)
	{
		super(true, refractionIndex, factory);
		
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.isMouseListener = isMouseListener;
		
		this.points = new Point2D.Double[3];
		
//		this.angleOfCorner = angleOfCorner; //Useless if height, width and height of the corner is given!
		this.angle = angle; //TODO AffineTransform for rotation!
		this.height = height;
		this.width = width;
		this.heightOfCorner = heightOfCorner;
		
		assert(heightOfCorner>=0 && heightOfCorner<=height);
		
		this.calculatePoints();
		
		/*
		 * Only for debbuging and testing
		 */
		this.intersectionPoint = new double[2];
		
		this.intersectionPoint[0] = Double.POSITIVE_INFINITY;
		this.intersectionPoint[1] = Double.POSITIVE_INFINITY;
	}
	
	///////////
	//Getters//
	///////////

	public final double[] getCenter()
	{
		this.center[0] = this.matrix.getTranslateX()+this.width/2;
		this.center[1] = this.matrix.getTranslateY()+this.height/2;
		
		return this.center;
	}
	
	/*
	 * Only for debbuging and testing
	 */
	public double[] getIntersectionPoint()
	{
		return this.intersectionPoint;
	}


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
	
	public double getHeightOfCorner()
	{
		return this.heightOfCorner;
	}
	
	public double getAngleOfCorner()
	{
		return this.angleOfCorner;
	}
	
	public Point2D[] getPoints()
	{
		return this.points;
	}
	///////////
	//Setters//
	///////////
	
	public void setPosition(int x, int y)
	{
		this.matrix.setToTranslation(x, y);
	}
	
	
	/*
	 * Only for debbuging and testing
	 */
	public void setPointOfIntersection(double[] point) 
	{
		this.intersectionPoint = point;
	}
	
	///////////
	//Special//
	///////////
	
	@Override
	public void update()
	{
		//TODO
	}
	
	private void calculatePoints()
	{
		Point2D.Double upperLeftCorner = new Point2D.Double(this.matrix.getTranslateX(), this.matrix.getTranslateY());
		Point2D.Double lowerLeftCorner = new Point2D.Double(this.matrix.getTranslateX(), this.matrix.getTranslateY()+this.height);
		Point2D.Double rightCorner = new Point2D.Double(this.matrix.getTranslateX()+this.width, this.matrix.getTranslateY()+this.heightOfCorner);
		
		this.points[0] = upperLeftCorner;
		this.points[1] = lowerLeftCorner;
		this.points[2] = rightCorner;
	}

	
}
