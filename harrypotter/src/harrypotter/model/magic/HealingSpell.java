package harrypotter.model.magic;

//A spell that the champion casts to recover his hp by a specic amount.

public class HealingSpell extends Spell {
	private int healingAmount;
	//represents the amount that this spell will restore to the champion's hp.
	
	public HealingSpell(String name, int cost, int defaultCoolDown, int healingAmount){
		super(name,cost,defaultCoolDown);
		this.healingAmount=healingAmount;
	}
	
	public int getHealingAmount(){
		return healingAmount;
	}
	
	public String SpellType()
	{
		return "Healing Spell";
	}

}
