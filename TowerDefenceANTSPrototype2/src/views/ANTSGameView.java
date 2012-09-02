package views;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import javax.swing.JButton;
//import listeners.ANTSUpdateListener;
import models.ANTSGameModel;

public class ANTSGameView extends ANTSAbstractView implements ANTSIView
{
	private ANTSGameModel model;
	private JButton buttonUpdate;
	
	public ANTSGameView(ANTSGameModel m) 
	{
		super();
		this.model = m;
		
//		this.buttonUpdate = new JButton("Update");
//		this.buttonUpdate.addActionListener(ANTSUpdateListener.getInstance());
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
	
	@Override
	protected void updateShape(float interpolation) 
	{
		//Do nothing
	}	
	
	@Override
	public void paint(Graphics2D g2d)
	{
		
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		this.paintBounds(g2d);
	}
	
	
}
