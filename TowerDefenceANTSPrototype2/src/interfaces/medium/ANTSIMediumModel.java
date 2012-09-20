package interfaces.medium;

import controllers.ANTSSimpleRayLightController;

public interface ANTSIMediumModel 
{
	///////////
	//GETTERS//
	///////////
	public double getRefractionIndex();
	
	///////////
	//SETTERS//
	///////////
	
	
	//////////////
	//ADD/REMOVE//
	//////////////
	
	public void addRay(ANTSSimpleRayLightController rayLightController);

	public void removeRay(ANTSSimpleRayLightController rayLightController);
		
	///////////
	//SPECIAL//
	///////////

	public boolean containsRay(ANTSSimpleRayLightController c);
}
