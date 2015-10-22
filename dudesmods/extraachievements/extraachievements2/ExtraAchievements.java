package dudesmods.extraachievements.extraachievements2;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import dudesmods.extraachievements.extraachievements2.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="extraachievements", name="Extra Achievements", version="2.1.1")
public class ExtraAchievements
{
  public static final String MODID = "extraachievements";
  public static final String NAME = "Extra Achievements";
  public static final String VERSION = "2.1.1";
  public static AchievementPage ExtraAchievements;
  public static Achievement buildHoeStone;
  public static Achievement buildHoeIron;
  public static Achievement buildHoeGold;
  public static Achievement buildHoeDiamond;
  public static Achievement buildPickaxeIron;
  public static Achievement buildPickaxeGold;
  public static Achievement buildPickaxeDiamond;
  public static Achievement buildSwordStone;
  public static Achievement buildSwordIron;
  public static Achievement buildSwordGold;
  public static Achievement buildSwordDiamond;
  public static Achievement buildAxeWood;
  public static Achievement buildAxeStone;
  public static Achievement buildAxeIron;
  public static Achievement buildAxeGold;
  public static Achievement buildAxeDiamond;
  public static Achievement buildShovelWood;
  public static Achievement buildShovelStone;
  public static Achievement buildShovelIron;
  public static Achievement buildShovelGold;
  public static Achievement buildShovelDiamond;
  public static Achievement buildHelmetLeather;
  public static Achievement buildHelmetChain;
  public static Achievement buildHelmetIron;
  public static Achievement buildHelmetGold;
  public static Achievement buildHelmetDiamond;
  public static Achievement buildChestpieceLeather;
  public static Achievement buildChestpieceChain;
  public static Achievement buildChestpieceIron;
  public static Achievement buildChestpieceGold;
  public static Achievement buildChestpieceDiamond;
  public static Achievement buildLeggingsLeather;
  public static Achievement buildLeggingsChain;
  public static Achievement buildLeggingsIron;
  public static Achievement buildLeggingsGold;
  public static Achievement buildLeggingsDiamond;
  public static Achievement buildBootsLeather;
  public static Achievement buildBootsChain;
  public static Achievement buildBootsIron;
  public static Achievement buildBootsGold;
  public static Achievement buildBootsDiamond;
  public static Achievement soulSand;
  public static Achievement witherSkulls;
  public static Achievement witherDead;
  public static Achievement craftBeacon;
  public static Achievement craftCompass;
  public static Achievement craftClock;
  public static Achievement aquireClay;
  public static Achievement smeltBrick;
  public static Achievement craftBricks;
  public static Achievement aquireGold;
  public static Achievement aquireSand;
  public static Achievement smeltGlass;
  public static Achievement craftGlassPane;
  public static Achievement craftBowl;
  public static Achievement craftSoup;
  public static Achievement cookPork;
  public static Achievement cookBeef;
  public static Achievement cookChicken;
  public static Achievement aquireNetherrack;
  public static Achievement smeltNetherBrick;
  public static Achievement craftNetherBrick;
  public static Achievement mobKillChicken;
  public static Achievement mobKillCow;
  public static Achievement mobKillPig;
  public static Achievement mobKillSheep;
  public static Achievement mobKillBat;
  public static Achievement mobKillMooshroom;
  public static Achievement mobKillSquid;
  public static Achievement mobKillCaveSpider;
  public static Achievement mobKillEnderman;
  public static Achievement mobKillSpider;
  public static Achievement mobKillWolf;
  public static Achievement mobKillPigZombie;
  public static Achievement mobKillBlaze;
  public static Achievement mobKillCreeper;
  public static Achievement mobKillGhast;
  public static Achievement mobKillMagmaCube;
  public static Achievement mobKillSilverfish;
  public static Achievement mobKillSkeleton;
  public static Achievement mobKillSlime;
  public static Achievement mobKillWitch;
  public static Achievement mobKillZombie;
  public static Achievement mobKillSnowGolem;
  public static Achievement mobKillIronGolem;
  @SidedProxy(clientSide="dudesmods.extraachievements.extraachievements2.proxy.ClientProxy", serverSide="dudesmods.extraachievements.extraachievements2.proxy.CommonProxy")
  public static CommonProxy proxy;
  
  @Mod.EventHandler
  public void preLoad(FMLPreInitializationEvent event)
  {
    initAchieves();
    AchievementPage.registerAchievementPage(ExtraAchievements);
    FMLCommonHandler.instance().bus().register(new AchievementEvent());
    MinecraftForge.EVENT_BUS.register(new AchievementEvent());
  }
  
