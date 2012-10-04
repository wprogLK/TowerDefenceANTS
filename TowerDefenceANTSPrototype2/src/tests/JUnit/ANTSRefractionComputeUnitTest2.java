package tests.JUnit;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import interfaces.ANTSIRayController;
import interfaces.ANTSIStandardMediumController;
import interfaces.medium.ANTSIMediumController;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import basics.ANTSDevelopment.ANTSStream;
import basics.ANTSPerpendicular;
import basics.ANTSRefractionComputeUnit;
import basics.ANTSUtility;

import ch.unibe.jexample.Given;
import ch.unibe.jexample.JExample;

import org.hamcrest.*;

import enums.ANTSDirectionEnum;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JExample.class)
public class ANTSRefractionComputeUnitTest2
{
	private ANTSRefractionComputeUnit unit;
	
	private ANTSIStandardMediumController standardMediumControllerMock;
	private ANTSIRayController rayControllerMock;
	
	private ANTSIMediumController mediumInControllerMock;
	private ANTSIMediumController mediumOutControllerMock;
	
	//MediumOut
	private double refractionIndexMediumOut = 1.5;
	
	//StandardMedium
	private double refractionIndexStandardMedium = 1;
	
	//MediumIn
	private double refractionIndexMediumIn = 1.1;
	
	@Before
	public Mockery setUp()
	{
		Mockery mockery = new JUnit4Mockery();
		
		this.standardMediumControllerMock = mockery.mock(ANTSIStandardMediumController.class);
		this.rayControllerMock = mockery.mock(ANTSIRayController.class);
		
		this.mediumInControllerMock = mockery.mock(ANTSIMediumController.class, "mediumIn");
		this.mediumOutControllerMock = mockery.mock(ANTSIMediumController.class, "mediumOut");
		
		this.unit = new ANTSRefractionComputeUnit(this.standardMediumControllerMock);
		
		return mockery;
	}
	
	@Test
	public Mockery simpleTestFromAMediumWithGreaterRefractionIndexIntoStandardMediumWithoutReflexion() 
	{
		return this.setUp();
	}
	
	@Given("simpleTestFromAMediumWithGreaterRefractionIndexIntoStandardMediumWithoutReflexion")
	public void testSituationA1(Mockery mockery)
	{
		//Setup for Situation A1:
			//perpendicularAngle will be 90�
			//rayAngle will be <=90� & >=0�
		
			//Perpendicular
			//in java coordinate system!
			double[] startPointPerpendicular = {100, 500};	//IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
			double[] endPointPerpendicular = {100, 100};	//IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
			
			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.OUT);
			double[] directionVectorPerpendicular = perpendicular.getDirectionVector(ANTSDirectionEnum.OUT);
			
			//Ray
			//in java coordinate system!
			double[] startPointRay = {100,100};		
			double[] endPointRay = {150,160};
			
			final double[] directionVectorRay = ANTSUtility.computeDirectionVector(startPointRay, endPointRay);
			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
			
			//AngleBetween
			double angleBetweenIn = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
			
			assert(rayAngle<=90 && rayAngle>=0);
			assert(perpendicularAngle==90);
			
			//CriticalAngle
			double criticalAngle = ANTSRefractionComputeUnit.calculateCriticalAngle(this.refractionIndexStandardMedium, this.refractionIndexMediumOut);
			
			assertThat(angleBetweenIn,lessThan(criticalAngle));
			
