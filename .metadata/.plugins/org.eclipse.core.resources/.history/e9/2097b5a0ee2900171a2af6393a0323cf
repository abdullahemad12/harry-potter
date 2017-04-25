package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Potion;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import harrypotter.exceptions.*;

public class FirstTask extends Task {
	private Random randomGenerator;
	
	//An array list of Points containing the cells marked by the dragon to attack once the currentChamp makes his move.
	private ArrayList<Point> markedCells;
	
	//An array list containing the winners of this task who are thus qualified for the second task.
	private ArrayList<Champion> winners;
	
	public FirstTask(ArrayList<Champion> champions) throws IOException{
		super(shuffleHelper(champions));
		markedCells = new ArrayList<Point>();
		markCells();
		winners = new ArrayList<Champion>();
		
		
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
	
	public ArrayList<Point> getMarkedCells(){
		return this.markedCells;
	}
	
	public void setMarkedCells(ArrayList<Point> markedCells){
		this.markedCells=markedCells;
	}
	public ArrayList<Champion> getWinners(){
		return this.winners;
	}
	
	public void setWinners(ArrayList<Champion> winners){
		this.winners=winners;
	}
	
	//This method is responsible for getting the cells that a dragon will attack after the currentChamp performs an action.
	public void markCells(){
		randomGenerator = new Random();
		markedCells.clear();

		// get location of champ
		Point p= new Point(((Wizard)getCurrentChamp()).getLocation());
		int x = p.x;
		int y=p.y;
		
		//flags to prevent duplicates 
		boolean f0=true;
		boolean f1=true;
		boolean f2=true;
		boolean f3=true;
		boolean f4=true;
		
		// set 2 locations.
		for(int i=0;i<2;i++){
			int xx=randomGenerator.nextInt(5);
			switch (xx){
			// fire in same cell
			case 0:if(f0){ 
						markedCells.add(p);f0=false; break;}
					else{ i--; break;}
			// fire left
			case 1: if (x>0&& f1){
						Point p1= new Point(p); p1.translate(-1,0); markedCells.add(new Point(x-1, y));f1=false; break;}
					else{ i--; break;}
			//fire right
			case 2: if (x<9&& f2){
						Point p2= new Point(p); p2.translate(1,0); markedCells.add(new Point(x+1, y));f2=false; break;}
					else{ i--; break;}
			//fire up
			case 3: if (y<9&& f3){
					Point p3= new Point(p); p3.translate(0,1); markedCells.add(new Point(x, y+1));f3=false; break;}
				else{ i--; break;}
			//fire down
			case 4: if (y>0&& f4){
					Point p4= new Point(p); p4.translate(0,-1); markedCells.add(new Point(x, y-1));f4=false; break;}
				else{ i--; break;}
			default: break;
			}
			
		}
		
		
		/*Point p =new Point(((Wizard)this.getCurrentChamp()).getLocation());
		markedCells.add(p);
		markedCells.add(getTargetPoint(Direction.FORWARD));
		markedCells.add(getTargetPoint(Direction.BACKWARD));
		markedCells.add(getTargetPoint(Direction.RIGHT));
		markedCells.add(getTargetPoint(Direction.LEFT));
		Collections.shuffle(markedCells);
		markedCells.remove(4);
		markedCells.remove(3);
		markedCells.remove(2);*/
	}
	
	//the dragon fires on the markedCells.
	public void fire() throws IOException{
		// looping on the fire cells
		for (int i=0; i<2;i++){
			if (getMap() [markedCells.get(i).x][markedCells.get(i).y] instanceof ChampionCell){
				ChampionCell c = (ChampionCell) getMap() [markedCells.get(i).x][markedCells.get(i).y];
				Wizard w = (Wizard) c.getChamp();
				if (w.getHp()-150 >0){
					((Wizard)((ChampionCell)getMap() [markedCells.get(i).x][markedCells.get(i).y]).getChamp()).setHp(w.getHp()-150);
				}
				else {
					((Wizard)((ChampionCell)getMap() [markedCells.get(i).x][markedCells.get(i).y]).getChamp()).setHp(0);
					getMap() [markedCells.get(i).x][markedCells.get(i).y] = new EmptyCell();
					getChampions().remove(w);
					if(getChampions().isEmpty())
					{ 	if (getListener() != null){
								getListener().onFinishingFirstTask(((FirstTask) this).getWinners());
								return;
						}
					}
				}
			}
		}
			
	}	
	
	//moving the currentChamp one cell up return the new point
	public Point moveForward() throws IOException, InvalidTargetCellException, OutOfBordersException {
		//getting old point
		Point pp= ((Wizard)getCurrentChamp()).getLocation();
		if (pp.x>0){
			Point p=new Point(pp);
			// moving it up
			p.translate(-1, 0);
			//checking if it is possible to move
			if (getMap()[p.x][p.y] instanceof EmptyCell || getMap()[p.x][p.y] instanceof CollectibleCell){
				//changing ip after collecting the collectible
				if (getMap()[p.x][p.y] instanceof CollectibleCell){
					int amount =((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()).getAmount();
					int newIp= amount + ((Wizard)getCurrentChamp()).getIp();
					((Wizard)getCurrentChamp()).setIp(newIp);
					((Wizard)getCurrentChamp()).getInventory().add(((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()));
				}
				//changing map cell type
				Point oldP= ((Wizard)getCurrentChamp()).getLocation();
				getMap()[oldP.x][oldP.y]= new EmptyCell();
				getMap()[p.x][p.y]= new ChampionCell(getCurrentChamp());
				//changing champs location
				((Wizard)getCurrentChamp()).setLocation(p);
				finalizeAction();
				return p;
			}
			else
			{
				throw new InvalidTargetCellException("You are Trying to Move to an Invalid Target Cell");
			}
		
		}
		else
			throw new OutOfBordersException("You are Trying to Move to an Invalid Direction");
	}
	
	//moving the currentChamp one cell down returns the new point
	public Point moveBackward() throws IOException, InvalidTargetCellException, OutOfBordersException {
		Point pp= ((Wizard)getCurrentChamp()).getLocation();
		Point p=new Point(pp);
		if (pp.x<9){
			p.translate(1, 0);
			if (getMap()[p.x][p.y] instanceof EmptyCell || getMap()[p.x][p.y] instanceof CollectibleCell){
				if (getMap()[p.x][p.y] instanceof CollectibleCell){
					int amount =((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()).getAmount();
					int newIp= amount + ((Wizard)getCurrentChamp()).getIp();
					((Wizard)getCurrentChamp()).setIp(newIp);
					((Wizard)getCurrentChamp()).getInventory().add(((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()));
				}
				Point oldP= ((Wizard)getCurrentChamp()).getLocation();
				getMap()[oldP.x][oldP.y]= new EmptyCell();
				getMap()[p.x][p.y]= new ChampionCell(getCurrentChamp());
				((Wizard)getCurrentChamp()).setLocation(p);
				finalizeAction();
				return p;
			}
			else
			{
				throw new InvalidTargetCellException("You are Trying to Move to an Invalid Target Cell");
			}
		}
		else
			throw new OutOfBordersException("You are Trying to Move to an Invalid Direction");
		
	}
	
	//moving the currentChamp one cell left return the new point
	public Point moveLeft() throws IOException, InvalidTargetCellException, OutOfBordersException {
		Point pp= ((Wizard)getCurrentChamp()).getLocation();
		Point p=new Point(pp);
		if (pp.y>0){
			p.translate(0, -1);
			if (getMap()[p.x][p.y] instanceof EmptyCell || getMap()[p.x][p.y] instanceof CollectibleCell){
				if (getMap()[p.x][p.y] instanceof CollectibleCell){
					int amount =((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()).getAmount();
					int newIp= amount + ((Wizard)getCurrentChamp()).getIp();
					((Wizard)getCurrentChamp()).setIp(newIp);
					((Wizard)getCurrentChamp()).getInventory().add(((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()));
				}
				Point oldP= ((Wizard)getCurrentChamp()).getLocation();
				getMap()[oldP.x][oldP.y]= new EmptyCell();
				getMap()[p.x][p.y]= new ChampionCell(getCurrentChamp());
				((Wizard)getCurrentChamp()).setLocation(p);
				finalizeAction();
				return p;
			}
			else
			{
				throw new InvalidTargetCellException("You are Trying to Move to an Invalid Target Cell");
			}
		}
		else
			throw new OutOfBordersException("You are Trying to Move to an Invalid Direction");
	}
	
	//moving the currentChamp one cell right
	public Point moveRight() throws IOException, InvalidTargetCellException, OutOfBordersException {
		Point pp= ((Wizard)getCurrentChamp()).getLocation();
		Point p=new Point(pp);
		if (pp.y<9){
			p.translate(0, 1);
			if (getMap()[p.x][p.y] instanceof EmptyCell || getMap()[p.x][p.y] instanceof CollectibleCell){
				if (getMap()[p.x][p.y] instanceof CollectibleCell){
					int amount =((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()).getAmount();
					int newIp= amount + ((Wizard)getCurrentChamp()).getIp();
					((Wizard)getCurrentChamp()).setIp(newIp);
					((Wizard)getCurrentChamp()).getInventory().add(((Potion)((CollectibleCell)getMap()[p.x][p.y]).getCollectible()));
				}
				Point oldP= ((Wizard)getCurrentChamp()).getLocation();
				getMap()[oldP.x][oldP.y]= new EmptyCell();
				getMap()[p.x][p.y]= new ChampionCell(getCurrentChamp());
				((Wizard)getCurrentChamp()).setLocation(p);
				finalizeAction();
				return p;
			}
			else
			{
				throw new InvalidTargetCellException("You are Trying to Move to an Invalid Target Cell");
			}
		}
		else
			throw new OutOfBordersException("You are Trying to Move to an Invalid Direction");
	}
	
	public void onSlytherinTrait(Direction d) throws IOException, OutOfBordersException, InvalidTargetCellException {

		Point temp = new Point(((Wizard)getCurrentChamp()).getLocation());
		if (!trans(d, temp))
			throw new OutOfBordersException("Trying to move out of the borders of the map");
		
		if(!(getMap()[temp.x][temp.y] instanceof EmptyCell))
		{
			throw new InvalidTargetCellException("The trait is activated on an invalid target cell type");
		}
		super.onSlytherinTrait(d);
		((Wizard)getCurrentChamp()).setTraitCooldown(6);
		finalizeAction();
	}
	private static boolean trans(Direction d, Point p )
	{
		if (d==Direction.FORWARD && p.x>1)
			 p.translate(-2, 0);
			
		else if (d==Direction.BACKWARD && p.x<8)
			p.translate(2, 0);
		else if (d==Direction.RIGHT && p.y<8)
			p.translate(0, 2);
		else if (d==Direction.LEFT && p.y>1)
			p.translate(0, -2);
		else
			return false;
		return true;
	}
	
	public Object onRavenclawTrait(){
		if (!isTraitActivated()){
			((Wizard)getCurrentChamp()).setTraitCooldown(5);
			setTraitActivated(true);
			ArrayList<Point> ms = new ArrayList<Point>();
			for (int i=0; i< markedCells.size(); i++){
				ms.add(markedCells.get(i));
			}
				
			//ArrayList<Point> ms = new ArrayList<Point>(markedCells);
			return ms;
		}
		else
			return null;
	}
	/*public void finalizeAction() throws IOException{
		super.finalizeAction();
		Point p =((Wizard)getCurrentChamp()).getLocation();

			if(!((Wizard)getCurrentChamp() instanceof HufflepuffWizard) &&isTraitActivated())
			{
				fire();
			}
			if (getAllowedMoves()==0)
				endTurn();
			
		if (p.x==4&& p.y==4){
			winners.add(getCurrentChamp());
			getMap()[4][4]= new EmptyCell();
			getChampions().remove(getCurrentChamp());
			
		if(getChampions().isEmpty()|| getWinners().size()==4)
		{
			if (getListener() != null)
				getListener().onFinishingFirstTask(winners);
		}
		}
		
	}*/

}
