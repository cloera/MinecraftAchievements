package com.dyn.achievements.achievement;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.dyn.achievements.achievement.AchievementPlus.AchievementType;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.BaseRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.CraftRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.KillRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.PickupRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.SmeltRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.SpawnRequirement;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cpw.mods.fml.common.registry.LanguageRegistry;
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
public class AchievementPlus extends Achievement {

	private Requirements requirements;
	private String name;
	private String desc;
	private AchievementPlus parent;

	public AchievementPlus() {
		super("", "", 0, 0, Item.getItemById(0), null);
		requirements = new Requirements();
		name = "";
		desc = "";
		parent = null;
	}
	
	public AchievementPlus(Requirements requirements, String name, String description, int xCoord, int yCoord,
			ItemStack displayIcon, AchievementPlus parent) {
		// something is wrong with the achievement constructor, we may need to
		// overwrite it as the text is displaying wrong
		super(name.toLowerCase(), description, xCoord, yCoord, displayIcon.getItem(), parent);
		this.requirements = requirements;
		AchievementHandler.registerAchievement(name.toLowerCase(), this);
		
		this.name = name;
		this.desc = description;
		this.parent = parent;
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
	public Achievement getAchievement() {
		return this;
	}

	/***
	 * Overrides setSpecial() in Achievement class.
	 */
	public Achievement setSpecial() {
		return super.setSpecial();
	}

	/***
	 * Awards achievement to player.
	 * @param world World
	 * @param player EntityPlayer
	 * @param itemStack ItemStack
	 */
	public void awardAchievement(World world, EntityPlayer player, ItemStack itemStack) {
		player.addStat(this, 1);
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription() {
		return this.desc;
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
					r.amount = reqSubType.get("amount").getAsInt();
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
					r.amount = reqSubType.get("amount").getAsInt();
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
					r.amount = reqSubType.get("amount").getAsInt();
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
					r.amount = reqSubType.get("amount").getAsInt();
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
					r.amount = reqSubType.get("amount").getAsInt();
					requirements.addRequirement(r);
				}
			}
			if(json.get("parent")!=null){
				//we need to find the achievement with the name of parent
			}
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
						reqSubTypes.addProperty("item", t.getRequirementItem());
						reqSubTypes.addProperty("amount", t.getRequirementAmount());
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
						reqSubTypes.addProperty("item", t.getRequirementItem());
						reqSubTypes.addProperty("amount", t.getRequirementAmount());
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
						reqSubTypes.addProperty("item", t.getRequirementItem());
						reqSubTypes.addProperty("amount", t.getRequirementAmount());
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
						reqSubTypes.addProperty("stat", t.getRequirementItem());
						reqSubTypes.addProperty("amount", t.getRequirementAmount());
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
						reqSubTypes.addProperty("entity", t.getRequirementItem());
						reqSubTypes.addProperty("amount", t.getRequirementAmount());
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
						reqSubTypes.addProperty("entity", t.getRequirementItem());
						reqSubTypes.addProperty("amount", t.getRequirementAmount());
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
		if(this.parent != null){
			reply.addProperty("parent", this.parent.getName());
		}
		
		return reply;
	}
	
	public enum AchievementType {
		CRAFT, SMELT, PICKUP, STAT, KILL, SPAWN, OTHER
	}

	public static class Requirements {
		public abstract class BaseRequirement {
			public BaseRequirement() {};
			public String getRequirementItem(){ return "";}
			public int getRequirementAmount() { return 0;}
			public int getRequirementItemID() { return 0;}
		}

		public class CraftRequirement extends BaseRequirement {
			public ItemStack item;
			public int amount;
			public int id;
			
			public CraftRequirement() {
				item = null;
				amount =0;
				id =0;
			}
			
			@Override
			public String getRequirementItem(){ return item.getDisplayName();}
			@Override
			public int getRequirementAmount() { return amount;}
			@Override
			public int getRequirementItemID() { return id;}
			
			public void setFromItemId(int id){ item = new ItemStack(Item.getItemById(id)); this.id = id;}
		}

		public class SmeltRequirement extends BaseRequirement {
			public ItemStack item;
			public int amount;
			public int id;
			
			public SmeltRequirement() {
				item = null;
				amount =0;
				id=0;
			}
			
