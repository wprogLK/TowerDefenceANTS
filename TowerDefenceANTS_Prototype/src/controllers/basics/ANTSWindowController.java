/**
 * 
 */
package controllers.basics;

import model.basics.ANTSWindowModel;
import view.basics.ANTSWindowView;
import interfaces.ANTSIController;
import interfaces.ANTSIView;

/**
 * @author lukas
 *
 */
public class ANTSWindowController implements ANTSIController
{
	private ANTSWindowView view;
	private ANTSWindowModel model;
	
	public ANTSWindowController()
	{
		this.model = new ANTSWindowModel();
		this.view = new ANTSWindowView(this.model);
		
		this.setupListeners();
	}
	
	private void setupListeners()
	{

	}
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public ANTSIView getView() 
	{
		return this.view;
	}
	
	///////////
	//ACTIONS//	(only in this special controller!)
	///////////
	
	public void setupView(ANTSIView startView)
	{
		this.view.setCurrentView(startView);
	}
	
	public void showWindow()
	{
		this.view.showWindow();
	}
	
	public void hideWindow()
	{
		this.view.hideWindow();
	}
	
	////////////////////////////////
	//INNER CLASSES: THE LISTENERS//
	////////////////////////////////
	
//	class TestListener implements ActionListener
//	{
//
//		@Override
//		public void actionPerformed(ActionEvent e) 
//		{
//
//			boolean draw = view.getDraw();
//			draw = model.setNewDraw(draw);
//			view.setDraw(draw);
//			view.refresh();
//		}
//		
//	}
}
