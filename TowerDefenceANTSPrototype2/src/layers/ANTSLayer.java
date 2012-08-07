package layers;

import basics.ANTSDevelopment.ANTSStream;
import layers.ANTSLayerSystem.Layer;
import interfaces.ANTSIController;

public class ANTSLayer
{
	private Layer layer;
	private ANTSIController controller;
	
	public ANTSLayer(ANTSIController c)
	{
		this.controller = c;
		this.layer = Layer.behind;
	}
	
	public void shiftUp()
	{
		Layer layerBefore = this.layer;
		
		switch(this.layer)
		{
			case behind:
			{
				this.layer = Layer.middle;
				break;
			}
			case middle:
			{
				this.layer = Layer.top;
				break;
			}
			default:
			{
				break;
			}
		}
		
		ANTSStream.printDebug("Shift layer up from " + layerBefore + " to layer " + this.layer);
	}
	
	public void shiftDown()
	{
		Layer layerBefore = this.layer;
		
		switch(this.layer)
		{
			case top:
			{
				this.layer = Layer.middle;
				break;
			}
			case middle:
			{
				this.layer = Layer.behind;
				break;
			}
			default:
			{
				break;
			}
		}
		
		ANTSStream.printDebug("Shift layer down from " + layerBefore + " to layer " + this.layer);
	}
	
	
	
}
