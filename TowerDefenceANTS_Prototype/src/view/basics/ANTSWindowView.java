/**
 * 
 */
package view.basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaces.ANTSIController;
import interfaces.ANTSIView;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.abstracts.ANTSViewAbstact;

import model.basics.ANTSWindowModel;

import controllers.basics.ANTSGameController;


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
	private RefreshListener refreshListener;
	
	public ANTSWindowView(ANTSWindowModel model)
	{
		this.model = model;
		
		this.setupWindow();
		
		this.refreshListener = new RefreshListener();
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
		
		this.currentView.setRefreshListener(this.refreshListener);
		
		this.currentMainPanel = this.currentView.getPanel();
		this.antsWindow.setCurrentView(this.currentView);

		this.antsWindow.setContentPane(this.currentMainPanel);
		this.antsWindow.setSize(600, 800);
		//this.antsWindow.pack();
	}
	
	///////////////////////////
	//INNER CLASS: A LISTENER//	(only in this special view!)
	///////////////////////////
	
	class RefreshListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			antsWindow.repaint();
		}
		
	}
}
