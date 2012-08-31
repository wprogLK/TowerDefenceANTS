package interfaces.medium;

import controllers.ANTSSimpleRayLightController;

public interface ANTSIMediumModel 
{
	double getRefractionIndex();

	boolean containsRay(ANTSSimpleRayLightController c);

	void addRay(ANTSSimpleRayLightController rayLightController);

	void removeRay(ANTSSimpleRayLightController rayLightController);
	
	public double getPlumbAngle();
	public void setPlumbAngle(double angle);

	double getAngle(ANTSSimpleRayLightController rayLightController);

}
