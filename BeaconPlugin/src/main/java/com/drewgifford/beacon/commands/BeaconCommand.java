package com.drewgifford.beacon.commands;

import org.bukkit.command.CommandSender;

import java.util.Arrays;

public abstract class BeaconCommand {

    String[] commandLabels;
    String description;
    String usage;

    public BeaconCommand(String[] commandLabels, String description, String usage){
        this.commandLabels = commandLabels;
        this.description = description;
        this.usage = usage;

    }

    public boolean hasLabel(String s){
        return Arrays.asList(commandLabels).contains(s);
    }

    public boolean run(CommandSender sender, String[] args){
        return true;
    }

}
