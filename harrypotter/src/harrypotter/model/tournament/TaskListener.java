package harrypotter.model.tournament;

import harrypotter.model.character.Champion;

import java.util.ArrayList;
import java.util.EventListener;

public interface TaskListener extends EventListener{

	public void onFinishingFirstTask(ArrayList<Champion>winners);
	public void onFinishingSecondTask(ArrayList<Champion>winners);
	public void onFinishingThirdTask(Champion winner);
}
