package models;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

public class ANTSSimpleSourceLightModel implements ANTSIModel 
{
	private AffineTransform matrix;
	private double radius;
	
	private boolean on;
	private Color color;
	
	public ANTSSimpleSourceLightModel() 
	{
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(100, 100);
		this.radius = 50;
		
		this.on = true;
		this.color = Color.yellow;
	}
	
	public ANTSSimpleSourceLightModel(double posX, double posY, double radius, Color color)
	{
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(posX, posY);
		this.radius = radius;
		
		this.on = true;
		this.color = color;
	}
	
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
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
	
	public boolean isOn()
	{
		return this.on;
	}
	
	///////////
	//SPECIAL//
	///////////
	
	public void switchLight()
	{
		this.on = !this.on;
	}

}
