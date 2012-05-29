package models;

import interfaces.ANTSIModel;

public class ANTSAbstractModel implements ANTSIModel
{
	private static ANTSAbstractModel emptyModel = new ANTSAbstractModel();
	protected boolean isMouseListener;
	
	public ANTSAbstractModel() 
	{
		this.isMouseListener = false;
	}
	
	public final static ANTSIModel getEmptyModel()
	{
		return emptyModel;
	}

	@Override
	public void update() 
	{
		
	}
	
	public boolean isMouseListener()
	{
		return this.isMouseListener;
	}
	


}
