package views;

import interfaces.ANTSIView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sun.awt.image.ToolkitImage;

import models.ANTSSimpleAntModel;
import models.ANTSSimpleSourceLightModel;

public class ANTSSimpleAntView extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleAntModel model;
	private Ellipse2D circle;
	private Image[] images;
	
	public ANTSSimpleAntView(ANTSSimpleAntModel m) 
	{
		super();
		
		this.popupMenu = new JPopupMenu("Simple popupMenu: Ant");
		this.popupMenu.add(new JMenuItem("A popup menu item"));
		
		this.model = m;
		
		this.initImages();
	}
	
	private void initImages()
	{
		
		Toolkit tk = getToolkit();
		Image image = tk.getImage("images/ameise1.gif");
		System.out.println("Ameise: " + image);
	}
	
	private void createCircle()
	{
		AffineTransform aT = this.model.getMatrix();
		double radius = this.model.getRadius();
		
		this.circle = new Ellipse2D.Double(aT.getTranslateX()-(radius/2), aT.getTranslateY()-(radius/2), radius, radius);
	}
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		this.createCircle();			//TODO CHECK IF position and radius has changed
		AffineTransform aT = this.model.getMatrix();
		
		Shape shape = aT.createTransformedShape(circle);
//		this.setBounds(shape.getBounds());

		if(this.model.isDragged())
		{
			//Only an example
			
		}
		
//		Toolkit tk = getToolkit();
//		Image image = tk.getImage("images/ameise1.gif");
//		Image i = transformGrayToTransparency(image);
//		g2d.drawImage(i, aT, null);
	}
	
//	 private Image transformGrayToTransparency(Image image)	//TODO TEST THIS!!
//	  {
//	    ImageFilter filter = new RGBImageFilter()
//	    {
//	      public final int filterRGB(int x, int y, int rgb)
//	      {
//	        return (rgb << 8) & 0xFF000000;
//	      }
//	    };
//
//	    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
//	    return Toolkit.getDefaultToolkit().createImage(ip);
//	  }

	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
		//interpolation not needed for this view!
		this.paint(g2d);
	}
	
	public String toString()
	{
		return "SOURCE Ant " + this.model.getPosX() + " " + this.model.getPosY() + " " + this.model.getColor();
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
}
