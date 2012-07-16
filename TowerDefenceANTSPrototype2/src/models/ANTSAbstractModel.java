package models;

import layers.ANTSLayerSystem.Layer;
import interfaces.ANTSIModel;

public class ANTSAbstractModel implements ANTSIModel
{
	private static ANTSAbstractModel emptyModel = new ANTSAbstractModel();
	protected boolean isMouseListener;
	
	protected Layer layer;
	
	
	public ANTSAbstractModel() 
	{
		this.isMouseListener = false;
		this.layer = Layer.none;
	}
	
	public final static ANTSIModel getEmptyModel()
	{
		return emptyModel;
	}

	@Override
	public void update() 
	{
		
	}
	
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
}
