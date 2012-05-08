/**
 * 
 */
package view.basics;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import enums.ANTSStateEnum;

import listeners.actionListeners.paint.ANTSPaintActionListenerAbstract;
import model.basics.ANTSSimpleRayLightModel;



import view.abstracts.ANTSViewAbstact;

/**
 * @author Lukas
 *
 */
public class ANTSSimpleRayLightView extends ANTSViewAbstact 
{
	private ANTSSimpleRayLightModel model;
	private AffineTransform aT;
	private AffineTransform aTVelocity;
	private	Line2D.Double ray;
	
	public ANTSSimpleRayLightView(ANTSSimpleRayLightModel model)
	{
		super();
		this.currentPaintState = ANTSStateEnum.animate;
		this.model = model;
		//this.mainPanel.setSize(this.model.getLength(), this.model.getLength());
		this.mainPanel.setName("RAY PANLE");
		this.setupAffineTransform();
		
		this.setupLine();
	}
	
	public void setupAffineTransform()
	{
		this.aT = new AffineTransform();
		this.aTVelocity = new AffineTransform();

		this.aT.rotate(Math.toRadians(this.model.getAngle()), this.model.getPosX(), this.model.getPosY());
	}
	
	private void setupLine()
	{
		int length = this.model.getLength();
		double x = this.model.getSourcePosX();
		double y = this.model.getSourcePosY();
		
		this.ray = new Line2D.Double(x,y,x+length,y);
	}
	
	@Override
	protected void initComponents()
	{

	}
	
	@Override
	protected void configMainPanel()
	{
		
		this.mainPanel.setLayout(new FlowLayout());
	}
	
	@Override
	protected void paintView(Graphics2D g)
	{
		this.isFinish = false;
		
		g.setColor(this.model.getSourceLightColor());
		System.out.println("MY STATE: " + this.currentPaintState);
		switch(this.currentPaintState)
		{
			case basic:
			{
//				System.out.println("PAINT BASIC RAY LIGHT");
				AffineTransform aTemp = this.model.getCurrentAffineTransform();
				g.draw(ray);
				break;
			}
			case draw:
			{
//				System.out.println("PAINT DRAW RAY LIGHT");
				AffineTransform aTemp = this.model.getCurrentAffineTransform();
				Shape shape = aTemp.createTransformedShape(ray);
				
				g.draw(shape);
				break;
			}
			case animate:
			{
//				System.out.println("PAINT ANIMATE RAY LIGHT");
				AffineTransform aTemp = this.model.getNextAffineTransform();
				Shape shape = aTemp.createTransformedShape(ray);
				
				g.draw(shape);
				break;
			}
			default:
			{
//				System.out.println("DEFAULT");
			}
		}
		this.isFinish = true;
	}
	
	//TODO: change action
	public void refresh()
	{
		this.fireDefaultAction();
	}
	
	/////////////
	//LISTENERS//
	/////////////
	
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
		return "SimpleRayLight";
	}
	
//	public  boolean getDraw()
//	{
//		return this.draw;
//	}
//	
}
