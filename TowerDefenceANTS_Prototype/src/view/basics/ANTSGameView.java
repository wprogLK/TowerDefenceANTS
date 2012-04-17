/**
 * 
 */
package view.basics;


import java.awt.FlowLayout;
import java.awt.Graphics2D;
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
	
	public ANTSGameView(ANTSGameModel model)
	{
		super();
		this.model = model;
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
		//g.drawLine(30,0, 200, 200);		//Example
		Line2D.Double line = new Line2D.Double(30,0,200,200);
		g.draw(line);
	}

	
}
