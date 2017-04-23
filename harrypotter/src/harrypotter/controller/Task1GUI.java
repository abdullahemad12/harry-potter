package harrypotter.controller;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;

import codeproject.jimagecomponent.javax.swing.JImageComponent;

import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.EmptyCell;
import harrypotter.view.loadingView;

public class Task1GUI extends TaskGUI{

	public Task1GUI(Tournament tournament) {
		super(tournament);
		/*
		 * shows the loading window while the game is still setting the images
		 */
		loadingView loading = new loadingView();
		loading.setVisibility(true);
		
		UpdateCurrentChamp(tournament.getFirstTask());
		initializeMap();
		// closes the window after the game is successfully loaded
		new WindowEvent(loading, WindowEvent.WINDOW_CLOSING);
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
	
		
		// sets the center of the map to an egg picture
		ImageIcon icon = new ImageIcon("img/egg.png") ;
		Image img = icon.getImage();
		Image newimg = ((Image) img).getScaledInstance(((getTaskview().getWidth()-405)/ 10), getTaskview().getHeight()/10,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon(newimg);
		
		map[4][4].setImage(icon);
		
	}
	
	
	
}
