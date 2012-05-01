package view.basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import interfaces.ANTSIView;

import javax.swing.JFrame;
import javax.swing.JPanel;

import enums.ANTSStateEnum;

import view.abstracts.ANTSViewAbstact;

public class ANTSWindow extends JFrame
{
	ANTSIView currentView;
//	private ANTSStateEnum currentState;
	
	
	public ANTSWindow()
	{
		
	}
	
	public void setCurrentView(ANTSIView currentView)
	{
		this.currentView = currentView;
	}
	
//	//NEW
//	/** only draw the graphics. NO AnimationMove!
//	 * 
//	 */
//	public void draw()
//	{
//		this.currentState = ANTSStateEnum.draw;
//		
//	}
	
	@Override
	public void paint(Graphics g)
	{
		//TODO: switch states
//		OLD
//		super.paintComponents(g); 
//		
//		JPanel mainPanel =this.currentView.getPanel();
//		Graphics gp = mainPanel.getGraphics();
//		Graphics2D g2d = (Graphics2D) gp;
//		
//		this.configRendering(g2d);
//		this.currentView.paint(g2d);
		
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
