package basics;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import views.ANTSAbstractView;

public class ANTSCanvas extends Canvas implements MouseListener
{
	private ANTSWindow window;
	
	public ANTSCanvas(ANTSWindow w)
	{
		this.window = w;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked in ANTSCanvas at " + e.getX() + " | " + e.getY());
		ANTSAbstractView v = this.window.getViewAt(e.getX(), e.getY());
		System.out.println("View is: " + v);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

}
