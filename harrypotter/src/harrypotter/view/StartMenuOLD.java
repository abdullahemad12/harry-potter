package harrypotter.view;
import javax.swing.*;



public class StartMenuOLD extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*private JPanel left; // left portion of the screen 
	private JPanel middle;// middle portion of the screen 
	private JPanel right;// right portion of the screen 
	private ButtonGroup Champions; // all champions 
	private JTextField name; // name of the player
	private ArrayList<JCheckBox> spell;
	private int currentChamp; // counter of the number of champions 
	public StartMenuView()
	{
		spell = new ArrayList<JCheckBox>();
		this.setTitle("HarryPotter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50,50,1200,600);
		
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
		middle.add(name);
		middle.setBackground(Color.BLUE);
		middle.setPreferredSize(new Dimension(400,600));
		name = new JTextField("Enter Your Name", 10);
		middle.setBackground(Color.BLUE);
		middle.setPreferredSize(new Dimension(400,600));
		middle.add(name);
		//middle.add(name);
		add(middle, BorderLayout.CENTER);
		
		right = new JPanel();
		add(right, BorderLayout.EAST);
		right.setBackground(Color.YELLOW);
		right.setPreferredSize(new Dimension(400,600));
		
	}
	
	public void addSpell(JCheckBox cb)
	{
		spell.add(cb);
		right.add(cb);
		//middle.add(cb);
		//left.add(cb);
		
	}

	public static void main(String[] args)
	{
		 new StartMenuView();
	}*/
}