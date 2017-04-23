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

abstract public class TaskGUI implements ActionListener {
	
	private Tournament tournament;
	private TaskView taskview;
	
	public TaskView getTaskview()
	{
		return taskview;
	}
	public TaskGUI(Tournament tournament)
	{
		this.setTournament(tournament);
		taskview = new Task1view();
		taskview.getSpells().addActionListener(this);
		taskview.getUp().addActionListener(this);
		taskview.getDown().addActionListener(this);
		taskview.getLeft().addActionListener(this);
		taskview.getRight().addActionListener(this);
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
			UpdateSpells(taskview.getSelectedSpell());
			taskview.revalidate();
		}
		
		// moves forward
		else if(e.getSource() == taskview.getUp())
		{
			
			try 
			{
				Point p =  tournament.getTask().moveForward();
				UpdateCurrentChamp(tournament.getTask());
				UpdateMap(oldLocation, p);
				taskview.revalidate();
				taskview.repaint();
				
				
			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
			}
			//taskview.revalidate();
		}
		else if(e.getSource() == taskview.getDown()){
			try {
				Point p = tournament.getTask().moveBackward();
				UpdateCurrentChamp(tournament.getTask());
				UpdateMap(oldLocation, p);
				taskview.revalidate();
				taskview.repaint();

			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				;
			}
			//taskview.revalidate();
		}
		else if(e.getSource() == taskview.getLeft()){
			try {
				Point p = tournament.getTask().moveLeft();
				UpdateMap(oldLocation, p);
				UpdateCurrentChamp(tournament.getTask());
				taskview.revalidate();
				taskview.repaint();

			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
			}
			//taskview.revalidate();
		}
		else if(e.getSource() == taskview.getRight()){
			try {
				Point p = tournament.getTask().moveRight();
				UpdateCurrentChamp(tournament.getTask());
				UpdateMap(oldLocation,p);
				taskview.revalidate();
				taskview.repaint();

			} catch (InvalidTargetCellException | OutOfBordersException
					| IOException e1) {
				JOptionPane.showMessageDialog(taskview,
					    e1.getMessage(),
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
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
	 * infers the type of cell and returns the appropriate image relatively
	 */
	public JImageComponent inferCell(int i, int j) throws IOException
	{
		Cell cell = getTournament().getFirstTask().getMap()[i][j];
		JImageComponent map = getTaskview().getMap()[i][j];
		String location = "";
		
		// the cell contains a champion 
		if(cell instanceof ChampionCell)
		{
			Wizard champ = (Wizard)((ChampionCell)cell).getChamp();
			if(champ instanceof GryffindorWizard)
			{
				location = "img/gryffindorWizard.png";
			}
			else if(champ instanceof HufflepuffWizard)
			{
				location = "img/hufflepuffWizard.png";
			}
			else if(champ instanceof SlytherinWizard)
			{
				location = "img/slytherinWizard.png";
			}
			else
			{
				location = "img/ravenclawWizard.png";
			}
		}
		// the cell contains the Cup
		else if(cell instanceof CupCell)
		{
			location = "img/cupCell.png";
		}
		// the cell contains a treasure
		else if(cell instanceof TreasureCell)
		{
			location = "img/treasureCell.png";
		}
		// the cell instance of obstacle
		else if(cell instanceof ObstacleCell)
		{
			Obstacle obstacle = ((ObstacleCell)cell).getObstacle();
			if(obstacle instanceof Merperson)
			{
				location = "img/merperson.png";
			}
			else if(obstacle instanceof PhysicalObstacle)
			{
				location = "img/physicalobstacle.png";
			}
		}
		// a collectible Cell
		else if(cell instanceof CollectibleCell)
		{
			location = "img/Collectible.png";
		}
		// Sets the image components to null removing any existing picture
		else if(cell instanceof EmptyCell)
		{
			map.unsetImage();
			System.out.print("Image was removed");
			return null;
		}
		// creates a new image and resizes it
		ImageIcon icon = new ImageIcon(location) ;
		Image img = icon.getImage();
		Image newimg = ((Image) img).getScaledInstance(((taskview.getWidth()-405)/ 10), taskview.getHeight()/10,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon(newimg);
		
		map.setImage(icon);
		return new JImageComponent(icon);
	}
	
	
	
	/*
	 * updates the current map
	 * takes the old and new point of the character, sets the new point to the image of the oldpoint 
	 * Clears the image of the old point 
	 */
	void UpdateMap(Point old, Point target) 
	{
		Cell[][] cells = getTournament().getFirstTask().getMap();
		JImageComponent[][] map = getTaskview().getMap();
		
		JImageComponent newcell = taskview.getMap()[target.x][target.y];
		JImageComponent oldcell = taskview.getMap()[old.x][old.y];
		newcell.setImage(oldcell.getIcon());
		oldcell.unsetImage();
	}

}
