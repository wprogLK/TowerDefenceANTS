package interfaces;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import layers.ANTSLayerSystem;

public interface ANTSIController extends MouseListener, MouseMotionListener, MouseWheelListener
{
	///////////
	//Getters//
	///////////
	
	public ANTSIModel getModel();
	public ANTSIView getIView();
	
	public ANTSLayerSystem.Layer getLayer();
	
	public double getPosX();
	public double getPosY();
	
	///////////
	//Setters//
	///////////
	
	public void setLayer(ANTSLayerSystem.Layer layer);
	
	///////////
	//Special//
	///////////
	
	public void update();
}
