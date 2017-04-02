package harrypotter.model.character;
import harrypotter.exceptions.*;
public class GryffindorWizard extends Wizard implements Champion {
	
	public GryffindorWizard(String name)
	{	
		super(name);
		
	}
    public void useTrait()throws InCooldownException{
    	int temp;
		if((temp = ((Wizard)this).getTraitCooldown()) != 0)
		{
			throw new InCooldownException(temp);
		}
    	if (getListener() != null)
    		getListener().onGryffindorTrait();
	}

}
