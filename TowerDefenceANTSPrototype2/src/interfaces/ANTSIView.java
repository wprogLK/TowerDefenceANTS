package interfaces;

import java.awt.Graphics2D;
import javax.swing.JPanel;

public interface ANTSIView 
{
	public void paint(Graphics2D g2d);
	public void paint(Graphics2D g2d, float interpolation);
	public boolean pointIsIn(double x, double y);
}
