package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import models.ANTSAbstractModel;

import views.ANTSAbstractView;
import views.ANTSSimpleRayLightView;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

/**
 * wrapper
 * @author Lukas
 *
 */
public abstract class ANTSAbstractController implements ANTSIController//, MouseListener, MouseMotionListener
{
	protected boolean listenToMouse;
	
	protected boolean isDragged;	//For example to change the graphic in the view if it is dragged
	
	private static ANTSAbstractController emptyAbstractController = new ANTSAbstractController() {};
	
	public ANTSAbstractController()
	{
		this.isDragged = false;
		this.listenToMouse = false;
	}
	
	public ANTSAbstractController(boolean isListenToMouse)
	{
		this.isDragged = false;
		this.listenToMouse = isListenToMouse;
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public boolean isListenToMouse()
	{
		return this.listenToMouse;
	}
	
	@Override
	public ANTSIModel getModel()
	{
		return ANTSAbstractModel.getEmptyModel();
	}
	
	@Override
	public ANTSIView getIView()
	{
		return ANTSAbstractView.getEmptyView();
	}
	
	public final static ANTSAbstractController getEmptyController()
	{
		return emptyAbstractController;
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		this.isDragged = false;	
	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		this.isDragged = true;
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
	
	

}
