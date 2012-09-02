package views;

import java.awt.Graphics2D;
import interfaces.ANTSIView;

import controllers.ANTSCellController;

import models.ANTSGridModel;

public class ANTSGridView extends ANTSAbstractView implements ANTSIView
{
	private ANTSGridModel model;
	
	public ANTSGridView(ANTSGridModel m) 
	{
		super();
		
		this.model = m;
	}
		
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "Grid view";
	}

	@Override
	public boolean isMouseListener() 
	{
		return this.model.isMouseListener();
	}
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	

	@Override
	protected void updateShape(float interpolation) 
	{
		//do nothing
	}	
	
	@Override
	public void paint(Graphics2D g2d, float interpolation)
	{
		ANTSIView currentHoveringView = ANTSAbstractView.getEmptyView();
		
		for(int y = 0; y < this.model.getMaxCellY(); y++)
		{
			for(int x = 0; x < this.model.getMaxCellX(); x++)
			{
				ANTSCellController controller = model.getCellControllerAt(x, y);
				ANTSIView v = controller.getIView();
				v.paint(g2d, interpolation);
				
				if(v.isMouseOver())
				{
					currentHoveringView = v;
				}
			}
		}
		
		currentHoveringView.paint(g2d,interpolation);
	}
}
