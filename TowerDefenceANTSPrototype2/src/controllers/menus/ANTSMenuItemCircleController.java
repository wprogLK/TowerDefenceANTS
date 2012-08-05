package controllers.menus;

import java.awt.event.MouseEvent;

import controllers.ANTSAbstractController;

import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuItemController;
import views.menus.ANTSMenuItemCircleView;
import models.menus.ANTSCircleMenuModel;
import models.menus.ANTSMenuItemCircleModel;

public class ANTSMenuItemCircleController extends ANTSAbstractController implements ANTSIController, ANTSIMenuItemController
{
	private ANTSMenuItemCircleModel model;
	private ANTSMenuItemCircleView view;
	
	public ANTSMenuItemCircleController(String string, ANTSCircleMenuModel m ,ANTSFactory factory) 
	{
		this.model = new ANTSMenuItemCircleModel(string, factory);
		this.view = new ANTSMenuItemCircleView(this.model, m);
		
		this.model.setIndex(m.getMaxIndexMenuItem());
		
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
	
	public ANTSMenuItemCircleModel getModel()
	{
		return this.model;
	}
	
	@Override
	public String toString()
	{
		return "CircleMenu CONTROLLER " + this.model;
	}
	
	public void setView(ANTSIView v)
	{
		this.iview = v;
	}
	
	public ANTSIView getView()
	{
		return this.iview;
	}
	
	///////////
	//SPECIAL//
	///////////
	
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
			this.model.setMouseEntered(false);	//TODO maybe don't do this
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
