package com.drewgifford.beacon;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class DataStore {

    private BeaconPlugin plugin;
    private String pluginFolder = "plugins" + File.separator + "Beacon";
    private String modulesFolder = "plugins" + File.separator + "Beacon" + File.separator + "modules";

    private String configFilePath = "config.yml";
    private String messagesFilePath = "messages.yml";

    private YamlConfiguration configYaml;
    private YamlConfiguration messagesYaml;

    public DataStore(BeaconPlugin plugin){
        this.plugin = plugin;
    }

    //This is where the fun starts.
    void initialize() throws Exception {

        // I think Bukkit does this already but screw it, let's ensure that our folder is made!
        File dataFolder = new File(pluginFolder);
        if(!dataFolder.exists()){
            dataFolder.mkdirs();
        }

        // Initialize our files
        ChatUtil.sendMessage(null, "Loading config.yml");
        configYaml = initializeFile(configFilePath);
        ChatUtil.sendMessage(null, "Loading messages.yml");
        messagesYaml = initializeFile(messagesFilePath);

        // Initialize modules!
        File modFolder = new File(modulesFolder);
        if(!modFolder.exists()){
            modFolder.mkdirs();
        }

    }

    // Here we go!
    private YamlConfiguration initializeFile(String filePath) throws Exception{

        File configFile = new File(pluginFolder + File.separator + filePath);

        // Let's make that file if it doesn't exist yet. Otherwise, that would be sad.
        if(!configFile.exists()){
            plugin.saveResource(filePath, false);
        }

        // Thank god for yamlconfiguration.
        YamlConfiguration cfg = new YamlConfiguration();
        cfg.load(configFile);
        return cfg;

    }

    // Ugh! I hate people who write getters.



    // sssshhh... I made some getters
    public YamlConfiguration getConfig(){
        return this.configYaml;
    }
    public YamlConfiguration getMessages(){
        return this.messagesYaml;
    }

    public String getMessage(Messages message){
        // I'm no expert but this should just grab the message from the configuration file determined earlier.
        String messageId = message.name();
        String raw;

        if (!messagesYaml.contains(messageId)){
            // I don't have any other ideas for this message, unfortunately.
            raw = "&cNo message found for " + messageId+".";
        } else {
            raw = messagesYaml.getString(messageId);
        }
        // Lazy function!
        return ChatUtil.translateColor(raw);

    }
    public String getMessage(Messages message, String... args){
        // We can use the original method to make an even better method!
        String raw = getMessage(message);
        int counter = 0;
        // This should replace {0}, {1}, {2}, with their respective arguments... unless I'm dumb! Very much a possibility!
        for (String arg : args){
            raw = raw.replace("{" + counter + "}", arg);
            counter++;
        }
        // Lazy function part 2!
        return ChatUtil.translateColor(raw);

    }


}
