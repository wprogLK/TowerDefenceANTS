package views;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import javax.swing.JButton;
//import listeners.ANTSUpdateListener;
import models.ANTSGameModel;

public class ANTSGameView extends ANTSAbstractView implements ANTSIView
{
	private ANTSGameModel model;
	
	public ANTSGameView(ANTSGameModel m) 
	{
		super(m);
		this.model = m;
	}
	
	///////////
	//Getters//
	///////////
	
	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	@Override
	public String toString()
	{
		return "Game view";
	}
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
}
