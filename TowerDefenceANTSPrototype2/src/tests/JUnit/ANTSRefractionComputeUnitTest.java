package tests.JUnit;

import interfaces.ANTSIRayController;
import interfaces.ANTSIStandardMediumController;
import interfaces.medium.ANTSIMediumController;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
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
public class ANTSRefractionComputeUnitTest
{
//	private Mockery mockery;
	
	private ANTSRefractionComputeUnit unit;
	
	private ANTSIStandardMediumController standardMediumControllerMock;
	private ANTSIRayController rayControllerMock;
	
	private ANTSIMediumController mediumInControllerMock;
	
	//MediumIn
	private double refractionIndexMediumIn = 1.5;
	
	//StandardMedium
	private double refractionIndexStandardMedium = 1;
	
	@Before
	public Mockery setUp()
	{
		Mockery mockery = new JUnit4Mockery();
		
		this.standardMediumControllerMock = mockery.mock(ANTSIStandardMediumController.class);
		this.rayControllerMock = mockery.mock(ANTSIRayController.class);
		
		this.mediumInControllerMock = mockery.mock(ANTSIMediumController.class);
		
		this.unit = new ANTSRefractionComputeUnit(this.standardMediumControllerMock);
		
		return mockery;
	}
	
	@Test
	public Mockery simpleTestFromStandardMediumIntoAnotherWithGreaterRefractionIndex()
	{
		return this.setUp();
	}
	
	@Given("simpleTestFromStandardMediumIntoAnotherWithGreaterRefractionIndex")
	public void testSituationA1(Mockery mockery)
	{
		ANTSStream.printTest("========================A1=========================");
		
		//Setup for Situation A1:
			//perpendicularAngle will be 90°
			//rayAngle will be <=90° & >=0°
		
			//Perpendicular
			double[] startPointPerpendicular = {100, 100};
			double[] endPointPerpendicular = {100, 500};
			
			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.IN);
			
			//Ray
			double[] startPointRay = {100,100};		
			double[] endPointRay = {150,100};	//TODO: WARNING THIS IS NOT THE JAVA COORDINATE SYSTEM!!! BUT IT HAS TO BE THE JAVA COORDINATE SYSTEM, OTHERWAYS THE RAY WILL BE PAINTED WRONG!!
			final double[] directionVectorRay = ANTSUtility.computeDirectionVector(startPointRay, endPointRay);
			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
			
			//AngleBetween
			double angleBetweenIn = ANTSUtility.computeAngleBetween(perpendicularAngle, rayAngle);
			
			ANTSStream.printTest("++++++++++++++++ray Angle A1 +++++++++++++++++++" + rayAngle + " perp: "+ perpendicularAngle);
			
			assert(rayAngle<=90 && rayAngle>=0);
			assert(perpendicularAngle==90);

		mockery.checking(new Expectations()
		{
			{
				oneOf(rayControllerMock).getCurrentMedium(); 
					will(returnValue(standardMediumControllerMock));
					
				oneOf(mediumInControllerMock).calculatePerpendicular(rayControllerMock);
					will(returnValue(perpendicular));
					
				oneOf(rayControllerMock).getDirectionVector();
					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
					
				oneOf(mediumInControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexMediumIn));
					
				oneOf(standardMediumControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexStandardMedium));
					
				oneOf(rayControllerMock).getAngle();
					will(returnValue(rayAngle));
					
				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
				oneOf(rayControllerMock).setCurrentMedium(mediumInControllerMock);
			}
		});
		
		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.mediumInControllerMock);
		
		double angleBetweenOut = ANTSUtility.computeAngleBetween(newAngleRay, perpendicularAngle);
		
		ANTSStream.printTest("ANGLE OUT TEST = " + newAngleRay);
		ANTSStream.printTest("ANGLE B IN = " + angleBetweenIn);
		ANTSStream.printTest("ANGLE B OUT = " + angleBetweenOut);
		
		assert(perpendicularAngle>newAngleRay);
		assert(angleBetweenIn>angleBetweenOut);
		
		mockery.assertIsSatisfied();
		
		ANTSStream.printTest("=================================================");
	}
	
	@Given("simpleTestFromStandardMediumIntoAnotherWithGreaterRefractionIndex")
	public void testSituationA2(Mockery mockery)
	{
		ANTSStream.printTest("========================A2=========================");
		
		//Setup for Situation A2:
			//perpendicularAngle will be 90°
			//rayAngle will be <180° & >90°
		
			//Perpendicular
			double[] startPointPerpendicular = {100, 100};
			double[] endPointPerpendicular = {100, 500};
			
			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.IN);
			
			//Ray
			double[] startPointRay = {150,100};		
			double[] endPointRay = {100,150};	
			final double[] directionVectorRay = {endPointRay[0] - startPointRay[0], endPointRay[1] - startPointRay[1]};
			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
		
