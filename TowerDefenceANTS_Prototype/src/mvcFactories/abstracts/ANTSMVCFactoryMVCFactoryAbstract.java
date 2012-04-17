package mvcFactories.abstracts;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

public abstract class ANTSMVCFactoryMVCFactoryAbstract 
{
	protected ANTSIView view;
//	protected ANTSIController controller;
//	protected ANTSIModel model;
	
	public ANTSMVCFactoryMVCFactoryAbstract()
	{
		this.createMVC();
	}
	
	public final ANTSIView getView()
	{
		return this.view;
	}
	
	protected abstract void createMVC();
}
