package harrypotter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.*;
import harrypotter.model.tournament.Task;
import harrypotter.model.tournament.Tournament;
import harrypotter.view.*;

abstract public class TaskGUI implements ActionListener {
	
	private Tournament tournament;
	private TaskView taskview;
	
	public TaskGUI(Tournament tournament)
	{
		this.setTournament(tournament);
		taskview = new Task1view();
		taskview.getSpells().addActionListener(this);
	}

	/*
	 * updates all the current champion information for all tasks
	 */
	public void UpdateCurrentChamp(Task task)
	{
		// sets all the champions information 
		
		Wizard currentChamp = (Wizard)task.getCurrentChamp();
		
		
		taskview.setChampName(currentChamp.getName());
		// sets the Wizard class
		if(currentChamp instanceof GryffindorWizard)
		{
			taskview.setWizClass("Gryffindor Wizard");
		}
		else if(currentChamp instanceof HufflepuffWizard)
		{
			taskview.setWizClass("Hufflepuff Wizard");
		}
		else  if(currentChamp instanceof RavenclawWizard)
		{
			taskview.setWizClass("Ravenclaw Wizard");
		}
		else
		{
			taskview.setWizClass("Slytherin Wizard");
		}
		
		//sets ip, hp, traitactivated, remaining moves
		taskview.setIp(currentChamp.getIp());
		taskview.setHp(currentChamp.getHp());
		taskview.setTraitActivated(task.isTraitActivated());	
		taskview.setRemainingMoves(task.getAllowedMoves());
		
		// sets the combobox of the spells 
		ArrayList<Spell> spells = currentChamp.getSpells();
		for(Spell spell : spells)
		{
			taskview.addspell(spell);
		}
		
		// sets the selectedspell info
		UpdateSpells(spells.get(0));
		
	}
	
	/*
	 * updates the spell labels to the selected labels 
	 */
	protected void UpdateSpells(Spell selected)
	{
		taskview.setSpellName(selected.getName());
		taskview.setSpellCost(selected.getCost());
		taskview.setSpellcooldown(selected.getCoolDown());
		taskview.setSpellType(selected.SpellType());
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if the event was selecting a new spell from the comboBox
		JComboBox<Spell> spells = taskview.getSpells();
		if(e.getSource() == spells)
		{
			UpdateSpells((Spell) spells.getSelectedItem());
			taskview.revalidate();
		}
		
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	public void setVisibility(boolean visible)
	{
		taskview.setVisible(visible);
	}
	

}