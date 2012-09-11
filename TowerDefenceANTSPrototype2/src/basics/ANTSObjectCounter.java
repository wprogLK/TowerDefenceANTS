package basics;

import interfaces.ANTSIController;
import interfaces.ANTSIRayController;
import interfaces.medium.ANTSIMediumController;

import java.util.Arrays;

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

	public void add(ANTSIController c) 
	{
		if(this.implementsInterface(c, ANTSIRayController.class))
		{
			this.numberOfRays++;
			this.numberOfGameObjectsTotal++;
		}
		else if(this.implementsInterface(c, ANTSIMediumController.class))
		{
			this.numberOfMedium++;
			this.numberOfGameObjectsTotal++;
		}
		else
		{
			//unknown
		}
//		else if(this.implementsInterface(c, ANTSI.class))
//		{
//			this.numberOfMedium++;
//			this.numberOfGameObjectsTotal++;
//		}
		
	}
	
	public void remove(ANTSIController c)
	{
		if(this.implementsInterface(c, ANTSIRayController.class))
		{
			this.numberOfRays--;
			this.numberOfGameObjectsTotal--;
		}
		else if(this.implementsInterface(c, ANTSIMediumController.class))
		{
			this.numberOfMedium--;
			this.numberOfGameObjectsTotal--;
		}
		else
		{
			//unknown
		}
//		else if(this.implementsInterface(c, ANTSI.class))
//		{
//			this.numberOfMedium++;
//			this.numberOfGameObjectsTotal++;
//		}
	}
	
	private boolean implementsInterface(ANTSIController c, Object ANTSInterface)
	{
		return Arrays.asList(c.getClass().getInterfaces()).contains(ANTSInterface);
	}
	
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