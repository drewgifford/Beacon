package com.drewgifford.beacon.commands;

import com.drewgifford.beacon.ChatUtil;
import com.drewgifford.beacon.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCommand extends BeaconCommand{

    public HelpCommand(){
        super(new String[]{"help", "?"}, "Shows the help menu for Beacon", "");
    }

    @Override
    public boolean run(CommandSender sender, String[] args){

        ChatUtil.sendMessage(sender, Messages.MENU_HEADER, "Beacon Help");
        for(BeaconCommand command : CommandHandler.commands){
            String usage = "";
            if(command.usage != ""){
                usage = " " + command.usage;
            }
            ChatUtil.sendMessage(sender, Messages.HELP_FORMAT, "/beacon " + command.commandLabels[0] + ChatColor.GRAY + usage, command.description);

        }

        return true;
    }
}
