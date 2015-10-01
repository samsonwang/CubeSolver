/*
2013-5-5
Samson
*/

package edu.ncut.main;

public class getActionID {

	public static int getActionID (String str){
		
		int ActionID = -1;
		
		
		if(str.equals("f1;"))	ActionID=1;
		if(str.equals("f2;"))	ActionID=2;
		if(str.equals("f3;"))	ActionID=3;
		
		if(str.equals("b1;"))	ActionID=4;
		if(str.equals("b2;"))	ActionID=5;
		if(str.equals("b3;"))	ActionID=6;
		
		if(str.equals("l1;"))	ActionID=7;
		if(str.equals("l2;"))	ActionID=8;
		if(str.equals("l3;"))	ActionID=9;
		
		if(str.equals("r1;"))	ActionID=10;
		if(str.equals("r2;"))	ActionID=11;
		if(str.equals("r3;"))	ActionID=12;
		
		if(str.equals("u1;"))	ActionID=13;
		if(str.equals("u2;"))	ActionID=14;
		if(str.equals("u3;"))	ActionID=15;
		
		if(str.equals("d1;"))	ActionID=16;
		if(str.equals("d2;"))	ActionID=17;
		if(str.equals("d3;"))	ActionID=18;
		
		return ActionID;
	}
	
	
	
	
}
