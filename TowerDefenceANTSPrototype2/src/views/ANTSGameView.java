package views;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import listeners.ANTSUpdateListener;
import models.ANTSGameModel;

public class ANTSGameView extends JPanel{
	
	private ANTSGameModel model;
	private JButton buttonUpdate;
	
	public ANTSGameView(ANTSGameModel m) 
	{
		this.model = m;
		
		this.buttonUpdate = new JButton("Update");
		this.buttonUpdate.addActionListener(ANTSUpdateListener.getInstance());
		this.add(this.buttonUpdate);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g);
	}

}
