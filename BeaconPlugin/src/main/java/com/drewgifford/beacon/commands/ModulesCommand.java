package com.drewgifford.beacon.commands;

import com.drewgifford.beacon.BeaconPlugin;
import com.drewgifford.beacon.ChatUtil;
import com.drewgifford.beacon.Messages;
import com.drewgifford.beacon.module.BeaconModule;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ModulesCommand extends BeaconCommand{

    public ModulesCommand(){
        super(new String[]{"modules", "module", "info", "list"}, "Displays list of modules or information about a single module", "[id]");
    }

    @Override
    public boolean run(CommandSender sender, String[] args){

        if(args.length > 0){

            String moduleId = args[0];
            for(BeaconModule module : BeaconPlugin.moduleLoader.getModules()){
                if(module.getId().equalsIgnoreCase(moduleId) || module.getName().equalsIgnoreCase(moduleId)){

                    ChatUtil.sendMessage(sender, Messages.MENU_HEADER, "Module Info");
                    ChatUtil.sendMessage(sender, Messages.MODULE_INFO_NAME, module.getName());
                    ChatUtil.sendMessage(sender, Messages.MODULE_INFO_ID, module.getId());
                    ChatUtil.sendMessage(sender, Messages.MODULE_INFO_VERSION, module.getVersion());
                    ChatUtil.sendMessage(sender, Messages.MODULE_INFO_AUTHOR, module.getAuthor());
                    ChatUtil.sendMessage(sender, Messages.MODULE_INFO_DEPENDENCIES, String.join( ", ", module.getDependencies()));
                    ChatUtil.sendMessage(sender, Messages.MODULE_INFO_DESCRIPTION, module.getDescription());
                    return true;

                }
            }

            ChatUtil.sendMessage(sender, Messages.MODULE_NOT_FOUND);
            return true;
        }

        ChatUtil.sendMessage(sender, Messages.MENU_HEADER, "Modules");

        List<String> modules = new ArrayList<String>();

        for(BeaconModule module : BeaconPlugin.moduleLoader.getModules()) {

            if(module.isEnabled()){
                modules.add(ChatColor.GREEN + module.getName());
            } else {
                modules.add(ChatColor.RED + module.getName());
            }

        }

        ChatUtil.sendMessage(sender, ChatColor.WHITE + "Modules (" + modules.size() + "): " + String.join(ChatColor.RESET + ", ", modules));

        return true;
    }
}
