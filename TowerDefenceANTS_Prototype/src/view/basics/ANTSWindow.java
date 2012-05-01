package view.basics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import interfaces.ANTSIView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import enums.ANTSStateEnum;

import view.abstracts.ANTSViewAbstact;

public class ANTSWindow extends JFrame implements Runnable
{
	Thread thread;
	ANTSIView currentView;
	private ANTSStateEnum currentState;

	
	private JTextField textMiliseconds;
	
	public ANTSWindow()
	{
		
		this.textMiliseconds = new JTextField("800");
	
		
		this.currentState = ANTSStateEnum.basic;
	}
	
	public void setCurrentView(ANTSIView currentView)
	{
		this.currentView = currentView;
	
		JPanel mainPanel =this.currentView.getPanel();	//Just for testing
		mainPanel.add(textMiliseconds);					//Just for testing
	}
	
	public final void setPaintState(ANTSStateEnum state)
	{
		this.currentState = state;
		this.currentView.setPaintState(state);
//		System.out.println("SET STATE IN WINDOW! " + this.currentState);
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
//		System.out.println("PAINT IN WINDOW! the state is: " + this.currentState);
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
	
	
	public void runAnimation()
	{
		this.thread = new Thread(this);
		this.thread.setPriority(Thread.MAX_PRIORITY);
		this.thread.start();
		
	}
	
	public void stopAnimation()
	{
		if(this.thread != null)
		{
			thread.interrupt();
			thread = null;
		}
	}

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		
		
		try {
			while(t == thread)
			{
				long timeStart = System.currentTimeMillis();
				int miliSeconds;
				
					try
					{
						this.textMiliseconds.setBackground(Color.white);
						String text = this.textMiliseconds.getText();
						miliSeconds = Integer.parseInt(text);
					}
					catch(NumberFormatException nfe)
					{
						this.textMiliseconds.setBackground(Color.red);
						miliSeconds = 500;
					}
					
	
					Thread.sleep(miliSeconds);
				
				long timeEnd = System.currentTimeMillis();
				
				System.out.println("FRAMERATE: 1 Frame per " + (timeEnd-timeStart));
				
				timeStart = System.currentTimeMillis();
				this.repaint();
				Thread.sleep(miliSeconds);
				timeEnd = System.currentTimeMillis();
				
				System.out.println("Duration for one repaint" + (timeEnd-timeStart));
			}
		} catch (InterruptedException e) {
			
		}
	}
}
