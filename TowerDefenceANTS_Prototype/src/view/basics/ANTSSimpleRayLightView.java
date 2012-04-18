/**
 * 
 */
package view.basics;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.Vector;

import javax.swing.JButton;

import model.basics.ANTSGameModel;
import model.basics.ANTSSimpleRayLightModel;
import model.basics.ANTSSimpleSourceLightModel;

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
	
	public ANTSSimpleRayLightView(ANTSSimpleRayLightModel model)
	{
		super();
		this.model = model;
		//this.mainPanel.setSize(this.model.getLength(), this.model.getLength());
		this.mainPanel.setName("RAY PANLE");
		this.setupAffineTransform();
	}
	
	public void setupAffineTransform()
	{
		this.aT = new AffineTransform();
		this.aTVelocity = new AffineTransform();
		//this.aT.translate(this.model.getSourcePosX(), this.model.getSourcePosX());
		//this.aT.translate(300,300);
	
		//this.aT.rotate(Math.toRadians(this.model.getAngle()), this.model.getSourcePosX(), this.model.getSourcePosY());
		this.aT.rotate(Math.toRadians(this.model.getAngle()), this.model.getPosX(), this.model.getPosY());

	}
	
	@Override
	protected void initComponents()
	{
		//this.button = new JButton("Turn off/on");
	}
	
	@Override
	protected void configMainPanel()
	{
		
		this.mainPanel.setLayout(new FlowLayout());
	//	this.mainPanel.add(this.button);
	}
	
	@Override
	protected void paintView(Graphics2D g)
	{

		int length = this.model.getLength();
		double x = this.model.getPosX();
		double y = this.model.getPosY();
		
		System.out.println("NAME: " + this.toString() + " getX " + this.model.getPosX()  + " getY " + this.model.getPosY());
		
		//Ellipse2D.Double bulb = new Ellipse2D.Double(x - (radius/2), y - (radius/2), radius, radius);
		Line2D.Double ray = new Line2D.Double(x,y,x+length,y);
//		if(this.model.isOn())
//		{
//			//Line2D.Double line = new Line2D.Double(30,0,200,200);	//Example
//			g.setColor(Color.YELLOW);
//			g.fill(bulb);
//		}
//		else
//		{
//			g.setColor(Color.BLACK);
//			g.draw(bulb);
//		}
		//ray.
//		aT.createTransformedShape(ray);
		g.setColor(this.model.getSourceLightColor());
//		g.draw(ray);
		
		this.aTVelocity.translate(this.model.getVelocity(), 0); //Move ray
		aT.concatenate(aTVelocity);
		
		
		System.out.println("AT: X: " + aT.getTranslateX());
		System.out.println("AT: Y: " + aT.getTranslateY());
		
		//TODO
		g.transform(aT);
		
		Point2D pos = this.model.getPos();
		
		aTVelocity.transform(pos, pos);
		
		g.draw(ray);
		
		//Reset g
		try {
			AffineTransform aT_inv=aT.createInverse();
			g.transform(aT_inv);
			
			
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
