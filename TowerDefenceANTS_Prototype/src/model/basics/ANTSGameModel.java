/**
 * 
 */
package model.basics;

import interfaces.ANTSIModel;

/**
 * @author Lukas
 *
 */
public class ANTSGameModel implements ANTSIModel
{
	public boolean setNewDraw(boolean drawState)
	{
		return !drawState;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
