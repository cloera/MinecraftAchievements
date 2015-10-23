package com.dyn.achievements.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.Achievement;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.ArrayList;

import com.dyn.achievements.achievement.AchievementHandler;
import com.dyn.achievements.achievement.BaseAchievement.AchievementType;

public class EventHandler {

	@SubscribeEvent
	public void craftingEvent(PlayerEvent.ItemCraftedEvent event) {
		ArrayList<Achievement> ach = AchievementHandler.findAchievementByType(AchievementType.CRAFT_ITEM);
		if (event.crafting != null) {
			for (int i = 0; i < ach.size(); i++) {
//				if (ach[i].getItemStack() != null && event.crafting.getItem() == ach[i].getEventItem().getItem()) {
//					event.player.addStat(ach[i], 1);
//				}
			}
		}
	}

	@SubscribeEvent
	public void smeltingEvent(PlayerEvent.ItemSmeltedEvent event) {
//		if (event.smelting != null && ach.getEventItem() != null
//				&& event.smelting.getItem() == ach.getEventItem().getItem()) {
//			event.player.addStat(ach, 1);
//		}

	}

	@SubscribeEvent
	public void pickupEvent(PlayerEvent.ItemPickupEvent event) {

//		if (event.pickedUp != null && event.pickedUp.getEntityItem() != null
//				&& event.pickedUp.getEntityItem().getItem() != null && ach.getEventItem() != null
//				&& event.pickedUp.getEntityItem().getItem() == ach.getEventItem()) {
//			event.player.addStat(ach, 1);
//		}
	}

	@SubscribeEvent
	public void killEvent(LivingDeathEvent event) {
		if (event.source != null && event.source.getEntity() != null) {
			if (event.source.getEntity() instanceof EntityPlayer) {
//				if (event.entityLiving != null && ach.getEntity() != null
//						&& EntityList.getEntityString(event.entityLiving).equalsIgnoreCase(ach.getEntity())) {
//					((EntityPlayer) event.source.getEntity()).addStat(ach, 1);
//				}
			}
		}
	}

	@SubscribeEvent
	public void livingEvent(LivingEvent.LivingUpdateEvent event) {

		if (event.entityLiving instanceof EntityPlayerMP) {

			EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;

//			if (player.func_147099_x().writeStat(ach.getEventStat()) >= ach.getStatAmount()) {
//				player.addStat(ach, 1);
//			}

		}
	}

}