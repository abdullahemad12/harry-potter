package harrypotter.controller;



import codeproject.jimagecomponent.javax.swing.JImageComponent;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.EmptyCell;

public class ThirdTaskGUI extends TaskGUI{
	
	
	public ThirdTaskGUI(Tournament tournament)
	{
		super(tournament);
		initializeMap();
		
	}
	
	/*
	 * Initializes the Map
	 */
	void initializeMap() 
	{
		Cell[][] cells = getTournament().getFirstTask().getMap();
		JImageComponent[][] map = getTaskview().getMap();
		
		for(int i = 0; i  < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(!(cells[i][j] instanceof EmptyCell))
				{
					inferCell(cells[i][j], map[i][j]);	
				}
			}
		}
	
		
	}
	


}
