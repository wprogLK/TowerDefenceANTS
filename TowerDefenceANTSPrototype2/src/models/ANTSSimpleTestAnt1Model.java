package models;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import basics.ANTSDriver;

public class ANTSSimpleTestAnt1Model extends ANTSAbstractModel implements ANTSIModel 
{
	private AffineTransform matrix;
	
	private boolean on;
	private Color color;
	
	private boolean isDragged;
	
	public ANTSSimpleTestAnt1Model() 
	{
		this.matrix = new AffineTransform();
		this.matrix.setToTranslation(100, 100);
		
		this.on = true;
		this.color = Color.yellow;
		
		this.isDragged = false;
		this.isMouseListener = true;
	}
	
	public ANTSSimpleTestAnt1Model(double posX, double posY, Color color)
	{
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.on = true;
		this.color = color;
		
		this.isDragged = false;
		this.isMouseListener = true;
	}
	
	public ANTSSimpleTestAnt1Model(double posX, double posY, Color color, boolean isMouseListener)
	{
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.on = true;
		this.color = color;
		
		this.isDragged = false;
		this.isMouseListener = isMouseListener;
	}
	
	@Override
	public void update()
	{

	}
	
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
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
		return "Model: ANT X: " + this.matrix.getTranslateX() + " Y: " + this.matrix.getTranslateY() + " COLOR: " + this.color;
	}
	
	public void setPosition(int x, int y)
	{
		this.matrix.setToTranslation(x, y);
	}
	
	public void setDragged(boolean isDragged)
	{
		this.isDragged = isDragged;
	}
	
	///////////
	//SPECIAL//
	///////////
	
	public void switchLight()
	{
		this.on = !this.on;
	}
}
