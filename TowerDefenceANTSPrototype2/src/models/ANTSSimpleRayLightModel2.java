package models;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

public class ANTSSimpleRayLightModel2 extends ANTSAbstractModel implements ANTSIModel
{
	private AffineTransform matrix;
	private AffineTransform velocityMatrix;
	private AffineTransform rotateMatrix;
	private AffineTransform sourceMatrix;
	
	private AffineTransform matrixForInterpolation;
	
	private double length;
	private double velocity;
	private double[] center;
	private double angle;
	private Color color;
	
	public ANTSSimpleRayLightModel2(double[] center, double velocity, double angle, Color sourceColor, ANTSFactory factory)
	{
		super(factory);
		
		this.isCollisionDetected = true;
		
		this.matrix = new AffineTransform();
		this.velocityMatrix = new AffineTransform();
		this.rotateMatrix = new AffineTransform();
		this.sourceMatrix = new AffineTransform();
		
		this.matrixForInterpolation = new AffineTransform();
		
		this.matrix.translate(center[0], center[1]);
		this.sourceMatrix.translate(center[0], center[1]);
		
		this.rotateMatrix.rotate(Math.toRadians(angle), 0,0);
		
		this.angle = angle;
		this.center = center;
		this.length = 100;
		this.color = sourceColor;
		this.velocity = velocity;
	}
	
	@Override
	public void update()
	{
		this.matrix.setTransform(this.sourceMatrix);
		
		this.velocityMatrix.translate(this.velocity, 0);
		
		this.matrix.concatenate(this.rotateMatrix);
		this.matrix.concatenate(this.velocityMatrix);
	}
	
	/**
	 * its a pseudo update for interpolation
	 * to get the pseudo result use the get...Interpolation() method!
	 * @param interpolation
	 */
	public void update(float interpolation)
	{
		this.matrixForInterpolation.setTransform(this.sourceMatrix);
		
		AffineTransform tmpVelocityMatrix = new AffineTransform(this.velocityMatrix);
		tmpVelocityMatrix.translate(this.velocity*interpolation, 0);
		
		this.matrixForInterpolation.concatenate(this.rotateMatrix);
		this.matrixForInterpolation.concatenate(tmpVelocityMatrix);	
	}
	
	///////////
	//Getters//
	///////////
	
	public AffineTransform getInterpolationMatrix()
	{
		return this.matrixForInterpolation;
	}
	
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
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	
	
}
