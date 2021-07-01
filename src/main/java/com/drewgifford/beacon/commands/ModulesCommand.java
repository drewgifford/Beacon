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
        super(new String[]{"modules", "module"}, "Displays all modules", "[id]");
    }

    @Override
    public boolean run(CommandSender sender, String[] args){

        ChatUtil.sendMessage(sender, Messages.MENU_HEADER, "Modules");

        List<String> modules = new ArrayList<String>();

        for(BeaconModule module : BeaconPlugin.moduleLoader.modules) {

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