		mockery.checking(new Expectations()
		{
			{
				oneOf(rayControllerMock).getCurrentMedium(); 
					will(returnValue(mediumOutControllerMock));
				
				oneOf(rayControllerMock).getCurrentMedium(); 
					will(returnValue(mediumOutControllerMock));
					
				oneOf(mediumOutControllerMock).calculatePerpendicular(rayControllerMock);
					will(returnValue(perpendicular));
					
				oneOf(rayControllerMock).getDirectionVector();
					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
					
				oneOf(mediumOutControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexMediumOut));
//					
				oneOf(standardMediumControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexStandardMedium));
//					
				oneOf(rayControllerMock).getAngle();
					will(returnValue(rayAngle));
//					
				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
				oneOf(rayControllerMock).setCurrentMedium(standardMediumControllerMock);
			}
		});
		
		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.standardMediumControllerMock);
		
		Point2D p = new Point2D.Double(100, 0);
		
		AffineTransform aT = new AffineTransform();
		aT.rotate(Math.toRadians(newAngleRay));
		
		aT.transform(p, p);
		
		double[] directionVectorRayOut = {p.getX(),p.getY()};
		
		double angleBetweenOut =ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorPerpendicular, directionVectorRayOut);
		double expectedAngleBetweenOut = this.unit.calculateSnell(angleBetweenIn, refractionIndexStandardMedium, refractionIndexMediumOut);
		
		assertThat(ANTSUtility.roundScale2(newAngleRay), equalTo(ANTSUtility.roundScale2(perpendicularAngle-expectedAngleBetweenOut)));
		
		assert(perpendicularAngle>newAngleRay);
		assert(angleBetweenIn<angleBetweenOut);
		
		mockery.assertIsSatisfied();
	}
	
	@Given("simpleTestFromAMediumWithGreaterRefractionIndexIntoStandardMediumWithoutReflexion")
	public void testSituationA2(Mockery mockery)
	{
		//Setup for Situation A2:
			//perpendicularAngle will be 90�
			//rayAngle will be <180� & >90�
		
			//Perpendicular
			//in java coordinate system!
			double[] startPointPerpendicular = {100, 500}; //IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
			double[] endPointPerpendicular = {100, 100}; //IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
			
			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.OUT);
			double[] directionVectorPerpendicular = perpendicular.getDirectionVector(ANTSDirectionEnum.OUT);
			
			//Ray
			//in java coordinate system!
			double[] startPointRay = {150,100};		
			double[] endPointRay = {100,160};	
			final double[] directionVectorRay = ANTSUtility.computeDirectionVector(startPointRay, endPointRay);
			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
		
			//AngleBetween
			double angleBetweenIn = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
			
			assert(rayAngle<180 && rayAngle>90);
			assert(perpendicularAngle==90);
			
			//CriticalAngle
			double criticalAngle = ANTSRefractionComputeUnit.calculateCriticalAngle(this.refractionIndexStandardMedium, this.refractionIndexMediumOut);
			
			assertThat(angleBetweenIn,lessThan(criticalAngle));
			
		mockery.checking(new Expectations()
		{
			{
				oneOf(rayControllerMock).getCurrentMedium(); 
					will(returnValue(mediumOutControllerMock));
			
				oneOf(rayControllerMock).getCurrentMedium(); 
					will(returnValue(mediumOutControllerMock));
					
				oneOf(mediumOutControllerMock).calculatePerpendicular(rayControllerMock);
					will(returnValue(perpendicular));
					
				oneOf(rayControllerMock).getDirectionVector();
					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
					
				oneOf(mediumOutControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexMediumOut));
	//				
				oneOf(standardMediumControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexStandardMedium));
	//				
				oneOf(rayControllerMock).getAngle();
					will(returnValue(rayAngle));
	//				
				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
				oneOf(rayControllerMock).setCurrentMedium(standardMediumControllerMock);
			}
		});
		
		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.standardMediumControllerMock);
		
		Point2D p = new Point2D.Double(100, 0);
		
		AffineTransform aT = new AffineTransform();
		aT.rotate(Math.toRadians(newAngleRay));
		
		aT.transform(p, p);
		
		double[] directionVectorRayOut = {p.getX(),p.getY()};
		
		double angleBetweenOut = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorPerpendicular, directionVectorRayOut);
		double expectedAngleBetweenOut = this.unit.calculateSnell(angleBetweenIn, refractionIndexStandardMedium, refractionIndexMediumOut);
		
		assertThat(ANTSUtility.roundScale2(newAngleRay), equalTo(ANTSUtility.roundScale2(perpendicularAngle+expectedAngleBetweenOut)));
		
		assert(perpendicularAngle<newAngleRay);
		assert(angleBetweenIn<angleBetweenOut);
		
		mockery.assertIsSatisfied();
	}
	
	@Given("simpleTestFromAMediumWithGreaterRefractionIndexIntoStandardMediumWithoutReflexion")
	public void testSituationB1(Mockery mockery)
	{
		//Setup for Situation B1:
			//perpendicularAngle will be 180� 
			//rayAngle will be <=180� & >=90�
		
			//Perpendicular
			//in java coordinate system!
			double[] startPointPerpendicular = {100,100 };//IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
			double[] endPointPerpendicular = {500, 100}; //IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
			
			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.OUT);
			double[] directionVectorPerpendicular = perpendicular.getDirectionVector(ANTSDirectionEnum.OUT);
			
			//Ray
			//in java coordinate system!
			double[] startPointRay = {150,100};		
			double[] endPointRay = {100,110};	
			final double[] directionVectorRay = ANTSUtility.computeDirectionVector(startPointRay, endPointRay);
			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
		
			//AngleBetween
			double angleBetweenIn = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
			
			assert(rayAngle<=180 && rayAngle>=90);
			assert(perpendicularAngle==180);
			
			//CriticalAngle
			double criticalAngle = ANTSRefractionComputeUnit.calculateCriticalAngle(this.refractionIndexStandardMedium, this.refractionIndexMediumOut);
			
			assertThat(angleBetweenIn,lessThan(criticalAngle));
			
		mockery.checking(new Expectations()
		{
			{
//				oneOf(rayControllerMock).getCurrentMedium(); 
//					will(returnValue(standardMediumControllerMock));
//					
//				oneOf(mediumInControllerMock).calculatePerpendicular(rayControllerMock);
//					will(returnValue(perpendicular));
//					
//				oneOf(rayControllerMock).getDirectionVector();
//					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
//					
//				oneOf(mediumInControllerMock).getRefractionIndex();
//					will(returnValue(refractionIndexMediumIn));
//					
//				oneOf(standardMediumControllerMock).getRefractionIndex();
//					will(returnValue(refractionIndexStandardMedium));
//					
//				oneOf(rayControllerMock).getAngle();
//					will(returnValue(rayAngle));
//					
//				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
//				oneOf(rayControllerMock).setCurrentMedium(mediumInControllerMock);
				oneOf(rayControllerMock).getCurrentMedium(); 
				will(returnValue(mediumOutControllerMock));
		
			oneOf(rayControllerMock).getCurrentMedium(); 
				will(returnValue(mediumOutControllerMock));
				
			oneOf(mediumOutControllerMock).calculatePerpendicular(rayControllerMock);
				will(returnValue(perpendicular));
				
			oneOf(rayControllerMock).getDirectionVector();
				will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
				
			oneOf(mediumOutControllerMock).getRefractionIndex();
				will(returnValue(refractionIndexMediumOut));
//				
			oneOf(standardMediumControllerMock).getRefractionIndex();
				will(returnValue(refractionIndexStandardMedium));
//				
			oneOf(rayControllerMock).getAngle();
				will(returnValue(rayAngle));
//				
			oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
			oneOf(rayControllerMock).setCurrentMedium(standardMediumControllerMock);
			}
		});
		
		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.standardMediumControllerMock);
		
		Point2D p = new Point2D.Double(100, 0);
		
		AffineTransform aT = new AffineTransform();
		aT.rotate(Math.toRadians(newAngleRay));
		
		aT.transform(p, p);
		
		double[] directionVectorRayOut = {p.getX(),p.getY()};
		
		double angleBetweenOut = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorPerpendicular, directionVectorRayOut);
		double expectedAngleBetweenOut = this.unit.calculateSnell(angleBetweenIn, refractionIndexStandardMedium, refractionIndexMediumOut);
		
		assertThat(ANTSUtility.roundScale2(newAngleRay), equalTo(ANTSUtility.roundScale2(perpendicularAngle-expectedAngleBetweenOut)));
		
		assert(perpendicularAngle>newAngleRay);
		assert(angleBetweenIn<angleBetweenOut);
		
		mockery.assertIsSatisfied();
	}
	
	@Given("simpleTestFromAMediumWithGreaterRefractionIndexIntoStandardMediumWithoutReflexion")
	public void testSituationB2(Mockery mockery)
	{
		//Setup for Situation B2:
			//perpendicularAngle will be 180�
			//rayAngle will be <270� & >180�
		
			//Perpendicular
			//in java coordinate system!
			double[] startPointPerpendicular = {100,100};//IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
			double[] endPointPerpendicular = {500, 100};//IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
			
			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.OUT);
			double[] directionVectorPerpendicular = perpendicular.getDirectionVector(ANTSDirectionEnum.OUT);
			
			//Ray
			//in java coordinate system!
			double[] startPointRay = {150,120};		
			double[] endPointRay = {100,100};	
			
			final double[] directionVectorRay = {endPointRay[0] - startPointRay[0], endPointRay[1] - startPointRay[1]};
			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
			
			//AngleBetween
			double angleBetweenIn = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
			
			//CriticalAngle
			double criticalAngle = ANTSRefractionComputeUnit.calculateCriticalAngle(this.refractionIndexStandardMedium, this.refractionIndexMediumOut);
			
			assertThat(angleBetweenIn,lessThan(criticalAngle));
			
			assert(rayAngle<270 && rayAngle>180);
			assert(perpendicularAngle==180);
			
		mockery.checking(new Expectations()
		{
			{
			oneOf(rayControllerMock).getCurrentMedium(); 
				will(returnValue(mediumOutControllerMock));
		
			oneOf(rayControllerMock).getCurrentMedium(); 
				will(returnValue(mediumOutControllerMock));
				
			oneOf(mediumOutControllerMock).calculatePerpendicular(rayControllerMock);
				will(returnValue(perpendicular));
				
			oneOf(rayControllerMock).getDirectionVector();
				will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
				
			oneOf(mediumOutControllerMock).getRefractionIndex();
				will(returnValue(refractionIndexMediumOut));
//				
			oneOf(standardMediumControllerMock).getRefractionIndex();
				will(returnValue(refractionIndexStandardMedium));
//				
			oneOf(rayControllerMock).getAngle();
				will(returnValue(rayAngle));
//				
			oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
			oneOf(rayControllerMock).setCurrentMedium(standardMediumControllerMock);
			}
		});
		
		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.standardMediumControllerMock);
		
		Point2D p = new Point2D.Double(100, 0);
		
		AffineTransform aT = new AffineTransform();
		aT.rotate(Math.toRadians(newAngleRay));
		
		aT.transform(p, p);
		
		double[] directionVectorRayOut = {p.getX(),p.getY()};
		
		double angleBetweenOut = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorPerpendicular, directionVectorRayOut);

		assert(perpendicularAngle<newAngleRay);
		assert(angleBetweenIn<angleBetweenOut);
		
		mockery.assertIsSatisfied();
	}
