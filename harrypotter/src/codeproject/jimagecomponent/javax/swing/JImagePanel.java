package codeproject.jimagecomponent.javax.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

	public class JImagePanel extends JPanel {
	
	  private Image img;
	
	  public JImagePanel(String img, int width, int height) {
	    this(new ImageIcon(img).getImage(), width,height);
	  }
	
	
	public JImagePanel(Image img, int width, int height) {
		this.img = img;
	    Dimension size = new Dimension(width, width);
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	  }
	
	  public void paintComponent(Graphics g) {
		  g.drawImage(img, 0, 0, getWidth(), getHeight(), this);	    
	  }

}


