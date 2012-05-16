/**
 * 
 */
package basics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import listeners.ANTSSwitchLightListener;
import listeners.ANTSUpdateListener;
import models.ANTSSimpleSourceLightModel;

import interfaces.ANTSIController;
import interfaces.ANTSIDriver;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import controllers.ANTSGameController;
import controllers.ANTSSimpleRayLightController;
import controllers.ANTSSimpleSourceLightController;

/**
 * @author Lukas
 *
 */
public class ANTSDriver extends Thread implements ANTSIDriver, MouseListener
{
	private static ANTSWindow window;
	private static ANTSGameController gameController;
	private static ArrayList<ANTSIModel> models;
	private static ArrayList<ANTSIView> views;
	private static ArrayList<ANTSIController> controllers;
	
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
		
		models = new ArrayList<ANTSIModel>();
		views = new ArrayList<ANTSIView>();
		controllers = new ArrayList<ANTSIController>();
		
		this.initAllListeners();
		
		this.createGame();
		createSimpleSourceLight(); //Only for testing
		createSimpleSourceLight2();
		
		this.initActiveRendering();
	}
	
	private void initActiveRendering()
	{
		window.setIgnoreRepaint(true);
		
		//Canvas for painting
		Canvas canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		
		window.addCanvas(canvas);
		
		canvas.addMouseListener(this);
		
		//BackBuffer
		canvas.createBufferStrategy(2);
		this.buffer = canvas.getBufferStrategy();
		
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

	//Views
	
	private static void addView(ANTSIView v)
	{
		views.add(v);
	}

	//Model
	
	private static void addModel(ANTSIModel m) 
	{
		if(!models.contains(m))
		{
			models.add(m);
		}
	}
	
	//Controller
	
	private static void addController(ANTSIController c) 
	{
		if(!controllers.contains(c))
		{
			controllers.add(c);
		}
	}
	
	//Create new objects
	
	public static void createSimpleSourceLight()
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController(200,200,60,Color.yellow);
		addModel(c.getModel());
		addView(c.getView());
		addController(c);
		
		ANTSSwitchLightListener.addLight((ANTSSimpleSourceLightModel) c.getModel());	//only for testing
	}
	
	public static void createSimpleSourceLight2()
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController(20,20,20,Color.blue);
		addModel(c.getModel());
		addView(c.getView());
		addController(c);
	}
	
	public static void createSimpleRayLight(AffineTransform matrix, double velocity, double angle, Color color)
	{
		ANTSSimpleRayLightController c = new ANTSSimpleRayLightController(matrix,10,angle,color);
		addModel(c.getModel());
		addView(c.getView());
	}
	
	private void createGame()
	{
		gameController = new ANTSGameController();
		addModel(gameController.getModel());
		addView(gameController.getView());
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
				this.updateModels();
				
				nextGameTick +=SKIP_TICKS;
				loops++;
			}
			
			interpolation =(System.currentTimeMillis() + SKIP_TICKS - nextGameTick) /(SKIP_TICKS); //TODO Float
			this.paint(interpolation);
		}
	}
	
	private void updateModels()
	{
		for(int i = 0; i<models.size();i++)
		{
			ANTSIModel m = models.get(i);
			m.update();
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
		this.g2d.fillRect(0, 0, this.window.getWidthOfGraphics(),this.window.getHeightOfGraphics());
		
		for(int i = 0; i<views.size(); i++)
		{
			ANTSIView v = views.get(i);
			v.paint(g2d, interpolation);
		}
		
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
	//MOUSE LISTENER//
	//////////////////
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("CLICK");
		System.out.println("MODEL: "+ this.getModelAt(e.getX(),e.getY()));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//////////
	//SEARCH//
	//////////
	
	private ANTSIModel getModelAt(double x, double y)
	{
		for(ANTSIController c:this.controllers)
		{
			if(c.getIView().pointIsIn(x, y))
			{
				return c.getModel();
			}
		}
		
		return null;
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
