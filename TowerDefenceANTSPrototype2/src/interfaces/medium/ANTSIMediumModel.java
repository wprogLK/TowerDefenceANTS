package interfaces.medium;

import controllers.ANTSSimpleRayLightController;

public interface ANTSIMediumModel 
{
	double getRefractionIndex();

	boolean containsRay(ANTSSimpleRayLightController c);

	double getAngle(double refractionIndexOtherMedium);

	void addRay(ANTSSimpleRayLightController rayLightController);

	void removeRay(ANTSSimpleRayLightController rayLightController);

}
