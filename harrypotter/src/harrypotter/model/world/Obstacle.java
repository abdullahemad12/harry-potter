package harrypotter.model.world;

/*
 * abstract super class representing all kinds of obstacles in general
 */
abstract class Obstacle {
	
	// health points
	private int hp; 


	/*
	 * default constructor
	 */
	Obstacle(){
		hp = 0;

	}
	
	/*
	 * creates object of type Obstacle 
	 */
	Obstacle(int hp)
	{
		this.hp = hp;
	}
	
	/*
	 * returns the value of hp
	 */
	public int getHp()
	{
		return this.hp;
	}
	

}