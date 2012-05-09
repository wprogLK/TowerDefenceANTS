/**
 * 
 */
package view.basics;


import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import enums.ANTSStateEnum;

import model.basics.ANTSSimpleRayLightModel;

import view.abstracts.ANTSViewAbstact;

/**
 * @author Lukas
 *
 */
public class ANTSSimpleRayLightView extends ANTSViewAbstact 
{
	private ANTSSimpleRayLightModel model;
	private	Line2D.Double ray;
	
	public ANTSSimpleRayLightView(ANTSSimpleRayLightModel model)
	{
		super();
		this.currentPaintState = ANTSStateEnum.animate;
		this.model = model;
		this.mainPanel.setName("RAY PANLE");
		
		this.setupLine();
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
	
		AffineTransform aTemp = this.model.getAffineTransform();
		Shape shape = aTemp.createTransformedShape(ray);
		
		g.draw(shape);
		
		this.isFinish = true;
	}
	
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

	}
	
	///////////
	//GETTERS//
	///////////
	
	public String toString()
	{
		return "SimpleRayLight";
	}
}
