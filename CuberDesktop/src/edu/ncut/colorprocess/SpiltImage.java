/*
2013-4-24
Samson
*/


package edu.ncut.colorprocess;

import java.awt.color.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;



public class SpiltImage {
		
	
	private static final int size = 50;
	
	public static List<BufferedImage> splitImage(BufferedImage img)
			throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		subImgs.add(img.getSubimage(290, 200, size, size));//central part
		
		subImgs.add(img.getSubimage(160, 90, size, size));//1
		subImgs.add(img.getSubimage(290, 90, size, size));//2
		subImgs.add(img.getSubimage(420, 90, size, size));//3
		subImgs.add(img.getSubimage(160, 200, size, size));//4
		subImgs.add(img.getSubimage(290, 200, size, size));//5
		subImgs.add(img.getSubimage(420, 200, size, size));//6
		subImgs.add(img.getSubimage(160, 330, size, size));//7
		subImgs.add(img.getSubimage(290, 330, size, size));//8
		subImgs.add(img.getSubimage(420, 330, size, size));//9
		return subImgs;
	}
	
	
	public static void spiltImage() throws Exception{

		
		for(int m=0;m<=7;m++){
			String facepath = "faces\\face"+m+".JPEG";
			
			BufferedImage img = ImageIO.read(new File(facepath));
			
			List<BufferedImage> list = splitImage(img);
			
			for(int i=1;i<=9;i++){
				String pointpath ="list\\lists"+m+"\\point"+i+".JPEG";
				javax.imageio.ImageIO.write(list.get(i), "JPEG", new File(pointpath));
			}
			list.clear();
		}
		

		
	}
	
	
}
