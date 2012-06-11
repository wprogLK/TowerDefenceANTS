package views;

import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import models.ANTSSimpleSourceLightModel;
import models.ANTSSimpleTestAnt1Model;

public class ANTSSimpleTestAnt1View extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleTestAnt1Model model;
	
	//Animation
	private ArrayList<BufferedImage> animation;
	private int animationIndex;
	private int animationStep;
	
	public ANTSSimpleTestAnt1View(ANTSSimpleTestAnt1Model m) 
	{
		super();
		this.popupMenu = new JPopupMenu("Simple popupMenu: Test ant");
		this.popupMenu.add(new JMenuItem("A popup menu item of Ant"));
		
		this.model = m;
		
		this.setupAnimation();
	}
	
	private void setupAnimation()
	{
		this.animation = new ArrayList<BufferedImage>();
		this.loadImage("img/testAnt/testAnt1.jpg");
		this.loadImage("img/testAnt/testAnt2.jpg");
		
		this.animationIndex = 0;
		this.animationStep = 6;	//TODO example
	}
	
	private void loadImage(String fileWithPath)
	{
		BufferedImage img = null;
		try 
		{	
		    img = ImageIO.read(new File(fileWithPath));
		    this.animation.add(img);
		} 
		catch (IOException e)
		{
			//TODO
		}
	}
	
	
	private BufferedImage getCurrentImage()
	{
		if(this.animation.isEmpty())
		{
			System.out.println("image: null! ArrayList is empty!");
			//TODO
			return null;
		}
		else if(!(this.animationIndex>0 && (this.animationIndex % this.animationStep)<this.animation.size()))
		{
			this.animationIndex=0;
		}
		System.out.println("Animation: index " + this.animationIndex + " % animationStep " + this.animationStep + " = " +  (this.animationIndex % this.animationStep)  );
		BufferedImage image = this.animation.get(this.animationIndex % this.animationStep);
		this.animationIndex++;
		
		return image;
	}
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		AffineTransform aT = this.model.getMatrix();
		
		//this.setBounds();	//TODO

//		if(this.model.isDragged())
//		{
//			//Only an example
//			
//			g2d.setColor(Color.GRAY);
//
//			float[] dash_array=new float[4];
//			dash_array[0]=5; //sichtbar
//			dash_array[1]=20; //unsichtbar
//			
//			BasicStroke dashStroke = new BasicStroke( 
//				5f, //Breite
//				BasicStroke.CAP_SQUARE, //End Style
//				BasicStroke.JOIN_ROUND, //Join Style
//				1f, //Limit für Join
//				dash_array, //Strichelung
//				0 //offset in Pixeln f. Strichelung
//				);
//			g2d.setStroke(dashStroke);
//			g2d.draw(shape.getBounds2D());
//			
//			g2d.setStroke(new BasicStroke());
//		}
//		
//		
//		if(this.model.isOn())
//		{
//			g2d.setColor(this.model.getColor());
//			g2d.fill(shape);
//		}
//		else
//		{
//			g2d.setColor(Color.black);
//			g2d.draw(shape);
//		}
		Rectangle2D r = new Rectangle2D.Double(aT.getTranslateX(), aT.getTranslateY(),60, 60);
		g2d.draw(r);
		g2d.drawImage(this.getCurrentImage(), aT, null);
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		//interpolation not needed for this view!
		this.paint(g2d);
	}
	
	public String toString()
	{
		return "ANT VIEW " + this.model.getPosX() + " " + this.model.getPosY() + " " + this.model.getColor();
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
}
