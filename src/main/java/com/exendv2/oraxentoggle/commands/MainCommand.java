package com.exendv2.oraxentoggle.commands;

import com.exendv2.oraxentoggle.OraxenToggle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private final OraxenToggle plugin;

    public MainCommand(OraxenToggle plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(!(sender instanceof Player)){
            sender.sendMessage("This command is only available for players!");
            return true;
        }

        plugin.getPlayerManager().toggleAutoDownload(((Player) sender).getPlayer());

        return true;
    }
}
