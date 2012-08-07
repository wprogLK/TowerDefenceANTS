package layers;

import interfaces.ANTSIController;

public class ANTSLayerSystem 
{
	public enum Layer
	{
		top,
		middle,
		behind,
		none;
	}
	
	private ANTSIController[] layers;
	
	public ANTSLayerSystem()
	{
		int sizeOfLayers = Layer.values().length;
		this.layers = new ANTSIController[sizeOfLayers];
	}
}
