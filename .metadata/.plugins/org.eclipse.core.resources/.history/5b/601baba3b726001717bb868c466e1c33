package harrypotter.model.magic;

//A class representing a spell that a champion can cast during the tournament.

public abstract class Spell {
	private String name;
	//The spell's name.
	private int cost;
	//The ip cost needed for casting the spell.
	private int defaultCooldown;
	//The amount of turns the champion needs to wait before being able to cast this specic spell again.
	private int coolDown;
	//The number of turns left for this spell to nish its cooldown.
	
	public Spell(String name, int cost, int defaultCoolDown){
		this.name=name;
		this.cost=cost;
		this.defaultCooldown=defaultCoolDown;
		this.coolDown=0;
	}
	
	public String getName(){
		return name;
	}
	public int getCost(){
		return cost;
	}
	public int getDefaultCooldown(){
		return defaultCooldown;
	}
	public int getCoolDown(){
		return coolDown;
	}
	public void setCoolDown(int coolDown){
		this.coolDown= coolDown;
	}

}
