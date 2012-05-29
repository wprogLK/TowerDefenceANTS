package interfaces;

import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public interface ANTSIView 
{
	public void paint(Graphics2D g2d);
	public void paint(Graphics2D g2d, float interpolation);
	public boolean isListenToMouse();
	//public boolean pointIsIn(double x, double y);
	public void showPopupMenu(Component component, int x, int y);
}
