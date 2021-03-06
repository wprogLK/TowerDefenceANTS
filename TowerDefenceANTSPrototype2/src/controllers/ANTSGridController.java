package controllers;

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
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public String toString()
	{
		return "GRID CONTROLLER";
	}
	
	@Override
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSGridView getView()
	{
		return this.view;
	}

	@Override
	public ANTSIView getIView()
	{
		return this.view;
	}
}
