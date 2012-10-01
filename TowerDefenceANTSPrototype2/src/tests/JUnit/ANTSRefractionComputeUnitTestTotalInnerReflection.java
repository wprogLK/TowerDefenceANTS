package tests.JUnit;

import org.junit.Test;
import org.junit.runner.RunWith;

import basics.ANTSDevelopment.ANTSStream;
import basics.ANTSRefractionComputeUnit;
import basics.ANTSUtility;

import ch.unibe.jexample.JExample;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JExample.class)
public class ANTSRefractionComputeUnitTestTotalInnerReflection
{
	private ANTSRefractionComputeUnit unit = new ANTSRefractionComputeUnit(null);
	
	//MediumOut
	private final double refractionIndexMediumOut = 1.5;
	
	//MediumIn
	private final double refractionIndexMediumIn = 1.1;
	
	private final double criticalAngle = ANTSRefractionComputeUnit.calculateCriticalAngle(refractionIndexMediumIn, refractionIndexMediumOut);
	private final double offsetRayAngleIn = 10;
	
	@Test
	public void rayAngleOutShouldBe0TotalInnerReflectionSituationPerpendicularAngle90() //TODO 
	{	
//		double anglePerpendicular = 90;
//		double angleRayIn = anglePerpendicular-this.criticalAngle-offsetRayAngleIn;
//		
//		assert(angleRayIn>=0 && angleRayIn<=90);
//		
//		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
//		
//		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)>ANTSUtility.roundScale2(this.criticalAngle));
//		
//		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
//	
//		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(0.0));
	}
	
//	@Test
//	public void rayAngleOutShouldBe45CriticalSituationPerpendicularAngle135() //A1 b
//	{	
//		double anglePerpendicular = 135;
//		double angleRayIn = anglePerpendicular-this.criticalAngle;
//		
//		assert(angleRayIn>=0 && angleRayIn<=90);
//		
//		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
//		
//		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)==ANTSUtility.roundScale2(this.criticalAngle));
//		
//		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
//	
//		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(45.0));
//	}
//	
//	@Test
//	public void rayAngleOutShouldBe180CriticalSituationPerpendicularAngle90() //A2
//	{
//		double anglePerpendicular = 90;
//		double angleRayIn = anglePerpendicular+this.criticalAngle;
//		
//		assert(angleRayIn<180 && angleRayIn>90);
//		
//		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
//		
//		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)==ANTSUtility.roundScale2(this.criticalAngle));
//		
//		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
//	
//		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(180.0));
//	}
//	
//	@Test
//	public void rayAngleOutShouldBe90CriticalSituationPerpendicularAngle180() //B1
//	{
//		double anglePerpendicular = 180;
//		double angleRayIn = anglePerpendicular-this.criticalAngle;
//		
//		assert(angleRayIn>90 && angleRayIn<180);
//		
//		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
//		
//		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)==ANTSUtility.roundScale2(this.criticalAngle));
//		
//		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
//	
//		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(90.0));
//	}
//	
//	@Test
//	public void rayAngleOutShouldBe270CriticalSituationPerpendicularAngle180() //B2
//	{
//		double anglePerpendicular = 180;
//		double angleRayIn = anglePerpendicular+this.criticalAngle;
//		
//		assert(angleRayIn>180 && angleRayIn<270);
//		
//		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
//		
//		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)==ANTSUtility.roundScale2(this.criticalAngle));
//		
//		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
//	
//		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(270.0));
//	}
//	
//	@Test
//	public void rayAngleOutShouldBe0CriticalSituationPerpendicularAngle270() //C1
//	{
//		double anglePerpendicular = 270;
//		double angleRayIn = anglePerpendicular+this.criticalAngle;
//		
//		assert(angleRayIn>270 && angleRayIn<360);
//		
//		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
//		
//		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)==ANTSUtility.roundScale2(this.criticalAngle));
//		
//		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
//	
//		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(0.0));
//	}
//	
//	@Test
//	public void rayAngleOutShouldBe180CriticalSituationPerpendicularAngle270() //C2
//	{
//		double anglePerpendicular = 270;
//		double angleRayIn = anglePerpendicular-this.criticalAngle;
//		
//		assert(angleRayIn>180 && angleRayIn<270);
//		
//		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
//		
//		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)==ANTSUtility.roundScale2(this.criticalAngle));
//		
//		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
//	
//		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(180.0));
//	}
//	
//	@Test
//	public void rayAngleOutShouldBe90CriticalSituationPerpendicularAngle0() //D1
//	{
//		double anglePerpendicular = 0;
//		double angleRayIn = anglePerpendicular+this.criticalAngle;
//		
//		assert(angleRayIn>0 && angleRayIn<90);
//		
//		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
//		
//		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)==ANTSUtility.roundScale2(this.criticalAngle));
//		
//		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
//	
//		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(90.0));
//	}
//	
//	@Test
//	public void rayAngleOutShouldBe270CriticalSituationPerpendicularAngle0() //D2
//	{
//		double anglePerpendicular = 0;
//		double angleRayIn = ANTSUtility.angleBetween0And359Degree(anglePerpendicular-this.criticalAngle);
//
//		assert(angleRayIn>270 && angleRayIn<360);
//		
//		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
//		
//		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)==ANTSUtility.roundScale2(this.criticalAngle));
//		
//		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
//	
//		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(270.0));
//	}
//	
}
