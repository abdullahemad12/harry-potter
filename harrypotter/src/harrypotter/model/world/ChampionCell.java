package harrypotter.model.world;

import harrypotter.model.character.Champion;

/*
 * represents cells that  currently have a champion positioned in them
 */
public class ChampionCell extends Cell {
	private Champion champ;
	
	/*
	 * Creates object of champion cell
	 */
	public ChampionCell(Champion champ){
		this.champ = champ;
	}
	public Champion getChamp(){
		return champ;
	}
	
	

}
