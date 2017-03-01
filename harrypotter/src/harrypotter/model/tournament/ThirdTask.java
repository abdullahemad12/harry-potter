package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Obstacle;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.WallCell;

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
				case 1: if (getChampions().size()>0) 
							mapx[i][j]=new ChampionCell(getChampions().get(0));
						else
							getMap()[i][j]= new EmptyCell(); break;
				case 2: if (getChampions().size()>1) 
							getMap()[i][j]=new ChampionCell(getChampions().get(1));
						else
							getMap()[i][j]= new EmptyCell(); break;
				case 3: if (getChampions().size()>2) 
							getMap()[i][j]=new ChampionCell(getChampions().get(2));
						else
							getMap()[i][j]= new EmptyCell(); break;
				case 4: if (getChampions().size()>3) 
							getMap()[i][j]=new ChampionCell(getChampions().get(3));
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
}
