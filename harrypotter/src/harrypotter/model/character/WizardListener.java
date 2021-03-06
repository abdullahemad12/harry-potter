package harrypotter.model.character;


import java.io.IOException;
import java.util.EventListener;

import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.world.Direction;

//Interface containing the methods needed for the wizard listeners.
public interface WizardListener extends EventListener {
	public void onGryffindorTrait();
	public void onSlytherinTrait(Direction d) throws IOException, OutOfBordersException , InvalidTargetCellException  ;
	public void onHufflepuffTrait();
	public Object onRavenclawTrait();




}
