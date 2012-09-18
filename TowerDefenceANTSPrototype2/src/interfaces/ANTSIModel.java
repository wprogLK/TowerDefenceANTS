package interfaces;

import interfaces.menus.ANTSIMenuController;

import java.awt.geom.AffineTransform;
import java.util.Iterator;

import controllers.ANTSDevelopmentController;

import basics.ANTSFactory;

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
	public ANTSFactory getFactory();
	
	boolean isCollisionDetected();
	public boolean isDragged();
	public boolean isAlreadyUpdated();
	
	public AffineTransform getMatrix();
	
	public ANTSDevelopmentController getDevelopmentController();
	public double[] getCenter();
	///////////
	//Setters//
	///////////
	
	public void setLayer(Layer layer);
	public void setDragged(boolean isDragged);
	public void setMouseEntered(boolean value);
	public void setIsAlreadyUpdated(boolean u);
	
	///////////
	//Special//
	///////////
	
	public void update();
	
}
