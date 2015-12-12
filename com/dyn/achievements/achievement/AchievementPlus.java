package com.dyn.achievements.achievement;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.dyn.achievements.achievement.AchievementPlus.AchievementType;
import com.dyn.achievements.achievement.Requirements.BaseRequirement;
import com.dyn.achievements.achievement.Requirements.CraftRequirement;
import com.dyn.achievements.achievement.Requirements.KillRequirement;
import com.dyn.achievements.achievement.Requirements.PickupRequirement;
import com.dyn.achievements.achievement.Requirements.SmeltRequirement;
import com.dyn.achievements.achievement.Requirements.SpawnRequirement;
import com.dyn.server.http.ThreadedHttpPost;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

/***
 * AchievementPlus class modifies Achievement class in MineCraft source code.
 * 
 * @author Dominic Amato
 *
 */
public class AchievementPlus /*extends Achievement*/{

	private Requirements requirements;
	private String name;
	private String desc;
	private AchievementPlus parent;
	//private Achievement achievement;
	
	//optional but needed to award a badge online;
	private int badgeId;
	
	private boolean awarded;

	public AchievementPlus(){
		//super("", "", 0, 0, Item.getItemById(0), null);
		requirements = new Requirements();
		name = "";
		desc = "";
		parent = null;
		awarded = false;
		badgeId = 0;
	}
	
	public AchievementPlus(Requirements requirements, String name, String description, int xCoord, int yCoord,
			ItemStack displayIcon, AchievementPlus parent, int badgeId) {
		//if we want the text to look normal we need to make a .lang file that is associated with the name and description like so
		// super("achievement.buildBootsGold", "buildBootsGold",
		//super(name.toLowerCase(), description, xCoord, yCoord, displayIcon.getItem(), parent);
		//this.achievement = new Achievement(name.toLowerCase(), description, xCoord, yCoord, displayIcon.getItem(), parent == null ? null : parent.getAchievement());
		//this.achievement.registerStat();
		this.requirements = requirements;
		AchievementHandler.registerAchievement(this);
		
		//this.registerStat();
		
		this.name = name;
		this.desc = description;
		this.parent = parent;
		this.badgeId = badgeId;
		awarded = false;
	}

	/***
	 * Get Requirements.
	 * @return requirements
	 */
	public Requirements getRequirements() {
		return requirements;
	}

	/***
	 * If requirements of specified type exists it returns true, else false.
	 * @param type AchievementType
	 * @return boolean 
	 */
	public boolean hasRequirementOfType(AchievementType type) {
		return requirements.getRequirementsByType(type).size() > 0;
	}

	/***
	 * Get Achievement object.
	 * @return Achievement
	 */
	public AchievementPlus getAchievement() {
		return this;
	}

	
	/***
	 * Overrides setSpecial() in Achievement class.
	 */
	public Achievement setSpecial() {
		return this.setSpecial();
	}

	/***
	 * Awards achievement to player.
	 * @param world World
	 * @param player EntityPlayer
	 * @param itemStack ItemStack
	 */
	public void awardAchievement(EntityPlayer player) {
		//player.addStat(this, 1);
		new ThreadedHttpPost(badgeId);
		awarded = true;
	}
	
	public boolean isAwarded(){
		return awarded;
	}
	
