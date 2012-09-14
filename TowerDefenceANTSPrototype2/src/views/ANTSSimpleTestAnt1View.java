package views;

import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import models.ANTSSimpleTestAnt1Model;

public class ANTSSimpleTestAnt1View extends ANTSAbstractView implements ANTSIView
{
	private ANTSSimpleTestAnt1Model model;
	
	//Animation
	private ArrayList<BufferedImage> animation;
	private int animationIndex;
	private int animationStep;		//ticks between image change
	private BufferedImage currentImage;
	private int currentStep;
	
	//Timer					//TODO: only for Debug
	private long startTime;
	private long endTime = 0;
	
	private Color transparentColor = new Color(255,0,255);
	
	public ANTSSimpleTestAnt1View(ANTSSimpleTestAnt1Model m) 
	{
		super(m);
		
		this.model = m;
		
		this.setupAnimation();
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
		//TODO shape
	}
	
	private void setupAnimation()
	{
		this.animation = new ArrayList<BufferedImage>();
		this.loadImage("img/testAnt/testAnt2T.png");
//		this.loadImage("img/testAnt/testAnt1.png");
//		this.loadImage("img/testAnt/testAnt2.png");
		
		this.animationIndex = 0;
		this.currentStep = 0;
		this.animationStep = 1000;	//TODO example
		
		this.startTime = System.currentTimeMillis();
	}
	
	private void loadImage(String fileWithPath)
	{
		BufferedImage img = null;
		try 
		{	
			img = ImageIO.read(new File(fileWithPath));
//			img = this.makeColorTransparent(fileWithPath, this.transparentColor);
		    this.animation.add(img);
		} 
		catch (IOException e)
		{

		}
	}
	
//	public  BufferedImage makeColorTransparent(String ref, Color color) 
//	{  
//        BufferedImage image = null;
//		try 
//		{
//			image = ImageIO.read(new File(ref));
//		} 
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
//        BufferedImage dimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);  
//        
//        Graphics2D g = dimg.createGraphics();  
//	    g.setComposite(AlphaComposite.Src);  
//	    g.drawImage(image, null, 0, 0);  
//	    g.dispose();  
//	    
//	    for(int i = 0; i < dimg.getHeight(); i++) 
//	    {  
//	        for(int j = 0; j < dimg.getWidth(); j++)
//	        {  
//	            if(dimg.getRGB(j, i) == color.getRGB())
//	            {  
//	            	dimg.setRGB(j, i, 0x8F1C1C);  
//	            }  
//	        }  
//	    }  
//	    return dimg;  
//	}  
	
	private BufferedImage getCurrentImage()
	{
		if(this.currentStep>=this.animationStep)
		{
			this.currentStep = 0;
			this.nextImage();
			
			this.endTime = System.currentTimeMillis();
			
			this.startTime = System.currentTimeMillis();
		}
		else
		{
			this.currentStep++;
		}
		
		return this.currentImage;
	}
	
	private BufferedImage nextImage()
	{	
		if(this.animation.isEmpty())
		{
			System.out.println("image: null! ArrayList is empty!");
			//TODO
			return null;
		}
		else if(this.animationIndex>=this.animation.size())
		{
			this.animationIndex = 0;
		}
		
		this.currentImage = this.animation.get(this.animationIndex);
		this.animationIndex++;
		return this.currentImage;
	}
	
	@Override
	public void paint(Graphics2D g2d) 
	{
		BufferedImage image = this.getCurrentImage();
		AffineTransform aT = null;
		if(image != null)
		{
			aT = this.model.getAffineTransformForImage(image.getWidth(), image.getHeight());
			g2d.drawImage(image, aT, null);
		}
		else
		{
//			System.out.println("Image IS NULL");
		}
	}
	
	@Override
	public void paint(Graphics2D g2d, float interpolation) 
	{
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
