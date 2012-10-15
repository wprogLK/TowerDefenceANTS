import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;


public class BezierMain extends JFrame
{
//	private ArrayList<GeneralPath> paths;
	private ArrayList<BezierIPath> paths;
	private static double[] pointToDraw = {-1,-1};
	
	private double[] startPointRay ={130,150}; //{150,110};
	private double[] endPointRay = {300,170};//{160,200};
	private double[][] rayVector = {startPointRay,endPointRay};
	
	public BezierMain(String title)
	{
		super(title);
		
		this.bezierSetup();
		
		this.windowSetup();
		
		this.analyzePaths();
	}
	
	private void analyzePaths() 
	{
//		Iterator<GeneralPath> pathIterator = this.paths.iterator();
		
//		while(pathIterator.hasNext())
//		{
//			System.out.println("--------------Start path--------------------");
//			BezierPathIteratorUtil.foo(pathIterator.next().getPathIterator(new AffineTransform()));
//			System.out.println("---------------End path---------------------");
//		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		BezierMain main = new BezierMain("TechDemo BezierCurves");
	}
	
	private void bezierSetup() 
	{
		this.paths = new ArrayList<BezierIPath>();
		
		BezierIPath p = new BezierPath();
		
		p.addMoveTo(100, 100);
//		p.addLineTo(200, 200);
//		p.addQuadTo(150, 150, 300, 50);
//		p.addCurveTo(150, 150, 200, 250, 300, 50);
		p.addCurveTo(150, 150, 200, 250, 150, 70);
		
		this.paths.add(p);
		
	
		
//		this.paths = new ArrayList<GeneralPath>();
//		
////		GeneralPath simpleLinePath = new GeneralPath();
////		simpleLinePath.moveTo(25, 50);
////		simpleLinePath.lineTo(150, 50);
////		
////		this.paths.add(simpleLinePath);
//		
////		GeneralPath simpleQuadraticPath = new GeneralPath();
////		simpleQuadraticPath.moveTo(25, 50);
////		simpleQuadraticPath.quadTo(150, 150, 300, 50);
////		
////		
////		this.paths.add(simpleQuadraticPath);
//		
//		
//		GeneralPath simpleCubicPath = new GeneralPath();
//		simpleCubicPath.moveTo(25,50);
//		simpleCubicPath.curveTo(10, 110, 155, 130, 260, 300);
//		this.paths.add(simpleCubicPath);
	}

	private void windowSetup() 
	{
		this.setSize(1000, 800);
		
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
//		Iterator<GeneralPath> pathIterator = this.paths.iterator();
//		
//		while(pathIterator.hasNext())
//		{
//			g2d.draw(pathIterator.next());
//		}
		
		Iterator<BezierIPath> pathIterator = this.paths.iterator();
		
		while(pathIterator.hasNext())
		{
			BezierIPath currentPath = pathIterator.next();
			
			g2d.draw(currentPath);
			
			currentPath.drawSingleSegments(g2d);
			
			double[] intersectionPoint = currentPath.calculateIntersectionPoint(rayVector);
//			
			g2d.setColor(Color.RED);
			g2d.fill(new Rectangle2D.Double(intersectionPoint[0], intersectionPoint[1], 5, 5));
			g2d.drawString("Intersection Point ray ", (int) intersectionPoint[0], (int) intersectionPoint[1]);
		}
		
		g2d.setColor(Color.BLUE);
		
		Rectangle2D point = new Rectangle2D.Double(pointToDraw[0], pointToDraw[1], 5, 5);
		g2d.drawString("Intersection Point cubic ", (int) pointToDraw[0], (int) pointToDraw[1]);
		
		g2d.fill(point);
		
		g2d.draw(new Line2D.Double(this.startPointRay[0], this.startPointRay[1], this.endPointRay[0], this.endPointRay[1]));
		
	}
	
	public static void setPointToDraw(double[] p)
	{
		pointToDraw = p;
	}
}
