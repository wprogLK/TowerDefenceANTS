/**
 * 
 */
package basics;

import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import basics.ANTSDevelopment.ANTSStream;

import controllers.ANTSDevelopmentController;

import interfaces.ANTSIController;
import interfaces.ANTSIDriver;

/**
 * @author Lukas
 *
 */
public class ANTSDriver extends Thread implements ANTSIDriver
{
	private ANTSFactory factory;
	private ANTSDevelopmentController developmentController;
	private ANTSFPS fps;
	
	private ANTSWindow window;
	
	private Canvas canvas;
	private Color backgroundColor = Color.white;
	
	//Active Rendering:
	private Graphics graphics;
	private Graphics2D g2d;
	private BufferedImage bufferedImage;
	private BufferStrategy buffer;
	private GraphicsConfiguration graphicsConfig;
	
	private int width;
	private int height;
	
	public ANTSDriver()
	{	
		this.window = new ANTSWindow();
		this.window.setVisible(true);
		
		this.canvas = new Canvas();
		
		this.factory = new ANTSFactory(this);
		this.developmentController = this.factory.getDevelopmentController();
		
		this.createGame();
		
		this.initActiveRendering();
	}		
	
	public ANTSDriver(int width, int height)
	{	
		this.width = width;
		this.height = height;
		
		this.window = new ANTSWindow(width,height);
		this.window.setVisible(true);
		
		this.canvas = new Canvas();
		
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
		this.graphicsConfig = gD.getDefaultConfiguration();
		
		//Create off-screen drawing surface
		this.bufferedImage = this.graphicsConfig.createCompatibleImage(this.window.getWidthOfGraphics(),this.window.getHeightOfGraphics());
	}
	
	public void addControllerToMouseListener(ANTSIController c)
	{
		this.canvas.addMouseListener(c);
		this.canvas.addMouseMotionListener(c);
		this.canvas.addMouseWheelListener(c);
	}
	
	public void addControllerToKeyListener(ANTSIController c)
	{
		this.canvas.addKeyListener(c);	//TODO use key bindings
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
	
		this.fps = new ANTSFPS();
		
		while(gameIsRunning)
		{
			loops = 0;
			while(System.currentTimeMillis() >nextGameTick && loops<this.fps.getMaxFrameSkip())
			{
				if(!this.developmentController.isPause())
				{
					this.factory.updateAllModels();
				}
				
				nextGameTick +=this.fps.getSkiptTicks();
				loops++;
			}
			
			if(!this.developmentController.getInterpolationOn())
			{
				interpolation = 0;
			}
			else
			{
				interpolation =(System.currentTimeMillis() + this.fps.getSkiptTicks() - nextGameTick) /(this.fps.getSkiptTicks());
			}
			
			this.paint(interpolation);
		}
	}
	
	private void paint(float interpolation)
	{
		this.fps.update();
		
		this.bufferedImage = this.graphicsConfig.createCompatibleImage(this.window.getWidthOfGraphics(),this.window.getHeightOfGraphics());
		this.g2d = bufferedImage.createGraphics();
		
		this.developmentController.setGraphics2D(this.g2d);
		this.developmentController.setFPS(fps);
		this.developmentController.setInterpolation(interpolation);
		
		//Clear:
		g2d.setColor(this.backgroundColor); 
		this.g2d.fillRect(0, 0, this.window.getWidthOfGraphics(),this.window.getHeightOfGraphics());
		
		this.factory.paintAllViews(g2d, interpolation);

		//Blit image and flip
		this.graphics = this.buffer.getDrawGraphics();
		graphics.drawImage(this.bufferedImage, 0, 0, null);
		
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
	
	///////////
	//GETTERS//
	///////////
	
	public int getWidth()
	{
		return this.window.getWidth();
	}
	
	public int getHeight()
	{
		return this.window.getHeight();
	}
	
	///////////
	//SETTERS//
	///////////
	
	///////////
	//SPECIAL//
	///////////
	
	//////////////////
	//INNER CLASSES://
	//////////////////
	
	public class ANTSFPS
	{
		private int fps;
		private int frames;
		private long totalTime;
		private long currentTime;
		private long lastTime;
		
		private int maxFPS;
		private int minFPS;
		private int averageFPS;
		private int counter;
		private int totalFPS;
		
		
		private final int TICKS_PER_SECOND = 25;
		private final int SKIP_TICKS = 1000/TICKS_PER_SECOND;
		private final int MAX_FRAMESKIP = 5;
		
		public ANTSFPS()
		{
			this.fps = 0;
			this.frames = 0;
			this.totalTime = 0;
			this.currentTime = System.currentTimeMillis();
			this.lastTime = this.currentTime;
			
			this.maxFPS = 0;
			this.minFPS = 1000;
			this.averageFPS = 0;
			this.counter = 0;
			this.totalFPS = 0;
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
				
				this.stats();
			}
			
			++this.frames;
		}
		
		private void stats() 
		{
			this.counter++;
			this.totalFPS+= this.fps;
			
			this.averageFPS = this.totalFPS/counter;
			
			this.calculateMax(this.fps);
			this.calculateMin(this.fps);
		}

		private void calculateMin(int fps)
		{
			if(fps<this.minFPS)
			{
				this.minFPS = fps;
			}
		}

		private void calculateMax(int fps) {
			if(fps>this.maxFPS)
			{
				this.maxFPS = fps;
			}
		}

		public int getFPS()
		{
			return this.fps;
		}
		
		public int getAverageFPS()
		{
			return this.averageFPS;
		}
		
		public int getMaxFPS()
		{
			return this.maxFPS;
		}
		
		public int getMinFPS()
		{
			return this.minFPS;
		}
		
		public int getTicksPerSecond()
		{
			return this.TICKS_PER_SECOND;
		}
		
		public int getSkiptTicks()
		{
			return this.SKIP_TICKS;
		}
		
		public int getMaxFrameSkip()
		{
			return this.MAX_FRAMESKIP;
		}
	}
}
