package models;

import interfaces.ANTSIModel;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import controllers.ANTSSimpleRayLightController;

import basics.ANTSFactory;

public class ANTSSimpleMediumModel extends ANTSAbstractModel implements ANTSIModel 
{
	private AffineTransform matrix;
	
	private double refractionIndex = 1;
	private double height = 100;
	private double width = 100;
	
	private ArrayList<ANTSSimpleRayLightController> rays;
	
	
	public ANTSSimpleMediumModel(double posX, double posY, double height, double width, boolean isMouseListener, ANTSFactory factory)
	{
		super(factory);
		
		this.isCollisionDetected = true;
		
		this.matrix = new AffineTransform();
		this.rays = new ArrayList<ANTSSimpleRayLightController>();
		
		this.matrix.setToTranslation(posX, posY);
		
		this.height = height;
		this.width = width;
		
		this.isMouseListener = isMouseListener;
	}
	
	///////////
	//Getters//
	///////////

	@Override
	public boolean isDragged()
	{
		return this.isDragged;
	}
	
	public double getPosX()
	{
		return this.matrix.getTranslateX();
	}

	public double getPosY()
	{
		return this.matrix.getTranslateY();
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
		
	public double getRefractionIndex()
	{
		return this.refractionIndex;
	}
	
	public String toString()
	{
		return "Model simple medium";
	}
	
	public boolean containsRay(ANTSSimpleRayLightController c)
	{
		return this.rays.contains(c);
	}
	
	public double getAngle(double refractionIndexOtherMedium)
	{
		//TODO
		return 45;
	}
	
	///////////
	//Setters//
	///////////
	
	public void setPosition(int x, int y)
	{
		this.matrix.setToTranslation(x, y);
	}
	
	///////////
	//Special//
	///////////
	
	@Override
	public void update()
	{

	}

	public void addRay(ANTSSimpleRayLightController rayLightController, double refractoringIndexOfOtherMedium) {
		if(!this.containsRay(rayLightController))
		{
			this.rays.add(rayLightController);
			rayLightController.addAngle(this.getAngle(refractoringIndexOfOtherMedium));
		}
	}

	public void removeRay(ANTSSimpleRayLightController rayLightController, int refractoringIndexOfOtherMedium) 
	{	
		if(this.containsRay(rayLightController))
		{
			this.rays.remove(rayLightController);
			rayLightController.addAngle(-this.getAngle(refractoringIndexOfOtherMedium));	//TODO check this
		}
	}
}
