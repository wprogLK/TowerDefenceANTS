package interfaces.menus;

import interfaces.ANTSIView;

public interface ANTSIMenuController
{
	///////////
	//Getters//
	///////////
	
	public ANTSIMenuModel getModel();
	public ANTSIView getIView();
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	
	public void addNewMenuItem(String string); //TODO ANTSICommand
}
