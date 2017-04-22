package harrypotter.controller;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import codeproject.jimagecomponent.javax.swing.JImageComponent;

import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.EmptyCell;

public class Task1GUI extends TaskGUI{

	public Task1GUI(Tournament tournament) {
		super(tournament);
		UpdateCurrentChamp(tournament.getFirstTask());
		initializeMap();
		setVisibility(true);
	}
	
	
	void initializeMap() 
	{
		Cell[][] cells = getTournament().getFirstTask().getMap();
		JImageComponent[][] map = getTaskview().getMap();
		
		for(int i = 0; i  < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(!(cells[i][j] instanceof EmptyCell))
				{
					try 
					{
						 inferCell(i,j);
						
						//adds the image to the map
					} 
					catch (IOException e)
					{
						
						e.printStackTrace();
					}	
				}
			}
		}
	
		this.getTaskview().revalidate();
	}
}
