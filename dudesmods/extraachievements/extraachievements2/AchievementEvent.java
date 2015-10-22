package dudesmods.extraachievements.extraachievements2;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class AchievementEvent
{
  @SubscribeEvent
  public void onPickup(PlayerEvent.ItemPickupEvent e)
  {
    if (e.pickedUp.func_92059_d().func_77969_a(new ItemStack(Items.field_151119_aD))) {
      e.player.func_71064_a(ExtraAchievements.aquireClay, 1);
    }
    if (e.pickedUp.func_92059_d().func_77969_a(new ItemStack(Items.field_151144_bL, 1))) {
      e.player.func_71064_a(ExtraAchievements.witherSkulls, 1);
    }
    if (e.pickedUp.func_92059_d().func_77969_a(new ItemStack(Blocks.field_150425_aM))) {
      e.player.func_71064_a(ExtraAchievements.soulSand, 1);
    }
    if (e.pickedUp.func_92059_d().func_77969_a(new ItemStack(Blocks.field_150424_aL))) {
      e.player.func_71064_a(ExtraAchievements.aquireNetherrack, 1);
    }
    if (e.pickedUp.func_92059_d().func_77969_a(new ItemStack(Blocks.field_150354_m))) {
      e.player.func_71064_a(ExtraAchievements.aquireSand, 1);
    }
  }
  
  @SubscribeEvent
  public void onCrafting(PlayerEvent.ItemCraftedEvent e)
  {
    if (e.crafting.func_77973_b().equals(Items.field_151018_J)) {
      e.player.func_71064_a(ExtraAchievements.buildHoeStone, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151019_K)) {
      e.player.func_71064_a(ExtraAchievements.buildHoeIron, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151013_M)) {
      e.player.func_71064_a(ExtraAchievements.buildHoeGold, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151012_L)) {
      e.player.func_71064_a(ExtraAchievements.buildHoeDiamond, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151035_b)) {
      e.player.func_71064_a(ExtraAchievements.buildPickaxeIron, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151005_D)) {
      e.player.func_71064_a(ExtraAchievements.buildPickaxeGold, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151046_w)) {
      e.player.func_71064_a(ExtraAchievements.buildPickaxeDiamond, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151052_q)) {
      e.player.func_71064_a(ExtraAchievements.buildSwordStone, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151040_l)) {
      e.player.func_71064_a(ExtraAchievements.buildSwordIron, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151010_B)) {
      e.player.func_71064_a(ExtraAchievements.buildSwordGold, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151048_u)) {
      e.player.func_71064_a(ExtraAchievements.buildSwordDiamond, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151053_p)) {
      e.player.func_71064_a(ExtraAchievements.buildAxeWood, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151049_t)) {
      e.player.func_71064_a(ExtraAchievements.buildAxeStone, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151036_c)) {
      e.player.func_71064_a(ExtraAchievements.buildAxeIron, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151006_E)) {
      e.player.func_71064_a(ExtraAchievements.buildAxeGold, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151056_x)) {
      e.player.func_71064_a(ExtraAchievements.buildAxeDiamond, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151038_n)) {
      e.player.func_71064_a(ExtraAchievements.buildShovelWood, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151051_r)) {
      e.player.func_71064_a(ExtraAchievements.buildShovelStone, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151037_a)) {
      e.player.func_71064_a(ExtraAchievements.buildShovelIron, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151011_C)) {
      e.player.func_71064_a(ExtraAchievements.buildShovelGold, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151047_v)) {
      e.player.func_71064_a(ExtraAchievements.buildShovelDiamond, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151024_Q)) {
      e.player.func_71064_a(ExtraAchievements.buildHelmetLeather, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151020_U)) {
      e.player.func_71064_a(ExtraAchievements.buildHelmetChain, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151028_Y)) {
      e.player.func_71064_a(ExtraAchievements.buildHelmetIron, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151169_ag)) {
      e.player.func_71064_a(ExtraAchievements.buildHelmetGold, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151161_ac)) {
      e.player.func_71064_a(ExtraAchievements.buildHelmetDiamond, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151027_R)) {
      e.player.func_71064_a(ExtraAchievements.buildChestpieceLeather, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151023_V)) {
      e.player.func_71064_a(ExtraAchievements.buildChestpieceChain, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151030_Z)) {
      e.player.func_71064_a(ExtraAchievements.buildChestpieceIron, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151171_ah)) {
      e.player.func_71064_a(ExtraAchievements.buildChestpieceGold, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151163_ad)) {
      e.player.func_71064_a(ExtraAchievements.buildChestpieceDiamond, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151026_S)) {
      e.player.func_71064_a(ExtraAchievements.buildLeggingsLeather, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151022_W)) {
      e.player.func_71064_a(ExtraAchievements.buildLeggingsChain, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151165_aa)) {
      e.player.func_71064_a(ExtraAchievements.buildLeggingsIron, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151149_ai)) {
      e.player.func_71064_a(ExtraAchievements.buildLeggingsGold, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151173_ae)) {
      e.player.func_71064_a(ExtraAchievements.buildLeggingsDiamond, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151021_T)) {
      e.player.func_71064_a(ExtraAchievements.buildBootsLeather, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151029_X)) {
      e.player.func_71064_a(ExtraAchievements.buildBootsChain, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151167_ab)) {
      e.player.func_71064_a(ExtraAchievements.buildBootsIron, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151151_aj)) {
      e.player.func_71064_a(ExtraAchievements.buildBootsGold, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151175_af)) {
      e.player.func_71064_a(ExtraAchievements.buildBootsDiamond, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151111_aL)) {
      e.player.func_71064_a(ExtraAchievements.craftCompass, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151113_aN)) {
      e.player.func_71064_a(ExtraAchievements.craftClock, 1);
    }
    if (e.crafting.func_77973_b().equals(Item.func_150898_a(Blocks.field_150336_V))) {
      e.player.func_71064_a(ExtraAchievements.craftBricks, 1);
    }
    if (e.crafting.func_77973_b().equals(Item.func_150898_a(Blocks.field_150410_aZ))) {
      e.player.func_71064_a(ExtraAchievements.craftGlassPane, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151054_z)) {
      e.player.func_71064_a(ExtraAchievements.craftBowl, 1);
    }
    if (e.crafting.func_77973_b().equals(Items.field_151009_A)) {
      e.player.func_71064_a(ExtraAchievements.craftSoup, 1);
    }
    if (e.crafting.func_77973_b().equals(Item.func_150898_a(Blocks.field_150385_bj))) {
      e.player.func_71064_a(ExtraAchievements.craftNetherBrick, 1);
    }
  }
  
  @SubscribeEvent
  public void onSmelting(PlayerEvent.ItemSmeltedEvent e)
  {
    if (e.smelting.func_77973_b().equals(Items.field_151118_aC)) {
      e.player.func_71064_a(ExtraAchievements.smeltBrick, 1);
    }
    if (e.smelting.func_77973_b().equals(Items.field_151043_k)) {
      e.player.func_71064_a(ExtraAchievements.aquireGold, 1);
    }
    if (e.smelting.func_77973_b().equals(Item.func_150898_a(Blocks.field_150359_w))) {
      e.player.func_71064_a(ExtraAchievements.smeltGlass, 1);
    }
    if (e.smelting.func_77973_b().equals(Items.field_151147_al)) {
      e.player.func_71064_a(ExtraAchievements.cookPork, 1);
    }
    if (e.smelting.func_77973_b().equals(Items.field_151083_be)) {
      e.player.func_71064_a(ExtraAchievements.cookBeef, 1);
    }
    if (e.smelting.func_77973_b().equals(Items.field_151077_bg)) {
      e.player.func_71064_a(ExtraAchievements.cookChicken, 1);
    }
    if (e.smelting.func_77973_b().equals(Items.field_151130_bT)) {
      e.player.func_71064_a(ExtraAchievements.smeltNetherBrick, 1);
    }
  }
  
  private static boolean mobChickenKill = false;
  private static boolean mobCowKill = false;
  private static boolean mobPigKill = false;
  private static boolean mobSheepKill = false;
  private static boolean mobBatKill = false;
  private static boolean mobMooShroomKill = false;
  private static boolean mobSquidKill = false;
  private static boolean mobCaveSpiderKill = false;
  private static boolean mobEndermanKill = false;
  private static boolean mobSpiderKill = false;
  private static boolean mobWolfKill = false;
  private static boolean mobPigZombieKill = false;
  private static boolean mobBlazeKill = false;
  private static boolean mobCreeperKill = false;
  private static boolean mobGhastKill = false;
  private static boolean mobMagmaCubeKill = false;
  private static boolean mobSilverfishKill = false;
  private static boolean mobSkeletonKill = false;
  private static boolean mobSlimeKill = false;
  private static boolean mobWitchKill = false;
  private static boolean mobZombieKill = false;
  private static boolean mobSnowGolemKill = false;
  private static boolean mobIronGolemKill = false;
  
  @SubscribeEvent
  public void onEntityDeath(LivingDeathEvent event)
  {
    Entity e = event.entityLiving;
    DamageSource source = event.source;
    if ((source.func_76364_f() instanceof EntityPlayer))
    {
      if ((e instanceof EntityChicken)) {
        mobChickenKill = true;
      }
      if ((e instanceof EntityCow)) {
        mobCowKill = true;
      }
      if ((e instanceof EntityPig)) {
        mobPigKill = true;
      }
      if ((e instanceof EntitySheep)) {
        mobSheepKill = true;
      }
      if ((e instanceof EntityBat)) {
        mobBatKill = true;
      }
      if ((e instanceof EntityMooshroom)) {
        mobMooShroomKill = true;
      }
      if ((e instanceof EntitySquid)) {
        mobSquidKill = true;
      }
      if ((e instanceof EntityCaveSpider)) {
        mobCaveSpiderKill = true;
      }
      if ((e instanceof EntityEnderman)) {
        mobEndermanKill = true;
      }
      if ((e instanceof EntitySpider)) {
        mobSpiderKill = true;
      }
      if ((e instanceof EntityWolf)) {
        mobWolfKill = true;
      }
      if ((e instanceof EntityPigZombie)) {
        mobPigZombieKill = true;
      }
      if ((e instanceof EntityBlaze)) {
        mobBlazeKill = true;
      }
      if ((e instanceof EntityCreeper)) {
        mobCreeperKill = true;
      }
      if ((e instanceof EntityGhast)) {
        mobGhastKill = true;
      }
      if ((e instanceof EntityMagmaCube)) {
        mobMagmaCubeKill = true;
      }
      if ((e instanceof EntitySilverfish)) {
        mobSilverfishKill = true;
      }
      if ((e instanceof EntitySkeleton)) {
        mobSkeletonKill = true;
      }
      if ((e instanceof EntitySlime)) {
        mobSlimeKill = true;
      }
      if ((e instanceof EntityWitch)) {
        mobWitchKill = true;
      }
      if ((e instanceof EntityZombie)) {
        mobZombieKill = true;
      }
      if ((e instanceof EntitySnowman)) {
        mobSnowGolemKill = true;
      }
      if ((e instanceof EntityIronGolem)) {
        mobIronGolemKill = true;
      }
    }
  }
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event)
  {
    if (mobChickenKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillChicken, 1);
      mobChickenKill = false;
    }
    if (mobCowKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillCow, 1);
      mobCowKill = false;
    }
    if (mobPigKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillPig, 1);
      mobPigKill = false;
    }
    if (mobSheepKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillSheep, 1);
      mobSheepKill = false;
    }
    if (mobBatKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillBat, 1);
      mobBatKill = false;
    }
    if (mobMooShroomKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillMooshroom, 1);
      mobMooShroomKill = false;
    }
    if (mobSquidKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillSquid, 1);
      mobSquidKill = false;
    }
    if (mobCaveSpiderKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillCaveSpider, 1);
      mobCaveSpiderKill = false;
    }
    if (mobEndermanKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillEnderman, 1);
      mobEndermanKill = false;
    }
    if (mobSpiderKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillSpider, 1);
      mobSpiderKill = false;
    }
    if (mobWolfKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillWolf, 1);
      mobWolfKill = false;
    }
    if (mobPigZombieKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillPigZombie, 1);
      mobPigZombieKill = false;
    }
    if (mobBlazeKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillBlaze, 1);
      mobBlazeKill = false;
    }
    if (mobCreeperKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillCreeper, 1);
      mobCreeperKill = false;
    }
    if (mobGhastKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillGhast, 1);
      mobGhastKill = false;
    }
    if (mobMagmaCubeKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillMagmaCube, 1);
      mobMagmaCubeKill = false;
    }
    if (mobSilverfishKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillSilverfish, 1);
      mobSilverfishKill = false;
    }
    if (mobSkeletonKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillSkeleton, 1);
      mobSkeletonKill = false;
    }
    if (mobSlimeKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillSlime, 1);
      mobSlimeKill = false;
    }
    if (mobWitchKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillWitch, 1);
      mobWitchKill = false;
    }
    if (mobZombieKill == true)
    {
      event.player.func_71064_a(ExtraAchievements.mobKillZombie, 1);
      mobZombieKill = false;
    }
  }
}
