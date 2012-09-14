package views.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuView;

import views.ANTSAbstractView;

import models.menus.ANTSMenuItemRectangleModel;
import models.menus.ANTSRectangleMenuModel;

public class ANTSMenuItemRectangleView extends ANTSAbstractView implements ANTSIView, ANTSIMenuView
{
	private ANTSRectangleMenuModel parentModel;
	private ANTSMenuItemRectangleModel model;
	
	private static Font font = new Font("Courier New", Font.PLAIN, fontSize );
	private static Color fontColor = Color.BLACK;
	
	public ANTSMenuItemRectangleView(ANTSMenuItemRectangleModel model, ANTSRectangleMenuModel parentModel) 
	{
		super(model);
		
		this.model = model;
		this.parentModel = parentModel;
	}
		
	///////////
	//Getters//
	///////////
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	

	@Override
	protected void updateShape(float interpolation) 
	{
		AffineTransform aT = this.model.getMatrix();
		
		Rectangle2D.Double rec = new Rectangle2D.Double(0,0,this.parentModel.getMinWidth(),fontSize);
		
		AffineTransform t = new AffineTransform(aT);
		
		double posX = 0;
		double posY = this.model.getIndex()*fontSize ;
		
		t.translate(posX, posY-fontSize);
		
		this.shape = t.createTransformedShape(rec);
	}	
	
	public String toString()
	{
		return "Cell view";
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	@Override
	public void paint(Graphics2D g2d)
	{
		this.paint(g2d, 0);
	}
	
	@Override
	public boolean doPaintDirect()
	{
		return false;
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation)
	{
		AffineTransform aT = this.parentModel.getMatrix();
		
		g2d.setColor(Color.black);
		
		if(this.model.getMouseEntered())
		{
			g2d.setColor(Color.blue);
		}
		else
		{
			g2d.setColor(Color.red);
		}
		
		this.updateShape(interpolation);
		
		AffineTransform t = new AffineTransform(aT);
		
		double posX = 0;
		double posY = this.model.getIndex()*fontSize ;

		t.translate(posX, posY-fontSize);

		g2d.fill(shape);
		
		g2d.setColor(fontColor);
		g2d.setFont(font);
		
		g2d.drawString(this.model.getText(), (int) aT.getTranslateX(), (int)(t.getTranslateY()+fontSize));
	}
}
