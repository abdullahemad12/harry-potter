package harrypotter.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import codeproject.jimagecomponent.javax.swing.JImageComponent;

import harrypotter.model.character.Wizard;
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
		// the  location of the champ 
		Wizard CurrentChamp =  (Wizard)(getTournament().getTask().getCurrentChamp());
		Point p = CurrentChamp.getLocation();
		
		// sets the center of the map to an egg picture
		ImageIcon icon = new ImageIcon("img/egg.png") ;
		Image img = icon.getImage();
		Image newimg = ((Image) img).getScaledInstance(((getTaskview().getWidth()-405)/ 10), getTaskview().getHeight()/10,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon(newimg);
		
		map[4][4].setImage(icon);
		
	}
	
	
	
}
