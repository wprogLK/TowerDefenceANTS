/**
 * 
 */
package model.basics;

import interfaces.ANTSIModel;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * @author Lukas
 *
 */
public class ANTSSimpleRayLightModel implements ANTSIModel
{
	private double velocity;
	private double posX;
	private double posY;
	private int lenght;
	private double angle;
	
	private AffineTransform aTPos;
	private AffineTransform aTVel;
	private AffineTransform aTRot;
	
	private int sourcePosX;
	private int sourcePosY;
	private Color sourceLightColor;
	
	public ANTSSimpleRayLightModel(ANTSSimpleSourceLightModel lightSourceModel, double startAngle) 
	{
		this.setVelocity(10); 	//10
		
		this.setPosX( lightSourceModel.getPosX());
		this.setPosY( lightSourceModel.getPosY());
		
		this.lenght =10;
		
		//this.angle = startAngle;
		this.setAngle(startAngle);
		
		this.sourcePosX = lightSourceModel.getPosX();
		this.sourcePosY = lightSourceModel.getPosY();
		this.sourceLightColor = lightSourceModel.getLightColor();
		
		this.aTPos= new AffineTransform();
		this.aTRot = new AffineTransform();
		this.aTVel = new AffineTransform();
		
		this.aTRot.rotate(Math.toRadians(angle), this.getPosX(), this.getPosY());
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
	
	public AffineTransform getNextAffineTransform()
	{
		AffineTransform aTTot = new AffineTransform();
		this.aTVel.translate(this.getVelocity(), 0); //Move ray
		
		aTTot.concatenate(aTRot);
		
		aTTot.concatenate(aTVel); 
		
		return aTTot;
	}
	
	public AffineTransform getCurrentAffineTransform()
	{
		AffineTransform aTTot = new AffineTransform();
		this.aTVel.translate(0, 0); //Move ray
		
		aTTot.concatenate(aTRot);
		
		aTTot.concatenate(aTVel); 
		
		return aTTot;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
}
