package harrypotter.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import codeproject.jimagecomponent.javax.swing.ImageBuffer;
import codeproject.jimagecomponent.javax.swing.JImageComponent;
import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.world.*;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.*;
import harrypotter.model.tournament.FirstTask;
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

	private boolean FireFlag; 	
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
		taskview.getUseSpell().addActionListener(this);
		taskview.getUsePotion().addActionListener(this);
		taskview.getUp().addActionListener(this);
		taskview.getDown().addActionListener(this);
		taskview.getLeft().addActionListener(this);
		taskview.getRight().addActionListener(this);
		taskview.getUseTrait().addActionListener(this);

		
		// prepares the image buffer for each icon
		gryff = new ImageBuffer("img/gryffindorWizard.png", width, height, 10);
		huff = new ImageBuffer("img/hufflepuffWizard.png", width, height, 20);
		slyn = new ImageBuffer("img/slytherinWizard.png", width, height, 30);
		raven = new ImageBuffer("img/ravenclawWizard.png", width, height, 40);
		Wall = new ImageBuffer("img/wallCell.png", getWidth(), getHeight(), 3);
	}

	
	/*
	 *  All the setters and getters
	 */
	public boolean isFireFlag() {
		return FireFlag;
	}

	public void setFireFlag(boolean fireFlag) {
		FireFlag = fireFlag;
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
	 * updates all the current champion information for all tasks left right panels 
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
		
		taskview.Emptypotions();
		if (currentChamp.getInventory().isEmpty()){
			taskview.disableUsePotion();
		}
		else{
			taskview.enableUsePotion();
			for (int i=0; i<currentChamp.getInventory().size();i++){
				taskview.addpotion((Potion) currentChamp.getInventory().get(i));
			}
		}
		
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
	
	/*
	 * Action Listener for all the common buttons and GUI components
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		// the old location of the champ 
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
		
		// moving forward
		else if(e.getSource() == taskview.getUp())
		{
			
			move(Direction.FORWARD);
		}
		//moving backward
		else if(e.getSource() == taskview.getDown()){
			move(Direction.BACKWARD);

		}
		// moving left
		else if(e.getSource() == taskview.getLeft()){
			move(Direction.LEFT);
		}
		// moving right
		else if(e.getSource() == taskview.getRight()){
			move(Direction.RIGHT);
		}
		// the user is trying to use a spell 
		else if(e.getSource() == taskview.getUseSpell()){
			castSpell();
			
		}
		
		// user tries to use his potions
		else if(e.getSource() == taskview.getUsePotion()){
			tournament.getTask().usePotion(taskview.getSelectedPotion());
		}
		
		// user trying to use traits
		else if (e.getSource() == taskview.getUseTrait()){
			
			useTrait();
		}
		
	}
	
	/*
	 * initializes the Map
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
	
	
	/*
	 * cast a spell when the UseSpell button is pressed 
	 * Cast the selected spell in the JComboBox
	 */
	public void castSpell()
	{
		Spell spell = taskview.getSelectedSpell();
		// trying to cast a healing spell
		if(spell instanceof HealingSpell){
			try {
				tournament.getTask().castHealingSpell((HealingSpell) spell);
				if(tournament.getTask() instanceof FirstTask){
					FireFlag = true;
				}
			} catch (InCooldownException | NotEnoughIPException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());

			}
		}
		
		else if(spell instanceof DamagingSpell){
			Direction[] possibilities = {Direction.FORWARD, Direction.BACKWARD, Direction.RIGHT, Direction.LEFT};
			
			// popout
			Direction s = (Direction)JOptionPane.showInputDialog(
								taskview,
			                    "Please choose spell direction",
			                    "A plain message",
			                    JOptionPane.PLAIN_MESSAGE,
			                     null, possibilities, Direction.FORWARD
			                   );
				
				try {
					tournament.getTask().castDamagingSpell((DamagingSpell) spell, s);
					if(tournament.getTask() instanceof FirstTask){
						FireFlag = true;
					}
				} catch (InCooldownException | NotEnoughIPException
						| InvalidTargetCellException
						| OutOfBordersException | IOException e1) {
					JOptionPane.showMessageDialog(taskview,
						    e1.getMessage(),
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
					System.out.println(e1.getMessage());
				}
			
		}
		else{
			Direction[] possibilities = {Direction.FORWARD, Direction.BACKWARD, Direction.RIGHT, Direction.LEFT};
			Direction d = (Direction)JOptionPane.showInputDialog(
								taskview,
			                    "Please choose Old direction",
			                    "A plain message",
			                    JOptionPane.PLAIN_MESSAGE,
			                     null, possibilities, Direction.FORWARD
			                   );
			Direction t = (Direction)JOptionPane.showInputDialog(
					taskview,
                    "Please choose new direction",
                    "A plain message",
                    JOptionPane.PLAIN_MESSAGE,
                     null, possibilities, Direction.FORWARD
                   );
			Object[] range = {1,2,3,4,5,6,7,8,9};
			int r = (int)JOptionPane.showInputDialog(
					taskview,
                    "Please choose spell range",
                    "A plain message",
                    JOptionPane.PLAIN_MESSAGE,
                     null, range, 1
                   );
			try {
				tournament.getTask().castRelocatingSpell((RelocatingSpell) spell, d, t, r);
				if(tournament.getTask() instanceof FirstTask){
					FireFlag = true;
				}
			} catch (InCooldownException | NotEnoughIPException
					| InvalidTargetCellException | OutOfRangeException
					| OutOfBordersException | IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());
			}
		}
	}
	
	/*
	 * uses the trait of the champion According to the task and the class of the current champion
	 */
	public void useTrait()
	{
		Wizard champ = (Wizard) tournament.getTask().getCurrentChamp();
		if (champ instanceof GryffindorWizard || champ instanceof HufflepuffWizard ){
			try {
				champ.useTrait();
			} catch (InCooldownException | OutOfBordersException
					| InvalidTargetCellException | IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());
			}
		}
		else if(champ instanceof SlytherinWizard){
			Direction[] possibilities = {Direction.FORWARD, Direction.BACKWARD, Direction.RIGHT, Direction.LEFT};
			Direction d = (Direction)JOptionPane.showInputDialog(
								taskview,
			                    "Please choose trait direction",
			                    "A plain message",
			                    JOptionPane.PLAIN_MESSAGE,
			                     null, possibilities, Direction.FORWARD
			                   );
			((SlytherinWizard) champ).setTraitDirection(d);
			try {
				champ.useTrait();
				if(tournament.getTask() instanceof FirstTask){
					FireFlag = true;
				}
			} catch (InCooldownException | OutOfBordersException
					| InvalidTargetCellException | IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());
			}
		}
	}
	
	/*
	 * Takes a direction and attempts to move the Current champion in the given direction
	 * pops out a message in case of failure
	 */
	public void move(Direction d)
	{
		// moving backward
		if (d == Direction.BACKWARD)
		{
			try {	
				tournament.getTask().moveBackward();
				if(tournament.getTask() instanceof FirstTask){
					FireFlag = true;
				}

			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());

				;
			}
		}
		// moving forward
		else if(d == Direction.FORWARD)
		{
			try 
			{
				
				tournament.getTask().moveForward();
				if(tournament.getTask() instanceof FirstTask){
					FireFlag = true;
				}
								
			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());
			}
		}
		
		// trying to move left
		else if(d == Direction.LEFT)
		{
			try {	
				tournament.getTask().moveLeft();
				if(tournament.getTask() instanceof FirstTask){
					FireFlag = true;
				}
				
			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());

			}
		}
		// moving to the right 
		else
		{
			try {
				
				tournament.getTask().moveRight();
				if(tournament.getTask() instanceof FirstTask){
					FireFlag = true;
				}
			
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

}
