package harrypotter.model.tournament;

import harrypotter.model.character.Champion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SecondTask extends Task {
	public SecondTask(ArrayList<Champion> champions)throws IOException{
		super(shuffleHelper(champions));
	}
	
	private static ArrayList<Champion> shuffleHelper(ArrayList<Champion> champions){
		Collections.shuffle(champions);
		return champions;
	}
	public void generateMap(){
		super.addplayers();
		super.addingpotions();
		super.task2();
				
	}


}
