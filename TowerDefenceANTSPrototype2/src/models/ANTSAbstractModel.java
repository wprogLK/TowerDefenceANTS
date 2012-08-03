package models;

import java.util.ArrayList;
import java.util.Iterator;

import controllers.ANTSCircleMenuController;

import basics.ANTSFactory;
import layers.ANTSLayerSystem.Layer;
import interfaces.ANTSIModel;

public class ANTSAbstractModel implements ANTSIModel
{
	private static ANTSAbstractModel emptyModel = new ANTSAbstractModel();
	private ArrayList<ANTSCircleMenuController> menus;
	
	protected boolean isMouseListener;
	
	protected Layer layer;
	protected ANTSFactory factory;
	
	protected boolean isDragged;
	
	public ANTSAbstractModel() 
	{
		this.isMouseListener = false;
		this.layer = Layer.none;
		this.menus = new ArrayList<ANTSCircleMenuController>();
	}
	
	public ANTSAbstractModel(ANTSFactory factory)
	{
		this.isMouseListener = false;
		this.layer = Layer.none;
		this.factory = factory;
		this.menus = new ArrayList<ANTSCircleMenuController>();
	}
	
	public final static ANTSIModel getEmptyModel()
	{
		return emptyModel;
	}

	@Override
	public void update() 
	{
		
	}
	
	public void addMenu(ANTSCircleMenuController m)
	{
		this.menus.add(m);
	}
	
	
	public Iterator<ANTSCircleMenuController> getMenuIterator()
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
	public void setLayer(Layer layer)
	{
		this.layer = layer;
	}
	
	
	@Override
	public final void setDragged(boolean isDragged)
	{
		this.isDragged = isDragged;
	}
	
	//Mouse Detection
	@Override
	public boolean containsPoint(int x, int y)
	{
		return false;
	}
}
