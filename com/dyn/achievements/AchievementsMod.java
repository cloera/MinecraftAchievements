package com.dyn.achievements;

import com.dyn.achievements.achievement.InitExample;
import com.dyn.achievements.handlers.*;
import com.dyn.achievements.proxy.CommonProxy;
import com.dyn.achievements.reference.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name=Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class AchievementsMod {

    @Mod.Instance(Reference.MOD_ID)
    public static AchievementsMod instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
        FMLCommonHandler.instance().bus().register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        // TODO: Add initializations to proxy
        InitExample.initalize();
    }
}
