package harrypotter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;
import harrypotter.view.Startmenu2;
import harrypotter.view.Task1view;

public class Task1GUI implements ActionListener {
	
	private Tournament tournament;
	private Task1view Task1view;
	
	public Task1GUI(Tournament tournament)
	{
		this.tournament = tournament;
		Task1view = new Task1view();
		
		Task1view.addtextLU("");
		Task1view.addtextLU("Current champ");
		Task1view.addtextLU("");
		Task1view.addtextLU(((Wizard)(tournament.getFirstTask().getCurrentChamp())).getName());
		Task1view.addtextLU("");
		if(((Wizard)(tournament.getFirstTask().getCurrentChamp())) instanceof GryffindorWizard)
		{
			Task1view.addtextLU("Gryffindor Wizard");
		}
		else if(((Wizard)(tournament.getFirstTask().getCurrentChamp())) instanceof HufflepuffWizard)
		{
			Task1view.addtextLU("Hufflepuff Wizard");
		}
		else  if(((Wizard)(tournament.getFirstTask().getCurrentChamp())) instanceof RavenclawWizard)
		{
			Task1view.addtextLU("Ravenclaw Wizard");
		}
		else
		{
			Task1view.addtextLU("Slytherin Wizard");
		}
		
		Task1view.addtextLU("IP");
		Task1view.addtextLU("");
		Task1view.addtextLU(""+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getIp());
		
		Task1view.addtextLU("HP");
		Task1view.addtextLU("");
		Task1view.addtextLU(""+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getHp());
		
		Task1view.addtextLU("Trait Activated");
		Task1view.addtextLU("");
		Task1view.addtextLU(""+tournament.getFirstTask().isTraitActivated());
		
		Task1view.addtextLU("Remaining Moves");
		Task1view.addtextLU("");
		Task1view.addtextLU(""+""+tournament.getFirstTask().getAllowedMoves());
		
		Task1view.addtextLU("Spell 1");Task1view.addtextLU("Spell 2");Task1view.addtextLU("Spell 3");
		
		if(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0)) instanceof DamagingSpell)
		{
			Task1view.addtextLU("Damaging Spell");
		}
		else if(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0)) instanceof RelocatingSpell)
		{
			Task1view.addtextLU("Relocating Spell");
		}
		else  if(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0)) instanceof HealingSpell)
		{
			Task1view.addtextLU("Healing Spell");
		}
		
		if(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1)) instanceof DamagingSpell)
		{
			Task1view.addtextLU("Damaging Spell");
		}
		else if(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1)) instanceof RelocatingSpell)
		{
			Task1view.addtextLU("Relocating Spell");
		}
		else  if(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1)) instanceof HealingSpell)
		{
			Task1view.addtextLU("Healing Spell");
		}
		
		if(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2)) instanceof DamagingSpell)
		{
			Task1view.addtextLU("Damaging Spell");
		}
		else if(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2)) instanceof RelocatingSpell)
		{
			Task1view.addtextLU("Relocating Spell");
		}
		else  if(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2)) instanceof HealingSpell)
		{
			Task1view.addtextLU("Healing Spell");
		}
		
		Task1view.addtextLU(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0)).getName());
		Task1view.addtextLU(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1)).getName());
		Task1view.addtextLU(((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2)).getName());
		
		Task1view.addtextLU("Cost: "+((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0)).getCost());
		Task1view.addtextLU("Cost: "+((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1)).getCost());
		Task1view.addtextLU("Cost: "+((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2)).getCost());
		
		Task1view.addtextLU("Cool Down: "+((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0)).getCoolDown());
		Task1view.addtextLU("Cool Down: "+((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1)).getCoolDown());
		Task1view.addtextLU("Cool Down: "+((Spell)((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2)).getCoolDown());
		
		
		Task1view.setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
