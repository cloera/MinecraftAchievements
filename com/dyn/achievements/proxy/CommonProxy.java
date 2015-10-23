package com.dyn.achievements.proxy;

import com.dyn.achievements.config.ConfigHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.init(event.getSuggestedConfigurationFile());
    }
}
