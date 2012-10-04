package basics;

import interfaces.ANTSIRayController;
import interfaces.ANTSIRefractionComputeUnit;
import interfaces.ANTSIStandardMediumController;
import interfaces.medium.ANTSIMediumController;

import controllers.medium.ANTSStandardMediumController;
import enums.ANTSDirectionEnum;
import enums.ANTSQuadrantEnum;

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
				
				double angleBetweenRayPerpendicular = this.computeAngleBetweenRayAndPerpendicular(directionVectorRay, directionVectorPerpendicular);
				double criticalAngle = this.calculateCriticalAngle(refractionIndexMediumIn,refractionIndexMediumOut);
				
				if(refractionIndexMediumIn>refractionIndexMediumOut)
				{
//					ANTSStream.printDebug("Brechung ZUM Lot");
				}
				else
				{
//					ANTSStream.printDebug("Brechung VOM Lot");
					
					
					
//					ANTSStream.printDebug("crit angle = " +criticalAngle);
//					ANTSStream.printDebug("between angle = " +angleBetweenRayPerpendicular);
					
					boolean isPossibleTotalReflection = refractionIndexMediumIn<=refractionIndexMediumOut;
					boolean angleRelationForTotalReflection = !(ANTSUtility.roundScale2(angleBetweenRayPerpendicular)<=ANTSUtility.roundScale2(criticalAngle));
					
					if(isPossibleTotalReflection && angleRelationForTotalReflection)
					{
//						ANTSStream.printDebug("total internal reflection happen! round between " +ANTSUtility.roundScale2(angleBetweenRayPerpendicular) + " round crit " + ANTSUtility.roundScale2(criticalAngle) );
						mediumIn = mediumOut; //The ray is not changing the medium!
					}
					
				}
//				
				double realAngleToSet = getNewAngleRay(directionVectorRay, directionVectorPerpendicular, refractionIndexMediumIn, refractionIndexMediumOut, angleRay, anglePerpendicular);
//				
				this.updateRay(realAngleToSet, ray, mediumIn);
				
//ONLY FOR DEBUGGING				
//				double[] directionVectorNew = ray.getDirectionVector();
//				double newAngleRay2 = ANTSUtility.computeAngleOfOneVector(directionVectorNew);
//				double angleBetweenOUT = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorPerpendicular, directionVectorNew);
				
//				ANTSStream.print("__________________________________________________________");
//				ANTSStream.print("NEW direction vector ray = [ " + directionVectorNew[0] + " | " + directionVectorNew[1] + " ]");
//				ANTSStream.print("OLD direction vector ray = [ " + directionVectorRay[0] + " | " + directionVectorRay[1] + " ]");
//				ANTSStream.print("OLD ray angle = " + angleRay);
//				ANTSStream.print("NEW ray angle calc with DIRECTION vector = " + newAngleRay2);
//				ANTSStream.print("NEW ray angle TO SET = " + realAngleToSet );
//				ANTSStream.print("NEW ray angle GET = " + ray.getAngle());
//				ANTSStream.print("angle between OUT = " + angleBetweenOUT);
//				ANTSStream.print("angle between IN = " + angleBetweenRayPerpendicular);
//				ANTSStream.print("critical angle = " + criticalAngle);
//				ANTSStream.print("ANGLE Perpendicular = " + anglePerpendicular);
//				ANTSStream.print("__________________________________________________________");
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
		
//		ANTSStream.printDebug("between IN angle = " +angleBetweenRayPerpendicular);
		
		assert(ANTSUtility.roundScale2(angleBetweenRayPerpendicular)==ANTSUtility.roundScale2(ANTSUtility.angleBetweenToAngles(angleRay, anglePerpendicular)));
		
		double realAngleToSet = 0;
		
		if(!Double.isNaN(angleBetweenRayPerpendicular))
		{
			realAngleToSet = this.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRay, anglePerpendicular, angleBetweenRayPerpendicular);
//			ANTSStream.print("TO SET " + realAngleToSet);
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
		int sign = 0;
		
		boolean isPossibleTotalReflection = refractionIndexMediumIn<=refractionIndexMediumOut;
		boolean angleRelationForTotalReflection = !(ANTSUtility.roundScale2(angleBetweenRayPerpendicular)<ANTSUtility.roundScale2(criticalAngle));
		
