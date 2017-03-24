package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;
import harrypotter.model.character.WizardListener;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.Cell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.TreasureCell;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


//import javafx.scene.control.Cell;



//A class representing a tournament task. 

public abstract class Task implements WizardListener {
	private Random randomGenerator;
	
	private ArrayList<Champion> champions;
	//Represents the champions competing in this task.
	
	private Champion currentChamp;
	//Represents the champion whose turn it currently is in this task.
	
	private Cell[][] map;
	//A 2D array representing the map that the task is taking place in.
	
	private int allowedMoves;
	//Indicates the number of moves allowed for the currentchamp in the current turn.
	
	private boolean traitActivated;
	//Indicates whether the currentchamp's trait is activated in the current turn or not.
	
	private ArrayList<Potion> potions;
	//List of potions available to be distributed in the maps of the three tasks.
	
	private TaskListener listener;
	//This attribute represents the instance of the TaskListener added to the class.
	
	public Task(ArrayList<Champion> champions)  throws IOException{
		this.champions=champions;
		allowedMoves=1;
		traitActivated= false;
		map = new Cell[10][10];
		this.potions = new ArrayList <Potion>();
		loadPotions("Potions.csv");
		generateMap();
	}
	public TaskListener getListener() {
		return listener;
	}
	public void setListener(TaskListener listener) {
		this.listener = listener;
	}
	public void setTraitActivated(boolean traitActivated)
	{
		this.traitActivated = traitActivated;
	}
	public ArrayList<Champion> getChampions(){
		return champions;
	}
	
	public Champion getCurrentChamp(){
		return currentChamp;
	}
	
	public void setCurrentChamp(Champion currentChamp){
		this.currentChamp=currentChamp;
	}
	
	public Cell[][] getMap(){
		return map;
	}
	
	public int getAllowedMoves(){
		return allowedMoves;
	}
	
	public void setAllowedMoves(int allowedMoves){
		this.allowedMoves=allowedMoves;
	}
	
	public boolean isTraitActivated(){
		return traitActivated;
	}
	
	public void setTaitActivated(boolean traitActivated){
		this.traitActivated=traitActivated;
	}
	
	public ArrayList<Potion> getPotions(){
		return potions;
	}
	
