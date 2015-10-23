package com.dyn.achievements.handlers;

import com.dyn.achievements.achievement.InitExample;
import com.dyn.achievements.reference.Reference;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.gameevent.TickEvent;

public class WorldHandler {

    @Mod.EventHandler
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        World playerWorld = event.player.worldObj;
        if(playerWorld.isRaining()){
        	event.player.addChatComponentMessage(new ChatComponentTranslation("Its Raining").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA)));
            event.player.addStat(InitExample.itsRaining.getAchievement(), 1);
        }
    }
}
