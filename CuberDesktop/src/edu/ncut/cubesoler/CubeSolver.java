/*
2013-4-26
Samson
 */

package edu.ncut.cubesoler;

import java.lang.*;

import java.util.*;

public class CubeSolver {

	private static final int up = 4;
	private static final int down = 5;
	private static final int right = 2;
	private static final int left = 3;
	private static final int front = 0;
	private static final int back = 1;
	private static final int o = 0;
	private static final int r = 1;
	private static final int b = 2;
	private static final int g = 3;
	private static final int y = 4;
	private static final int w = 5;

	public static int[][][] cubestate = new int[6][3][3];
	public static int[][][] colorstate = new int[6][3][3];
	public static String solvemethod;
	public static List<String> solve = new ArrayList<String>();

	// 魔方单步转换方法
	public static void Face(int[][][] cube, int face) {

		int t1 = cube[face][3][1];
		int t2 = cube[face][2][1];
		int t3 = cube[face][1][1];
		int t4 = cube[face][3][2];
		int t5 = cube[face][1][2];
		int t6 = cube[face][3][3];
		int t7 = cube[face][2][3];
		int t8 = cube[face][1][3];

		cube[face][1][1] = t1;
		cube[face][1][2] = t2;
		cube[face][1][3] = t3;
		cube[face][2][1] = t4;
		cube[face][2][3] = t5;
		cube[face][3][1] = t6;
		cube[face][3][2] = t7;
		cube[face][3][3] = t8;

		// return cube;
	}

	private static void F(int[][][] cube, int i) {

		for (; i > 0; i--) {
			Face(cube, front);

			int t1 = cube[down][1][1];
			int t2 = cube[down][1][2];
			int t3 = cube[down][1][3];

			int t4 = cube[left][1][3];
			int t5 = cube[left][2][3];
			int t6 = cube[left][3][3];

			int t7 = cube[up][3][3];
			int t8 = cube[up][3][2];
			int t9 = cube[up][3][1];

			int t10 = cube[right][3][1];
			int t11 = cube[right][2][1];
			int t12 = cube[right][1][1];

			//
			cube[left][1][3] = t1;
			cube[left][2][3] = t2;
			cube[left][3][3] = t3;

			cube[up][3][3] = t4;
			cube[up][3][2] = t5;
			cube[up][3][1] = t6;

			cube[right][3][1] = t7;
			cube[right][2][1] = t8;
			cube[right][1][1] = t9;

			cube[down][1][1] = t10;
			cube[down][1][2] = t11;
			cube[down][1][3] = t12;

		}
		// return cube;
	}

	private static void B(int[][][] cube, int i) {
		// int[][][] t_cube = new
		// int[cube.length][cube[0].length][cube[0][0].length];
		// System.arraycopy(cube, 0, t_cube, 0, cube.length);

		for (; i > 0; i--) {
			Face(cube, back);

			int t1 = cube[down][3][3];
			int t2 = cube[down][3][2];
			int t3 = cube[down][3][1];

			int t4 = cube[right][1][3];
			int t5 = cube[right][2][3];
			int t6 = cube[right][3][3];

			int t7 = cube[up][1][1];
			int t8 = cube[up][1][2];
			int t9 = cube[up][1][3];

			int t10 = cube[left][3][1];
			int t11 = cube[left][2][1];
			int t12 = cube[left][1][1];

			//

			cube[right][1][3] = t1;
			cube[right][2][3] = t2;
			cube[right][3][3] = t3;

			cube[up][1][1] = t4;
			cube[up][1][2] = t5;
			cube[up][1][3] = t6;

			cube[left][3][1] = t7;
			cube[left][2][1] = t8;
			cube[left][1][1] = t9;

			cube[down][3][3] = t10;
			cube[down][3][2] = t11;
			cube[down][3][1] = t12;

		}
	}

