package controllers;

import java.awt.Graphics2D;

import basics.ANTSDriver.ANTSFPS;
import basics.ANTSFactory;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import views.ANTSDevelopmentView;

import models.ANTSDevelopmentModel;

public class ANTSDevelopmentController extends ANTSAbstractController implements ANTSIController
{
	private ANTSDevelopmentModel model;
	private ANTSDevelopmentView view;
	
	public ANTSDevelopmentController(ANTSFactory factory) 
	{
		this.model = new ANTSDevelopmentModel(factory);
		this.view = new ANTSDevelopmentView(this.model);
		this.iview = view;
		
		this.setIModel(this.model);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	@Override
	public ANTSIModel getModel()
	{
		return this.model;
	}

	@Override
	public ANTSIView getIView()
	{
		return this.view;
	}
	
	@Override
	public String toString()
	{
		return "DEVELOPMENTE CONTROLLER " + this.model;
	}
	
	///////////
	//SPECIAL//
	///////////
	


	///////////////////
	//PRIVATE METHODS//
	///////////////////

	
	///////////
	//SETTERS//
	//////////
	
	public void setCurrentHoveringCell(ANTSCellController c)
	{
		this.model.setCurrentHoveringCell(c);
	}
	
	public void setGraphics2D(Graphics2D g)
	{
		this.model.setGraphics2D(g);
	}
	
	public  void setFPS(ANTSFPS fpsIn)
	{
		this.model.setFPS(fpsIn);
	}

	public void setInterpolation(float in) 
	{
		this.model.setInterpolation(in);
	}
	
	public void setShowDebugScreenAll(boolean value)
	{
		this.model.setShowDebugScreenAll(value);
	}

	public void setShowFPS(boolean value)
	{
		this.model.setShowFPS(value);
	}
	
	public void setShowExtraInformation(boolean value)
	{
		this.model.setShowExtraInformation(value);
	}
	
	public void setShowCurrentHoveringCellInfo(boolean value)
	{
		this.model.setShowCurrentHoveringCellInfo(value);
	}
	
	public void setShowCurrentInterplation(boolean value)
	{
		this.model.setShowCurrentHoveringCellInfo(value);
	}
	
	public void setShowInterpolationState(boolean value)
	{
		this.model.setShowInterpolationState(value);
	}
	
	public void setShowBounds(boolean value)
	{
		this.model.setShowBounds(value);
	}
	
	public void setShowNrOfObjects(boolean value)
	{
		this.model.setShowNrOfObjects(value);
	}
	
	public void setShowDetectionGrid(boolean value)
	{
		this.model.setShowDetectionGrid(value);
	}
	
	public void setInterpolationOn(boolean value)
	{
		this.model.setInterpolationOn(value);
	}
	
	///////////
	//GETTERS//
	//////////
	
	public boolean isShowBounds()
	{
		return this.model.isShowBounds();
	}

	public boolean getInterpolationOn() 
	{
		return this.model.getInterpolationOn();
	}

	public boolean isShowDetectionGrid() 
	{
		return this.model.isShowDetectionGrid();
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
}
