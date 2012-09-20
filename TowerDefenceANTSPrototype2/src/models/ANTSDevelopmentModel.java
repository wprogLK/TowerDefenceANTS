package models;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import controllers.ANTSCellController;
import interfaces.ANTSIController;
import interfaces.ANTSIModel;

import basics.ANTSDevelopment;
import basics.ANTSDevelopment.ANTSStream;
import basics.ANTSFactory;
import basics.ANTSObjectCounter;
import basics.ANTSDriver.ANTSFPS;

public class ANTSDevelopmentModel extends ANTSAbstractModel implements ANTSIModel 
{
	private boolean doShowDebugScreenAll = true;
	
	private  boolean showFPS = true;
	private  boolean showExtraInformation = true;
	private  boolean showCurrentHoveringCellInfo = true;
	private  boolean showCurrentInterpolation = true;
	private  boolean showInterpolationStatus = true;
	private  boolean showBounds = false;
	private boolean showNrOfObjects = true;
	private boolean showDetectionGrid = false;
	private boolean showIsPause = true;
	
	private boolean interpolationOn = false;
	
	private boolean recordFPS = false;			//TODO
	
	private boolean isPause = false;
	
	private Graphics2D g2d;
	
	private int fontSize = 12;
	private Font font = new Font("Courier New", Font.PLAIN, fontSize );
	private Color fontColor = Color.BLACK;
	private int[] startPos = {fontSize, fontSize};
	private int[] currentPos = {10, 10};
	
	private ANTSIController currentHoveringCell = ANTSCellController.getEmptyController();
	private ANTSFPS fps;
	
	private float interpolation;
	
	public ANTSDevelopmentModel(ANTSFactory factory)
	{
		super(factory);
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
	

	
	////////////////
	//SHOW METHODS//
	////////////////
	
	public void showDebugScreen()
	{
		currentPos[0] = startPos[0];
		currentPos[1] = startPos[1];
		
		if(doShowDebugScreenAll && ANTSDevelopment.isDebugModeOn())
		{	
			g2d.setFont( font );
			g2d.setColor( fontColor );
			
			showCurrentHoveringCell();
			showFPS();
			showInterpolation();
			showInterpolationStatus();
			showFPSExtraInformation();
			showNumberOfObjects();
			showPause();
		}
	}
	
	private void showPause() 
	{
		if(showIsPause)
		{
			drawString("is pause: " + this.isPause);
		}
	}

	private void showNumberOfObjects() 
	{
		if(showNrOfObjects)
		{
				if(this.factory == null)
				{
					ANTSStream.printDebug("factory is null");
				}
			
				ANTSObjectCounter counter = this.factory.getObjectCounter();
				drawString(counter.getNumberOfMedium());
				drawString(counter.getNumberOfRays());
				drawString(counter.getNumberOfSourceOfLights());
				drawString(counter.getNumberOfGameObjectsTotal());
		}
	}

	private void showFPSExtraInformation() 
	{
		if(showExtraInformation)
		{
			drawString(String.format("average fps: %s", fps.getAverageFPS()));
			drawString(String.format("min fps: %s", fps.getMinFPS()));
			drawString(String.format("max fps: %s", fps.getMaxFPS()));
		}
	}

	///////////////////
	//PRIVATE METHODS//
	///////////////////
	
	private void showInterpolation() 
	{
		if(showCurrentInterpolation)
		{
			drawString(String.format("current interpolation: %s", interpolation));
		}
	}

	private void showCurrentHoveringCell()
	{
		if(showCurrentHoveringCellInfo)
		{
			drawString(String.format("currentHoveringCell: %s", currentHoveringCell));
		}
	}
	
	private void showFPS()
	{
		if(showFPS)
		{
			drawString(String.format("FPS: %s", fps.getFPS()));
		}
	}
	
	private void showInterpolationStatus()
	{
		if(showInterpolationStatus)
		{
			drawString(String.format("Interpolation status: %s", interpolationOn));
		}
	}
	
	private void drawString(String s)
	{
		 g2d.drawString(s,currentPos[0], currentPos[1] );
		 updatePos();
	}
	
	private void updatePos()
	{
		currentPos[1] += fontSize+5;
	}
	
	///////////
	//SETTERS//
	//////////
	
	public void setCurrentHoveringCell(ANTSCellController c)
	{
		currentHoveringCell = c;
	}
	
	public void setGraphics2D(Graphics2D g)
	{
		g2d = g;
	}
	
	public  void setFPS(ANTSFPS fpsIn)
	{
		fps = fpsIn;
	}
	
	public void setFactory(ANTSFactory factoryIn)
	{
		factory = factoryIn;
	}

	public void setInterpolation(float in) 
	{
		interpolation = in;
	}
	
	public void setShowDebugScreenAll(boolean value)
	{
		this.doShowDebugScreenAll = value;
	}

	public void setShowFPS(boolean value)
	{
		this.showFPS = value;
	}
	
	public void setShowExtraInformation(boolean value)
	{
		this.showExtraInformation = value;
	}
	
	public void setShowCurrentHoveringCellInfo(boolean value)
	{
		this.showCurrentHoveringCellInfo = value;
	}
	
	public void setShowCurrentInterplation(boolean value)
	{
		this.showCurrentInterpolation = value;
	}
	
	public void setShowInterpolationState(boolean value)
	{
		this.showInterpolationStatus = value;
	}
	
	public void setShowBounds(boolean value)
	{
		this.showBounds = value;
	}
	
	public void setShowNrOfObjects(boolean value)
	{
		this.showNrOfObjects = value;
	}
	
	public void setShowDetectionGrid(boolean value)
	{
		this.showDetectionGrid = value;
	}
	
	public void setInterpolationOn(boolean value)
	{
		this.interpolationOn = value;
	}
	
	
	
	////////////
	//SWITCHES//
	////////////
	

	public void switchShowDebugScreenAll()
	{
		this.doShowDebugScreenAll = !this.doShowDebugScreenAll;
	}

	public void switchShowFPS()
	{
		this.showFPS = !this.showFPS;
	}
	
	public void switchShowExtraInformation()
	{
		this.showExtraInformation = !this.showExtraInformation;
	}
	
	public void switchShowCurrentHoveringCellInfo()
	{
		this.showCurrentHoveringCellInfo = !this.showCurrentHoveringCellInfo;
	}
	
	public void switchShowCurrentInterplation()
	{
		this.showCurrentInterpolation = !this.showCurrentInterpolation;
	}
	
	public void switchShowInterpolationState()
	{
		this.showInterpolationStatus = 	!this.showInterpolationStatus;
	}
	
	public void switchShowBounds()
	{
		this.showBounds = !this.showBounds;
	}
	
	public void switchShowNrOfObjects()
	{
		this.showNrOfObjects = !this.showNrOfObjects;
	}
	
	public void switchShowDetectionGrid()
	{
		this.showDetectionGrid = !this.showDetectionGrid;
	}
	
	public void switchInterpolationOn()
	{
		this.interpolationOn = !this.interpolationOn;
	}
	
	public void switchRecordFPS() 
	{
		this.recordFPS = !this.recordFPS;
		
	}
	
	public void switchIsPause()
	{
		this.isPause = !this.isPause;
	}
	
	///////////
	//GETTERS//
	//////////
	
	public boolean isShowBounds()
	{
		return showBounds;
	}

	public boolean getInterpolationOn() 
	{
		return interpolationOn;
	}

	public boolean isShowDetectionGrid() 
	{
		return showDetectionGrid;
	}

	public boolean isPause() 
	{
		return this.isPause;
	}



	
}
