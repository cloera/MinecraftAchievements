package com.dyn.achievements;

import java.io.InputStreamReader;
import java.net.URL;

import org.apache.logging.log4j.Logger;

import com.dyn.achievements.achievement.AchievementPlus;
import com.dyn.achievements.handlers.*;
import com.dyn.achievements.proxy.Proxy;
import com.dyn.achievements.reference.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class AchievementsMod {

	@Mod.Instance(Reference.MOD_ID)
	public static AchievementsMod instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static Proxy proxy;

	public static Logger logger;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		Configuration configs = new Configuration(event.getSuggestedConfigurationFile());
		try {
			configs.load();
		} catch (RuntimeException e) {
			logger.warn(e);
		}

		try {
			// Download the JSON into a json list
			URL url = new URL("https://dl.dropboxusercontent.com/u/33377940/test.json");
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(new JsonReader(new InputStreamReader(url.openStream())));
			// we might not need to do this but eh it works
			JsonObject overallObject = element.getAsJsonObject(); 
			JsonArray jsonA = overallObject.get("achievements").getAsJsonArray();
			for (JsonElement ach : jsonA) {
				AchievementPlus.JsonToAchievement(ach.getAsJsonObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Json Parsing Failed");
		}

		proxy.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		AchievementHandler.addAchievementPage("DYN Achievements", AchievementHandler.getAllAchievements());
		/*Requirements r = new Requirements();

		// Crafting
		CraftRequirement cr2 = r.new CraftRequirement();
		ItemStack is = new ItemStack(Blocks.wool, 1, 11);
		cr2.setFromItemId(Item.getIdFromItem(is.getItem()), is.getItemDamage());
		cr2.setAmountNeeded(1);
		cr2.setRequirementId(1);
		r.addRequirement(cr2);

		CraftRequirement cr = r.new CraftRequirement();
		is = new ItemStack(Blocks.wool, 1, 4);
		cr.setFromItemId(Item.getIdFromItem(is.getItem()), is.getItemDamage());
		cr.setAmountNeeded(1);
		cr.setRequirementId(2);
		r.addRequirement(cr);

		// Smelting
		SmeltRequirement sr = r.new SmeltRequirement();
		sr.setFromItemId(265, 0); // iron ingot
		sr.setAmountNeeded(1);
		sr.setRequirementId(1);
		r.addRequirement(sr);

		// Pick Up
		PickupRequirement pr = r.new PickupRequirement();
		is = new ItemStack(Items.apple);
		pr.setFromItemId(Item.getIdFromItem(is.getItem()), is.getItemDamage()); // egg
		pr.setAmountNeeded(10);
		pr.setRequirementId(1);
		r.addRequirement(pr);

		// Stat - Not Implmented Yet
		
		 * StatRequirement st = r.new StatRequirement(); st.eventStat = StatBase
		 * st.amount = 10; r.addRequirement(st);
		 

		// Kill
		KillRequirement kr = r.new KillRequirement();
		kr.entityType = "Pig";
		kr.setAmountNeeded(1);
		kr.setRequirementId(1);
		r.addRequirement(kr);

		// Spawn - Not Implmented Yet
		
		 * SpawnRequirement sp = r.new SpawnRequirement(); sp.entityType =
		 * "Chicken"; sp.amount = 3; r.addRequirement(sp);
		 

		int idCount = 0;

		// AchievementPlus test = new AchievementPlus(r, "My Test Achievement",
		// "this is a test to make sure that the achievement system works", 0,
		// 0, 2168, idCount++, 0, "", null, false);
		// AchievementPlus test2 = new AchievementPlus();;
		
		 * for(int i=1;i<5;i++){ test = new
		 * AchievementPlus(Requirements.getCopy(r), "Achievement " + i,
		 * "this is a test", 2168, idCount++, 0, "", test); }
		 

		JsonObject ache = new AchievementPlus(r, "My Test Achievement",
				"this is a test to make sure that the achievement system works", 0, 0, 2168, idCount++, 0, "", null,
				false).achievementToJson();
		System.out.println(ache);
		
		JsonArray jArray = new JsonArray();
		for (AchievementPlus a : AchievementHandler.getAllAchievements()) {
			jArray.add(a.achievementToJson());
		}
		JsonObject acheList = new JsonObject();
		acheList.add("achievements", jArray);
		System.out.println(acheList);
		// AchievementPlus test3 = test.JsonToAchievement(ache);*/

	}
}
