package models;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import basics.ANTSFactory;

public class ANTSSimpleSourceLightModel extends ANTSAbstractModel implements ANTSIModel 
{
	private AffineTransform matrix;
	private double radius;
	
	private boolean on;
	private Color color;
	
	private int ticksBetweenTwoRays = 10;
	private int tickCounter;
	
	//RayProperties:
	private int numberOfRaysPer360Degrees = 18;
	private int angle = 360;
	private double angleOffset = 0;
	
	public ANTSSimpleSourceLightModel(double posX, double posY, double radius, Color color, boolean isMouseListener, ANTSFactory factory)
	{
		super(factory);
		
		this.matrix = new AffineTransform();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.radius = radius;
		
		this.on = false;		//TODO
		this.color = color;
		
		this.tickCounter = 0;
		
		this.isDragged = false;
		this.isMouseListener = isMouseListener;
	}
	
	///////////
	//Getters//
	///////////

	private double getAngleBetweetTwoRays()
	{	
		Double d = (double) this.numberOfRaysPer360Degrees;
		Double tmpAngle = 360/d;
		
		return tmpAngle;
	}
	
	public int getNumberOfRays()
	{
		return this.numberOfRaysPer360Degrees/(360/this.angle);
	}
	
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
	
	public void switchLight()
	{
		this.on = !this.on;
	}
	
	@Override
	public void update()
	{
		if(this.canSendRay())
		{
			this.createRays();
		}
	}
	
	private boolean canSendRay()
	{
		if(this.on)
		{
			if(this.tickCounter==0)
			{
				this.tickCounter++;
				return true;
			}
			else if(this.tickCounter<this.ticksBetweenTwoRays)
			{
				this.tickCounter++;
			}
			else
			{
				this.tickCounter = 0;
			}
			
			return false;
		}
		else
		{
			return false;
		}
	}
	
	private void createRays()
	{
		double tmpAngle = this.angleOffset;
		
		for(int numberRay = 0; numberRay<this.getNumberOfRays(); numberRay++)
		{
			this.factory.createSimpleRayLight(this.matrix, 10, tmpAngle, this.color);
			tmpAngle+=this.getAngleBetweetTwoRays();
		}
	}
	
//	private void createRays()
//	{
//		double tmpAngle = this.angleOffset;
//		
//		for(int numberRay = 0; numberRay<this.getNumberOfRays(); numberRay++)
//		{
//			//this.factory.createSimpleRayLight(this.matrix, 10, tmpAngle, this.color);
//			
//			Double[] center = {this.matrix.getTranslateX()-this.radius/2, this.matrix.getTranslateY()-this.radius/2};
//			
//			this.factory.createSimpleRayLight2(center,10,tmpAngle,this.color);
//			tmpAngle+=this.getAngleBetweetTwoRays();
//		}
//	}

}
