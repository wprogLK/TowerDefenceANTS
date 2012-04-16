/**
 * 
 */
package view.basics;

import java.awt.Graphics;
import java.awt.Graphics2D;

import interfaces.ANTSIView;

import javax.swing.JFrame;

/**
 * @author Lukas
 *
 */
public class ANTSGameMainWindow extends JFrame
{
	private int width;
	private int height;
	
	private ANTSIView gameView;
	
	public ANTSGameMainWindow()
	{
		this.width = 800;
		this.height = 600;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(this.width, this.height);
		
		this.gameView = new ANTSGameView();
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		this.gameView.paint(g2d);
	}
}
