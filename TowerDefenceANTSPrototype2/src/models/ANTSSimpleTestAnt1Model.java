package models;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

public class ANTSSimpleTestAnt1Model extends ANTSAbstractModel implements ANTSIModel 
{
	private AffineTransform matrix;
	
	private Color color;
	
	private int width;
	private int height;
	
	public ANTSSimpleTestAnt1Model() 
	{
		this.matrix = new AffineTransform();
		this.matrix.setToTranslation(100, 100);
		
		this.width = 100;
		this.height = 100;
		
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
		
		this.color = color;
		
		this.isDragged = false;
		this.isMouseListener = isMouseListener;
	}
	
	///////////
	//Getters//
	///////////
	
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
	//Setters//
	///////////
	
	public void setPosition(int x, int y)
	{
		this.matrix.setToTranslation(x, y);
	}
	
	///////////
	//Special//
	///////////
}
