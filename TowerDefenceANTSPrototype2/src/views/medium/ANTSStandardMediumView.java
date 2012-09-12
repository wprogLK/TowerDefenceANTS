package views.medium;

import views.ANTSAbstractView;
import interfaces.ANTSIView;
import interfaces.medium.ANTSIMediumView;

import models.medium.ANTSStandardMediumModel;

public class ANTSStandardMediumView extends ANTSAbstractView implements ANTSIView, ANTSIMediumView
{
	private ANTSStandardMediumModel model;
	
	public ANTSStandardMediumView(ANTSStandardMediumModel m) 
	{
		super();
		this.model = m;
	}
	
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "medium";
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	///////////
	//SPECIAL//
	///////////
}
