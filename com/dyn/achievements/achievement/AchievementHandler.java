package com.dyn.achievements.achievement;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import java.util.*;

import com.dyn.achievements.achievement.AchievementPlus.AchievementType;

/***
 * An event handler class for achievements.
 * 
 * @author Dominic Amato
 *
 */
public class AchievementHandler {

	public static Map<String, AchievementPage> achievementPages = new HashMap();
	public static ArrayList<AchievementPlus> achievements = new ArrayList();
	public static Map<String, AchievementPlus> achievementsName = new HashMap();
	// currently achievements can have mixed requirements so this doesnt work
	public static Map<AchievementType, ArrayList<AchievementPlus>> achievementsType = new HashMap();


	/***
	 * Initializes page of achievements.
	 * @see addAchievementsPage()
	 */
	public static void initalize() {
		addAchievementPage("DYN Achievements", getAllAchievements());
	}

	/***
	 * Adds page of achievements.
	 * @param pageName Name of achievement page
	 * @param achievements ArrayList of achievements
	 */
	public static void addAchievementPage(String pageName, ArrayList<AchievementPlus> achievements) {
		if (achievements.size() > 0) {
			AchievementPage achievementPage = new AchievementPage(pageName,
					achievements.toArray(new Achievement[achievements.size()]));
			AchievementPage.registerAchievementPage(achievementPage);

			achievementPages.put(pageName, achievementPage);
		}
	}

	/***
	 * Gets list of all achievements.
	 * @return ArrayList of achievements
	 */
	public static ArrayList<AchievementPlus> getAllAchievements() {
		return achievements;
	}

	/***
	 * Finds achievement by given name.
	 * @param name String of achievement name
	 * @return achievement object
	 */
	public static Achievement findAchievementByName(String name) {
		return achievementsName.get(name);
	}

	/***
	 * Find all achievements of given type.
	 * @param type AchievementType object
	 * @return ArrayList of achievements
	 */
	public static ArrayList<AchievementPlus> findAchievementByType(AchievementType type) {
		return achievementsType.get(type);
	}

	/***
	 * Registers achievement by CRAFT, SMELT, PICKUP, and STAT type.
	 * @param achievement AchievementPlus object
	 */
	public static void registerAchievementRequirementTypes(AchievementPlus achievement) {
		/**< if the key doesn't exist make it */
		boolean[] vals = achievement.getRequirements().getRequirementTypes();
		if (vals[0]) {
			if (achievementsType.get(AchievementType.CRAFT) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				achievementsType.put(AchievementType.CRAFT, ach);
			}
			achievementsType.get(AchievementType.CRAFT).add(achievement);
		}
		if (vals[1]) {
			if (achievementsType.get(AchievementType.SMELT) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				achievementsType.put(AchievementType.SMELT, ach);
			}
			achievementsType.get(AchievementType.SMELT).add(achievement);
		}
		if (vals[2]) {
			if (achievementsType.get(AchievementType.PICKUP) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				achievementsType.put(AchievementType.PICKUP, ach);
			}
			achievementsType.get(AchievementType.PICKUP).add(achievement);
		}
		if (vals[3]) {
			if (achievementsType.get(AchievementType.STAT) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				achievementsType.put(AchievementType.STAT, ach);
			}
			achievementsType.get(AchievementType.STAT).add(achievement);
		}
		if (vals[4]) {
			if (achievementsType.get(AchievementType.KILL) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				achievementsType.put(AchievementType.KILL, ach);
			}
			achievementsType.get(AchievementType.KILL).add(achievement);
		}
		if (vals[5]) {
			if (achievementsType.get(AchievementType.SPAWN) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				achievementsType.put(AchievementType.SPAWN, ach);
			}
			achievementsType.get(AchievementType.SPAWN).add(achievement);
		}
	}

	/***
	 * Registers Achievements.
	 * Add achievement to achievements ArrayList.
	 * Put achievement and name in achievementName HashMap.
	 * @see registerAchievementRequirementTypes
	 * @param name of type String
	 * @param achievement of type AchievementPlus
	 */
	public static void registerAchievement(String name, AchievementPlus achievement) {

		achievements.add(achievement);

		if (achievementsName.get(name) != null) {
			throw new RuntimeException("The achievement with the name " + name + " already exists!");
		}
		achievementsName.put(name, achievement);

		registerAchievementRequirementTypes(achievement);
	}
}