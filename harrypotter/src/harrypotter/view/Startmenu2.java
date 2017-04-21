package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Startmenu2 extends JFrame {
	
	public JPanel championsPan;
	private JPanel startPan;
	
	private JTextField name1;
	private JTextField name2;
	private JTextField name3;
	private JTextField name4;
	
	private ButtonGroup Champions1;
	private ButtonGroup Champions2;
	private ButtonGroup Champions3;
	private ButtonGroup Champions4;
	
	private JButton startBtn;
	
	private ArrayList<JCheckBox> spell;
	
	public Startmenu2(){
		
		spell = new ArrayList<JCheckBox>();
		
		this.setTitle("HarryPotter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50,50,1200,600);
		//setContentPane(new JLabel(new ImageIcon("C:\\Users\\youssef\\Desktop\\guc\\Semester 4\\Computer Programming\\harry-potter\\harrypotter\\src\\startm.jpg")));
		
		
		championsPan = new JPanel();
		startPan=  new JPanel();
		
		championsPan.setLayout(new GridLayout(0,4));
		
		add(championsPan, BorderLayout.CENTER);
		add(startPan, BorderLayout.SOUTH);
		
		championsPan.add(new JLabel("Player 1"));
		championsPan.add(new JLabel("Player 2"));
		championsPan.add(new JLabel("Player 3"));
		championsPan.add(new JLabel("Player 4"));
		championsPan.add(new JLabel("Enter name"));
		championsPan.add(new JLabel("Enter name"));
		championsPan.add(new JLabel("Enter name"));
		championsPan.add(new JLabel("Enter name"));
		
		name1 = new JTextField("Player 1");
		championsPan.add(name1);
		name2 = new JTextField("Player 2");
		championsPan.add(name2);
		name3 = new JTextField("Player 3");
		championsPan.add(name3);
		name4 = new JTextField("Player 4");
		championsPan.add(name4);
		
		Champions1 = new ButtonGroup();
		
		JRadioButton gryffindor1 = new JRadioButton("Gryffindor Wizard");
		JRadioButton hufflepuf1 = new JRadioButton("Hufflepuff Wizard");
		JRadioButton ravenclaw1 = new JRadioButton("Ravenclaw Wizard");
		JRadioButton slytherin1 = new JRadioButton("Slytherin Wizard");
		Champions1.add(gryffindor1);
		Champions1.add(hufflepuf1);
		Champions1.add(ravenclaw1);
		Champions1.add(slytherin1);
		
		Champions2 = new ButtonGroup();
		
		JRadioButton gryffindor2 = new JRadioButton("Gryffindor Wizard");
		JRadioButton hufflepuf2 = new JRadioButton("Hufflepuff Wizard");
		JRadioButton ravenclaw2 = new JRadioButton("Ravenclaw Wizard");
		JRadioButton slytherin2 = new JRadioButton("Slytherin Wizard");
		Champions2.add(gryffindor2);
		Champions2.add(hufflepuf2);
		Champions2.add(ravenclaw2);
		Champions2.add(slytherin2);
		
		
		Champions3 = new ButtonGroup();
		
		JRadioButton gryffindor3 = new JRadioButton("Gryffindor Wizard");
		JRadioButton hufflepuf3 = new JRadioButton("Hufflepuff Wizard");
		JRadioButton ravenclaw3 = new JRadioButton("Ravenclaw Wizard");
		JRadioButton slytherin3 = new JRadioButton("Slytherin Wizard");
		Champions3.add(gryffindor3);
		Champions3.add(hufflepuf3);
		Champions3.add(ravenclaw3);
		Champions3.add(slytherin3);
		
		Champions4 = new ButtonGroup();
		
		JRadioButton gryffindor4 = new JRadioButton("Gryffindor Wizard");
		JRadioButton hufflepuf4 = new JRadioButton("Hufflepuff Wizard");
		JRadioButton ravenclaw4 = new JRadioButton("Ravenclaw Wizard");
		JRadioButton slytherin4 = new JRadioButton("Slytherin Wizard");
		Champions4.add(gryffindor4);
		Champions4.add(hufflepuf4);
		Champions4.add(ravenclaw4);
		Champions4.add(slytherin4);
		
		
		championsPan.add(new JLabel("Choose house"));
		championsPan.add(new JLabel("Choose house"));
		championsPan.add(new JLabel("Choose house"));
		championsPan.add(new JLabel("Choose house"));
		
		
		championsPan.add(gryffindor1);championsPan.add(gryffindor2);championsPan.add(gryffindor3);championsPan.add(gryffindor4);
		championsPan.add(hufflepuf1);championsPan.add(hufflepuf2);championsPan.add(hufflepuf3);championsPan.add(hufflepuf4);
		championsPan.add(ravenclaw1);championsPan.add(ravenclaw2);championsPan.add(ravenclaw3);championsPan.add(ravenclaw4);
		championsPan.add(slytherin1);championsPan.add(slytherin2);championsPan.add(slytherin3);championsPan.add(slytherin4);
		
		
		championsPan.add(new JLabel("Choose Spells"));
		championsPan.add(new JLabel("Choose Spells"));
		championsPan.add(new JLabel("Choose Spells"));
		championsPan.add(new JLabel("Choose Spells"));
		
		
		startBtn = new JButton("Start the Tournament");
		startBtn.setSize(10, 10);
		startPan.add(startBtn);
		
		//setContentPane(new JLabel(new ImageIcon("C:\\Users\\youssef\\Desktop\\guc\\Semester 4\\Computer Programming\\harry-potter\\harrypotter\\src\\startm.jpg")));
		//setForeground(new ImageIcon("C:\\Users\\youssef\\Desktop\\guc\\Semester 4\\Computer Programming\\harry-potter\\harrypotter\\src\\startm.jpg"));
//		startPan.setBackground(Color.black);
//		championsPan.setBackground(Color.black);
		
		//setVisible(true);
	}
	
//	public void addSpell(JCheckBox cb)
//	{
//		spell.add(cb);
//		add(cb);
//		add(cb);
//		add(cb);
//		add(cb);
//		//middle.add(cb);
//		//left.add(cb);
//		
//	}
	
//	public static void main(String[] args)
//	{
//		 new Startmenu2();
//	}

}
