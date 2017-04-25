package harrypotter.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.Timer;

import codeproject.jimagecomponent.javax.swing.ImageBuffer;
import codeproject.jimagecomponent.javax.swing.JImageComponent;

import harrypotter.model.tournament.Tournament;

public class Task1GUI extends TaskGUI implements ActionListener{
	
	private ImageBuffer eggs;
	private ImageBuffer fire;
	
	public Task1GUI(Tournament tournament) {
		super(tournament);
		
		// sets the custom icons for this task
		setObst("img/physicalobstacle.png");
		
		eggs = new ImageBuffer("img/egg.png", getWidth(), getHeight(), -1);
		fire = new ImageBuffer("img/fire.png", getWidth(), getHeight(), -1);

		
		
		
		/*
		 * shows the loading window while the game is still setting the images
		 */
		
		
		UpdateCurrentChamp(tournament.getFirstTask());
		initializeMap();
		// closes the window after the game is successfully loaded
		setVisibility(true);
		loading.setVisibility(false);
	}
	
	void UpdateMap()
	{
		super.UpdateMap();
		eggs.setImageComponent(getTaskview().getMap()[4][4]);
		getTaskview().revalidate();
		getTaskview().repaint();
		
	}
	void initializeMap() 
	{
		super.initializeMap();
		// sets the center of the map to an egg picture
		eggs.setImageComponent(getTaskview().getMap()[4][4]);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see harrypotter.controller.TaskGUI#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() instanceof JButton)
		{
			JImageComponent[][] map = getTaskview().getMap();
			ArrayList<Point> targetCells = new ArrayList<Point>( getTournament().getFirstTask().getMarkedCells()) ;

			super.actionPerformed(e);
			eggs.setImageComponent(map[4][4]);
			if (super.isFireFlag()){
				try {
					fire(targetCells);
					super.setFireFlag(false);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			 int delay = 1000; //milliseconds
			  ActionListener taskPerformer = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) 
			      {
			    	  UpdateMap();
			      }
			  };
			  new Timer(delay, taskPerformer).start();
			
		}
	}
	
	public void fire(ArrayList<Point> targetCells ) throws InterruptedException
	{
  		JImageComponent[][] map = getTaskview().getMap();
    	for(Point p : targetCells)
  		{
  			fire.setImageComponent(map[p.x][p.y]);
  		}
  		getTaskview().revalidate();
  		getTaskview().repaint();
		
		
	}
	
	
	
	
}