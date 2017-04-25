package codeproject.jimagecomponent.javax.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageBuffer {
	
	private BufferedImage image;
	private Graphics imageGraphics;
	private final int cellID ; // The ID that identifies what type of Image
	
	
	public ImageBuffer()
	{
		cellID = -1;
		this.image = null;
		imageGraphics = null;
	}
	
	public ImageBuffer(String location, int width, int height, int cellID)
	{
		this.cellID = cellID;
		// creates a new image and resizes it
		ImageIcon imageIcon = new ImageIcon(location) ;
		Image img = imageIcon.getImage();
		Image newimg = ((Image) img).getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH ) ;  
		imageIcon = new ImageIcon(newimg);
		
		// sets the buffered image
		this.image = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		this.imageGraphics = this.image.createGraphics();
		this.imageGraphics.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight(), null);
	}
	
	public void setImageComponent(JImageComponent img)
	{
		img.setBufferedImage(this.image);
		img.setImageGraphics(this.imageGraphics);
		img.setID(cellID);
	}

}
