/**
 * 
 */
package view.basics;


import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.JButton;

import model.basics.ANTSGameModel;

import view.abstracts.ANTSViewAbstact;

/**
 * @author Lukas
 *
 */
public class ANTSGameView extends ANTSViewAbstact 
{
	private JButton button;
	
	private boolean draw;
	public ANTSGameView(ANTSGameModel model)
	{
		super();
		this.draw = false;
	}
	
	@Override
	protected void initComponents()
	{
		this.button = new JButton("Test");
	}
	
	@Override
	protected void configMainPanel()
	{
		this.mainPanel.setSize(800, 600);
		this.mainPanel.setLayout(new FlowLayout());
		this.mainPanel.add(this.button);
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
	
	public void refresh()
	{
		this.fireRefreshAction();
	}
	
	/////////////
	//LISTENERS//
	/////////////
	public void addButtonTestListener(ActionListener listener)
	{
		this.button.addActionListener(listener);
	}
	
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
	
}
