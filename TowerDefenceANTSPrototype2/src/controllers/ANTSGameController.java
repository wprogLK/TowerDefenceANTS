package controllers;

import views.ANTSGameView;
import models.ANTSGameModel;
import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

public class ANTSGameController implements ANTSIController
{

	private ANTSGameModel model;
	private ANTSGameView view;
	
	public ANTSGameController()
	{
		this.model = new ANTSGameModel();
		this.view = new ANTSGameView(this.model);
	}
	
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

}
