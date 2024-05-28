package com.exendv2.oraxentoggle.listeners;

import com.exendv2.oraxentoggle.OraxenToggle;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvents implements Listener {

    private final OraxenToggle plugin;

    public PlayerEvents(OraxenToggle plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        plugin.getPlayerManager().loadUserDataFile(event.getPlayer());

        boolean isEnabled = plugin.getPlayerManager().isEnabled(event.getPlayer());
        if(isEnabled){
            String command = "/oraxen pack send";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command + " " + event.getPlayer().getName());

        }
    }

}
