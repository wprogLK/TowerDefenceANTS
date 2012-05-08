package view.basics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import helper.ANTSGameLogicUpdater;
import helper.ANTSPainter;
import interfaces.ANTSIView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import enums.ANTSStateEnum;

import view.abstracts.ANTSViewAbstact;

public class ANTSWindow extends JFrame implements Runnable
{
	private Thread thread;
	ANTSIView currentView;
	private ANTSStateEnum currentState;
	
	private JTextField textMiliseconds;
	
	private long timestepUpdateGameLogic;
	private long startTimeUpdateGameLogic;
	private long endTimeUpdateGameLogic;
	
	private long timestepPaint;
	private long startTimePaint;
	private long endTimePaint;
	
	private long timestepBasic;
	private long startTimeBasic;
	private long endTimeBasic;
	
	private long milisecondTimerBasic;
	private int counterBasic;
	
	private long milisecondTimerUpdateGameLogic;
	private int counterUpdateGameLogic;
	
	private long milisecondTimerPaint;
	private int counterPaint;
	
	private ArrayList<Integer> steps = new ArrayList<Integer>();
	private ArrayList<Integer> paints = new ArrayList<Integer>();
	private ArrayList<Integer> updates = new ArrayList<Integer>();
	
	public ANTSWindow()
	{
		this.textMiliseconds = new JTextField("800");
	
		
		this.currentState = ANTSStateEnum.basic;
		this.resetTimers();
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
//		this.currentView.setPaintState(state);
		ANTSPainter.setPaintState(state);
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
		this.startTimeBasic = System.currentTimeMillis();
		
		//System.out.println("C: start repaint");
		super.paintComponents(g);
		
		JPanel mainPanel =this.currentView.getPanel();
		Graphics gp = mainPanel.getGraphics();
		Graphics2D g2d = (Graphics2D) gp;
		
		this.configRendering(g2d);
		
		ANTSPainter t = new ANTSPainter();
		ANTSGameLogicUpdater gameLogicUpdater = new ANTSGameLogicUpdater();
		
		t.setGraphics(g2d);
		
		try 
		{
//			System.out.println("A: gameLogicUpdater start");
			this.startTimeUpdateGameLogic = System.currentTimeMillis();
			gameLogicUpdater.start();
			gameLogicUpdater.join();
			this.endTimeUpdateGameLogic = System.currentTimeMillis();
			this.timestepUpdateGameLogic = this.endTimeUpdateGameLogic - this.startTimeUpdateGameLogic;
			this.milisecondTimerUpdateGameLogic+=this.timestepUpdateGameLogic;
			this.counterUpdateGameLogic++;
			this.checkTimeUpUpdateGameLogic();
//			System.out.println("A: gameLogicUpdater end");
			
//			System.out.println("B: painter start");
			this.startTimePaint = System.currentTimeMillis();
			t.start();
			t.join();
			this.endTimePaint = System.currentTimeMillis();
			this.timestepPaint = this.endTimePaint - this.startTimePaint;
			this.milisecondTimerPaint+=this.timestepPaint;
			this.counterPaint++;
			this.checkTimeUpPaint();
//			System.out.println("B: painter end");
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("C: end repaint");
		
//		try {
//			Thread.sleep(2);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		this.endTimeBasic = System.currentTimeMillis();
		this.timestepBasic = this.endTimeBasic - this.startTimeBasic;
		this.milisecondTimerBasic+=this.timestepBasic;
		this.counterBasic++;
		this.checkTimeUpBasic();
		
		//this.showTimes();
	}
	
	private void showTimes()
	{
		System.out.println("TimeStep Basic: " + this.timestepBasic);
		System.out.println("TimeStep Update: " + this.timestepUpdateGameLogic);
		System.out.println("TimeStep Paint: " + this.timestepPaint);
	}
	
	private void resetTimers()
	{
		this.counterBasic = 0;
		this.counterPaint = 0;
		this.counterUpdateGameLogic = 0;
		
		this.milisecondTimerBasic = 0;
		this.milisecondTimerPaint = 0;
		this.milisecondTimerUpdateGameLogic = 0;
	}
	
//	private void showStats()
//	{
//		System.out.println("Steps per second: " + (this.counterBasic/(this.milisecondTimerBasic/1000)));
//		System.out.println("Updates per second: " + (this.counterUpdateGameLogic/(this.milisecondTimerUpdateGameLogic/1000)));
//		System.out.println("Frames per second: " + (this.counterPaint/(this.milisecondTimerPaint/1000)));
//	}
	
	public void showStats()
	{
		System.out.println("----------------------------");
		System.out.println("STATS:");
		System.out.println("----------------------------");
		
		System.out.println("steps per second");
		int countS = 0;
		int sumS = 0;
		for(int i:this.steps)
		{
			System.out.println(i);
			sumS+=i;
			countS++;
		}
		if(countS==0)
		{
			System.out.println("Average steps per second: --");
		}
		else
		{
			System.out.println("Average steps per second: " + (sumS/countS));
		}
		
		
		System.out.println("updates per second");
		int countU = 0;
		int sumU = 0;
		for(int i:this.updates)
		{
			System.out.println(i);
			sumU+=i;
			countU++;
		}
		if(countU==0)
		{
			System.out.println("Average updates per second: --");
		}
		else
		{
			System.out.println("Average updates per second: " + (sumU/countU));
		}
	
		
		System.out.println("frames per second");
		int countF = 0;
		int sumF = 0;
		for(int i:this.paints)
		{
			System.out.println(i);
			sumF+=i;
			countF++;
		}
		if(countF==0)
		{
			System.out.println("Average frames per second: --");
		}
		else
		{
			System.out.println("Average frames per second: " + (sumF/countF));
		}
		
	}
	
	private void checkTimeUpPaint()
	{
		if(this.milisecondTimerPaint>=1000)
		{
			int fps =  (int) (this.counterPaint/(this.milisecondTimerPaint/1000));
//			System.out.println("Frames per second: " + fps);
			this.paints.add(fps);
			this.counterPaint = 0;
			this.milisecondTimerPaint = 0;
		}
	}
	
	private void checkTimeUpBasic()
	{
		if(this.milisecondTimerBasic>=1000)
		{
			int sps =  (int) (this.counterBasic/(this.milisecondTimerBasic/1000));
//			System.out.println("Steps per second: " + sps );
			this.steps.add(sps);
			this.counterBasic = 0;
			this.milisecondTimerBasic = 0;
		}
	}
	
	private void checkTimeUpUpdateGameLogic()
	{
		if(this.milisecondTimerUpdateGameLogic>=1000)
		{
			int ups = (int) (this.counterUpdateGameLogic/(this.milisecondTimerUpdateGameLogic/1000));
//			System.out.println("Updates per second: " + ups);
			this.updates.add(ups);
			this.counterUpdateGameLogic = 0;
			this.milisecondTimerUpdateGameLogic = 0;
		}
	}
	
	
	
	private void configRendering(Graphics2D g2d) 
	{
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	
	public void runAnimation()
	{
		this.thread = new Thread(this);
		this.thread.setPriority(Thread.MIN_PRIORITY);
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
	
// OLD
//	@Override
//	public void run() {
//		Thread t = Thread.currentThread();
//		
//		
//		try {
//			while(t == thread)
//			{
//				long timeStart = System.currentTimeMillis();
//				int miliSeconds;
//				
//					try
//					{
//						this.textMiliseconds.setBackground(Color.white);
//						String text = this.textMiliseconds.getText();
//						miliSeconds = Integer.parseInt(text);
//					}
//					catch(NumberFormatException nfe)
//					{
//						this.textMiliseconds.setBackground(Color.red);
//						miliSeconds = 500;
//					}
//					
//	
//					Thread.sleep(miliSeconds);
//				
//				long timeEnd = System.currentTimeMillis();
//				
////				System.out.println("FRAMERATE: 1 Frame per " + (timeEnd-timeStart));
//				
//				timeStart = System.currentTimeMillis();
//				this.repaint();
//				
//				
//				Thread.sleep(miliSeconds);
//				timeEnd = System.currentTimeMillis();
//				
////				System.out.println("Duration for one repaint" + (timeEnd-timeStart));
//			}
//		} catch (InterruptedException e) {
//			
//		}
//	}
	

	@Override
	/**
	 * this method should be the gameLogic timer and the paint timer
	 */
	public void run() {
		Thread t = Thread.currentThread();

//		try {
			while(t == thread)
			{
				//int miliSeconds=2;
				
					
	
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//				}
				
				
//				System.out.println("FRAMERATE: 1 Frame per " + (timeEnd-timeStart));
				
				this.repaint();
				
				
				
//				System.out.println("Duration for one repaint" + (timeEnd-timeStart));
			}
//		} catch (InterruptedException e) {
//			
//		}
	}
}
