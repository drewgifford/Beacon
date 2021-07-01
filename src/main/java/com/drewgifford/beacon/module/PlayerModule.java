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

    @Override
    public String getModuleId(){
        return "players";
    }

    @Override
    public BeaconModule update(){

        BeaconCollection collection = this.getCollection();

        for(Player p : Bukkit.getOnlinePlayers()){

            BeaconCollection player = new BeaconCollection();


            // Player-Only Variables
            player.add("allowFlight", p.getAllowFlight(), "players.allowFlight")
                    .add("address", p.getAddress().getAddress().toString(), "players.address")
                    .addLocation("bedSpawnLocation", p.getBedSpawnLocation(), "players.bedSpawnLocation")
                    .add("clientViewDistance", p.getClientViewDistance(), "players.clientViewDistance")
                    .addLocation("compassTarget", p.getCompassTarget(), "players.compassTarget")
                    .add("displayName", p.getDisplayName(), "players.displayName")
                    .add("exp", p.getExp(), "player.exp")
                    .add("flySpeed", p.getFlySpeed(), "player.flySpeed")
                    .add("healthScale", p.getHealthScale(), "player.getHealthScale")
                    .add("level", p.getLevel(), "player.level")
                    .add("locale", p.getLocale(), "player.locale")
                    .add("ping", getPing(p), "player.ping")
                    .add("playerListFooter", p.getPlayerListFooter(), "players.playerListFooter")
                    .add("playerListHeader", p.getPlayerListHeader(), "players.playerListHeader")
                    .add("playerListName", p.getPlayerListName(), "players.playerListName")
                    .add("playerTime", p.getPlayerTime(), "players.playerTime")
                    .add("playerTimeOffset", p.getPlayerTimeOffset(), "players.playerTimeOffset")
                    .add("walkSpeed", p.getWalkSpeed(), "players.walkSpeed")
                    .add("isFlying", p.isFlying(), "players.isFlying")
                    .add("isHealthScaled", p.isHealthScaled(), "players.isHealthScaled")
                    .add("isOnGround", p.isOnGround(), "players.isOnGround")
                    .add("isPlayerTimeRelative", p.isPlayerTimeRelative(), "players.isPlayerTimeRelative")
                    .add("isSleepingIgnored", p.isSleepingIgnored(), "players.isSleepingIgnored")
                    .add("isSneaking", p.isSneaking(), "players.isSneaking")
                    .add("isSprinting", p.isSprinting(), "players.isSprinting");

            if(p.getPlayerWeather() != null){
                player.add("playerWeather", p.getPlayerWeather().name(), "players.playerWeather");
            }

            // HumanEntity Variables
            player.add("attackCooldown", p.getAttackCooldown(), "players.attackCooldown")
                    .add("exhaustion", p.getExhaustion(), "players.exhaustion")
                    .add("expToLevel", p.getExpToLevel(), "players.expToLevel")
                    .add("foodLevel", p.getFoodLevel(), "players.foodLevel")
                    .add("gameMode", p.getGameMode().name(), "players.gameMode")
                    .add("name", p.getName(), "players.name")
                    //.add("saturatedRegenRate", p.getSaturatedRegenRate(), "players.saturatedRegenRate")
                    .add("saturation", p.getSaturation(), "players.saturation")
                    .add("sleepTicks", p.getSleepTicks(), "players.sleepTicks")
                    //.add("starvationRate", p.getStarvationRate(), "players.starvationRate")
                    //.add("unsaturatedRegenRate", p.getUnsaturatedRegenRate(), "players.unsaturatedRegenRate")
                    .add("isBlocking", p.isBlocking(), "players.isBlocking")
                    .add("isHandRaised", p.isHandRaised(), "players.isHandRaised");

            // Killer
            if(p.getKiller() != null){
                player.add("killer", p.getKiller(), "players.killer");
            }

            // LivingEntity Variables
            player.add("arrowCooldown", p.getArrowCooldown(), "players.arrowCooldown")
                    .add("arrowsInBody", p.getArrowsInBody(), "players.arrowsInBody")
                    .add("canPickupItems", p.getCanPickupItems(), "players.canPickupItems")
                    .add("eyeHeight", p.getEyeHeight(), "players.eyeHeight")
                    .addLocation("eyeLocation", p.getEyeLocation(), "players.eyeLocation")
                    .add("lastDamage", p.getLastDamage(), "players.lastDamage")
                    .add("maximumAir", p.getMaximumAir(), "players.maximumAir")
                    .add("maximumNoDamageTicks", p.getMaximumNoDamageTicks(), "players.maximumNoDamageTicks")
                    .add("noDamageTicks", p.getNoDamageTicks(), "players.noDamageTicks")
                    .add("remainingAir", p.getRemainingAir(), "players.remainingAir")
                    //.add("isClimbing", p.isClimbing(), "players.isClimbing")
                    .add("isCollidable", p.isCollidable(), "players.isCollidable")
                    .add("isGliding", p.isGliding(), "players.isGliding")
                    //.add("isInvisible", p.isInvisible(), "players.isInvisible")
                    .add("isRiptiding", p.isRiptiding(), "players.isRiptiding")
                    .add("isSleeping", p.isSleeping(), "players.isSleeping")
                    .add("isSwimming", p.isSwimming(), "players.isSwimming");

            // Entity Variables
            player.add("uuid", p.getUniqueId().toString(), "players") // UUID is the player key, anyways. Having this is just a QOL thing
                    .add("facing", p.getFacing().name(), "players.facing")
                    .add("fallDistance", p.getFallDistance(), "players.fallDistance")
                    .add("fireTicks", p.getFireTicks(), "players.fireTicks")
                    //TODO: 1.17: p.getFreezeTicks()
                    .add("height", p.getHeight(), "players.height")
                    .addLocation("location", p.getLocation(), "players.location")
                    .add("maxFireTicks", p.getMaxFireTicks(), "players.maxFireTicks")
                    //TODO: 1.17: p.getMaxFreezeTicks()
                    .addVector("velocity", p.getVelocity(), "players.velocity")
                    .add("width", p.getWidth(), "players.width")
                    .add("world", p.getWorld().getName(), "players.world")
                    .add("isDead", p.isDead(), "players.isDead")
                    //TODO: 1.17: p.isFrozen()
                    .add("isGlowing", p.isGlowing(), "players.isGlowing")
                    .add("isInsideVehicle", p.isInsideVehicle(), "players.isInsideVehicle");
                    //.add("isInWater", p.isInWater(), "players.isInWater")
                    //.add("isVisualFire", p.isVisualFire(), "players.isVisualFire");
            if(p.getLastDamageCause() != null) {
                player.add("lastDamageCause", p.getLastDamageCause().getCause().name(), "players.lastDamageCause");
            }
            // Damageable Variables
            player.add("health", p.getHealth(), "players.health")
                    .add("absorptionAmount", p.getAbsorptionAmount(), "players.absorptionAmount")
                    .add("maxHealth", p.getMaxHealth(), "players.maxHealth");

            // Potion Effects
            BeaconCollection potions = new BeaconCollection();
            for(PotionEffect e : p.getActivePotionEffects()){
                BeaconCollection effect = new BeaconCollection();
                effect.add("duration", e.getDuration(), "players.activePotionEffects.duration");
                effect.add("amplifier", e.getAmplifier(), "players.activePotionEffects.amplifier");
                effect.add("name", e.getType().getName(), "players.activePotionEffects");

                potions.add(e.getType().getName(), effect, "players.activePotionEffects");
            }

            player.add("activePotionEffects", potions, "players.activePotionEffects");


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

                item.add("material", s.getType().name(), "players.inventory.material");
                item.add("amount", s.getAmount(), "players.inventory.amount");
                item.add("durability", s.getDurability(), "players.inventory.durability");

                // And if it's applicable, add display names and lore!
                if(meta.hasDisplayName()){
                    item.add("displayName", s.getItemMeta().getDisplayName(), "players.inventory.displayname");
                }
                if(meta.hasLore()){
                    item.add("lore", s.getItemMeta().getLore(), "players.inventory.lore");
                }
                // Check for any enchants, first create the collection
                BeaconCollection enchants = new BeaconCollection();
                for(Enchantment e : s.getEnchantments().keySet()){
                    enchants.add(e.getName(), s.getEnchantments().get(e), "players.inventory.enchants");
                }
                item.add("enchantments", enchants, "players.inventory.enchants");

                // Add this item to the inventory. If they can see the inventory, of course they can see the items so we just require the same permission here.

                inventory.add(Integer.toString(slot), item, "players.inventory");


            }

            player.add("inventory", inventory, "players.inventory");

            collection.add(p.getUniqueId().toString(), player, "players");

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
