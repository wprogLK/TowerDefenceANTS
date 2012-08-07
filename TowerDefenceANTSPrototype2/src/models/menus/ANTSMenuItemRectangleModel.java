package models.menus;

import interfaces.ANTSIModel;
import interfaces.menus.ANTSIMenuModel;

import java.awt.geom.AffineTransform;

import models.ANTSAbstractModel;

import basics.ANTSFactory;

public class ANTSMenuItemRectangleModel extends ANTSAbstractModel implements ANTSIModel, ANTSIMenuModel
{
	private String text;
	private int index;
	
	public ANTSMenuItemRectangleModel(String string, ANTSFactory factory )
	{
		super(factory);
		
		this.text = string;
		
		this.isMouseListener = true;
		this.mouseEntered = false;
	}
	
	/////////////////////
	//GETTERS & SETTERS//
	/////////////////////
	
	public String toString()
	{
		return "menu item : " + text;
	}
	
	public String getText()
	{
		return this.text;
	}

	public void setIndex(int index) 
	{
		this.index = index;
	}
	
	public int getIndex()
	{
		return this.index;
	}

	///////////
	//SPECIAL//
	///////////
	
}
