package models;

import interfaces.ANTSIModel;

import java.awt.geom.AffineTransform;

import basics.ANTSFactory;

public class ANTSCircleMenuModel extends ANTSAbstractModel implements ANTSIModel 
{
	private AffineTransform matrix;
	private double radius;
	private boolean mouseEntered;
	
	public ANTSCircleMenuModel(double posX, double posY, double radius, ANTSFactory factory )
	{
		super(factory);
		
		this.matrix = new AffineTransform();
		this.matrix.translate(posX, posY);
		
		this.radius = radius;
		
		this.isMouseListener = true;
		this.mouseEntered = false;
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public String toString()
	{
		return "circle menu";
	}

	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public double getRadius()
	{
		return this.radius;
	}

	public void setMouseEntered(boolean value) 
	{
		this.mouseEntered = value;
	}

	public boolean getMouseEntered() 
	{
		return this.mouseEntered;
	}
	
	///////////
	//SPECIAL//
	///////////
	
}
