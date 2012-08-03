package controllers;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import layers.ANTSLayerSystem.Layer;

import models.ANTSAbstractModel;

import views.ANTSAbstractView;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

/**
 * wrapper
 * @author Lukas
 *
 */
public abstract class ANTSAbstractController implements ANTSIController
{
	
	private static ANTSAbstractController emptyAbstractController = new ANTSAbstractController() {};
	private ANTSIModel model;
	protected ANTSIView iview;
	
	
	public ANTSAbstractController()
	{
		this.model = ANTSAbstractModel.getEmptyModel();
		this.iview = ANTSAbstractView.getEmptyView();
	}
	
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	
	@Override
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	@Override
	public ANTSIView getIView()
	{
		return this.iview;
	}
	
	protected void setIModel(ANTSIModel m)
	{
		this.model = m;
	}
	
	public final static ANTSAbstractController getEmptyController()
	{
		return emptyAbstractController;
	}
	
	@Override
	public Layer getLayer()
	{
		return  this.model.getLayer();
	}
	
	@Override
	public void setLayer(Layer layer)
	{
		 this.model.setLayer(layer);
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	@Override
	public final void mouseClicked(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.mouseClickedANTS(e);
		}
	}

	@Override
	public final void mouseEntered(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.mouseEnteredANTS(e);
		}
	}

	@Override
	public final void mouseExited(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.mouseExitedANTS(e);
		}
	}

	@Override
	public final void mousePressed(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.mousePressedANTS(e);
			
			if(e.isPopupTrigger())
			{
				System.out.println("Menu by pressing");	//TODO
			}
		}
	}

	@Override
	public final void mouseReleased(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.model.setDragged(false);
			this.mouseReleasedANTS(e);
			
			if(e.isPopupTrigger())
			{
				System.out.println("Menu by releasing");	//TODO
			}
		}
	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////

	@Override
	public final void mouseDragged(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.model.setDragged(true);
			this.mouseDraggedANTS(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.mouseMovedANTS(e);
		}
	}
	
	protected final boolean containsPoint(int x, int y)
	{
		return this.model.containsPoint(x, y) || this.iview.containsPoint(x,y);
	}
	
	///////////////////
	//Mouse "Actions"//
	///////////////////
	
	public void mouseClickedANTS(MouseEvent e) 
	{

	}

	public void mouseEnteredANTS(MouseEvent e) 
	{

	}

	public void mouseExitedANTS(MouseEvent e) 
	{

	}

	protected void mousePressedANTS(MouseEvent e) 
	{

	}

	protected void mouseReleasedANTS(MouseEvent e) 
	{

	}
	
	public void mouseDraggedANTS(MouseEvent arg0) 
	{

	}

	public void mouseMovedANTS(MouseEvent arg0) 
	{

	}
	
}
