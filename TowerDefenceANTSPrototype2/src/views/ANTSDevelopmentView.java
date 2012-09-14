package views;

import java.awt.Graphics2D;

import interfaces.ANTSIView;

import models.ANTSDevelopmentModel;

public class ANTSDevelopmentView extends ANTSAbstractView implements ANTSIView
{
	private ANTSDevelopmentModel model;
	
	public ANTSDevelopmentView(ANTSDevelopmentModel m) 
	{
		super(m);
		
		this.model = m;
	}
		
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "Development view";
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	@Override
	public boolean doPaintDirect()
	{
		return false;
	}
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	
	@Override
	public void paint(Graphics2D g2d, float interpolation)
	{
		this.model.showDebugScreen();
	}

}
