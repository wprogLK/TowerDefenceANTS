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
	
	public double getRefractionIndex();
	double getAngle();
	
	public double[] getCenter();
	
	public Point2D.Double[] getVector();
	///////////
	//SPECIAL//
	///////////
	
	public void addAngle(double angle);
}
