package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Potion;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Obstacle;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.WallCell;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ThirdTask extends Task {
	private Random randomGenerator;
	
	
	public ThirdTask(ArrayList<Champion> champions) throws IOException{
		super(champions);
	}
	
	
	private void readMap(String filePath) throws IOException{
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		String[][] num = new String[10][];
		int n=0;
		while ((currentLine = br.readLine()) != null) {
		String [] line= currentLine.split(",");
		num[n]= line;
		n++;
		}
		br.close();
		//return num;
		//String[][] mapn = ThirdTask.readMap("task3map.csv"); 
		Cell[][] mapx= this.getMap(); 
		randomGenerator = new Random();
		for (int i = 0; i < 10; i++)
		   {
			for (int j = 0; j < 10; j++)
		      {
		    	int x=Integer.parseInt(num[i][j]);  
				switch (x){
				case 0: getMap()[i][j]= new EmptyCell(); break; 
				case 1: if (getChampions().size()>0){ 
							mapx[i][j]=new ChampionCell(getChampions().get(0));
							((Wizard) getChampions().get(0)).setLocation(new Point(i, j));}
						else
							getMap()[i][j]= new EmptyCell(); break;
				case 2: if (getChampions().size()>1) {
							getMap()[i][j]=new ChampionCell(getChampions().get(1));
							((Wizard) getChampions().get(1)).setLocation(new Point(i, j));}
						else
							getMap()[i][j]= new EmptyCell(); break;
				case 3: if (getChampions().size()>2){ 
							getMap()[i][j]=new ChampionCell(getChampions().get(2));
							((Wizard) getChampions().get(2)).setLocation(new Point(i, j));}
						else
							getMap()[i][j]= new EmptyCell(); break;
				case 4: if (getChampions().size()>3){ 
							getMap()[i][j]=new ChampionCell(getChampions().get(3));
							((Wizard) getChampions().get(3)).setLocation(new Point(i, j));}
						else
							getMap()[i][j]= new EmptyCell(); break;
				case 5: getMap()[i][j]= new WallCell(); break; 			
				case 6: 
				{
					randomGenerator = new Random();
					Obstacle  ob = new PhysicalObstacle(randomGenerator.nextInt(101)+200);
					getMap()[i][j]= new ObstacleCell(ob); 
					
					break;	
				}
				default: getMap()[i][j]= new CupCell(); break;
				}
		      }	
		   }

		
	}
	
	
	public void generateMap() throws IOException {
		readMap("task3map.csv");
		for (int k=0;k<10;k++){
			int i= randomGenerator.nextInt(10);
			int j= randomGenerator.nextInt(10);
			if (getMap()[i][j] instanceof EmptyCell){
				int index =randomGenerator.nextInt(getPotions().size());
				getMap()[i][j]= new CollectibleCell(getPotions().get(index));
			}
			else
				k--;
		}
	
	}
	//moving the currentChamp one cell up
	public void moveForward()  throws IOException {
		//getting old point
		Point p= ((Wizard)getCurrentChamp()).getLocation();
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
		}
		//calling the listener 
		if (getMap()[p.x][p.y] instanceof CupCell){
			if (getListener() != null)
				getListener().onFinishingThirdTask(getCurrentChamp());
			
		}
		finalizeAction();
	}
	
	//moving the currentChamp one cell down
	public void moveBackward() throws IOException {
		Point p= ((Wizard)getCurrentChamp()).getLocation();
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
		}
		if (getMap()[p.x][p.y] instanceof CupCell){
			if (getListener() != null)
				getListener().onFinishingThirdTask(getCurrentChamp());
			
		}
		finalizeAction();
	}
	
	//moving the currentChamp one cell left
	public void moveLeft() throws IOException {
		Point p= ((Wizard)getCurrentChamp()).getLocation();
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
		}
		if (getMap()[p.x][p.y] instanceof CupCell){
			if (getListener() != null)
				getListener().onFinishingThirdTask(getCurrentChamp());
			
		}
		finalizeAction();
	}
	
	//moving the currentChamp one cell right
	public void moveRight() throws IOException {
		Point p= ((Wizard)getCurrentChamp()).getLocation();
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
		}
		if (getMap()[p.x][p.y] instanceof CupCell){
			if (getListener() != null)
				getListener().onFinishingThirdTask(getCurrentChamp());
			
		}
		finalizeAction();
	}
	
	public void onSlytherinTrait(Direction d) throws IOException {
		super.onSlytherinTrait(d);
		if (!isTraitActivated()){
			((Wizard)getCurrentChamp()).setTraitCooldown(10);
			finalizeAction();
		}
	}
	
	public Object onRavenclawTrait(){
		if (!isTraitActivated()){
			((Wizard)getCurrentChamp()).setTraitCooldown(7);
			setTaitActivated(true);
			//getting location of cup cell
			Point treloc = new Point();
			for (int i=0; i<10; i++){
				for (int j=0; j<10; j++){
					if (getMap()[i][j] instanceof CupCell){
						treloc.setLocation(i, i);
						break;
					}
						
				}
			}
			ArrayList<Direction> location = new ArrayList<Direction>();
			Point p= ((Wizard)getCurrentChamp()).getLocation();
			// checking the location of the treasure cell relative to the champions cell and adding it to the array location 
			if (treloc.x-p.x>0)
				location.add(Direction.BACKWARD);
			else
				if (treloc.x-p.x<0)
					location.add(Direction.FORWARD);
			
			if (treloc.y-p.y>0)
				location.add(Direction.RIGHT);
			else
				if (treloc.y-p.y<0)
					location.add(Direction.LEFT);
			return location;
		}
		return null;
		
	}	
	
	

}
