package harrypotter.model.character;
import java.io.IOException;
import harrypotter.exceptions.*;

import harrypotter.exceptions.InCooldownException;
import harrypotter.model.world.*;

public class SlytherinWizard extends Wizard implements Champion{
	
	// used from the package world
	private Direction traitDirection; //represents the direction of the move resulting from activating their trait.
	
	public SlytherinWizard(String name) {
		super(name);
	}
	
	public Direction getTraitDirection(){
		return traitDirection;
	}
	
	public void setTraitDirection(Direction traitDirection){ //throws InvalidTargetCellException{
		this.traitDirection=traitDirection;
	}
	 public void useTrait()throws InCooldownException, IOException{
    	int temp;
		if((temp = ((Wizard)this).getTraitCooldown()) != 0)
		{
			throw new InCooldownException(temp);
		}		
		if (getListener() != null)
			getListener().onSlytherinTrait(traitDirection);
	}


}
