/**
 * 
 */
package model.basics;

import java.awt.Color;

/**
 * @author Lukas
 *
 */
public class ANTSSimpleRayLightModel 
{
	private int velocity;
	private int posX;
	private int posY;
	private int lenght;
	private double angle;
	
	private int sourcePosX;
	private int sourcePosY;
	private Color sourceLightColor;
	
	
	public ANTSSimpleRayLightModel(int sourcePosX, int sourcePosY, double startAngle)
	{
		this.velocity = 10;
		this.posX = 0;
		this.posY = 0;
		this.lenght = 20;
		
		this.angle = startAngle;
		
		this.sourcePosX = sourcePosX;
		this.sourcePosY = sourcePosY;
		
	}
	
	public ANTSSimpleRayLightModel(ANTSSimpleSourceLightModel lightModel, double startAngle)
	{
		this.velocity = 10;
		this.posX =  lightModel.getPosX();;
		this.posY = lightModel.getPosY();;
		this.lenght = 20;
		
		this.angle = startAngle;
		
		this.sourcePosX = lightModel.getPosX();
		this.sourcePosY = lightModel.getPosY();
		this.sourceLightColor = lightModel.getLightColor();
	}
	
	public int getPosX()
	{
		return this.posX;
	}
	
	public int getPosY()
	{
		return this.posY;
	}
	
	public int getVelocity()
	{
		return this.velocity;
	}
	
	public int getLength()
	{
		return this.lenght;
	}
	
	public int getSourcePosX()
	{
		return this.sourcePosX;
	}
	
	public int getSourcePosY()
	{
		return this.sourcePosY;
	}
	
	public double getAngle()
	{
		return this.angle;
	}
	
	public Color getSourceLightColor()
	{
		return this.sourceLightColor;
	}
}
