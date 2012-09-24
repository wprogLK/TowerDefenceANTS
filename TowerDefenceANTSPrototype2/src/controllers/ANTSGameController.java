package controllers;

import java.awt.Color;

import basics.ANTSFactory;

import views.ANTSGameView;

import models.ANTSGameModel;

import interfaces.ANTSIController;
import interfaces.ANTSIModel;
import interfaces.ANTSIView;

public class ANTSGameController extends ANTSAbstractController implements ANTSIController
{
	private ANTSGameModel model;
	private ANTSGameView view;
	private ANTSFactory factory;
	
	//Grid Config
	private int xCells = 10;
	private int yCells = 10;
	
	public ANTSGameController(ANTSFactory factory)
	{
		super();
		
		this.model = new ANTSGameModel(factory);
		this.view = new ANTSGameView(this.model);
		
		this.factory = factory;
		this.iview = view;
		this.setIModel(this.model);
		
		this.initGame();
	}
	
	private void initGame() 
	{
		this.createGrid();
//		createSimpleSourceLight();
		createSimpleSourceLightNeon();
//		createSimpleSourceLight2();
//		createSimpleSourceLight3();
//		createSimpleTestAnt1();
		createSimpleMedium1();
//		createSimpleMedium2();
		
		createSimpleLens();
	}

	////////////
	//CREATERS//
	////////////

	private void createSimpleLens() 
	{
		this.factory.createSimpleLens(300, 300, 100, 0.1, true);
	}

	private void createGrid()
	{
		this.factory.createGrid(xCells, yCells);
	}
	
	public void createSimpleSourceLight()
	{
		this.factory.createSimpleSourceLight(200,200,60,Color.black,true);
//		ANTSSwitchLightListener.addLight((ANTSSimpleSourceLightModel) c.getModel());	//only for testing
	}

	public void createSimpleSourceLight2()
	{
		this.factory.createSimpleSourceLight(20,20,20,Color.blue,false);
	}
	
	public void createSimpleSourceLight3()
	{
		this.factory.createSimpleSourceLight(6,5,20,Color.RED,true);
	}
	
	private void createSimpleSourceLightNeon() 
	{
		this.factory.createSimpeSourceLigthNeon(300, 200, 100, Color.red, true);
		
	}
	
	public void createSimpleTestAnt1()
	{
//		this.factory.createSimpleTestAnt1(60,50,320,320,Color.RED,true);
	}
	
	private void createSimpleMedium1() 
	{
		this.factory.createSimpleMedium(100, 100, 100, 100, 2, true);
	}
	
	private void createSimpleMedium2() 
	{
		this.factory.createSimpleMedium(300, 300, 100, 200, 2, true);
	}
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public ANTSIModel getModel()
	{
		return this.model;
	}
	
	public ANTSGameView getView()
	{
		return this.view;
	}
	
	@Override
	public ANTSIView getIView()
	{
		return this.view;
	}
}
