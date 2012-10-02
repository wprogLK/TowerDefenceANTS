package basics;

import interfaces.ANTSIController;
import interfaces.ANTSIDriver;
import interfaces.ANTSIFactory;
import interfaces.ANTSIModel;
import interfaces.ANTSIRefractionComputeUnit;
import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuController;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import basics.ANTSDevelopment.ANTSStream;

import controllers.ANTSCellController;
import controllers.ANTSDevelopmentController;
import controllers.ANTSGameController;
import controllers.ANTSGridController;
import controllers.ANTSSimpleRayLightController;
import controllers.lens.ANTSSimpleLensController;
import controllers.medium.ANTSSimpleMediumController;
import controllers.medium.ANTSStandardMediumController;
import controllers.menus.ANTSCircleMenuController;
import controllers.menus.ANTSRectangleMenuController;
import controllers.sourceLight.ANTSSimpleSourceLightController;
import controllers.sourceLight.ANTSSimpleSourceLightNeonController;

public class ANTSFactory implements ANTSIFactory
{
	private ANTSIDriver driver;
	
	private ArrayList<ANTSIController> controllers;
	private ArrayList<ANTSIMenuController> menuControllers;
	
	private ANTSGameController gameController;
	private ANTSGridController gridController;
	private ANTSCollisionDetection collisionDetection;
	private ANTSStandardMediumController standardMediumController;
	private ANTSDevelopmentController developmentController;
	private ANTSIRefractionComputeUnit refractionCalculationUnit;
	
	private ANTSObjectCounter objectCounter;
	
	private int cellNumbersX = 10;
	private int cellNumbersY = 10;
	
	public ANTSFactory(ANTSIDriver d) 
	{
		super();
		
		this.driver = d;
		
		this.controllers = new ArrayList<ANTSIController>();
		this.menuControllers = new ArrayList<ANTSIMenuController>();
		this.standardMediumController = new ANTSStandardMediumController(1.8,this);	//Vacuum n = 1
		this.developmentController = new ANTSDevelopmentController(this);
		this.refractionCalculationUnit = new ANTSRefractionComputeUnit(this.standardMediumController);
		
		this.createCollisionDetection();
		
		this.addToKeyListener(this.developmentController);
		
		this.objectCounter = new ANTSObjectCounter();
	}
	
	//////////////////
	//Create methods//
	//////////////////
	
	public ANTSCollisionDetection createCollisionDetection(int cellsX, int cellsY)
	{
		this.collisionDetection  = new ANTSCollisionDetection(this.driver.getHeight(), this.driver.getWidth(),cellsX,cellsY, this);
		return this.collisionDetection;
	}
	
	public ANTSCollisionDetection createCollisionDetection()
	{
		this.collisionDetection  = new ANTSCollisionDetection(this.driver.getHeight(), this.driver.getWidth(),this.cellNumbersX,this.cellNumbersY, this);
		return this.collisionDetection;
	}
	
	public void createGrid(int xCellsIn, int yCellsIn)
	{
		int xCells;
		
		if(xCellsIn==1)
		{
			xCells=1;
		}
		else
		{
			xCells=xCellsIn/2; // xCells/2 for a more quadratic square look
		}
		
		this.gridController = new ANTSGridController(xCells,yCellsIn, this); 		
	}
	
	public ANTSCellController createCell(double cellHeight, double cellAngleInDegree, int cellNrX, int cellNrY, int shiftHalf, int xOffset, int yOffset)
	{
		ANTSCellController c = new ANTSCellController(cellHeight, cellAngleInDegree, cellNrX, cellNrY, shiftHalf, xOffset, yOffset, this);
		this.addController(c);
		
		return c;
	}
	
	////////////////
	//Create lens'//
	///////////////
	
	public ANTSSimpleLensController createSimpleLens(double posX, double posY, double radius, double refractionIndex, boolean isMouseListener )
	{
		ANTSSimpleLensController c = new ANTSSimpleLensController(posX, posY, radius, refractionIndex, isMouseListener, this);
		this.addController(c);
		
		return c;
	}
	
	////////////////////////
	//Create source lights//
	////////////////////////
	
