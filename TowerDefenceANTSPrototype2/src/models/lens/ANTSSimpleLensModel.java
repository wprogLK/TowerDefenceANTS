package models.lens;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import models.ANTSAbstractModel;

import basics.ANTSFactory;

public class ANTSSimpleLensModel extends ANTSAbstractModel implements ANTSIModel 
{
	private AffineTransform matrix;
	private double radius;
	
	private Color color;

	
	public ANTSSimpleLensModel(double posX, double posY, double radius, Color color, boolean isMouseListener, ANTSFactory factory)
	{
		super(factory);
		
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.radius = radius;
		
		this.color = color;
		
		this.isMouseListener = isMouseListener;
	}
	
	///////////
	//Getters//
	///////////

	
	public double getPosX()
	{
		return this.matrix.getTranslateX();
	}

	public double getPosY()
	{
		return this.matrix.getTranslateY();
	}
	
	public double getRadius()
	{
		return this.radius;
	}
	
	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public String toString()
	{
		return "Model: X: " + this.matrix.getTranslateX() + " Y: " + this.matrix.getTranslateY() + " Radius : " +this.radius + " COLOR: " + this.color;
	}
	
	///////////
	//Setters//
	///////////
	
	public void setPosition(int x, int y)
	{
		this.matrix.setToTranslation(x, y);
	}
	
	///////////
	//Special//
	///////////
	
	@Override
	public void update()
	{

	}
	

}
