/*
2013-4-22
Samson
 */

package edu.ncut.colorprocess;

import javax.media.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

//import javax.swing.ImageIcon;
//import edu.ncut.visualprocess.JMFtestMod.ImagePanel;
//import edu.ncut.visualprocess.ShowImage;
//import edu.ncut.visualprocess.*;

public class Capture extends JFrame{


	private static final long serialVersionUID = -5687530308815359179L;


	public Player _player = null;
	public Image img = null;
	public Buffer buffer = null;
	public BufferToImage btoi = null;
	public String mediaFile = "vfw:Micrsoft WDM Image Capture (Win32):0";

	
//构造函数
	public Capture(){
		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());
		panel.setSize(650, 490);		
		try {
			MediaLocator mlr = new MediaLocator(mediaFile);
			_player = Manager.createRealizedPlayer(mlr);
			if (_player.getVisualComponent() != null) {
				panel.add("South", _player.getVisualComponent());
			}
		} catch (Exception e) {
			System.err.println("Got exception " + e);
		}
		
		_player.start();
		panel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				new Capture().saveImage("faces\\test.jpeg");
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	
	}
//获取并保存图像
	public void saveImage(String savepath){
		
		FrameGrabbingControl fgc = (FrameGrabbingControl) _player
				.getControl("javax.media.control.FrameGrabbingControl");

		buffer = fgc.grabFrame();
		btoi = new BufferToImage((VideoFormat) buffer.getFormat());
		img = btoi.createImage(buffer);
		
		BufferedImage bi =new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) bi.getGraphics();
			
		g2.drawImage(img, 0, 0, null);
		
//		String filepath = savepath;
		try {
			javax.imageio.ImageIO.write(bi, "JPEG", new File(savepath));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		g2.dispose();
	}


//	public void getImage() {}

	public static void main(String[] args) throws InterruptedException {
		Capture capturepanel = new Capture();

		capturepanel.setVisible(true);
		
		Thread.sleep(10000);
		String face0="faces\\face0.jpeg";
		String face1="faces\\face1.jpeg";
		String face2="faces\\face2.jpeg";
		String face3="faces\\face3.jpeg";
		String face4="faces\\face4.jpeg";
		String face5="faces\\face5.jpeg";
		
//		String face1="faces\\face1.jpeg";
//		String face2="faces\\face2.jpeg";
//		String test="C:\\test.jpeg";
		
		capturepanel.saveImage(face1);
//		Thread.sleep(10000);
		capturepanel.saveImage(face2);

		
//		capturepanel.saveImage(test);
		
	}

}
