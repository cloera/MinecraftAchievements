package com.dyn.achievements.achievement;

import java.util.ArrayList;

import com.dyn.achievements.AchievementsMod;
import com.dyn.achievements.achievement.Requirements.BaseRequirement;
import com.dyn.achievements.achievement.Requirements.CraftRequirement;
import com.dyn.achievements.achievement.Requirements.KillRequirement;
import com.dyn.achievements.achievement.Requirements.PickupRequirement;
import com.dyn.achievements.achievement.Requirements.SmeltRequirement;
import com.dyn.achievements.achievement.Requirements.SpawnRequirement;
import com.dyn.login.LoginGUI;
import com.dyn.server.http.PostBadge;
import com.google.gson.JsonObject;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentText;

/***
 * AchievementPlus class modifies Achievement class in MineCraft source code.
 * 
 * @author Dominic Amato
 *
 */
public class AchievementPlus extends Achievement {

	private Requirements requirements;
	private String name;
	private String desc;
	private int ach_id;
	private int map_id;
	private String world_name;
	private AchievementPlus parent;
	private int xCoord;
	private int yCoord;
	private boolean awarded;

	// optional but needed to award a badge online;
	private int badgeId;

	public AchievementPlus(Requirements requirements, String name, String description, int xPos, int yPos, int badgeId,
			int achievementId, int mapId, String worldName, AchievementPlus parent, boolean awarded) {
		super(name.replace(' ', '_'), name.replace(' ', '_'), xPos, yPos, new ItemStack(Blocks.dirt), parent);
		LanguageRegistry.instance().addStringLocalization("achievement." + name.replace(' ', '_'), "en_US", name);
		LanguageRegistry.instance().addStringLocalization("achievement." + name.replace(' ', '_') + ".desc", "en_US",
				description);
		this.requirements = requirements;
		this.name = name;
		this.desc = description;
		this.badgeId = badgeId;
		this.awarded = awarded;
		if(awarded){
			//Minecraft.getMinecraft().thePlayer.addStat(this, 1);
		}
		this.ach_id = achievementId;
		this.map_id = mapId;
		this.world_name = worldName;
		this.parent = parent;
		this.xCoord = xPos;
		this.yCoord = yPos;
		AchievementsMod.achievementHandler.registerAchievement(this);
	}

	/***
	 * Get Requirements.
	 * 
	 * @return requirements
	 */
	public Requirements getRequirements() {
		return requirements;
	}
	
	public boolean hasParent() {
		return this.parent != null;
	}
	
	public AchievementPlus getParent() {
		return parent;
	}

	/***
	 * If requirements of specified type exists it returns true, else false.
	 * 
	 * @param type
	 *            AchievementType
	 * @return boolean
	 */
	public boolean hasRequirementOfType(AchievementType type) {
		return requirements.getRequirementsByType(type).size() > 0;
	}

	/***
	 * Awards achievement to player.
	 * 
	 * @param world
	 *            World
	 * @param player
	 *            EntityPlayer
	 * @param itemStack
	 *            ItemStack
	 */
	public void awardAchievement(EntityPlayer player) {
		if(!LoginGUI.DYN_Username.isEmpty()){
			player.addChatMessage(new ChatComponentText("Awrding achievment " + getName()));
			System.out.println("Awarding Achievement");
			new PostBadge(badgeId, LoginGUI.DYN_Username, "", "", player);
			awarded = true;
		}
	}

	public boolean isAwarded() {
		return awarded;
	}

	public void setAwarded(boolean state) {
		awarded = state;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.desc;
	}

	public int getId() {
		return this.ach_id;
	}

	public int getMapId() {
		return this.map_id;
	}

	public String getWorldName() {
		return this.world_name;
	}

	public void setWorldName(String name) {
		this.world_name = name;
	}

	public void meetsRequirements(EntityPlayer player) {
		for (BaseRequirement r : requirements.getRequirements()) {
			if (r.getTotalAquired() < r.getTotalNeeded())
				return;
		}
		awardAchievement(player);
	}

