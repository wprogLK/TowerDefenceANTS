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
import controllers.ANTSGameController;
import controllers.ANTSSimpleRayLightController;
import controllers.ANTSSimpleSourceLightController;

/**
 * @author Lukas
 *
 */
public class ANTSDriver extends Thread implements ANTSIDriver
{
	private static ANTSWindow window;
	private static ANTSGameController gameController;
	private static ArrayList<ANTSIModel> models;
	private static ArrayList<ANTSIView> views;
	private static ArrayList<ANTSIController> controllers;
	
	private static ANTSCanvas canvas;
	
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
		
		canvas = new ANTSCanvas();
		
		this.initAllListeners();
		
		this.createGame();
		createSimpleSourceLight(); //Only for testing
		createSimpleSourceLight2();
		createSimpleSourceLight3();
		
		this.initActiveRendering();
	}
	
	private void initActiveRendering()
	{
		window.setIgnoreRepaint(true);
		
		//Canvas for painting
		canvas.setIgnoreRepaint(true);
//		canvas.addMouseListener(window);
		
		window.addCanvas(this.canvas);
		
		//BackBuffer
		JPanel p = new JPanel();
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
	
	private static void addView(ANTSAbstractView v)
	{
		views.add(v);
		canvas.addView(v);
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
	
	public static void createSimpleSourceLight3()
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController(6,5,20,Color.RED);
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
	
	public ANTSIController getControllerFrom(ANTSIView v)
	{
		for(ANTSIController c: controllers)
		{
			if(c.getIView().equals(v))
			{
				return c;
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
	
	private class ANTSCanvas extends Canvas implements MouseListener, MouseMotionListener
	{
		private JPanel hiddenPanel; //Contains all ANTSAbstract views, but it is hidden; used for correct mouse position
		private ANTSAbstractView currentEnteredView;	//Only one entered view per time possible! (TODO: change this! (if possible)
		private ANTSAbstractView currentDragAndDropView;
		
		private final ANTSAbstractView emptyView = new ANTSAbstractView(){};
		
		public ANTSCanvas()
		{
			this.hiddenPanel = new JPanel();
			this.currentEnteredView = this.emptyView; //empty view
			this.currentDragAndDropView = this.emptyView; //empty view
			
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
		}
		
		public void addView(ANTSAbstractView v)
		{
			if(v.isListenToMouse())
			{
				this.hiddenPanel.add(v);
			}
		}
		
		@Override
		public void setSize(int width, int height)
		{
			super.setSize(width, height);
			this.hiddenPanel.setSize(width, height); //TODO CHECK THIS!
		}
		
		private ANTSAbstractView getViewAt(int x, int y)
		{
			Component c =  this.hiddenPanel.getComponentAt(x, y);
			
			if(! c.equals(this.hiddenPanel))
			{
				return ((ANTSAbstractView) c);
			}
			else
			{
				return this.emptyView; //Return a new empty view // -> every return value will be a valid ANTSAbstractView!
			}
		}
		
		//////////////////
		//MOUSE LISTENER//
		//////////////////
		
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			ANTSAbstractView v = this.getViewAt(e.getX(), e.getY());
//			System.out.println("VIEW: "+ getControllerFrom(v).getIView());
			
			v.mouseClicked(e);
			
		
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) 
		{

		}

		@Override
		public void mouseExited(MouseEvent e) 
		{

		}

		@Override
		public void mousePressed(MouseEvent e) 
		{
			ANTSAbstractView v = this.getViewAt(e.getX(), e.getY());
			
			if(!v.equals(this.emptyView))
			{
				this.currentDragAndDropView = v;	//Set the current view at point (x|y) as a possible dagAndDropView
			}
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{

			JPopupMenu m = this.currentDragAndDropView.getPopupMenu();
			
			if(e.isPopupTrigger()){	//TODO
				  m.show(this, e.getX(), e.getY());
				  System.out.println("SHOW MENU");
				  }
			
			
			this.currentDragAndDropView.mouseReleased(e);
			this.currentDragAndDropView = this.emptyView;
		}

		/////////////////////////
		//MOUSE MOTION LISTENER//
		/////////////////////////
		
		@Override
		public void mouseDragged(MouseEvent e) 
		{
			this.currentDragAndDropView.mouseDragged(e);
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
			ANTSAbstractView v = this.getViewAt(e.getX(), e.getY());
			
			this.exitEnterView(e, v);
		}
		
		private void exitEnterView(MouseEvent e, ANTSAbstractView v)
		{
			if(!v.equals(this.currentEnteredView))
			{
				this.currentEnteredView.mouseExited(e);	//Exited the old view
				
				this.currentEnteredView = v;			
				v.mouseEntered(e);						//Enter the new view
			}
		}
	}
}
