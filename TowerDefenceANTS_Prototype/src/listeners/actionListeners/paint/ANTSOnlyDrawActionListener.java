package listeners.actionListeners.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ANTSStateEnum;

public class ANTSOnlyDrawActionListener extends ANTSPaintActionListenerAbstract implements ActionListener {

	private static ANTSPaintActionListenerAbstract listener;
	
	public static final ANTSPaintActionListenerAbstract getInstance()
	{
		if(listener == null)
		{
			listener = new ANTSOnlyDrawActionListener();
		}
		
		return listener;
	}
	
	@Override
	protected void setState() {
		this.window.setPaintState(ANTSStateEnum.draw);
		System.out.println("SOULD BE NOW DRAW");
//		super.currentState = ANTSStateEnum.draw;
	}

	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//	
//		
//		window.repaint();
//		
//	}

}
