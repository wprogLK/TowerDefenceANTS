package interfaces;

import java.awt.Graphics2D;
import java.awt.Shape;

public interface ANTSIView 
{
	
	///////////
	//Getters//
	///////////
	public boolean isMouseListener();
	
	/**
	 * if a view should not painted by calling from the Driver. Example ANTSCells.
	 * @return
	 */
	
	public boolean doPaintDirect();
	public boolean containsPoint(int x, int y);
	public boolean isMouseOver();
	public Shape getShape();
	
	///////////
	//Special//
	///////////
	
	public void paint(Graphics2D g2d);
	public void paint(Graphics2D g2d, float interpolation);

	public boolean doesCollideWith(Shape shape);


}
