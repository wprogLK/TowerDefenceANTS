/**
 * 
 */
package view.basics;


import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.JButton;

import listeners.actionListeners.paint.ANTSAnimateActionListener;
import listeners.actionListeners.paint.ANTSDefaultActionListener;
import listeners.actionListeners.paint.ANTSOnlyDrawActionListener;
import listeners.actionListeners.paint.ANTSPaintActionListenerAbstract;
import listeners.actionListeners.paint.ANTSRunActionListener;
import listeners.actionListeners.paint.ANTSStopActionListener;
import model.basics.ANTSGameModel;

import view.abstracts.ANTSViewAbstact;

/**
 * @author Lukas
 *
 */
public class ANTSGameView extends ANTSViewAbstact 
{
	private JButton button;
	private JButton buttonDefaultPaint;
	private JButton buttonOnlyDrawPaint;
	private JButton buttonRun;
	private JButton buttonStop;
	
	private boolean draw;
	public ANTSGameView(ANTSGameModel model)
	{
		super();
		this.draw = false;
	}
	
	@Override
	protected void initComponents()
	{
		this.buttonRun = new JButton("Start");
		this.buttonRun.setActionCommand("Run");
		this.buttonRun.addActionListener(ANTSRunActionListener.getInstance());	//NEW: add actionListener here
		this.buttonRun.addActionListener(new RunStopButtonListener());
		
		this.buttonStop = new JButton("Stop");
		this.buttonStop.setActionCommand("Stop");
		this.buttonStop.addActionListener(ANTSStopActionListener.getInstance());	//NEW: add actionListener here
		this.buttonStop.addActionListener(new RunStopButtonListener());
		
		this.button = new JButton("Default");
		this.button.addActionListener(ANTSDefaultActionListener.getInstance());	//NEW: add actionListener here
		
		this.buttonDefaultPaint = new JButton("animated paint");
		this.buttonDefaultPaint.addActionListener(ANTSAnimateActionListener.getInstance());
		
		this.buttonOnlyDrawPaint = new JButton("Only draw paint");
		this.buttonOnlyDrawPaint.addActionListener(ANTSOnlyDrawActionListener.getInstance());
	}
	
	@Override
	protected void configMainPanel()
	{
		this.mainPanel.setSize(800, 600);
		this.mainPanel.setLayout(new FlowLayout());
		
		this.mainPanel.add(this.button);
		this.mainPanel.add(this.buttonDefaultPaint);
		this.mainPanel.add(this.buttonOnlyDrawPaint);
		this.mainPanel.add(this.buttonRun);
		this.mainPanel.add(this.buttonStop);
	}
	
	@Override
	protected void paintView(Graphics2D g)
	{
		if(this.draw)
		{
			Line2D.Double line = new Line2D.Double(30,0,200,200);	//Example
			g.draw(line);
		}
	}
	
	
	//TODO: change action
	public void refresh()
	{
		this.fireDefaultAction();
	}
	
	
	/////////////
	//LISTENERS//
	/////////////
// No longer needed
//	public void addButtonTestListener(ActionListener listener)
//	{
//		this.button.addActionListener(listener);
//		
//	}
	
	///////////
	//ACTIONS//
	///////////
	
	public void setDraw(boolean drawState)
	{
		this.draw = drawState;
	}
	
	///////////
	//GETTERS//
	///////////
	
	public  boolean getDraw()
	{
		return this.draw;
	}
	
	public String toString()
	{
		return "GameView";
	}
	
	

	private class RunStopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(e.getActionCommand().equals("Run"))
			{
				buttonRun.setEnabled(false);
				buttonStop.setEnabled(true);
			}
			else if(e.getActionCommand().equals("Stop"))
			{
				buttonRun.setEnabled(true);
				buttonStop.setEnabled(false);
			}
			
		}

	}

}
