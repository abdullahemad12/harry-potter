package harrypotter.model.world;

import harrypotter.model.character.Champion;

/*
 * Treasure cells are the ones containing the treasure belonging to each champion
 * in the second task.
 */
public class TreasureCell extends Cell {
	
	private Champion owner;
	
	public TreasureCell(Champion owner)
	{
		this.owner = owner;
	}
	
	public Champion getOwner(){
		return owner;
	}

}
