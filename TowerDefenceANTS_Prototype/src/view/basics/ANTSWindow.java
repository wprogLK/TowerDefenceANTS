package view.basics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import helper.ANTSGameLogicUpdater;
import helper.ANTSPainter;
import interfaces.ANTSIView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.ANTSStateEnum;


public class ANTSWindow extends JFrame implements Runnable
{
	Thread thread;
	ANTSIView currentView;
	private ANTSStateEnum currentState;
	
	private ANTSGameLogicUpdater gameLogicUpdater;
	private ANTSPainter painter;
	
	private JTextField textMiliseconds;
	private boolean ready;
	
	public ANTSWindow()
	{
		this.textMiliseconds = new JTextField("800");
		this.ready = true;
		
		this.currentState = ANTSStateEnum.basic;
		
		this.gameLogicUpdater = new ANTSGameLogicUpdater(this);
		this.painter= new ANTSPainter(this, gameLogicUpdater);
		this.gameLogicUpdater.setPainter(this.painter);
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
		ANTSPainter.setPaintState(state);
	}
	
	@Override
	public void paint(Graphics g)
	{	
		this.ready = false;
		super.paintComponents(g);
		
		JPanel mainPanel =this.currentView.getPanel();
		Graphics gp = mainPanel.getGraphics();
		Graphics2D g2d = (Graphics2D) gp;
		
		this.configRendering(g2d);
		
		
//		ANTSPainter t = new ANTSPainter(this);
//		ANTSGameLogicUpdater gameLogicUpdater = new ANTSGameLogicUpdater(this);
		
		//t.setGraphics(g2d);
		ANTSPainter.setGraphics(g2d);
		this.ready = true;
//		try 
//		{
//			gameLogicUpdater.start();
//			gameLogicUpdater.join();
//			
//			t.start();
//			t.join();
//		} 
//		catch (InterruptedException e) 
//		{
//			e.printStackTrace();
//		}
	}
	
	public boolean isReady()
	{
		return this.ready;
	}
	
	private void configRendering(Graphics2D g2d) 
	{
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	
	public void runAnimation()
	{
		
		this.gameLogicUpdater.start();
		this.painter.start();
		//OLD
//		ANTSGameLogicUpdater gameLogicUpdater = new ANTSGameLogicUpdater(this);
//		ANTSPainter t = new ANTSPainter(this, gameLogicUpdater);
//	
//		gameLogicUpdater.start();
//		t.start();
		
		
		
		//OLD OLD
//		t.join();
		
//		this.thread = new Thread(this);
//		this.thread.setPriority(Thread.MIN_PRIORITY);
//		this.thread.start();
	}
	
	public void stopAnimation()
	{
		System.out.println("------------------------ STATE: " + this.painter.getState());
		this.painter.off();
		this.gameLogicUpdater.off();
		
//		this.painter.interrupt();
//		this.gameLogicUpdater.interrupt();
		
//		if(this.thread != null)
//		{
//			thread.interrupt();
//			thread = null;
//		}
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
				
				timeStart = System.currentTimeMillis();
				this.repaint();
				
				
				Thread.sleep(miliSeconds);
				timeEnd = System.currentTimeMillis();
			}
		} 
		catch (InterruptedException e)
		{
			
		}
	}
}
