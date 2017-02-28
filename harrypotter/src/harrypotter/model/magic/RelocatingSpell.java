package harrypotter.model.magic;

//A spell that relocates another player or obstacle within a given range.

public class RelocatingSpell extends Spell {
	private int range;
	//The range represents the maximum number of cells that this spell can move objects to relative to their initial location.
	
	public RelocatingSpell(String name, int cost, int defaultCoolDown, int range){
		super(name,cost,defaultCoolDown);
		this.range=range;
	}
	public int getRange(){
		return range;
	}

}
