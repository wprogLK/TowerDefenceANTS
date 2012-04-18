/**
 * 
 */
package model.basics;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * @author Lukas
 *
 */
public class ANTSSimpleRayLightModel 
{
	private double velocity;
	private double posX;
	private double posY;
	private int lenght;
	private double angle;
	private Point2D.Double pos;
	
	private int sourcePosX;
	private int sourcePosY;
	private Color sourceLightColor;
	
	
	public ANTSSimpleRayLightModel(int sourcePosX, int sourcePosY, double startAngle)
	{
		this.velocity = 2;
		this.posX = 0;
		this.posY = 0;
		this.lenght = 20;
		
		this.angle = startAngle;
		
		this.sourcePosX = sourcePosX;
		this.sourcePosY = sourcePosY;
	}
	
	public ANTSSimpleRayLightModel(ANTSSimpleSourceLightModel lightModel, double startAngle)
	{
		this.velocity = 0.1;
		this.posX =  lightModel.getPosX();
		this.posY = lightModel.getPosY();
		
		this.pos = new Point2D.Double(posX, posY);
		
		this.lenght = 10;
		
		this.angle = startAngle;
		
		this.sourcePosX = lightModel.getPosX();
		this.sourcePosY = lightModel.getPosY();
		this.sourceLightColor = lightModel.getLightColor();
	}
	
	public double getPosX()
	{
		return this.pos.getX();//this.posX;
	}
	
	public double getPosY()
	{
		//return this.posY;
		return this.pos.getY();
	}
	
	public Point2D.Double getPos()
	{
		return this.pos;
	}
	
	public double getVelocity()
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
	
	
	public void setPosX(double x)
	{
		//this.posX = x;
	}
	
	public void setPosY(double y)
	{
		//this.posY = y;
	}
	
}
