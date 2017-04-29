package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;
import harrypotter.model.tournament.*;

public class HufflepuffWizard extends Wizard implements Champion{

	public HufflepuffWizard(String name)
	{	
		super(name);
		
	}
	 public void useTrait()throws InCooldownException{
		 int temp= ((Wizard)this).getTraitCooldown();
		if (getListener() != null)
		{
			 if(!(getListener() instanceof ThirdTask))
			 {
				if(temp != 0 )
				{
					throw new InCooldownException(temp);
				}
			 }
				getListener().onHufflepuffTrait();
		}
	}

}
