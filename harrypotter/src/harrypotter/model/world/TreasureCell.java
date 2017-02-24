package harrypotter.model.world;

/*
 * Treasure cells are the ones containing the treasure belonging to each champion
 * in the second task.
 */
public class TreasureCell extends Cell {
	
	private Champion owner;
	
	public TreasureCell(champion owner)
	{
		this.owner = owner;
	}

}