	//loading the potions from the csv file.
	private void loadPotions(String filePath) throws IOException{
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String [] potion= currentLine.split(",");
			potions.add(new Potion(potion[0],Integer.parseInt(potion[1])));
		}
		br.close();

	}
	

	// adding players.
	void addplayers(){
		if (champions.size()>0){
			map[9][0]= new ChampionCell(this.champions.get(0));
			((Wizard) champions.get(0)).setLocation(new Point(9, 0));}
		if (champions.size()>1){
			map[9][9]= new ChampionCell(this.champions.get(1));
			((Wizard) champions.get(1)).setLocation(new Point(9, 9));}
		if (champions.size()>2){
			map[0][9]= new ChampionCell(this.champions.get(2));
			((Wizard) champions.get(2)).setLocation(new Point(0, 9));}
		if (champions.size()>3){
			map[0][0]= new ChampionCell(this.champions.get(3));
			((Wizard) champions.get(3)).setLocation(new Point(0, 0));}
		
	}	

	//adding potions method.
	void addingpotions(){
		for (int i=0; i<10;i++){
			randomGenerator = new Random();
			int x=randomGenerator.nextInt(10);
			int y=randomGenerator.nextInt(10);
			if (this.map[x][y]!=null)
				i--;
			else{
				int index =randomGenerator.nextInt(potions.size());
				map[x][y]= new CollectibleCell(potions.get(index));
			}
		
		}
	}
	
	//properties of task 1.
	void task1(){
		randomGenerator = new Random();
		
		for (int i=0; i<40;i++){
			int x=randomGenerator.nextInt(10);
			int y=randomGenerator.nextInt(10);
			this.map[4][4]=new EmptyCell();
			if (this.map[x][y]!=null||(x==4&&y==4))
				i--;
			else{
				map[x][y]= new ObstacleCell(new PhysicalObstacle(randomGenerator.nextInt(101)+200));
			}
		
		}
		for (int i=0; i<10;i++){
			int x=randomGenerator.nextInt(10);
			int y=randomGenerator.nextInt(10);
			if (this.map[x][y]!=null||(x==4&&y==4))
				i--;
			else{
				int index =randomGenerator.nextInt(potions.size());
				map[x][y]= new CollectibleCell(potions.get(index));
			}
		
		}
		for (int i = 0; i < 10; i++)
		   {
			for (int j = 0; j < 10; j++)
		      { if (map[i][j]==null)
		    	  map[i][j]=new EmptyCell();
				
		      }
		   }	
	
	}
	
	//properties of task 2.
	void task2(){

		
		for (int i=0;i<champions.size();i++){
			randomGenerator = new Random();
			int x=randomGenerator.nextInt(10);
			int y=randomGenerator.nextInt(10);
			if (this.map[x][y]!=null)
				i--;
			else{
				map[x][y]= new TreasureCell(champions.get(i));
			}
		}
		for (int i=0; i<40;i++){
			randomGenerator = new Random();
			int x=randomGenerator.nextInt(10);
			int y=randomGenerator.nextInt(10);
			if (this.map[x][y]!=null)
				i--;
			else{
				map[x][y]=new ObstacleCell( new Merperson(randomGenerator.nextInt(101)+200, randomGenerator.nextInt(201)+100));
			}
		
		}
		for (int i = 0; i < 10; i++)
		   {
			for (int j = 0; j < 10; j++)
		      { if (map[i][j]==null)
		    	  map[i][j]=new EmptyCell();
				
		      }
		   }	
	
		
	}
	
	//This method is responsible for finalizing the action of casting a spell.
	// This method should be called at the end of any spell casting method.
	public void useSpell(Spell s){
		s.setCoolDown(s.getDefaultCooldown());
		int newIp = ((Wizard)getCurrentChamp()).getIp()-s.getCost();
		((Wizard)getCurrentChamp()).setIp(newIp);
	}
	
	public void castRelocatingSpell(RelocatingSpell s,Direction d,Direction t,int r){
		// getting old location
		Point ObsLoc= ((Wizard)getCurrentChamp()).getLocation();
		if (d==Direction.FORWARD)
			ObsLoc.translate(0, 1);
		if (d==Direction.BACKWARD)
			ObsLoc.translate(0, -1);
		if (d==Direction.RIGHT)
			ObsLoc.translate(1, 0);
		if (d==Direction.LEFT)
			ObsLoc.translate(-1, 0);
		
		//getting new location
		Point newloc= ((Wizard)getCurrentChamp()).getLocation();
		if (t==Direction.FORWARD)
			newloc.translate(0, r);
		if (t==Direction.BACKWARD)
			newloc.translate(0, -r);
		if (t==Direction.RIGHT)
			newloc.translate(r, 0);
		if (t==Direction.LEFT)
			newloc.translate(-r, 0);
		
		//swapping cells
		getMap()[newloc.x][newloc.y]=getMap()[ObsLoc.x][ObsLoc.y];
		getMap()[ObsLoc.x][ObsLoc.y]= new EmptyCell();
		useSpell(s);
		finalizeAction();
	}
	
	//This method is responsible for ending the turn of the currentChamp.
	public void endTurn(){
		//changing currentChamp.
		champions.remove(currentChamp);
		champions.add(champions.size()-1, currentChamp);
		currentChamp= champions.get(0);
	}
	
	//This method is responsible for increasing the currentChamp's ip
	//based on the value gained from the potion p.
	public void usePotion(Potion p){
		// getting new ip
		int newIp= p.getAmount() + ((Wizard)currentChamp).getIp();
		//checking if ip<DefaultIp 
		if (newIp>((Wizard)currentChamp).getDefaultIp())
			newIp=((Wizard)currentChamp).getDefaultIp();
		// setting new ip.
		((Wizard)currentChamp).setIp(newIp);	
		
	}
	
	public abstract void generateMap() throws IOException;
	
	public abstract void moveForward();
	public abstract void moveBackward();
	public abstract void moveLeft();
	public abstract void moveRight();
	
	
	public void onSlytherinTrait(Direction d){
		Point p= ((Wizard)getCurrentChamp()).getLocation();
		getMap()[p.x][p.y]= new EmptyCell();
		if (d==Direction.FORWARD)
			p.translate(0, 2);
		if (d==Direction.BACKWARD)
			p.translate(0, -2);
		if (d==Direction.RIGHT)
			p.translate(2, 0);
		if (d==Direction.LEFT)
			p.translate(-2, 0);
		((Wizard)currentChamp).setLocation(p);
		getMap()[p.x][p.y]= new ChampionCell(getCurrentChamp());
	}
	
}
