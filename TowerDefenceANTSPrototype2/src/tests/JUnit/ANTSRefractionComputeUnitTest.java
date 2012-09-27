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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JExample.class)
public class ANTSRefractionComputeUnitTest
{
	private Mockery mockery;
	
	private ANTSRefractionComputeUnit unit;
	
	private ANTSIStandardMediumController standardMediumControllerMock;
	private ANTSIRayController rayControllerMock;
	
	private ANTSIMediumController mediumInControllerMock;
	private ANTSPerpendicular perpendicular;
	
	@Before
	public void setUp()
	{
		this.mockery = new JUnit4Mockery();
		
		this.standardMediumControllerMock = this.mockery.mock(ANTSIStandardMediumController.class);
		this.rayControllerMock = this.mockery.mock(ANTSIRayController.class);
		
		this.mediumInControllerMock = this.mockery.mock(ANTSIMediumController.class);
		
		this.unit = new ANTSRefractionComputeUnit(this.standardMediumControllerMock);
	}
	
	@Test
	public void simpleMockTest()
	{
		this.setUp();
	}
	
	@Given("simpleMockTest")
	public void setRefractionIndexOfStandardMedium()
	{
		ANTSStream.print("set");
//		this.mockery.checking(new Expectations()
//		{
//			{
////				oneOf(standardMediumControllerMock).setRefractionIndex(1.0);
//			}
//		});
		
//		this.standardMediumControllerMock.setRefractionIndex(1.0);
		
//		this.mockery.assertIsSatisfied();
		
		assert(!mediumInControllerMock.equals(this.standardMediumControllerMock));
	}
	
	@Given("setRefractionIndexOfStandardMedium")
	public void simpleTestFromStandardMediumIntoAnotherWithGreaterRefractionIndex()
	{
		//Setup for Situation A1:
			//perpendicularAngle will be 90°
			//rayAngle will be <=90° & >=0°
		
			//Perpendicular
			double[] startPointPerpendicular = {100, 100};
			double[] endPointPerpendicular = {100, 500};
			double perpendicularAngle = 90;
			
			this.perpendicular = new ANTSPerpendicular(startPointPerpendicular, endPointPerpendicular);
			
			//Ray
			double[] startPointRay = {100,100};		
			double[] endPointRay = {150,150};	
			final double[] directionVectorRay = {endPointRay[0] - startPointRay[0], endPointRay[1] - startPointRay[1]};
			final double rayAngle = ANTSUtility.computeAngleOfOneVector(directionVectorRay);
		
			//MediumIn
			final double refractionIndexMediumIn = 1.5;
			
			//StandardMedium
			final double refractionIndexStandardMedium = 1;
		
		this.mockery.checking(new Expectations()
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
		
		double angle = this.unit.calculateAngle(this.rayControllerMock,  this.mediumInControllerMock);
		
		ANTSStream.print("ANGLE OUT TEST = " + angle);
		
		this.mockery.assertIsSatisfied();
	}
}
