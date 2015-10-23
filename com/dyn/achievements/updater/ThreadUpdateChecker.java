package com.dyn.achievements.updater;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.dyn.achievements.reference.Reference;

import java.io.InputStreamReader;
import java.net.URL;

public class ThreadUpdateChecker extends Thread {

    public ThreadUpdateChecker() {
        setName("Achievement Mod update checker thread");
        setDaemon(true);
        start();
    }

   /* @Override
    public void run() {
        // Get the last successful build
        try {
            // Download the JSON into a json list
            URL url = new URL(UpdateChecker.updateCheckFile);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(new JsonReader(new InputStreamReader(url.openStream())));
            JsonObject overallObject = element.getAsJsonObject();
            JsonElement obj = overallObject.get("1.8");
            String version = obj.getAsString();
            if(!version.equalsIgnoreCase(Reference.VERSION)){
                UpdateChecker.newVersion = version;
            }
        } catch (Exception e) {
            UpdateChecker.updateFailure = true;
            e.printStackTrace();
        }
        UpdateChecker.updateDone = true;
    }*/
}
