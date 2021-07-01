package com.drewgifford.beacon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Console;
import java.util.logging.Logger;

public class BeaconPlugin extends JavaPlugin {
    // I'm really doing this, aren't I...

    private PluginDescriptionFile pluginYml = this.getDescription();
    String version = pluginYml.getVersion();
    public static DataStore dataStore;
    private BeaconServer server;

    //Yup, we are...

    @Override
    public void onEnable(){
        ChatUtil.sendMessage(null, "Initializing Beacon v" + version);
        ChatUtil.sendMessage(null, "Let's get this party started!");

        loadConfiguration();


        loadWebserver();
    }

    @Override
    public void onDisable(){

        ChatUtil.sendMessage(null, "Party's over. Shutting down...");

    }


    public void loadConfiguration(){
        // Initialize our Data Storage! Yay!
        ChatUtil.sendMessage(null, "Loading configuration...");

        dataStore = new DataStore(this);

        // venmo @drews.gif please

        try {

            dataStore.initialize();

        } catch (Exception e){

            // Uh oh! Something went wrong loading files.
            ChatUtil.sendMessage(null, "&cWe've run into an error on file initialization.");
            e.printStackTrace();
            this.setEnabled(false);

            return;

        }
    }

    public void loadWebserver(){
        // Initializing webserver
        // $unsetcoda on cashapp (not me)
        ChatUtil.sendMessage(null, "Starting webserver...");
        server = new BeaconServer(this);

        try {
            server.initialize();
        } catch (Exception e){
            ChatUtil.sendMessage(null, "&cWe've run into an error on webserver initialization.");
            e.printStackTrace();
            this.setEnabled(false);

            return;
        }
    }


}
