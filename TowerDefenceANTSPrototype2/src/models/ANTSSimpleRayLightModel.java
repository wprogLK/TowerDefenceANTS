package models;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

public class ANTSSimpleRayLightModel implements ANTSIModel
{
	private AffineTransform matrix;
	private AffineTransform velocityMatrix;
	private AffineTransform rotateMatrix;
	private AffineTransform sourceMatrix;
	
	private double length;
	private double velocity;
	private Color color;
	
	public ANTSSimpleRayLightModel(AffineTransform sourceMatrix, double velocity, double angle, Color sourceColor)
	{
		this.matrix = new AffineTransform();
		this.velocityMatrix = new AffineTransform();
		this.rotateMatrix = new AffineTransform();
		this.sourceMatrix = new AffineTransform();
		
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
