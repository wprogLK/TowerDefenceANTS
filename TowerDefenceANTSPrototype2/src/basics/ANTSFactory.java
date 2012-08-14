package basics;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;
import interfaces.menus.ANTSIMenuController;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import controllers.ANTSCellController;
import controllers.ANTSGameController;
import controllers.ANTSGridController;
import controllers.ANTSSimpleMediumController;
import controllers.ANTSSimpleRayLightController;
import controllers.ANTSSimpleSourceLightController;
import controllers.menus.ANTSCircleMenuController;
import controllers.menus.ANTSRectangleMenuController;

public class ANTSFactory
{
	private ANTSDriver driver;
	
	private ArrayList<ANTSIController> controllers;
	private ArrayList<ANTSIMenuController> menuControllers;
	
	private ANTSGameController gameController;
	private ANTSGridController gridController;
	
	public ANTSFactory(ANTSDriver d) 
	{
		super();
		this.driver = d;
		
		this.controllers = new ArrayList<ANTSIController>();
		this.menuControllers = new ArrayList<ANTSIMenuController>();
	}
	

	//////////////////
	//Create methods//
	//////////////////
	
	public void createSimpleSourceLight(double posX, double posY, double radius, Color color, boolean isMouseListener)
	{
		ANTSSimpleSourceLightController c = new ANTSSimpleSourceLightController(posX, posY, radius, color, isMouseListener, this);
		this.addController(c);
	}
	
	public void createSimpleRayLight(AffineTransform sourceMatrix, double velocity, double angle, Color sourceColor)
	{
		ANTSSimpleRayLightController c = new ANTSSimpleRayLightController(sourceMatrix, velocity, angle, sourceColor, this);
		this.addController(c);
	}
	
	public void createGame()
	{
		ANTSGameController c = new ANTSGameController(this);
		this.gameController = c;
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
	
	public ANTSSimpleMediumController createSimpleMedium(double posX, double posY, double height, double width, boolean isMouseListener)
	{
		ANTSSimpleMediumController c = new ANTSSimpleMediumController(posX, posY, height, width, isMouseListener, this);
		this.addController(c);
		
		return c;
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
	

	
	
	///////////////
	//Add methods//
	///////////////
	
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
		
		this.addToMouseListener(c);
	}
	
	public void addToMouseListener(ANTSIController c) 
	{
		if(c.getModel().isMouseListener())
		{
			this.driver.addControllerToMouseListener(c);
		}
		
	}
	
	///////////
	//Special//
	///////////
	
	public void updateAllModels()
	{
		this.gridController.getModel().update();
		
		for(int i = 0; i<this.controllers.size();i++)
		{
			ANTSIModel model = this.controllers.get(i).getModel();
			model.update();
		} 
	}
	
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
	}
}
