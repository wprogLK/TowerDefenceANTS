package controllers;

import views.ANTSGameView;
import models.ANTSGameModel;
import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

public class ANTSGameController extends ANTSAbstractController implements ANTSIController
{

	private ANTSGameModel model;
	private ANTSGameView view;
	
	public ANTSGameController()
	{
		super();
		
		this.model = new ANTSGameModel();
		this.view = new ANTSGameView(this.model);
		
		this.setIModel(this.model);
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSGameView getView()
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
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////

}
