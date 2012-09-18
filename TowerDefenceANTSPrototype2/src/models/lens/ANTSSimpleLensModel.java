package models.lens;

import interfaces.ANTSIModel;

import java.awt.geom.AffineTransform;

import models.medium.ANTSAbstractMediumModel;

import basics.ANTSFactory;

public class ANTSSimpleLensModel extends ANTSAbstractMediumModel implements ANTSIModel 
{
	private AffineTransform matrix;
	
	private double height = 100;
	private double width = 100;
	
	private double radius; //TODO: Attention this is not the radius, it's the diameter!!
	
	/*
	 * Only for debbuging and testing
	 */
	private double[] intersectionPoint1;
	private double[] intersectionPoint2;
	private double[] theIntersectionPoint;
	
	public ANTSSimpleLensModel(double posX, double posY, double radius, double refractionIndex, boolean isMouseListener, ANTSFactory factory)
	{
		super(true, refractionIndex, factory);
		
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.radius = radius;
		
		this.isMouseListener = isMouseListener;
		
		
		/*
		 * Only for debbuging and testing
		 */
		this.intersectionPoint1 = new double[2];
		this.intersectionPoint2 = new double[2];
		this.theIntersectionPoint = new double[2];
		
		this.intersectionPoint1[0] = Double.POSITIVE_INFINITY;
		this.intersectionPoint1[1] = Double.POSITIVE_INFINITY;
		
		this.intersectionPoint2[0] = Double.POSITIVE_INFINITY;
		this.intersectionPoint2[1] = Double.POSITIVE_INFINITY;
		
		this.theIntersectionPoint[0] = Double.POSITIVE_INFINITY;
		this.theIntersectionPoint[1] = Double.POSITIVE_INFINITY;
	}
	
	///////////
	//Getters//
	///////////

	public final double[] getCenter()
	{
		this.center[0] = this.matrix.getTranslateX()+this.radius/2;
		this.center[1] = this.matrix.getTranslateY()+this.radius/2;
		
		return this.center;
	}
	
	/*
	 * Only for debbuging and testing
	 */
	public double[] getPointOfIntersection1()
	{
		return this.intersectionPoint1;
	}
	
	/*
	 * Only for debbuging and testing
	 */
	public double[] getPointOfIntersection2()
	{
		return this.intersectionPoint2;
	}
	
	/*
	 * Only for debbuging and testing
	 */
	public double[] getThePointOfIntersection()
	{
		return this.theIntersectionPoint;
	}


	
	public double getRadius()
	{
		return this.radius;
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
	public void setPointsOfIntersection(double x_1, double y_1, double x_2, double y_2) 
	{
		this.intersectionPoint1[0] = x_1;
		this.intersectionPoint1[1] = y_1;
		
		this.intersectionPoint2[0] = x_2;
		this.intersectionPoint2[1] = y_2;
	}
	
	/*
	 * Only for debbuging and testing
	 */
	public void setThePointOfIntersection(double[] point) 
	{
		this.theIntersectionPoint = point;
	}
	
	///////////
	//Special//
	///////////
	
	@Override
	public void update()
	{

	}

	
}
