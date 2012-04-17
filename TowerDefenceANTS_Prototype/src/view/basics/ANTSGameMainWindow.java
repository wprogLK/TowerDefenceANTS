/**
 * 
 */
package view.basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import interfaces.ANTSIController;
import interfaces.ANTSIView;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.basics.ANTSGameController;


/**
 * @author Lukas
 *
 */
public class ANTSGameMainWindow extends JFrame
{
	private ANTSIView gameView;
	private ANTSIController gameController;		//the "main" controller
	
	public ANTSGameMainWindow()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//this.gameView = new ANTSGameView();
		this.gameController = new ANTSGameController();
		
		this.changeView();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g); 
		
		JPanel mainPanel =this.gameView.getPanel();
		Graphics gp = mainPanel.getGraphics();
		Graphics2D g2d = (Graphics2D) gp;
		
		this.configRendering(g2d);
		
		
		
		this.gameView.paint(g2d);
	}
	
	private void configRendering(Graphics2D g2d) 
	{
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public void changeView()
	{
		this.gameView = this.gameController.getView();
		JPanel mainPanel = this.gameView.getPanel();
		this.setContentPane(mainPanel);
		this.pack();
	}
	
	
}
