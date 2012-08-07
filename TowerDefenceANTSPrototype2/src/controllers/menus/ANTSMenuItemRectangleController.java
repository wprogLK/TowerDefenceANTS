package controllers.menus;

import java.awt.event.MouseEvent;

import controllers.ANTSAbstractController;

import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuItemController;

import views.menus.ANTSMenuItemRectangleView;

import models.menus.ANTSMenuItemRectangleModel;

import models.menus.ANTSRectangleMenuModel;

public class ANTSMenuItemRectangleController extends ANTSAbstractController implements ANTSIController, ANTSIMenuItemController
{
	private ANTSMenuItemRectangleModel model;
	private ANTSMenuItemRectangleView view;
	
	public ANTSMenuItemRectangleController(String string, ANTSRectangleMenuModel m ,ANTSFactory factory) 
	{
		this.model = new ANTSMenuItemRectangleModel(string, factory);
		this.view = new ANTSMenuItemRectangleView(this.model, m);
		
		this.model.setIndex(m.getMaxIndexMenuItem());
		
		this.iview = view;
		this.setIModel(this.model);
	}
	
	///////////
	//GETTERS//
	///////////
	
	public ANTSIModel getIModel()
	{
		return this.model;
	}
	
	public ANTSMenuItemRectangleModel getModel()
	{
		return this.model;
	}
	
	@Override
	public String toString()
	{
		return "RectangleMenu CONTROLLER " + this.model;
	}
	
	public ANTSIView getView()
	{
		return this.iview;
	}
	
	////////////
	//SETTERES//
	////////////
	
	public void setView(ANTSIView v)
	{
		this.iview = v;
	}
	

	public void setMenuItemIndex(int index) 
	{
		this.model.setIndex(index);
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
	
	@Override
	public void mouseLeftClickedANTS(MouseEvent e) 
	{
		//Only an example
		ANTSStream.print("CLICK item of " + this.model.getText());
	}
	
	@Override 
	public void mouseEnteredANTS(MouseEvent e)
	{
		this.model.setMouseEntered(true);
	}

	
}