//		ANTSStream.print("CRITICAL ANGLE IS " + criticalAngle);
		
		if(isPossibleTotalReflection && angleRelationForTotalReflection)
		{
			if(ANTSUtility.roundScale2(angleBetweenRayPerpendicular)==ANTSUtility.roundScale2(criticalAngle))
			{
				angleToSet= this.calculateAngleInCriticalSituation(angleBetweenRayPerpendicular,anglePerpendicular,angleRay);
				
				sign = 1;
			}
			else if(ANTSUtility.roundScale2(angleBetweenRayPerpendicular)>ANTSUtility.roundScale2(criticalAngle))
			{
				angleToSet = this.calculateTotalReflection(angleBetweenRayPerpendicular,anglePerpendicular,angleRay);
//				anglePerpendicular = ANTSUtility.angleBetween0And359Degree(anglePerpendicular + 180);
				
				sign = -1;
//				return angleToSet;
			}
			else
			{
				ANTSStream.printErr("Something strange happend!"); //TODO
			}
		}
		else
		{
			angleToSet = this.calculateSnell(angleBetweenRayPerpendicular,refractionIndexMediumIn,refractionIndexMediumOut);
			sign = 1;	
			assert(angleToSet<360 && angleToSet>=0);
		}
		return this.computeNewRayAngle(anglePerpendicular, angleRay ,angleToSet ,refractionIndexMediumIn,refractionIndexMediumOut, sign);
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
//		ANTSStream.print("Totalreflection: Perp: " + anglePerpendicular + " between " + angleBetweenRayPerpendicular) ;
//	
//		double rayAngleOut = -1;
//		double anglePerpendicularOut = anglePerpendicular+180;
//		
//		if(angleRay<=anglePerpendicular)
//		{
//			rayAngleOut = anglePerpendicularOut+angleBetweenRayPerpendicular;
//			ANTSStream.print("Totalreflection : PLUS : "  + rayAngleOut);
//		}
//		else if(angleRay>anglePerpendicular)
//		{
//			rayAngleOut = anglePerpendicularOut-angleBetweenRayPerpendicular;
//			ANTSStream.print("Totalreflection : MINUS : " +rayAngleOut);
//		}
//		else
//		{
//			ANTSStream.printErr("Error: Unknown case in calculateTotalReflection"); //TODO
//		}
//		
//		rayAngleOut = ANTSUtility.angleBetween0And359Degree(rayAngleOut);
//		
//		return rayAngleOut;
		
		return angleBetweenRayPerpendicular;
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
	 * @param sign: if it's a normal refraction +1
	 * 				if it's a total reflection -1
	 * @return the new absolute angle of the ray
	 */
	public Double computeNewRayAngle(double perpendicularAngle,double rayAngle, double angleToAdd, double refractionIndexIn, double refractionIndexOut,int sign) 
	{			
//		   |
//		C  |	D
//	-------|------>
//		   |
//		B  |	A
//		   v
		
		/*
		 * Idea: reduce angle perpendicular and ray to a easier situation where perpendicular angle is 0�, 90�, 180� or 270�
		 */
		
		double newRayAngle = -1;
		
		int signCalc = 0;
		
		double minuend = perpendicularAngle % 90;
		double anglePerpendicularReduced = perpendicularAngle - minuend;
		double angleRayReduced = rayAngle - minuend;
		
		angleRayReduced = ANTSUtility.angleBetween0And359Degree(angleRayReduced);
		
		assert(anglePerpendicularReduced==0 || anglePerpendicularReduced==90 || anglePerpendicularReduced == 180 || anglePerpendicularReduced == 270);
		assert(angleRayReduced>=0 && angleRayReduced<360);
		
//		ANTSStream.print("______________________________________________________________________________________________________________________________________________");
//		ANTSStream.print("INFO: \n anglePerpendicular = " + perpendicularAngle + " anglePerpendicularReduced = " + anglePerpendicularReduced + "\n rayAngle = " + rayAngle + " rayAngleReduced = " + angleRayReduced + " Angle to add = " + angleToAdd +  "\n sign = " + sign +"\n minuend = " + minuend);
//		ANTSStream.print("______________________________________________________________________________________________________________________________________________");
		
		if(angleRayReduced<anglePerpendicularReduced)
		{
//			ANTSStream.print("MINUS REFRACTION");
			signCalc = -1;
		}
		else
		{
//			ANTSStream.print("PLUS REFRACTION");
			signCalc = 1;
		}
		
		ANTSQuadrantEnum quadrantRayReduced = ANTSUtility.getQuadrantOfAngle(angleRayReduced);
		
		if(anglePerpendicularReduced==0)
		{
			if(quadrantRayReduced==ANTSQuadrantEnum.A)
			{
//				ANTSStream.print("PLUS REFRACTION");
				signCalc = 1;
			}
			else if(quadrantRayReduced == ANTSQuadrantEnum.D)
			{
//				ANTSStream.print("MINUS REFRACTION");
				signCalc = -1;
			}
			else
			{
				ANTSStream.print("ERROR: Impossible quadrant! The quadrant was " + quadrantRayReduced); //TODO
				return null;
			}
		}
		
		if(sign==-1)
		{
			if(signCalc==1)
			{
//				ANTSStream.print("MINUS TOTAL REFLECTION");
			}
			else
			{
//				ANTSStream.print("PLUS TOTAL REFLECTION");
			}
			
			anglePerpendicularReduced = anglePerpendicularReduced + 180;
		}
		
		
		double reducedNewAngleRay = anglePerpendicularReduced+(signCalc*sign*angleToAdd);
		newRayAngle = reducedNewAngleRay + minuend;
		
//		ANTSStream.print("newRayAngle is : " +newRayAngle );
//		assert(newRayAngle>=0 && newRayAngle<360);
		
		newRayAngle = ANTSUtility.angleBetween0And359Degree(newRayAngle);
//		ANTSStream.print("newRayAngle is correct : " +newRayAngle );
		
		return newRayAngle;
	}
	
	
	@Override
	/**
	 * @param angleIncoming
	 * @param refractionIndexMediumIn
	 * @param refractionIndexMediumOut
	 * @return angle of refraction: The angle {@code alpha} is always: {@code 0�<=alpha<=90�}
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
	 * @return the angle between the directionVector of the ray and the directionVector of the perpendicular. The angle {@code alpha} is always: {@code 0�<=alpha<=90�}
	 */
	public double computeAngleBetweenRayAndPerpendicular(double[] directionVectorRay, double[] directionVectorPerpendicular)
	{
		return ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
	}
}
