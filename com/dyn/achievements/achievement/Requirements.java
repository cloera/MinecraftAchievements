package com.dyn.achievements.achievement;

import java.util.ArrayList;

import com.dyn.achievements.achievement.AchievementPlus.AchievementType;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;

public class Requirements {
	public abstract class BaseRequirement {
		private int aquired;
		private int amount;

		BaseRequirement() {
			aquired = 0;
			amount = 0;
		}

		public abstract String getRequirementItemEntity();

		public int getTotalNeeded() {
			return amount;
		}

		public int getTotalAquired() {
			return aquired;
		}

		public abstract int getRequirementItemID();

		public void incrementTotal() {
			this.aquired++;
		}

		public void incrementTotalBy(int amt) {
			aquired += amt;
		}

		public void setAquiredTo(int total) {
			aquired = total;
		}
		
		public void setAmountNeeded(int total) {
			amount = total;
		}
	}

	public class CraftRequirement extends BaseRequirement {
		public ItemStack item;
		public int id;

		public CraftRequirement() {
			item = null;
			id = 0;
		}

		public String getRequirementItemEntity() {
			return item.getDisplayName();
		}

		@Override
		public int getRequirementItemID() {
			return id;
		}

		public void setFromItemId(int id) {
			item = new ItemStack(Item.getItemById(id));
			this.id = id;
		}
	}

	public class SmeltRequirement extends BaseRequirement {
		public ItemStack item;
		public int id;

		public SmeltRequirement() {
			item = null;
			id = 0;
		}

		@Override
		public String getRequirementItemEntity() {
			return item.getDisplayName();
		}

		@Override
		public int getRequirementItemID() {
			return id;
		}

		public void setFromItemId(int id) {
			item = new ItemStack(Item.getItemById(id));
			this.id = id;
		}
	}

	public class KillRequirement extends BaseRequirement {
		public String entityType;

		public KillRequirement() {
			entityType = "";
		}

		@Override
		public String getRequirementItemEntity() {
			return entityType;
		}

		@Override
		public int getRequirementItemID() {
			return 0;
		}
	}

	public class SpawnRequirement extends BaseRequirement {
		public String entityType;

		public SpawnRequirement() {
			entityType = "";
		}

		@Override
		public String getRequirementItemEntity() {
			return entityType;
		}

		@Override
		public int getRequirementItemID() {
			return 0;
		}
	}

	public class PickupRequirement extends BaseRequirement {
		public Item item;
		public int id;

		public PickupRequirement() {
			item = null;
			id = 0;
		}

		public String getRequirementItemEntity() {
			return item.getUnlocalizedName().substring(5);
		}

		@Override
		public int getRequirementItemID() {
			return id;
		}

		public void setFromItemId(int id) {
			item = Item.getItemById(id);
			this.id = id;
		}
	}

	public class StatRequirement extends BaseRequirement {
		public StatBase eventStat;

		public StatRequirement() {
			eventStat = null;
		}

		@Override
		public String getRequirementItemEntity() {
			return eventStat.toString();
		}

		@Override
		public int getRequirementItemID() {
			return 0;
		}
	}

	private ArrayList<BaseRequirement> requirements = new ArrayList();

	public Requirements() {

	}

	/***
	 * Add requirement to requirements ArrayList.
	 * 
	 * @param req
	 *            BaseRequirement
	 */
	public void addRequirement(BaseRequirement req) {
		requirements.add(req);
	}

	/***
	 * Get requirements ArrayList.
	 * 
	 * @return requirements ArrayList
	 */
	public ArrayList<BaseRequirement> getRequirements() {
		return requirements;
	}

	/***
	 * Get boolean of all types from requirements ArrayList.
	 * 
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
			if (r instanceof CraftRequirement)
				hasCraft = true;

			if (r instanceof SmeltRequirement)
				hasSmelt = true;

			if (r instanceof PickupRequirement)
				hasPickup = true;

			if (r instanceof StatRequirement)
				hasStat = true;

			if (r instanceof KillRequirement)
				hasKill = true;

			if (r instanceof SpawnRequirement)
				hasSpawn = true;

		}
		boolean[] types = { hasCraft, hasSmelt, hasPickup, hasStat, hasKill, hasSpawn };
		return types;
	}

	/***
	 * Get list of requirements by specified type.
	 * 
	 * @param type
	 *            AchievementType
	 * @return ArrayList<BaseRequirement> of given type
	 */
	public ArrayList<BaseRequirement> getRequirementsByType(AchievementType type) {
		ArrayList<BaseRequirement> typereq = new ArrayList();
		for (BaseRequirement r : requirements) {
			switch (type) {
			case CRAFT:
				if (r instanceof CraftRequirement)
					typereq.add(r);
				break;
			case SMELT:
				if (r instanceof SmeltRequirement)
					typereq.add(r);
				break;
			case PICKUP:
				if (r instanceof PickupRequirement)
					typereq.add(r);
				break;
			case STAT:
				if (r instanceof StatRequirement)
					typereq.add(r);
				break;
			case KILL:
				if (r instanceof KillRequirement)
					typereq.add(r);
				break;
			case SPAWN:
				if (r instanceof SpawnRequirement)
					typereq.add(r);
				break;
			default:
				break;
			}
		}
		return typereq;
	}
}
