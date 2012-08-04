package controllers;

import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import controllers.menus.ANTSCircleMenuController;

import basics.ANTSDevelopment.ANTSStream;

import layers.ANTSLayerSystem.Layer;

import models.ANTSAbstractModel;

import views.ANTSAbstractView;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuController;

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
			if(SwingUtilities.isLeftMouseButton(e))
			{
				this.mouseLeftClickedANTS(e);
			}
			else if(SwingUtilities.isMiddleMouseButton(e))
			{
				this.mouseWheelClickedANTS(e);
			}
			else if(SwingUtilities.isRightMouseButton(e))
			{
				this.mouseRightClickedANTS(e);
			}
			else
			{
				ANTSStream.print("Unkown button was clicked!");
			}
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
			if(SwingUtilities.isLeftMouseButton(e))
			{
				this.mouseLeftPressedANTS(e);
			}
			else if(SwingUtilities.isMiddleMouseButton(e))
			{
				this.mouseWheelPressedANTS(e);
			}
			else if(SwingUtilities.isRightMouseButton(e))
			{
				this.mouseRightPressedANTS(e);
			}
			else
			{
				ANTSStream.print("Unkown button was pressed!");
			}
			
			if(e.isPopupTrigger())
			{
				this.showMenu(e);
			}
		}
	}

	@Override
	public final void mouseReleased(MouseEvent e) 
	{
		if(this.containsPoint(e.getX(),e.getY()))
		{
			this.model.setDragged(false);
			
			if(SwingUtilities.isLeftMouseButton(e))
			{
				this.mouseLeftReleasedANTS(e);
			}
			else if(SwingUtilities.isMiddleMouseButton(e))
			{
				this.mouseWheelReleasedANTS(e);
			}
			else if(SwingUtilities.isRightMouseButton(e))
			{
				this.mouseRightReleasedANTS(e);
			}
			else
			{
				ANTSStream.print("Unkown button was released!");
			}
			
			if(e.isPopupTrigger())
			{
				this.showMenu(e);
			}
			else
			{
				
			}
		}
	}
	
	private void showMenu(MouseEvent e)
	{
		System.out.println("show menu"); //TODO
		
		Iterator<ANTSIMenuController> menuIterator = this.model.getMenuIterator();
		
		while(menuIterator.hasNext())
		{
			ANTSIMenuController c = menuIterator.next();
			c.getModel().setMouseEntered(true);
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
	
	
	
	public void mouseRightClickedANTS(MouseEvent e) 
	{

	}
	
	public void mouseLeftClickedANTS(MouseEvent e) 
	{

	}

	public void mouseWheelClickedANTS(MouseEvent e) 
	{

	}
	
	public void mouseEnteredANTS(MouseEvent e) 
	{

	}

	public void mouseExitedANTS(MouseEvent e) 
	{

	}

	public void mouseRightPressedANTS(MouseEvent e) 
	{

	}
	
	public void mouseLeftPressedANTS(MouseEvent e) 
	{

	}

	public void mouseWheelPressedANTS(MouseEvent e) 
	{

	}

	public void mouseRightReleasedANTS(MouseEvent e) 
	{

	}
	
	public void mouseLeftReleasedANTS(MouseEvent e) 
	{

	}

	public void mouseWheelReleasedANTS(MouseEvent e) 
	{

	}
	public void mouseDraggedANTS(MouseEvent arg0) 
	{

	}

	public void mouseMovedANTS(MouseEvent arg0) 
	{

	}
	
}
