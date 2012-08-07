package views.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;

import basics.ANTSDevelopment.ANTSStream;

import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuView;

import views.ANTSAbstractView;

import models.menus.ANTSCircleMenuModel;
import models.menus.ANTSMenuItemCircleModel;
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
		super();
		
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
		
		int maxNumberOfItems = this.parentModel.getMaxIndexMenuItem();
		
		Rectangle2D.Double rec = new Rectangle2D.Double(0,0,this.parentModel.getMinWidth(),this.fontSize);
		
		AffineTransform t = new AffineTransform(aT);
		
		double posX = 0;
		double posY = this.model.getIndex()*this.fontSize ;
		
		t.translate(posX, posY-this.fontSize);
		
		
		this.shape = t.createTransformedShape(rec);
		g2d.fill(shape);
		
		g2d.setColor(this.fontColor);
		g2d.setFont(this.font);
		
		g2d.drawString(this.model.getText(), (int) aT.getTranslateX(), (int)(t.getTranslateY()+this.fontSize));
	}
	
}
