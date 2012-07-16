package interfaces;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import layers.ANTSLayerSystem;

import views.ANTSSimpleRayLightView;

public interface ANTSIController extends MouseListener, MouseMotionListener
{
	public ANTSIModel getModel();
	public ANTSIView getIView();
	
	public ANTSLayerSystem.Layer getLayer();
	public void setLayer(ANTSLayerSystem.Layer layer);
}