//			ANTSStream.printTest("++++++++++++++++ray Angle +++++++++++++++++++" + rayAngle + " perp: "+ perpendicularAngle);
			
			//AngleBetween
			double angleBetweenIn = ANTSUtility.computeAngleBetween(perpendicularAngle, rayAngle);

			assert(rayAngle<180 && rayAngle>90);
			assert(perpendicularAngle==90);
			
		mockery.checking(new Expectations()
		{
			{
				oneOf(rayControllerMock).getCurrentMedium(); 
					will(returnValue(standardMediumControllerMock));
					
				oneOf(mediumInControllerMock).calculatePerpendicular(rayControllerMock);
					will(returnValue(perpendicular));
					
				oneOf(rayControllerMock).getDirectionVector();
					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
					
				oneOf(mediumInControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexMediumIn));
					
				oneOf(standardMediumControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexStandardMedium));
					
				oneOf(rayControllerMock).getAngle();
					will(returnValue(rayAngle));
					
				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
				oneOf(rayControllerMock).setCurrentMedium(mediumInControllerMock);
			}
		});
		
		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.mediumInControllerMock);
		
		double angleBetweenOut = ANTSUtility.computeAngleBetween(newAngleRay, perpendicularAngle);
		
		ANTSStream.printTest("ANGLE OUT TEST = " + newAngleRay);
		ANTSStream.printTest("ANGLE B IN = " + angleBetweenIn);
		ANTSStream.printTest("ANGLE B OUT = " + angleBetweenOut);
		
		assert(perpendicularAngle<newAngleRay);
		assert(angleBetweenIn>angleBetweenOut);
		
		mockery.assertIsSatisfied();
		
		ANTSStream.printTest("=================================================");
	}
	

	@Given("simpleTestFromStandardMediumIntoAnotherWithGreaterRefractionIndex")
	public void testSituationB1(Mockery mockery)
	{
		ANTSStream.printTest("========================B1=========================");
		
		//Setup for Situation B1:
			//perpendicularAngle will be 180°
			//rayAngle will be <=180° & >=90°
		
			//Perpendicular
			double[] startPointPerpendicular = {500,100 };
			double[] endPointPerpendicular = {100, 100};
			
			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.IN);
			
			//Ray
			double[] startPointRay = {150,100};		
			double[] endPointRay = {100,150};	
			final double[] directionVectorRay = {endPointRay[0] - startPointRay[0], endPointRay[1] - startPointRay[1]};
			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
		
			ANTSStream.printTest("++++++++++++++++ray Angle +++++++++++++++++++" + rayAngle);
			
			//AngleBetween
			double angleBetweenIn = ANTSUtility.computeAngleBetween(perpendicularAngle, rayAngle);

			assert(rayAngle<=180 && rayAngle>=90);
			assert(perpendicularAngle==180);
			
		mockery.checking(new Expectations()
		{
			{
				oneOf(rayControllerMock).getCurrentMedium(); 
					will(returnValue(standardMediumControllerMock));
					
				oneOf(mediumInControllerMock).calculatePerpendicular(rayControllerMock);
					will(returnValue(perpendicular));
					
				oneOf(rayControllerMock).getDirectionVector();
					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
					
				oneOf(mediumInControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexMediumIn));
					
				oneOf(standardMediumControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexStandardMedium));
					
				oneOf(rayControllerMock).getAngle();
					will(returnValue(rayAngle));
					
				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
				oneOf(rayControllerMock).setCurrentMedium(mediumInControllerMock);
			}
		});
		
		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.mediumInControllerMock);
		
		double angleBetweenOut = ANTSUtility.computeAngleBetween(newAngleRay, perpendicularAngle);
		
		ANTSStream.printTest("ANGLE OUT TEST = " + newAngleRay);
		ANTSStream.printTest("ANGLE B IN = " + angleBetweenIn);
		ANTSStream.printTest("ANGLE B OUT = " + angleBetweenOut);
		
		assert(perpendicularAngle>newAngleRay);
		assert(angleBetweenIn>angleBetweenOut);
		
		mockery.assertIsSatisfied();
		
		ANTSStream.printTest("=================================================");
	}
	
	@Given("simpleTestFromStandardMediumIntoAnotherWithGreaterRefractionIndex")
	public void testSituationB2(Mockery mockery)
	{
		ANTSStream.printTest("========================B2=========================");
		
		//Setup for Situation B2:
			//perpendicularAngle will be 180°
			//rayAngle will be <270° & >180°
		
			//Perpendicular
			double[] startPointPerpendicular = {500,100};
			double[] endPointPerpendicular = {100, 100};
			
			final ANTSPerpendicular perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
			double perpendicularAngle = perpendicular.getAngle(ANTSDirectionEnum.IN);
			
			//Ray
			double[] startPointRay = {110,110};		
			double[] endPointRay = {100,100};	
			
			final double[] directionVectorRay = {endPointRay[0] - startPointRay[0], endPointRay[1] - startPointRay[1]};
			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
		
			ANTSStream.printTest("++++++++++++++++ray Angle +++++++++++++++++++" + rayAngle +" dire[x] = " + directionVectorRay[0] + " dire[y] = " + directionVectorRay[1]);
			
			//AngleBetween
			double angleBetweenIn = ANTSUtility.computeAngleBetween(perpendicularAngle, rayAngle);

			assert(rayAngle<270 && rayAngle>180);
			assert(perpendicularAngle==180);
			
		mockery.checking(new Expectations()
		{
			{
				oneOf(rayControllerMock).getCurrentMedium(); 
					will(returnValue(standardMediumControllerMock));
					
				oneOf(mediumInControllerMock).calculatePerpendicular(rayControllerMock);
					will(returnValue(perpendicular));
					
				oneOf(rayControllerMock).getDirectionVector();
					will(returnValue(directionVectorRay)); //TODO: is it necessary ?, because it's only for calculating the angle between ray and perpendicular
					
				oneOf(mediumInControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexMediumIn));
					
				oneOf(standardMediumControllerMock).getRefractionIndex();
					will(returnValue(refractionIndexStandardMedium));
					
				oneOf(rayControllerMock).getAngle();
					will(returnValue(rayAngle));
					
				oneOf(rayControllerMock).setAngle(with(lessThan(360.0)));
				oneOf(rayControllerMock).setCurrentMedium(mediumInControllerMock);
			}
		});
		
		double newAngleRay = this.unit.calculateAngle(this.rayControllerMock,  this.mediumInControllerMock);
		
		double angleBetweenOut = ANTSUtility.computeAngleBetween(newAngleRay, perpendicularAngle);
		
		ANTSStream.printTest("ANGLE OUT TEST = " + newAngleRay);
		ANTSStream.printTest("ANGLE B IN = " + angleBetweenIn);
		ANTSStream.printTest("ANGLE B OUT = " + angleBetweenOut);
		
		assert(perpendicularAngle<newAngleRay);
		assert(angleBetweenIn>angleBetweenOut);
		
		mockery.assertIsSatisfied();
		
		ANTSStream.printTest("=================================================");
	}
}