	public void setAwarded(boolean state){
		awarded = state;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription() {
		return this.desc;
	}
	
	public void meetsRequirements(EntityPlayer player){
		for(BaseRequirement r : requirements.getRequirements()){
			if(r.getTotalAquired() < r.getTotalNeeded())
				return;
		}
		System.out.println("Awarding Achievement to player: " + player.getDisplayName());
		awardAchievement(player);
	}
	
	public void JsonToAchievement(JsonObject json){
		try{
			this.name = json.get("name").getAsString();
			this.desc = json.get("desc").getAsString();
			JsonObject req = (JsonObject) json.get("requirements");
			if(req.has("craft_requirements")){
				JsonObject reqType = (JsonObject) req.get("craft_requirements");
				int counter =1;
				while(reqType.has("craft_req_"+Integer.toString(counter))){
					JsonObject reqSubType = (JsonObject) reqType.get("craft_req_"+Integer.toString(counter++));
					CraftRequirement r = requirements.new CraftRequirement();
					r.setFromItemId(reqSubType.get("id").getAsInt());
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if(req.has("smelt_requirements")){
				JsonObject reqType = (JsonObject) req.get("smelt_requirements");
				int counter =1;
				while(reqType.has("smelt_req_"+Integer.toString(counter))){
					JsonObject reqSubType = (JsonObject) reqType.get("smelt_req_"+Integer.toString(counter++));
					SmeltRequirement r = requirements.new SmeltRequirement();
					r.setFromItemId(reqSubType.get("id").getAsInt());
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if(req.has("pick_up_requirements")){
				JsonObject reqType = (JsonObject) req.get("pick_up_requirements");
				int counter =1;
				while(reqType.has("pickup_req_"+Integer.toString(counter))){
					JsonObject reqSubType = (JsonObject) reqType.get("pickup_req_"+Integer.toString(counter++));
					PickupRequirement r = requirements.new PickupRequirement();
					r.setFromItemId(reqSubType.get("id").getAsInt());
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if(req.has("kill_requirements")){
				JsonObject reqType = (JsonObject) req.get("kill_requirements");
				int counter =1;
				while(reqType.has("kill_req_"+Integer.toString(counter))){
					JsonObject reqSubType = (JsonObject) reqType.get("kill_req_"+Integer.toString(counter++));
					KillRequirement r = requirements.new KillRequirement();
					r.entityType = reqSubType.get("entity").getAsString();
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if(req.has("spawn_requirements")){
				JsonObject reqType = (JsonObject) req.get("spawn_requirements");
				int counter =1;
				while(reqType.has("spawn_req_"+Integer.toString(counter))){
					JsonObject reqSubType = (JsonObject) reqType.get("spawn_req_"+Integer.toString(counter++));
					SpawnRequirement r = requirements.new SpawnRequirement();
					r.entityType = reqSubType.get("entity").getAsString();
					r.setAmountNeeded(reqSubType.get("amount").getAsInt());
					requirements.addRequirement(r);
				}
			}
			if(json.has("badge_id"))
				this.badgeId = json.get("badge_id").getAsInt();
			if(json.get("parent")!=null){
				//we need to find the achievement with the name of parent
			}
			//this.achievement = new Achievement(name.toLowerCase(), desc, 1, 1, Item.getItemById(0), null);

		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public JsonObject achievementToJson(){
		JsonObject reply = new JsonObject();
		reply.addProperty("name", this.name);
		reply.addProperty("desc", this.desc);
		JsonObject req = new JsonObject();
		boolean[] types = requirements.getRequirementTypes();
		for(int i =0;i<6;i++){
			JsonObject reqTypes = new JsonObject();
			switch (i){
			case 0:
				if(types[i]){
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.CRAFT);
					int counter = 1;
					for(BaseRequirement t : typeReq){
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("item", t.getRequirementItemEntity());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqSubTypes.addProperty("id", t.getRequirementItemID());
						reqTypes.add("craft_req_"+Integer.toString(counter++), reqSubTypes);
					}
					req.add("craft_requirements", reqTypes);
				}
				break;
			case 1:
				if(types[i]){
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.SMELT);
					int counter = 1;
					for(BaseRequirement t : typeReq){
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("item", t.getRequirementItemEntity());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqSubTypes.addProperty("id", t.getRequirementItemID());
						reqTypes.add("smelt_req_"+Integer.toString(counter++), reqSubTypes);
					}
					req.add("smelt_requirements", reqTypes);
				}
				break;
			case 2:
				if(types[i]){
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.PICKUP);
					int counter = 1;
					for(BaseRequirement t : typeReq){
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("item", t.getRequirementItemEntity());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqSubTypes.addProperty("id", t.getRequirementItemID());
						reqTypes.add("pickup_req_"+Integer.toString(counter++), reqSubTypes);
					}
					req.add("pick_up_requirements", reqTypes);
				}
				break;
			case 3:
				if(types[i]){
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.STAT);
					int counter = 1;
					for(BaseRequirement t : typeReq){
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("stat", t.getRequirementItemEntity());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqTypes.add("stat_req_"+Integer.toString(counter++), reqSubTypes);
					}
					req.add("stat_requirements", reqTypes);
				}
				break;
			case 4:
				if(types[i]){
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.KILL);
					int counter = 1;
					for(BaseRequirement t : typeReq){
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("entity", t.getRequirementItemEntity());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqTypes.add("kill_req_"+Integer.toString(counter++), reqSubTypes);
					}
					req.add("kill_requirements", reqTypes);
				}
				break;
			case 5:
				if(types[i]){
					ArrayList<BaseRequirement> typeReq = requirements.getRequirementsByType(AchievementType.SPAWN);
					int counter = 1;
					for(BaseRequirement t : typeReq){
						JsonObject reqSubTypes = new JsonObject();
						reqSubTypes.addProperty("entity", t.getRequirementItemEntity());
						reqSubTypes.addProperty("amount", t.getTotalNeeded());
						reqTypes.add("spawn_req_"+Integer.toString(counter++), reqSubTypes);
					}
					req.add("spawn_requirements", reqTypes);
				}
				break;
			default:
				break;
			}
		}
		reply.add("requirements", req);
		if(this.badgeId>0)
			reply.addProperty("badge_id", this.badgeId);
		if(this.parent != null){
			reply.addProperty("parent", this.parent.getName());
		}
		
		return reply;
	}
	
	public enum AchievementType {
		CRAFT, SMELT, PICKUP, STAT, KILL, SPAWN, OTHER
	}
}
