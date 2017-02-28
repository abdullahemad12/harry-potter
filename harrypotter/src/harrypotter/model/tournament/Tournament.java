package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Class representing the whole game play i.e. the Triwizard Tournament.

public class Tournament {
	private ArrayList<Champion> champions;
	//Represents the champions competing in the tournament.
	
	private ArrayList<Spell> spells;
	//List of spells available for champions to choose from throughout the whole tournament.
	
	private FirstTask firstTask;
	//The rst task of the tournament.
	
	private SecondTask secondTask;
	//The second task of the tournament.
	
	private ThirdTask thirdTask;
	//The third task of the tournament.
	
	public Tournament() throws Exception{
		champions =new ArrayList<Champion>();
		spells= new ArrayList<Spell>();
		loadSpells("Spells.csv");
		firstTask = new FirstTask(champions);
		secondTask = new SecondTask(champions);
		thirdTask = new ThirdTask(champions);

	}
	private void loadSpells(String filePath) throws IOException{
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String [] spell= currentLine.split(",");
			if (spell[0].equals("DMG"))
				this.spells.add(new DamagingSpell(spell[1], Integer.parseInt(spell[2]), Integer.parseInt(spell[4]), Integer.parseInt(spell[3])));
			else
				if(spell[0].equals("HEL"))
						this.spells.add(new HealingSpell(spell[1], Integer.parseInt(spell[2]), Integer.parseInt(spell[4]), Integer.parseInt(spell[3])));
				else
					this.spells.add(new RelocatingSpell(spell[1], Integer.parseInt(spell[2]), Integer.parseInt(spell[4]), Integer.parseInt(spell[3])));
		}
		br.close();
	}
	
	
	public ArrayList<Champion> getChampions(){
		return champions;
	}
	public FirstTask getFirstTask(){
		return firstTask;
	}
	public SecondTask getSecondTask(){
		return secondTask;
	}
	public ThirdTask getThirdTask(){
		return thirdTask;
	}
	
	public ArrayList<Spell> getSpells(){
		return spells;
				
	}

}
