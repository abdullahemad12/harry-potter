package harrypotter.model.character;
import harrypotter.model.world.*;

public class SlytherinWizard extends Wizard {
	
	// used from the package world
	private Direction traitDirection; //represents the direction of the move resulting from activating their trait.
	
	public SlytherinWizard(String name) {
		super(name);
	}
	
	public Direction getTraitDirection(){
		return traitDirection;
	}
	
	public void setTraitDirection(Direction traitDirection){
		this.traitDirection=traitDirection;
	}
	public void useTrait(){
		
	}
<<<<<<< HEAD

=======
>>>>>>> 555f87d8ed6747a6c76ff72b5865eb8541c013e8

}
