package view.basics;

import java.awt.Color;
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

import enums.ANTSStateEnum;


public class ANTSWindow extends JFrame
{
	ANTSIView currentView;
	private ANTSStateEnum currentState;
	
	private ANTSGameLogicUpdater gameLogicUpdater;
	private ANTSPainter painter;
	
	private JTextField textMiliseconds;
	private boolean ready;
	
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
		
		
		ANTSPainter.setGraphics(g2d);
		this.ready = true;
	}
	
	public boolean isReady()
	{
		return this.ready;
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
		if(this.gameLogicUpdater.getRunning())
		{
			this.gameLogicUpdater.on();
		}
		else
		{
			this.gameLogicUpdater.start();
		}
		
		if(this.painter.getRunning())
		{
			this.painter.on();
		}
		else
		{
			this.painter.start();
		}
		
	}
	
	public void stopAnimation()
	{
		System.out.println("------------------------ STATE: " + this.painter.getState());
		this.painter.off();
		this.gameLogicUpdater.off();
	}
}
