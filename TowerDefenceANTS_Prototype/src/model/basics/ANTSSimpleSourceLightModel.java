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
