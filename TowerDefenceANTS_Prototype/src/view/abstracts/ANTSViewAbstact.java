/**
 * 
 */
package view.abstracts;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @author Lukas
 *
 */
public abstract class ANTSViewAbstact implements ANTSIView
{
	protected ArrayList<ANTSIView> views;
	protected JPanel mainPanel;
	
	public ANTSViewAbstact()
	{
		this.views = new ArrayList<ANTSIView>();
		this.mainPanel = new JPanel();
		
		this.initComponents();
		this.configMainPanel();
	}	
	
	@Override
	public final void paint(Graphics2D g)
	{
		
		
		//paintOtherViews(g);
		this.paintView(g);
	}
	
	/**
	 * Paint all the views of the ArrayList <code> views </code>
	 * @param g 
	 */
	protected final void paintOtherViews(Graphics2D g)
	{
		for(ANTSIView currentView:this.views)
		{
			currentView.paint(g);
		}
	}
	
	/**
	 * Paint this view
	 * @param g
	 */
	protected void paintView(Graphics2D g)
	{
	
	}
	
	
	protected void configMainPanel()
	{
		
	}
	
	protected void initComponents()
	{
		
	}
	
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public final JPanel getPanel()
	{
		return this.mainPanel;
	}
	
	
}
