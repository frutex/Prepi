package com.examprep.app.util;

public class LevelCalc {

	private static int[] res = { 10, 25, 45, 70, 100, 135, 175, 220, 270, 335, 395, 460, 530 };

	public static int calculateLevel(int num) {
		int level = 0;
		for (int i = 0; i < res.length; i++) {
			if (num > res[i]) {
				level++;
			}
		}

		return level;
	}

	public static int calculateProgress(int cred) {

		int level = calculateLevel(cred);

		int lower;
		if (level == 0) {
			lower = 0;
		} else {
			lower = res[level - 1];
		}

		int next = res[level];

		double dist = next - lower;
		double current = cred - lower;

		//int progress = (current / dist) * 100;
		double progress = (current / dist) * 100;

		return (int) progress;

	}

}
