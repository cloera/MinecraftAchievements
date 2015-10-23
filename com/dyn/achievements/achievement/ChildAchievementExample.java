package com.dyn.achievements.achievement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ChildAchievementExample extends BaseAchievement {

	public ChildAchievementExample() {
		super(AchievementType.PICKUP_ITEM, 0, 2, new ItemStack(Blocks.dirt), "Child Achievement",
				InitExample.genericAch.getAchievement());
	}

	@Override
	public boolean canAward(World world, EntityPlayer player, ItemStack itemStack) {
		return false;
	}
}
