package models;

import interfaces.ANTSIModel;

import java.awt.event.MouseAdapter;

public  class ANTSAbstractModel implements ANTSIModel
{
	private static ANTSAbstractModel emptyModel = new ANTSAbstractModel();
	
	public ANTSAbstractModel() 
	{
		
	}
	
	public final static ANTSIModel getEmptyModel()
	{
		return emptyModel;
	}

	@Override
	public void update() 
	{
		
	}


}
