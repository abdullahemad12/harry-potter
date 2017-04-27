package harrypotter.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.Timer;

import codeproject.jimagecomponent.javax.swing.ImageBuffer;
import codeproject.jimagecomponent.javax.swing.JImageComponent;
import harrypotter.model.character.Champion;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.tournament.Tournament;

public class FirstTaskGUI extends TaskGUI implements ActionListener{
	
	private ImageBuffer eggs;
	private ImageBuffer fire;
	private TaskListener listener;
	
	
	
	
	public FirstTaskGUI(Tournament tournament) {
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
	}
	
	public void setTaskListener(TaskListener listener)
	{
		this.listener = listener;
	}
	
	void UpdateMap()
	{
		super.UpdateMap();
		eggs.setImageComponent(getTaskview().getMap()[4][4]);
		getTaskview().revalidate();
		getTaskview().repaint();
		
		ArrayList<Champion> champion = getTournament().getFirstTask().getChampions();
		if(champion.isEmpty())
		{
			listener.onFinishingFirstTask();
		}
		
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
			
			
			// The ravenClaw trait for the first task 
			if(e.getSource() == getTaskview().getUseTrait() && getTournament().getTask().getCurrentChamp() instanceof RavenclawWizard)
			{
				super.actionPerformed(e);
				if(getTournament().getTask().getCurrentChamp() instanceof RavenclawWizard)
				{
					@SuppressWarnings("unchecked")
					ArrayList<Point> targetCells = (ArrayList<Point>) getTournament().getTask().onRavenclawTrait(); 
					fire(targetCells);
				}
			}
			
			
			// shows the fired cells when After the user moves right, left, up or dowm
			else /*if(e.getSource() == getTaskview().getRight() || e.getSource() == getTaskview().getLeft() ||
					e.getSource() == getTaskview().getUp() || e.getSource() == getTaskview().getDown())*/
			{
				JImageComponent[][] map = getTaskview().getMap();
				// targeted Cells
				ArrayList<Point> targetCells = new ArrayList<Point>( getTournament().getFirstTask().getMarkedCells()) ;
				
				
				
				boolean wiz = ((Wizard) getTournament().getTask().getCurrentChamp() instanceof HufflepuffWizard);
				boolean trac = getTournament().getTask().isTraitActivated();
				boolean allmov = getTournament().getTask().getAllowedMoves()==1;
	
				super.actionPerformed(e);
				eggs.setImageComponent(map[4][4]);
				
				// fires when needed
				if (super.isFireFlag() && !(wiz && trac) && allmov){
						fire(targetCells);
						super.setFireFlag(false);
				}
				
				// removes the fire image after two seconds
				 int delay = 2000; //milliseconds
				  ActionListener taskPerformer = new ActionListener() {
				      public void actionPerformed(ActionEvent evt) 
				      {
				    	  UpdateMap();
				      }
				  };
				  new Timer(delay, taskPerformer).start();
			}
//			else
//			{
//				super.actionPerformed(e);
//			}
				
		}
	}
	
	public void fire(ArrayList<Point> targetCells )
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