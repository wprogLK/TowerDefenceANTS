/**
 * 
 */
package mvcFactories;

import controllers.basics.ANTSGameController;
import view.basics.ANTSGameView;
import interfaces.ANTSIModel;
import model.basics.ANTSGameModel;
import mvcFactories.abstracts.ANTSMVCFactoryMVCFactoryAbstract;

/**
 * @author lukas
 *
 */
public class ANTSGameMVCFactory extends ANTSMVCFactoryMVCFactoryAbstract {

	private ANTSGameModel model;
	private ANTSGameController controller;
	
	@Override
	protected void createMVC() 
	{
		this.model = new ANTSGameModel(); 
		this.view = new ANTSGameView(this.model);
	}

}
