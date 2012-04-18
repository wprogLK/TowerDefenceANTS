package view.basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import interfaces.ANTSIView;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.abstracts.ANTSViewAbstact;

public class ANTSWindow extends JFrame
{
	ANTSIView currentView;
	
	public ANTSWindow()
	{
	}
	
	public void setCurrentView(ANTSIView currentView)
	{
		this.currentView = currentView;
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g); 
		
		JPanel mainPanel =this.currentView.getPanel();
		Graphics gp = mainPanel.getGraphics();
		Graphics2D g2d = (Graphics2D) gp;
		
		this.configRendering(g2d);
		this.currentView.paint(g2d);
	}
	
	private void configRendering(Graphics2D g2d) 
	{
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
