package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Task1view  extends JFrame {
	
	private JPanel leftUpPan;//champs panel
	private JPanel leftDownPan;// controls panel
	private JPanel rightPan;// map panel
	private JPanel leftPan;// panel containing left up and down
	
	public Task1view(){
		
		//creating the window
		this.setTitle("HarryPotter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50,50,1200,600);
		setResizable(false);

		
		leftUpPan = new JPanel();
		leftDownPan = new JPanel();
		rightPan = new JPanel();
		leftPan = new JPanel();
		
		//setting panels size
		rightPan.setPreferredSize(new Dimension(getWidth()-300, getHeight()));
		leftPan.setPreferredSize(new Dimension(400, getHeight()));
		
		leftPan.setLayout(new GridLayout(0,1));
		leftUpPan.setLayout(new GridLayout(0,3));
		
		//adding the panel
		add(leftPan, BorderLayout.WEST);
		add(rightPan, BorderLayout.EAST);
		
		leftPan.add(leftUpPan);
		leftPan.add(leftDownPan);
		
		leftUpPan.setBackground(Color.RED);
		leftDownPan.setBackground(Color.BLUE);
		rightPan.setBackground(Color.GREEN);
		
		setVisible(true);
	}
	
	public void addtextLU(String x){
		leftUpPan.add(new JLabel(x));
	}
	
	public static void main(String[] args)
	{
		 new Task1view();
	}

}
