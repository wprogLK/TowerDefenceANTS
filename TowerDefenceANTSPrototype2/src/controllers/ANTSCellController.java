package controllers;

import java.awt.event.MouseEvent;

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
	
	public ANTSCellController(int cellNrX, int cellNrY, int shiftHalf, int xOffset, int yOffset) 
	{
		this.model = new ANTSCellModel(cellNrX, cellNrY, shiftHalf, xOffset, yOffset);
		this.view = new ANTSCellView(this.model);
		
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
		this.model.addView(c.getIView());
		this.model.addModel(c.getModel());
		this.model.addController(c);
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//Only an example
		System.out.println("CLICK");
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
