package harrypotter.controller;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import codeproject.jimagecomponent.javax.swing.ImageBuffer;
import codeproject.jimagecomponent.javax.swing.JImageComponent;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.world.*;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.*;
import harrypotter.model.tournament.Task;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.view.*;


/*
 * Cells id
 * Champion id = X0 where x = 1 ,2, 3 ,4 Gryffindor, HufflePuff, Slytherin, Ravenclaw respectively
 * Empty = -1
 * Obstacle = 2
 * Wall = 3
 */


abstract public class TaskGUI implements ActionListener {
	
	private Tournament tournament;
	private TaskView taskview;
	private ImageBuffer gryff; // The icon of the Gryffindor id: 10
	private ImageBuffer huff; // the icon of the hufflepuf Wizard id:20 
	private ImageBuffer slyn; // the icon the slyntherin Wizard id: 30
	private ImageBuffer raven; // the icon of the ravenClaw Wizard id: 40
	private ImageBuffer obst; // the icon of the Obstacle in the task id: 2
	private ImageBuffer Wall; // wall cells id: 3

	
	private final int width; // the width of the icon
	private final int height; // the height of the icon
	
	
	protected loadingView loading;
	// note: the Obst icon has to be set in each task independently depending on the obstacle in this task
	
	/*
	 * constructor
	 */
	public TaskGUI(Tournament tournament)
	{
		loading = new loadingView();
		loading.setVisibility(true);
		
		// sets the tournament and creates new view
		this.setTournament(tournament);
		taskview = new Task1view();
		
		// sets the height and the width of the Icons
		width = (taskview.getWidth()-405)/ 10;
		height = taskview.getHeight()/10;
		// add the Action Listeners
		taskview.getSpells().addActionListener(this);

		
		// prepares the image buffer for each icon
		gryff = new ImageBuffer("img/gryffindorWizard.png", width, height, 10);
		huff = new ImageBuffer("img/hufflepuffWizard.png", width, height, 20);
		slyn = new ImageBuffer("img/slytherinWizard.png", width, height, 30);
		raven = new ImageBuffer("img/ravenclawWizard.png", width, height, 40);
		Wall = new ImageBuffer("img/wallCell.png", getWidth(), getHeight(), 3);
	}

	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setObst(String location)
	{
		this.obst = new ImageBuffer(location, width, height, 2);
	}
	
	
	public void setTaskview(TaskView taskview) {
		this.taskview = taskview;
	}
	TaskView getTaskview()
	{
		return taskview;
	}
	
	/*
	 * updates all the current champion information for all tasks
	 */
	public void UpdateCurrentChamp(Task task)
	{
		// sets all the champions information 
		
		Wizard currentChamp = (Wizard)task.getCurrentChamp();
		
		
		taskview.setChampName(currentChamp.getName());
		// sets the Wizard class
		if(currentChamp instanceof GryffindorWizard)
		{
			taskview.setWizClass("Gryffindor Wizard");
		}
		else if(currentChamp instanceof HufflepuffWizard)
		{
			taskview.setWizClass("Hufflepuff Wizard");
		}
		else  if(currentChamp instanceof RavenclawWizard)
		{
			taskview.setWizClass("Ravenclaw Wizard");
		}
		else
		{
			taskview.setWizClass("Slytherin Wizard");
		}
		
		//sets ip, hp, traitactivated, remaining moves
		taskview.setIp(currentChamp.getIp());
		taskview.setHp(currentChamp.getHp());
		taskview.setTraitActivated(task.isTraitActivated());	
		taskview.setRemainingMoves(task.getAllowedMoves());
		
		
		// Makes sure the spells ComboBox is empty before adding any spells
		if(taskview.getSpells().getItemCount() != 0)
		{
			taskview.Emptyspells();
		}

		// sets the combobox of the spells 
		ArrayList<Spell> spells = currentChamp.getSpells();
		for(Spell spell : spells)
		{
			taskview.addspell(spell);
		}
		
		// sets the selectedspell info
		UpdateSpells(spells.get(0));
		
	}
	
