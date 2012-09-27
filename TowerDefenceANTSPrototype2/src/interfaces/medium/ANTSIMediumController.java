package interfaces.medium;

import basics.ANTSPerpendicular;
import interfaces.ANTSIController;
import interfaces.ANTSIRayController;

public interface ANTSIMediumController extends ANTSIController
{
	///////////
	//SETTERS//
	///////////
	
	public void setRefractionIndex(double d);
	
	///////////
	//GETTERS//
	///////////
	
	public double getRefractionIndex();
	
	/**
	 * 
	 * @return the intersectionPoint with the ray which is nearest to the ray
	 */
	public double[] calculateIntersectionPoint(ANTSIRayController ray);
	
	/**
	 * 
	 * @return the perpendicular of the intersectionPoint with the ray which is nearest to the ray
	 */
	public ANTSPerpendicular calculatePerpendicular(ANTSIRayController ray);
}
