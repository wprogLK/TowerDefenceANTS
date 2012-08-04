package interfaces.menus;

import interfaces.ANTSIView;

public interface ANTSIMenuController 
{
	public ANTSIMenuModel getModel();

	public ANTSIView getIView();
	public void addNewMenuItem(String string); //TODO ANTSICommand
}
