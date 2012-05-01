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

import enums.ANTSStateEnum;

import listeners.actionListeners.paint.ANTSDefaultActionListener;
import listeners.actionListeners.paint.ANTSOnlyDrawActionListener;

/**
 * @author Lukas
 *
 */
public abstract class ANTSViewAbstact  implements ANTSIView// extends Canvas
{
	protected ArrayList<ANTSIView> views;
	protected JPanel mainPanel;
	protected  ANTSStateEnum currentPaintState;	
	
	public ANTSViewAbstact()
	{
		this.views = new ArrayList<ANTSIView>();
		this.mainPanel = new JPanel();
		this.currentPaintState = ANTSStateEnum.basic;
		
		this.initComponents();
		this.configMainPanel();
	}	
	
	public final void setPaintState(ANTSStateEnum state)
	{
		this.currentPaintState = state;
		
		for(ANTSIView view: this.views)
		{
			view.setPaintState(state);
		}
	}
	
	@Override
	public final void paint(Graphics2D g)
	{
		this.paintOtherViews(g);
		this.paintView(g);
	}
	
	protected void fireDefaultAction()
	{
		ANTSDefaultActionListener.getInstance().actionPerformed(new ActionEvent(this,1,"BASIC ACTION"));		
	}
	
	//NEW
	protected void fireOnlyDrawAction()
	{
		ANTSOnlyDrawActionListener.getInstance().actionPerformed(new ActionEvent(this,1,"ONLY DRAW ACTION"));
	}
	
	/**
	 * Paint all the views of the ArrayList <code> views </code>
	 * @param g 
	 */
	protected final void paintOtherViews(Graphics2D g)
	{
		for(ANTSIView currentView:this.views)
		{
			//currentView.setRefreshListener(this.refreshListener);
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
	
//	@Override
//	public void setRefreshListener(ActionListener refreshListener)
//	{
//		this.refreshListener = refreshListener;
//	}
	
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
		}
	
//		view.setRefreshListener(this.refreshListener);
	}
	
}
