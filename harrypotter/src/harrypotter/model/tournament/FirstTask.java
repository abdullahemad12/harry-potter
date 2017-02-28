package harrypotter.model.tournament;

import harrypotter.model.character.Champion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class FirstTask extends Task {
	public FirstTask(ArrayList<Champion> champions) throws IOException{
		super(shuffleHelper(champions));
		
	}
	
	//helper that shuffles the champions.
	private static ArrayList<Champion> shuffleHelper(ArrayList<Champion> champions){
		Collections.shuffle(champions);
		return champions;
	}
	
	public void generateMap(){
		super.addplayers();
		super.task1();
				
	}

}
