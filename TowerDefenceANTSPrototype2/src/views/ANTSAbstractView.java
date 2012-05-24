package views;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public abstract class ANTSAbstractView extends  Container implements MouseListener, MouseMotionListener, ANTSIView 
{
	protected JPopupMenu popupMenu;
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	private boolean listenToMouse;
	
	///////////////
	//DRAG & DROP//
	///////////////
	
	protected boolean isDragged;	//For example to change the graphic if it is dragged
	
	public ANTSAbstractView(boolean listenToMouse)
	{
		this.basicInit();
		
		this.listenToMouse = listenToMouse;
	}
	
	public ANTSAbstractView()
	{
		this.basicInit();
		
		this.listenToMouse = false;
	}
	
	
	private void basicInit()
	{
		this.isDragged = false;
		this.popupMenu = new JPopupMenu("Simple popupMenu: ABSTRACT");
		this.popupMenu.add(new JMenuItem("A popup menu item abstract"));
		
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);	//shows the popupmenu in the foreground of the canvas!
	}
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		// TODO Auto-generated method stub
	}
	
	//////////////////
	//MOUSE LISTENER//
	//////////////////
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{

	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		this.isDragged = false;	
	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		this.isDragged = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public boolean isListenToMouse()
	{
		return this.listenToMouse;
	}
	
	public JPopupMenu getPopupMenu()
	{
		return this.popupMenu;
	}
}
