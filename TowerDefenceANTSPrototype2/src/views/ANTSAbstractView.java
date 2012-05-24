package views;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public abstract class ANTSAbstractView extends  Container implements MouseListener, MouseMotionListener, ANTSIView 
{
	private boolean listenToMouse;
	
	public ANTSAbstractView(boolean listenToMouse)
	{
		this.listenToMouse = listenToMouse;
	}
	
	public ANTSAbstractView()
	{
		this.listenToMouse = false;
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
		System.out.println("CLICK abstract" + e.getX() + " / " +e.getY());
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println("entered abstract");
		
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/////////////////////////
	//MOUSE MOTION LISTENER//
	/////////////////////////
	@Override
	public void mouseDragged(MouseEvent e) {
		
		
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
}
