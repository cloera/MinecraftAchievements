package com.dyn.achievements.handlers;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;

import java.util.ArrayList;

import com.dyn.achievements.achievement.AchievementHandler;
import com.dyn.achievements.achievement.AchievementPlus;
import com.dyn.achievements.achievement.AchievementPlus.AchievementType;
import com.dyn.achievements.achievement.Requirements.BaseRequirement;

public class EventHandler {

	@SubscribeEvent
	public void craftingEvent(PlayerEvent.ItemCraftedEvent event) {
		event.player.addChatComponentMessage(
				new ChatComponentTranslation("Crafted Item " + event.crafting.getDisplayName() + ", " + event.getPhase().toString())
						.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA)));
		ArrayList<AchievementPlus> ach = AchievementHandler.findAchievementByType(AchievementType.CRAFT);
		if (event.crafting != null && ach != null) {
			for (AchievementPlus a : AchievementHandler.itemIds.get(AchievementType.CRAFT)
					.get(Item.getIdFromItem(event.crafting.getItem()))) {
				if (!a.isAwarded()) {
					for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.CRAFT)) {
						if (r.getRequirementItemID() == Item.getIdFromItem(event.crafting.getItem())) {
							if (r.getTotalAquired() < r.getTotalNeeded()) {
								System.out.println("Incrementing Total");
								r.incrementTotal();
								System.out.println("Aquired: " + r.getTotalAquired() + " Needed: " + r.getTotalNeeded());
							}
						}
					}
					a.meetsRequirements(event.player);
				}
			}
		}
	}

	@SubscribeEvent
	public void smeltingEvent(PlayerEvent.ItemSmeltedEvent event) {
		event.player.addChatComponentMessage(
				new ChatComponentTranslation("Smelted Item " + event.smelting.getDisplayName() + ", " + event.getPhase().toString())
						.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA)));
		ArrayList<AchievementPlus> ach = AchievementHandler.findAchievementByType(AchievementType.SMELT);
		if (event.smelting != null && ach != null) {
			for (AchievementPlus a : AchievementHandler.itemIds.get(AchievementType.SMELT)
					.get(Item.getIdFromItem(event.smelting.getItem()))) {
				if (!a.isAwarded()) {
					for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.SMELT)) {
						if (r.getRequirementItemID() == Item.getIdFromItem(event.smelting.getItem())) {
							if (r.getTotalAquired() < r.getTotalNeeded()) {
								System.out.println("Incrementing Total");
								r.incrementTotal();
								System.out.println("Aquired: " + r.getTotalAquired() + " Needed: " + r.getTotalNeeded());
							}
						}
					}
					a.meetsRequirements(event.player);
				}
			}
		}
	}

	@SubscribeEvent
	public void pickupEvent(PlayerEvent.ItemPickupEvent event) {
		event.player.addChatComponentMessage(
				new ChatComponentTranslation("Picked Up Item " + event.pickedUp.getEntityItem().getDisplayName())
						.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA)));
		ArrayList<AchievementPlus> ach = AchievementHandler.findAchievementByType(AchievementType.PICKUP);
		if (event.pickedUp != null && ach != null) {
			for (AchievementPlus a : AchievementHandler.itemIds.get(AchievementType.PICKUP)
					.get(Item.getIdFromItem(event.pickedUp.getEntityItem().getItem()))) {
				if (!a.isAwarded()) {
					for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.PICKUP)) {
						if (r.getRequirementItemID() == Item.getIdFromItem(event.pickedUp.getEntityItem().getItem())) {
							if (r.getTotalAquired() < r.getTotalNeeded()) {
								System.out.println("Incrementing Total");
								r.incrementTotal();
								System.out.println("Aquired: " + r.getTotalAquired() + " Needed: " + r.getTotalNeeded());
							}
						}
					}
					a.meetsRequirements(event.player);
				} else {
					System.out.println("Achievement Awarded");
				}
			}
		}
	}

	@SubscribeEvent
	public void killEvent(LivingDeathEvent event) {
		if (event.source != null && event.source.getEntity() != null) {
			if (event.source.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.source.getEntity();
				player.addChatComponentMessage(
						new ChatComponentTranslation("Killed " + EntityList.getEntityString(event.entity))
								.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA)));
				ArrayList<AchievementPlus> ach = AchievementHandler.findAchievementByType(AchievementType.KILL);
				if (ach != null) {
					for (AchievementPlus a : AchievementHandler.entityNames.get(AchievementType.KILL)
							.get(EntityList.getEntityString(event.entity))) {
						if (!a.isAwarded()) {
							for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.KILL)) {
								if (r.getRequirementItemEntity() == EntityList.getEntityString(event.entity)) {
									if (r.getTotalAquired() < r.getTotalNeeded()) {
										System.out.println("Incrementing Total");
										r.incrementTotal();
									}
								}
							}
							a.meetsRequirements(player);
						}
					}
				}
			}
		}
	}

	
	@SubscribeEvent
	public void achievementEvent(AchievementEvent event){
		System.out.println(event.achievement.statId);
	}
	
	/*
	 * @SubscribeEvent public void spawnEvent(LivingEvent.LivingSpawnEvent
	 * event) { EntityClientPlayerMP player =
	 * Minecraft.getMinecraft().thePlayer; if(player!=null)
	 * player.addChatComponentMessage( new ChatComponentTranslation("Spawned " +
	 * EntityList.getStringFromID(event.entityLiving.getEntityId())
	 * ).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA))); if
	 * (event.entityLiving instanceof EntityPlayerMP) {
	 * 
	 * } }
	 */

	// this is really dangerous as it happens every game tick,
	// we either should find an alternative, thread this, or keep code as
	// minimal as possible
	/*
	 * @SubscribeEvent public void onPlayerTick(TickEvent.PlayerTickEvent event)
	 * { World playerWorld = event.player.worldObj; if (playerWorld.isRaining())
	 * { event.player.addChatComponentMessage(new ChatComponentTranslation(
	 * "Its Raining") .setChatStyle(new
	 * ChatStyle().setColor(EnumChatFormatting.AQUA))); //
	 * event.player.addStat(InitExample.itsRaining.getAchievement(), 1); } }
	 */

}