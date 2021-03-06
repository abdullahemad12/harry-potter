package harrypotter.model.world;
/*
 * class represents all the physical obstacles faced by the character
 */
public class PhysicalObstacle extends Obstacle {
	
	/*
	 * takes the health points and creates object physical obstacle
	 */
	public PhysicalObstacle(int hp){
	
		super(hp);
	}
	
	/*
	 * returns the value of hp
	 */
	public int getHp(){
		return super.getHp();
	}
	
}
