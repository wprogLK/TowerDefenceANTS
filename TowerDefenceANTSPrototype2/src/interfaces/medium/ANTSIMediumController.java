package interfaces.medium;

import interfaces.ANTSIController;

public interface ANTSIMediumController extends ANTSIController
{
	public void addCollisionRay(ANTSIController c);

	public void removeCollisionRay(ANTSIController c);
	public double getRefractionIndex();
}
