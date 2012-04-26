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
		g.setColor(this.model.getSourceLightColor());
		
//		while(this.model.finish)
//		{
//			System.out.println("WARTE....");
//		}
		
		AffineTransform aTemp = this.model.calculateAffineTransform();
		//OLD
//		this.aTVelocity.translate(this.model.getVelocity(), 0); //Move ray
//		aT.concatenate(aTVelocity);
		
		
	//	Shape shape = aT.createTransformedShape(ray);
		Shape shape = aTemp.createTransformedShape(ray);
//		this.model.setPosX(aT.getTranslateX());
//		this.model.setPosY(aT.getTranslateY());
			
//	System.out.println("NAME: " + this.toString() + " getX " + this.model.getPosX()  + " getY " + this.model.getPosY());
//		
//		System.out.println("AT: X: " + aT.getTranslateX());
//		System.out.println("AT: Y: " + aT.getTranslateY());
		
		g.draw(shape);
	}
	
	public void refresh()
	{
		this.fireRefreshAction();
	}
	
	/////////////
	//LISTENERS//
	/////////////
	public void addButtonSwitchListener(ActionListener listener)
	{
		//this.button.addActionListener(listener);
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
		return "SimpleRayLight";
	}
	
//	public  boolean getDraw()
//	{
//		return this.draw;
//	}
//	
}
