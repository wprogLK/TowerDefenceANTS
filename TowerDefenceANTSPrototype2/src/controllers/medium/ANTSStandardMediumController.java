package controllers.medium;

import basics.ANTSFactory;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import interfaces.medium.ANTSIMediumController;

import views.medium.ANTSStandardMediumView;

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
	
	@Override
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	@Override
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
