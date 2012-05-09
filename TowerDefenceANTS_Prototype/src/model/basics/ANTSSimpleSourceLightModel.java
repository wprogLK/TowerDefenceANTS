/**
 * 
 */
package model.basics;

import helper.ANTSPainter;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Color;
import java.util.ArrayList;

import model.abstracts.ANTSModelAbstract;

import controllers.basics.ANTSSimpleRayLightController;

/**
 * @author Lukas
 *
 */
public class ANTSSimpleSourceLightModel extends ANTSModelAbstract implements ANTSIModel
{
	private boolean isOn;

	private int radius;
	private int posX;
	private int posY;
	private int angle;
	private int numberOfRaysPer360Degree;
	private int angleBetweetTwoRays;
	private int angleOffset;
	
	private int timeBetweenRays;
	private int timeCounter;
	
	private Color lightColor;
	
	public ANTSSimpleSourceLightModel()
	{
		super();
		
		this.turnOn();
		
		this.radius = 10;
		this.posX = 150;
		this.posY = 320;
		this.timeBetweenRays=10; //10
		this.timeCounter = 0;
		this.angle = 360;
		this.numberOfRaysPer360Degree = 18;
		this.angleOffset = 0;
		
		this.lightColor = Color.RED;
	}
	
	public void turnOn()
	{
		this.resetTimerCounter();
		this.isOn = true;
	}
	
	public void turnOff()
	{
		this.resetTimerCounter();
		this.isOn = false;
	}
	
	public void switchLight()
	{
		this.resetTimerCounter();
		this.isOn = !this.isOn;
	}
	
	public boolean isOn()
	{
		return isOn;
	}
	
	public int getRadius()
	{
		return this.radius;
	}
	
	public int getPosX()
	{
		return this.posX;
	}
	
	public int getPosY()
	{
		return this.posY;
	}
	
	public Color getLightColor()
	{
		return this.lightColor;
	}
	
	public double getAngleBetweetTwoRays()
	{	
		Double d = (double) this.numberOfRaysPer360Degree;
		Double tmpAngle = 360/d;
		
		return tmpAngle;
	}
	
	public int getEndAngle()
	{
		return this.angle+this.angleOffset;
	}
	
	public int getNumberOfRays()
	{
		return this.numberOfRaysPer360Degree/(360/this.angle);
	}
	
	public int getOffsetAngle()
	{
		return this.angleOffset;
	}
	
	public boolean canSendRay()
	{
		if(this.isOn)
		{
			if(this.timeCounter==0)
			{
				this.timeCounter++;
				return true;
			}
			else if(this.timeCounter==this.timeBetweenRays)
			{
				this.resetTimerCounter();
				return false;
			}
			else
			{
				this.timeCounter++;
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	private void resetTimerCounter()
	{
		this.timeCounter=0;
	}

	@Override
	public void update() {
		if(this.canSendRay())
		{
			this.createRays();
		}
	}
	
	private void createRays()
	{
		double angle = this.getOffsetAngle();
		
		for(int numberRay = 0; numberRay<this.getNumberOfRays(); numberRay++)
		{
			ANTSSimpleRayLightController simpleRayLightController = new ANTSSimpleRayLightController(this,angle);
			
			angle+=this.getAngleBetweetTwoRays();
		}
	}
}