	private static void U(int[][][] cube, int i) {

		int[][][] t_cube = new int[cube.length][cube[0].length][cube[0][0].length];
		System.arraycopy(cube, 0, t_cube, 0, cube.length);

		for (; i > 0; i--) {
			Face(cube, up);

			int t1 = cube[front][1][1];
			int t2 = cube[front][1][2];
			int t3 = cube[front][1][3];

			int t4 = cube[left][1][1];
			int t5 = cube[left][1][2];
			int t6 = cube[left][1][3];

			int t7 = cube[back][1][1];
			int t8 = cube[back][1][2];
			int t9 = cube[back][1][3];

			int t10 = cube[right][1][1];
			int t11 = cube[right][1][2];
			int t12 = cube[right][1][3];

			//

			cube[left][1][1] = t1;
			cube[left][1][2] = t2;
			cube[left][1][3] = t3;

			cube[back][1][1] = t4;
			cube[back][1][2] = t5;
			cube[back][1][3] = t6;

			cube[right][1][1] = t7;
			cube[right][1][2] = t8;
			cube[right][1][3] = t9;

			cube[front][1][1] = t10;
			cube[front][1][2] = t11;
			cube[front][1][3] = t12;
		}
		// return t_cube;
	}

	private static void D(int[][][] cube, int i) {
		// int[][][] t_cube = new
		// int[cube.length][cube[0].length][cube[0][0].length];
		// System.arraycopy(cube, 0, t_cube, 0, cube.length);

		for (; i > 0; i--) {
			Face(cube, down);

			int t1 = cube[front][3][1];
			int t2 = cube[front][3][2];
			int t3 = cube[front][3][3];

			int t4 = cube[right][3][1];
			int t5 = cube[right][3][2];
			int t6 = cube[right][3][3];

			int t7 = cube[back][3][1];
			int t8 = cube[back][3][2];
			int t9 = cube[back][3][3];

			int t10 = cube[left][3][1];
			int t11 = cube[left][3][2];
			int t12 = cube[left][3][3];

			//

			cube[right][3][1] = t1;
			cube[right][3][2] = t2;
			cube[right][3][3] = t3;

			cube[back][3][1] = t4;
			cube[back][3][2] = t5;
			cube[back][3][3] = t6;

			cube[left][3][1] = t7;
			cube[left][3][2] = t8;
			cube[left][3][3] = t9;

			cube[front][3][1] = t10;
			cube[front][3][2] = t11;
			cube[front][3][3] = t12;

			// t_cube = cube;
		}
		// return t_cube;
	}

	private static void L(int[][][] cube, int i) {
		// int[][][] t_cube = new
		// int[cube.length][cube[0].length][cube[0][0].length];
		// System.arraycopy(cube, 0, t_cube, 0, cube.length);

		for (; i > 0; i--) {
			Face(cube, left);
			int t1 = cube[back][3][3];
			int t2 = cube[back][2][3];
			int t3 = cube[back][1][3];

			int t4 = cube[down][3][1];
			int t5 = cube[down][2][1];
			int t6 = cube[down][1][1];

			int t7 = cube[front][1][1];
			int t8 = cube[front][2][1];
			int t9 = cube[front][3][1];

			int t10 = cube[up][1][1];
			int t11 = cube[up][2][1];
			int t12 = cube[up][3][1];

			//

			cube[up][1][1] = t1;
			cube[up][2][1] = t2;
			cube[up][3][1] = t3;

			cube[back][1][3] = t4;
			cube[back][2][3] = t5;
			cube[back][3][3] = t6;

			cube[down][1][1] = t7;
			cube[down][2][1] = t8;
			cube[down][3][1] = t9;

			cube[front][1][1] = t10;
			cube[front][2][1] = t11;
			cube[front][3][1] = t12;

			// t_cube = cube;
		}
		// return t_cube;
	}

	private static void R(int[][][] cube, int i) {
		// int[][][] temp = new
		// int[cube.length][cube[0].length][cube[0][0].length];
		// System.arraycopy(cube, 0, temp, 0, cube.length);

		for (; i > 0; i--) {
			Face(cube, right);
			int t1 = cube[front][1][3];
			int t2 = cube[front][2][3];
			int t3 = cube[front][3][3];

			int t4 = cube[down][1][3];
			int t5 = cube[down][2][3];
			int t6 = cube[down][3][3];

			int t7 = cube[back][3][1];
			int t8 = cube[back][2][1];
			int t9 = cube[back][1][1];

			int t10 = cube[up][1][3];
			int t11 = cube[up][2][3];
			int t12 = cube[up][3][3];

			//

			cube[up][1][3] = t1;
			cube[up][2][3] = t2;
			cube[up][3][3] = t3;

			cube[front][1][3] = t4;
			cube[front][2][3] = t5;
			cube[front][3][3] = t6;

			cube[down][1][3] = t7;
			cube[down][2][3] = t8;
			cube[down][3][3] = t9;

			cube[back][3][1] = t10;
			cube[back][2][1] = t11;
			cube[back][1][1] = t12;

		}
		// return temp;
	}

