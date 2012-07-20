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
	private static ANTSWindow window;
	private static ANTSGameController gameController;
	private static ANTSGridController gridController;
	
	private static ArrayList<ANTSIModel> models;
	private static ArrayList<ANTSIView> views;
	private static ArrayList<ANTSIController> controllers;
	
	private static ANTSCanvas canvas;
	
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
	
	//Grid Config
	private int xCells = 10;
	private int yCells = 10;
	
	private boolean ready = true;
	
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
//		createSimpleSourceLight2();
//		createSimpleSourceLight3();
//		createSimpleTestAnt1();
		
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

	public static void addComponents(ANTSIController c) 
	{
		addModel(c.getModel());
		addController(c);
	}
	
	//Views
	
	private static void addView(ANTSAbstractView v)
	{
		views.add(v);
		canvas.addView(v);
	}
	
	
	public static void addToCanvas(ANTSAbstractView v)
	{
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
		addComponents(c);
		addView(c.getView());
		ANTSSwitchLightListener.addLight((ANTSSimpleSourceLightModel) c.getModel());	//only for testing
	}

	public static void createSimpleSourceLight2()
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController(20,20,20,Color.blue,false);
		addView(c.getView());
		addComponents(c);
	}
	
	public static void createSimpleSourceLight3()
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController(6,5,20,Color.RED,true);
		addView(c.getView());
		addComponents(c);
	}
	
	public static void createSimpleTestAnt1()
	{
		ANTSSimpleTestAnt1Controller c = new ANTSSimpleTestAnt1Controller(60,50,320,320,Color.RED,true);
		addView(c.getView());
		addComponents(c);
	}
	
	public static void createSimpleRayLight(AffineTransform matrix, double velocity, double angle, Color color)
	{
		ANTSSimpleRayLightController c = new ANTSSimpleRayLightController(matrix,10,angle,color);
		addView(c.getView());
		addComponents(c);
	}
	
	
	
	private void createGame()
	{
		gameController = new ANTSGameController();
		addView(gameController.getView());
		addComponents(gameController);
		
		this.createGrid();
	}
	
	private void createGrid()
	{
		int xCells;
		
		if(this.xCells==1)
		{
			xCells=1;
		}
		else
		{
			xCells=this.xCells/2; // xCells/2 for a more quadratic square

		}
		
		gridController = new ANTSGridController(xCells,this.yCells); 		
		addView(gridController.getView());									
		addComponents(gridController);
		//TODO add to game and co
		addToCanvas(gridController.getView());
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
		g2d.setColor(this.backgroundColor); 
		this.g2d.fillRect(0, 0, this.window.getWidthOfGraphics(),this.window.getHeightOfGraphics());
		
		this.gridController.getView().paint(g2d, interpolation);

		for(int i = 0; i<views.size(); i++)
		{
			ANTSIView v = views.get(i);
			if(v.doPaintDirect())
			{
				v.paint(g2d, interpolation);
			}
			
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
	
	/**
	 * @param v
	 * @return always returns a valid controller (if no controller was found it will return an empty controller) 
	 */
	public ANTSIController getControllerFrom(ANTSIView v)
	{
		for(int i = controllers.size()-1 ; i>=0;i--)
		{
			ANTSIController c = controllers.get(i);
			System.out.println("controller: size " + i);

			if(c.getIView().equals(v)) 
			{
				return c;
			}
		}
		
		return ANTSAbstractController.getEmptyController();
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
		
		private ANTSIController currentEnteredController; //Only one entered controller per time possible! (TODO: change this! (if possible)
		private ANTSIController currentDragAndDropController;
		
		public ANTSCanvas()
		{
			this.hiddenPanel = new JPanel();
			
			this.currentDragAndDropController = ANTSAbstractController.getEmptyController();
			this.currentEnteredController = ANTSAbstractController.getEmptyController();
			
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
		}
		
		public void addView(ANTSAbstractView v)
		{
			if(v.isMouseListener())
			{
				this.hiddenPanel.add(v);
			}
		}
		
		@Override
		public void setSize(int width, int height)
		{
			super.setSize(width, height);
			this.hiddenPanel.setSize(width, height);
		}
		
		private ANTSIView getViewAt(int x, int y)
		{
			Component c =  this.hiddenPanel.getComponentAt(x, y);
			if(! c.equals(this.hiddenPanel))
			{
				return ((ANTSIView) c);
			}
			else
			{
				return  ANTSAbstractView.getEmptyView(); //Return a new empty view // -> every return value will be a valid ANTSAbstractView!
			}
		}
		
		public ANTSIController getControllerFromPos(int x, int y)
		{
			ANTSIView v = this.getViewAt(x, y);
			return getControllerFrom(v);
		}
		
		//////////////////
		//MOUSE LISTENER//
		//////////////////
		
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			ANTSIController c = this.getControllerFromPos(e.getX(), e.getY());
			c.mouseClicked(e);
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
			ANTSIController c = this.getControllerFromPos(e.getX(), e.getY());
			
			
			if(!c.equals(ANTSAbstractController.getEmptyController()))
			{
				this.currentDragAndDropController = c; //Set the current controller with view at point (x|y) as a possible dagAndDropController
			}
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			if(e.isPopupTrigger())
			{
				ANTSIView v = this.currentDragAndDropController.getIView();
				v.showPopupMenu(this,e.getX(), e.getY());
				System.out.println("SHOW MENU");
			}
			else
			{
				ANTSIView v = this.currentDragAndDropController.getIView();
				System.out.println("No menu: view " + v);
			}
			
			this.currentDragAndDropController.mouseReleased(e);
			this.currentDragAndDropController = ANTSAbstractController.getEmptyController();
			
		}

		/////////////////////////
		//MOUSE MOTION LISTENER//
		/////////////////////////
		
		@Override
		public void mouseDragged(MouseEvent e) 
		{
			this.currentDragAndDropController.mouseDragged(e);
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
			ANTSIController c = this.getControllerFromPos(e.getX(), e.getY());
			this.exitEnterController(e, c);
		}
		
		private void exitEnterController(MouseEvent e, ANTSIController c)
		{
			if(!c.equals(this.currentEnteredController))
			{
				this.currentEnteredController.mouseExited(e);	//Exited the old controller
				
				this.currentEnteredController = c;			
				c.mouseEntered(e);						//Enter the new controller
			}
		}
	}
	
	
}
