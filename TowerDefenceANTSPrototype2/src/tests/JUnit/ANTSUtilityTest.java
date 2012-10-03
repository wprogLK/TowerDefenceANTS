package tests.JUnit;

import org.junit.Test;
import org.junit.runner.RunWith;

import basics.ANTSUtility;
import basics.ANTSDevelopment.ANTSStream;

import ch.unibe.jexample.*;
import enums.ANTSQuadrantEnum;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

@RunWith(JExample.class)
public class ANTSUtilityTest
{
	///////////
	//VECTORS//
	///////////
	
		private double[] directionVectorSmallerQuadrantA;
		private double[] directionVectorBiggerQuadrantA;
		private double[] fixDirectionVectorQuadrantA;
		
		private double[] directionVectorSmallerQuadrantB;
		private double[] directionVectorBiggerQuadrantB;
		private double[] fixDirectionVectorQuadrantB;
		
		private double[] directionVectorSmallerQuadrantC;
		private double[] directionVectorBiggerQuadrantC;
		private double[] fixDirectionVectorQuadrantC;
		
		private double[] directionVectorSmallerQuadrantD;
		private double[] directionVectorBiggerQuadrantD;
		private double[] fixDirectionVectorQuadrantD;
	
		//with java coordinate system		
		//			|
		//			|
		//		C	|	D
		//			|
		//--------------------> x
		//			|
		//			|
		//		B	|	A
		//			|
		//			v
		//			y	
		
	//////////
	//ANGLES//
	//////////
		
		private double smallerAngleQuadrantA;
		private double biggerAngleQuadrantA;
		private double fixAngleQuadrantA;
		
		private double smallerAngleQuadrantB;
		private double biggerAngleQuadrantB;
		private double fixAngleQuadrantB;
		
		private double smallerAngleQuadrantC;
		private double biggerAngleQuadrantC;
		private double fixAngleQuadrantC;
		
		private double smallerAngleQuadrantD;
		private double biggerAngleQuadrantD;
		private double fixAngleQuadrantD;
	
	////////////////////////
	//MIN, AVG & MAX ANGLE// 
	////////////////////////
		
		private final double minAngleQuadrantA = 0;
		private final double avgAngleQuadrantA = 45;
		private final double maxAngleQuadrantA = 90;
	
		private final double minAngleQuadrantB = 90;
		private final double avgAngleQuadrantB = 135;
		private final double maxAngleQuadrantB = 180;
		
		private final double minAngleQuadrantC = 180;
		private final double avgAngleQuadrantC = 225;
		private final double maxAngleQuadrantC = 270;
		
		private final double minAngleQuadrantD = 270;
		private final double avgAngleQuadrantD = 315;
		private final double maxAngleQuadrantD = 360;
		
	//////////////
	//SETUP TEST//
	//////////////
	
	/*
	 * for avoiding slips in the setup
	 * 
	 * Comment: using the method computeAngleOfOneVector is allowed if oneVectorTest are green!
	 */
	
	@Test
	public void testSetup()
	{
		this.setUp();
	}
	
	@Given("testSetup")
	public void smallerAngleCheckInQuadrantA()
	{
		assertThat(this.smallerAngleQuadrantA, lessThan(this.avgAngleQuadrantA));
		assertThat(this.smallerAngleQuadrantA, greaterThanOrEqualTo(this.minAngleQuadrantA));
	}
	
	@Given("testSetup")
	public void biggerAngleCheckInQuadrantA()
	{
		assertThat(this.biggerAngleQuadrantA, lessThan(this.maxAngleQuadrantA));
		assertThat(this.biggerAngleQuadrantA, greaterThanOrEqualTo(this.avgAngleQuadrantA));
	}
	
	@Given("testSetup")
	public void smallerAngleShouldBeLessThanBiggerAngleInQuadrantA()
	{
		assertThat(this.smallerAngleQuadrantA,lessThan(biggerAngleQuadrantA));
	}
	
	@Given("smallerAngleShouldBeLessThanBiggerAngleInQuadrantA")
	public void fixAngleShouldBetweenSmallerAngleAndBiggerAngleInQuadrantA()
	{
		assertThat(this.fixAngleQuadrantA, greaterThan(this.smallerAngleQuadrantA));
		assertThat(this.fixAngleQuadrantA, lessThan(this.biggerAngleQuadrantA));
	}
	
