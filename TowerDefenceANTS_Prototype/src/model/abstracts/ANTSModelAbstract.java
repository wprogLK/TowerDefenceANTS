package model.abstracts;

import helper.ANTSGameLogicUpdater;
import interfaces.ANTSIModel;

public abstract class ANTSModelAbstract implements ANTSIModel
{
	public ANTSModelAbstract()
	{
		ANTSGameLogicUpdater.addModel(this);
	}
}
