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
	
	public static class ANTSDebug
	{
		private static boolean doShowDebugScreenAll = true;
		
		private static boolean showFPS = true;
		private static boolean showCurrentHoveringCellInfo = true;
		private static boolean showCurrentInterpolation = true;
		
		private  static boolean showBounds = false;
		
		private static Graphics2D g2d;
		
		private static int fontSize = 12;
		private static Font font = new Font("Courier New", Font.PLAIN, fontSize );
		private static Color fontColor = Color.BLACK;
		private static int[] startPos = {fontSize, fontSize};
		private static int[] currentPos = {10, 10};
		
		private static ANTSIController currentHoveringCell = ANTSCellController.getEmptyController();
		private static ANTSFPS fps;
		private static float interpolation;
		
		
		////////////////
		//SHOW METHODS//
		////////////////
		
		public static void showDebugScreen()
		{
			currentPos[0] = startPos[0];
			currentPos[1] = startPos[1];
			
			if(doShowDebugScreenAll && debugModeOn)
			{	
				g2d.setFont( font );
				g2d.setColor( fontColor );
				
				showCurrentHoveringCell();
				showFPS();
				showInterpolation();
			}
		}
		
		///////////////////
		//PRIVATE METHODS//
		///////////////////
		
		private static void showInterpolation() 
		{
			if(showCurrentInterpolation && debugModeOn)
			{
				drawString(String.format("current interpolation: %s", interpolation));
				updatePos();
			}
			
		}

		private static void showCurrentHoveringCell()
		{
			if(showCurrentHoveringCellInfo && debugModeOn)
			{
				drawString(String.format("currentHoveringCell: %s", currentHoveringCell));
				updatePos();
			}
		}
		
		private static void showFPS()
		{
			if(showFPS  && debugModeOn)
			{
				drawString(String.format("FPS: %s", fps.getFPS()));
				updatePos();
			}
		}
		
		private static void drawString(String s)
		{
			 g2d.drawString(s,currentPos[0], currentPos[1] );
		}
		
		private static void updatePos()
		{
			currentPos[1] += fontSize+5;
		}
		
		///////////
		//SETTERS//
		//////////
		
		public static void setCurrentHoveringCell(ANTSCellController c)
		{
			currentHoveringCell = c;
		}
		
		public static void setGraphics2D(Graphics2D g)
		{
			g2d = g;
		}
		
		public static void setFPS(ANTSFPS fpsIn)
		{
			fps = fpsIn;
		}

		public static void setInterpolation(float in) 
		{
			interpolation = in;
		}
		
		///////////
		//GETTERS//
		//////////
		
		public static boolean isShowBounds()
		{
			return showBounds;
		}
	}
	
	////////////////
	//STREAM CLASS//
	////////////////
	
	public static class ANTSStream
	{
		private static boolean doPrintAll = true;
		
		private static boolean doPrintMessages = true;
		private static boolean doPrintErr = true;
		
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
			if(debugModeOn && doPrintAll)
			{
				System.out.println(output);
			}
		}
	}
}