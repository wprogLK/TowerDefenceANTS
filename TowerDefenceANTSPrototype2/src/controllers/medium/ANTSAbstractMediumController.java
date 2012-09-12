package controllers.medium;

import java.awt.event.MouseEvent;

import controllers.ANTSAbstractController;

import interfaces.medium.ANTSIMediumController;
import interfaces.medium.ANTSIMediumModel;

public abstract class ANTSAbstractMediumController extends ANTSAbstractController implements ANTSIMediumController
{
	protected ANTSIMediumModel model;
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public double getRefractionIndex() 
	{
		return this.model.getRefractionIndex();
	}
	
	/////////////
	//SETTERS//
	///////////
	
	public void setModel(ANTSIMediumModel m)
	{
		this.model = m;
	}
	
	///////////
	//SPECIAL//
	///////////
	
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
}
