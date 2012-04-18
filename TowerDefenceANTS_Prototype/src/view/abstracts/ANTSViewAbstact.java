/**
 * 
 */
package view.abstracts;

import interfaces.ANTSIView;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @author Lukas
 *
 */
public abstract class ANTSViewAbstact extends Canvas implements ANTSIView 
{
	protected ArrayList<ANTSIView> views;
	protected JPanel mainPanel;
	protected ActionListener refreshListener;
	
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
		paintOtherViews(g);
		this.paintView(g);
	}
	
	protected void fireRefreshAction()
	{
		this.refreshListener.actionPerformed(new ActionEvent(this,1,""));	
	}
	
	/**
	 * Paint all the views of the ArrayList <code> views </code>
	 * @param g 
	 */
	protected final void paintOtherViews(Graphics2D g)
	{
		for(ANTSIView currentView:this.views)
		{
			currentView.setRefreshListener(this.refreshListener);
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
	
	@Override
	public void setRefreshListener(ActionListener refreshListener)
	{
		this.refreshListener = refreshListener;
	}
	
	///////////
	//GETTERS//
	///////////
	
	public ANTSViewAbstact getAbstract()
	{
		return this;
	}
	
	@Override
	public final JPanel getPanel()
	{
		return this.mainPanel;
	}
	
	public final boolean isPanelEmpty()
	{
		return this.mainPanel.getComponentCount()==0;
	}
	
	
	public  void addInternalView(ANTSIView view)
	{
		this.views.add(view);
		
		if(!view.isPanelEmpty())
		{
			this.mainPanel.add(view.getPanel());
			System.out.println("Main panel is  NOT empty");
			System.out.println("view to add: " + view.toString());
		}
		else
		{
			System.out.println("Main panel is empty");
			System.out.println("view to add: " + view.toString());
		}
	
		view.setRefreshListener(this.refreshListener);
	}
	
	
	
}
