/**
 * 
 */
package controllers.basics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		this.setupListeners();
		this.createBulb();
	}

	private void setupListeners()
	{
		view.addButtonTestListener(new TestListener());
	}
	
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
	
	class TestListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			boolean draw = view.getDraw();
			draw = model.setNewDraw(draw);
			
			view.setDraw(draw);
			view.refresh();
		}
		
	}
}