	private static void MoveCube(int[][][] cube, String face, int i) {

		if (face == "f")
			F(cube, i);// 将魔方的正面顺时针旋转i次
		if (face == "b")
			B(cube, i);// 将魔方的背面顺时针旋转i次
		if (face == "r")
			R(cube, i);// 将魔方的右面顺时针旋转i次
		if (face == "l")
			L(cube, i);// 将魔方的左面顺时针旋转i次
		if (face == "u")
			U(cube, i);// 将魔方的上面顺时针旋转i次
		if (face == "d")
			D(cube, i);// 将魔方的底面顺时针旋转i次
		/**
		 * if(!strcmp(sur,"mr")) MR(m,i);//将魔方的左面和右面之间的面以右面为基准顺时针旋转i次
		 * if(!strcmp(sur,"mf")) MF(m,i);//将魔方的前面和后面之间的面以前面为基准顺时针旋转i次
		 * if(!strcmp(sur,"mu")) MU(m,i);//将魔方的上面和底面之间的面以上面为基准顺时针旋转i次
		 **/

		solve.add(face + i + ";");
	}

	//
	private static int[][][] getCubeState() {
		// cubestate = colorstate;

		int[][][] cubestate = {
				{ { 0, 0, 0, 0 }, { 0, o, b, o }, { 0, o, o, o },
					{ 0, o, o, o } },
			{ { 0, 0, 0, 0 }, { 0, r, r, r }, { 0, r, r, r },
					{ 0, r, r, r } },
			{ { 0, 0, 0, 0 }, { 0, b, g, b }, { 0, b, b, b },
					{ 0, b, b, b } },
			{ { 0, 0, 0, 0 }, { 0, g, o, g }, { 0, g, g, g },
					{ 0, g, g, g } },
			{ { 0, 0, 0, 0 }, { 0, y, y, y }, { 0, y, y, y },
					{ 0, y, y, y } },
			{ { 0, 0, 0, 0 }, { 0, w, w, w }, { 0, w, w, w },
					{ 0, w, w, w } } };
		
		return cubestate;
	}

