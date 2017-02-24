package harrypotter.model.character;
import java.awt.*;

/*
 * A class representing a wizard character
 */
abstract public class Wizard {
	public String name; //The wizards name
	
	public int defaultHp; // The default health points of the wizard
	
	public int defaultIp; // The default intelligence points of the wizard
	
	public int hp; // the actual health points of the wizard
	
	public int ip; // Intelligence points of the wizard
	
	private Spell[] spells; // the list of the Wizard's currently chosen spells
	
	private Collectible[] inventory; // The list of the wizard’s belongings that he gathers
	
	Point location; //A point representing the wizard’s location in the map.
	
	int traitCooldown; //The amount of turns the champion needs to wait before activating his house trait again.
	
	/*
	 * default constructor
	 */
	public Wizard()
	{
		this.name = "unknow";
		hp = defaultHp;
		ip = defaultIp;
	}
	/*
	 * Constructor that initializes a Wizard object.
	 */
	public Wizard(String name){
		
		// checks for which type of wizard is this and assigns the correct default HP and IP
		if(this instanceof GryffindorWizard)
		{
			this.defaultHp = 900;
			this.defaultIp = 500;
		}
		else if(this instanceof HufflepuffWizard)
		{
			this.defaultHp = 1000;
			this.defaultIp = 450;
		}
		else  if(this instanceof RavenclawWizard)
		{
			this.defaultHp = 750;
			this.defaultIp = 700;
		}
		else
		{
			this.defaultHp = 850;
			this.defaultIp = 550;
		}
		this.name = name;
		// sets hp and ip to default initially
		hp = defaultHp;
		ip = defaultIp;
		this.traitCooldown = 0;
	}


}