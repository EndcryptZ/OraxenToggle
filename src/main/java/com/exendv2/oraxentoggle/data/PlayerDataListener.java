package com.exendv2.oraxentoggle.data;

import com.exendv2.oraxentoggle.OraxenToggle;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerDataListener implements Listener {

    private final OraxenToggle plugin;
    public PlayerDataListener(OraxenToggle plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerData playerData = plugin.getDatabaseManager().loadPlayerData(event.getPlayer().getUniqueId());
        if(playerData.isOraxenEnabled()) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "oraxen pack send " + event.getPlayer().getName()), 15L);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerJoinEvent event) {
        plugin.getDatabaseManager().savePlayerData(plugin.getDatabaseManager().loadPlayerData(event.getPlayer().getUniqueId()));
    }
}
