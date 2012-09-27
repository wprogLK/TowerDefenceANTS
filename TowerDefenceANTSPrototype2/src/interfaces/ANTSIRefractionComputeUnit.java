package interfaces;

import interfaces.medium.ANTSIMediumController;

public interface ANTSIRefractionComputeUnit 
{
	public void calculateAngle(ANTSIRayController ray, ANTSIMediumController mediumIn);
	public double computeNewRayAngle(double perpendicularAngle, double rayAngle, double angleToAdd, double refractionIndexIn, double refractionIndexOut);
	public double calculateSnell(double angleIncoming, double refractionIndexMediumIn, double refractionIndexMediumOut);
	public double calculateCriticalAngle(double refractionIndexMediumIn, double refractionIndexMediumOut);
	public double computeAngleBetweenRayAndPerpendicular(double[] directionVecRay, double[] directionVecPerpendicular);
}
