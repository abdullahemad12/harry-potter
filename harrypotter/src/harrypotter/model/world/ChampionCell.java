package harrypotter.model.world;

/*
 * represents cells that  currently have a champion positioned in them
 */
public class ChampionCell extends Cell {
	private Champion champ;
	
	/*
	 * Creates object of champion cell
	 */
	public ChampionCell(champion champ){
		this.champ = champ;
	}
	
	

}
