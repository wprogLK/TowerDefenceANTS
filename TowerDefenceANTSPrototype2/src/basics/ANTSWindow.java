package basics;

import interfaces.ANTSIView;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.ANTSSwitchLightListener;

public class ANTSWindow extends JFrame implements ComponentListener
{
	private ArrayList<ANTSIView> views;
	private float interpolation;
	
	private JPanel navigation;	//Panel for buttons and other stuff
	private JPanel graphic;		//Panel for graphics
	
	private JButton buttonUpdate;
	private Canvas canvas;
	
	public ANTSWindow() 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		
		this.navigation = new JPanel();
		this.graphic = new JPanel();
		
		this.navigation.setLayout(new FlowLayout());
		this.getContentPane().setLayout(new BorderLayout());
		
		this.views = new ArrayList<ANTSIView>();
		
		//Test button:
		this.buttonUpdate = new JButton("Switch light");
		this.buttonUpdate.addActionListener(ANTSSwitchLightListener.getInstance());
		
		this.getContentPane().add(this.navigation, BorderLayout.PAGE_START);
		this.getContentPane().add(this.graphic, BorderLayout.CENTER);
		this.navigation.add(this.buttonUpdate);
		
		//this.navigation.setSize(100, 100);
		this.graphic.setSize(600,600);
		
		this.addComponentListener(this);
	}
	
	//Do it only once!
	public void addCanvas(Canvas c)
	{
		if(this.canvas == null)
		{
			this.canvas = c;
			this.canvas.setSize(this.graphic.getWidth(), this.graphic.getHeight());
			this.graphic.add(this.canvas);
			this.canvas.setBounds(0,0, this.graphic.getWidth(), this.graphic.getHeight());
		}
	}
	
	
	public int getWidthOfGraphics()
	{
		return this.graphic.getWidth();
	}
	
	public int getHeightOfGraphics()
	{
		return this.graphic.getHeight();
	}

	@Override
	public void componentHidden(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void componentMoved(ComponentEvent arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void componentResized(ComponentEvent e) {
		System.out.println("RESIZE");
		int width = this.getContentPane().getWidth();//e.getComponent().getWidth();
		int height = e.getComponent().getHeight()-this.navigation.getHeight();
		
		this.graphic.setSize(width, height);
		if(this.canvas != null)
		{
			this.canvas.setSize(width,height);
			this.canvas.repaint();
		}
		
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
	
	public void paint(Graphics g)
	{
		super.paintComponents(g);
	}

}
