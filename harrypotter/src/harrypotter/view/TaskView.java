package harrypotter.view;

import harrypotter.model.magic.Spell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import codeproject.jimagecomponent.javax.swing.JImageComponent;
import codeproject.jimagecomponent.javax.swing.JImagePanel;

abstract public class TaskView  extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5369493443035581134L;
	private JPanel leftUpPan;//champs panel
	private JPanel leftDownPan;// controls panel
	private JImagePanel rightPan;// map panel "Main Pannel right"
	private JPanel leftPan;// panel containing left up and down "Main Pannel Left"
	
	private JButton Up;
	private JButton Down;
	private JButton Left;
	private JButton Right;
	private JButton UseTrait;
	private JButton UseSpell;
	private JComboBox<Spell> spells;
	
	//Attributes related to the left upper pannel
	
	private JLabel wizClass; // the class of the Wizard
	private JLabel ChampName; // name of the current champion 
	private JLabel ip; // ip of the current champion
	private JLabel hp; // hp of the current champion
	private JLabel traitactivated; // trait activated flag of the current champion
	private JLabel remainingmoves; // remaining moves of the current champion
	private JLabel spellName; // the name of the selected spell 
	private JLabel spellCost; // tbtnTest.setIcon(new ImageIcon("IMAGE PATH"));he cost of the selected spell
	private JLabel spellcooldown; // the cooldown of the selected spell
	private JLabel spelltype; // the type of the selected spell (Healing, damaging)
	
	private JImageComponent[][] map; // 
	
	public JImageComponent[][] getMap()
	{
		return map;
	}
	public JComboBox<Spell> getSpells()
	{
		return spells;
	}
	
	public void setWizClass(String wiz)
	{
		this.wizClass.setText(wiz);
	}
	public void setChampName(String ChampName)
	{
		this.ChampName.setText(ChampName);
	}
	public void setIp(int ip)
	{
		this.ip.setText(""+ip);
	}
	public void setHp(int hp)
	{
		this.hp.setText(""+hp);
	}
	
	public void setTraitActivated(boolean traitActivated)
	{
		this.traitactivated.setText("" + traitActivated);
	}
	
	public void setRemainingMoves(int remainingMoves)
	{
		this.remainingmoves.setText("" + remainingMoves);
	}

	
	public void setSpellName(String spellName) {
		this.spellName.setText(spellName);
	}
	public void setSpellType(String spelltype) {
		this.spelltype.setText(spelltype);
	}


	public void setSpellCost(int spellCost) {
		this.spellCost.setText("" + spellCost);

	}


	public void setSpellcooldown(int spellcooldown) {
		this.spellcooldown.setText("" + spellcooldown);
	}


	
	public TaskView(String title)
	{
		//creating the window
		this.setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50,50,1200,600);
		setResizable(false);

		// creating instances of the pannels 
		leftUpPan = new JPanel();
		leftDownPan = new JPanel();
		rightPan = new JImagePanel("img/background.jpg",getWidth()-405, getHeight());
		leftPan = new JPanel();
		spells=new JComboBox<Spell>();
		//setting panels size
		leftPan.setPreferredSize(new Dimension(400, getHeight()));	
		leftPan.setLayout(new GridLayout(0,1));
		leftUpPan.setLayout(new GridLayout(0,3));
		leftDownPan.setLayout(new GridLayout(0,3));
		
		// map will be a grid 10x10
		rightPan.setLayout(new GridLayout(10,10));
		//adding the panel
		add(leftPan, BorderLayout.WEST);
		add(rightPan, BorderLayout.EAST);
		
		leftPan.add(leftUpPan);
		leftPan.add(leftDownPan);
		
		// setting the backgroung
		leftUpPan.setBackground(Color.RED);
		leftDownPan.setBackground(Color.BLUE);

		
		// Setting the buttons 
		Up = new JButton("UP");
		Down = new JButton("Down");
		Left = new JButton("Left");
		Right = new JButton("Right");
		UseTrait = new JButton("Use Trait");
		UseSpell = new JButton("Use Spell");
		
		leftDownPan.add(new JLabel(""));
		leftDownPan.add(Up);
		leftDownPan.add(new JLabel(""));
		leftDownPan.add(Left);
		leftDownPan.add(Down);
		leftDownPan.add(Right);
		leftDownPan.add(UseSpell);
		leftDownPan.add(spells);
		leftDownPan.add(UseTrait);
		
		// setting up the Left upper pannel 
		
		
		// creating new instances of the related labels
		wizClass = new JLabel();
		ChampName = new JLabel(); 
		ip = new JLabel();
		hp = new JLabel();
		traitactivated = new JLabel();
		remainingmoves = new JLabel();
		spellName = new JLabel();
		spellCost = new JLabel();
		spellcooldown = new JLabel();
		spelltype = new JLabel();
		
		// title
		addtextLU();
		addtextLU("Current champ");
		addtextLU();
		
		
		addtextLU(wizClass);
		addtextLU();
		addtextLU(ChampName);
		
		addtextLU("IP:");
		addtextLU();
		addtextLU(ip);
		
		addtextLU("HP:");
		addtextLU();
		addtextLU(hp);
		
		addtextLU("Trait Activated:");
		addtextLU();
		addtextLU(traitactivated);
		
		
		addtextLU("Remaining Moves:");
		addtextLU("");
		addtextLU(remainingmoves);
		
		// spell info
		addtextLU();
		addtextLU("Selected Spell");
		addtextLU();
		
		addtextLU("Spell Name");
		addtextLU();
		addtextLU(spellName);
		
		addtextLU("Spell Cost");
		addtextLU();
		addtextLU(spellCost);
		
		addtextLU("Spell Cooldown");
		addtextLU();
		addtextLU(spellcooldown);
		
		addtextLU("Type");
		addtextLU();
		addtextLU(spelltype);
		
		setMap();
		
	}
	
	
	// initializes and sets all the image components
	protected void setMap()
	{
		map = new JImageComponent[10][10];
		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				map[i][j] = new JImageComponent();
				rightPan.add(map[i][j]);
			}
			
		}
	}
	
	/*
	 * adds a spell to the combobox 
	 */
	public void addspell(Spell spell)
	{
		spells.addItem(spell);
	}
	protected void setLeftUpPan(Component o)
	{
		leftUpPan.add(o);
	}
	
	/*
	 * Adds Labels with text x to the upper left pannel
	 */
	protected void addtextLU(String x){
		setLeftUpPan(new JLabel(x));
	}
	
	/*
	 * adds an empty label to the upper left pannel
	 */
	protected void addtextLU(){
		setLeftUpPan(new JLabel(""));
	}
	
	/*
	 * adds an already created labl
	 */
	protected void addtextLU(JLabel label){
		setLeftUpPan(label);
	}
	public JButton getUp() {
		return Up;
	}
	public JButton getDown() {
		return Down;
	}
	public JButton getLeft() {
		return Left;
	}
	public JButton getRight() {
		return Right;
	}
	public JButton getUseTrait() {
		return UseTrait;
	}
	public JButton getUseSpell() {
		return UseSpell;
	}
	public JLabel getTraitactivated() {
		return traitactivated;
	}
	public Spell getSelectedSpell()
	{
		return (Spell) spells.getSelectedItem();
	}
}
