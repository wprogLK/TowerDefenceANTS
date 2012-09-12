package models.medium;

import interfaces.ANTSIModel;

import java.awt.geom.AffineTransform;

import basics.ANTSFactory;

public class ANTSStandardMediumModel extends ANTSAbstractMediumModel implements ANTSIModel 
{
	public ANTSStandardMediumModel(ANTSFactory factory)
	{
		super(true,0,factory);
		
		this.matrix = new AffineTransform();
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
