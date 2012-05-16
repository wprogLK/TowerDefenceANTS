package basics;

import interfaces.ANTSIView;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ANTSWindow extends JFrame{

	private ArrayList<ANTSIView> views;
	private float interpolation;
	
	private JPanel navigation;
	private JPanel graphic;
	
	public ANTSWindow() 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		
		this.navigation = new JPanel();
		this.graphic = new JPanel();
		
		this.navigation.setLayout(new FlowLayout());
		this.navigation.setBackground(Color.BLUE);
		
		this.graphic.setBackground(Color.GRAY);
		
		this.getContentPane().setLayout(new GridLayout(2,1));
		this.views = new ArrayList<ANTSIView>();
		
		this.getContentPane().add(this.navigation);
		this.getContentPane().add(this.graphic);
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i = 0; i<this.views.size(); i++)
		{
			ANTSIView v = this.views.get(i);
			v.paint(g2d, interpolation);
		}
	}
	
	public void addView(ANTSIView v)
	{
		this.views.add(v);
		
//		if(v.getPanel() != null)	//OLD
//		{
//			this.add(v.getPanel());
//		}
	}

	public void paintWithInterpolation(float interpolation) 
	{
		this.interpolation = interpolation;
		this.repaint();
	}
	
	public void addCanvas(Canvas c)
	{
		this.graphic.add(c);
	}

}
