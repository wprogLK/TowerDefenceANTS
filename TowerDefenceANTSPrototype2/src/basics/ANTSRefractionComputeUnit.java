package basics;

import interfaces.ANTSIRayController;
import interfaces.ANTSIRefractionComputeUnit;
import interfaces.ANTSIStandardMediumController;
import interfaces.medium.ANTSIMediumController;

import controllers.medium.ANTSStandardMediumController;
import enums.ANTSDirectionEnum;

import basics.ANTSDevelopment.ANTSStream;

public class ANTSRefractionComputeUnit implements ANTSIRefractionComputeUnit
{
	private ANTSIStandardMediumController standardMediumController;
	
	public ANTSRefractionComputeUnit(ANTSIStandardMediumController standardMediumController)
	{
		this.standardMediumController = standardMediumController;
	}
	
	@Override
	public double calculateAngle(ANTSIRayController ray, ANTSIMediumController mediumIn)
	{
			ANTSIMediumController mediumOut = ray.getCurrentMedium();
			
			ANTSDirectionEnum direction = null;
			ANTSIMediumController medium;
			
			if(this.isMediumInStandardMedium(mediumIn)) 
			{
				medium = ray.getCurrentMedium();
				direction = ANTSDirectionEnum.OUT;
			}
			else
			{
				medium = mediumIn;
				direction = ANTSDirectionEnum.IN;
			}
			
			if(!mediumOut.equals(mediumIn))
			{
				ANTSPerpendicular perpendicular = medium.calculatePerpendicular(ray);
//				
				double[] directionVectorRay = ray.getDirectionVector();
				double[] directionVectorPerpendicular = perpendicular.getDirectionVector(direction);
				
				double refractionIndexMediumIn = mediumIn.getRefractionIndex();
				double refractionIndexMediumOut = mediumOut.getRefractionIndex();
				
				double angleRay = ray.getAngle();
				double anglePerpendicular = perpendicular.getAngle(direction);
				
				if(refractionIndexMediumIn>refractionIndexMediumOut)
				{
					ANTSStream.printDebug("Brechung ZUM Lot");
				}
				else
				{
					ANTSStream.printDebug("Brechung VOM Lot");
					ANTSStream.printDebug("Reflection or total internal reflection are possible!");
				}
//				
				double realAngleToSet = getNewAngleRay(directionVectorRay, directionVectorPerpendicular, refractionIndexMediumIn, refractionIndexMediumOut, angleRay, anglePerpendicular);
//				
				this.updateRay(realAngleToSet, ray, mediumIn);
				return realAngleToSet; //Only for testing!
			}
			else
			{
//				ANTSStream.printDebug("ERROR: MediumIn and MediumOut are the same! Don't calculate a new angle...");
				return ray.getAngle();
			}
	}
	
	@Override
	/**
	 * 
	 * @param directionVectorRay
	 * @param directionVectorPerpendicular
	 * @param refractionIndexMediumIn
	 * @param refractionIndexMediumOut
	 * @param angleRay
	 * @param anglePerpendicular
	 * @return  the new absolute angle of the ray
	 */
	public double getNewAngleRay(double[] directionVectorRay, double[] directionVectorPerpendicular, double refractionIndexMediumIn, double refractionIndexMediumOut, double angleRay, double anglePerpendicular) 
	{
		double angleBetweenRayPerpendicular = this.computeAngleBetweenRayAndPerpendicular(directionVectorRay, directionVectorPerpendicular);
		
		assert(ANTSUtility.roundScale2(angleBetweenRayPerpendicular)==ANTSUtility.roundScale2(ANTSUtility.angleBetweenToAngles(angleRay, anglePerpendicular)));
		
		double realAngleToSet = 0;
		
		if(!Double.isNaN(angleBetweenRayPerpendicular))
		{
			realAngleToSet = this.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRay, anglePerpendicular, angleBetweenRayPerpendicular);
			ANTSStream.print("TO SET " + realAngleToSet);
		}
		else
		{
			realAngleToSet = angleRay;
			
			ANTSStream.print("ERROR angle between ray and perpendicular is NaN because the should not be a collision!"); //TODO
		}
		return realAngleToSet;
	}

