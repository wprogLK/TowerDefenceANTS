package controllers;

import java.awt.event.MouseEvent;
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
	
	
	public ANTSAbstractController()
	{
		this.model = ANTSAbstractModel.getEmptyModel();
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
