package interfaces;

public interface ANTSIDriver 
{
	public int getHeight();
	public int getWidth();
	public void addControllerToMouseListener(ANTSIController c);
	public void addControllerToKeyListener(ANTSIController c);
}
