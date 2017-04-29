package harrypotter.controller;



import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import codeproject.jimagecomponent.javax.swing.ImageBuffer;
import codeproject.jimagecomponent.javax.swing.JImageComponent;
import harrypotter.model.character.Champion;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.tournament.ThirdTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.view.TaskView;

public class ThirdTaskGUI extends TaskGUI{
	
	
	private TaskListener listener;
	
	
	public ThirdTaskGUI(Tournament tournament)
	{
		super(tournament);
		setWall( new ImageBuffer("img/wallCell.png", getWidth(), getHeight(), 3)); 
		initializeMap();
		
	}
	
	/*
	 * Initializes the Map
	 */
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
					inferCell(cells[i][j], map[i][j]);	
				}
			}
		}
	
		
	}
	public void setTaskListener(TaskListener listener) 
	{
		this.listener = listener;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see harrypotter.controller.TaskGUI#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) 
	{
		super.actionPerformed(e);
		if(e.getSource() instanceof JButton)
		{
			if(e.getSource() == getTaskview().getUseTrait())
			{
				if(getTournament().getTask().getCurrentChamp() instanceof RavenclawWizard)
				{
					@SuppressWarnings("unchecked")
					ArrayList<Direction> raven = (ArrayList<Direction>) getTournament().getTask().onRavenclawTrait();
					JOptionPane.showMessageDialog(getTaskview(), "To reach the cup you need to go: " + raven.get(0)+" and " + raven.get(1));
					
				}
			}
		}
		
	}
	

	public void UpdateMap()
	{
		if(gameOver())
		{
			TaskView frame = getTaskview();
			JOptionPane.showMessageDialog(frame,
				    "Game Over",
				    "Inane error",
				    JOptionPane.ERROR_MESSAGE);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			
		}
		if(getTournament().getWinner() != null)
		{
			listener.onFinishingThirdTask(getTournament().getWinner());
		}
		super.UpdateMap();
	}
	public boolean gameOver()
	{
		ThirdTask task = getTournament().getThirdTask();
		ArrayList<Champion> players = task.getChampions();
		if(players.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	
	}
	


	


}