  @Mod.EventHandler
  public void mainLoad(FMLInitializationEvent event) {}
  
  @Mod.EventHandler
  public void postLoad(FMLPostInitializationEvent event) {}
  
  public void initAchieves()
  {
    aquireGold = new Achievement("achievement.aquireGold", "aquireGold", 1, 6, Items.field_151043_k, AchievementList.field_76016_k).func_75971_g();
    
    buildHoeStone = new Achievement("achievement.buildStoneHoe", "buildHoeStone", 0, -7, Items.field_151018_J, AchievementList.field_76013_l).func_75971_g();
    buildHoeIron = new Achievement("achievement.buildHoeIron", "buildHoeIron", 1, -7, Items.field_151019_K, buildHoeStone).func_75971_g();
    buildHoeGold = new Achievement("achievement.buildHoeGold", "buildHoeGold", 1, -6, Items.field_151013_M, buildHoeIron).func_75971_g();
    buildHoeDiamond = new Achievement("achievement.buildHoeDiamond", "buildHoeDiamond", 2, -7, Items.field_151012_L, buildHoeIron).func_75987_b().func_75971_g();
    
    buildPickaxeIron = new Achievement("achievement.buildPickaxeIron", "buildPickaxeIron", 1, -4, Items.field_151035_b, AchievementList.field_76012_o).func_75971_g();
    buildPickaxeGold = new Achievement("achievement.buildPickaxeGold", "buildPickaxeGold", 1, -3, Items.field_151005_D, buildPickaxeIron).func_75971_g();
    buildPickaxeDiamond = new Achievement("achievement.buildPickaxeDiamond", "buildPickaxeDiamond", 2, -4, Items.field_151046_w, buildPickaxeIron).func_75987_b().func_75971_g();
    
    buildSwordStone = new Achievement("achievement.buildSwordStone", "buildSwordStone", 0, -1, Items.field_151052_q, AchievementList.field_76024_r).func_75971_g();
    buildSwordIron = new Achievement("achievement.buildSwordIron", "buildSwordIron", 1, -1, Items.field_151040_l, buildSwordStone).func_75971_g();
    buildSwordGold = new Achievement("achievement.buildSwordGold", "buildSwordGold", 1, 0, Items.field_151010_B, buildSwordIron).func_75971_g();
    buildSwordDiamond = new Achievement("achievement.buildSwordDiamond", "buildSwordDiamond", 2, -1, Items.field_151048_u, buildSwordIron).func_75987_b().func_75971_g();
    
    buildAxeWood = new Achievement("achievement.buildAxeWood", "buildAxeWood", -1, 2, Items.field_151053_p, AchievementList.field_76017_h).func_75971_g();
    buildAxeStone = new Achievement("achievement.buildAxeStone", "buildAxeStone", 0, 2, Items.field_151049_t, buildAxeWood).func_75971_g();
    buildAxeIron = new Achievement("achievement.buildAxeIron", "buildAxeIron", 1, 2, Items.field_151036_c, buildAxeStone).func_75971_g();
    buildAxeGold = new Achievement("achievement.buildAxeGold", "buildAxeGold", 1, 3, Items.field_151006_E, buildAxeIron).func_75971_g();
    buildAxeDiamond = new Achievement("achievement.buildAxeDiamond", "buildAxeDiamond", 2, 2, Items.field_151056_x, buildAxeIron).func_75987_b().func_75971_g();
    
    buildShovelWood = new Achievement("achievement.buildShovelWood", "buildShovelWood", -1, 5, Items.field_151038_n, AchievementList.field_76017_h).func_75971_g();
    buildShovelStone = new Achievement("achievement.buildShovelStone", "buildShovelStone", 0, 5, Items.field_151051_r, buildShovelWood).func_75971_g();
    buildShovelIron = new Achievement("achievement.buildShovelIron", "buildShovelIron", 1, 5, Items.field_151037_a, buildShovelStone).func_75971_g();
    buildShovelGold = new Achievement("achievement.buildShovelGold", "buildShovelGold", 1, 6, Items.field_151011_C, buildShovelIron).func_75971_g();
    buildShovelDiamond = new Achievement("achievement.buildShovelDiamond", "buildShovelDiamond", 2, 5, Items.field_151047_v, buildShovelIron).func_75987_b().func_75971_g();
    
    buildHelmetLeather = new Achievement("achievement.buildHelmetLeather", "buildHelmetLeather", 4, -7, Items.field_151024_Q, AchievementList.field_76022_t).func_75971_g();
    buildHelmetChain = new Achievement("achievement.buildHelmetChain", "buildHelmetChain", 5, -7, Items.field_151020_U, buildHelmetLeather).func_75971_g();
    buildHelmetIron = new Achievement("achievement.buildHelmetIron", "buildHelmetIron", 6, -7, Items.field_151028_Y, buildHelmetLeather).func_75971_g();
    buildHelmetGold = new Achievement("achievement.buildHelmetGold", "buildHelmetGold", 7, -7, Items.field_151169_ag, buildHelmetIron).func_75971_g();
    buildHelmetDiamond = new Achievement("achievement.buildHelmetDiamond", "buildHelmetDiamond", 8, -7, Items.field_151161_ac, buildHelmetIron).func_75987_b().func_75971_g();
    
    buildChestpieceLeather = new Achievement("achievement.buildChestpieceLeather", "buildChestpieceLeather", 4, -6, Items.field_151027_R, AchievementList.field_76022_t).func_75971_g();
    buildChestpieceChain = new Achievement("achievement.buildChestpieceChain", "buildChestpieceChain", 5, -6, Items.field_151023_V, buildChestpieceLeather).func_75971_g();
    buildChestpieceIron = new Achievement("achievement.buildChestpieceIron", "buildChestpieceIron", 6, -6, Items.field_151030_Z, buildChestpieceLeather).func_75971_g();
    buildChestpieceGold = new Achievement("achievement.buildChestpieceGold", "buildChestpieceGold", 7, -6, Items.field_151171_ah, buildChestpieceIron).func_75971_g();
    buildChestpieceDiamond = new Achievement("achievement.buildChestpieceDiamond", "buildChestpieceDiamond", 8, -6, Items.field_151163_ad, buildChestpieceIron).func_75987_b().func_75971_g();
    
    buildLeggingsLeather = new Achievement("achievement.buildLeggingsLeather", "buildLeggingsLeather", 4, -5, Items.field_151026_S, AchievementList.field_76022_t).func_75971_g();
    buildLeggingsChain = new Achievement("achievement.buildLeggingsChain", "buildLeggingsChain", 5, -5, Items.field_151022_W, buildLeggingsLeather).func_75971_g();
    buildLeggingsIron = new Achievement("achievement.buildLeggingsIron", "buildLeggingsIron", 6, -5, Items.field_151165_aa, buildLeggingsLeather).func_75971_g();
    buildLeggingsGold = new Achievement("achievement.buildLeggingsGold", "buildLeggingsGold", 7, -5, Items.field_151149_ai, buildLeggingsIron).func_75971_g();
    buildLeggingsDiamond = new Achievement("achievement.buildLeggingsDiamond", "buildLeggingsDiamond", 8, -5, Items.field_151173_ae, buildLeggingsIron).func_75987_b().func_75971_g();
    
    buildBootsLeather = new Achievement("achievement.buildBootsLeather", "buildBootsLeather", 4, -4, Items.field_151021_T, AchievementList.field_76022_t).func_75971_g();
    buildBootsChain = new Achievement("achievement.buildBootsChain", "buildBootsChain", 5, -4, Items.field_151029_X, buildBootsLeather).func_75971_g();
    buildBootsIron = new Achievement("achievement.buildBootsIron", "buildBootsIron", 6, -4, Items.field_151167_ab, buildBootsLeather).func_75971_g();
    buildBootsGold = new Achievement("achievement.buildBootsGold", "buildBootsGold", 7, -4, Items.field_151151_aj, buildBootsIron).func_75971_g();
    buildBootsDiamond = new Achievement("achievement.buildBootsDiamond", "buildBootsDiamond", 8, -4, Items.field_151175_af, buildBootsIron).func_75987_b().func_75971_g();
    
    craftCompass = new Achievement("achievement.craftCompass", "craftCompass", 4, 0, Items.field_151111_aL, AchievementList.field_76016_k).func_75971_g();
    craftClock = new Achievement("achievement.craftClock", "craftClock", 5, 0, Items.field_151113_aN, aquireGold).func_75971_g();
    
    aquireClay = new Achievement("achievement.aquireClay", "aquireClay", 3, 1, Items.field_151119_aD, null).func_75971_g();
    smeltBrick = new Achievement("achievement.smeltBrick", "smeltBrick", 4, 1, Items.field_151118_aC, aquireClay).func_75971_g();
    craftBricks = new Achievement("achievement.craftBricks", "craftBricks", 5, 1, Blocks.field_150336_V, smeltBrick).func_75971_g();
    
    aquireSand = new Achievement("achievement.aquireSand", "aquireSand", 3, 2, Blocks.field_150354_m, null).func_75971_g();
    smeltGlass = new Achievement("achievement.smeltGlass", "smeltGlass", 4, 2, Blocks.field_150359_w, aquireSand).func_75971_g();
    craftGlassPane = new Achievement("achievement.craftGlassPane", "craftGlassPane", 5, 2, Blocks.field_150410_aZ, smeltGlass).func_75971_g();
    
    craftBowl = new Achievement("achievement.craftBowl", "craftBowl", 4, 3, Items.field_151054_z, AchievementList.field_76017_h).func_75971_g();
    craftSoup = new Achievement("achievement.craftSoup", "craftSoup", 5, 3, Items.field_151009_A, craftBowl).func_75971_g();
    
    cookPork = new Achievement("achievement.cookPork", "cookPork", 6, 3, Items.field_151147_al, AchievementList.field_76015_j).func_75971_g();
    cookBeef = new Achievement("achievement.cookBeef", "cookBeef", 7, 3, Items.field_151083_be, AchievementList.field_76015_j).func_75971_g();
    cookChicken = new Achievement("achievement.cookChicken", "cookChicken", 8, 3, Items.field_151077_bg, AchievementList.field_76015_j).func_75971_g();
    
    aquireNetherrack = new Achievement("achievement.aquireNetherrack", "aquireNetherrack", 6, 1, Blocks.field_150424_aL, AchievementList.field_76029_x).func_75971_g();
    smeltNetherBrick = new Achievement("achievement.smeltNetherBrick", "smeltNetherBrick", 7, 1, Items.field_151130_bT, aquireNetherrack).func_75971_g();
    craftNetherBrick = new Achievement("achievement.craftNetherBrick", "craftNetherBrick", 8, 1, Blocks.field_150385_bj, smeltNetherBrick).func_75971_g();
    
    soulSand = new Achievement("achievement.soulSand", "soulSand", 6, 2, Blocks.field_150425_aM, null).func_75971_g();
    witherSkulls = new Achievement("achievement.witherSkulls", "witherSkulls", 7, 2, new ItemStack(Items.field_151144_bL, 1, 1), null).func_75971_g();
    
    mobKillChicken = new Achievement("achievement.mobKillChicken", "mobKillChicken", 6, 5, new ItemStack(Items.field_151063_bx, 1, 93), null).func_75971_g();
    mobKillCow = new Achievement("achievement.mobKillCow", "mobKillCow", 7, 5, new ItemStack(Items.field_151063_bx, 1, 92), null).func_75971_g();
    mobKillPig = new Achievement("achievement.mobKillPig", "mobKillPig", 8, 5, new ItemStack(Items.field_151063_bx, 1, 90), null).func_75971_g();
    mobKillSheep = new Achievement("achievement.mobKillSheep", "mobKillSheep", 9, 5, new ItemStack(Items.field_151063_bx, 1, 91), null).func_75971_g();
    mobKillBat = new Achievement("achievement.mobKillBat", "mobKillBat", 10, 5, new ItemStack(Items.field_151063_bx, 1, 65), null).func_75971_g();
    mobKillMooshroom = new Achievement("achievement.mobKillMooshroom", "mobKillMooshroom", 11, 5, new ItemStack(Items.field_151063_bx, 1, 96), null).func_75971_g();
    mobKillSquid = new Achievement("achievement.mobKillSquid", "mobKillSquid", 12, 5, new ItemStack(Items.field_151063_bx, 1, 94), null).func_75971_g();
    
    mobKillCaveSpider = new Achievement("achievement.mobKillCaveSpider", "mobKillCaveSpider", 6, 6, new ItemStack(Items.field_151063_bx, 1, 59), null).func_75971_g();
    mobKillEnderman = new Achievement("achievement.mobKillEnderman", "mobKillEnderman", 7, 6, new ItemStack(Items.field_151063_bx, 1, 58), null).func_75971_g();
    mobKillSpider = new Achievement("achievement.mobKillSpider", "mobKillSpider", 8, 6, new ItemStack(Items.field_151063_bx, 1, 52), null).func_75971_g();
    mobKillWolf = new Achievement("achievement.mobKillWolf", "mobKillWolf", 9, 6, new ItemStack(Items.field_151063_bx, 1, 95), null).func_75971_g();
    mobKillPigZombie = new Achievement("achievement.mobKillPigZombie", "mobKillPigZombie", 10, 6, new ItemStack(Items.field_151063_bx, 1, 57), null).func_75971_g();
    
    mobKillBlaze = new Achievement("achievement.mobKillBlaze", "mobKillBlaze", 6, 7, new ItemStack(Items.field_151063_bx, 1, 61), null).func_75971_g();
    mobKillCreeper = new Achievement("achievement.mobKillCreeper", "mobKillCreeper", 7, 7, new ItemStack(Items.field_151063_bx, 1, 50), null).func_75971_g();
    mobKillGhast = new Achievement("achievement.mobKillGhast", "mobKillGhast", 8, 7, new ItemStack(Items.field_151063_bx, 1, 56), null).func_75971_g();
    mobKillMagmaCube = new Achievement("achievement.mobKillMagmaCube", "mobKillMagmaCube", 9, 7, new ItemStack(Items.field_151063_bx, 1, 62), null).func_75971_g();
    mobKillSilverfish = new Achievement("achievement.mobKillSilverfish", "mobKillSilerfish", 10, 7, new ItemStack(Items.field_151063_bx, 1, 60), null).func_75971_g();
    mobKillSkeleton = new Achievement("achievement.mobKillSkeleton", "mobKillSkeleton", 11, 7, new ItemStack(Items.field_151063_bx, 1, 51), null).func_75971_g();
    mobKillSlime = new Achievement("achievement.mobKillSlime", "mobKillSlime", 12, 7, new ItemStack(Items.field_151063_bx, 1, 55), null).func_75971_g();
    mobKillWitch = new Achievement("achievement.mobKillWitch", "mobKillWitch", 13, 7, new ItemStack(Items.field_151063_bx, 1, 66), null).func_75971_g();
    mobKillZombie = new Achievement("achievement.mobKillZombie", "mobKillZombie", 14, 7, new ItemStack(Items.field_151063_bx, 1, 54), null).func_75971_g();
    
    mobKillSnowGolem = new Achievement("achievement.mobKillSnowGolem", "mobKillSnowGolem", 6, 8, new ItemStack(Blocks.field_150423_aK), null).func_75971_g();
    mobKillIronGolem = new Achievement("achievement.mobKillIronGolem", "mobKillIronGolem", 7, 8, new ItemStack(Blocks.field_150339_S), null).func_75971_g();
    
    ExtraAchievements = new AchievementPage("Extra Achievements", new Achievement[] { buildHoeStone, buildHoeIron, buildHoeGold, buildHoeDiamond, buildPickaxeIron, buildPickaxeGold, buildPickaxeDiamond, buildSwordStone, buildSwordIron, buildSwordGold, buildSwordDiamond, buildAxeWood, buildAxeStone, buildAxeIron, buildAxeGold, buildAxeDiamond, buildShovelWood, buildShovelStone, buildShovelIron, buildShovelGold, buildShovelDiamond, buildHelmetLeather, buildHelmetChain, buildHelmetIron, buildHelmetGold, buildHelmetDiamond, buildChestpieceLeather, buildChestpieceChain, buildChestpieceIron, buildChestpieceGold, buildChestpieceDiamond, buildLeggingsLeather, buildLeggingsChain, buildLeggingsIron, buildLeggingsGold, buildLeggingsDiamond, buildBootsLeather, buildBootsChain, buildBootsIron, buildBootsGold, buildBootsDiamond, craftCompass, craftClock, aquireClay, smeltBrick, craftBricks, aquireSand, smeltGlass, craftGlassPane, craftBowl, craftSoup, cookPork, cookBeef, cookChicken, aquireNetherrack, smeltNetherBrick, craftNetherBrick, soulSand, witherSkulls, mobKillChicken, mobKillCow, mobKillPig, mobKillSheep, mobKillBat, mobKillMooshroom, mobKillSquid, mobKillCaveSpider, mobKillEnderman, mobKillSpider, mobKillWolf, mobKillPigZombie, mobKillBlaze, mobKillCreeper, mobKillGhast, mobKillMagmaCube, mobKillSilverfish, mobKillSkeleton, mobKillSlime, mobKillWitch, mobKillZombie, mobKillSnowGolem, mobKillIronGolem });
  }
}
