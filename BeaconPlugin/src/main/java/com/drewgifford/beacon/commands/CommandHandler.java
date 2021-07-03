package com.drewgifford.beacon.commands;

import com.drewgifford.beacon.ChatUtil;
import com.drewgifford.beacon.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler implements CommandExecutor {

    public static List<BeaconCommand> commands = new ArrayList<BeaconCommand>();

    public CommandHandler(){
        commands = new ArrayList<BeaconCommand>();

        commands.add(new HelpCommand());
        commands.add(new ModulesCommand());
        commands.add(new DisableCommand());
        commands.add(new EnableCommand());
        commands.add(new UpdateCommand());
        commands.add(new ReloadCommand());

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("beacon") || label.equalsIgnoreCase("bcn")){
            // Beacon command!
            if(args.length < 1){
                args = new String[]{"help"};
            }
            args[0] = args[0].toLowerCase();

            for(BeaconCommand cmd : commands){
                if(cmd.hasLabel(args[0])){
                    return cmd.run(sender, Arrays.copyOfRange(args, 1, args.length));
                }
            }

            ChatUtil.sendMessage(sender, Messages.COMMAND_NOT_FOUND);
            return true;

        }
        return false;




    }
}
