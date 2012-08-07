package models;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import basics.ANTSFactory;

public class ANTSSimpleRayLightModel extends ANTSAbstractModel implements ANTSIModel
{
	private AffineTransform matrix;
	private AffineTransform velocityMatrix;
	private AffineTransform rotateMatrix;
	private AffineTransform sourceMatrix;
	
	private AffineTransform matrixForInterpolation;
	
	private double length;
	private double velocity;
	private Color color;
	
	public ANTSSimpleRayLightModel(AffineTransform sourceMatrix, double velocity, double angle, Color sourceColor, ANTSFactory factory)
	{
		super(factory);
		
		this.matrix = new AffineTransform();
		this.velocityMatrix = new AffineTransform();
		this.rotateMatrix = new AffineTransform();
		this.sourceMatrix = new AffineTransform();
		
		this.matrixForInterpolation = new AffineTransform();
		
		this.matrix.setTransform(sourceMatrix);
		this.sourceMatrix.setTransform(sourceMatrix);
		
		this.rotateMatrix.rotate(Math.toRadians(angle), this.matrix.getTranslateX(), this.matrix.getTranslateY());
		
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
