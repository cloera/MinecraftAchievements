package com.dyn.achievements.achievement;

public class InitExample {

    public static GenericExample genericAch;
    public static ChildAchievementExample ChildAch;
    public static AltExample itsRaining;

    public static void initalize(){
        genericAch = new GenericExample();
        ChildAch = new ChildAchievementExample();
        itsRaining = new AltExample();
        AchievementHandler.postInit();
    }
}
