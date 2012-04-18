/**
 * 
 */
package model.basics;

import java.awt.Color;

/**
 * @author Lukas
 *
 */
public class ANTSSimpleSourceLightModel 
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
		this.turnOn();
		
		this.radius = 10;
		this.posX = 200;
		this.posY = 200;
		this.timeBetweenRays=1000;
		this.timeCounter = 0;
		this.angle = 180;
		this.numberOfRaysPer360Degree = 18;
		this.angleOffset = 0;
		
		this.lightColor = Color.BLUE;
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
	
}
