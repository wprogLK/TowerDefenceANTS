/**
 * 
 */
package controllers.basics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.basics.ANTSGameModel;
import model.basics.ANTSSimpleSourceLightModel;
import view.basics.ANTSGameView;
import view.basics.ANTSSimpleSourceLightView;
import interfaces.ANTSIController;
import interfaces.ANTSIView;

/**
 * @author lukas
 *
 */
public class ANTSSimpleSourceLightController implements ANTSIController
{
	private ANTSSimpleSourceLightView view;
	private ANTSSimpleSourceLightModel model;
	
	
	public ANTSSimpleSourceLightController()
	{
		this.model = new ANTSSimpleSourceLightModel();
		this.view = new ANTSSimpleSourceLightView(this.model);
		
		this.setupListeners();
	}

	private void setupListeners()
	{
		//view.addButtonTestListener(new TestListener());
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