//	/*
//	 * Only for testing
//	 */
//	private boolean checkAngleBetweenRayAndPerpendicular(double angleBetweenRayPerpendicular, double angleRay,double anglePerpendicular)
//	{
//		double biggerAngle = Math.max(angleRay, anglePerpendicular);
//		double smallerAngle = Math.min(angleRay, anglePerpendicular);
//		
//		double angleBetween = biggerAngle - smallerAngle;
//		
//		if(angleBetween>90)
//		{
//			angleBetween = 360 - angleBetween;
//		}
//		
////		ANTSStream.print("AngleBetweenCheck = " + ANTSUtility.roundScale2(angleBetween) + "AngleBetweenCalc = " + ANTSUtility.roundScale2(angleBetweenRayPerpendicular));
//		
//		return (ANTSUtility.roundScale2(angleBetween)==ANTSUtility.roundScale2(angleBetweenRayPerpendicular));
//	}

	
	/**
	 * 
	 * @param refractionIndexMediumIn
	 * @param refractionIndexMediumOut
	 * @param angleRay
	 * @param anglePerpendicular
	 * @param angleBetweenRayPerpendicular
	 * @return the new absolute angle of the ray
	 */
	@Override
	public double computeRefractionOrTotalReflection(double refractionIndexMediumIn, double refractionIndexMediumOut,double angleRay, double anglePerpendicular, double angleBetweenRayPerpendicular) 
	{
		double criticalAngle = this.calculateCriticalAngle(refractionIndexMediumIn,refractionIndexMediumOut);
		double angleToSet = 0;
		
		boolean isPossibleTotalReflection = refractionIndexMediumIn<=refractionIndexMediumOut;
		boolean angleRelationForTotalReflection = !(ANTSUtility.roundScale2(angleBetweenRayPerpendicular)<ANTSUtility.roundScale2(criticalAngle));
		
//		ANTSStream.print("CRITICAL ANGLE IS " + criticalAngle);
		
		if(isPossibleTotalReflection && angleRelationForTotalReflection)
		{
			if(ANTSUtility.roundScale2(angleBetweenRayPerpendicular)==ANTSUtility.roundScale2(criticalAngle))
			{
				angleToSet= this.calculateAngleInCriticalSituation(angleBetweenRayPerpendicular,anglePerpendicular,angleRay);
			}
			else if(ANTSUtility.roundScale2(angleBetweenRayPerpendicular)>ANTSUtility.roundScale2(criticalAngle))
			{
				angleToSet = this.calculateTotalReflection(angleBetweenRayPerpendicular,anglePerpendicular,angleRay);
				
				//TODO: fix bug, if it was a totalReflection don't check this ray so quick again!
				
				return angleToSet;
			}
			else
			{
				ANTSStream.printErr("Something strange happend!"); //TODO
			}
		}
		else
		{
			angleToSet = this.calculateSnell(angleBetweenRayPerpendicular,refractionIndexMediumIn,refractionIndexMediumOut);
				
			assert(angleToSet<360 && angleToSet>=0);
		}
		return this.computeNewRayAngle(anglePerpendicular, angleRay ,angleToSet ,refractionIndexMediumIn,refractionIndexMediumOut);
	}
	
	@Override
	public double calculateAngleInCriticalSituation(double angleBetweenRayPerpendicular, double anglePerpendicular,double angleRay) 
	{
		ANTSStream.print("GRENZE");
		
		return 90;
	}

	@Override
	public double calculateTotalReflection(double angleBetweenRayPerpendicular, double anglePerpendicular,double angleRay) 
	{
		ANTSStream.print("Totalreflection: Perp: " + anglePerpendicular + " between " + angleBetweenRayPerpendicular) ;
	
		double rayAngleOut = -1;
		double anglePerpendicularOut = anglePerpendicular+180;
		
		if(angleRay<=anglePerpendicular)
		{
			rayAngleOut = anglePerpendicularOut+angleBetweenRayPerpendicular;
			ANTSStream.print("Totalreflection : PLUS : "  + rayAngleOut);
		}
		else if(angleRay>anglePerpendicular)
		{
			rayAngleOut = anglePerpendicularOut-angleBetweenRayPerpendicular;
			ANTSStream.print("Totalreflection : MINUS : " +rayAngleOut);
		}
		else
		{
			ANTSStream.printErr("Error: Unknown case in calculateTotalReflection"); //TODO
		}
		
		rayAngleOut = ANTSUtility.angleBetween0And359Degree(rayAngleOut);
		
		return rayAngleOut;
	}

	private void updateRay(double realAngleToSet, ANTSIRayController ray, ANTSIMediumController mediumIn) 
	{
		ray.setAngle(realAngleToSet);
		ray.setCurrentMedium(mediumIn);
	}

	private boolean isMediumInStandardMedium(ANTSIMediumController mediumIn) 
	{
		return mediumIn.equals(this.standardMediumController); //TODO TEST THIS (is it working if this.standardMediumController is an interface?)
	}

	@Override
	/**
	 * @param perpendicularAngle
	 * @param rayAngle
	 * @param angleToAdd
	 * @param refractionIndexIn
	 * @param reafractionIndexOut
	 * @return the new absolute angle of the ray
	 */
	public double computeNewRayAngle(double perpendicularAngle,double rayAngle, double angleToAdd, double refractionIndexIn, double refractionIndexOut) 
	{
		double newRayAngle = 0;
		
//		ANTSStream.print("perpendicularAngle = " + perpendicularAngle +"\nrayAngle = " + rayAngle );//+"\nangleToAdd = " + angleToAdd);
		
		double angleRayReduced = ANTSUtility.angleBetween0And359Degree(rayAngle - perpendicularAngle);	//perpemdicularAngle is now always 0°
		
		if(angleRayReduced>=180)
		{
			ANTSStream.print("MINUS");
			newRayAngle = 360-angleToAdd+perpendicularAngle;
		}
		else
		{
			ANTSStream.print("PLUS");
			newRayAngle = angleToAdd+perpendicularAngle;
		}
		
		ANTSStream.print("new ANGLE s = " + newRayAngle);
		
		newRayAngle = ANTSUtility.angleBetween0And359Degree(newRayAngle);
		
//		ANTSStream.printErr("----------ANGLE TO SET----------- " + newRayAngle); 
		
		return newRayAngle;
	}

	@Override
	/**
	 * @param angleIncoming
	 * @param refractionIndexMediumIn
	 * @param refractionIndexMediumOut
	 * @return angle of refraction: The angle {@code alpha} is always: {@code 0°<=alpha<=90°}
	 */
	public double calculateSnell(double angleIncoming, double refractionIndexMediumIn, double refractionIndexMediumOut) 
	{
		double angleIncomingRad = Math.toRadians(angleIncoming);
		
		double factor = (Math.sin(angleIncomingRad)*refractionIndexMediumOut)/refractionIndexMediumIn;
		double angle = Math.toDegrees(Math.asin(factor));
//		double criticalAngle = this.calculateCriticalAngle(refractionIndexMediumIn, refractionIndexMediumOut);
		
////		ANTSStream.printErr("phi_1 = " + ANTSUtility.roundScale2(angleIncoming) + "\t critical angle = " + ANTSUtility.roundScale2(criticalAngle) +" factor is " + factor + "medIn " + refractionIndexMediumIn + " < medOut" + refractionIndexMediumOut);	
//		
//		if(refractionIndexMediumIn<=refractionIndexMediumOut && ANTSUtility.roundScale2(angleIncoming)>=ANTSUtility.roundScale2(criticalAngle))
//		{
////			ANTSStream.printErr("'Error': TOTAL REFLECTION! \n phi_1 = " + angleIncoming + "\t critical angle = " + criticalAngle +" factor is " + factor);	
//			
//			//TODO test this
//			
//			double rest = factor % 1;
//			double number = factor - rest;
//			
//			double extraAngle = number * 90;
//			angle = Math.toDegrees(Math.asin(rest));
//			
////			ANTSStream.print("angle " + angle);
//			angle = angle + extraAngle;
//			
////			ANTSStream.print("extra Angle " + extraAngle);
////			ANTSStream.print("return angle: " + angle);
//		}
//		
		
		angle = ANTSUtility.angleBetween0And359Degree(angle);
//		ANTSStream.print("return angle: " + angle);
		return angle;
	}

	/**
	 * Calculates the critical angle for a total reflection
	 * @param refractionIndexMediumIn
	 * @param refractionIndexMediumOut
	 * @return
	 */
	public static double calculateCriticalAngle(double refractionIndexMediumIn, double refractionIndexMediumOut)
	{
		if(refractionIndexMediumIn<refractionIndexMediumOut)
		{
			double angleCrit = Math.toDegrees(Math.asin(refractionIndexMediumIn/refractionIndexMediumOut));
			
//			ANTSStream.print("critical angle is " + angleCrit + "med in " + refractionIndexMediumIn + " med out " + refractionIndexMediumOut);
			
			return ANTSUtility.angleBetween0And359Degree(angleCrit);
		}
		else
		{
			return -1;
		}
	}
	
	@Override
	/**
	 * @param directionVectorRay
	 * @param directionVectorPerpendicular
	 * @return the angle between the directionVector of the ray and the directionVector of the perpendicular. The angle {@code alpha} is always: {@code 0°<=alpha<=90°}
	 */
	public double computeAngleBetweenRayAndPerpendicular(double[] directionVectorRay, double[] directionVectorPerpendicular)
	{
		return ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
	}
}
