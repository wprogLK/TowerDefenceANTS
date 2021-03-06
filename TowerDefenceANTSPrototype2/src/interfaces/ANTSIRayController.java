package interfaces;

import java.awt.geom.Point2D;

import interfaces.medium.ANTSIMediumController;

public interface ANTSIRayController extends ANTSIController
{
	///////////
	//SETTERS//
	///////////
	
	public boolean setCurrentMedium(ANTSIMediumController c);
	
	///////////
	//GETTERS//
	///////////
	
	public ANTSIMediumController getCurrentMedium();
	public double getRefractionIndex();
	double getAngle();
	
	public double[] getCenter();
	
	public Point2D.Double[] getVector();
	public double[] getDirectionVector();
	
	///////////
	//SPECIAL//
	///////////
	
	public void addAngle(double angle);

	public void setAngle(double newRayAngle);
}
