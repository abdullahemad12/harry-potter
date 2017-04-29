package harrypotter.model.magic;

//A class representing an item that can be collected by the champions.

public abstract class Collectible {
	private String name;
	
	public Collectible(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

}
