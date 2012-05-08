package view.basics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import helper.ANTSGameLogicUpdater;
import helper.ANTSPainter;
import interfaces.ANTSIView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import enums.ANTSStateEnum;

import view.abstracts.ANTSViewAbstact;

public class ANTSWindow extends JFrame// implements Runnable
{
	//Thread thread;
	ANTSPainter painter;
	ANTSGameLogicUpdater logicUpdater;
	
	ANTSIView currentView;
	private ANTSStateEnum currentState;

	
	private JTextField textMiliseconds;
	
	public ANTSWindow()
	{
		this.painter = new ANTSPainter(this);
		this.logicUpdater = new ANTSGameLogicUpdater();
		
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
		super.paintComponents(g);
		//TODO Make it better
//		JPanel mainPanel =this.currentView.getPanel();
//		Graphics gp = mainPanel.getGraphics();
//		Graphics2D g2d = (Graphics2D) gp;
//		
//		this.configRendering(g2d);
//		ANTSPainter.setGraphics(g2d);
		
		
//OLD
//		super.paintComponents(g);
//		System.out.println("WINDOW PAINT");
//		JPanel mainPanel =this.currentView.getPanel();
//		Graphics gp = mainPanel.getGraphics();
//		Graphics2D g2d = (Graphics2D) gp;
//		
//		this.configRendering(g2d);
//		
//		ANTSPainter t = new ANTSPainter();
//		ANTSGameLogicUpdater gameLogicUpdater = new ANTSGameLogicUpdater();
//		
//		t.setGraphics(g2d);
//
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
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	private void configRendering(Graphics2D g2d) 
	{
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	
	public void runAnimation()
	{
		this.logicUpdater.execute();//	GOOD
		this.painter.execute();
	}
	
	public void stopAnimation()
	{
		this.logicUpdater.exit();
		this.painter.exit();
	}
	
	public JPanel getCurrentMainPanel()
	{
		return this.currentView.getPanel();
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
}
