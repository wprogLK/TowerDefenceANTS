package controllers.basics;

import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import helper.ANTSGameLogicUpdater;
import helper.ANTSPainter;

public class ANTSControllerAbstract {
	public ANTSControllerAbstract(ANTSIView view, ANTSIModel model)
	{
		ANTSPainter.addView(view);
		ANTSGameLogicUpdater.addModel(model);
	}
}
