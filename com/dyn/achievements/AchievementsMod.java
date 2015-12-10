package com.dyn.achievements;

import com.dyn.achievements.achievement.AchievementHandler;
import com.dyn.achievements.achievement.AchievementPlus;
import com.dyn.achievements.achievement.AchievementPlus.AchievementType;
import com.dyn.achievements.achievement.AchievementPlus.Requirements;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.CraftRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.KillRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.PickupRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.SmeltRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.SpawnRequirement;
import com.dyn.achievements.achievement.AchievementPlus.Requirements.StatRequirement;
import com.dyn.achievements.handlers.*;
import com.dyn.achievements.proxy.CommonProxy;
import com.dyn.achievements.reference.Reference;
import com.google.gson.JsonObject;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class AchievementsMod {

	@Mod.Instance(Reference.MOD_ID)
	public static AchievementsMod instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		FMLCommonHandler.instance().bus().register(new EventHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		Requirements r = new Requirements();
		
		//Crafting
		CraftRequirement cr = r.new CraftRequirement();
		cr.setFromItemId(Block.getIdFromBlock(Blocks.grass));
		cr.amount = 10;
		r.addRequirement(cr);
		
		CraftRequirement cr2 = r.new CraftRequirement();
		cr2.setFromItemId(Block.getIdFromBlock(Blocks.oak_stairs));
		cr2.amount = 10;
		r.addRequirement(cr2);
		
		//Smelting
		SmeltRequirement sr = r.new SmeltRequirement();
		sr.setFromItemId(265); //iron ingot
		sr.amount = 7;
		r.addRequirement(sr);
		
		//Pick Up
		PickupRequirement pr = r.new PickupRequirement();
		pr.setFromItemId(344); //egg
		pr.amount = 10;
		r.addRequirement(pr);
		
		//Stat - Not Implmented Yet
		/*StatRequirement st = r.new StatRequirement();
		st.eventStat = StatBase
		st.amount = 10;
		r.addRequirement(st);*/
				
		//Kill
		KillRequirement kr = r.new KillRequirement();
		kr.entityType = "Cow";
		kr.amount = 20;
		r.addRequirement(kr);
		
		//Spawn - listener isnt working yet
		/*SpawnRequirement sp = r.new SpawnRequirement();
		sp.entityType = "Chicken";
		sp.amount = 3;
		r.addRequirement(sp);*/
		
		AchievementPlus test = new AchievementPlus(r, "test", "this is a test", 0, 0,
				new ItemStack(Blocks.grass), null);
		
		JsonObject ache = test.achievementToJson();
		AchievementPlus test2 = new AchievementPlus();
		test2.JsonToAchievement(ache);
		System.out.println(ache.toString());
	}
}
