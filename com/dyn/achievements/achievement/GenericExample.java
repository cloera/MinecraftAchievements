package com.dyn.achievements.achievement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GenericExample extends BaseAchievement{

    public GenericExample(){
        super(AchievementType.OTHER, 0, 0, new ItemStack(Blocks.grass), "Generic Example", null);
        setSpecial();
    }

    @Override
    public boolean canAward(World world, EntityPlayer player, ItemStack itemStack) {
        return false;
    }
}