	@Given("testSetup")
	public void smallerAngleCheckInQuadrantB()
	{
		assertThat(this.smallerAngleQuadrantB, lessThan(this.avgAngleQuadrantB));
		assertThat(this.smallerAngleQuadrantB, greaterThanOrEqualTo(this.minAngleQuadrantB));
	}
	
	@Given("testSetup")
	public void biggerAngleCheckInQuadrantB()
	{
		assertThat(this.biggerAngleQuadrantB, lessThan(this.maxAngleQuadrantB));
		assertThat(this.biggerAngleQuadrantB, greaterThanOrEqualTo(this.avgAngleQuadrantB));
	}
	
	@Given("testSetup")
	public void smallerAngleShouldBeLessThanBiggerAngleInQuadrantB()
	{
		assertThat(this.smallerAngleQuadrantB,lessThan(biggerAngleQuadrantB));
	}
	
	@Given("smallerAngleShouldBeLessThanBiggerAngleInQuadrantB")
	public void fixAngleShouldBetweenSmallerAngleAndBiggerAngleInQuadrantB()
	{
		assertThat(this.fixAngleQuadrantB, greaterThan(this.smallerAngleQuadrantB));
		assertThat(this.fixAngleQuadrantB, lessThan(this.biggerAngleQuadrantB));
	}
	
	@Given("testSetup")
	public void smallerAngleCheckInQuadrantC()
	{
		assertThat(this.smallerAngleQuadrantC, lessThan(this.avgAngleQuadrantC));
		assertThat(this.smallerAngleQuadrantC, greaterThanOrEqualTo(this.minAngleQuadrantC));
	}
	
	@Given("testSetup")
	public void biggerAngleCheckInQuadrantC()
	{
		assertThat(this.biggerAngleQuadrantC, lessThan(this.maxAngleQuadrantC));
		assertThat(this.biggerAngleQuadrantC, greaterThanOrEqualTo(this.avgAngleQuadrantC));
	}
	
	@Given("testSetup")
	public void smallerAngleShouldBeLessThanBiggerAngleInQuadrantC()
	{
		assertThat(this.smallerAngleQuadrantC,lessThan(biggerAngleQuadrantC));
	}

	@Given("smallerAngleShouldBeLessThanBiggerAngleInQuadrantC")
	public void fixAngleShouldBetweenSmallerAngleAndBiggerAngleInQuadrantC()
	{
		assertThat(this.fixAngleQuadrantC, greaterThan(this.smallerAngleQuadrantC));
		assertThat(this.fixAngleQuadrantC, lessThan(this.biggerAngleQuadrantC));
	}
	
	@Given("testSetup")
	public void smallerAngleCheckInQuadrantD()
	{
		assertThat(this.smallerAngleQuadrantD, lessThan(this.avgAngleQuadrantD));
		assertThat(this.smallerAngleQuadrantD, greaterThanOrEqualTo(this.minAngleQuadrantD));
	}
	
	@Given("testSetup")
	public void biggerAngleCheckInQuadrantD()
	{
		assertThat(this.biggerAngleQuadrantD, lessThan(this.maxAngleQuadrantD));
		assertThat(this.biggerAngleQuadrantD, greaterThanOrEqualTo(this.avgAngleQuadrantD));
	}
	
	@Given("testSetup")
	public void smallerAngleShouldBeLessThanBiggerAngleInQuadrantD()
	{
		assertThat(this.smallerAngleQuadrantD,lessThan(biggerAngleQuadrantD));
	}
	
	@Given("smallerAngleShouldBeLessThanBiggerAngleInQuadrantD")
	public void fixAngleShouldBetweenSmallerAngleAndBiggerAngleInQuadrantD()
	{
		assertThat(this.fixAngleQuadrantD, greaterThan(this.smallerAngleQuadrantD));
		assertThat(this.fixAngleQuadrantD, lessThan(this.biggerAngleQuadrantD));
	}
	
	//////////////
	//VECTORTEST//
	//////////////
	
