package listeners.actionListeners.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

public class ANTSAnimateActionListener extends ANTSPaintActionListenerAbstract implements ActionListener {

	private static ANTSPaintActionListenerAbstract listener;
	
	public static final ANTSPaintActionListenerAbstract getInstance()
	{
		if(listener == null)
		{
			listener = new ANTSAnimateActionListener();
		}
		
		return listener;
	}
	
	@Override
	protected void setState() 
	{
		//ANTSStateEnum.setCurrentState(ANTSStateEnum.basic);
//		super.currentState = ANTSStateEnum.basic;
		this.window.setPaintState(ANTSStateEnum.animate);
		
	}
	

//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//	
//
//		try {
//			e.wait(100);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		window.repaint();
//		
//	}

}
