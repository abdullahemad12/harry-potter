package harrypotter.controller;

import java.awt.Checkbox;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.*;
import harrypotter.view.StartMenuView;
import harrypotter.view.Startmenu2;

public class HarrypotterGUI {
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
		
		spells1A = new JComboBox<>();
		spells1B = new JComboBox<>();
		spells1C = new JComboBox<>();
		spells2A = new JComboBox<>();
		spells2B = new JComboBox<>();
		spells2C = new JComboBox<>();
		spells3A = new JComboBox<>();
		spells3B = new JComboBox<>();
		spells3C = new JComboBox<>();
		spells4A = new JComboBox<>();
		spells4B = new JComboBox<>();
		spells4C = new JComboBox<>();
		
		for(Spell spell : spells)
		{
			String name = spell.getName();
			spells1A.addItem(name);
			spells1B.addItem(name);
			spells1C.addItem(name);
			spells2A.addItem(name);
			spells2B.addItem(name);
			spells2C.addItem(name);
			spells3A.addItem(name);
			spells3B.addItem(name);
			spells3C.addItem(name);
			spells4A.addItem(name);
			spells4B.addItem(name);
			spells4C.addItem(name);
			
			//JCheckBox cspell = new JCheckBox(name);
			//startmenu2.addSpell(cspell);
		}
		startmenu2.championsPan.add(spells1A); startmenu2.championsPan.add(spells2A);startmenu2.championsPan.add(spells3A);startmenu2.championsPan.add(spells4A);
		startmenu2.championsPan.add(spells1B); startmenu2.championsPan.add(spells2B);startmenu2.championsPan.add(spells3B);startmenu2.championsPan.add(spells4B);
		startmenu2.championsPan.add(spells1C); startmenu2.championsPan.add(spells2C);startmenu2.championsPan.add(spells3C);startmenu2.championsPan.add(spells4C);
		
		startmenu2.setVisible(true);
	}
	public static void main(String[] args) throws Exception
	{
		new HarrypotterGUI();
	}
}