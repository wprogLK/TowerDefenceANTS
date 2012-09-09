package interfaces;

import java.awt.geom.Line2D;

import interfaces.medium.ANTSIMediumController;

public interface ANTSIRayController extends ANTSIController
{
	public boolean setCurrentMedium(ANTSIMediumController c);
	public double getRefractionIndex();
	double getAngle();
	public void addAngle(double angle);
	public double[] getCenter();
}
