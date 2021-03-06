/**
 * 
 */
package interfaces;

import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import enums.ANTSStateEnum;

import view.abstracts.ANTSViewAbstact;

/**
 * @author Lukas
 *
 */
public interface ANTSIView
{
	public void paint(Graphics2D g);
	
	public JPanel getPanel();
	public boolean isPanelEmpty();
	public  boolean isFinish();
	public ANTSViewAbstact getAbstract();
	
	public void setPaintState(ANTSStateEnum state);
}
