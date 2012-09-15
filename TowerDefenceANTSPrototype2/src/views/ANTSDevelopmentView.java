package views;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import interfaces.ANTSIView;

import models.ANTSDevelopmentModel;

public class ANTSDevelopmentView extends ANTSAbstractView implements ANTSIView
{
	private ANTSDevelopmentModel model;
	
	private ANTSDebugWindow debugWindow;
	
	public ANTSDevelopmentView(ANTSDevelopmentModel m) 
	{
		super(m);
		
		this.model = m;
		
		this.debugWindow = new ANTSDebugWindow();
		
	}
		
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "Development view";
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	@Override
	public boolean doPaintDirect()
	{
		return false;
	}
	
	///////////
	//Setters//
	///////////
	
	
	
	///////////
	//Special//
	///////////
	
	@Override
	public void paint(Graphics2D g2d, float interpolation)
	{
		this.model.showDebugScreen();
	}
	
	public void show()
	{
		this.debugWindow.setVisible(true);
	}
	
	/////////////////////////
	//ANTSDebugWindow Class//
	/////////////////////////
	
	private class ANTSDebugWindow extends JFrame
	{
		JButton showFPSButton = new JButton("Show FPS");
		JButton showExtraInformationButton  = new JButton("Show extra information");
		JButton showCurrentHoveringCellButton = new JButton("Show current hovering cell");
		JButton showCurrentInterpolationButton = new JButton("Show current interpolation");
		JButton showBoundsButton = new JButton("Show bounds");
		JButton showNrOfObjectsButton = new JButton("Show # of objects");
		JButton showDetectionGridButton = new JButton("Show detection grid");
		JButton interpolationSwitchButton = new JButton("Switch interpolation on/off");
		JButton recordFPSButton = new JButton("Record FPS");
		
//		ANTSDevelopmentModel model;
		
		public ANTSDebugWindow()
		{
			this.setSize(200, 200);
			this.setTitle("ANTS Debug Window");
			
			this.setLayout(new GridLayout(9, 1));
			
			this.add(this.showFPSButton);
			this.add(this.showExtraInformationButton);
			this.add(this.showCurrentHoveringCellButton);
			this.add(this.showCurrentInterpolationButton);
			this.add(this.showBoundsButton);
			this.add(this.showNrOfObjectsButton);
			this.add(this.showDetectionGridButton);
			this.add(this.interpolationSwitchButton);
			this.add(this.recordFPSButton);
			
			this.addActionToButton();
		}
		
		private void addActionToButton()
		{
			this.showFPSButton.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					model.switchShowFPS();
				}
			});
			
			this.showExtraInformationButton.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					model.switchShowExtraInformation();
				}
			});
			
			this.showCurrentHoveringCellButton.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					model.switchShowCurrentHoveringCellInfo();
				}
			});
			
			this.showCurrentInterpolationButton.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					model.switchShowCurrentInterplation();
				}
			});
			
			this.showBoundsButton.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					model.switchShowBounds();
				}
			});
			
			this.showNrOfObjectsButton.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					model.switchShowNrOfObjects();
				}
			});
			
			this.showDetectionGridButton.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					model.switchShowDetectionGrid();
				}
			});
			
			this.interpolationSwitchButton.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					model.switchInterpolationOn();
				}
			});
			
			this.recordFPSButton.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					model.switchRecordFPS();
				}
			});
		}
	}
}