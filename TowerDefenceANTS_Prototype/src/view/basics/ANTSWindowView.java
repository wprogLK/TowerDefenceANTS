/**
 * 
 */
package view.basics;

import interfaces.ANTSIView;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.abstracts.ANTSViewAbstact;

import listeners.actionListeners.paint.ANTSPaintActionListenerAbstract;
import model.basics.ANTSWindowModel;

/**
 * This view is not a normal view. It's the "top view"
 * @author Lukas
 *
 */
public class ANTSWindowView extends ANTSViewAbstact
{
	private ANTSWindowModel model;
	
	private ANTSIView currentView;
	private ANTSWindow antsWindow;
	private JPanel currentMainPanel;

	
	public ANTSWindowView(ANTSWindowModel model)
	{
		this.model = model;
		
		this.setupWindow();
		
		ANTSPaintActionListenerAbstract.setWindow(antsWindow);	//Set the window everywhere
	}
	
	private void setupWindow()
	{
		this.antsWindow = new ANTSWindow();
		this.antsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public void showWindow()
	{
		this.antsWindow.setVisible(true);
	}
	
	public void hideWindow()
	{
		this.antsWindow.setVisible(false);
	}
	
	///////////
	//GETTERS//
	///////////

	
	///////////
	//SETTERS//
	///////////
	
	
	/////////////
	//LISTENERS//
	/////////////
	
	
	///////////
	//ACTIONS//
	///////////
	
	public void setCurrentView(ANTSIView view)
	{
		this.currentView = view;
		
		this.currentMainPanel = this.currentView.getPanel();
		this.antsWindow.setCurrentView(this.currentView);

		this.antsWindow.setContentPane(this.currentMainPanel);
		this.antsWindow.setSize(600, 800);
	}
}
