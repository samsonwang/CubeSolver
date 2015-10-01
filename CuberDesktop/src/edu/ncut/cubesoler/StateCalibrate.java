/*
2013-6-4
Samson
 */

package edu.ncut.cubesoler;

public class StateCalibrate {

	public static boolean StateCalibrate(int[][][] cubestate) {
		boolean isCorrect = false;
		int o_count = 0;
		int r_count = 0;
		int b_count = 0;
		int g_count = 0;
		int y_count = 0;
		int w_count = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 1; j < 4; j++) {
				for (int k = 1; k < 4; k++) {
					switch (cubestate[i][j][k]) {
					case 0:
						o_count++;
						break;
					case 1:
						r_count++;
						break;
					case 2:
						b_count++;
						break;
					case 3:
						g_count++;
						break;
					case 4:
						y_count++;
						break;
					case 5:
						w_count++;
						break;
					}
				}
			}
		}
		if (o_count == 9 && r_count == 9 & b_count == 9 && g_count == 9
				&& y_count == 9 && w_count == 9) {
			isCorrect = true;
		} else {
			isCorrect = false;
		}

		return isCorrect;
	}

}
