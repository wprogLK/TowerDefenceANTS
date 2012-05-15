package basics;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ANTSWindow extends JFrame{

	private ArrayList<JPanel> panels;
	
	public ANTSWindow() 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.setLayout(new FlowLayout());
		this.panels = new ArrayList<JPanel>();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		for(JPanel cp:this.panels)
		{
			cp.paint(g);
		}
	}
	
	public void addView(JPanel p)
	{
		this.panels.add(p);
	}

}
