package basics;

import interfaces.ANTSIController;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import basics.ANTSDriver.ANTSFPS;

import controllers.ANTSCellController;

public class ANTSDevelopment
{
	private static boolean debugModeOn = true;
	
	///////////////
	//DEBUG CLASS//
	///////////////
	
//	public static class ANTSDebug
//	{
//		private static boolean doShowDebugScreenAll = true;
//		
//		private static boolean showFPS = true;
//		private static boolean showExtraInformation = true;
//		private static boolean showCurrentHoveringCellInfo = true;
//		private static boolean showCurrentInterpolation = true;
//		private static boolean showInterpolationStatus = true;
//		private  static boolean showBounds = false;
//		private static boolean showNrOfObjects = true;
//		private static boolean showDetectionGrid = true;
//		
//		private static boolean interpolationOn = false;
//		
//		private static boolean recordFPS = false;			//TODO
//		
//		private static Graphics2D g2d;
//		
//		private static int fontSize = 12;
//		private static Font font = new Font("Courier New", Font.PLAIN, fontSize );
//		private static Color fontColor = Color.BLACK;
//		private static int[] startPos = {fontSize, fontSize};
//		private static int[] currentPos = {10, 10};
//		
//		private static ANTSIController currentHoveringCell = ANTSCellController.getEmptyController();
//		private static ANTSFPS fps;
//		private static ANTSFactory factory;
//		
//		private static float interpolation;
//		
//		////////////////
//		//SHOW METHODS//
//		////////////////
//		
//		public static void showDebugScreen()
//		{
//			currentPos[0] = startPos[0];
//			currentPos[1] = startPos[1];
//			
//			if(doShowDebugScreenAll && debugModeOn)
//			{	
//				g2d.setFont( font );
//				g2d.setColor( fontColor );
//				
//				showCurrentHoveringCell();
//				showFPS();
//				showInterpolation();
//				showInterpolationStatus();
//				showFPSExtraInformation();
//				showNumberOfObjects();
//			}
//		}
//		
//		private static void showNumberOfObjects() 
//		{
//			if(showNrOfObjects && debugModeOn)
//			{
//					ANTSObjectCounter counter = factory.getObjectCounter();
//					drawString(counter.getNumberOfMedium());
//					drawString(counter.getNumberOfRays());
//					drawString(counter.getNumberOfSourceOfLights());
//					drawString(counter.getNumberOfGameObjectsTotal());
//			}
//		}
//
//		private static void showFPSExtraInformation() 
//		{
//			if(showExtraInformation && debugModeOn)
//			{
//				drawString(String.format("average fps: %s", fps.getAverageFPS()));
//				drawString(String.format("min fps: %s", fps.getMinFPS()));
//				drawString(String.format("max fps: %s", fps.getMaxFPS()));
//			}
//		}
//
//		///////////////////
//		//PRIVATE METHODS//
//		///////////////////
//		
//		private static void showInterpolation() 
//		{
//			if(showCurrentInterpolation && debugModeOn)
//			{
//				drawString(String.format("current interpolation: %s", interpolation));
//			}
//			
//		}
//
//		private static void showCurrentHoveringCell()
//		{
//			if(showCurrentHoveringCellInfo && debugModeOn)
//			{
//				drawString(String.format("currentHoveringCell: %s", currentHoveringCell));
//			}
//		}
//		
//		private static void showFPS()
//		{
//			if(showFPS  && debugModeOn)
//			{
//				drawString(String.format("FPS: %s", fps.getFPS()));
//			}
//		}
//		
//		private static void showInterpolationStatus()
//		{
//			if(showInterpolationStatus && debugModeOn)
//			{
//				drawString(String.format("Interpolation status: %s", interpolationOn));
//			}
//		}
//		
//		private static void drawString(String s)
//		{
//			 g2d.drawString(s,currentPos[0], currentPos[1] );
//			 updatePos();
//		}
//		
//		private static void updatePos()
//		{
//			currentPos[1] += fontSize+5;
//		}
//		
//		///////////
//		//SETTERS//
//		//////////
//		
//		public static void setCurrentHoveringCell(ANTSCellController c)
//		{
//			currentHoveringCell = c;
//		}
//		
//		public static void setGraphics2D(Graphics2D g)
//		{
//			g2d = g;
//		}
//		
//		public static void setFPS(ANTSFPS fpsIn)
//		{
//			fps = fpsIn;
//		}
//		
//		public static void setFactory(ANTSFactory factoryIn)
//		{
//			factory = factoryIn;
//		}
//
//		public static void setInterpolation(float in) 
//		{
//			interpolation = in;
//		}
//		
//		///////////
//		//GETTERS//
//		//////////
//		
//		public static boolean isShowBounds()
//		{
//			return showBounds;
//		}
//
//		public static boolean getInterpolationOn() 
//		{
//			return interpolationOn;
//		}
//
//		public static boolean isShowDetectionGrid() 
//		{
//			return showDetectionGrid;
//		}
//	}
	
	////////////////
	//STREAM CLASS//
	////////////////
	
	public static class ANTSStream
	{
		private static boolean doPrintAll = true;
		
		private static boolean doPrintMessages = true;
		private static boolean doPrintErr = true;
		private static boolean doPrintDebug = true;
		
		public static void print(String output)
		{
			if(doPrintMessages && doPrintAll)
			{
				System.out.println(output);
			}
		}
		
		public static void printErr(String output)
		{
			if(doPrintErr && doPrintAll)
			{
				System.out.println(output);
			}
		}
		
		public static void printDebug(String output)
		{
			if(debugModeOn && doPrintAll &&  doPrintDebug)
			{
				System.out.println(output);
			}
		}
	}

	public static boolean isDebugModeOn() 
	{
		return debugModeOn;
	}
}