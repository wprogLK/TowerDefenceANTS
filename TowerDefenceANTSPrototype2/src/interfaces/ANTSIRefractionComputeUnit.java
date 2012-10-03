package interfaces;

import interfaces.medium.ANTSIMediumController;

public interface ANTSIRefractionComputeUnit 
{
	public double calculateAngle(ANTSIRayController ray, ANTSIMediumController mediumIn);
	public double calculateSnell(double angleIncoming, double refractionIndexMediumIn, double refractionIndexMediumOut);
//	public double calculateCriticalAngle(double refractionIndexMediumIn, double refractionIndexMediumOut);
	public double computeAngleBetweenRayAndPerpendicular(double[] directionVecRay, double[] directionVecPerpendicular);
	public double getNewAngleRay(double[] directionVectorRay,double[] directionVectorPerpendicular,double refractionIndexMediumIn, double refractionIndexMediumOut,double angleRay, double anglePerpendicular);
	public double computeRefractionOrTotalReflection(double refractionIndexMediumIn,double refractionIndexMediumOut, double angleRay, double anglePerpendicular, double angleBetweenRayPerpendicular);
	double calculateTotalReflection(double angleBetweenRayPerpendicular,
			double anglePerpendicular, double angleRay);
	double calculateAngleInCriticalSituation(
			double angleBetweenRayPerpendicular, double anglePerpendicular,
			double angleRay);
	Double computeNewRayAngle(double perpendicularAngle, double rayAngle,
			double angleToAdd, double refractionIndexIn,
			double refractionIndexOut, int sign);
}
