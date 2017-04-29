package harrypotter.controller;

import harrypotter.model.character.Champion;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Direction;
import harrypotter.view.TaskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SecondTaskGUI  extends TaskGUI implements ActionListener {
	
	private TaskListener listener;
	public SecondTaskGUI(Tournament tournament) {
		super(tournament);
		
		setObst("img/merperson.png");
		
		UpdateCurrentChamp(tournament.getSecondTask());
		
		super.initializeMap();
		
		setVisibility(true);
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
		super.UpdateMap();
	}
	public void setTaskListener(TaskListener listener) 
	{
		this.setListener(listener);
		
	}
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
					JOptionPane.showMessageDialog(getTaskview(), "To reach your treasure you need to go: " + raven.get(0)+" and " + raven.get(1));
					
				}
			}
			UpdateMap();
			if(getTournament().getSecondTask().getChampions().isEmpty())
			{
				listener.onFinishingSecondTask();
			}
		}
		
	}
	// returns true if all the champions has died and false otherwise
	public boolean gameOver()
	{
		SecondTask task = getTournament().getSecondTask();
		ArrayList<Champion> players = task.getChampions();
		ArrayList<Champion> winners = task.getWinners();
		
		if(players.isEmpty() && winners.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
		UpdateMap();
		
	}

	public TaskListener getListener() {
		return listener;
	}

	public void setListener(TaskListener listener) {
		this.listener = listener;
	}



}
