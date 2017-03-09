package harrypotter.model.tournament;

import harrypotter.model.character.Champion;

import java.util.ArrayList;
import java.util.EventListener;
import java.io.IOException;

public interface TaskListener extends EventListener {

	public void onFinishingFirstTask(ArrayList<Champion>winners) throws IOException;
	public void onFinishingSecondTask(ArrayList<Champion>winners)throws IOException;
	public void onFinishingThirdTask(Champion winner) throws IOException;
}
