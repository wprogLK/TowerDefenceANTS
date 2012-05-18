package views;

import interfaces.ANTSIView;

import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import listeners.ANTSUpdateListener;
import models.ANTSGameModel;

public class ANTSGameView extends ANTSAbstractView implements ANTSIView
{
	private ANTSGameModel model;
	private JButton buttonUpdate;
	
	public ANTSGameView(ANTSGameModel m) 
	{
		this.model = m;
		
		this.buttonUpdate = new JButton("Update");
		this.buttonUpdate.addActionListener(ANTSUpdateListener.getInstance());
	}
	
	@Override
	public void paint(Graphics2D g2d)
	{
		
	}
	
	public boolean pointIsIn(double x, double y)
	{
		return false; //TODO
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		
	}


}
