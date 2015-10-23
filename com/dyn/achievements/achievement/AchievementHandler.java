package com.dyn.achievements.achievement;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import java.util.*;

import com.dyn.achievements.achievement.BaseAchievement.AchievementType;

public class AchievementHandler {

    public static AchievementPage achievementPage;
    public static Map<String, Achievement> achievementsName = new HashMap<>();
    public static Map<AchievementType, ArrayList<Achievement>> achievementsType = new HashMap<>();

    public static void postInit() {
        ArrayList<Achievement> achievementArrayList = getAllAchievements();
        achievementPage = new AchievementPage("DYN Achievements", achievementArrayList.toArray(new Achievement[achievementArrayList.size()]));
        AchievementPage.registerAchievementPage(achievementPage);
    }

    public static ArrayList<Achievement> getAllAchievements() {
        ArrayList<Achievement> ach = new ArrayList<>();
        for (String s : achievementsName.keySet()) {
            ach.add(achievementsName.get(s));
        }
        return ach;
    }

    public static Achievement findAchievementByName(String name){
        return achievementsName.get(name);
    }

    public static ArrayList<Achievement> findAchievementByType(AchievementType type){
        return achievementsType.get(type);
    }
    
    public static void registerAchievement(String name, AchievementType type, Achievement achievement){
        if(achievementsName.get(name) != null){
            throw new RuntimeException("The achievement with the name "+name+" already exists!");
        }
        achievementsName.put(name, achievement);
        
        //if the key doesn't exist make it
        if(achievementsType.get(type) == null){
        	ArrayList<Achievement> ach = new ArrayList<>();
            achievementsType.put(type, ach);
        }
        achievementsType.get(type).add(achievement);
        
    }
}
