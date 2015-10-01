/*
2013-4-23
Samson
*/

package edu.ncut.visualprocess;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ShowImage extends JFrame {

	private static final long serialVersionUID = 7439894042838764329L;
	
	public Image img = null;

	public ShowImage() {
		try{
			Image image = img;
		}
		catch(Exception e){
			
		}
		Image image = img;
		JLabel label = new JLabel(new ImageIcon(image));
		add(label);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	
	
}
