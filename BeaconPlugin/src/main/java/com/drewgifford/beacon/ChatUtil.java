package com.drewgifford.beacon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ChatUtil {

    // God, I'm really lazy, aren't I?
    public static String translateColor(String... args){

        String result = String.join(" ", args);
        return ChatColor.translateAlternateColorCodes('&', result);

    }
    public static String stripColor(String... args){

        String result = String.join(" ", args);
        return ChatColor.stripColor(result);

    }

    public static void sendMessage(Object target, String... args){

        // We're working with a player, maybe?
        if (target instanceof Player){

            Player p = (Player) target;
            p.sendMessage(ChatUtil.translateColor(args));

        } else {

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            // Fine, let's just do a Console message...
            console.sendMessage(ChatUtil.translateColor("&bBEACON &3|&r ") + ChatUtil.translateColor(args));

        }
    }

    // What if I just want to send a single Message instance? I'm lazy.
    public static void sendMessage(Object target, Messages msg){
        DataStore dataStore = BeaconPlugin.dataStore;

        String argTranslated = dataStore.getMessage(msg);
        sendMessage(target, argTranslated);

    }
    // MULTIPLE ARGUMENT MESSAGES!!! YAYAYA
    public static void sendMessage(Object target, Messages msg, String... args){
        DataStore dataStore = BeaconPlugin.dataStore;

        String argTranslated = dataStore.getMessage(msg, args);
        sendMessage(target, argTranslated);

    }
}
