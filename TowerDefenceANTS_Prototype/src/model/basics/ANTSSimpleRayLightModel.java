/**
 * 
 */
package model.basics;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import model.abstracts.ANTSModelAbstract;

/**
 * @author Lukas
 *
 */
public class ANTSSimpleRayLightModel extends ANTSModelAbstract implements ANTSIModel
{
	private double velocity;
	private double posX;
	private double posY;
	private int lenght;
	private double angle;
	
	//private AffineTransform aTPos;
	private AffineTransform aTVel;
	private AffineTransform aTRot;
	private AffineTransform aTot;
	
	private int sourcePosX;
	private int sourcePosY;
	private Color sourceLightColor;
	
	public ANTSSimpleRayLightModel(ANTSSimpleSourceLightModel lightSourceModel, double startAngle) 
	{
		super();
		this.setVelocity(10); 	//10
		
		this.setPosX( lightSourceModel.getPosX());
		this.setPosY( lightSourceModel.getPosY());
		
		this.lenght =10;
		
		this.setAngle(startAngle);
		
		this.sourcePosX = lightSourceModel.getPosX();
		this.sourcePosY = lightSourceModel.getPosY();
		this.sourceLightColor = lightSourceModel.getLightColor();
		
		//this.aTPos= new AffineTransform();
		this.aTRot = new AffineTransform();
		this.aTVel = new AffineTransform();
		this.aTot = new AffineTransform();
		
		this.aTRot.rotate(Math.toRadians(angle), this.getPosX(), this.getPosY());
		
		this.aTVel.translate(lightSourceModel.getRadius(), 0);
		aTot.concatenate(aTRot);
		aTot.concatenate(aTVel); 
	}
	
	public void setVelocity(double velocity)
	{
		this.velocity = velocity;
	}
	
	public double getPosX()
	{
		return this.posX;
	}
	
	public double getPosY()
	{
		return this.posY;
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
		this.posX = x;
	}
	
	public void setPosY(double y)
	{
		this.posY = y;
	}
	
	public void setAngle(double angle)
	{
		this.aTRot = new AffineTransform();
		
		this.angle = angle;
	}
	
	public void move()
	{
		this.aTot = new AffineTransform();
		this.aTVel.translate(this.getVelocity(), 0);
		
		aTot.concatenate(aTRot);
		aTot.concatenate(aTVel); 
	}
	
	
	public AffineTransform getAffineTransform()
	{
		return this.aTot;
	}

	@Override
	public void update() 
	{
		this.move();
	}
}
