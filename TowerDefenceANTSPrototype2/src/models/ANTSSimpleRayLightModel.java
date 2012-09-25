package models;

import interfaces.ANTSIModel;
import interfaces.medium.ANTSIMediumController;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import controllers.ANTSSimpleRayLightController;

import basics.ANTSFactory;
import basics.ANTSUtility;

public class ANTSSimpleRayLightModel extends ANTSAbstractModel implements ANTSIModel
{
	private AffineTransform matrix;
	private AffineTransform velocityMatrix;
	private AffineTransform sourcePivotRotateMatrix;
	private AffineTransform sourceMatrix;
	private AffineTransform localRotateMatrix;
	
	private AffineTransform matrixForInterpolation;
	
	private double length;
	private double velocity;
	private double angle;
	private Color color;
	
	//Vector
	private Point2D.Double start;
	private Point2D.Double end;
	
	
	//equation r = a + tv
	private double a_1;
	private double a_2;
	
	private double r_1;
	private double r_2;
	
	private double v_1;
	private double v_2;
	
	private ANTSIMediumController medium;
	
	public ANTSSimpleRayLightModel(double[] center, double velocity, double angle, Color sourceColor, ANTSFactory factory)
	{
		super(factory);
		
		this.isCollisionDetected = true;
		
		this.matrix = new AffineTransform();
		this.velocityMatrix = new AffineTransform();
		this.sourcePivotRotateMatrix = new AffineTransform();
		this.sourceMatrix = new AffineTransform();
		this.localRotateMatrix = new AffineTransform();
		
		this.matrixForInterpolation = new AffineTransform();
		
		this.matrix.translate(center[0], center[1]);
		this.sourceMatrix.translate(center[0], center[1]);
		this.matrixForInterpolation.translate(center[0], center[1]);
		
		this.sourcePivotRotateMatrix.rotate(Math.toRadians(angle), 0,0);
		
		this.angle = angle;
		this.center = center;
		this.length = 5;
		this.color = sourceColor;
		this.velocity =velocity;
		
		this.medium = factory.createStandardMediumController();
		
		this.matrix.concatenate(this.sourcePivotRotateMatrix);
		this.matrixForInterpolation.concatenate(this.sourcePivotRotateMatrix);
		
		this.updateVector();
		
		this.classInvariant();
	}
	
	@Override
	protected void classInvariant()
	{
		assert(this.angle>=0 && this.angle<360);
	}
	
	private void updateVector() 
	{
		this.start = new Point2D.Double(0, 0);
		this.end = new Point2D.Double(this.length, 0);
		
		this.matrix.transform(this.start, this.start);
		this.matrix.transform(this.end, this.end);
	}

	@Override
	public void update()
	{	
		this.velocityMatrix = new AffineTransform();
		this.velocityMatrix.translate(this.velocity, 0);
		
		this.matrix.concatenate(this.velocityMatrix);
	}
	
	/**
	 * its a pseudo update for interpolation
	 * to get the pseudo result use the get...Interpolation() method!
	 * @param interpolation
	 */
	public void update(float interpolation)
	{
		this.velocityMatrix = new AffineTransform();
		this.velocityMatrix.translate(this.velocity*interpolation, 0);
		this.matrix.concatenate(this.velocityMatrix);
	}
	
	///////////
	//Getters//
	///////////
	
	@Override
	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public double getLength()
	{
		return this.length;
	}
	
	public double getAngle()
	{
		this.classInvariant();
		return this.angle;
	}

	public ANTSIMediumController getCurrentMedium()
	{
		return this.medium;
	}
	
	/**
	 * first point is startPoint, second point is endPoint
	 * @return
	 */
	public Point2D.Double[] getVector()
	{
		this.updateVector();
		
		Point2D.Double[] vec = {this.start,this.end};
		
		return vec;
	}
	
	///////////
	//Setters//
	///////////
	
	public void setCurrentMedium(ANTSIMediumController c) 
	{
		this.medium = c;
	}
	
	public void setAngle(double angle) 
	{
		this.classInvariant();
		
		this.addAngle(-this.angle);		//Reset matrix
		this.addAngle(ANTSUtility.angleBetween0And359Degree(angle));
		this.classInvariant();
	}
	
	public void setIsAlreadyUpdated(boolean b)
	{
		this.isAlreadyUpdated = b;
	}
	
	///////////
	//Special//
	///////////
	
	public void addAngle(double angle) 
	{
		this.classInvariant();
		this.angle+=angle;
		
		this.angle = ANTSUtility.angleBetween0And359Degree(this.angle);
		
		this.localRotateMatrix = new AffineTransform();
		
		this.localRotateMatrix.rotate(Math.toRadians(angle));
		
		this.matrix.concatenate(this.localRotateMatrix);
		this.matrixForInterpolation.concatenate(this.localRotateMatrix);
		this.classInvariant();
	}
}
