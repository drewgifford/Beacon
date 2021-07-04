package com.drewgifford.beacon;

import com.drewgifford.beacon.commands.CommandHandler;
import com.drewgifford.beacon.module.ModuleLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import spark.Spark;

public class BeaconPlugin extends JavaPlugin {
    // I'm really doing this, aren't I...

    private PluginDescriptionFile pluginYml = this.getDescription();
    String version = pluginYml.getVersion();
    public static DataStore dataStore;
    public static ModuleLoader moduleLoader;
    private static BeaconServer server;

    //Yup, we are...

    @Override
    public void onEnable(){
        Spark.port(8080);
        ChatUtil.sendMessage(null, "Initializing Beacon v" + version);
        ChatUtil.sendMessage(null, "Let's get this party started!");

        dataStore = new DataStore(this);
        loadConfiguration();

        server = new BeaconServer(this);
        loadWebserver();

        this.getCommand("beacon").setExecutor(new CommandHandler());

        moduleLoader = new ModuleLoader();
        try {
            moduleLoader.reloadModules();
        } catch (Exception e){
            e.printStackTrace();
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable(){

        ChatUtil.sendMessage(null, "Party's over. Shutting down...");

    }



    public static void loadConfiguration(){
        // Initialize our Data Storage! Yay!
        ChatUtil.sendMessage(null, "Loading configuration...");

        // venmo @drews.gif please

        try {

            dataStore.initialize();

        } catch (Exception e){

            // Uh oh! Something went wrong loading files.
            ChatUtil.sendMessage(null, "&cWe've run into an error on file initialization.");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("Beacon"));

            return;

        }
    }

    public static void loadWebserver(){
        // Initializing webserver
        // $unsetcoda on cashapp (not me)
        ChatUtil.sendMessage(null, "Starting webserver...");


        try {
            server.initialize();
        } catch (Exception e){
            ChatUtil.sendMessage(null, "&cWe've run into an error on webserver initialization.");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("Beacon"));

            return;
        }
    }


}
