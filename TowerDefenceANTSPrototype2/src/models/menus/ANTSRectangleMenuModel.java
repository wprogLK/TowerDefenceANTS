package models.menus;

import interfaces.ANTSIModel;
import interfaces.menus.ANTSIMenuModel;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

import controllers.menus.ANTSMenuItemRectangleController;

import models.ANTSAbstractModel;

import basics.ANTSFactory;

public class ANTSRectangleMenuModel extends ANTSAbstractModel implements ANTSIModel, ANTSIMenuModel
{
	private AffineTransform matrix;
	private double minWidth;
	
	private ArrayList<ANTSMenuItemRectangleController> menuItems;
	
	
	public ANTSRectangleMenuModel(double posX, double posY, double minWidth, ANTSFactory factory )
	{
		super(factory);
		
		this.matrix = new AffineTransform();
		this.matrix.translate(posX, posY);
		
		this.minWidth = minWidth;
		
		this.isMouseListener = true;
		this.mouseEntered = false;
		this.menuItems = new ArrayList<ANTSMenuItemRectangleController>();
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	///////////
	//Getters//
	///////////
	
	///////////
	//Setters//
	///////////
	
	///////////
	//Special//
	///////////
	
	public String toString()
	{
		return "rectangle menu";
	}

	public AffineTransform getMatrix()
	{
		return this.matrix;
	}
	
	public double getMinWidth()
	{
		return this.minWidth;
	}

	public ANTSFactory getFactory() 
	{
		return this.factory;
	}

	public void addNewMenuItem(ANTSMenuItemRectangleController c) 
	{
		this.menuItems.add(c);
		
		int index = this.menuItems.size();
		
		c.setMenuItemIndex(index);
	}
	
	public Iterator<ANTSMenuItemRectangleController> getIteratorMenuItems()
	{
		return this.menuItems.iterator();
	}

	public int getMaxIndexMenuItem() 
	{
		return this.menuItems.size();
	}
	
	
	///////////
	//SPECIAL//
	///////////
	
}
