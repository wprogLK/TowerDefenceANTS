package models;

import java.util.ArrayList;
import java.util.Iterator;

import basics.ANTSDevelopment.ANTSStream;
import basics.ANTSFactory;

import layers.ANTSLayerSystem.Layer;

import interfaces.ANTSIEvent;
import interfaces.ANTSIEventListener;
import interfaces.ANTSIModel;
import interfaces.menus.ANTSIMenuController;

public class ANTSAbstractModel implements ANTSIModel, ANTSIEventListener
{
	private static ANTSAbstractModel emptyModel = new ANTSAbstractModel();
	private ArrayList<ANTSIMenuController> menus;
	
	protected boolean isMouseListener;
	
	protected Layer layer;
	protected ANTSFactory factory;
	
	protected boolean isDragged;
	protected boolean mouseEntered;
	
	public ANTSAbstractModel() 
	{
		this.isMouseListener = false;
		this.layer = Layer.none;
		this.menus = new ArrayList<ANTSIMenuController>();
		
		this.mouseEntered = false;
	}
	
	public ANTSAbstractModel(ANTSFactory factory)
	{
		this.isMouseListener = false;
		this.layer = Layer.none;
		this.factory = factory;
		this.menus = new ArrayList<ANTSIMenuController>();
		
		this.mouseEntered = false;
	}
	
	///////////
	//Getters//
	///////////
	public final static ANTSIModel getEmptyModel()
	{
		return emptyModel;
	}
	
	public Iterator<ANTSIMenuController> getMenuIterator()
	{
		return this.menus.iterator();
	}	
	@Override
	public boolean isMouseListener()
	{
		return this.isMouseListener;
	}
	@Override
	public Layer getLayer()
	{
		return this.layer;
	}	
	
	@Override
	public final boolean getMouseEntered() 
	{
		return this.mouseEntered;
	}

	@Override
	public ANTSFactory getFactory() 
	{
		return this.factory;
	}


	///////////
	//Setters//
	///////////
	
	@Override
	public void setLayer(Layer layer)
	{
		this.layer = layer;
	}
	
	@Override
	public final void setDragged(boolean isDragged)
	{
		this.isDragged = isDragged;
	}
	
	@Override
	public final void setMouseEntered(boolean value) 
	{
		this.mouseEntered = value;
	}

	///////////
	//Special//
	///////////
	
	@Override
	public void update() 
	{
		
	}
	
	//Mouse Detection
	@Override
	public boolean containsPoint(int x, int y)
	{
		return false;
	}

	public void addMenu(ANTSIMenuController m)
	{
		this.menus.add(m);
	}

	@Override
	public void doEvent(ANTSIEvent event) 
	{
		ANTSStream.printDebug("Event source: " + event.getSource());
		
	}

		
	

	
	
	
	
	

}