		//////////////////
		//OneVectorTests//
		//////////////////
	
	@Test 
	public void oneVectorTests()
	{
		this.setUp();
	}
	
	@Given("oneVectorTests")
	public void angleShouldBeLessThan90OneVectorTest() //QuadrantA
	{
		double angle = ANTSUtility.computeAngleOfOneVector(this.fixDirectionVectorQuadrantA);
		
		assertTrue(angle>=0 && angle<=90);
	}
	
	@Given("oneVectorTests") //QuadrantB
	public void angleShouldBeLessThan180AndGreaterThan90OneVectorTest()
	{
		double angle = ANTSUtility.computeAngleOfOneVector(this.fixDirectionVectorQuadrantB);
		
		assertTrue(angle>=90 && angle<=180);
	}
	
	@Given("oneVectorTests") //QuadrantC
	public void angleShouldBeLessThan270AndGreaterThan180OneVectorTest()
	{
		double angle = ANTSUtility.computeAngleOfOneVector(this.fixDirectionVectorQuadrantC);
		
		assertTrue(angle>=180 && angle<=270);
	}
	
	@Given("oneVectorTests") //QuadrantD
	public void angleShouldBeLessThan360AndGreaterThan270OneVectorTest()
	{
		//With java coordinates
		
		double angle = ANTSUtility.computeAngleOfOneVector(this.fixDirectionVectorQuadrantD);
		
		assertTrue(angle>=270 && angle<360);
	}
	
	//////////////////
	//TwoVectorTests//
	//////////////////

	@Test
	public void twoVectorTests()
	{
		this.setUp();
	}
	
