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

	public Obstacle getObstacle(){
		return obstacle;
	}

	public static void main(String[] args)
	{
		Obstacle  ob = new PhysicalObstacle(200);
		System.out.println(ob.getHp());
		ObstacleCell c = new ObstacleCell(ob);
		System.out.println(c.getObstacle().getHp());
	}
}

