package mvcFactories.abstracts;

import interfaces.ANTSIView;

public abstract class ANTSMVCFactoryMVCFactoryAbstract 
{
	protected ANTSIView view;
	
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
