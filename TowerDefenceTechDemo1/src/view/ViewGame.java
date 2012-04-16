package view;

import java.awt.Graphics;

import javax.swing.JFrame;

public class ViewGame extends JFrame
{
	private LightView light1;
	private LightView light2;
	
	public ViewGame()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(600, 600);
		this.light1 = new LightView(100,100,100);
		this.light2 = new LightView(100,100,100);
	}
	
	public void paint(Graphics g)
	{
		this.light1.paint(g);
		this.light2.paint(g);
		System.out.println("PAINT");
	}
}
