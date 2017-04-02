package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;

public class RavenclawWizard extends Wizard implements Champion {

	public RavenclawWizard(String name)
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
			getListener().onRavenclawTrait();
	}

}
