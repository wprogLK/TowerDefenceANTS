package views;

import interfaces.ANTSIView;

import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import listeners.ANTSUpdateListener;
import models.ANTSGameModel;

public class ANTSGameView implements ANTSIView
{
	private ANTSGameModel model;
	private JButton buttonUpdate;
	private JPanel panel;
	
	public ANTSGameView(ANTSGameModel m) 
	{
		this.model = m;
		
		this.panel = new JPanel();
		
		this.buttonUpdate = new JButton("Update");
		this.buttonUpdate.addActionListener(ANTSUpdateListener.getInstance());
		this.panel.add(this.buttonUpdate);
	}
	
	@Override
	public void paint(Graphics2D g2d)
	{
		
	}
	
	@Override
	public JPanel getPanel()
	{
		return this.panel;
	}


}
