package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;

public class HufflepuffWizard extends Wizard implements Champion{

	public HufflepuffWizard(String name)
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
			getListener().onHufflepuffTrait();
	}

}