			@Override
			public String getRequirementItem(){ return item.getDisplayName();}
			@Override
			public int getRequirementAmount() { return amount;}
			@Override
			public int getRequirementItemID() { return id;}
			
			public void setFromItemId(int id){ item = new ItemStack(Item.getItemById(id)); this.id = id;}
		}

		public class KillRequirement extends BaseRequirement {
			public String entityType;
			public int amount;
			
			public KillRequirement() {
				entityType = "";
				amount =0;
			}
			
			@Override
			public String getRequirementItem(){ return entityType;}
			@Override
			public int getRequirementAmount() { return amount;}
		}

		public class SpawnRequirement extends BaseRequirement {
			public String entityType;
			public int amount;
			
			public SpawnRequirement() {
				entityType = "";
				amount =0;
			}
			
			@Override
			public String getRequirementItem(){ return entityType;}
			@Override
			public int getRequirementAmount() { return amount;}
		}

		public class PickupRequirement extends BaseRequirement {
			public Item item;
			public int amount;
			public int id;
			
			public PickupRequirement() {
				item = null;
				amount =0;
				id=0;
			}
			
			@Override
			public String getRequirementItem(){ return item.getUnlocalizedName().substring(5);}
			@Override
			public int getRequirementAmount() { return amount;}
			@Override
			public int getRequirementItemID() { return id;}
			
			public void setFromItemId(int id){ item = Item.getItemById(id); this.id = id;}
		}

		public class StatRequirement extends BaseRequirement {
			public StatBase eventStat;
			public int amount;
			
			public StatRequirement() {
				eventStat = null;
				amount =0;
			}
			
			@Override
			public String getRequirementItem(){ return eventStat.toString();}
			@Override
			public int getRequirementAmount() { return amount;}
		}

		private ArrayList<BaseRequirement> requirements = new ArrayList();

		public Requirements() {

		}

		/***
		 * Add requirement to requirements ArrayList.
		 * @param req BaseRequirement
		 */
		public void addRequirement(BaseRequirement req) {
			requirements.add(req);
		}

		/***
		 * Get requirements ArrayList.
		 * @return requirements ArrayList
		 */
		public ArrayList<BaseRequirement> getRequirements() {
			return requirements;
		}

		/***
		 * Get boolean of all types from requirements ArrayList.
		 * @return boolean[] of types in requirements
		 */
		public boolean[] getRequirementTypes() {
			boolean hasCraft = false;
			boolean hasSmelt = false;
			boolean hasPickup = false;
			boolean hasStat = false;
			boolean hasKill = false;
			boolean hasSpawn = false;
			for (BaseRequirement r : requirements) {
				if (r instanceof CraftRequirement)
					hasCraft = true;

				if (r instanceof SmeltRequirement)
					hasSmelt = true;

				if (r instanceof PickupRequirement)
					hasPickup = true;

				if (r instanceof StatRequirement)
					hasStat = true;

				if (r instanceof KillRequirement)
					hasKill = true;

				if (r instanceof SpawnRequirement)
					hasSpawn = true;

			}
			boolean[] types = { hasCraft, hasSmelt, hasPickup, hasStat, hasKill, hasSpawn };
			return types;
		}
		
		/***
		 * Get list of requirements by specified type.
		 * @param type AchievementType
		 * @return ArrayList<BaseRequirement> of given type
		 */
		public ArrayList<BaseRequirement> getRequirementsByType(AchievementType type) {
			ArrayList<BaseRequirement> typereq = new ArrayList();
			for (BaseRequirement r : requirements) {
				switch (type) {
				case CRAFT:
					if (r instanceof CraftRequirement)
						typereq.add(r);
					break;
				case SMELT:
					if (r instanceof SmeltRequirement)
						typereq.add(r);
					break;
				case PICKUP:
					if (r instanceof PickupRequirement)
						typereq.add(r);
					break;
				case STAT:
					if (r instanceof StatRequirement)
						typereq.add(r);
					break;
				case KILL:
					if (r instanceof KillRequirement)
						typereq.add(r);
					break;
				case SPAWN:
					if (r instanceof SpawnRequirement)
						typereq.add(r);
					break;
				default:
					break;
				}
			}
			return typereq;
		}
	}
}
