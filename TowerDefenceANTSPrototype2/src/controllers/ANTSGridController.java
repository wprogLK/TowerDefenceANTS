package controllers;

import java.awt.event.MouseEvent;

import basics.ANTSFactory;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import views.ANTSGridView;
import models.ANTSGridModel;

public class ANTSGridController extends ANTSAbstractController implements ANTSIController
{	
	private ANTSGridModel model;
	private ANTSGridView view;
	
	
	
	public ANTSGridController(int xCells, int yCells, ANTSFactory factory) 
	{
		this.model = new ANTSGridModel(xCells, yCells, factory);
		this.view = new ANTSGridView(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	

	@Override
	public String toString()
	{
		return "GRID CONTROLLER";
	}


	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSGridView getView()
	{
		return this.view;
	}

	public ANTSIView getIView()
	{
		return this.view;
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	@Override
	public void mouseLeftClickedANTS(MouseEvent e) 
	{
		//Only an example
		System.out.println("Click on grid");
		
	}
}
