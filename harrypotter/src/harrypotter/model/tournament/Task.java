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

import harrypotter.model.character.*;
import harrypotter.model.magic.*;



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
		
		// resets the health points, intelligence points, traitcooldown, spells for the next Task
		for(Champion champion: this.champions)
		{
			((Wizard)champion).setHp(((Wizard)champion).getDefaultHp());
			((Wizard)champion).setIp(((Wizard)champion).getDefaultIp());
			((Wizard)champion).setTraitCooldown(0);
			ArrayList<Spell> spells = ((Wizard)champion).getSpells();
			// sets all the spells for the champion
			for(Spell spell : spells)
			{
				spell.setCoolDown(0);
			}

		}
		currentChamp = champions.get(0);
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
	
	public void castRelocatingSpell(RelocatingSpell s,Direction d,Direction t,int r) throws IOException{
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
		boolean flag =champions.remove(currentChamp);
		if (flag)
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
	

	public abstract void moveForward() throws IOException ;
	public abstract void moveBackward() throws IOException ;
	public abstract void moveLeft() throws IOException ;
	public abstract void moveRight() throws IOException ;
	
	
	public void onSlytherinTrait(Direction d) throws IOException {
		traitActivated = true;
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

	/*
	 * performs the steps needed for finalizing any action i.e. move performed by the currentChamp.
	 */
	public void finalizeAction()throws IOException
	{
		// the location of the current champion
		Point p = ((Wizard) this.currentChamp).getLocation();
		Wizard temp =(Wizard) currentChamp;
		Cell current = map[p.x][p.y];
		if(this instanceof FirstTask)
		{
			// we have a winner
			if(p.x == 4 && p.y == 4)
			{
				// removes the Champ from the cell and adds him to the winners list
				map[4][4] = new EmptyCell();
				ArrayList<Champion> win = ((FirstTask)this).getWinners();
				win.add(temp);
				((FirstTask)this).setWinners(win);
				champions.remove(currentChamp);
				// if all the players were removed than they must have winned or died
				if(champions.isEmpty())
				{
					listener.onFinishingFirstTask(((FirstTask) this).getWinners());
				}

			}
			else
			{
				//does not fire a hifflepuffWizard whose trait has been activated
				if(!(temp instanceof HufflepuffWizard) || !traitActivated)
				{
					((FirstTask)this).fire();
				}
			}
			
		}
		else if(this instanceof SecondTask)
		{
			//does not fire a hifflepuffWizard whose trait has been activated
			if(!(temp instanceof HufflepuffWizard) || !traitActivated)
			{
				((SecondTask)this).encounterMerPerson();
			}
			
			// collects treasure
			if(current instanceof TreasureCell)
			{
				// gets the owner to check whether the treasure belongs to the champion or not
				Wizard owner =(Wizard) ((TreasureCell) current).getOwner();
				// winner
				if(owner == currentChamp)
				{
					// adds his to the list of winners 
					ArrayList<Champion> win = ((SecondTask)this).getWinners();
					win.add(temp);
					((SecondTask)this).setWinners(win);
					// removes him from champions
					champions.remove(temp);
					
					// Task ends if the list is empty
					
					if(champions.isEmpty())
					{
						listener.onFinishingFirstTask(win);
					}
					
				}
			}
		}
	}
	
	/*
	 * This method is responsible for returning the Point of the cell adjacent to the currentChamp’s location in the targeted direction d.
	 */
	public Point getTargetPoint(Direction d)
	{
		// the location of the current champ
		Point p =((Wizard)this.currentChamp).getLocation();
		
		// adds 1 to the Y-coord
		if(d == Direction.FORWARD)
		{
			p.y = (int) (p.getY() + 1) ;
			return p;
		}
		// removes 1 from the Y-coord
		else if(d == Direction.BACKWARD)
		{
			p.y = (int) (p.getY() - 1) ; 
			return p;
		}
		// adds 1 to the X-coord
		else if(d == Direction.RIGHT)
		{
			p.x = (int) (p.getX() + 1) ; 
			return p;
		}
		// removes 1 from the X-coord
		else if(d == Direction.LEFT)
		{
			p.x = (int) (p.getX() - 1) ;
			return p;
		}
		else
		{
			return p;
		}
			
	}
	
	/*
	 * method is responsible for casting a DamagingSpell to the currentChamp’s adjacent cell in the target direction d
	 */
	public void castDamagingSpell(DamagingSpell s, Direction d) throws IOException
	{
		// gets the target of the damaging spell 
		Point p = getTargetPoint(d);
		
		// the cell at which the attack was made
		Cell targetedCell = map[p.x][p.y];
		
		// a player was standing in the attacked cell
		if(targetedCell instanceof ChampionCell)
		{
			Champion target = ((ChampionCell) targetedCell).getChamp();
			// the Champion was a HufflepuffWizard in the third task
			if((this instanceof ThirdTask) && (target instanceof HufflepuffWizard))
			{
				((Wizard) target).setHp(((Wizard) target).getHp() - (s.getDamageAmount() / 2));
			}
			else
			{
				// otherwise
				((Wizard) target).setHp(((Wizard) target).getHp() - s.getDamageAmount());
			}
		}
		// an obstacle
		else if(targetedCell instanceof ObstacleCell)
		{
			Champion target = ((ChampionCell) targetedCell).getChamp();
			
			((Wizard) target).setHp(((Wizard) target).getHp() - s.getDamageAmount());

		}
		useSpell(s);
		finalizeAction();
		
	}
	
	/*
	 * This is responsible for casting a HealingSpell to restore the hp of the currentChamp with the healing amount of the spell.
	 */
	public void castHealingSpell(HealingSpell s) throws IOException
	{
		// values
		int hp = ((Wizard)this.currentChamp).getHp();
		int defaulthp = ((Wizard)this.currentChamp).getDefaultHp();
		int heal = s.getHealingAmount();
		// The healing amount was less or equal to the defualthp
		if(defaulthp >= hp + heal)
		{
			((Wizard)this.currentChamp).setHp(hp+heal);
		}
		else
		{
			((Wizard)this.currentChamp).setHp(defaulthp);
		}
		useSpell(s);
		finalizeAction();
	}
	/*
	 * responsible for activating the Gryffindor trait.
	 */
	public void onGryffindorTrait()
	{	
		Wizard currentchamp = (Wizard) this.currentChamp;
		
		// makes sure the current Champion is a Hufflepuf Wizard
		if(!(currentchamp instanceof GryffindorWizard))
		{
			return;
		}
		
		// activates trait
		traitActivated = true;

		// sets the cool down according to the task
		
		currentchamp.setTraitCooldown(4);
		 allowedMoves = 2;
		
		
		
	}
	
	/*
	 * responsible for activating the Hufflepuff trait.
	 */
	public void onHufflepuffTrait()
	{
		Wizard currentchamp = (Wizard) this.currentChamp;
		
		// makes sure the current Champion is a Hufflepuf Wizard
		if(!(currentchamp instanceof HufflepuffWizard))
		{
			return;
		}
		
		// activates trait
		traitActivated = true;

		// sets the cool down according to the task
		
		if(this instanceof FirstTask)
		{
			currentchamp.setTraitCooldown(3);
		}
		else if(this instanceof SecondTask)
		{
			currentchamp.setTraitCooldown(6);
		}
		
		// no cool down for the third task

	
		/*
		 * not sure if I should use it in any other function
		 */
		
	}
}


