package models;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import basics.ANTSFactory;

public class ANTSSimpleRayLightModel2 extends ANTSAbstractModel implements ANTSIModel
{
	private AffineTransform matrix;
	
	private AffineTransform matrixForInterpolation;
	
	private double length;
	private double velocity;
	private Color color;
	
	public ANTSSimpleRayLightModel2(Double[] center, double velocity, double angle, Color sourceColor, ANTSFactory factory)
	{
		super(factory);
		
		this.isCollisionDetected = true;
		
		this.matrix = new AffineTransform();
		
		this.matrix.translate(center[0], center[1]);
		
		this.matrix.rotate(Math.toRadians(angle),center[0],center[1]);
		this.matrixForInterpolation = new AffineTransform();
	
		
		this.length = 10;
		this.color = sourceColor;
		this.velocity = velocity;
	}
	
	@Override
	public void update()
	{
		this.matrix.translate(0, this.velocity);
	}
	
	/**
	 * its a pseudo update for interpolation
	 * to get the pseudo result use the get...Interpolation() method!
	 * @param interpolation
	 */
	public void update(float interpolation)
	{
		
//		this.matrixForInterpolation.setTransform(this.sourceMatrix);
//		
//		AffineTransform tmpVelocityMatrix = new AffineTransform(this.velocityMatrix);
//		tmpVelocityMatrix.translate(this.velocity*interpolation, 0);
//		
//		this.matrixForInterpolation.concatenate(this.rotateMatrix);
//		this.matrixForInterpolation.concatenate(tmpVelocityMatrix);
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
