package basics;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import views.ANTSAbstractView;

import controllers.ANTSCellController;
import controllers.ANTSGameController;
import controllers.ANTSGridController;
import controllers.ANTSSimpleRayLightController;
import controllers.ANTSSimpleSourceLightController;

public class ANTSFactory 
{
	private ANTSDriver driver;
	
	private ArrayList<ANTSIController> controllers;
	private ANTSGameController gameController;
	private ANTSGridController gridController;
	
	public ANTSFactory(ANTSDriver d) 
	{
		this.driver = d;
		
		this.controllers = new ArrayList<ANTSIController>();
	}
	
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
			xCells=xCellsIn/2; // xCells/2 for a more quadratic square

		}
		
		this.gridController = new ANTSGridController(xCells,yCellsIn, this); 		
	}
	
	public ANTSCellController createCell(double cellHeight, double cellAngleInDegree, int cellNrX, int cellNrY, int shiftHalf, int xOffset, int yOffset) {
		ANTSCellController c = new ANTSCellController(cellHeight, cellAngleInDegree, cellNrX, cellNrY, shiftHalf, xOffset, yOffset, this);
		this.addController(c);
		
		return c;
	}
	
	
	//------------------------------------------------------//
	
	private void addController(ANTSIController c)
	{
		if(!this.controllers.contains(c))
		{
			this.controllers.add(c);
		}
		
		this.addToMouseListener(c);
	}
	
	public void updateAllModels()
	{
		this.gridController.getModel().update();
		
		for(int i = 0; i<this.controllers.size();i++)
		{
			ANTSIModel m = this.controllers.get(i).getModel();
			m.update();
		} 
	}
	
	public void paintAllViews(Graphics2D g2d, float interpolation)
	{
		this.gridController.getIView().paint(g2d, interpolation);
		
		for(int i = 0; i<this.controllers.size(); i++)
		{
			ANTSIView v = this.controllers.get(i).getIView();
			
			if(v.doPaintDirect())
			{
				v.paint(g2d, interpolation);
			}
		}
	}

	private void addToMouseListener(ANTSIController c) 
	{
		if(c.getModel().isMouseListener())
		{
			this.driver.addControllerToMouseListener(c);
		}
		
	}
}