	public static AchievementPlus JsonToAchievement(JsonObject json) {
		Requirements requirements = new Requirements();

		// optional but needed to award a badge online;
		int badgeId = 0;
		String parentName = "";
		boolean awarded = false;
		try {
			String name = json.get("name").getAsString();
			String desc = json.get("desc").getAsString();
			int ach_id = json.get("ach_id").getAsInt();
			int map_id = json.get("map_id").getAsInt();
			String world_name = json.get("world").getAsString();

			int xCoord = json.get("x_coord").getAsInt();
			int yCoord = json.get("y_coord").getAsInt();

			JsonObject req = (JsonObject) json.get("requirements");
			if (req.has("craft_requirements")) {
				JsonObject reqType = (JsonObject) req.get("craft_requirements");
				int counter = 1;
				while (reqType.has("craft_req_" + Integer.toString(counter))) {
					JsonObject reqSubType = (JsonObject) reqType.get("craft_req_" + Integer.toString(counter++));
					CraftRequirement r = requirements.new CraftRequirement();
					r.setFromItemId(reqSubType.get("id").getAsInt(), reqSubType.get("sub_id").getAsInt());
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if (req.has("smelt_requirements")) {
				JsonObject reqType = (JsonObject) req.get("smelt_requirements");
				int counter = 1;
				while (reqType.has("smelt_req_" + Integer.toString(counter))) {
					JsonObject reqSubType = (JsonObject) reqType.get("smelt_req_" + Integer.toString(counter++));
					SmeltRequirement r = requirements.new SmeltRequirement();
					r.setFromItemId(reqSubType.get("id").getAsInt(), reqSubType.get("sub_id").getAsInt());
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if (req.has("pick_up_requirements")) {
				JsonObject reqType = (JsonObject) req.get("pick_up_requirements");
				int counter = 1;
				while (reqType.has("pickup_req_" + Integer.toString(counter))) {
					JsonObject reqSubType = (JsonObject) reqType.get("pickup_req_" + Integer.toString(counter++));
					PickupRequirement r = requirements.new PickupRequirement();
					r.setFromItemId(reqSubType.get("id").getAsInt(), reqSubType.get("sub_id").getAsInt());
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if (req.has("kill_requirements")) {
				JsonObject reqType = (JsonObject) req.get("kill_requirements");
				int counter = 1;
				while (reqType.has("kill_req_" + Integer.toString(counter))) {
					JsonObject reqSubType = (JsonObject) reqType.get("kill_req_" + Integer.toString(counter++));
					KillRequirement r = requirements.new KillRequirement();
					r.entityType = reqSubType.get("entity").getAsString();
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if (req.has("spawn_requirements")) {
				JsonObject reqType = (JsonObject) req.get("spawn_requirements");
				int counter = 1;
				while (reqType.has("spawn_req_" + Integer.toString(counter))) {
					JsonObject reqSubType = (JsonObject) reqType.get("spawn_req_" + Integer.toString(counter++));
					SpawnRequirement r = requirements.new SpawnRequirement();
					r.entityType = reqSubType.get("entity").getAsString();
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if (json.has("badge_id"))
				badgeId = json.get("badge_id").getAsInt();
			if (json.has("parent_name")) {
				parentName = json.get("parent_name").getAsString();
			}
			return new AchievementPlus(requirements, name, desc, xCoord, yCoord, badgeId, ach_id, map_id, world_name,
					AchievementsMod.achievementHandler.findAchievementByName(parentName), awarded);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JsonObject achievementToJson() {
		JsonObject reply = new JsonObject();
		reply.addProperty("name", this.name);
		reply.addProperty("desc", this.desc);
		reply.addProperty("ach_id", this.ach_id);
		reply.addProperty("map_id", this.map_id);
		reply.addProperty("world", this.world_name);
		reply.addProperty("x_coord", this.xCoord);
		reply.addProperty("y_coord", this.yCoord);

		JsonObject req = new JsonObject();
		boolean[] types = requirements.getRequirementTypes();
		for (int i = 0; i < 6; i++) {
			JsonObject reqTypes = new JsonObject();
			switch (i) {
			case 0:
				if (types[i]) {
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.CRAFT);
					int counter = 1;
					for (BaseRequirement t : typeReq) {
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("item", t.getRequirementEntityName());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqSubTypes.addProperty("id", t.getRequirementItemID());
						reqSubTypes.addProperty("sub_id", t.getRequirementSubItemID());
						reqTypes.add("craft_req_" + Integer.toString(counter++), reqSubTypes);
					}
					req.add("craft_requirements", reqTypes);
				}
				break;
			case 1:
				if (types[i]) {
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.SMELT);
					int counter = 1;
					for (BaseRequirement t : typeReq) {
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("item", t.getRequirementEntityName());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqSubTypes.addProperty("id", t.getRequirementItemID());
						reqSubTypes.addProperty("sub_id", t.getRequirementSubItemID());
						reqTypes.add("smelt_req_" + Integer.toString(counter++), reqSubTypes);
					}
					req.add("smelt_requirements", reqTypes);
				}
				break;
			case 2:
				if (types[i]) {
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.PICKUP);
					int counter = 1;
					for (BaseRequirement t : typeReq) {
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("item", t.getRequirementEntityName());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqSubTypes.addProperty("id", t.getRequirementItemID());
						reqSubTypes.addProperty("sub_id", t.getRequirementSubItemID());
						reqTypes.add("pickup_req_" + Integer.toString(counter++), reqSubTypes);
					}
					req.add("pick_up_requirements", reqTypes);
				}
				break;
			case 3:
				if (types[i]) {
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.STAT);
					int counter = 1;
					for (BaseRequirement t : typeReq) {
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("stat", t.getRequirementEntityName());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqTypes.add("stat_req_" + Integer.toString(counter++), reqSubTypes);
					}
					req.add("stat_requirements", reqTypes);
				}
				break;
			case 4:
				if (types[i]) {
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.KILL);
					int counter = 1;
					for (BaseRequirement t : typeReq) {
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("entity", t.getRequirementEntityName());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqTypes.add("kill_req_" + Integer.toString(counter++), reqSubTypes);
					}
					req.add("kill_requirements", reqTypes);
				}
				break;
			case 5:
				if (types[i]) {
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.SPAWN);
					int counter = 1;
					for (BaseRequirement t : typeReq) {
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("entity", t.getRequirementEntityName());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqTypes.add("spawn_req_" + Integer.toString(counter++), reqSubTypes);
					}
					req.add("spawn_requirements", reqTypes);
				}
				break;
			default:
				break;
			}
		}
		reply.add("requirements", req);
		if (this.badgeId > 0)
			reply.addProperty("badge_id", this.badgeId);
		if (this.parent != null)
			reply.addProperty("parent_name", this.parent.getName());

		return reply;
	}

	public enum AchievementType {
		CRAFT, SMELT, PICKUP, STAT, KILL, SPAWN, BREW, OTHER
	}
}
