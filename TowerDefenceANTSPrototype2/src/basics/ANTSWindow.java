package basics;

import java.awt.Graphics;

import javax.swing.JFrame;

public class ANTSWindow extends JFrame{

	public ANTSWindow() 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g);
	}

}
