package interfaces;

public interface ANTSIRayController extends ANTSIController
{
	public void setCurrentMedium(ANTSIMediumController c);
	public double getRefractionIndex();
}
