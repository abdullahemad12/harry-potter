package harrypotter.model.character;

import java.util.EventListener;

import harrypotter.model.world.Direction;

//Interface containing the methods needed for the wizard listeners.
public interface WizardListener extends EventListener {
	public void onGryffindorTrait();
	public void onSlytherinTrait(Direction d);
	public void onHufflepuffTrait();
	public Object onRavenclawTrait();

}