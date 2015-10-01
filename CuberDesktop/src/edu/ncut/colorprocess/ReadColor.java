/*
2013-5-1
Samson
 */

package edu.ncut.colorprocess;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import edu.ncut.cubesoler.*;

public class ReadColor {

	// private static int size = 250;
	// priavte int
	// private static int[] Red = new int[size];
	// private static int[] Green = new int[size];
	// private static int[] Blue = new int[size];
	// BufferedImage img = ImageIO.read(new File(pointpath));

	private static final int orange = 0;
	private static final int red = 1;
	private static final int blue = 2;
	private static final int green = 3;
	private static final int yellow = 4;
	private static final int white = 5;

	private static Image getImage(String path) {

		// MediaTracker tracker = new MediaTracker(this);
		// 用MediaTracker跟踪图像加载
		// Image img = Toolkit.getDefaultToolkit().getImage(path);
		// tracker.addImage(img,0);
		// 等待图像完成加载
		// try { tracker.waitForID(0); }
		// catch(InterruptedException e) { e.printStackTrace();}

		Image img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	private static int getRed(int[] pix, int iw, int ih) {
		int d = 1;
		int r = 0;
		ColorModel cm = ColorModel.getRGBdefault();
		for (int i = 0; i < ih; i = i + d) {
			for (int j = 0; j < iw; j = j + d) {
				// int r = 0, g = 0, b = 0;
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				r = r + cm.getRed(pix[i * iw + j]);
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				// g = g + cm.getGreen(pix[(i+k)*iw+(j+l)]);
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				// b = b + cm.getBlue(pix[(i+k)*iw+(j+l)]);
				// r = (int)(r/dd);
				// g = (int)(g/dd);
				// b = (int)(b/dd);
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				// pix[(i+k)*iw+(j+l)] = 255<<24|r<<16|g<<8|b;
			}

		}
		return r / (iw * ih);
	}

	private static int getGreen(int[] pix, int iw, int ih) {
		int d = 1;
		// int dd=d*d;
		int g = 0;
		ColorModel cm = ColorModel.getRGBdefault();
		for (int i = 0; i < ih; i = i + d) {
			for (int j = 0; j < iw; j = j + d) {
				// int r = 0, g = 0, b = 0;
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				// r = r + cm.getRed(pix[i*iw+j]);
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				g = g + cm.getGreen(pix[i * iw + (j)]);
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				// b = b + cm.getBlue(pix[(i+k)*iw+(j+l)]);
				// r = (int)(r/dd);
				// g = (int)(g/dd);
				// b = (int)(b/dd);
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				// pix[(i+k)*iw+(j+l)] = 255<<24|r<<16|g<<8|b;
			}

		}
		return g / (iw * ih);
	}

	private static int getBlue(int[] pix, int iw, int ih) {
		int d = 1;
		// int dd=d*d;
		int b = 0;
		ColorModel cm = ColorModel.getRGBdefault();
		for (int i = 0; i < ih; i = i + d) {
			for (int j = 0; j < iw; j = j + d) {
				// int r = 0, g = 0, b = 0;
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				// r = r + cm.getRed(pix[i*iw+j]);
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				// g = g + cm.getGreen(pix[(i+k)*iw+(j+l)]);
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				b = b + cm.getBlue(pix[(i) * iw + (j)]);
				// r = (int)(r/dd);
				// g = (int)(g/dd);
				// b = (int)(b/dd);
				// for(int k = 0; k < d; k++)
				// for(int l = 0; l < d; l++)
				// pix[(i+k)*iw+(j+l)] = 255<<24|r<<16|g<<8|b;
			}

		}
		return b / (iw * ih);
	}

	public static int getColorID(int r, int g, int b) {

		int colorid = -2;

		if (r < 100) {
			if (b > g)
				colorid = blue;
			else
				colorid = green;
		} else if (g > 100) {
			if (b > 100)
				colorid = white;
			else
				colorid = yellow;
		} else {
			if (r > 150)
				colorid = orange;
			else
				colorid = red;
		}

		return colorid;
	}

	private static int[] Grabber(Image im, int iw, int ih) {
		int[] pix = new int[iw * ih];
		try {
			PixelGrabber pg = new PixelGrabber(im, 0, 0, iw, ih, pix, 0, iw);
			pg.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return pix;
	}

	public static int[][][] getColorState() {

		int[][][] colorstate = {
				{ { -1, -1, -1, -1 }, { -1, -1, -1, -1 }, { -1, -1, -1, -1 },
						{ -1, -1, -1, -1 } },
				{ { -1, -1, -1, -1 }, { -1, -1, -1, -1 }, { -1, -1, -1, -1 },
						{ -1, -1, -1, -1 } },
				{ { -1, -1, -1, -1 }, { -1, -1, -1, -1 }, { -1, -1, -1, -1 },
						{ -1, -1, -1, -1 } },
				{ { -1, -1, -1, -1 }, { -1, -1, -1, -1 }, { -1, -1, -1, -1 },
						{ -1, -1, -1, -1 } },
				{ { -1, -1, -1, -1 }, { -1, -1, -1, -1 }, { -1, -1, -1, -1 },
						{ -1, -1, -1, -1 } },
				{ { -1, -1, -1, -1 }, { -1, -1, -1, -1 }, { -1, -1, -1, -1 },
						{ -1, -1, -1, -1 } } };
		;

		for (int j = 1; j <= 3; j++) {// 行
			for (int k = 1; k <= 3; k++) {// 列

				int point = (j - 1) * 3 + k;

				String pointpath = "list\\lists0\\point" + point + ".JPEG ";
				Image img = getImage(pointpath);
				int iw = img.getWidth(null);
				int ih = img.getHeight(null);
				int[] pixels = Grabber(img, iw, ih);
				int r = getRed(pixels, iw, ih);
				int g = getGreen(pixels, iw, ih);
				int b = getBlue(pixels, iw, ih);
				colorstate[4][j][k] = getColorID(r, g, b);// y

			}
		}
		if (true) {
			String pointpath = "list\\lists4\\point4.JPEG ";
			Image img = getImage(pointpath);
			int iw = img.getWidth(null);
			int ih = img.getHeight(null);
			int[] pixels = Grabber(img, iw, ih);
			int r = getRed(pixels, iw, ih);
			int g = getGreen(pixels, iw, ih);
			int b = getBlue(pixels, iw, ih);
			colorstate[4][2][1] = getColorID(r, g, b);// y
		}

		if (true) {
			String pointpath = "list\\lists4\\point6.JPEG ";
			Image img = getImage(pointpath);
			int iw = img.getWidth(null);
			int ih = img.getHeight(null);
			int[] pixels = Grabber(img, iw, ih);
			int r = getRed(pixels, iw, ih);
			int g = getGreen(pixels, iw, ih);
			int b = getBlue(pixels, iw, ih);
			colorstate[4][2][3] = getColorID(r, g, b);// y
		}

		for (int j = 1; j <= 3; j++) {// 行
			for (int k = 1; k <= 3; k++) {// 列

				int point = (j - 1) * 3 + k;

				String pointpath = "list\\lists1\\point" + point + ".JPEG ";
				Image img = getImage(pointpath);
				int iw = img.getWidth(null);
				int ih = img.getHeight(null);
				int[] pixels = Grabber(img, iw, ih);
				int r = getRed(pixels, iw, ih);
				int g = getGreen(pixels, iw, ih);
				int b = getBlue(pixels, iw, ih);
				colorstate[0][j][k] = getColorID(r, g, b);// o

			}
		}

		for (int j = 1; j <= 3; j++) {// 行
			for (int k = 1; k <= 3; k++) {// 列

				int point = (j - 1) * 3 + k;

				String pointpath = "list\\lists2\\point" + point + ".JPEG ";
				Image img = getImage(pointpath);
				int iw = img.getWidth(null);
				int ih = img.getHeight(null);
				int[] pixels = Grabber(img, iw, ih);
				int r = getRed(pixels, iw, ih);
				int g = getGreen(pixels, iw, ih);
				int b = getBlue(pixels, iw, ih);
				colorstate[5][j][k] = getColorID(r, g, b);// w

			}
		}

		for (int j = 1; j <= 3; j++) {// 行
			for (int k = 1; k <= 3; k++) {// 列

				int point = (j - 1) * 3 + k;
				point = 10 - point;// order reverse
				String pointpath = "list\\lists3\\point" + point + ".JPEG ";
				Image img = getImage(pointpath);
				int iw = img.getWidth(null);
				int ih = img.getHeight(null);
				int[] pixels = Grabber(img, iw, ih);
				int r = getRed(pixels, iw, ih);
				int g = getGreen(pixels, iw, ih);
				int b = getBlue(pixels, iw, ih);
				colorstate[1][j][k] = getColorID(r, g, b);// r

			}
		}

		for (int j = 1; j <= 3; j++) {// 行
			for (int k = 1; k <= 3; k++) {// 列

				int point = (j - 1) * 3 + k;

				String pointpath = "list\\lists5\\point" + point + ".JPEG ";
				Image img = getImage(pointpath);
				int iw = img.getWidth(null);
				int ih = img.getHeight(null);
				int[] pixels = Grabber(img, iw, ih);
				int r = getRed(pixels, iw, ih);
				int g = getGreen(pixels, iw, ih);
				int b = getBlue(pixels, iw, ih);
				colorstate[3][j][k] = getColorID(r, g, b);// g

			}
		}

		if (true) {
			String pointpath = "list\\lists6\\point6.JPEG ";
			Image img = getImage(pointpath);
			int iw = img.getWidth(null);
			int ih = img.getHeight(null);
			int[] pixels = Grabber(img, iw, ih);
			int r = getRed(pixels, iw, ih);
			int g = getGreen(pixels, iw, ih);
			int b = getBlue(pixels, iw, ih);
			colorstate[5][2][1] = getColorID(r, g, b);// w
		}

		if (true) {
			String pointpath = "list\\lists6\\point4.JPEG ";
			Image img = getImage(pointpath);
			int iw = img.getWidth(null);
			int ih = img.getHeight(null);
			int[] pixels = Grabber(img, iw, ih);
			int r = getRed(pixels, iw, ih);
			int g = getGreen(pixels, iw, ih);
			int b = getBlue(pixels, iw, ih);
			colorstate[5][2][3] = getColorID(r, g, b);// w
		}

		for (int j = 1; j <= 3; j++) {// 行
			for (int k = 1; k <= 3; k++) {// 列

				int point = (j - 1) * 3 + k;

				String pointpath = "list\\lists7\\point" + point + ".JPEG ";
				Image img = getImage(pointpath);
				int iw = img.getWidth(null);
				int ih = img.getHeight(null);
				int[] pixels = Grabber(img, iw, ih);
				int r = getRed(pixels, iw, ih);
				int g = getGreen(pixels, iw, ih);
				int b = getBlue(pixels, iw, ih);
				colorstate[2][j][k] = getColorID(r, g, b);// b

			}
		}

		// correct the g and b
		CubeSolver.Face(colorstate, 2);
		CubeSolver.Face(colorstate, 3);
		CubeSolver.Face(colorstate, 3);
		CubeSolver.Face(colorstate, 3);

		return colorstate;
	}

	public static void main(String[] args) {

		// int[][][] colorstate = getColorState();

		System.out.println("pause");

		String pointpath = "list\\lists1\\point2.JPEG ";
		Image img = getImage(pointpath);
		int iw = img.getWidth(null);
		int ih = img.getHeight(null);
		int[] pixels = Grabber(img, iw, ih);
		int r = getRed(pixels, iw, ih);
		int g = getGreen(pixels, iw, ih);
		int b = getBlue(pixels, iw, ih);
		int color = getColorID(r, g, b);
		System.out.println("Color = " + color);
		System.out.println("r= " + r);
		System.out.println("g= " + g);
		System.out.println("b= " + b);

	}

}
