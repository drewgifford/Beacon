package com.drewgifford.beacon.module;

import com.drewgifford.beacon.ChatUtil;
import com.drewgifford.beacon.entry.BeaconCollection;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

            player.add("health", p.getHealth(), "players.health");
            player.add("username", p.getName(), "players.username");
            player.add("hunger", p.getFoodLevel(), "players.hunger");

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
}
