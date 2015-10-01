/*
2013-5-5
Samson
 */

package edu.ncut.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;

import edu.ncut.colorprocess.*;
import edu.ncut.cubesoler.*;

public class Arbiter {

	private static final int o = 0;
	private static final int r = 1;
	private static final int b = 2;
	private static final int g = 3;
	private static final int y = 4;
	private static final int w = 5;

	private static boolean isCorrect = false;

	private static void isReady(DataInputStream inData, DataOutputStream outData) {
		try {
			outData.writeInt(1);
			outData.flush();
		} catch (IOException ioe) {
			System.err.println("IO Exception writing bytes");
		}
	}

	private static void getColor(DataInputStream inData,
			DataOutputStream outData, Capture capturepanel) {
		// int t = -1;
		boolean flag = true;
		while (flag) {
			try {
				int t = inData.readInt();
				switch (t) {
				case 0:
					capturepanel.saveImage("faces\\face" + t + ".JPEG");
					break;
				case 1:
					capturepanel.saveImage("faces\\face" + t + ".JPEG");
					break;
				case 2:
					capturepanel.saveImage("faces\\face" + t + ".JPEG");
					break;
				case 3:
					capturepanel.saveImage("faces\\face" + t + ".JPEG");
					break;
				case 4:
					capturepanel.saveImage("faces\\face" + t + ".JPEG");
					break;
				case 5:
					capturepanel.saveImage("faces\\face" + t + ".JPEG");
					break;
				case 6:
					capturepanel.saveImage("faces\\face" + t + ".JPEG");
					break;
				case 7:
					capturepanel.saveImage("faces\\face" + t + ".JPEG");
					break;
				case -1:
					flag = false;
					break;
				}
			} catch (IOException ioe) {
				System.err.println("IO Exception reading reply");
			}
		}
	}

	public static void main(String[] args) {

		NXTConnector conn = new NXTConnector();
		if (!conn.connectTo("usb://")) {
			System.err.println("No NXT found using USB");
			System.exit(1);
		}
		DataInputStream inData = new DataInputStream(conn.getInputStream());
		DataOutputStream outData = new DataOutputStream(conn.getOutputStream());

		// Cam Initialized
		Capture capturepanel = new Capture();
		capturepanel.setVisible(true);
		System.out.println("Webcam initialized.");

		// 获取魔方颜色数据
		System.out.println("Getting color.");
		getColor(inData, outData, capturepanel);
		try {
			SpiltImage.spiltImage();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int[][][] colorstate1 = ReadColor.getColorState();

		int[][][] colorstate = {
				{ { 0, 0, 0, 0 }, { 0, y, o, r }, { 0, o, o, o },
					{ 0, o, o, o } },
			{ { 0, 0, 0, 0 }, { 0, y, r, g }, { 0, r, r, r },
					{ 0, r, r, r } },
			{ { 0, 0, 0, 0 }, { 0, y, g, g }, { 0, b, b, b },
					{ 0, b, b, b } },
			{ { 0, 0, 0, 0 }, { 0, o, b, b }, { 0, g, g, g },
					{ 0, g, g, g } },
			{ { 0, 0, 0, 0 }, { 0, y, y, r }, { 0, y, y, y },
					{ 0, o, y, b } },
			{ { 0, 0, 0, 0 }, { 0, w, w, w }, { 0, w, w, w },
					{ 0, w, w, w } } };
		// 判断颜色数据是否合理
		isCorrect = StateCalibrate.StateCalibrate(colorstate);

		// get the solve method
		System.out.println("Getting method.");
		List<String> Method = CubeSolver.Solver(colorstate);

		// Method Ready
		/**
		 * try { int m = inData.readInt(); // if (m == -10) {
		 * System.out.println("Cube Ready."); } } catch (Exception e) { //
		 * break; }
		 **/
		// send method
		System.out.println("Sending method.");
		for (int i = 0; i < Method.size(); i++) {
			System.out.print((String) Method.get(i) + " ");
			String str = (String) Method.get(i);
			int actionID = getActionID.getActionID(str);
			try {
				System.out.println(actionID);
				outData.writeInt(actionID);
				outData.flush();
			} catch (IOException ioe) {
				System.err.println("IO Exception writing bytes");
			}
		}

	}

}
