package controllers;

import java.awt.event.MouseEvent;

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
	
	public ANTSAbstractController()
	{
	}
	
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	
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

	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{

	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
}
