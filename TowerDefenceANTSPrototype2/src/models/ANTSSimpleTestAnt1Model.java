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
	
	private int width;
	private int height;
	
	public ANTSSimpleTestAnt1Model() 
	{
		this.matrix = new AffineTransform();
		this.matrix.setToTranslation(100, 100);
		
		this.width = 100;
		this.height = 100;
		
		this.on = true;
		this.color = Color.yellow;
		
		this.isDragged = false;
		this.isMouseListener = true;
	}
	
	public ANTSSimpleTestAnt1Model(double posX, double posY, Color color)
	{
		this.matrix = new AffineTransform();
		this.matrix.setToTranslation(posX, posY);
		
		this.width = 100;
		this.height = 100;
		
		this.on = true;
		this.color = color;
		
		this.isDragged = false;
		this.isMouseListener = true;
	}
	
	public ANTSSimpleTestAnt1Model(double posX, double posY, int width, int height, Color color, boolean isMouseListener)
	{
		this.matrix = new AffineTransform();
		this.matrix.setToTranslation(posX, posY);
		
		this.width = width;
		this.height = height;
		
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
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
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
	
	/**
	 * calculates an affineTransform with an additional scaling factor for the image
	 * @param widthImage
	 * @param heightImage
	 * @return
	 */
	public AffineTransform getAffineTransformForImage(int widthImage, int heightImage)
	{
		AffineTransform aTImage = new AffineTransform(matrix);
		
		float dw = (float) this.width/widthImage;
		float dh = (float) this.height/heightImage;
		
		aTImage.scale(dw,dh);
		
		return aTImage;
	}
	
	///////////
	//SPECIAL//
	///////////
	
	public void switchLight()
	{
		this.on = !this.on;
	}
}
