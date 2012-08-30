package models.medium;

import interfaces.ANTSIModel;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import controllers.ANTSSimpleRayLightController;

import basics.ANTSFactory;

public class ANTSSimpleMediumModel extends ANTSAbstractMediumModel implements ANTSIModel 
{
	private AffineTransform matrix;
	
	private double height = 100;
	private double width = 100;
	
	private ArrayList<ANTSSimpleRayLightController> rays;
	
	
	public ANTSSimpleMediumModel(double posX, double posY, double height, double width, boolean isMouseListener, ANTSFactory factory)
	{
		super(true,factory);
		
		this.matrix = new AffineTransform();
		this.rays = new ArrayList<ANTSSimpleRayLightController>();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.height = height;
		this.width = width;
		
		this.isMouseListener = isMouseListener;
	}
	
	///////////
	//Getters//
	///////////

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
