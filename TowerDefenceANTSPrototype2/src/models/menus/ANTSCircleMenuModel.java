package models.menus;

import interfaces.ANTSIModel;
import interfaces.menus.ANTSIMenuModel;

import java.awt.geom.AffineTransform;

import models.ANTSAbstractModel;

import basics.ANTSFactory;

public class ANTSCircleMenuModel extends ANTSAbstractModel implements ANTSIModel, ANTSIMenuModel
{
	private AffineTransform matrix;
	private double radius;
	
	
	public ANTSCircleMenuModel(double posX, double posY, double radius, ANTSFactory factory )
	{
		super(factory);
		
		this.matrix = new AffineTransform();
		this.matrix.translate(posX, posY);
		
		this.radius = radius;
		
		this.isMouseListener = true;
		this.mouseEntered = false;
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public String toString()
	{
		return "circle menu";
	}

	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public double getRadius()
	{
		return this.radius;
	}

	
	
	///////////
	//SPECIAL//
	///////////
	
}
