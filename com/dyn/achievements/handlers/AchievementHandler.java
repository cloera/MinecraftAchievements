package com.dyn.achievements.handlers;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import java.util.*;

import com.dyn.achievements.achievement.AchievementPlus;
import com.dyn.achievements.achievement.AchievementPlus.AchievementType;
import com.dyn.achievements.achievement.Requirements.BaseRequirement;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/***
 * An event handler class for achievements.
 * 
 * @author Dominic Amato
 *
 */
public class AchievementHandler {

	private Map<String, AchievementPage> achievementPages = new HashMap();
	private ArrayList<AchievementPlus> achievements = new ArrayList();
	private Map<String, AchievementPlus> achievementsName = new HashMap();
	private Map<AchievementType, ArrayList<AchievementPlus>> achievementsType = new HashMap();
	private Map<AchievementType, ListMultimap<String, AchievementPlus>> itemNames = new HashMap();
	private Map<AchievementType, ListMultimap<String, AchievementPlus>> entityNames = new HashMap();

	public AchievementHandler() {

	}

	/***
	 * Adds page of achievements.
	 * 
	 * @param pageName
	 *            Name of achievement page
	 * @param achievements
	 *            ArrayList of achievements
	 */
	public void addAchievementPage(String pageName, ArrayList<AchievementPlus> achievements) {
		if (achievements.size() > 0) {
			AchievementPage achievementPage = new AchievementPage(pageName,
					achievements.toArray(new Achievement[achievements.size()]));
			AchievementPage.registerAchievementPage(achievementPage);

			this.achievementPages.put(pageName, achievementPage);
		}
	}

	/***
	 * Gets list of all achievements.
	 * 
	 * @return ArrayList of achievements
	 */
	public ArrayList<AchievementPlus> getAllAchievements() {
		return this.achievements;
	}

	/***
	 * Finds achievement by given name.
	 * 
	 * @param name
	 *            String of achievement name
	 * @return achievement object
	 */
	public AchievementPlus findAchievementByName(String name) {
		return this.achievementsName.get(name);
	}

	/***
	 * Finds achievements by name.
	 * 
	 * @param name
	 *            String of achievement name
	 * @return list of achievement objects containing name
	 */
	public List<AchievementPlus> findAchievementsByName(String name) {
		List<AchievementPlus> achList = new ArrayList();
		for (AchievementPlus achs : this.achievementsName.values()) {
			if (achs.getName().contains(name)) {
				achList.add(achs);
			}
		}
		return achList;
	}

	public Map<AchievementType, ListMultimap<String, AchievementPlus>> getItemNames() {
		return this.itemNames;
	}

	/***
	 * Find all achievements of given type.
	 * 
	 * @param type
	 *            AchievementType object
	 * @return ArrayList of achievements
	 */
	public ArrayList<AchievementPlus> findAchievementByType(AchievementType type) {
		return this.achievementsType.get(type);
	}

	/***
	 * Registers achievement by CRAFT, SMELT, PICKUP, and STAT type.
	 * 
	 * @param achievement
	 *            AchievementPlus object
	 */
	private void registerAchievementRequirementTypes(AchievementPlus achievement) {
		/** < if the key doesn't exist make it */
		boolean[] vals = achievement.getRequirements().getRequirementTypes();
		if (vals[0]) {
			if (this.achievementsType.get(AchievementType.CRAFT) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				this.achievementsType.put(AchievementType.CRAFT, ach);
			}
			this.achievementsType.get(AchievementType.CRAFT).add(achievement);
		}
		if (vals[1]) {
			if (this.achievementsType.get(AchievementType.SMELT) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				this.achievementsType.put(AchievementType.SMELT, ach);
			}
			this.achievementsType.get(AchievementType.SMELT).add(achievement);
		}
		if (vals[2]) {
			if (this.achievementsType.get(AchievementType.PICKUP) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				this.achievementsType.put(AchievementType.PICKUP, ach);
			}
			this.achievementsType.get(AchievementType.PICKUP).add(achievement);
		}
		if (vals[3]) {
			if (this.achievementsType.get(AchievementType.STAT) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				this.achievementsType.put(AchievementType.STAT, ach);
			}
			this.achievementsType.get(AchievementType.STAT).add(achievement);
		}
		if (vals[4]) {
			if (this.achievementsType.get(AchievementType.KILL) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				this.achievementsType.put(AchievementType.KILL, ach);
			}
			this.achievementsType.get(AchievementType.KILL).add(achievement);
		}
		if (vals[5]) {
			if (this.achievementsType.get(AchievementType.SPAWN) == null) {
				ArrayList<AchievementPlus> ach = new ArrayList();
				this.achievementsType.put(AchievementType.SPAWN, ach);
			}
			this.achievementsType.get(AchievementType.SPAWN).add(achievement);
		}
	}

