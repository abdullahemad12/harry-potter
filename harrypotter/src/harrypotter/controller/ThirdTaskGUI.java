package harrypotter.controller;



import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import codeproject.jimagecomponent.javax.swing.JImageComponent;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.Direction;
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
	
	/*
	 * (non-Javadoc)
	 * @see harrypotter.controller.TaskGUI#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) 
	{
		super.actionPerformed(e);
		if(e.getSource() instanceof JButton)
		{
			if(e.getSource() == getTaskview().getUseTrait())
			{
				if(getTournament().getTask().getCurrentChamp() instanceof RavenclawWizard)
				{
					@SuppressWarnings("unchecked")
					ArrayList<Direction> raven = (ArrayList<Direction>) getTournament().getTask().onRavenclawTrait();
					JOptionPane.showMessageDialog(getTaskview(), "To reach the cup you need to go: " + raven.get(0)+" and " + raven.get(1));
					
				}
			}
		}
		
	}

	


}
