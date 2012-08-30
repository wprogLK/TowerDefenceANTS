package models.medium;

import interfaces.ANTSIModel;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import controllers.ANTSSimpleRayLightController;

import basics.ANTSFactory;

public class ANTSStandardMediumModel extends ANTSAbstractMediumModel implements ANTSIModel 
{
	private ArrayList<ANTSSimpleRayLightController> rays;
	
	public ANTSStandardMediumModel(ANTSFactory factory)
	{
		super(true,factory);
		
		this.matrix = new AffineTransform();
		this.rays = new ArrayList<ANTSSimpleRayLightController>();
		
		this.isMouseListener = false;
	}
	
	///////////
	//Getters//
	///////////

	@Override
	public boolean isDragged()
	{
		return this.isDragged;
	}
	
	
	public String toString()
	{
		return "Model standard medium";
	}
	
	///////////
	//Special//
	///////////
	
	@Override
	public void update()
	{

	}

}
