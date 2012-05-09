/**
 * 
 */
package model.basics;

import model.abstracts.ANTSModelAbstract;
import interfaces.ANTSIModel;

/**
 * @author Lukas
 *
 */
public class ANTSGameModel extends ANTSModelAbstract implements ANTSIModel
{
	public ANTSGameModel()
	{
		super();
	}
	
	public boolean setNewDraw(boolean drawState)
	{
		return !drawState;
	}

	@Override
	public void update() 
	{
		
	}
}
