package controllers.medium;

import java.awt.Color;
import java.awt.event.MouseEvent;

import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import interfaces.medium.ANTSIMediumController;

import views.medium.ANTSSimpleMediumView;
import views.medium.ANTSStandardMediumView;

import models.medium.ANTSSimpleMediumModel;
import models.medium.ANTSStandardMediumModel;

public class ANTSStandardMediumController extends ANTSAbstractMediumController implements ANTSIMediumController
{
	private ANTSStandardMediumModel model;
	private ANTSStandardMediumView view;
	
	public ANTSStandardMediumController(ANTSFactory factory)
	{
		this.model = new ANTSStandardMediumModel(factory);
		this.view = new ANTSStandardMediumView(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
	}
	
	///////////
	//GETTERS//
	///////////
	
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSIView getIView()
	{
		return this.view;
	}
	
	@Override
	public double getRefractionIndex() 
	{
		return this.model.getRefractionIndex();
	}
	
	///////////
	//SPECIAL//
	///////////
	
}
