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
	
	public ANTSGameController(ANTSGameView view, ANTSGameModel model)
	{
		this.view = view;
		this.model = model;
		
		this.setupListeners();
	}
	
	public ANTSGameController()
	{
		this.model = new ANTSGameModel();
		this.view = new ANTSGameView(this.model);
		
		this.setupListeners();
	}

	private void setupListeners()
	{
		view.addButtonTestListener(new TestListener());
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
