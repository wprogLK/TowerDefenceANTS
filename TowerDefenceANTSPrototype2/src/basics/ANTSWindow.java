package basics;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.util.localization.NullLocalizable;

public class ANTSWindow extends JFrame{

	private ANTSDriver driver;
	private ArrayList<JPanel> panels;
	
	public ANTSWindow(ANTSDriver d) 
	{
		this.driver = d;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.setLayout(new FlowLayout());
		this.panels = new ArrayList<JPanel>();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		for(JPanel cp:this.panels)
		{
			cp.paint(g);
		}
	}
	
	public void addView(JPanel p)
	{
		this.panels.add(p);
	}

}
