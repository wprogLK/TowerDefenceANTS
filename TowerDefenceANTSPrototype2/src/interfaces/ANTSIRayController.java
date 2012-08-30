package interfaces;

import interfaces.medium.ANTSIMediumController;

public interface ANTSIRayController extends ANTSIController
{
	public void setCurrentMedium(ANTSIMediumController c);
	public double getRefractionIndex();
}
