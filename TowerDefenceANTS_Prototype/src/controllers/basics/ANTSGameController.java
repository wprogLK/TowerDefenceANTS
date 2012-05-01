/**
 * 
 */
package controllers.basics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import listeners.actionListeners.paint.ANTSOnlyDrawActionListener;
import model.basics.ANTSGameModel;
import view.basics.ANTSGameView;
import interfaces.ANTSIController;
import interfaces.ANTSIView;

/**
 * @author lukas
 *
 */
public class ANTSGameController implements ANTSIController
{
	private ANTSGameView view;
	private ANTSGameModel model;
	
	public ANTSGameController()
	{
		this.model = new ANTSGameModel();
		this.view = new ANTSGameView(this.model);
		
		//this.setupListeners(); No longer needed
		this.createBulb();
	}

//	No longer needed
//	private void setupListeners()
//	{
//		//view.addButtonTestListener(new TestListener()); 
//		view.addButtonTestListener(ANTSOnlyDrawActionListener.getInstance());
//	}
	
	private void createBulb()
	{
		ANTSSimpleSourceLightController simpleSourceController = new ANTSSimpleSourceLightController();
		ANTSIView simpleSourceView = simpleSourceController.getView();
		this.view.addInternalView(simpleSourceView);
	}
	///////////
	//GETTERS//
	///////////
	
	@Override
	public ANTSIView getView() 
	{
		return this.view;
	}
	
	////////////////////////////////
	//INNER CLASSES: THE LISTENERS// 
	////////////////////////////////
	
// No longer needed
//	class TestListener implements ActionListener
//	{
//		@Override
//		public void actionPerformed(ActionEvent e) 
//		{
//			boolean draw = view.getDraw();
//			draw = model.setNewDraw(draw);
//			
//			view.setDraw(draw);
//			view.refresh();
//		}
//		
//	}
}
