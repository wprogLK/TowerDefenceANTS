import javax.swing.JFrame;


public class BezierMain extends JFrame
{
	public BezierMain(String title)
	{
		super(title);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		BezierMain main = new BezierMain("TechDemo BezierCurves");
		main.setSize(1000, 800);
		
		main.setVisible(true);

	}

}
