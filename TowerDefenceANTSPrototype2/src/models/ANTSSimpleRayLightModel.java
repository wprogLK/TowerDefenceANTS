package models;

import java.awt.Color;
import java.awt.geom.AffineTransform;

public class ANTSSimpleRayLightModel 
{
	private AffineTransform matrix;
	private AffineTransform velocityMatrix;
	private AffineTransform rotateMatrix;
	
	private double length;
	private double velocity;
	private Color color;
	
	public ANTSSimpleRayLightModel(AffineTransform sourceMatrix, double velocity, double angle, Color sourceColor)
	{
		this.matrix = sourceMatrix;
		this.velocityMatrix = new AffineTransform();
		this.rotateMatrix = new AffineTransform();
		
		this.rotateMatrix.rotate(Math.toRadians(angle), this.matrix.getTranslateX(), this.matrix.getTranslateY());
		
		this.length = 10;
		this.color = sourceColor;
	}
	

	//@Override
	public void update()
	{
		this.velocityMatrix.translate(this.velocity, 0);
		
		this.matrix.concatenate(this.rotateMatrix);
		this.matrix.concatenate(this.velocityMatrix);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
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
	
}
