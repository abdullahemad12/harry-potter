package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Potion;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;



import harrypotter.model.world.TreasureCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;

public class SecondTask extends Task {
	
	private ArrayList<Champion> winners; // An array list containing the winners of this task
	
	public SecondTask(ArrayList<Champion> champions)throws IOException{
		super(shuffleHelper(champions));
		winners = new ArrayList<Champion>();
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
	
	//moving the currentChamp one cell up
		public void moveForward() throws IOException {
			//getting old point
			Point p= ((Wizard)getCurrentChamp()).getLocation();
			// moving it up
			p.translate(0, 1);
			//checking if it is possible to move
			if (getMap()[p.x][p.y] instanceof EmptyCell || getMap()[p.x][p.y] instanceof CollectibleCell ||(getMap()[p.x][p.y] instanceof TreasureCell && ((TreasureCell)getMap()[p.x][p.y]).getOwner().equals(getCurrentChamp()))){
				//changing ip after collecting the collectible
				if (getMap()[p.x][p.y] instanceof CollectibleCell){
					int amount =((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()).getAmount();
					int newIp= amount + ((Wizard)getCurrentChamp()).getIp();
					((Wizard)getCurrentChamp()).setIp(newIp);
				}
				//declaring winning champ as winner 
				if (getMap()[p.x][p.y] instanceof TreasureCell){
					winners.add(getCurrentChamp());
				}
				//changing map cell type
				Point oldP= ((Wizard)getCurrentChamp()).getLocation();
				getMap()[oldP.x][oldP.y]= new EmptyCell();
				getMap()[p.x][p.y]= new ChampionCell(getCurrentChamp());
				//changing champs location
				((Wizard)getCurrentChamp()).setLocation(p);
			}
			finalizeAction();
		}
		
		//moving the currentChamp one cell down
		public void moveBackward() throws IOException {
			Point p= ((Wizard)getCurrentChamp()).getLocation();
			p.translate(0, -1);
			if (getMap()[p.x][p.y] instanceof EmptyCell || getMap()[p.x][p.y] instanceof CollectibleCell||(getMap()[p.x][p.y] instanceof TreasureCell && ((TreasureCell)getMap()[p.x][p.y]).getOwner().equals(getCurrentChamp()))){
				if (getMap()[p.x][p.y] instanceof CollectibleCell){
					int amount =((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()).getAmount();
					int newIp= amount + ((Wizard)getCurrentChamp()).getIp();
					((Wizard)getCurrentChamp()).setIp(newIp);
				}
				if (getMap()[p.x][p.y] instanceof TreasureCell){
					winners.add(getCurrentChamp());
				}
				Point oldP= ((Wizard)getCurrentChamp()).getLocation();
				getMap()[oldP.x][oldP.y]= new EmptyCell();
				getMap()[p.x][p.y]= new ChampionCell(getCurrentChamp());
				((Wizard)getCurrentChamp()).setLocation(p);
			}
			finalizeAction();
		}
		
		//moving the currentChamp one cell left
		public void moveLeft() throws IOException {
			Point p= ((Wizard)getCurrentChamp()).getLocation();
			p.translate(-1, 0);
			if (getMap()[p.x][p.y] instanceof EmptyCell || getMap()[p.x][p.y] instanceof CollectibleCell||(getMap()[p.x][p.y] instanceof TreasureCell && ((TreasureCell)getMap()[p.x][p.y]).getOwner().equals(getCurrentChamp()))){
				if (getMap()[p.x][p.y] instanceof CollectibleCell){
					int amount =((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()).getAmount();
					int newIp= amount + ((Wizard)getCurrentChamp()).getIp();
					((Wizard)getCurrentChamp()).setIp(newIp);
				}
				if (getMap()[p.x][p.y] instanceof TreasureCell){
					winners.add(getCurrentChamp());
				}
				Point oldP= ((Wizard)getCurrentChamp()).getLocation();
				getMap()[oldP.x][oldP.y]= new EmptyCell();
				getMap()[p.x][p.y]= new ChampionCell(getCurrentChamp());
				((Wizard)getCurrentChamp()).setLocation(p);
			}
			finalizeAction();
		}
		
		//moving the currentChamp one cell right
		public void moveRight() throws IOException {
			Point p= ((Wizard)getCurrentChamp()).getLocation();
			p.translate(1, 0);
			if (getMap()[p.x][p.y] instanceof EmptyCell || getMap()[p.x][p.y] instanceof CollectibleCell||(getMap()[p.x][p.y] instanceof TreasureCell && ((TreasureCell)getMap()[p.x][p.y]).getOwner().equals(getCurrentChamp()))){
				if (getMap()[p.x][p.y] instanceof CollectibleCell){
					int amount =((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()).getAmount();
					int newIp= amount + ((Wizard)getCurrentChamp()).getIp();
					((Wizard)getCurrentChamp()).setIp(newIp);
				}
				if (getMap()[p.x][p.y] instanceof TreasureCell){
					winners.add(getCurrentChamp());
				}
				Point oldP= ((Wizard)getCurrentChamp()).getLocation();
				getMap()[oldP.x][oldP.y]= new EmptyCell();
				getMap()[p.x][p.y]= new ChampionCell(getCurrentChamp());
				((Wizard)getCurrentChamp()).setLocation(p);
			}
			finalizeAction();
		}
		public void onSlytherinTrait(Direction d) throws IOException {
			super.onSlytherinTrait(d);
			((Wizard)getCurrentChamp()).setTraitCooldown(4);
			finalizeAction();
		}
		
		public Object onRavenclawTrait(){
			((Wizard)getCurrentChamp()).setTraitCooldown(7);
			setTaitActivated(true);
			//getting location of treasure cell
			Point treloc = new Point();
			for (int i=0; i<10; i++){
				for (int j=0; j<10; j++){
					if (getMap()[i][j] instanceof TreasureCell && ((TreasureCell)getMap()[i][j]).getOwner()==getCurrentChamp()){
						treloc.setLocation(i, i);
						break;
					}
						
				}
			}
			ArrayList<Direction> location = new ArrayList<Direction>();
			Point p= ((Wizard)getCurrentChamp()).getLocation();
			// checking the location of the treasure cell relative to the champions cell and adding it to the array location 
			if (treloc.x-p.x>0)
				location.add(Direction.RIGHT);
			else
				if (treloc.x-p.x<0)
					location.add(Direction.LEFT);
			
			if (treloc.y-p.y>0)
				location.add(Direction.FORWARD);
			else
				if (treloc.y-p.y<0)
					location.add(Direction.BACKWARD);
			
			return location;
		}	
		

}
