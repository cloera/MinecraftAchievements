package com.dyn.achievements.handlers;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.dyn.achievements.AchievementsMod;
import com.dyn.achievements.achievement.AchievementPlus;
import com.dyn.achievements.achievement.AchievementPlus.AchievementType;
import com.dyn.achievements.achievement.Requirements.BaseRequirement;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class EventHandler {

	@SubscribeEvent
	public void craftingEvent(PlayerEvent.ItemCraftedEvent event) {
		if (event.crafting != null) {
			for (AchievementPlus a : AchievementHandler.getItemNames().get(AchievementType.CRAFT)
					.get(event.crafting.getDisplayName())) {
				if (!a.isAwarded()) {
					if (!a.hasParent()) {
						for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.CRAFT)) {
							if (r.getRequirementEntityName().equals(event.crafting.getDisplayName())) {
								if (r.getTotalAquired() < r.getTotalNeeded()) {
									//r.incrementTotal();
									//send a packet here
								}
							}
						}
						//a.meetsRequirements(event.player);
					} else if (a.getParent().isAwarded()) {
						for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.CRAFT)) {
							if (r.getRequirementItemID() == Item.getIdFromItem(event.crafting.getItem())) {
								if (r.getTotalAquired() < r.getTotalNeeded()) {
									//r.incrementTotal();
									//send a packet here
								}
							}
						}
						//a.meetsRequirements(event.player);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void smeltingEvent(PlayerEvent.ItemSmeltedEvent event) {
		if (event.smelting != null) {
			for (AchievementPlus a : AchievementHandler.getItemNames().get(AchievementType.SMELT)
					.get(event.smelting.getDisplayName())) {
				if (!a.isAwarded()) {
					if (!a.hasParent()) {
						for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.SMELT)) {
							if (r.getRequirementItemID() == Item.getIdFromItem(event.smelting.getItem())) {
								if (r.getTotalAquired() < r.getTotalNeeded()) {
									//r.incrementTotal();
									//send a packet here
								}
							}
						}
						//a.meetsRequirements(event.player);
					} else if (a.getParent().isAwarded()) {
						for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.SMELT)) {
							if (r.getRequirementItemID() == Item.getIdFromItem(event.smelting.getItem())) {
								if (r.getTotalAquired() < r.getTotalNeeded()) {
									//r.incrementTotal();
									//send a packet here
								}
							}
						}
						//a.meetsRequirements(event.player);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void pickupEvent(PlayerEvent.ItemPickupEvent event) {
		if (event.pickedUp != null) {
			for (AchievementPlus a : AchievementHandler.getItemNames().get(AchievementType.PICKUP)
					.get(event.pickedUp.getEntityItem().getDisplayName())) {
				if (!a.isAwarded()) {
					if (!a.hasParent()) {
						for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.PICKUP)) {
							if (r.getRequirementEntityName().equals(event.pickedUp.getEntityItem().getDisplayName())) {
								if (r.getTotalAquired() < r.getTotalNeeded()) {
									//r.incrementTotal();
									//send a packet here
								}
							}
						}
						//a.meetsRequirements(event.player);
					} else if (a.getParent().isAwarded()) {
						for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.PICKUP)) {
							if (r.getRequirementEntityName().equals(event.pickedUp.getEntityItem().getDisplayName())) {
								if (r.getTotalAquired() < r.getTotalNeeded()) {
									//r.incrementTotal();
									//send a packet here
								}
							}
						}
						//a.meetsRequirements(event.player);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void killEvent(LivingDeathEvent event) {
		if (event.source != null && event.source.getEntity() != null && event.entity != null) {
			if (event.source.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.source.getEntity();
				for (AchievementPlus a : AchievementHandler.getEntityNames().get(AchievementType.KILL)
						.get(EntityList.getEntityString(event.entity))) {
					if (!a.isAwarded()) {
						if (!a.hasParent()) {
							for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.KILL)) {
								if (r.getRequirementEntityName().equals(EntityList.getEntityString(event.entity))) {
									if (r.getTotalAquired() < r.getTotalNeeded()) {
										//r.incrementTotal();
										//send a packet here
									}
								}
							}
							//a.meetsRequirements(player);
						} else if (a.getParent().isAwarded()) {
							player.addChatMessage(new ChatComponentText(a.getName()
									+ " is incrementing and has a parent " + EntityList.getEntityString(event.entity)));
							for (BaseRequirement r : a.getRequirements().getRequirementsByType(AchievementType.KILL)) {
								if (r.getRequirementEntityName().equals(EntityList.getEntityString(event.entity))) {
									if (r.getTotalAquired() < r.getTotalNeeded()) {
										//r.incrementTotal();
										//send a packet here
									}
								}
							}
							//a.meetsRequirements(player);
						}
					}
				}
			}
		}
	}

	// how do we know which player brewed the potion
	/*
	 * @SubscribeEvent public void brewEvent(PotionBrewEvent event) { for(int
	 * i=0;i<3;i++){ if (event.getItem(1) != null) { for (AchievementPlus a :
	 * AchievementHandler.itemIds.get(AchievementType.BREW)
	 * .get(Item.getIdFromItem(event.getItem(i).getItem()))) { if
	 * (!a.isAwarded()) { for (BaseRequirement r :
	 * a.getRequirements().getRequirementsByType(AchievementType.BREW)) { if
	 * (r.getRequirementItemID() ==
	 * Item.getIdFromItem(event.getItem(i).getItem())) { if (r.getTotalAquired()
	 * < r.getTotalNeeded()) { r.incrementTotal(); } } }
	 * a.meetsRequirements(player); } } } } }
	 */

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