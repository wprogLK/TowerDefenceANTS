package interfaces.medium;

import controllers.ANTSSimpleRayLightController;

public interface ANTSIMediumModel 
{
	///////////
	//GETTERS//
	///////////
	public double getRefractionIndex();
	public double getPlumbAngle();
	public double getAngle(ANTSSimpleRayLightController rayLightController);
	
	///////////
	//SETTERS//
	///////////
	
	public void setPlumbAngle(double angle);
	
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
