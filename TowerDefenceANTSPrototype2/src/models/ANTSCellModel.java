package models;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Set;

import basics.ANTSDriver;

public class ANTSCellModel extends ANTSAbstractModel implements ANTSIModel 
{
	private ArrayList<ANTSIView> views;
	private ArrayList<ANTSIModel> models;
	private ArrayList<ANTSIController> controllers;
	
	private int relativePosX;	//nr of the cell
	private int relativePosY;	//nr of the cell
	
//	private double absolutePosX; //pos in pix
//	private double absolutePosY; //pos in pix
	
	private double defaultHeight = 60;
	private double defaultWidth = 60;
	
	private double height;
	private double width;
	
	private AffineTransform matrix;
	
	public ANTSCellModel(int cellNrX, int cellNrY) 
	{
		this.isMouseListener = true;
		
		this.views = new ArrayList<ANTSIView>();
		this.models = new ArrayList<ANTSIModel>();
		this.controllers = new ArrayList<ANTSIController>();
		
		this.relativePosX = cellNrX;
		this.relativePosY = cellNrY;
		
		this.height = defaultHeight;
		this.width = defaultWidth;
		
		this.matrix = new AffineTransform();
		this.matrix.setToTranslation(defaultWidth*cellNrX, defaultHeight*cellNrY);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public String toString()
	{
		return "CellModel";
	}

	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	///////////
	//SPECIAL//
	///////////
	public void addView(ANTSIView iView) 
	{
		if(!this.views.contains(iView))
		{
			this.views.add(iView);
		}
	}

	public void addModel(ANTSIModel model) 
	{
		if(!this.models.contains(model))
		{
			this.models.add(model);
		}
	}

	public void addController(ANTSIController controller)
	{
		if(!this.controllers.contains(controller))
		{
			this.controllers.add(controller);
		}
	}
	
}
