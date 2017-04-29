package harrypotter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;



import harrypotter.model.character.*;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.*;
import harrypotter.view.StartMenuView;
import harrypotter.view.TaskView;

/*
 * The Main class that controls all the other Controllers
 */
public class HarrypotterGUI implements ActionListener, TaskListener{
	private Tournament tournament; 
	private StartMenuView startmenu2;
	
	
	
	
	// spells for player 1
	private JComboBox<Spell> spells1A;
	private JComboBox<Spell> spells1B;
	private JComboBox<Spell> spells1C;
	
	// spells for player 2
	private JComboBox<Spell> spells2A;
	private JComboBox<Spell> spells2B;
	private JComboBox<Spell> spells2C;
	
	// spells for player 3
	private JComboBox<Spell> spells3A;
	private JComboBox<Spell> spells3B;
	private JComboBox<Spell> spells3C;
	
	// spells for player 4
	private JComboBox<Spell> spells4A;
	private JComboBox<Spell> spells4B;
	private JComboBox<Spell> spells4C;
	
	// Controllers
	
	// Makes them disposable to free the memory and enhance the performance
	FirstTaskGUI task1;  
	SecondTaskGUI task2;
	ThirdTaskGUI task3;
	
	public HarrypotterGUI() throws Exception
	{
		tournament = new Tournament();
		startmenu2 = new StartMenuView();
		
		
	
		
		
		ArrayList<Spell> spells = tournament.getSpells();
		
		spells1A = new JComboBox<>();startmenu2.setSpells(spells1A);
		spells1B = new JComboBox<>();startmenu2.setSpells(spells1B);
		spells1C = new JComboBox<>();startmenu2.setSpells(spells1C);
		spells2A = new JComboBox<>();startmenu2.setSpells(spells2A);
		spells2B = new JComboBox<>();startmenu2.setSpells(spells2B);
		spells2C = new JComboBox<>();startmenu2.setSpells(spells2C);
		spells3A = new JComboBox<>();startmenu2.setSpells(spells3A);
		spells3B = new JComboBox<>();startmenu2.setSpells(spells3B);
		spells3C = new JComboBox<>();startmenu2.setSpells(spells3C);
		spells4A = new JComboBox<>();startmenu2.setSpells(spells4A);
		spells4B = new JComboBox<>();startmenu2.setSpells(spells4B);
		spells4C = new JComboBox<>();startmenu2.setSpells(spells4C);
		
		for(Spell spell : spells)
		{
			spells1A.addItem(spell);
			spells1B.addItem(spell);
			spells1C.addItem(spell);
			spells2A.addItem(spell);
			spells2B.addItem(spell);
			spells2C.addItem(spell);
			spells3A.addItem(spell);
			spells3B.addItem(spell);
			spells3C.addItem(spell);
			spells4A.addItem(spell);
			spells4B.addItem(spell);
			spells4C.addItem(spell);
		}
		startmenu2.championsPan.add(spells1A); startmenu2.championsPan.add(spells2A);startmenu2.championsPan.add(spells3A);startmenu2.championsPan.add(spells4A);
		startmenu2.championsPan.add(spells1B); startmenu2.championsPan.add(spells2B);startmenu2.championsPan.add(spells3B);startmenu2.championsPan.add(spells4B);
		startmenu2.championsPan.add(spells1C); startmenu2.championsPan.add(spells2C);startmenu2.championsPan.add(spells3C);startmenu2.championsPan.add(spells4C);
		
		this.startmenu2.getButton().addActionListener(this);
		
		
		startmenu2.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		// names of the four champions 
		String name1 = startmenu2.getName1();
		String name2 = startmenu2.getName2();
		String name3 = startmenu2.getName3();
		String name4 = startmenu2.getName4();
		
		// gets the class of each champion; Gryffindor,etc ..
		String champ0 = startmenu2.getChampions1();
		String champ1 = startmenu2.getChampions2();
		String champ2 = startmenu2.getChampions3();
		String champ3 = startmenu2.getChampions4();
		
		// infers the the type the champion and creates a new instance
		Champion[] player = new Champion[4];
		player[0] = inferChamp(champ0, name1);
		player[1] = inferChamp(champ1, name2);
		player[2]= inferChamp(champ2, name3);
		player[3] = inferChamp(champ3, name4);
		
		// All spells are stored respectively in an array
		int spellIndex = 0;
		
		// iterates over the champions  separately
		for(int i = 0; i < 4; i++)
		{
			ArrayList<Spell> spells = new ArrayList<Spell>();
			// spells for each champion 
			for(int j = 0; j < 3; j++)
			{
				// add the spell to the array list of spells that will be passed to the champion
				spells.add(startmenu2.getSpell(spellIndex++));
			}
			// set the spells array to the Champ
			((Wizard)player[i]).setSpells(spells);
			tournament.addChampion(player[i]);
		}
		
		try {
			tournament.beginTournament();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// sets the Graphics
		task1 = new FirstTaskGUI(this.tournament);
		
		task1.setTaskListener(this);
		
		
		// gets rid of the start menu to free memory
		startmenu2.dispose();
		startmenu2 = null;
		
		
	}
	
	
	/*
	 * Return new Champion according to the type passed in to the parameters
	 */
	public static Champion inferChamp(String champ, String name)
	{
		if(champ.equals("Gryffindor Wizard"))
		{
			return new GryffindorWizard(name);
		}
		else if(champ.equals("Hufflepuff Wizard"))
		{
			return new HufflepuffWizard(name);
		}
		else if(champ.equals("Ravenclaw Wizard"))
		{
			return new RavenclawWizard(name);
		}
		else
		{
			return new SlytherinWizard(name);
		}
	}
	
	
	public static void main(String[] args) throws Exception
	{
		new HarrypotterGUI();
	}

	/*
	 * After Finishing the First Task it initializes a new instance of the secondTask GUI 
	 */
	@Override
	public void onFinishingFirstTask() {
		
		// makes a new instance of Task2
		task2 = new SecondTaskGUI(tournament);
		// removes the old instance of task 1
		task2.setTaskListener(this);
		
		
		task1.DisposeView();
		task1 = null; // makes it easier for the garbage collector
		
	}

	/*
	 * After Finishing the First Task it initializes a new instance of the secondTask GUI 
	 */
	@Override
	public void onFinishingSecondTask() {
		task3 = new ThirdTaskGUI(tournament);
		task3.setTaskListener(this);
		
		// gets rid of task 2
		task2.DisposeView();
		task2 = null;
		
	}

	@Override
	public void onFinishingThirdTask(Champion Winner) {
		Wizard winner = (Wizard) Winner;
		TaskView frame = task3.getTaskview();
		JOptionPane.showMessageDialog(frame,
			    "The Winner is: " + winner.getName() + " Thank You for putting up to this very boring game till the End <3" ,
			    "Congrats",
			    JOptionPane.INFORMATION_MESSAGE);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		
	}

}
