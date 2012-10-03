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
	public void AngleInBetween0And90TotalInnerReflectionSituationPerpendicularAngle90() //A1
	{	
		double anglePerpendicular = 90;
		double angleRayIn = anglePerpendicular-this.criticalAngle-offsetRayAngleIn;
		
		assert(angleRayIn>=0 && angleRayIn<=90);
		
		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
		
		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)>ANTSUtility.roundScale2(this.criticalAngle));
		
		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
	
		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(ANTSUtility.roundScale2(anglePerpendicular+180+angleBetweenRayAndPerpendicular)));
		
		assert(angleRayOut>270 && angleRayOut<360);
	}
	 
	@Test
	public void AngleInBetween90And180TotalInnerReflectionSituationPerpendicularAngle90() //A2
	{	
		double anglePerpendicular = 90;
		double angleRayIn = anglePerpendicular+this.criticalAngle+offsetRayAngleIn;
	
		assert(angleRayIn>=90 && angleRayIn<=180);

		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
		
		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)>ANTSUtility.roundScale2(this.criticalAngle));
		
		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
	
		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(ANTSUtility.roundScale2(anglePerpendicular+180-angleBetweenRayAndPerpendicular)));
		
		assert(angleRayOut>180 && angleRayOut<270);
	}
	
	@Test
	public void AngleInBetween90And180TotalInnerReflectionSituationPerpendicularAngle180() //B1
	{	
		double anglePerpendicular = 180;
		double angleRayIn = anglePerpendicular-this.criticalAngle-offsetRayAngleIn;
	
		assert(angleRayIn>90 && angleRayIn<=180);

		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
		
		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)>ANTSUtility.roundScale2(this.criticalAngle));
		
		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
	
		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(ANTSUtility.roundScale2(ANTSUtility.angleBetween0And359Degree((anglePerpendicular+180+angleBetweenRayAndPerpendicular)))));
		
		assert(angleRayOut>0 && angleRayOut<90);
	}
	
	@Test
	public void AngleInBetween180And270TotalInnerReflectionSituationPerpendicularAngle180() //B2
	{	
		double anglePerpendicular = 180;
		double angleRayIn = anglePerpendicular+this.criticalAngle+offsetRayAngleIn;
	
		assert(angleRayIn>=180 && angleRayIn<=270);

		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
		
		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)>ANTSUtility.roundScale2(this.criticalAngle));
		
		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
	
		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(ANTSUtility.roundScale2(ANTSUtility.angleBetween0And359Degree((anglePerpendicular+180-angleBetweenRayAndPerpendicular)))));
	
		assert(angleRayOut>270 && angleRayOut<360);
	}
	
	
	@Test
	public void AngleInBetween270And360TotalInnerReflectionSituationPerpendicularAngle270() //C1
	{	
		double anglePerpendicular = 270;
		double angleRayIn = anglePerpendicular+this.criticalAngle+offsetRayAngleIn;
	
		assert(angleRayIn>=270 && angleRayIn<360);

		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
		
		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)>ANTSUtility.roundScale2(this.criticalAngle));
		
		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
	
		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(ANTSUtility.roundScale2(ANTSUtility.angleBetween0And359Degree((anglePerpendicular+180-angleBetweenRayAndPerpendicular)))));
		
		assert(angleRayOut>0 && angleRayOut<90);
	}
	
	@Test
	public void AngleInBetween180And270TotalInnerReflectionSituationPerpendicularAngle270() //C2
	{	
		double anglePerpendicular = 270;
		double angleRayIn = anglePerpendicular-this.criticalAngle-offsetRayAngleIn;
	
		assert(angleRayIn>=180 && angleRayIn<=270);

		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
		
		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)>ANTSUtility.roundScale2(this.criticalAngle));
		
		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
	
		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(ANTSUtility.roundScale2(ANTSUtility.angleBetween0And359Degree((anglePerpendicular+180+angleBetweenRayAndPerpendicular)))));
		
		assert(angleRayOut>90 && angleRayOut<180);
	}
	
	@Test
	public void AngleInBetween0And90TotalInnerReflectionSituationPerpendicularAngle0() //D1
	{	
		double anglePerpendicular = 0;
		double angleRayIn = anglePerpendicular+this.criticalAngle+offsetRayAngleIn;
	
		assert(angleRayIn>=0 && angleRayIn<=90);

		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
		
		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)>ANTSUtility.roundScale2(this.criticalAngle));
		
		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
	
		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(ANTSUtility.roundScale2(ANTSUtility.angleBetween0And359Degree((anglePerpendicular+180-angleBetweenRayAndPerpendicular)))));
		
		assert(angleRayOut>90 && angleRayOut<180);
	}
	
	@Test
	public void AngleInBetween270And360TotalInnerReflectionSituationPerpendicularAngle0() //D2
	{	
		double anglePerpendicular = 0;
		double angleRayIn = ANTSUtility.angleBetween0And359Degree(anglePerpendicular-this.criticalAngle-offsetRayAngleIn);
	
		assert(angleRayIn>=270 && angleRayIn<360);

		double angleBetweenRayAndPerpendicular = ANTSUtility.angleBetweenToAngles(angleRayIn, anglePerpendicular); 
		
		assert(ANTSUtility.roundScale2(angleBetweenRayAndPerpendicular)>ANTSUtility.roundScale2(this.criticalAngle));
		
		double angleRayOut = this.unit.computeRefractionOrTotalReflection(refractionIndexMediumIn, refractionIndexMediumOut, angleRayIn, anglePerpendicular, angleBetweenRayAndPerpendicular);
	
		assertThat(ANTSUtility.roundScale2(angleRayOut), equalTo(ANTSUtility.roundScale2(ANTSUtility.angleBetween0And359Degree((anglePerpendicular+180+angleBetweenRayAndPerpendicular)))));
	
		assert(angleRayOut>180 && angleRayOut<270);
	}
}
