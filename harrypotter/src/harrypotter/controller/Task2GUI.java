package harrypotter.controller;

import harrypotter.model.tournament.Tournament;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
		if(e.getSource() instanceof JButton)
		{
			super.actionPerformed(e);
		}
	}

}