	private void parseRequirementItemNames(AchievementPlus achievement) {
		boolean[] vals = achievement.getRequirements().getRequirementTypes();
		if (vals[0]) {
			if (this.itemNames.get(AchievementType.CRAFT) == null) {
				ListMultimap<String, AchievementPlus> map = ArrayListMultimap.create();
				this.itemNames.put(AchievementType.CRAFT, map);
			}
			for (BaseRequirement r : achievement.getRequirements().getRequirementsByType(AchievementType.CRAFT)) {
				this.itemNames.get(AchievementType.CRAFT).put(r.getRequirementEntityName(), achievement);
			}
		}
		if (vals[1]) {
			if (this.itemNames.get(AchievementType.SMELT) == null) {
				ListMultimap<String, AchievementPlus> map = ArrayListMultimap.create();
				this.itemNames.put(AchievementType.SMELT, map);
			}
			for (BaseRequirement r : achievement.getRequirements().getRequirementsByType(AchievementType.SMELT)) {
				this.itemNames.get(AchievementType.SMELT).put(r.getRequirementEntityName(), achievement);
			}
		}
		if (vals[2]) {
			if (this.itemNames.get(AchievementType.PICKUP) == null) {
				ListMultimap<String, AchievementPlus> map = ArrayListMultimap.create();
				this.itemNames.put(AchievementType.PICKUP, map);
			}
			for (BaseRequirement r : achievement.getRequirements().getRequirementsByType(AchievementType.PICKUP)) {
				this.itemNames.get(AchievementType.PICKUP).put(r.getRequirementEntityName(), achievement);
			}
		}
	}

	private void parseRequirementEntityNames(AchievementPlus achievement) {
		boolean[] vals = achievement.getRequirements().getRequirementTypes();
		if (vals[4]) {
			if (this.entityNames.get(AchievementType.KILL) == null) {
				ListMultimap<String, AchievementPlus> map = ArrayListMultimap.create();
				this.entityNames.put(AchievementType.KILL, map);
			}
			for (BaseRequirement r : achievement.getRequirements().getRequirementsByType(AchievementType.KILL)) {
				this.entityNames.get(AchievementType.KILL).put(r.getRequirementEntityName(), achievement);
			}
		}
		if (vals[5]) {
			if (this.entityNames.get(AchievementType.SPAWN) == null) {
				ListMultimap<String, AchievementPlus> map = ArrayListMultimap.create();
				this.entityNames.put(AchievementType.SPAWN, map);
			}
			for (BaseRequirement r : achievement.getRequirements().getRequirementsByType(AchievementType.SPAWN)) {
				this.entityNames.get(AchievementType.SPAWN).put(r.getRequirementEntityName(), achievement);
			}
		}
	}

	/***
	 * Registers Achievements. Add achievement to achievements ArrayList. Put
	 * achievement and name in achievementName HashMap.
	 * 
	 * @see registerAchievementRequirementTypes
	 * @param achievement
	 *            of type AchievementPlus
	 */
	public void registerAchievement(AchievementPlus achievement) {

		this.achievements.add(achievement);

		// we shouldnt crash from this
		/*
		 * if (achievementsName.get(achievement.getName()) != null) { throw new
		 * RuntimeException("The achievement with the name " +
		 * achievement.getName() + " already exists!"); }
		 */
		this.achievementsName.put(achievement.getName(), achievement);
		achievement.registerStat();

		registerAchievementRequirementTypes(achievement);
		parseRequirementItemNames(achievement);
		parseRequirementEntityNames(achievement);
	}
}