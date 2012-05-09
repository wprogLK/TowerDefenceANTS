/**
 * 
 */
package view.basics;



import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

import model.basics.ANTSSimpleSourceLightModel;

import view.abstracts.ANTSViewAbstact;

/**
 * @author Lukas
 *
 */
public class ANTSSimpleSourceLightView extends ANTSViewAbstact 
{
	private JButton button;
	private ANTSSimpleSourceLightModel model;
	
	public ANTSSimpleSourceLightView(ANTSSimpleSourceLightModel model)
	{
		super();
		this.model = model;
		this.mainPanel.setSize(this.model.getRadius(), this.model.getRadius());
	}
	
	@Override
	protected void initComponents()
	{
		this.button = new JButton("Turn off/on");
	}
	
	@Override
	protected void configMainPanel()
	{
		this.mainPanel.setLayout(new FlowLayout());
		this.mainPanel.add(this.button);
	}
	
	@Override
	protected void paintView(Graphics2D g)
	{	
		this.isFinish = false;
		
		int radius = this.model.getRadius();
		int x = this.model.getPosX();
		int y = this.model.getPosY();
		
		Ellipse2D.Double bulb = new Ellipse2D.Double(x - (radius/2), y - (radius/2), radius, radius);

		if(this.model.isOn())
		{
			g.setColor(Color.YELLOW);
			g.fill(bulb);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.draw(bulb);
		}

//		if(this.model.canSendRay())
//		{
//			this.createRays(g);
//		}
		
		this.isFinish = true;
	}
	
//OLD
//	private void createRays(Graphics2D g)
//	{
//		
//		ArrayList<ANTSIView> rays = new ArrayList<ANTSIView>();
//		
//		double angle = this.model.getOffsetAngle();
//		
//		for(int numberRay = 0; numberRay<this.model.getNumberOfRays(); numberRay++)
//		{
//			//ANTSSimpleRayLightController simpleRayLightController = new ANTSSimpleRayLightController(this.model,angle);
//			ANTSSimpleRayLightController simpleRayLightController = new ANTSSimpleRayLightController(this.model,angle);
//			
//			angle+=this.model.getAngleBetweetTwoRays();
//			ANTSIView rayView = simpleRayLightController.getView();
//
//			rays.add(rayView);
//		}
//	
//		for(ANTSIView currentView: rays)
//		{
//			this.isFinish =false;
//			//this.addInternalView(currentView);
//			this.addViewToPainter(currentView);
////			System.out.println("ADD");
//		}
//	}
	
	//TODO: change action
	public void refresh()
	{
		this.fireDefaultAction();
	}
	
	/////////////
	//LISTENERS//
	/////////////
	public void addButtonSwitchListener(ActionListener listener)
	{
		this.button.addActionListener(listener);
	}
	
	///////////
	//ACTIONS//
	///////////
	
	public void setDraw(boolean drawState)
	{
		//this.draw = drawState;
	}
	
	///////////
	//GETTERS//
	///////////
	
	public String toString()
	{
		return "SimpleSourceLight";
	}
	
//	public  boolean getDraw()
//	{
//		return this.draw;
//	}
//	
	
}
