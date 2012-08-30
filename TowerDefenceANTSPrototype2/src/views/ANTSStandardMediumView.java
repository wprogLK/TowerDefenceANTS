package views;

import interfaces.ANTSIMediumView;
import interfaces.ANTSIView;

import models.ANTSStandardMediumModel;

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
}
