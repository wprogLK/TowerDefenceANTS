/**
 * 
 */
package view.abstracts;

import interfaces.ANTSIView;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * @author Lukas
 *
 */
public abstract class ANTSViewAbstact implements ANTSIView
{
	protected ArrayList<ANTSIView> views;
	
	public ANTSViewAbstact()
	{
		this.views = new ArrayList<ANTSIView>();
	}
	
	@Override
	public final void paint(Graphics2D g)
	{
		paintOtherViews(g);
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
	
	
}
