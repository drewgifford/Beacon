package com.drewgifford.beacon.commands;

import com.drewgifford.beacon.BeaconPlugin;
import com.drewgifford.beacon.ChatUtil;
import com.drewgifford.beacon.Messages;
import com.drewgifford.beacon.module.BeaconModule;
import org.bukkit.command.CommandSender;

public class UpdateCommand extends BeaconCommand{

    public UpdateCommand(){
        super(new String[]{"update", "propagate"}, "Disables a module", "[id]");
    }

    @Override
    public boolean run(CommandSender sender, String[] args){

        if(args.length > 0){

            String moduleId = args[0];
            for(BeaconModule module : BeaconPlugin.moduleLoader.getModules()){
                if(module.getId().equalsIgnoreCase(moduleId) || module.getName().equalsIgnoreCase(moduleId)){

                    try {

                        module.update();
                        ChatUtil.sendMessage(sender, Messages.MODULE_UPDATED, module.getName());

                    } catch(Exception e){

                        module.setEnabled(false);
                        ChatUtil.sendMessage(sender, Messages.MODULE_UPDATED_ERROR, module.getName());

                    }

                    return true;

                }
            }

        }
        ChatUtil.sendMessage(sender, Messages.MODULE_NOT_FOUND);
        return true;
    }
}
