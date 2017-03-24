package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;
import harrypotter.model.world.Cell;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;

public class SecondTask extends Task {
	
	private ArrayList<Champion> winners; // An array list containing the winners of this task
	
	public SecondTask(ArrayList<Champion> champions)throws IOException{
		super(shuffleHelper(champions));
	}
	/*
	 * checks if the champion encounters a mereperson and does damage for him in this case
	 */
	public void encounterMerPerson()
	{
		// gets the location of the current champion
		Champion c = getCurrentChamp();
		Point location = ((Wizard) c).getLocation();
		// x-y points of the champion in the array of cells 
		int x = (int)location.getX();
		int y = (int)location.getY();
		Cell cell[][] = this.getMap();
		
		// Checks for the adjacent cell whether it contains a merperson
		if(cell[x][y + 1] instanceof ObstacleCell)
		{
			// gets the mere person
			Merperson merperson = (Merperson)((ObstacleCell)cell[x][y + 1]).getObstacle();
			int damage = merperson.getDamage();
			int hp = ((Wizard) c).getHp();
			
			// removes the champion if he can't handle the damage
			if(hp - damage <= 0)
			{
				//removes him from the map and from the list of chamions
				cell[x][y] = new EmptyCell();
				this.getChampions().remove(c);
			}
			// does damage do the wizard
			else
			{
				((Wizard)c).setHp(hp - damage);
			}
			
		}
		// does exactly the same but for another cell
		if(cell[x + 1][y] instanceof ObstacleCell)
		{
			Merperson merperson = (Merperson)((ObstacleCell)cell[x + 1][y]).getObstacle();
			int damage = merperson.getDamage();
			int hp = ((Wizard) c).getHp();
			if(hp - damage <= 0)
			{
				cell[x][y] = new EmptyCell();
				this.getChampions().remove(c);
			}
			else
			{
				((Wizard)c).setHp(hp - damage);
			}
			
		}
		
		if(cell[x][y - 1] instanceof ObstacleCell)
		{
			Merperson merperson = (Merperson)((ObstacleCell)cell[x][y - 1]).getObstacle();
			int damage = merperson.getDamage();
			int hp = ((Wizard) c).getHp();
			if(hp - damage <= 0)
			{
				cell[x][y] = new EmptyCell();
				this.getChampions().remove(c);
			}
			else
			{
				((Wizard)c).setHp(hp - damage);
			}
			
		}

		if(cell[x - 1][y] instanceof ObstacleCell)
		{
			Merperson merperson = (Merperson)((ObstacleCell)cell[x - 1][y]).getObstacle();
			int damage = merperson.getDamage();
			int hp = ((Wizard) c).getHp();
			if(hp - damage <= 0)
			{
				cell[x][y] = new EmptyCell();
				this.getChampions().remove(c);
			}
			else
			{
				((Wizard)c).setHp(hp - damage);
			}
			
		}
		
		
	}
	
	/*
	 * Gets the list of winners
	 */
	public ArrayList<Champion> getWinners()
	{
		return winners;
	}
	
	/*
	 * Sets the list of winners
	 */
	public void setWinners(ArrayList<Champion> winners) {
		this.winners = winners;
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
