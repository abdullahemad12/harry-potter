package harrypotter.model.world;

import harrypotter.model.magic.*;

/*
 * Collectible cells have collectible items of type Collectible in them.
 */
public class CollectibleCell extends Cell {
	
	private Collectible collectible;
	
	/*
	 *	creates object of CollectibleCell
	 */
	public CollectibleCell(Collectible collectible){
		this.collectible = collectible; 
	}
	
	public Collectible getCollectible(){
		return collectible;
	}
	
	

}
