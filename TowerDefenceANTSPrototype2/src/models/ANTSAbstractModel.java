package models;

import basics.ANTSFactory;
import layers.ANTSLayerSystem.Layer;
import interfaces.ANTSIModel;

public class ANTSAbstractModel implements ANTSIModel
{
	private static ANTSAbstractModel emptyModel = new ANTSAbstractModel();
	protected boolean isMouseListener;
	
	protected Layer layer;
	protected ANTSFactory factory;
	
	protected boolean isDragged;
	
	public ANTSAbstractModel() 
	{
		this.isMouseListener = false;
		this.layer = Layer.none;
	}
	
	public ANTSAbstractModel(ANTSFactory factory)
	{
		this.isMouseListener = false;
		this.layer = Layer.none;
		this.factory = factory;
	}
	
	public final static ANTSIModel getEmptyModel()
	{
		return emptyModel;
	}

	@Override
	public void update() 
	{
		
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
