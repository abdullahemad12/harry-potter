package harrypotter.controller;

import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Direction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Task2GUI  extends TaskGUI implements ActionListener {
	public Task2GUI(Tournament tournament) {
		super(tournament);
		
		setObst("img/merperson.png");
		
		UpdateCurrentChamp(tournament.getSecondTask());
		
		super.initializeMap();
		
		setVisibility(true);
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
		}
		
	}

}