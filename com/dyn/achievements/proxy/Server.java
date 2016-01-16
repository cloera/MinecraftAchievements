package com.dyn.achievements.proxy;

import java.io.InputStreamReader;
import java.net.URL;

import com.dyn.achievements.achievement.AchievementPlus;
import com.dyn.achievements.handlers.EventHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class Server implements Proxy {

	/**
	 * @see forge.reference.proxy.Proxy#renderGUI()
	 */
	@Override
	public void renderGUI() {
		// Actions on render GUI for the server (logging)

	}

	@Override
	public void init() {
		EventHandler eH = new EventHandler();
		
		FMLCommonHandler.instance().bus().register(eH);
		
		MinecraftForge.EVENT_BUS.register(eH);
	}

}