	/*
	 * updates the spell labels to the selected labels 
	 */
	protected void UpdateSpells(Spell selected)
	{
		taskview.setSpellName(selected.getName());
		taskview.setSpellCost(selected.getCost());
		taskview.setSpellcooldown(selected.getCoolDown());
		taskview.setSpellType(selected.SpellType());
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// the old location of the champ 
		Wizard CurrentChamp =  (Wizard)(tournament.getTask().getCurrentChamp());
		Point oldLocation = CurrentChamp.getLocation();
		//if the event was selecting a new spell from the comboBox
		JComboBox<Spell> spells = taskview.getSpells();
		if(e.getSource() == spells)
		{
			if(getTaskview().getSpells().getItemCount() != 0)
			{
				UpdateSpells(taskview.getSelectedSpell());
			}
			taskview.revalidate();
		}
		
		// moves forward
		else if(e.getSource() == taskview.getUp())
		{
			
			try 
			{
				tournament.getTask().moveForward();
								
			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());
			}
		}
		else if(e.getSource() == taskview.getDown()){
			try {
				tournament.getTask().moveBackward();				

			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());

				;
			}
			//taskview.revalidate();
		}
		else if(e.getSource() == taskview.getLeft()){
			try {
				tournament.getTask().moveLeft();
				
			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());

			}
			//taskview.revalidate();
		}
		else if(e.getSource() == taskview.getRight()){
			try {
					tournament.getTask().moveRight();
				
			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());

			}
		}
		
	}
	
	

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	public void setVisibility(boolean visible)
	{
		taskview.setVisible(visible);
	}
	
	/*
	 * infers the type of cell and sets the icon of the Object
	 */
	public void inferCell(Cell cell, JImageComponent img)
	{
	
		
		// the cell contains a champion 
		if(cell instanceof ChampionCell)
		{
			Wizard champ = (Wizard)((ChampionCell)cell).getChamp();
			if(champ instanceof GryffindorWizard)
			{
				gryff.setImageComponent(img);
			}
			else if(champ instanceof HufflepuffWizard)
			{
				huff.setImageComponent(img);
			}
			else if(champ instanceof SlytherinWizard)
			{
				slyn.setImageComponent(img);
			}
			else
			{
				raven.setImageComponent(img);
			}
		}
		// the cell instance of obstacle
		else if(cell instanceof ObstacleCell)
		{
			obst.setImageComponent(img);
		}
		else if(cell instanceof WallCell)
		{
			Wall.setImageComponent(img);
		}
		else if(cell instanceof EmptyCell)
		{
			img.unsetImage();
		}
	}
	
	
	
	/*
	 * updates the current map
	 * takes the old and new point of the character, sets the new point to the image of the oldpoint 
	 * Clears the image of the old point 
	 */
	void UpdateMap() 
	{
		Cell[][] cells = getTournament().getFirstTask().getMap();
		JImageComponent[][] map = getTaskview().getMap(); 
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				
				inferCell(cells[i][j], map[i][j]);
				if(cells[i][j] instanceof EmptyCell)
				{
					map[i][j].unsetImage();
				}
				
			}
		}
		
		UpdateCurrentChamp(tournament.getTask());
		
	}
	
	/*
	 * Cells id
	 * Champion id = X0 where x = 1 ,2, 3 ,4 Gryffindor, HufflePuff, Slytherin, Ravenclaw respectively
	 * Collectible = 1
	 * Cup = 2
	 * Empty = -1
	 * Obstacle = 3
	 * Treasure = 4
	 * Wall = 5
	 */
	
	public int CellID(Cell cell)
	{
		if(cell instanceof ChampionCell)
		{
			Wizard champ = (Wizard)((ChampionCell) cell).getChamp();
			if(champ instanceof GryffindorWizard)
			{
				return 10;
			}
			else if(champ instanceof HufflepuffWizard)
			{
				return 20;
			}
			else if(champ instanceof SlytherinWizard)
			{
				return 30;
			}
			else
			{
				return 40;
			}
		}
		else if(cell instanceof ObstacleCell)
		{
			return 3;
		}
		else
		{
			return -1;
		}
		
		
	}

}
