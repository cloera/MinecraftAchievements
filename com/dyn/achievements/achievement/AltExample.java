package com.dyn.achievements.achievement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AltExample extends BaseAchievement {
    public AltExample() {
        super(AchievementType.OTHER, -3, 0, new ItemStack(Items.water_bucket), "Alternative Example", InitExample.genericAch.getAchievement());
    }

    @Override
    public boolean canAward(World world, EntityPlayer player, ItemStack itemStack) {
        return false;
    }
}
