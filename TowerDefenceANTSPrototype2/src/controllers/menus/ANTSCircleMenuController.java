package controllers.menus;

import java.awt.event.MouseEvent;

import controllers.ANTSAbstractController;

import basics.ANTSDevelopment;
import basics.ANTSDevelopment.ANTSDebug;
import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuController;
import views.ANTSCellView;
import views.menus.ANTSCircleMenuView;
import models.ANTSCellModel;
import models.menus.ANTSCircleMenuModel;

public class ANTSCircleMenuController extends ANTSAbstractController implements ANTSIController, ANTSIMenuController
{
	private ANTSCircleMenuModel model;
	private ANTSCircleMenuView view;
	
	
	public ANTSCircleMenuController(double x, double y, double radius, ANTSFactory factory) 
	{
		this.model = new ANTSCircleMenuModel(x,y, radius, factory);
		this.view = new ANTSCircleMenuView(this.model);
		this.iview = view;
		
		this.setIModel(this.model);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public ANTSIModel getIModel()
	{
		return this.model;
	}
	
	public ANTSCircleMenuModel getModel()
	{
		return this.model;
	}

	public ANTSIView getIView()
	{
		return this.view;
	}
	
	@Override
	public String toString()
	{
		return "CircleMenu CONTROLLER " + this.model;
	}
	
	///////////
	//SPECIAL//
	///////////
	
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.mouseEnteredANTS(e);
		}
		else
		{
			this.model.setMouseEntered(false);	//TODO maybe don't do this
		}
	}
	
	@Override
	public void mouseLeftClickedANTS(MouseEvent e) 
	{
		//Only an example
		ANTSStream.print("CLICK of " + this.model.toString());
	}
	
	@Override 
	public void mouseEnteredANTS(MouseEvent e)
	{

	}
}
