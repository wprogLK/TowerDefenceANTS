package interfaces;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import views.ANTSSimpleRayLightView;

public interface ANTSIController extends MouseListener, MouseMotionListener
{
	public ANTSIModel getModel();
	public ANTSIView getIView();
	public boolean isListenToMouse();
}