	//////////////
	//QUADRANT A//
	//////////////
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantAAndVectorInQuadrantA()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantA, this.directionVectorBiggerQuadrantA);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantA, this.directionVectorSmallerQuadrantA);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantA, this.fixAngleQuadrantA);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantA, this.fixAngleQuadrantA);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantAAndVectorInQuadrantB()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantA, this.directionVectorBiggerQuadrantB);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantA, this.directionVectorSmallerQuadrantB);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantB, this.fixAngleQuadrantA);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantB, this.fixAngleQuadrantA);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	

	@Given("twoVectorTests")
	public void fixVectorQuadrantAAndVectorInQuadrantC()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantA, this.directionVectorBiggerQuadrantC);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantA, this.directionVectorSmallerQuadrantC);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantC, this.fixAngleQuadrantA);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantC, this.fixAngleQuadrantA);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantAAndVectorInQuadrantD()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantA, this.directionVectorBiggerQuadrantD);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantA, this.directionVectorSmallerQuadrantD);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantD, this.fixAngleQuadrantA);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantD, this.fixAngleQuadrantA);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	//////////////
	//QUADRANT B//
	//////////////
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantBAndVectorInQuadrantA()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantB, this.directionVectorBiggerQuadrantA);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantB, this.directionVectorSmallerQuadrantA);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantA, this.fixAngleQuadrantB);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantA, this.fixAngleQuadrantB);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantBAndVectorInQuadrantB()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantB, this.directionVectorBiggerQuadrantB);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantB, this.directionVectorSmallerQuadrantB);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantB, this.fixAngleQuadrantB);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantB, this.fixAngleQuadrantB);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	

	@Given("twoVectorTests")
	public void fixVectorQuadrantBAndVectorInQuadrantC()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantB, this.directionVectorBiggerQuadrantC);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantB, this.directionVectorSmallerQuadrantC);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantC, this.fixAngleQuadrantB);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantC, this.fixAngleQuadrantB);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantBAndVectorInQuadrantD()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantB, this.directionVectorBiggerQuadrantD);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantB, this.directionVectorSmallerQuadrantD);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantD, this.fixAngleQuadrantB);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantD, this.fixAngleQuadrantB);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	//////////////
	//QUADRANT C//
	//////////////
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantCAndVectorInQuadrantA()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantC, this.directionVectorBiggerQuadrantA);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantC, this.directionVectorSmallerQuadrantA);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantA, this.fixAngleQuadrantC);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantA, this.fixAngleQuadrantC);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantCAndVectorInQuadrantB()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantC, this.directionVectorBiggerQuadrantB);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantC, this.directionVectorSmallerQuadrantB);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantB, this.fixAngleQuadrantC);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantB, this.fixAngleQuadrantC);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	

	@Given("twoVectorTests")
	public void fixVectorQuadrantCAndVectorInQuadrantC()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantC, this.directionVectorBiggerQuadrantC);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantC, this.directionVectorSmallerQuadrantC);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantC, this.fixAngleQuadrantC);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantC, this.fixAngleQuadrantC);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantCAndVectorInQuadrantD()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantC, this.directionVectorBiggerQuadrantD);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantC, this.directionVectorSmallerQuadrantD);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantD, this.fixAngleQuadrantC);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantD, this.fixAngleQuadrantC);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	//////////////
	//QUADRANT D//
	//////////////
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantDAndVectorInQuadrantA()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantD, this.directionVectorBiggerQuadrantA);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantD, this.directionVectorSmallerQuadrantA);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantA, this.fixAngleQuadrantD);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantA, this.fixAngleQuadrantD);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantDAndVectorInQuadrantB()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantD, this.directionVectorBiggerQuadrantB);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantD, this.directionVectorSmallerQuadrantB);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantB, this.fixAngleQuadrantD);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantB, this.fixAngleQuadrantD);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	

	@Given("twoVectorTests")
	public void fixVectorQuadrantDAndVectorInQuadrantC()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantD, this.directionVectorBiggerQuadrantC);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantD, this.directionVectorSmallerQuadrantC);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantC, this.fixAngleQuadrantD);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantC, this.fixAngleQuadrantD);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	@Given("twoVectorTests")
	public void fixVectorQuadrantDAndVectorInQuadrantD()
	{
		double angleBigger = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantD, this.directionVectorBiggerQuadrantD);
		double angleSmaller = ANTSUtility.computeAngleBetweenTwoRealDirectionVectors(this.fixDirectionVectorQuadrantD, this.directionVectorSmallerQuadrantD);
		
		assertTrue(angleBigger>=0 && angleBigger<=180);
		assertTrue(angleSmaller>=0 && angleSmaller<=180);
		
		double expectedAngleBigger = ANTSUtility.angleBetweenToAngles(this.biggerAngleQuadrantD, this.fixAngleQuadrantD);
		double expectedAngleSmaller = ANTSUtility.angleBetweenToAngles(this.smallerAngleQuadrantD, this.fixAngleQuadrantD);
		
		assertThat(ANTSUtility.roundScale2(angleBigger),equalTo(ANTSUtility.roundScale2(expectedAngleBigger)));
		assertThat(ANTSUtility.roundScale2(angleSmaller),equalTo(ANTSUtility.roundScale2(expectedAngleSmaller)));
	}
	
	////////////////////
	//TEST CALCULATION//
	////////////////////
	
	@Test
	public void calculationAngleOfTwoAnglesTest1()
	{	
		double alpha = 360;
		double beta = 360;
		double expected = 0;
		
		double stepSize = 10;
		
		while(beta>180)
		{
			assertThat(ANTSUtility.angleBetweenToAngles(alpha, beta),equalTo(expected));
			beta -= stepSize;
			expected += stepSize;
		}
		
		while(beta>0)
		{
			assertThat(ANTSUtility.angleBetweenToAngles(alpha, beta),equalTo(expected));
			beta -= stepSize;
			expected -= stepSize;
		}
		
	}
	
	@Test
	public void calculationAngleOfTwoAnglesTest2()
	{	
		double alpha = 0;
		double beta = 360;
		double expected = 0;
		
		double stepSize = 10;
		
		while(beta>270)
		{
			assertThat(ANTSUtility.angleBetweenToAngles(alpha, beta),equalTo(expected));
			beta -= stepSize;
			alpha += stepSize;
			
			expected += stepSize;
			expected += stepSize;
		}

		while(beta>=180)
		{
			assertThat(ANTSUtility.angleBetweenToAngles(alpha, beta),equalTo(expected));
			beta -= stepSize;
			alpha += stepSize;
			
			expected -= stepSize;
			expected -= stepSize;
		}
	}
	
	
	//////////////////
	//QUADRANTS TEST//
	//////////////////
	
	@Test
	public void quadrantOfAngle0ShouldBeQuadrantA()
	{
		assertThat(ANTSUtility.getQuadrantOfAngle(0.0),equalTo(ANTSQuadrantEnum.A));
	}
	
	@Test
	public void quadrantOfAngle89ShouldBeQuadrantA()
	{
		assertThat(ANTSUtility.getQuadrantOfAngle(89.0),equalTo(ANTSQuadrantEnum.A));
	}
	
	@Test
	public void quadrantOfAngle90ShouldBeQuadrantB()
	{
		assertThat(ANTSUtility.getQuadrantOfAngle(90.0),equalTo(ANTSQuadrantEnum.B));
	}
	
	@Test
	public void quadrantOfAngle179ShouldBeQuadrantB()
	{
		assertThat(ANTSUtility.getQuadrantOfAngle(179.0),equalTo(ANTSQuadrantEnum.B));
	}
	
	@Test
	public void quadrantOfAngle180ShouldBeQuadrantC()
	{
		assertThat(ANTSUtility.getQuadrantOfAngle(180.0),equalTo(ANTSQuadrantEnum.C));
	}
	
	@Test
	public void quadrantOfAngle269ShouldBeQuadrantC()
	{
		assertThat(ANTSUtility.getQuadrantOfAngle(269.0),equalTo(ANTSQuadrantEnum.C));
	}
	
	@Test
	public void quadrantOfAngle270ShouldBeQuadrantD()
	{
		assertThat(ANTSUtility.getQuadrantOfAngle(270.0),equalTo(ANTSQuadrantEnum.D));
	}
	
	@Test
	public void quadrantOfAngle359ShouldBeQuadrantD()
	{
		assertThat(ANTSUtility.getQuadrantOfAngle(359.0),equalTo(ANTSQuadrantEnum.D));
	}
	
