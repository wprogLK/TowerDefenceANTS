package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.basics.ANTSWindow;

//DONT USE THIS INTERFACE!
public interface ANTSIDrawActionListener extends ActionListener{

	public void  setWindow(ANTSWindow window);
	@Override
	public  void actionPerformed(ActionEvent e);
}
