package harrypotter.view;

import javax.swing.JFrame;

public class loadingView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public loadingView()
	{
		this.setTitle("Loading...");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50,50,1200,600);
		setResizable(false);
	}
		
	public void setVisibility(boolean visible)
	{
		setVisible(visible);
	}

}