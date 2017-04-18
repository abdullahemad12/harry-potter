package harrypotter.view;
import javax.swing.*;

import java.awt.*;
 

public class StartMenuView extends JFrame{
	
	private JPanel left; // left portion of the screen 
	private JPanel middle;// middle portion of the screen 
	private JPanel right;// right portion of the screen 
	private ButtonGroup Champions; // all champions 
	private ButtonGroup Spells; // all available spells   
	private JTextField name; // name of the player
	public StartMenuView()
	{
		this.setTitle("HarryPotter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50,50,1200,600);
		setVisible(true);
	
		// sets the radio buttons of the champions
		Champions = new ButtonGroup();
		
		JRadioButton gryffindor = new JRadioButton("Gryffindor Wizard");
		JRadioButton hufflepuf = new JRadioButton("Hufflepuff Wizard");
		JRadioButton ravenclaw = new JRadioButton("Ravenclaw Wizard");
		JRadioButton slytherin = new JRadioButton("Slytherin Wizard");

		
		Champions.add(gryffindor);
		Champions.add(hufflepuf);
		Champions.add(ravenclaw);
		Champions.add(slytherin);
		
		// setting the left panel
		left = new JPanel();
		add(left, BorderLayout.WEST);
		left.setBackground(Color.red);
		left.setPreferredSize(new Dimension(400,600));
		left.add(gryffindor);
		left.add(hufflepuf);
		left.add(ravenclaw);
		left.add(slytherin);
		
		
		middle = new JPanel();
		name = new JTextField();
		middle.setBackground(Color.BLUE);
		middle.setPreferredSize(new Dimension(400,600));
		middle.add(name);
		add(middle, BorderLayout.CENTER);
		
		right = new JPanel();
		add(right, BorderLayout.EAST);
		right.setBackground(Color.YELLOW);
		right.setPreferredSize(new Dimension(400,600));
		
	}

	public static void main(String[] args)
	{
		 new StartMenuView();
	}
}
