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
		private int id;
		private int sub_id;

		BaseRequirement() {
			this.aquired = 0;
			this.amount = 0;
			this.id = 0;
			this.sub_id = 0;
		}

		BaseRequirement(BaseRequirement br) {
			this.aquired = br.aquired;
			this.amount = br.amount;
			this.id = br.id;
			this.sub_id = br.sub_id;
		}

		public abstract String getRequirementEntityName();

		public int getTotalNeeded() {
			return this.amount;
		}

		public int getTotalAquired() {
			return this.aquired;
		}

		public int getRequirementItemID() {
			return this.id;
		}

		public int getRequirementSubItemID(){
			return this.sub_id;
		}
		
		public void setId(int id){
			this.id = id;
		}
		
		public void setSubId(int id){
			this.sub_id = id;
		}

		public void incrementTotal() {
			this.aquired++;
		}

		public void incrementTotalBy(int amt) {
			this.aquired += amt;
		}

		public void setAquiredTo(int total) {
			this.aquired = total;
		}

		public void setAmountNeeded(int total) {
			this.amount = total;
		}
	}

	public class CraftRequirement extends BaseRequirement {
		public ItemStack item;

		public CraftRequirement() {
			super();
			this.item = null;
		}

		public CraftRequirement(CraftRequirement cr) {
			super(cr);
			this.item = cr.item;
		}

		@Override
		public String getRequirementEntityName() {
			return this.item.getDisplayName();
		}

		public void setFromItemId(int id, int subItemId) {
			this.item = new ItemStack(Item.getItemById(id), 1, subItemId);
			setId(id);
			setSubId(subItemId);
		}
	}

	public class SmeltRequirement extends BaseRequirement {
		public ItemStack item;

		public SmeltRequirement() {
			super();
			this.item = null;
			super.id = 0;
		}

		public SmeltRequirement(SmeltRequirement sr) {
			super(sr);
			this.item = sr.item;
			super.id = sr.getRequirementItemID();
		}

		@Override
		public String getRequirementEntityName() {
			return this.item.getDisplayName();
		}

		public void setFromItemId(int id, int subItemId) {
			this.item = new ItemStack(Item.getItemById(id), 1, subItemId);
			setId(id);
			setSubId(subItemId);
		}
	}

	public class KillRequirement extends BaseRequirement {
		public String entityType;

		public KillRequirement() {
			super();
			this.entityType = "";
		}

		public KillRequirement(KillRequirement kr) {
			super(kr);
			this.entityType = kr.entityType;
		}

		@Override
		public String getRequirementEntityName() {
			return this.entityType;
		}
	}

	public class SpawnRequirement extends BaseRequirement {
		public String entityType;

		public SpawnRequirement() {
			super();
			this.entityType = "";
		}

		public SpawnRequirement(SpawnRequirement sr) {
			super(sr);
			this.entityType = sr.entityType;
		}

		@Override
		public String getRequirementEntityName() {
			return this.entityType;
		}
	}

	public class PickupRequirement extends BaseRequirement {
		public ItemStack item;

		public PickupRequirement() {
			super();
			this.item = null;
		}

		public PickupRequirement(PickupRequirement pr) {
			super(pr);
			this.item = pr.item;
		}

		@Override
		public String getRequirementEntityName() {
			return this.item.getDisplayName();
		}

		public void setFromItemId(int id, int subItemId) {
			this.item = new ItemStack(Item.getItemById(id), 1, subItemId);
			setId(id);
			setSubId(subItemId);
		}
	}

	public class StatRequirement extends BaseRequirement {
		public StatBase eventStat;

		public StatRequirement() {
			super();
			this.eventStat = null;
		}

		public StatRequirement(StatRequirement sr) {
			super(sr);
			this.eventStat = sr.eventStat;
		}

		@Override
		public String getRequirementEntityName() {
			return this.eventStat.toString();
		}
	}

	private ArrayList<BaseRequirement> requirements = new ArrayList();;

	public Requirements() {
	
	}
	
	public static Requirements getCopy(Requirements r){
		Requirements copy = new Requirements();
		for (BaseRequirement br : r.getRequirements()) {
			if (br instanceof CraftRequirement) {
				copy.addRequirement(copy.new CraftRequirement((CraftRequirement) br));
			}
			if (br instanceof SmeltRequirement) {
				copy.addRequirement(copy.new SmeltRequirement((SmeltRequirement) br));
			}
			if (br instanceof PickupRequirement) {
				copy.addRequirement(copy.new PickupRequirement((PickupRequirement) br));
			}
			if (br instanceof StatRequirement) {
				copy.addRequirement(copy.new StatRequirement((StatRequirement) br));
			}
			if (br instanceof KillRequirement) {
				copy.addRequirement(copy.new KillRequirement((KillRequirement) br));
			}
			if (br instanceof SpawnRequirement) {
				copy.addRequirement(copy.new SpawnRequirement((SpawnRequirement) br));
			}
		}
		return copy;
	}
	

	/***
	 * Add requirement to requirements ArrayList.
	 * 
	 * @param req
	 *            BaseRequirement
	 */
	public void addRequirement(BaseRequirement req) {
		this.requirements.add(req);
	}

	/***
	 * Get requirements ArrayList.
	 * 
	 * @return requirements ArrayList
	 */
	public ArrayList<BaseRequirement> getRequirements() {
		return this.requirements;
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
		for (BaseRequirement r : this.requirements) {
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
		for (BaseRequirement r : this.requirements) {
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
