package interfaces;

import layers.ANTSLayerSystem.Layer;

public interface ANTSIModel
{
	public void update();
	
	public Layer getLayer();
	public void setLayer(Layer layer);

	boolean isMouseListener();

	boolean containsPoint(int x, int y);

	void setDragged(boolean isDragged);
}
