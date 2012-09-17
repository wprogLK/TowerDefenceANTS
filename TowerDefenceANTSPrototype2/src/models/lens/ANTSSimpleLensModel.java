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
	
	private double radius;
	
	public ANTSSimpleLensModel(double posX, double posY, double radius, double refractionIndex, boolean isMouseListener, ANTSFactory factory)
	{
		super(true, refractionIndex, factory);
		
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.radius = radius;
		
		this.isMouseListener = isMouseListener;
	}
	
	///////////
	//Getters//
	///////////

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
	
	///////////
	//Special//
	///////////
	
	@Override
	public void update()
	{

	}
}
