import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTCommConnector;
import lejos.nxt.comm.USB;
import lejos.nxt.comm.USBConnection;
import lejos.nxt.remote.RemoteMotor;
import lejos.nxt.remote.RemoteNXT;

/*
2013-5-17
Samson
 */

public class test {

	static RemoteNXT nxt2;

	
	public static void main(String[] args){
		/**
		LCD.drawString("USB_Connecting...", 0, 0);
		USBConnection conn = USB.waitForConnection();
		DataOutputStream outData = conn.openDataOutputStream();
		DataInputStream inData = conn.openDataInputStream();
		LCD.drawString("USB_Connected...", 0, 0);
		Button.waitForAnyPress();
**/
		LCD.drawString("Cuber2_Connecting...", 0, 1);
		NXTCommConnector connector = Bluetooth.getConnector();
		try {
			nxt2 = new RemoteNXT("Cuber_2", connector);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LCD.drawString("Cuber2_Connected...", 0, 1);
		Button.waitForAnyPress();
		
		
		Motor.A.rotate(60);
		
//		nxt2.A.rotate(-100);
		
		Button.waitForAnyPress();
	}
	

}
  