//	@Test
//	public void quadrantOfAngle360ShouldBeQuadrantA()
//	{
//		assertThat(ANTSUtility.getQuadrantOfAngle(360.0),equalTo(ANTSQuadrantEnum.A));
//	}
//	
//	@Test
//	public void quadrantOfAngle450ShouldBeQuadrantB()
//	{
//		assertThat(ANTSUtility.getQuadrantOfAngle(450.0),equalTo(ANTSQuadrantEnum.B));
//	}
//	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	
	private void setUp()
	{
		this.setUpVectorsForQuadrantA();
		this.setUpVectorsForQuadrantB();
		this.setUpVectorsForQuadrantC();
		this.setUpVectorsForQuadrantD();
		
		this.setUpAngleForQuadrantA();
		this.setUpAngleForQuadrantB();
		this.setUpAngleForQuadrantC();
		this.setUpAngleForQuadrantD();
	}
	
	/////////////////
	//Setup vectors//
	/////////////////
	
	private void setUpVectorsForQuadrantA() 
	{
		//With java coordinates
		double[] startPointSmallerVector = {100,100};
		double[] endPointSmallerVector = {110,105};
		
		this.directionVectorSmallerQuadrantA = ANTSUtility.computeDirectionVector(startPointSmallerVector, endPointSmallerVector);
		
		//With java coordinates
		double[] startPointBiggerVector = {100,100};
		double[] endPointBiggerVector = {110,140};
		
		this.directionVectorBiggerQuadrantA = ANTSUtility.computeDirectionVector(startPointBiggerVector, endPointBiggerVector);
		
		//With java coordinates
		double[] startPointFixVector = {100,100};
		double[] endPointFixVector = {120,130};
		
		this.fixDirectionVectorQuadrantA = ANTSUtility.computeDirectionVector(startPointFixVector, endPointFixVector);
	}
	
	private void setUpVectorsForQuadrantB() 
	{
		//With java coordinates
		double[] startPointSmallerVector = {120,100};
		double[] endPointSmallerVector = {100,140};
		
		this.directionVectorSmallerQuadrantB = ANTSUtility.computeDirectionVector(startPointSmallerVector, endPointSmallerVector);
		
		//With java coordinates
		double[] startPointBiggerVector = {120,100};
		double[] endPointBiggerVector = {100,110};
		
		this.directionVectorBiggerQuadrantB = ANTSUtility.computeDirectionVector(startPointBiggerVector, endPointBiggerVector);
		
		//With java coordinates
		double[] startPointFixVector = {120,100};
		double[] endPointFixVector = {100,120};
		
		this.fixDirectionVectorQuadrantB = ANTSUtility.computeDirectionVector(startPointFixVector, endPointFixVector);
	}
	
	private void setUpVectorsForQuadrantC() 
	{
		//With java coordinates
		double[] startPointSmallerVector = {120,120};
		double[] endPointSmallerVector = {100,120};
		
		this.directionVectorSmallerQuadrantC = ANTSUtility.computeDirectionVector(startPointSmallerVector, endPointSmallerVector);
		
		//With java coordinates
		double[] startPointBiggerVector = {120,120};
		double[] endPointBiggerVector = {100,100};
		
		this.directionVectorBiggerQuadrantC = ANTSUtility.computeDirectionVector(startPointBiggerVector, endPointBiggerVector);
		
		//With java coordinates
		double[] startPointFixVector = {120,120};
		double[] endPointFixVector = {100,115};
		
		this.fixDirectionVectorQuadrantC = ANTSUtility.computeDirectionVector(startPointFixVector, endPointFixVector);
	}
	
	private void setUpVectorsForQuadrantD() 
	{
		//With java coordinates
		double[] startPointSmallerVector = {100,150};
		double[] endPointSmallerVector = {120,110};
		
		this.directionVectorSmallerQuadrantD = ANTSUtility.computeDirectionVector(startPointSmallerVector, endPointSmallerVector);
		
		//With java coordinates
		double[] startPointBiggerVector = {100,150};
		double[] endPointBiggerVector = {120,140};
		
		this.directionVectorBiggerQuadrantD = ANTSUtility.computeDirectionVector(startPointBiggerVector, endPointBiggerVector);
		
		//With java coordinates
		double[] startPointFixVector = {100,120};
		double[] endPointFixVector = {120,100};
		
		this.fixDirectionVectorQuadrantD = ANTSUtility.computeDirectionVector(startPointFixVector, endPointFixVector);
	}
	
	////////////////
	//Setup angles//
	////////////////
	
	private void setUpAngleForQuadrantA()
	{
		this.smallerAngleQuadrantA = ANTSUtility.computeAngleOfOneVector(directionVectorSmallerQuadrantA);
		this.biggerAngleQuadrantA = ANTSUtility.computeAngleOfOneVector(directionVectorBiggerQuadrantA);
		
		this.fixAngleQuadrantA = ANTSUtility.computeAngleOfOneVector(this.fixDirectionVectorQuadrantA);
	}
	
	private void setUpAngleForQuadrantB()
	{
		this.smallerAngleQuadrantB = ANTSUtility.computeAngleOfOneVector(directionVectorSmallerQuadrantB);
		this.biggerAngleQuadrantB = ANTSUtility.computeAngleOfOneVector(directionVectorBiggerQuadrantB);
		
		this.fixAngleQuadrantB = ANTSUtility.computeAngleOfOneVector(this.fixDirectionVectorQuadrantB);
	}
	
	private void setUpAngleForQuadrantC()
	{
		this.smallerAngleQuadrantC = ANTSUtility.computeAngleOfOneVector(directionVectorSmallerQuadrantC);
		this.biggerAngleQuadrantC = ANTSUtility.computeAngleOfOneVector(directionVectorBiggerQuadrantC);

		this.fixAngleQuadrantC = ANTSUtility.computeAngleOfOneVector(this.fixDirectionVectorQuadrantC);
	}
	
	private void setUpAngleForQuadrantD()
	{
		this.smallerAngleQuadrantD = ANTSUtility.computeAngleOfOneVector(directionVectorSmallerQuadrantD);
		this.biggerAngleQuadrantD = ANTSUtility.computeAngleOfOneVector(directionVectorBiggerQuadrantD);
		
		this.fixAngleQuadrantD = ANTSUtility.computeAngleOfOneVector(this.fixDirectionVectorQuadrantD);
	}
}
