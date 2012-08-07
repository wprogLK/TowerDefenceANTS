package controllers.menus;

import java.awt.event.MouseEvent;

import controllers.ANTSAbstractController;

import basics.ANTSFactory;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuController;

import views.menus.ANTSRectangleMenuView;

import models.menus.ANTSRectangleMenuModel;

public class ANTSRectangleMenuController extends ANTSAbstractController implements ANTSIController, ANTSIMenuController
{
	private ANTSRectangleMenuModel model;
	private ANTSRectangleMenuView view;
	
	
	public ANTSRectangleMenuController(double posX, double PosY, double minWidth, ANTSFactory factory) 
	{
		this.model = new ANTSRectangleMenuModel(posX,PosY, minWidth, factory);
		this.view = new ANTSRectangleMenuView(this.model);
		this.iview = view;
		
		this.setIModel(this.model);
		
		this.addNewMenuItem("test item 1");
		this.addNewMenuItem("test item 2");
		this.addNewMenuItem("test item 3");
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public ANTSIModel getIModel()
	{
		return this.model;
	}
	
	public ANTSRectangleMenuModel getModel()
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
		return "RectangleMenu CONTROLLER " + this.model;
	}
	
	///////////
	//SPECIAL//
	///////////
	
	@Override 
	public void addNewMenuItem(String string) //TODO ANTSICommand
	{
		ANTSMenuItemRectangleController c = new ANTSMenuItemRectangleController(string, this.model ,this.model.getFactory());
		this.model.getFactory().addToMouseListener(c);
		this.model.addNewMenuItem(c);
	}
	
	public int getMaxIndexMenuItem()
	{
		return this.model.getMaxIndexMenuItem();
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
		}
		else
		{
			this.model.setMouseEntered(false);
		}
	}
}