	public void createSimpleSourceLight(double posX, double posY, double radius, Color color, boolean isMouseListener)
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController(posX, posY, radius, color, isMouseListener, this);
		this.addController(c);
	}
	
	public void createSimpeSourceLigthNeon(double posX, double posY, double length, Color color, boolean isMouseListener)
	{
		ANTSSimpleSourceLightNeonController c = new ANTSSimpleSourceLightNeonController(posX, posY, length, color, isMouseListener, this);
		this.addController(c);
	}
	
	///////////////
	//Create rays//
	///////////////
	
	public void createSimpleRayLight(double[] center, double velocity, double angle, Color sourceColor) 
	{
		ANTSSimpleRayLightController c = new ANTSSimpleRayLightController(center, velocity, angle, sourceColor, this);
		this.addController(c);
	}
	
	public void createGame()
	{
		this.gameController = new ANTSGameController(this);
		
		this.addToKeyListener(this.gameController);
	}
	
	/////////////////
	//Create medium//
	/////////////////
	
	public ANTSSimpleMediumController createSimpleMedium(double posX, double posY, double height, double width, double refractionIndex, boolean isMouseListener)
	{
		ANTSSimpleMediumController c = new ANTSSimpleMediumController(posX, posY, height, width, refractionIndex, isMouseListener, this);
		this.addController(c);
		
		return c;
	}
	
	public ANTSStandardMediumController createStandardMediumController()
	{
		return this.standardMediumController;
	}
	
	////////////////
	//Create menus//
	////////////////
	
	public ANTSCircleMenuController createCircleMenu(double posX, double posY, double radius)
	{
		ANTSCircleMenuController c = new ANTSCircleMenuController(posX, posY, radius, this);
		this.addMenuController(c);
		
		return c;
	}
	
	public ANTSRectangleMenuController createRectangleMenu(double posX, double posY, double minWidth)
	{
		ANTSRectangleMenuController c = new ANTSRectangleMenuController(posX, posY, minWidth, this);
		this.addMenuController(c);
		
		return c;
	}
	
	///////////
	//GETTERS//
	///////////
	
	public ANTSObjectCounter getObjectCounter()
	{
		return this.objectCounter;
	}
	
	public ANTSDevelopmentController getDevelopmentController()
	{
		return this.developmentController;
	}
	
	public ANTSIDriver getDriver()
	{
		return this.driver;
	}
	
	public ANTSIRefractionComputeUnit getRefractionCalculationUnit() 
	{
		return this.refractionCalculationUnit;
	}
	
	
	///////////
	//SETTERS//
	///////////

	public void setRefractionComputeUnit(ANTSIRefractionComputeUnit unit) 
	{
		this.refractionCalculationUnit = unit;
	}
	
	//////////////////////
	//Add/remove methods//
	//////////////////////
	
	private void addMenuController(ANTSIMenuController c) 
	{
		if(!this.menuControllers.contains(c))
		{
			this.menuControllers.add(c);
		}
		
		this.addToMouseListener((ANTSIController) c);
	}

	private void addController(ANTSIController c)
	{
		if(!this.controllers.contains(c))
		{
			this.controllers.add(c);
		}
		
		if(c.getModel().isCollisionDetected() )
		{
			this.collisionDetection.addController(c);
		}
		
		this.addToMouseListener(c);
		
		this.objectCounter.add(c);
	}
	
	public void removeController(ANTSIController c)
	{
		boolean value = this.controllers.remove(c);
		this.objectCounter.remove(c);
		
		if(!value)
		{
			ANTSStream.printErr("Error: The gameObject " + c + "couldn't remove in the ANTSFactory Class!" );	//TODO (check this)
		}
	}
	
	public void addToMouseListener(ANTSIController c) 
	{
		if(c.getModel().isMouseListener())
		{
			this.driver.addControllerToMouseListener(c);
		}
	}
	
	public void addToKeyListener(ANTSIController c)
	{
		this.driver.addControllerToKeyListener(c);
	}
	
	///////////
	//Special//
	///////////
	
	public void paintAllViews(Graphics2D g2d, float interpolation)
	{
		this.gridController.getIView().paint(g2d, interpolation);
		
		for(int i = 0; i<this.controllers.size(); i++)
		{
			ANTSIView view = this.controllers.get(i).getIView();
			
			if(view.doPaintDirect())
			{
				view.paint(g2d, interpolation);
			}
		}
		
		this.collisionDetection.paintDetectionGrid(g2d);
		this.developmentController.setGraphics2D(g2d);
		this.developmentController.getIView().paint(g2d, interpolation);
	}
	
	public void updateAllModels()
	{
		this.collisionDetection.update();
		
		this.gridController.update();			//TODO: IMPORTANT: disabled models update in gridController if isCollisionDetected == true!!!! Otherwise some gameObjects are updates twice in one gameLoop
		
		for(int i = 0; i<this.controllers.size();i++)
		{
			ANTSIController controller = this.controllers.get(i);
			ANTSIModel model = controller.getModel();	
			
			if(!model.isCollisionDetected())
			{
				controller.update();
			}
		} 
		
		this.developmentController.update();
	}
	
	public void resetUpdate()
	{
		for(ANTSIController c:this.controllers)
		{
			c.getModel().setIsAlreadyUpdated(false);

		}
	}



}
