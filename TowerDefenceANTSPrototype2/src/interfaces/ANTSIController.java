package interfaces;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import layers.ANTSLayerSystem;

public interface ANTSIController extends MouseListener, MouseMotionListener
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
