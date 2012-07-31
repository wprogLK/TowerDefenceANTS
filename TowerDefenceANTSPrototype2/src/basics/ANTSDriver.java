/**
 * 
 */
package basics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import views.ANTSAbstractView;

import listeners.ANTSSwitchLightListener;
import listeners.ANTSUpdateListener;
import models.ANTSSimpleSourceLightModel;

import interfaces.ANTSIController;
import interfaces.ANTSIDriver;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import controllers.ANTSAbstractController;
import controllers.ANTSCellController;
import controllers.ANTSGameController;
import controllers.ANTSGridController;
import controllers.ANTSSimpleRayLightController;
import controllers.ANTSSimpleSourceLightController;
import controllers.ANTSSimpleTestAnt1Controller;

/**
 * @author Lukas
 *
 */
public class ANTSDriver extends Thread implements ANTSIDriver
{
	private ANTSWindow window;
	
	private ANTSFactory factory;
	
	private Canvas canvas;
	
	private Color backgroundColor = Color.white;
	
	private final int TICKS_PER_SECOND = 25;
	private final int SKIP_TICKS = 1000/TICKS_PER_SECOND;
	private final int MAX_FRAMESKIP = 5;
	
	private boolean debugModeOn = true;
	private FPS fps;
	
	//Active Rendering:
	private Graphics graphics;
	private Graphics2D g2d;
	private BufferedImage bi;
	private BufferStrategy buffer;
	private GraphicsConfiguration gC;
	
	
	
	public ANTSDriver()
	{	
		window = new ANTSWindow();
		window.setVisible(true);
		
		this.canvas = new Canvas();
		
		this.initAllListeners();
		
		this.factory = new ANTSFactory(this);
		
		this.createGame();
		
		this.initActiveRendering();
	}
	
	private void initActiveRendering()
	{
		window.setIgnoreRepaint(true);
		
		//Canvas for painting
		canvas.setIgnoreRepaint(true);
		
		window.addCanvas(this.canvas);
		
		//BackBuffer
		canvas.createBufferStrategy(2);
		this.buffer = this.canvas.getBufferStrategy();
		
		//graphics config
		GraphicsEnvironment gE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gD = gE.getDefaultScreenDevice();
		this.gC = gD.getDefaultConfiguration();
		
		//Create off-screen drawing surface
		this.bi = this.gC.createCompatibleImage(this.window.getWidthOfGraphics(),this.window.getHeightOfGraphics());
	}
	
	private void initAllListeners() 
	{
		ANTSUpdateListener.setDriver(this);
	}

	public void addControllerToMouseListener(ANTSIController c)
	{
		this.canvas.addMouseListener(c);
		this.canvas.addMouseMotionListener(c);
	}
	
	
	//Create new objects
	
	private void createGame()
	{		
		this.factory.createGame();
	}
	
	@Override
	public void run()
	{
		long nextGameTick = System.currentTimeMillis();
		int loops;
		float interpolation;
		
		boolean gameIsRunning = true;
		
		if(this.debugModeOn)
		{
			this.fps = new FPS();
		}
		
		while(gameIsRunning)
		{
			loops = 0;
			while(System.currentTimeMillis() >nextGameTick && loops<MAX_FRAMESKIP)
			{
				this.factory.updateAllModels();
				nextGameTick +=SKIP_TICKS;
				loops++;
			}
			
			interpolation =(System.currentTimeMillis() + SKIP_TICKS - nextGameTick) /(SKIP_TICKS); //TODO Float
			this.paint(interpolation);
		}
	}
	
	private void paint(float interpolation)
	{
		
		if(this.debugModeOn)
		{
			this.fps.update();
		}
		this.bi = this.gC.createCompatibleImage(this.window.getWidthOfGraphics(),this.window.getHeightOfGraphics());
		this.g2d = bi.createGraphics();
		
		//Clear:
		g2d.setColor(this.backgroundColor); 
		this.g2d.fillRect(0, 0, this.window.getWidthOfGraphics(),this.window.getHeightOfGraphics());
		
		this.factory.paintAllViews(g2d, interpolation);

		//Show fps
		this.g2d.setFont( new Font( "Courier New", Font.PLAIN, 12 ) );
		this.g2d.setColor( Color.BLACK );
	    this.g2d.drawString( String.format( "FPS: %s", this.fps.getFPS() ), 20, 20 );
		
		//Blit image and flip
		this.graphics = this.buffer.getDrawGraphics();
		graphics.drawImage(this.bi, 0, 0, null);
		
		if(!buffer.contentsLost())
		{
			buffer.show();
		}
		
		Thread.yield();
		
		//release resources
		if(this.graphics != null)
		{
			this.graphics.dispose();
		}
		if(this.g2d != null)
		{
			this.g2d.dispose();
		}
	}
	
	//////////////////
	//INNER CLASSES://
	//////////////////
	
	private class FPS
	{
		private int fps;
		private int frames;
		private long totalTime;
		private long currentTime;
		private long lastTime;
		
		public FPS()
		{
			this.fps = 0;
			this.frames = 0;
			this.totalTime = 0;
			this.currentTime = System.currentTimeMillis();
			this.lastTime = this.currentTime;
		}
		
		public void update()
		{
			this.lastTime = currentTime;
			this.currentTime = System.currentTimeMillis();
			
			this.totalTime += this.currentTime - this.lastTime;
			
			if(this.totalTime >1000)
			{
				this.totalTime -= 1000;
				this.fps = this.frames;
				this.frames = 0;
			}
			
			++this.frames;
		}
		
		public int getFPS()
		{
			return this.fps;
		}
	}
}
