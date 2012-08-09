package models.menus;

import interfaces.ANTSIModel;
import interfaces.menus.ANTSIMenuModel;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

import controllers.menus.ANTSMenuItemCircleController;

import models.ANTSAbstractModel;

import basics.ANTSFactory;

public class ANTSCircleMenuModel extends ANTSAbstractModel implements ANTSIModel, ANTSIMenuModel
{
	private AffineTransform matrix;
	private double radius;
	
	private ArrayList<ANTSMenuItemCircleController> menuItems;
	
	
	public ANTSCircleMenuModel(double posX, double posY, double radius, ANTSFactory factory )
	{
		super(factory);
		
		this.matrix = new AffineTransform();
		this.matrix.translate(posX, posY);
		
		this.radius = radius;
		
		this.isMouseListener = true;
		this.mouseEntered = false;
		this.menuItems = new ArrayList<ANTSMenuItemCircleController>();
	}
	
	///////////
	//Getters//
	///////////
	
	public String toString()
	{
		return "circle menu";
	}

	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public double getRadius()
	{
		return this.radius;
	}

	public Iterator<ANTSMenuItemCircleController> getIteratorMenuItems()
	{
		return this.menuItems.iterator();
	}

	public int getMaxIndexMenuItem() 
	{
		return this.menuItems.size();
	}
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	
	public void addNewMenuItem(ANTSMenuItemCircleController c) 
	{
		this.menuItems.add(c);
		
		int index = this.menuItems.size();
		
		c.setMenuItemIndex(index);
	}
	
}
