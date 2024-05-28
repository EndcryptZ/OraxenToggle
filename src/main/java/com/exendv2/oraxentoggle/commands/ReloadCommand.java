package com.exendv2.oraxentoggle.commands;

import com.exendv2.oraxentoggle.OraxenToggle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final OraxenToggle plugin;

    public ReloadCommand(OraxenToggle plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        plugin.loadMessages();
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "[OraxenToggle] &aMessages reloaded!"));

        return true;
    }
}
