package com.drewgifford.beacon.commands;

import com.drewgifford.beacon.BeaconPlugin;
import com.drewgifford.beacon.ChatUtil;
import com.drewgifford.beacon.Messages;
import com.drewgifford.beacon.module.BeaconModule;
import com.drewgifford.beacon.module.ModuleLoader;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import spark.Spark;

public class ReloadCommand extends BeaconCommand{

    public ReloadCommand(){
        super(new String[]{"reload", "restart"}, "Reloads configuration and restarts the Beacon webserver", "[id]");
    }

    @Override
    public boolean run(CommandSender sender, String[] args){

        try {

            BeaconPlugin.loadConfiguration();


            BeaconPlugin.loadWebserver();

            BeaconPlugin.moduleLoader.reloadModules();

        } catch (Exception e){
            ChatUtil.sendMessage(null, "&cAn error occurred reloading Beacon. See console for details.");
            Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("Beacon"));
            e.printStackTrace();
        }
        return true;
    }
}
