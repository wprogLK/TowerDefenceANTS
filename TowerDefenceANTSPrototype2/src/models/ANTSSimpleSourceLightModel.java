package models;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import basics.ANTSDriver;

import controllers.ANTSSimpleRayLightController;


public class ANTSSimpleSourceLightModel implements ANTSIModel 
{
	private AffineTransform matrix;
	private double radius;
	
	private boolean on;
	private Color color;
	
	//RayProperties:
	private int numberOfRaysPer360Degrees = 18;
	private int angle = 360;
	private double angleOffset = 0;
	
	
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
	
	//@Override
	public void update()
	{
		if(this.canSendRay())
		{
			this.createRays();
		}
	}
	
	
	private boolean canSendRay()
	{
		//TODO: improve this methode (@see prototye 1)
		return this.on;
	}
	
	private void createRays()
	{
		double tmpAngle = this.angleOffset;
		
		for(int numberRay = 0; numberRay<this.getNumberOfRays(); numberRay++)
		{
			ANTSDriver.createSimpleRayLight(this.matrix, 10, tmpAngle, this.color);
			
			tmpAngle+=this.getAngleBetweetTwoRays();
		}
	}
	
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
		System.out.println("X: " + this.matrix.getTranslateX() + " Y: " + this.matrix.getTranslateY());
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
