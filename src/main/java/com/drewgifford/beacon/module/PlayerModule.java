package com.drewgifford.beacon.module;

import com.drewgifford.beacon.ChatUtil;
import com.drewgifford.beacon.entry.BeaconCollection;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

public class PlayerModule extends BeaconModule{


    public PlayerModule(){
        super(
                "player",
                "Player",
                "Various attributes for the Player and HumanEntity classes",
                "1.0"
        );
    }

    @Override
    public BeaconModule update(){

        BeaconCollection collection = this.getCollection();

        for(Player p : Bukkit.getOnlinePlayers()){

            BeaconCollection player = new BeaconCollection();


            // Player-Only Variables
            player.add("allowFlight", p.getAllowFlight(), "player.allowFlight")
                    .add("address", p.getAddress().getAddress().toString(), "player.address")
                    .addLocation("bedSpawnLocation", p.getBedSpawnLocation(), "player.bedSpawnLocation")
                    .add("clientViewDistance", p.getClientViewDistance(), "player.clientViewDistance")
                    .addLocation("compassTarget", p.getCompassTarget(), "player.compassTarget")
                    .add("displayName", p.getDisplayName(), "player.displayName")
                    .add("exp", p.getExp(), "player.exp")
                    .add("flySpeed", p.getFlySpeed(), "player.flySpeed")
                    .add("healthScale", p.getHealthScale(), "player.getHealthScale")
                    .add("level", p.getLevel(), "player.level")
                    .add("locale", p.getLocale(), "player.locale")
                    .add("ping", getPing(p), "player.ping")
                    .add("playerListFooter", p.getPlayerListFooter(), "player.playerListFooter")
                    .add("playerListHeader", p.getPlayerListHeader(), "player.playerListHeader")
                    .add("playerListName", p.getPlayerListName(), "player.playerListName")
                    .add("playerTime", p.getPlayerTime(), "player.playerTime")
                    .add("playerTimeOffset", p.getPlayerTimeOffset(), "player.playerTimeOffset")
                    .add("walkSpeed", p.getWalkSpeed(), "player.walkSpeed")
                    .add("isFlying", p.isFlying(), "player.isFlying")
                    .add("isHealthScaled", p.isHealthScaled(), "player.isHealthScaled")
                    .add("isOnGround", p.isOnGround(), "player.isOnGround")
                    .add("isPlayerTimeRelative", p.isPlayerTimeRelative(), "player.isPlayerTimeRelative")
                    .add("isSleepingIgnored", p.isSleepingIgnored(), "player.isSleepingIgnored")
                    .add("isSneaking", p.isSneaking(), "player.isSneaking")
                    .add("isSprinting", p.isSprinting(), "player.isSprinting");

            if(p.getPlayerWeather() != null){
                player.add("playerWeather", p.getPlayerWeather().name(), "player.playerWeather");
            }

            // HumanEntity Variables
            player.add("attackCooldown", p.getAttackCooldown(), "player.attackCooldown")
                    .add("exhaustion", p.getExhaustion(), "player.exhaustion")
                    .add("expToLevel", p.getExpToLevel(), "player.expToLevel")
                    .add("foodLevel", p.getFoodLevel(), "player.foodLevel")
                    .add("gameMode", p.getGameMode().name(), "player.gameMode")
                    .add("name", p.getName(), "player.name")
                    //.add("saturatedRegenRate", p.getSaturatedRegenRate(), "players.saturatedRegenRate")
                    .add("saturation", p.getSaturation(), "player.saturation")
                    .add("sleepTicks", p.getSleepTicks(), "player.sleepTicks")
                    //.add("starvationRate", p.getStarvationRate(), "player.starvationRate")
                    //.add("unsaturatedRegenRate", p.getUnsaturatedRegenRate(), "player.unsaturatedRegenRate")
                    .add("isBlocking", p.isBlocking(), "player.isBlocking")
                    .add("isHandRaised", p.isHandRaised(), "player.isHandRaised");

            // Killer
            if(p.getKiller() != null){
                player.add("killer", p.getKiller(), "player.killer");
            }

            // LivingEntity Variables
            player.add("arrowCooldown", p.getArrowCooldown(), "player.arrowCooldown")
                    .add("arrowsInBody", p.getArrowsInBody(), "player.arrowsInBody")
                    .add("canPickupItems", p.getCanPickupItems(), "player.canPickupItems")
                    .add("eyeHeight", p.getEyeHeight(), "player.eyeHeight")
                    .addLocation("eyeLocation", p.getEyeLocation(), "player.eyeLocation")
                    .add("lastDamage", p.getLastDamage(), "player.lastDamage")
                    .add("maximumAir", p.getMaximumAir(), "player.maximumAir")
                    .add("maximumNoDamageTicks", p.getMaximumNoDamageTicks(), "player.maximumNoDamageTicks")
                    .add("noDamageTicks", p.getNoDamageTicks(), "player.noDamageTicks")
                    .add("remainingAir", p.getRemainingAir(), "player.remainingAir")
                    //.add("isClimbing", p.isClimbing(), "players.isClimbing")
                    .add("isCollidable", p.isCollidable(), "player.isCollidable")
                    .add("isGliding", p.isGliding(), "player.isGliding")
                    //.add("isInvisible", p.isInvisible(), "players.isInvisible")
                    .add("isRiptiding", p.isRiptiding(), "player.isRiptiding")
                    .add("isSleeping", p.isSleeping(), "player.isSleeping")
                    .add("isSwimming", p.isSwimming(), "player.isSwimming");

            // Entity Variables
            player.add("uuid", p.getUniqueId().toString(), "player") // UUID is the player key, anyways. Having this is just a QOL thing
                    .add("facing", p.getFacing().name(), "player.facing")
                    .add("fallDistance", p.getFallDistance(), "player.fallDistance")
                    .add("fireTicks", p.getFireTicks(), "player.fireTicks")
                    //TODO: 1.17: p.getFreezeTicks()
                    .add("height", p.getHeight(), "player.height")
                    .addLocation("location", p.getLocation(), "player.location")
                    .add("maxFireTicks", p.getMaxFireTicks(), "player.maxFireTicks")
                    //TODO: 1.17: p.getMaxFreezeTicks()
                    .addVector("velocity", p.getVelocity(), "player.velocity")
                    .add("width", p.getWidth(), "player.width")
                    .add("world", p.getWorld().getName(), "player.world")
                    .add("isDead", p.isDead(), "player.isDead")
                    //TODO: 1.17: p.isFrozen()
                    .add("isGlowing", p.isGlowing(), "player.isGlowing")
                    .add("isInsideVehicle", p.isInsideVehicle(), "player.isInsideVehicle");
                    //.add("isInWater", p.isInWater(), "players.isInWater")
                    //.add("isVisualFire", p.isVisualFire(), "players.isVisualFire");
            if(p.getLastDamageCause() != null) {
                player.add("lastDamageCause", p.getLastDamageCause().getCause().name(), "player.lastDamageCause");
            }
            // Damageable Variables
            player.add("health", p.getHealth(), "player.health")
                    .add("absorptionAmount", p.getAbsorptionAmount(), "player.absorptionAmount")
                    .add("maxHealth", p.getMaxHealth(), "player.maxHealth");

            // Potion Effects
            BeaconCollection potions = new BeaconCollection();
            for(PotionEffect e : p.getActivePotionEffects()){
                BeaconCollection effect = new BeaconCollection();
                effect.add("duration", e.getDuration(), "player.activePotionEffects.duration");
                effect.add("amplifier", e.getAmplifier(), "player.activePotionEffects.amplifier");
                effect.add("name", e.getType().getName(), "player.activePotionEffects");

                potions.add(e.getType().getName(), effect, "player.activePotionEffects");
            }

            player.add("activePotionEffects", potions, "player.activePotionEffects");


            // Inventory
            BeaconCollection inventory = new BeaconCollection();

            int size = p.getInventory().getSize();


            // Iterate through the inventory
            for(int slot = 0; slot < size; slot++){

                // Create the collection for our item
                BeaconCollection item = new BeaconCollection();

                ItemStack s = p.getInventory().getItem(slot);

                if(s == null){
                    continue;
                }
                /*if(s.getType().isAir()){
                    continue;
                }*/

                ItemMeta meta = s.getItemMeta();

                // Add all the item data

                item.add("material", s.getType().name(), "player.inventory.material");
                item.add("amount", s.getAmount(), "player.inventory.amount");
                item.add("durability", s.getDurability(), "player.inventory.durability");

                // And if it's applicable, add display names and lore!
                if(meta.hasDisplayName()){
                    item.add("displayName", s.getItemMeta().getDisplayName(), "player.inventory.displayname");
                }
                if(meta.hasLore()){
                    item.add("lore", s.getItemMeta().getLore(), "player.inventory.lore");
                }
                // Check for any enchants, first create the collection
                BeaconCollection enchants = new BeaconCollection();
                for(Enchantment e : s.getEnchantments().keySet()){
                    enchants.add(e.getName(), s.getEnchantments().get(e), "player.inventory.enchants");
                }
                item.add("enchantments", enchants, "player.inventory.enchants");

                // Add this item to the inventory. If they can see the inventory, of course they can see the items so we just require the same permission here.

                inventory.add(Integer.toString(slot), item, "player.inventory");


            }

            player.add("inventory", inventory, "player.inventory");

            collection.add(p.getUniqueId().toString(), player, "player");

        }
        this.setCollection(collection);

        return this;
    }

    private int getPing(Player p) {
        try {
            Method handle = p.getClass().getMethod("getHandle");
            Object nmsHandle = handle.invoke(p);
            Field pingField = nmsHandle.getClass().getField("ping");
            return pingField.getInt(nmsHandle);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
                | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