	private static void DownCross(int[][][] cube) {

		int[] face = { front, left, back, right };
		String[] s = { "f", "l", "b", "r" };
		int subscript_of_down[][] = { { 1, 2 }, { 2, 1 }, { 3, 2 }, { 2, 3 } };
		int subscript_of_up[][] = { { 3, 2 }, { 2, 1 }, { 1, 2 }, { 2, 3 } };
		String ch;
		int n;

		while (!((cube[down][1][2] == cube[down][2][2] && cube[front][3][2] == cube[front][2][2])
				&& (cube[down][2][1] == cube[down][2][2] && cube[back][3][2] == cube[back][2][2])
				&& (cube[down][2][3] == cube[down][2][2] && cube[right][3][2] == cube[right][2][2]) && (cube[down][3][2] == cube[down][2][2] && cube[left][3][2] == cube[left][2][2]))) {// 底面十字没有拼好
			for (int i = 0; i < 4; i++) {
				if (cube[down][subscript_of_down[i][0]][subscript_of_down[i][1]] == cube[down][2][2]
						&& cube[face[i]][3][2] != cube[face[i]][2][2]) {
					ch = s[i];
					MoveCube(cube, ch, 2);
				}// 底面棱块为底面色位置不对

				if (cube[up][subscript_of_up[i][0]][subscript_of_up[i][1]] == cube[down][2][2]) {
					n = 0;
					while (cube[face[(i + n) % 4]][2][2] != cube[face[(i + n) % 4]][1][2]) {
						MoveCube(cube, "u", 1);
						n++;
					}
					ch = s[(i + n) % 4];
					MoveCube(cube, ch, 2);
				}// 以上是底面棱块在顶面的情况

				if (cube[face[i]][1][2] == cube[down][2][2])// 侧面上棱是底面色的情况
				{
					n = 0;
					while (cube[face[(i + n + 1) % 4]][2][2] != cube[up][subscript_of_up[(i + n) % 4][0]][subscript_of_up[(i + n) % 4][1]]) {
						MoveCube(cube, "u", 1);
						n++;
					}
					ch = s[(i + n) % 4];
					MoveCube(cube, ch, 3);
					ch = s[(n + 1 + i) % 4];
					MoveCube(cube, ch, 1);
					ch = s[(i + n) % 4];
					MoveCube(cube, ch, 1);
				}
				if (cube[face[i]][2][1] == cube[down][2][2])// 侧面左棱是底面色的情况
				{
					ch = s[(i + 1) % 4];
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 1);
				}
				if (cube[face[i]][2][3] == cube[down][2][2])// 侧面右棱是底面色的情况
				{
					ch = s[(i + 3) % 4];
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 3);
				}
				if (cube[face[i]][3][2] == cube[down][2][2])// 侧面底棱是底面色的情况
				{
					ch = s[i];
					MoveCube(cube, ch, 1);
					ch = s[(i + 1) % 4];
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 1);
					ch = s[i];
					MoveCube(cube, ch, 3);
				}// 以上是侧面棱块色是底面色的情
			}
		}
		// return cube;
	}

	private static void DownCorner(int[][][] cube) {

		int[] face = { front, left, back, right };
		String s[] = { "f", "l", "b", "r" };
		int subscript_of_down[][] = { { 1, 1 }, { 3, 1 }, { 3, 3 }, { 1, 3 } };
		int subscript_of_up[][] = { { 3, 1 }, { 1, 1 }, { 1, 3 }, { 3, 3 } };
		String ch;
		int n;
		// int[][][] cube = cube;
		while (!((cube[down][1][1] == cube[down][2][2]
				&& cube[front][3][1] == cube[front][2][2] && cube[left][3][3] == cube[left][2][2])
				&& (cube[down][1][3] == cube[down][2][2]
						&& cube[front][3][3] == cube[front][2][2] && cube[right][3][1] == cube[right][2][2])
				&& (cube[down][3][1] == cube[down][2][2]
						&& cube[right][3][1] == cube[right][2][2] && cube[back][3][3] == cube[back][2][2]) && (cube[down][3][3] == cube[down][2][2]
				&& cube[back][3][1] == cube[back][2][2] && cube[right][3][3] == cube[right][2][2])))// 直到底角全部归位
		{

			for (int i = 0; i < 4; i++) {
				if (cube[down][subscript_of_down[i][0]][subscript_of_down[i][1]] == cube[down][2][2]
						&& (cube[face[i]][3][1] != cube[face[i]][2][2] || cube[face[(i + 1) % 4]][3][3] != cube[face[(i + 1) % 4]][2][2])) {
					ch = s[i];
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 3);
				}// 底面角块颜色归位但是位置不对

				if (cube[up][subscript_of_up[i][0]][subscript_of_up[i][1]] == cube[down][2][2]) {
					n = 0;
					while (cube[face[(i + n) % 4]][1][1] != cube[face[(i + n + 1) % 4]][2][2]) {
						MoveCube(cube, "u", 1);
						n++;
					}
					ch = s[(i + n) % 4];
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 3);
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 2);
				}// 顶面有底角色块的情况
				if (cube[face[i]][1][1] == cube[down][2][2])// 侧面左上角是底面色的情况
				{
					n = 0;
					while (cube[face[(i + n + 1) % 4]][2][2] != cube[face[(i
							+ n + 1) % 4]][1][3]) {
						MoveCube(cube, "u", 1);
						n++;
					}
					ch = s[(n + i) % 4];
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 3);
				}
				if (cube[face[i]][1][3] == cube[down][2][2])// 侧面右上角是底面色的情况
				{
					n = 0;
					while (cube[face[(i + n + 3) % 4]][2][2] != cube[face[(i
							+ n + 3) % 4]][1][1]) {
						MoveCube(cube, "u", 1);
						n++;
					}
					ch = s[(n + i) % 4];
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 3);
					MoveCube(cube, ch, 1);
				}
				if (cube[face[i]][3][1] == cube[down][2][2])// 侧面左下角是底面色的情况
				{
					ch = s[i];
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 3);
				}
				if (cube[face[i]][3][3] == cube[down][2][2])// 侧面右下角是底面色的情况
				{
					ch = s[i];
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 3);
					MoveCube(cube, ch, 1);
				}// 侧面有底面色块
			}
		}
		// return cube;
	}

	private static int[][][] CentralEdge(int[][][] cube) {

		int[] face = { front, left, back, right };
		String[] s = { "f", "l", "b", "r" };
		int subscript_of_up[][] = { { 3, 2 }, { 2, 1 }, { 1, 2 }, { 2, 3 } };
		String ch;
		int n;

		while (!((cube[front][2][1] == cube[front][2][2] && cube[front][2][3] == cube[front][2][2])
				&& (cube[left][2][1] == cube[left][2][2] && cube[left][2][3] == cube[left][2][2])
				&& (cube[back][2][1] == cube[back][2][2] && cube[back][2][3] == cube[back][2][2]) && (cube[right][2][1] == cube[right][2][2] && cube[right][2][3] == cube[right][2][2]))) {
			for (int i = 0; i < 4; i++) {
				if (!(cube[face[i]][2][1] == cube[face[i]][2][2] && cube[face[(i + 1) % 4]][2][3] == cube[face[(i + 1) % 4]][2][2])
						&& (cube[face[i]][2][1] != cube[up][2][2] && cube[face[(i + 1) % 4]][2][3] != cube[up][2][2])) {
					ch = s[i];
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 3);
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 3);
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 2);
				}
				if (cube[face[i]][1][2] != cube[up][2][2]
						&& cube[up][subscript_of_up[i][0]][subscript_of_up[i][1]] != cube[up][2][2]) {
					n = 0;
					while (cube[face[(i + n) % 4]][1][2] != cube[face[(i + n) % 4]][2][2]) {
						n++;
						MoveCube(cube, "u", 1);
					}
					if (cube[up][subscript_of_up[(i + n) % 4][0]][subscript_of_up[(i + n) % 4][1]] == cube[face[(i
							+ n + 3) % 4]][2][2]) {
						ch = s[(i + n) % 4];
						MoveCube(cube, ch, 1);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 1);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 1);
						MoveCube(cube, "u", 3);
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 3);
						MoveCube(cube, ch, 3);
					}
					if (cube[up][subscript_of_up[(i + n) % 4][0]][subscript_of_up[(i + n) % 4][1]] == cube[face[(i
							+ n + 1) % 4]][2][2]) {
						ch = s[(i + n) % 4];
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 3);
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 3);
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 1);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 1);
					}
				}
			}
		}
		return cube;
	}

	private static void UpCross(int[][][] cube) {

		int[] face = { front, left, back, right };
		String[] s = { "f", "l", "b", "r" };
		int subscript_of_up[][] = { { 3, 2 }, { 2, 1 }, { 1, 2 }, { 2, 3 } };
		String ch;

		while (!(cube[up][1][2] == cube[up][2][2]
				&& cube[up][2][1] == cube[up][2][2]
				&& cube[up][2][3] == cube[up][2][2] && cube[up][3][2] == cube[up][2][2])) {

			if (cube[up][1][2] != cube[up][2][2]
					&& cube[up][2][1] != cube[up][2][2]
					&& cube[up][2][3] != cube[up][2][2]
					&& cube[up][3][2] != cube[up][2][2]) {
				MoveCube(cube, "f", 1);
				MoveCube(cube, "r", 1);
				MoveCube(cube, "u", 1);
				MoveCube(cube, "r", 3);
				MoveCube(cube, "u", 3);
				MoveCube(cube, "f", 3);
			}
			for (int i = 0; i < 4; i++) {
				if (cube[up][subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] == cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] == cube[up][2][2]
						&& cube[up][subscript_of_up[i][0]][subscript_of_up[i][1]] != cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] != cube[up][2][2]) {// 形成一个倒"L"
					ch = s[i];
					MoveCube(cube, ch, 1);
					ch = s[(i + 3) % 4];
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 3);
					ch = s[i];
					MoveCube(cube, ch, 3);
				}
				if (cube[up][subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] == cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] == cube[up][2][2]
						&& cube[up][subscript_of_up[i][0]][subscript_of_up[i][1]] != cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] != cube[up][2][2]) {// 形成一个横"一"
					ch = s[i];
					MoveCube(cube, ch, 1);
					ch = s[(i + 3) % 4];
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 3);
					ch = s[i];
					MoveCube(cube, ch, 3);
				}
			}
		}
		// return cube;
	}

	private static void UpFaceCorner(int[][][] cube)// 顶角面位
	{
		int[] face = { front, left, back, right };
		String[] s = { "f", "l", "b", "r" };
		int subscript_of_up[][] = { { 3, 1 }, { 1, 1 }, { 1, 3 }, { 3, 3 } };
		String ch;
		int n;

		while (!(cube[up][1][1] == cube[up][2][2]
				&& cube[up][1][3] == cube[up][2][2]
				&& cube[up][3][1] == cube[up][2][2] && cube[up][3][3] == cube[up][2][2])) {

			for (int i = 0; i < 4; i++) {
				if ((cube[up][1][1] != cube[up][2][2]
						&& cube[up][1][3] != cube[up][2][2]
						&& cube[up][3][1] != cube[up][2][2] && cube[up][3][3] != cube[up][2][2])
						&& (cube[face[i]][1][1] == cube[up][2][2] && cube[face[i]][1][3] == cube[up][2][2])) {// 十字型（前左右与上同色）
					n = 0;
					while (cube[face[(i + n) % 4]][1][2] != cube[face[(i + n) % 4]][2][2]) {
						MoveCube(cube, "u", 1);
						n++;
					}
					ch = s[(i + n + 3) % 4];
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 2);
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 1);
				}
				if (cube[up][subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] == cube[up][2][2]
						&& cube[up][subscript_of_up[i][0]][subscript_of_up[i][1]] != cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] != cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] != cube[up][2][2]) {// 鱼头朝右下的鱼
					if (cube[face[i]][1][1] != cube[up][2][2])// 前左与上异色
					{
						ch = s[(i + 3) % 4];
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 2);
						MoveCube(cube, ch, 1);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 1);
					} else// 前右与上同色
					{
						MoveCube(cube, "u", 3);
						ch = s[(i + 3) % 4];
						MoveCube(cube, ch, 1);
						MoveCube(cube, "u", 2);
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 3);
						MoveCube(cube, ch, 1);
						MoveCube(cube, "u", 3);
						MoveCube(cube, ch, 3);
					}
				}
				if (cube[up][subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] == cube[up][2][2]
						&& cube[up][subscript_of_up[i][0]][subscript_of_up[i][1]] != cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] != cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] == cube[up][2][2]) {// 大炮型
					if (cube[face[i]][1][1] == cube[up][2][2]
							&& cube[face[i]][1][3] == cube[up][2][2]) {// 前左右与上同色
						ch = s[(i + 1) % 4];
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 2);
						MoveCube(cube, ch, 1);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 1);
					} else {// 前左右与上异色
						ch = s[(i + 2) % 4];
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 2);
						MoveCube(cube, ch, 1);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 3);
						MoveCube(cube, "u", 1);
						MoveCube(cube, ch, 1);
					}
				}
				if (cube[up][subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] == cube[up][2][2]
						&& cube[up][subscript_of_up[i][0]][subscript_of_up[i][1]] != cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] == cube[up][2][2]
						&& cube[up][subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] != cube[up][2][2]) {// 双凌型
					MoveCube(cube, "u", 3);
					ch = s[(i + 3) % 4];
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 2);
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 1);
					MoveCube(cube, ch, 1);
				}
			}
		}
		// return cube;
	}

	private static void UpCorner(int[][][] cube)// 顶角还原
	{

		int[] face = { front, left, back, right };
		String[] s = { "f", "l", "b", "r" };
		int n;
		String ch;

		while (cube[front][1][1] != cube[front][2][2])
			MoveCube(cube, "u", 1);
		while (!((cube[front][1][1] == cube[front][2][2] && cube[front][1][3] == cube[front][2][2])
				&& (cube[left][1][1] == cube[left][2][2] && cube[left][1][3] == cube[left][2][2])
				&& (cube[back][1][1] == cube[back][2][2] && cube[back][1][3] == cube[back][2][2]) && (cube[right][1][1] == cube[right][2][2] && cube[right][1][3] == cube[right][2][2]))) {

			int i = 0;
			n = 0;
			/**
			 * ch = s[(i + n + 3) % 4]; MoveCube(cube, ch, 2);//r2 ch = s[(i + n
			 * ) % 4]; MoveCube(cube, ch, 2);//f2 ch = s[(i + n + 3) % 4];
			 * MoveCube(cube, ch, 3);//r3 ch = s[(i + n + 2) % 4];
			 * MoveCube(cube, ch, 3);//b3 ch = s[(i + n + 3) % 4];
			 * MoveCube(cube, ch, 1);//r1 ch = s[(i + n ) % 4]; MoveCube(cube,
			 * ch, 2);//f2 ch = s[(i + n + 3) % 4]; MoveCube(cube, ch, 3);//r3
			 * ch = s[(i + n + 2) % 4]; MoveCube(cube, ch, 1);//b1 ch = s[(i + n
			 * + 3) % 4]; MoveCube(cube, ch, 2);//r3
			 **/
			MoveCube(cube, "r", 2);// r2
			MoveCube(cube, "f", 2);// f2
			MoveCube(cube, "r", 3);// r3
			MoveCube(cube, "b", 3);// b3
			MoveCube(cube, "r", 1);// r1
			MoveCube(cube, "f", 2);// f2
			MoveCube(cube, "r", 3);// r3
			MoveCube(cube, "b", 1);// b1
			MoveCube(cube, "r", 3);// r3

			while (cube[front][1][1] != cube[front][2][2])
				MoveCube(cube, "u", 1);
		}
		// return cube;
	}

	private static void UpEdge(int[][][] cube)// 顶棱还原
	{
		int[] face = { front, left, back, right };
		String[] s = { "f", "l", "b", "r" };
		int n;
		String ch;

		while (cube[front][1][1] != cube[front][2][2])
			MoveCube(cube, "u", 1);
		while (!(cube[front][1][2] == cube[front][2][2]
				&& cube[left][1][2] == cube[left][2][2]
				&& cube[back][1][2] == cube[back][2][2] && cube[right][1][2] == cube[right][2][2])) {
			// 顶棱没有被还原
			for (int i = 0; i < 4; i++) {
				n = 0;
				if (cube[face[i]][1][1] == cube[face[i]][1][2]
						&& cube[face[i]][1][2] == cube[face[i]][1][3]) {// 某一面的顶棱已经完成还原
					while (cube[face[(i + n) % 4]][1][1] != cube[face[(i + n) % 4]][2][2]) {// 寻找某一个没拼好的棱
						MoveCube(cube, "u", 1);
						n++;
					}

					ch = s[(i + n + 1) % 4];
					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 3);

					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);

					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 1);

					MoveCube(cube, ch, 1);
					MoveCube(cube, "u", 3);

					MoveCube(cube, ch, 3);
					MoveCube(cube, "u", 3);
					MoveCube(cube, ch, 2);
				}
			}

			while (cube[front][1][1] != cube[front][2][2])
				MoveCube(cube, "u", 1);
		}
		// return cube;
	}

	public static List<String> Solver(int[][][] t_cube) {

		DownCross(t_cube);// 底部十字
		DownCorner(t_cube);// 底角还原
		CentralEdge(t_cube);// 中棱还原
		UpCross(t_cube);// 顶面十字
		UpFaceCorner(t_cube);// 顶角面位
		UpCorner(t_cube);// 顶角还原
		UpEdge(t_cube);// 顶棱还原

		System.out.println("solvemethod");
		for (int i = 0; i < solve.size(); i++) {
			System.out.print((String) solve.get(i) + " ");
		}

		return solve;
	}

	public static void main(String[] args) {

		int[][][] t_cube = getCubeState();

		DownCross(t_cube);// 底部十字
		DownCorner(t_cube);// 底角还原
		CentralEdge(t_cube);// 中棱还原
		UpCross(t_cube);// 顶面十字
		UpFaceCorner(t_cube);// 顶角面位
		UpCorner(t_cube);// 顶角还原
		UpEdge(t_cube);// 顶棱还原

		System.out.println("solvemethod");
		// System.out.println( (int)t_cube[front][1][1] );
		for (int i = 0; i < solve.size(); i++) {
			System.out.println((String) solve.get(i) + " ");
		}

	}

}
