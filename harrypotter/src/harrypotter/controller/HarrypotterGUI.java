package harrypotter.controller;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import harrypotter.model.character.*;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.*;
import harrypotter.view.Startmenu2;

public class HarrypotterGUI implements ActionListener {
	private Tournament tournament; 
	private Startmenu2 startmenu2;
	
	private JComboBox spells1A;
	private JComboBox spells1B;
	private JComboBox spells1C;
	private JComboBox spells2A;
	private JComboBox spells2B;
	private JComboBox spells2C;
	private JComboBox spells3A;
	private JComboBox spells3B;
	private JComboBox spells3C;
	private JComboBox spells4A;
	private JComboBox spells4B;
	private JComboBox spells4C;
	
	@SuppressWarnings("unchecked")
	public HarrypotterGUI() throws Exception
	{
		tournament = new Tournament();
		startmenu2 = new Startmenu2();
		
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
			
			//JCheckBox cspell = new JCheckBox(name);
			//startmenu2.addSpell(cspell);
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
		String champ1 = startmenu2.getChampions1();
		String champ2 = startmenu2.getChampions2();
		String champ3 = startmenu2.getChampions3();
		String champ4 = startmenu2.getChampions4();
		
		// infers the the type the champion and creates a new instance
		Champion[] player = new Champion[4];
		player[0] = inferChamp(champ1, name1);
		player[1] = inferChamp(champ2, name2);
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
		
		startmenu2.setVisible(false);
		try {
			tournament.beginTournament();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		new Task1GUI(this.tournament);
		
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
			return new HufflepuffWizard(name);
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
}
