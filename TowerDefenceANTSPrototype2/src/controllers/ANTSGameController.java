package controllers;

import views.ANTSGameView;
import models.ANTSGameModel;
import interfaces.ANTSIDriver;

public class ANTSGameController {

	private ANTSIDriver driver;
	private ANTSGameModel model;
	private ANTSGameView view;
	
	public ANTSGameController(ANTSIDriver d)
	{
		this.driver = d;
		
		this.model = new ANTSGameModel();
		this.view = new ANTSGameView(this.model);
	}

}
