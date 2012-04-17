/**
 * 
 */
package model.basics;

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
	
	public ANTSSimpleSourceLightModel()
	{
		this.turnOn();
		
		this.radius = 10;
		this.posX = 100;
		this.posY = 100;
	}
	
	public void turnOn()
	{
		this.isOn = true;
	}
	
	public void turnOff()
	{
		this.isOn = false;
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
}
