package harrypotter.controller;

import harrypotter.model.tournament.Tournament;

public class Task1GUI extends TaskGUI{

	public Task1GUI(Tournament tournament) {
		super(tournament);
		UpdateCurrentChamp(tournament.getFirstTask());
		setVisibility(true);
	}
	
	

}