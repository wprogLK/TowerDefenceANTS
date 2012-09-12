package interfaces;

import interfaces.medium.ANTSIMediumController;

public interface ANTSIRayController extends ANTSIController
{
	///////////
	//SETTERS//
	///////////
	
	public boolean setCurrentMedium(ANTSIMediumController c);
	
	///////////
	//GETTERS//
	///////////
	
	public double getRefractionIndex();
	double getAngle();
	
	public double[] getCenter();
	
	///////////
	//SPECIAL//
	///////////
	
	public void addAngle(double angle);
}
