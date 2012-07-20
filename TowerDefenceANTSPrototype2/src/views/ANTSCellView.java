package views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import interfaces.ANTSIView;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import basics.ANTSDriver;

import models.ANTSCellModel;

public class ANTSCellView extends ANTSAbstractView implements ANTSIView
{
	private ANTSCellModel model;
	
	public ANTSCellView(ANTSCellModel m) 
	{
		super();
		
		this.popupMenu = new JPopupMenu("Cell popupMenu: Cell");
		this.popupMenu.add(new JMenuItem("A popup menu item Cell"));
		
		this.model = m;
		
		ANTSDriver.addToCanvas(this);
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
		
		Rectangle2D rec = new Rectangle2D.Double(0, 0, model.getBoxWidth(), model.getBoxHeight());
		AffineTransform aT = this.model.getMatrix();
		Shape shape = aT.createTransformedShape(rec);
		this.setBounds(shape.getBounds());
		g2d.setColor(Color.BLUE);
		
//		g2d.draw(shape);
		
		
		Line2D lineA = new Line2D.Double(0, 0, this.model.getLineLength(), 0);
		Line2D lineB = new Line2D.Double(0, 0, this.model.getLineLength(), 0);
		Line2D lineC = new Line2D.Double(0, 0, this.model.getLineLength(), 0);
		Line2D lineD = new Line2D.Double(0, 0, this.model.getLineLength(), 0);
		
		AffineTransform aTA = this.model.getMatrixForLineA();
		AffineTransform aTB = this.model.getMatrixForLineB();
		AffineTransform aTC = this.model.getMatrixForLineC();
		AffineTransform aTD = this.model.getMatrixForLineD();
		
		Shape shapeA = aTA.createTransformedShape(lineA);
		Shape shapeB = aTB.createTransformedShape(lineB);
		Shape shapeC = aTC.createTransformedShape(lineC);
		Shape shapeD = aTD.createTransformedShape(lineD);
		
		g2d.setColor(Color.black);
		
		g2d.draw(shapeA);
		g2d.draw(shapeB);
		g2d.draw(shapeC);
		g2d.draw(shapeD);
		
//		AffineTransform aT = this.model.getMatrix();	//for debugging
		
		g2d.drawString(this.model.toString() ,(int) aT.getTranslateX()+10,(int) aT.getTranslateY() + 35);//for debugging
	}	
}
