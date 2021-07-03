package com.drewgifford.beacon.commands;

import com.drewgifford.beacon.BeaconPlugin;
import com.drewgifford.beacon.ChatUtil;
import com.drewgifford.beacon.Messages;
import com.drewgifford.beacon.module.BeaconModule;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class EnableCommand extends BeaconCommand{

    public EnableCommand(){
        super(new String[]{"enable", "load"}, "Enables a module", "[id]");
    }

    @Override
    public boolean run(CommandSender sender, String[] args){

        if(args.length > 0){

            String moduleId = args[0];
            for(BeaconModule module : BeaconPlugin.moduleLoader.getModules()){
                if(module.getId().equalsIgnoreCase(moduleId) || module.getName().equalsIgnoreCase(moduleId)){

                    boolean isEnabled = module.isEnabled();

                    if(isEnabled){

                        ChatUtil.sendMessage(sender, Messages.MODULE_ALREADY_ENABLED, module.getName());

                    } else {

                        try {
                            module.setEnabled(true);
                            ChatUtil.sendMessage(sender, Messages.MODULE_ENABLED, module.getName());
                        } catch (Exception e){
                            ChatUtil.sendMessage(sender, Messages.MODULE_ENABLED_ERROR, module.getName());
                            e.printStackTrace();
                        }

                    }
                    return true;

                }
            }

        }
        ChatUtil.sendMessage(sender, Messages.MODULE_NOT_FOUND);
        return true;
    }
}
