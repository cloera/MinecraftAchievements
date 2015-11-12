package com.dyn.achievements.achievement;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.dyn.achievements.achievement.AchievementPlus.AchievementType;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

/***
 * AchievementPlus class modifies Achievement class in MineCraft source code.
 * 
 * @author Dominic Amato
 *
 */
public class AchievementPlus extends Achievement {

	private Requirements requirements;

	public AchievementPlus(Requirements requirements, String name, String description, int xCoord, int yCoord,
			ItemStack displayIcon, Achievement parent) {
		/**< something is wrong with the achievement constructor, we may need to */
		/**< overwrite it as the text is displaying wrong */
		super(name.toLowerCase(), description, xCoord, yCoord, displayIcon.getItem(), parent);
		this.requirements = requirements;
		AchievementHandler.registerAchievement(name.toLowerCase(), this);
	}

	/***
	 * Get Requirements.
	 * @return requirements
	 */
	public Requirements getRequirements() {
		return requirements;
	}

	/***
	 * If requirements of specified type exists it returns true, else false.
	 * @param type AchievementType
	 * @return boolean 
	 */
	public boolean hasRequirementOfType(AchievementType type) {
		return requirements.getRequirementsByType(type).size() > 0;
	}

	/***
	 * Get Achievement object.
	 * @return Achievement
	 */
	public Achievement getAchievement() {
		return this;
	}

	/***
	 * Overrides setSpecial() in Achievement class.
	 */
	public Achievement setSpecial() {
		return super.setSpecial();
	}

	/***
	 * Awards achievement to player.
	 * @param world World
	 * @param player EntityPlayer
	 * @param itemStack ItemStack
	 */
	public void awardAchievement(World world, EntityPlayer player, ItemStack itemStack) {
		player.addStat(this, 1);
	}

	public enum AchievementType {
		CRAFT, SMELT, PICKUP, STAT, KILL, SPAWN, OTHER
	}

	/***
	 * This is Java's solution to nested classes. 
	 * Statics tend to be dangerous in C, but either we include it in the constructor
	 * as a static or it has to be initialized in the achievementPlus
	 * constructor.
	 */
	public static class Requirements {
		public abstract class BaseRequirement {

		}

		public class craftRequirement extends BaseRequirement {
			/**< what is the item? */
			public ItemStack item;
			/**< how many do they have to craft? */
			public int amount;
		}

		public class smeltRequirement extends BaseRequirement {
			/**< what is the item? */
			public ItemStack item;
			/**< how many do they have to craft? */
			public int amount;
		}

		public class killRequirement extends BaseRequirement {
			/**< what is the item? */
			public String entityType;
			/**< how many do they have to kill? */
			public int amount;
		}

		public class spawnRequirement extends BaseRequirement {
			/**< what is the item? */
			public String entityType;
			/**< how many do they have to create? */
			public int amount;
		}

		public class pickupRequirement extends BaseRequirement {
			/**< what is the item? */
			public Item pickupItem;
			/**< how many do they have to get? */
			public int amount;
		}

		public class statRequirement extends BaseRequirement {
			/**< what is the stat? */
			public StatBase eventStat;
			/**< how much? */
			public int amount;
		}

		private ArrayList<BaseRequirement> requirements = new ArrayList();

		public Requirements() {

		}

		/***
		 * Add requirement to requirements ArrayList.
		 * @param req BaseRequirement
		 */
		public void addRequirement(BaseRequirement req) {
			requirements.add(req);
		}

		/***
		 * Get requirements ArrayList.
		 * @return requirements ArrayList
		 */
		public ArrayList<BaseRequirement> getRequirements() {
			return requirements;
		}

		/***
		 * Get boolean of all types from requirements ArrayList.
		 * @return boolean[] of types in requirements
		 */
		public boolean[] getRequirementTypes() {
			boolean hasCraft = false;
			boolean hasSmelt = false;
			boolean hasPickup = false;
			boolean hasStat = false;
			boolean hasKill = false;
			boolean hasSpawn = false;
			for (BaseRequirement r : requirements) {
				if (r instanceof craftRequirement)
					hasCraft = true;

				if (r instanceof smeltRequirement)
					hasSmelt = true;

				if (r instanceof pickupRequirement)
					hasPickup = true;

				if (r instanceof statRequirement)
					hasStat = true;

				if (r instanceof killRequirement)
					hasKill = true;

				if (r instanceof spawnRequirement)
					hasSpawn = true;

			}
			boolean[] types = { hasCraft, hasSmelt, hasPickup, hasStat, hasKill, hasSpawn };
			return types;
		}

		/***
		 * Get list of requirements by specified type.
		 * @param type AchievementType
		 * @return ArrayList<BaseRequirement> of given type
		 */
		public ArrayList<BaseRequirement> getRequirementsByType(AchievementType type) {
			ArrayList<BaseRequirement> typereq = new ArrayList();
			for (BaseRequirement r : requirements) {
				switch (type) {
				case CRAFT:
					if (r instanceof craftRequirement)
						typereq.add(r);
					break;
				case SMELT:
					if (r instanceof smeltRequirement)
						typereq.add(r);
					break;
				case PICKUP:
					if (r instanceof pickupRequirement)
						typereq.add(r);
					break;
				case STAT:
					if (r instanceof statRequirement)
						typereq.add(r);
					break;
				case KILL:
					if (r instanceof killRequirement)
						typereq.add(r);
					break;
				case SPAWN:
					if (r instanceof spawnRequirement)
						typereq.add(r);
					break;
				default:
					break;
				}
			}
			return typereq;
		}
		/*
		 * public ItemStack getItemStack() { switch (type) { case CRAFT_ITEM:
		 * case SMELT_ITEM: return items; default: return null; } }
		 * 
		 * public String getItem() { switch (type) { case KILL_ENTITY: return
		 * entityType; default: return null; } }
		 * 
		 * public Item getEntity() { switch (type) { case PICKUP_ITEM: return
		 * pickupItem; default: return null; } }
		 * 
		 * public StatBase getBaseStatistic() { switch (type) { case STAT:
		 * return eventStat; default: return null; } }
		 * 
		 * public int getStatAmount() { switch (type) { case STAT: return
		 * statAmount; default: return 0; } }
		 */
	}
}
