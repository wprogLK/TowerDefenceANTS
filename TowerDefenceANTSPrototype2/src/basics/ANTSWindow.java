package basics;

import interfaces.ANTSIView;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ANTSWindow extends JFrame{

	private ArrayList<ANTSIView> views;
	public ANTSWindow() 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.getContentPane().setLayout(new FlowLayout());
		this.views = new ArrayList<ANTSIView>();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i = 0; i<this.views.size(); i++)
		{
			ANTSIView v = this.views.get(i);
			v.paint(g2d);
		}
		
	}
	
	public void addView(ANTSIView v)
	{
		this.views.add(v);
		
		if(v.getPanel() != null)
		{
			this.add(v.getPanel());
		}
	}

}
