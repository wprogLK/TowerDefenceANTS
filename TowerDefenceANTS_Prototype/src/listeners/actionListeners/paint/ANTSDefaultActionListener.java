package listeners.actionListeners.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

public class ANTSDefaultActionListener extends ANTSPaintActionListenerAbstract implements ActionListener {

	private static ANTSPaintActionListenerAbstract listener;
	
	public static final ANTSPaintActionListenerAbstract getInstance()
	{
		if(listener == null)
		{
			listener = new ANTSDefaultActionListener();
		}
		
		return listener;
	}
	
	@Override
	protected void setState() 
	{
		//ANTSStateEnum.setCurrentState(ANTSStateEnum.basic);
//		super.currentState = ANTSStateEnum.basic;
		this.window.setPaintState(ANTSStateEnum.basic);
		System.out.println("SOULD BE NOW BASIC");
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