package com.dyn.achievements.config;

import com.dyn.achievements.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/***
 * Handles configurations from Configuration class in MineCraft source code.
 * 
 * @author Dominic Amato
 *
 */
public class ConfigHandler {

    public static Configuration configuration;

    /***
     * Initializes configuration file.
     * If configuration is null then create new Configuration and call loadConfig().
     * Else register new ConfigHandler.
     * @param configFile File
     */
    public static void init(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfig();
        }
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
    }

    /***
     * Loads configuration file.
     * Checks for new mod versions and notifies player.
     * If configuration has changed then it is saved.
     * Enables debugging log if selected.
     */
    private static void loadConfig() {
        /**< Version checking */
        Property enableVersionCheckingProperty = configuration.get(Configuration.CATEGORY_GENERAL, "versionCheck", Settings.Version.enableVersionCheckDefault);
        enableVersionCheckingProperty.comment = "Have the mod automatically check for new versions and notify the player if a new version is found. Default: " + Settings.Version.enableVersionCheckDefault;
        Settings.Version.enableVersionCheck = enableVersionCheckingProperty.getBoolean();

        /**< General */
        Property debugProperty = configuration.get(Configuration.CATEGORY_GENERAL, "debug", Settings.General.debugDefault);
        debugProperty.comment = "Enable debug logging. Causes lots of unnecessary console spam useful only for debugging. Default: " + Settings.General.debugDefault;
        Settings.General.debug = debugProperty.getBoolean();
        if(configuration.hasChanged())
            configuration.save();
    }

    /***
     * Checks to see if configuration is changed.
     * If configuration is not changed then call loadConfig().
     * @param event OnConfigChangedEvent
     */
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfig();
        }
    }
}
