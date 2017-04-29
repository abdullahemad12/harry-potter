package harrypotter.model.magic;

//A subclass of Collectible representing a potion that can be used by wizards to recover their ip

public class Potion extends Collectible {
	private int amount;
	//The value by which the potion recovers the champion's current ip
	
	public Potion(String name, int amount){
		super(name);
		this.amount=amount;
	}
	public int getAmount(){
		return amount;
	}
	
	public String toString()
	{
		return this.getName()+"   " + amount;
	}
}
