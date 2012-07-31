package controllers;

import java.awt.event.MouseEvent;

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
	
//	public ANTSCellController(int cellNrX, int cellNrY) 
//	{
//		this.model = new ANTSCellModel(cellNrX, cellNrY);
//		this.view = new ANTSCellView(this.model);
//		
//		this.setIModel(this.model);
//	}
	
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
		return "CELL CONTROLLER";
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
	public void mouseClickedANTS(MouseEvent e) 
	{
		//Only an example
		System.out.println("CLICK of " + this.model.toString());
	}
}
