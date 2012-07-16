package interfaces;

import layers.ANTSLayerSystem.Layer;

public interface ANTSIModel
{
	public void update();
	
	public Layer getLayer();
	public void setLayer(Layer layer);
}
