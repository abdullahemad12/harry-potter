package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


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
		
		// get location of champ
		Point p= ((Wizard)getCurrentChamp()).getLocation();
		
		// set 2 locations.
		for(int i=0;i<2;i++){
			int x=randomGenerator.nextInt(5);
			switch (x){
			// fire in same cell
			case 0: markedCells.add(p); break;
			// fire left
			case 1: Point p1= new Point(p); p1.translate(1,0); markedCells.add(p1); break;
			//fire right
			case 2: Point p2= new Point(p); p2.translate(-1,0); markedCells.add(p2); break;
			//fire up
			case 3: Point p3= new Point(p); p3.translate(0,1); markedCells.add(p3); break;
			//fire down
			case 4: Point p4= new Point(p); p4.translate(0,-1); markedCells.add(p4); break;
			default: break;
			}
			
		}
	}
	
	//the dragon fires on the markedCells.
	public void fire(){
		// looping on the fire cells
		for (int i=0; i<markedCells.size();i++){
			Point p= markedCells.get(i);
			// looping on available champs
			for(int j=0; j<getChampions().size();j++){
				// checking if there is a champ in the cell
				if (((Wizard)getChampions().get(i)).getLocation().equals(p)){
					((Wizard)getChampions().get(i)).setHp((((Wizard)getChampions().get(i)).getHp())-150);
					// removing champs with hp<=0
					if (((Wizard)getChampions().get(i)).getHp()<=0){
						getChampions().remove(j);
						j--;
					}
				}
			}
		}
	}
	
	
	

}
