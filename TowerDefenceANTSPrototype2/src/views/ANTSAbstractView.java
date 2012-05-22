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

import javax.swing.JPanel;

public abstract class ANTSAbstractView extends  Container implements MouseListener, ANTSIView {
	
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
	public void mouseExited(MouseEvent e) {
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
	
	@Override
	public void paint(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics2D g2d, float interpolation) {
		// TODO Auto-generated method stub
		
	}
	
//	public boolean contains(int x, int y)
//	{
//		return this.
//	}
}
