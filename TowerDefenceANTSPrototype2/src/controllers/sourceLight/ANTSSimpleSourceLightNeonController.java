package controllers.sourceLight;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import controllers.ANTSAbstractController;

import basics.ANTSFactory;
import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIModel;
import interfaces.ANTSISourceLightController;
import interfaces.ANTSIView;

import views.sourceLight.ANTSSimpleSourceLightNeonView;

import models.sourceLight.ANTSSimpleSourceLightNeonModel;

public class ANTSSimpleSourceLightNeonController extends ANTSAbstractController implements ANTSISourceLightController
{
	private ANTSSimpleSourceLightNeonModel model;
	private ANTSSimpleSourceLightNeonView view;
	
	public ANTSSimpleSourceLightNeonController(double posX, double posY, double length, Color color, boolean isMouseListener, ANTSFactory factory)
	{
		this.model = new ANTSSimpleSourceLightNeonModel(posX,posY,length,color,isMouseListener, factory);
		this.view = new ANTSSimpleSourceLightNeonView(this.model);
		
		this.iview = view;
		this.setIModel(this.model);
	}
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSSimpleSourceLightNeonView getView()
	{
		return this.view;
	}

	@Override
	public ANTSIView getIView()
	{
		return this.view;
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	@Override
	public void mouseLeftClickedANTS(MouseEvent e) 
	{
		this.model.switchLight();
	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	
	@Override
	public void mouseDraggedANTS(MouseEvent e) 
	{
		this.model.setPosition((int) (e.getX()-this.model.getLength()/2), (int) (e.getY()-this.model.getLength()/4));
	}
	
	@Override
	public void mouseWheelMovedANTS(MouseWheelEvent e)
	{
		this.model.rotate(e.getWheelRotation());
	}
	
	////////////////
	//KEY LISTENER//
	////////////////
	
	@Override
	public void keyReleased(KeyEvent e) 
	{
		char c = Character.toLowerCase(e.getKeyChar());
		
		double posX = this.model.getPosX();
		double posY = this.model.getPosY();
		
		double deltaX = 1;
		double deltaY = 0.2;
		
		if(e.getKeyCode()==KeyEvent.VK_UP)	//For debugging
		{
			double y = posY-deltaY;
//			this.mouseDraggedANTS(new MouseEvent(null, 0, 0, 0, (int) posX, (int) y, 0, 0, 0, false, 0));
			this.model.setPosition(posX, y);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN) 	//For debugging
		{
			double y = posY+deltaY;
			
			this.model.setPosition(posX, y);
			
//			this.mouseDraggedANTS(new MouseEvent(, 0, 0, 0, (int) posX, (int) y, 0, 0, 0, false, 0));
		}
	}
}
