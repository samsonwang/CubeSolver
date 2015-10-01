package edu.ncut.visualprocess;

import javax.media.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

public class JMFtestMod extends JFrame {

	private static final long serialVersionUID = 1L;
	public Player _player = null;
	public Image img = null;
	public Buffer buffer = null;
	public BufferToImage btoi = null;
	public ImagePanel imagepanel = null;

	public class ImagePanel extends JFrame {

		private static final long serialVersionUID = 1L;

		public ImagePanel() {
			setLayout(new FlowLayout());
			setSize(650, 490);
			setVisible(true);
		}

		public void paint(Graphics g) {
			if (img != null) {
				g.drawImage(img, 0, 0, this);
			}
		}
	}

	public JMFtestMod() {

		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());

		JButton b = new JButton("Start");
		b.setMnemonic('S');
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(b);
		JButton s = new JButton("Stop");
		s.setMnemonic('Q');
		s.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		panel.add(s);
		JButton c = new JButton("Take photo");
		c.setMnemonic('T');
		c.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(c);

		b.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				_player.start();

			}
		});
		s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_player.stop();
			}
		});

		c.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FrameGrabbingControl fgc = (FrameGrabbingControl) _player
						.getControl("javax.media.control.FrameGrabbingControl");

				buffer = fgc.grabFrame();
				btoi = new BufferToImage((VideoFormat) buffer.getFormat());
				img = btoi.createImage(buffer);
				
				BufferedImage bi =new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = (Graphics2D) bi.getGraphics();
				
				
				
				g2.drawImage(img, 0, 0, null);
				
				String lastfilename = "C:\\test.jpeg";
				try {
					javax.imageio.ImageIO.write(bi, "JPEG", new java.io.File(
							lastfilename));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				g2.dispose();
				
				
				new ImagePanel();

			}
		});

		String mediaFile = "vfw:Micrsoft WDM Image Capture (Win32):0";
		try {
			MediaLocator mlr = new MediaLocator(mediaFile);
			_player = Manager.createRealizedPlayer(mlr);
			if (_player.getVisualComponent() != null) {
				panel.add("South", _player.getVisualComponent());
			}
		} catch (Exception e) {
			System.err.println("Got exception " + e);
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}

	public static void main(String[] args) {
		JMFtestMod jmfTest = new JMFtestMod();
		jmfTest.setSize(800, 600);
		jmfTest.setVisible(true);
	}
}