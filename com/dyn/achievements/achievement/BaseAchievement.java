package com.dyn.achievements.achievement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public abstract class BaseAchievement {

	private final AchievementType type;
	private final Achievement achievement;
	private ItemStack items; // Crafting/Smelt Type
	private String entityType; // Kill Type
	private Item pickupItem; // Pickup Type
	private StatBase eventStat; // Stat Type
	private int statAmount;

	public BaseAchievement(AchievementType type, int xCoord, int yCoord, ItemStack displayIcon, String name,
			Achievement requirement) {
		Achievement achievement = new Achievement(name.toLowerCase(), name, xCoord, yCoord, displayIcon.getItem(),
				requirement);
		achievement.registerStat();

		this.type = type;
		this.achievement = achievement;
		AchievementHandler.registerAchievement(name.toLowerCase(), type, achievement);
	}

	public AchievementType getType() {
		return type;
	}

	public ItemStack getItemStack() {
		switch (type) {
		case CRAFT_ITEM:
		case SMELT_ITEM:
			return items;
		default:
			return null;
		}
	}
	
	public String getItem() {
		switch (type) {
		case KILL_ENTITY:
			return entityType;
		default:
			return null;
		}
	}
	
	public Item getEntity() {
		switch (type) {
		case PICKUP_ITEM:
			return pickupItem;
		default:
			return null;
		}
	}
	
	public StatBase getBaseStatistic() {
		switch (type) {
		case STAT:
			return eventStat;
		default:
			return null;
		}
	}
	
	public int getStatAmount() {
		switch (type) {
		case STAT:
			return statAmount;
		default:
			return 0;
		}
	}

	public Achievement getAchievement() {
		return achievement;
	}

	public void setSpecial() {
		achievement.setSpecial();
	}

	public abstract boolean canAward(World world, EntityPlayer player, ItemStack itemStack);

	public enum AchievementType {
		CRAFT_ITEM, SMELT_ITEM, PICKUP_ITEM, STAT, KILL_ENTITY, OTHER
	}
}
