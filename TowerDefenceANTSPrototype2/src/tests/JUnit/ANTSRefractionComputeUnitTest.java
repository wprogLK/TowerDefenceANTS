package tests.JUnit;

import interfaces.ANTSIRefractionComputeUnit;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import ch.unibe.jexample.Given;
import ch.unibe.jexample.JExample;

@RunWith(JExample.class)
public class ANTSRefractionComputeUnitTest
{
	private Mockery mockery;
	
	@Before
	public void setUp()
	{
		this.mockery = new JUnit4Mockery();
	}
	
	@Test
	public ANTSIRefractionComputeUnit refractionTest()
	{
		ANTSIRefractionComputeUnit refractionComputeUnit = this.mockery.mock(ANTSIRefractionComputeUnit.class);
		return refractionComputeUnit;
	}
	
	@Given("refractionTest")
	public void shouldCalculateRefractionA(ANTSIRefractionComputeUnit unit)
	{
		//TODO
		
		this.mockery.checking(new Expectations()
		{
			{
				
			}
		});
		
		
		this.mockery.assertIsSatisfied();
	}
}
