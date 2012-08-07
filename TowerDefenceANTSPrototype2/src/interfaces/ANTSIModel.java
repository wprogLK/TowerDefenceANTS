package interfaces;

import interfaces.menus.ANTSIMenuController;

import java.util.Iterator;

import layers.ANTSLayerSystem.Layer;

public interface ANTSIModel
{
	///////////
	//Getters//
	///////////
	
	public Layer getLayer();
	public boolean isMouseListener();
	public boolean containsPoint(int x, int y);
	public Iterator<ANTSIMenuController> getMenuIterator();
	public boolean getMouseEntered();
	
	///////////
	//Setters//
	///////////
	
	public void setLayer(Layer layer);
	public void setDragged(boolean isDragged);
	public void setMouseEntered(boolean value);
	
	///////////
	//Special//
	///////////
	
	public void update();
}
