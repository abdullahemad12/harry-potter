package harrypotter.model.world;

/*
 * This class represents a Merperson that can cause damage to the character  
 */
public class Merperson extends Obstacle {
	
	private int damage;
	
	/*
	 * constructor that creates object of type Merperson
	 */
	public Merperson(int hp, int damage){
	
		super(hp);
		this.damage = damage;
	}
	/*
	 * returns value of damage
	 */
	public int getDamage()
	{
		return this.damage;
	}
	/*
	 * returns value of hp
	 */
	public int getHp()
	{
		return super.getHp();
	}

}
