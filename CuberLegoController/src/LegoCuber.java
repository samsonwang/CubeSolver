//package org.lejos.sample.usbreceive;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTCommConnector;
import lejos.nxt.comm.USB;
import lejos.nxt.comm.USBConnection;
import lejos.nxt.remote.RemoteMotor;
import lejos.nxt.remote.RemoteNXT;

public class LegoCuber {

	static final NXTRegulatedMotor Motor_C1 = Motor.A;
	static final NXTRegulatedMotor Motor_F = Motor.B;
	static final NXTRegulatedMotor Motor_B = Motor.C;

	static RemoteNXT nxt2;

	static final int c1_angel = 90;
	static final int c2_angel = 140;
	static final int r_angel = 90;

	public static class Left extends Thread {
		public void run() {
			nxt2.B.rotate(-r_angel);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Right extends Thread {
		public void run() {
			nxt2.C.rotate(r_angel);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Front extends Thread {
		public void run() {
			Motor_F.rotate(-r_angel);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Back extends Thread {
		public void run() {
			Motor_B.rotate(-r_angel);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Left_r extends Thread {
		public void run() {
			nxt2.B.rotate(r_angel);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Right_r extends Thread {
		public void run() {
			nxt2.C.rotate(-r_angel);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Front_r extends Thread {
		public void run() {
			Motor_F.rotate(r_angel);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Back_r extends Thread {
		public void run() {
			Motor_B.rotate(r_angel);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {

		LCD.drawString("USB_Connecting...", 0, 0);
		USBConnection conn = USB.waitForConnection();
		DataOutputStream outData = conn.openDataOutputStream();
		DataInputStream inData = conn.openDataInputStream();
		LCD.drawString("USB_Connected...", 0, 0);
		// Button.waitForAnyPress();

		LCD.drawString("Cuber2_Connecting...", 0, 1);
		NXTCommConnector connector = Bluetooth.getConnector();
		try {
			nxt2 = new RemoteNXT("Cuber_2", connector);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		LCD.drawString("Cuber2_Connected...", 0, 1);
		Button.waitForAnyPress();

		// put the cube in ; use 4 paws ; getColor
		// doMove(21);//lr close
		// doMove(20);//fb close
		doMove(27);// fb open
		// doMove(21);//lr close
		doMove(28);// lr open
		// Button.waitForAnyPress();
		/**
		 * doMove(19);// 4 close doMove(26);// 4 open //
		 * Button.waitForAnyPress(); doMove(1);// f doMove(3);// f' //
		 * Button.waitForAnyPress(); doMove(4);// b doMove(6);// b' //
		 * Button.waitForAnyPress(); doMove(7);// l doMove(9);// l' //
		 * Button.waitForAnyPress(); doMove(10);// r6 doMove(12);// r' //
		 * Button.waitForAnyPress(); doMove(22);// fb r doMove(23);// fb r' //
		 * Button.waitForAnyPress(); doMove(24);// lr r doMove(25);// lr r'
		 **/
		LCD.drawString("Cube_ready?", 0, 2);
		Button.waitForAnyPress();

		doMove(19);
		Button.waitForAnyPress();
		/**
		for (int i = 1; i < 38; i++) {
			doMove(i);
			Button.waitForAnyPress();
		}
**/
		// 颜色识别部分
		getColorCapture(inData, outData);
		LCD.drawString("Method_ready?", 0, 3);
		Button.waitForAnyPress();

		/**
		 * try { // m = inData.readInt(); // doMove(m); doMove(22); //
		 * 
		 * 
		 * Button.waitForAnyPress(); // Thread l2 = new Left(); // Thread r2 =
		 * new Right(); // l2.start(); // r2.start();
		 * 
		 * } catch (Exception e) { }
		 * 
		 * 
		 * try { outData.writeInt(-10);// outData.flush(); } catch (IOException
		 * e) { }
		 **/
		while (true) {
			int m = 0;
			try {
				m = inData.readInt(); //
				doMove(m); //
				if (m == -1) {
					LCD.drawString("Action Err", 0, 4);
				}
			} catch (Exception e) {
				break;
			}
		}

		// ??在判断动作完成时还需要一个动作，将true置为false

		// 动作完成，关闭数据流
		// outData.close();
		// inData.close();
		// conn.close();

	}

	public static void doMove(int actionid) {
		// f
		if (actionid == 1) {
			Motor_F.rotate(-r_angel);
			doMove(27);// fb open
			Motor_F.rotate(r_angel);
			doMove(20);// fb close
		}
		if (actionid == 2) {
			Motor_F.rotate(-r_angel * 2);
		}
		if (actionid == 3) {
			Motor_F.rotate(r_angel);
			doMove(27);// fb open
			Motor_F.rotate(r_angel);
			doMove(20);// fb close
		}

		// B
		if (actionid == 4) {
			Motor_B.rotate(r_angel);
			doMove(27);// fb open
			Motor_B.rotate(r_angel);
			doMove(20);// fb close
		}
		if (actionid == 5) {
			Motor_B.rotate(r_angel * 2);
		}
		if (actionid == 6) {
			Motor_B.rotate(-r_angel);
			doMove(27);// fb open
			Motor_B.rotate(r_angel);
			doMove(20);// fb close
		}

		// L
		if (actionid == 7) {
			nxt2.B.rotate(r_angel);
			doMove(28);// lr open
			nxt2.B.rotate(r_angel);
			doMove(21);// ;lr close
		}
		if (actionid == 8) {
			nxt2.B.rotate(r_angel * 2);
		}
		if (actionid == 9) {
			nxt2.B.rotate(-r_angel);
			doMove(28);// lr open
			nxt2.B.rotate(r_angel);
			doMove(21);// ;r close
		}

		// R
		if (actionid == 10) {
			nxt2.C.rotate(r_angel);
			doMove(28);// lr open
			nxt2.C.rotate(r_angel);
			doMove(21);// ;lr close
		}
		if (actionid == 11) {
			nxt2.C.rotate(r_angel * 2);
		}
		if (actionid == 12) {
			nxt2.C.rotate(-r_angel);
			doMove(28);// lr open
			nxt2.C.rotate(r_angel);
			doMove(21);// ;lr close
		}

		// U
		if (actionid == 13) {

			doMove(27);// fb open
			doMove(25);// lr r'
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(24);// lr r
			doMove(21);// lr close
			doMove(1);// f r
			doMove(27);// fb open
			doMove(3);// f r'
			doMove(24);// lr r
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(25);// lr r'
			doMove(21);// lr close
		}

		if (actionid == 14) {
			doMove(27);// fb open
			doMove(25);// lr r'
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(24);// lr r
			doMove(21);// lr close
			doMove(2);// f 2r
			doMove(27);// fb open

			doMove(24);// lr r
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(25);// lr r'
			doMove(21);// lr close
		}

		if (actionid == 15) {
			doMove(27);// fb open
			doMove(25);// lr r'
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(24);// lr r
			doMove(21);// lr close
			doMove(3);// f r'
			doMove(27);// fb open
			doMove(3);// f r
			doMove(24);// lr r
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(25);// lr r'
			doMove(21);// lr close
		}

		// D
		if (actionid == 16) {
			doMove(27);// fb open
			doMove(24);// lr r
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(25);// lr r'
			doMove(21);// lr close
			doMove(1);// f r
			doMove(27);// fb open
			doMove(3);// f r'
			doMove(25);// lr r'
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(24);// lr r
			doMove(21);// lr close
		}

		if (actionid == 17) {
			doMove(27);// fb open
			doMove(24);// lr r
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(25);// lr r'
			doMove(21);// lr close
			doMove(2);// f 2r
			doMove(27);// fb open
			doMove(25);// lr r'
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(24);// lr r
			doMove(21);// lr close
		}
		if (actionid == 18) {
			doMove(27);// fb open
			doMove(24);// lr r
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(25);// lr r'
			doMove(21);// lr close
			doMove(3);// f r'
			doMove(27);// fb open
			doMove(1);// f r
			doMove(25);// lr r'
			doMove(20);// fb close
			doMove(28);// lr open
			doMove(24);// lr r
			doMove(21);// lr close
		}

		// 应该增加整体旋转的方法
		if (actionid == 19) {// 4 close
			Motor_C1.rotate(c1_angel + 35);
			nxt2.A.rotate(-c2_angel - 25);
		}
		if (actionid == 20) {// F&B close
			Motor_C1.rotate(c1_angel + 35);
		}
		if (actionid == 21) {// L&R close
			nxt2.A.rotate(-c2_angel - 25);
		}

		if (actionid == 22) {// F&B r
			Thread t1 = new Front();
			Thread t2 = new Back();
			t1.start();
			t2.start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Motor_F.rotate(r_angel);
			// Motor_B.rotate(r_angel);
		}
		if (actionid == 23) {// F&B r'
			Thread t1 = new Front_r();
			Thread t2 = new Back_r();
			t1.start();
			t2.start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (actionid == 24) {// L&R r
			Thread t1 = new Left();
			Thread t2 = new Right();
			t1.start();
			t2.start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (actionid == 25) {// L&R r'
			Thread t1 = new Left_r();
			Thread t2 = new Right_r();
			t1.start();
			t2.start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (actionid == 26) {// 4 open
			Motor_C1.rotate(-c1_angel);
			nxt2.A.rotate(c2_angel + 20);
		}
		if (actionid == 27) {// F&B open
			Motor_C1.rotate(-c1_angel - 30);
		}
		if (actionid == 28) {// L&R open
			nxt2.A.rotate(c2_angel + 20);
		}

	}

	public static void getColorCapture(DataInputStream inData,
			DataOutputStream outData) throws InterruptedException {
		doMove(27);// F&B open
		Thread.sleep(1000);
		try {
			outData.writeInt(0);// y
			outData.flush();
		} catch (IOException e) {
		}
		doMove(24);// L&R r
		Thread.sleep(1000);
		try {
			outData.writeInt(1);// o
			outData.flush();
		} catch (IOException e) {
		}
		doMove(24);// L&R r
		Thread.sleep(1000);
		try {
			outData.writeInt(2);// w
			outData.flush();
		} catch (IOException e) {
		}
		doMove(24);// L&R r
		Thread.sleep(1000);
		try {
			outData.writeInt(3);// r
			outData.flush();
		} catch (IOException e) {
		}

		doMove(24);// L&R r
		doMove(20);// FB close
		doMove(28);// LR open
		Thread.sleep(1000);
		try {
			outData.writeInt(4);// y2
			outData.flush();
		} catch (IOException e) {
		}
		doMove(22);// FB r
		Thread.sleep(1000);
		try {
			outData.writeInt(5);// g
			outData.flush();
		} catch (IOException e) {
		}
		doMove(22);// FB r
		Thread.sleep(1000);
		try {
			outData.writeInt(6);// w2
			outData.flush();
		} catch (IOException e) {
		}
		doMove(22);// FB r
		Thread.sleep(1000);
		try {
			outData.writeInt(7);// b
			outData.flush();
		} catch (IOException e) {
		}
		doMove(22);// FB r
		Thread.sleep(1000);
		try {
			outData.writeInt(-1);
			outData.flush();
		} catch (IOException e) {
		}
		doMove(21);
		Thread.sleep(1000);
	}
}
