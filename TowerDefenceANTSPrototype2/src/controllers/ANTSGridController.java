package controllers;

import java.awt.event.MouseEvent;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import views.ANTSGridView;
import models.ANTSGridModel;

public class ANTSGridController extends ANTSAbstractController implements ANTSIController
{	
	private ANTSGridModel model;
	private ANTSGridView view;
	
	
	
	public ANTSGridController(int xCells, int yCells) 
	{
		this.model = new ANTSGridModel(xCells, yCells);
		this.view = new ANTSGridView(this.model);
		
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
	public void mouseClicked(MouseEvent e) 
	{
		//Only an example
		System.out.println("Click on grid");
		
		ANTSIController c = this.model.getCellControllerAtMousePos(e);
		c.mouseClicked(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
//		this.model.setDragged(false);
	}
	
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
//		this.model.setDragged(true);
//		this.model.setPosition(e.getX()/2, e.getY()/2);	//TODO: Important: test this! is it always /2 ? 
	}
}
