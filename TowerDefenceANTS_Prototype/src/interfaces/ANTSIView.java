/**
 * 
 */
package interfaces;

import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * @author Lukas
 *
 */
public interface ANTSIView 
{
	public void paint(Graphics2D g);
	
	public JPanel getPanel();

	void setRefreshListener(ActionListener refreshListener);
}
