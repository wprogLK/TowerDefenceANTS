package basics;

import interfaces.ANTSIController;
import interfaces.ANTSIRayController;
import interfaces.medium.ANTSIMediumController;

import java.util.Arrays;

import basics.ANTSDevelopment.ANTSStream;

public class ANTSObjectCounter
{
	private int numberOfRays;
	private int numberOfMedium;
	private int numberOfSourceOfLights;
	
	private int numberOfGameObjectsTotal;
	
	public ANTSObjectCounter()
	{
		this.numberOfRays = 0;
		this.numberOfMedium = 0;
		this.numberOfSourceOfLights = 0;
		
		this.numberOfGameObjectsTotal = 0;
	}

	//////////////////////
	//Add/remove methods//
	//////////////////////
	
	public void add(ANTSIController c) 
	{
		if(ANTSUtility.implementsInterface(c, ANTSIRayController.class))
		{
			this.numberOfRays++;
			this.numberOfGameObjectsTotal++;
		}
		else if(ANTSUtility.implementsInterface(c, ANTSIMediumController.class))
		{
			this.numberOfMedium++;
			this.numberOfGameObjectsTotal++;
		}
		else
		{
//			ANTSStream.printErr("unknown ANTSIController try to add to ANTSObjectCounter");
			//unknown
		}
	}
	
	public void remove(ANTSIController c)
	{
		if(ANTSUtility.implementsInterface(c, ANTSIRayController.class))
		{
			this.numberOfRays--;
			this.numberOfGameObjectsTotal--;
		}
		else if(ANTSUtility.implementsInterface(c, ANTSIMediumController.class))
		{
			this.numberOfMedium--;
			this.numberOfGameObjectsTotal--;
		}
		else
		{
//			ANTSStream.printErr("unknown ANTSIController try to remove from ANTSObjectCounter");
			//unknown
		}
	}
	
	///////////
	//Getters//
	///////////
	
	public String getNumberOfRays()
	{
		return "# Rays: " + this.numberOfRays;
	}
	
	public String getNumberOfMedium()
	{
		return "# Medium: " + this.numberOfMedium;
	}
	
	public String getNumberOfSourceOfLights()
	{
		return "# SourceLights: " + this.numberOfSourceOfLights;
	}
	
	public String getNumberOfGameObjectsTotal()
	{
		return "# total gameObjects: " + this.numberOfGameObjectsTotal;
	}
}