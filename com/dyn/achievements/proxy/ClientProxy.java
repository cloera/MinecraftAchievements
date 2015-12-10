package com.dyn.achievements.proxy;

import com.dyn.achievements.config.ConfigHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // Load configuration settings
        ConfigHandler.init(event.getSuggestedConfigurationFile());
    }
}
