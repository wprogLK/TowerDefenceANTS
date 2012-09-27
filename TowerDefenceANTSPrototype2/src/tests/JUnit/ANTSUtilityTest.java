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
import org.hamcrest.*;

import enums.ANTSDirectionEnum;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

@RunWith(JExample.class)
public class ANTSUtilityTest
{
	@Test
	public void oneVectorTest()
	{
		
	}
	
	@Test
	public void twoVectorTest()
	{
		
	}
	
	@Given("oneVectorTest")
	public void angleShouldBeLessThan90()
	{
		double[] startPoint = {100,120};
		double[] endPoint = {120,100};
		
		double[] directionVector = ANTSUtility.computeDirectionVector(startPoint, endPoint);
		
		double angle = ANTSUtility.computeAngleOfOneVector(directionVector);
		
		ANTSStream.printTest("angle is (0-90)= " + angle);
		
		assertTrue(angle>=0 && angle<=90);
	}
	
	@Given("oneVectorTest")
	public void angleShouldBeLessThan180AndGreaterThan90()
	{
		double[] startPoint = {120,120};
		double[] endPoint = {100,100};
		
		double[] directionVector = ANTSUtility.computeDirectionVector(startPoint, endPoint);
		
		double angle = ANTSUtility.computeAngleOfOneVector(directionVector);
		
		ANTSStream.printTest("angle is (90-180)= " + angle);
		
		assertTrue(angle>=90 && angle<=180);
	}
	
	@Given("oneVectorTest")
	public void angleShouldBeLessThan270AndGreaterThan180()
	{
		double[] startPoint = {120,100};
		double[] endPoint = {100,120};
		
		double[] directionVector = ANTSUtility.computeDirectionVector(startPoint, endPoint);
		
		double angle = ANTSUtility.computeAngleOfOneVector(directionVector);
		
		ANTSStream.printTest("angle is (180-270)= " + angle);
		
		assertTrue(angle>=180 && angle<=270);
	}
	
	@Given("oneVectorTest")
	public void angleShouldBeLessThan360AndGreaterThan270()
	{
		double[] startPoint = {100,100};
		double[] endPoint = {120,120};
		
		double[] directionVector = ANTSUtility.computeDirectionVector(startPoint, endPoint);
		
		double angle = ANTSUtility.computeAngleOfOneVector(directionVector);
		
		ANTSStream.printTest("angle is (270-360)= " + angle);
		
		assertTrue(angle>=270 && angle<360);
	}
	
}
