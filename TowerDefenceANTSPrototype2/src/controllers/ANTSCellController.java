package controllers;

import java.awt.event.MouseEvent;

import basics.ANTSDevelopment.ANTSDebug;
import basics.ANTSFactory;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import views.ANTSCellView;

import models.ANTSCellModel;

public class ANTSCellController extends ANTSAbstractController implements ANTSIController
{
	private ANTSCellModel model;
	private ANTSCellView view;
	
	public ANTSCellController(double cellHeight, double cellAngleInDegree, int cellNrX, int cellNrY, int shiftHalf, int xOffset, int yOffset, ANTSFactory factory) 
	{
		this.model = new ANTSCellModel(cellHeight, cellAngleInDegree, cellNrX, cellNrY, shiftHalf, xOffset, yOffset, factory);
		this.view = new ANTSCellView(this.model);
		this.iview = view;
		
		this.setIModel(this.model);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSCellView getView()
	{
		return this.view;
	}

	public ANTSIView getIView()
	{
		return this.view;
	}
	
	@Override
	public String toString()
	{
		return "CELL CONTROLLER " + this.model;
	}
	
	///////////
	//SPECIAL//
	///////////
	
	public void addComponent(ANTSIController c)
	{
		this.model.addController(c);
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.mouseEnteredANTS(e);
			ANTSDebug.setCurrentHoveringCell(this);
		}
		else
		{
			this.model.setMouseEntered(false);
		}
	}
	
	@Override 
	public void mouseEnteredANTS(MouseEvent e)
	{
		this.model.setMouseEntered(true);
	}
}