//	
//	@Given("simpleTestFromAMediumWithGreaterRefractionIndexIntoStandardMediumWithoutReflexion")
//	public void testSituationC1(Mockery mockery)
//	{
//		//Setup for Situation C1:
//			//perpendicularAngle will be 270�
//			//rayAngle will be <360� & >270�
//		
//			//Perpendicular
//			//in java coordinate system!
//			double[] startPointPerpendicular = {500,500}; //IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
//			double[] endPointPerpendicular = {500, 100}; //IMPORTANT: The start and endPoint of the perpendicular is always for the case IN the medium!!
//			
//			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
//			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.OUT);
//			double[] directionVectorPerpendicular = perpendicular.getDirectionVector(ANTSDirectionEnum.OUT);
//			
//			//Ray
//			//in java coordinate system!
//			double[] startPointRay = {100,150};		
//			double[] endPointRay = {150,100};	
//			
//			final double[] directionVectorRay = {endPointRay[0] - startPointRay[0], endPointRay[1] - startPointRay[1]};
//			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
//		
//			//AngleBetween
//			double angleBetweenIn = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
//
//			//CriticalAngle
//			double criticalAngle = ANTSRefractionComputeUnit.calculateCriticalAngle(this.refractionIndexStandardMedium, this.refractionIndexMediumOut);
//			
//			assertThat(angleBetweenIn,lessThan(criticalAngle));
//			
//			assert(rayAngle<360 && rayAngle>270);
//			assert(perpendicularAngle==270);
//			
//		mockery.checking(new Expectations()
//		{
//			{
//				{
//					oneOf(rayControllerMock).getCurrentMedium(); 
//						will(returnValue(mediumOutControllerMock));
//				
//					oneOf(rayControllerMock).getCurrentMedium(); 
//						will(returnValue(mediumOutControllerMock));
//						
//					oneOf(mediumOutControllerMock).calculatePerpendicular(rayControllerMock);
//						will(returnValue(perpendicular));
//						
//					oneOf(rayControllerMock).getDirectionVector();
//						will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
//						
//					oneOf(mediumOutControllerMock).getRefractionIndex();
//						will(returnValue(refractionIndexMediumOut));
////						
//					oneOf(standardMediumControllerMock).getRefractionIndex();
//						will(returnValue(refractionIndexStandardMedium));
////						
//					oneOf(rayControllerMock).getAngle();
//						will(returnValue(rayAngle));
////						
//					oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
//					oneOf(rayControllerMock).setCurrentMedium(standardMediumControllerMock);
//				}
//			}
//		});
//		
//		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.standardMediumControllerMock);
//		
//		Point2D p = new Point2D.Double(100, 0);
//		
//		AffineTransform aT = new AffineTransform();
//		aT.rotate(Math.toRadians(newAngleRay));
//		
//		aT.transform(p, p);
//		
//		double[] directionVectorRayOut = {p.getX(),p.getY()};
//		
//		double angleBetweenOut = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorPerpendicular, directionVectorRayOut);
//		
//		assert(perpendicularAngle<newAngleRay);
//		assert(angleBetweenIn<angleBetweenOut);
//		
//		mockery.assertIsSatisfied();
//	}
//	
//	@Given("simpleTestFromAMediumWithGreaterRefractionIndexIntoStandardMedium")
//	public void testSituationC2(Mockery mockery)
//	{
//		//Setup for Situation C2:
//			//perpendicularAngle will be 270�
//			//rayAngle will be <=270� & >=180�
//		
//			//Perpendicular
//			//in java coordinate system!
//			double[] startPointPerpendicular = {500,500};
//			double[] endPointPerpendicular = {500, 100};
//			
//			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
//			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.IN);
//			double[] directionVectorPerpendicular = perpendicular.getDirectionVector(ANTSDirectionEnum.IN);
//			
//			//Ray
//			//in java coordinate system!
//			double[] startPointRay = {150,150};		
//			double[] endPointRay = {100,100};	
//			
//			final double[] directionVectorRay = {endPointRay[0] - startPointRay[0], endPointRay[1] - startPointRay[1]};
//			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
//		
//			//AngleBetween
//			double angleBetweenIn = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
//			
//			assert(rayAngle<=270 && rayAngle>=180);
//			assert(perpendicularAngle==270);
//			
//		mockery.checking(new Expectations()
//		{
//			{
//				oneOf(rayControllerMock).getCurrentMedium(); 
//					will(returnValue(standardMediumControllerMock));
//					
//				oneOf(mediumInControllerMock).calculatePerpendicular(rayControllerMock);
//					will(returnValue(perpendicular));
//					
//				oneOf(rayControllerMock).getDirectionVector();
//					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
//					
//				oneOf(mediumInControllerMock).getRefractionIndex();
//					will(returnValue(refractionIndexMediumIn));
//					
//				oneOf(standardMediumControllerMock).getRefractionIndex();
//					will(returnValue(refractionIndexStandardMedium));
//					
//				oneOf(rayControllerMock).getAngle();
//					will(returnValue(rayAngle));
//					
//				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
//				oneOf(rayControllerMock).setCurrentMedium(mediumInControllerMock);
//			}
//		});
//		
//		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.mediumInControllerMock);
//		
//		Point2D p = new Point2D.Double(100, 0);
//		
//		AffineTransform aT = new AffineTransform();
//		aT.rotate(Math.toRadians(newAngleRay));
//		
//		aT.transform(p, p);
//		
//		double[] directionVectorRayOut = {p.getX(),p.getY()};
//		
//		double angleBetweenOut = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorPerpendicular, directionVectorRayOut);
//		
//		assert(perpendicularAngle>newAngleRay);
//		assert(angleBetweenIn>angleBetweenOut);
//		
//		mockery.assertIsSatisfied();
//	}
//	
//	@Given("simpleTestFromAMediumWithGreaterRefractionIndexIntoStandardMedium")
//	public void testSituationD1(Mockery mockery)
//	{
//		//Setup for Situation D1:
//			//perpendicularAngle will be 0�
//			//rayAngle will be <90� & >0�
//		
//			//Perpendicular
//			//in java coordinate system!
//			double[] startPointPerpendicular = {100,500};
//			double[] endPointPerpendicular = {500, 500};
//			
//			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
//			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.IN);
//			double[] directionVectorPerpendicular = perpendicular.getDirectionVector(ANTSDirectionEnum.IN);
//			
//			//Ray
//			//in java coordinate system!
//			double[] startPointRay = {100,100};		
//			double[] endPointRay = {150,150};	
//			
//			final double[] directionVectorRay = {endPointRay[0] - startPointRay[0], endPointRay[1] - startPointRay[1]};
//			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
//		
//			//AngleBetween
//			double angleBetweenIn = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
//			
//			assert(rayAngle<90&& rayAngle>0);
//			assert(perpendicularAngle==0);
//			
//		mockery.checking(new Expectations()
//		{
//			{
//				oneOf(rayControllerMock).getCurrentMedium(); 
//					will(returnValue(standardMediumControllerMock));
//					
//				oneOf(mediumInControllerMock).calculatePerpendicular(rayControllerMock);
//					will(returnValue(perpendicular));
//					
//				oneOf(rayControllerMock).getDirectionVector();
//					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
//					
//				oneOf(mediumInControllerMock).getRefractionIndex();
//					will(returnValue(refractionIndexMediumIn));
//					
//				oneOf(standardMediumControllerMock).getRefractionIndex();
//					will(returnValue(refractionIndexStandardMedium));
//					
//				oneOf(rayControllerMock).getAngle();
//					will(returnValue(rayAngle));
//					
//				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
//				oneOf(rayControllerMock).setCurrentMedium(mediumInControllerMock);
//			}
//		});
//		
//		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.mediumInControllerMock);
//		
//		Point2D p = new Point2D.Double(100, 0);
//		
//		AffineTransform aT = new AffineTransform();
//		aT.rotate(Math.toRadians(newAngleRay));
//		
//		aT.transform(p, p);
//		
//		double[] directionVectorRayOut = {p.getX(),p.getY()};
//		
//		double angleBetweenOut = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorPerpendicular, directionVectorRayOut);
//		
//		assert(perpendicularAngle<newAngleRay);
//		assert(angleBetweenIn>angleBetweenOut);
//		
//		mockery.assertIsSatisfied();
//	}
//	@Given("simpleTestFromAMediumWithGreaterRefractionIndexIntoStandardMedium")
//	public void testSituationD2(Mockery mockery)
//	{
//		//Setup for Situation D2:
//			//perpendicularAngle will be 0�
//			//rayAngle will be <360� & >=270� or =0�
//		
//			//Perpendicular
//			//in java coordinate system!
//			double[] startPointPerpendicular = {100,500};
//			double[] endPointPerpendicular = {500, 500};
//			
//			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
//			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.IN);
//			double[] directionVectorPerpendicular = perpendicular.getDirectionVector(ANTSDirectionEnum.IN);
//			
//			//Ray
//			//in java coordinate system!
//			double[] startPointRay = {100,150};		
//			double[] endPointRay = {150,100};	
//			
//			final double[] directionVectorRay = {endPointRay[0] - startPointRay[0], endPointRay[1] - startPointRay[1]};
//			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
//		
//			//AngleBetween
//			double angleBetweenIn = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorRay, directionVectorPerpendicular);
//			
//			assert((rayAngle<360 && rayAngle>=270) || rayAngle==0);
//			assert(perpendicularAngle==0);
//			
//		mockery.checking(new Expectations()
//		{
//			{
//				oneOf(rayControllerMock).getCurrentMedium(); 
//					will(returnValue(standardMediumControllerMock));
//					
//				oneOf(mediumInControllerMock).calculatePerpendicular(rayControllerMock);
//					will(returnValue(perpendicular));
//					
//				oneOf(rayControllerMock).getDirectionVector();
//					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
//					
//				oneOf(mediumInControllerMock).getRefractionIndex();
//					will(returnValue(refractionIndexMediumIn));
//					
//				oneOf(standardMediumControllerMock).getRefractionIndex();
//					will(returnValue(refractionIndexStandardMedium));
//					
//				oneOf(rayControllerMock).getAngle();
//					will(returnValue(rayAngle));
//					
//				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
//				oneOf(rayControllerMock).setCurrentMedium(mediumInControllerMock);
//			}
//		});
//		
//		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.mediumInControllerMock);
//
//		Point2D p = new Point2D.Double(100, 0);
//		
//		AffineTransform aT = new AffineTransform();
//		aT.rotate(Math.toRadians(newAngleRay));
//		
//		aT.transform(p, p);
//		
//		double[] directionVectorRayOut = {p.getX(),p.getY()};
//		
//		double angleBetweenOut = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(directionVectorPerpendicular, directionVectorRayOut);
//		
//		assert(perpendicularAngle<newAngleRay);
//		assert(angleBetweenIn>angleBetweenOut);
//		
//		mockery.assertIsSatisfied();
//	}
}
