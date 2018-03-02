package de.ur.mi.soundsderoberpfalzprojekt;

import java.util.ArrayList;
import java.util.HashMap;

public class SDOManager {
	private static ArrayList<Celebration> celebrationList;
	private static HashMap<Integer, Celebration> celebrationHashMap = new HashMap<Integer, Celebration>();

	public SDOManager() {

	}

	public static void createHashMap() {
		CelebrationCreator.createCelebrationList();
		celebrationList = CelebrationCreator.getCelebrationList();
		for (int i = 0; i < celebrationList.size(); i++) {
			Celebration celebration = celebrationList.get(i);
			int index = celebration.getIndex();
			celebrationHashMap.put(index, celebration);

		}
	}

	public static ArrayList<Celebration> getCelebrationList() {
		return celebrationList;
	}

	public static Celebration getCelebration(int index) {
		return celebrationHashMap.get(index);
	}

	public static int getNumCelebrations() {
		return celebrationList.size();
	}

}
