package basics;

import java.awt.Graphics;

import javax.swing.JFrame;

public class ANTSWindow extends JFrame{

	private ANTSDriver driver;
	
	public ANTSWindow(ANTSDriver d) 
	{
		this.driver = d;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
	}
	
	@Override
	public void paint(Graphics g)
	{
		//this.driver.update();
		super.paintComponents(g);
	}

}
