package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import game.Light;

public class LightView extends Light
{
	public LightView(int intensity, int x, int y)
	{
		super(intensity,x,y);
	}
	
	public void paint(Graphics g)
	{
	//	g.fillOval(x, y, 10, 10);
		
		paintLight(g);
	}
	
	private void paintLight(Graphics g)
	{				
		int r = 20;
		int d = 10;
		
		Graphics2D g2d=(Graphics2D)g; 
		
		Line2D.Double line = new Line2D.Double(x+d, y+d, x+r+d,y+r+d);
		Ellipse2D.Double oval = new Ellipse2D.Double(x-(r/2),y-(r/2),r,r);
		
		g2d.setPaint(Color.BLUE);
		
		AffineTransform aT = new AffineTransform();
		g2d.fill(oval);
		
		
		for(int i=0; i<45; i++)
		{
			//aT.rotate(i);
			aT.setToRotation(i, x, y); //rotation um punkt x;y
			g2d.setTransform(aT);
			g2d.setPaint(Color.RED);
			g2d.draw(line);
		}
		
		//TODO: eigene Klasse für Lichtstrahlen mit eigener Paint methode
		
		
		
	}
}
