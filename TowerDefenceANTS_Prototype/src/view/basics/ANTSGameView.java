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

import interfaces.ANTSIView;

/**
 * @author Lukas
 *
 */
public class ANTSGameView extends ANTSViewAbstact implements ANTSIView
{
	private JButton button;
	
	private ANTSGameModel model;
	private boolean draw;
	public ANTSGameView(ANTSGameModel model)
	{
		super();
		this.model = model;
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
		this.mainPanel.setOpaque(true);
	}
	
	@Override
	protected void paintView(Graphics2D g)
	{
		if(this.draw)
		{
			//g.drawLine(30,0, 200, 200);		//Example
			Line2D.Double line = new Line2D.Double(30,0,200,200);
			g.draw(line);
		}
	}
	
	public void refresh()
	{
		this.mainPanel.repaint();
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
		System.out.println("UPDATE");
	}
	
	///////////
	//GETTERS//
	///////////
	
	public  boolean getDraw()
	{
		return this.draw;
	}
	
}
