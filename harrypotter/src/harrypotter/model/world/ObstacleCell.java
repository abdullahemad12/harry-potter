package harrypotter.model.world;

/*
 * Obstacle cells are the ones containing the obstacles that the champions may en-
 * counter during any task
 */
public class ObstacleCell extends Cell{
	
	private Obstacle obstacle;
	
	public ObstacleCell(Obstacle obstacle)
	{
		this.obstacle = obstacle;
	}

	

}