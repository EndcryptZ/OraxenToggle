package com.exendv2.oraxentoggle.listeners;

import com.exendv2.oraxentoggle.OraxenToggle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

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

            asyncTaskSendPack(event.getPlayer());

        }
    }

    private void asyncTaskSendPack(final Player player) {
        (new BukkitRunnable() {
            public void run() {
                taskSendPack(player);
            }
        }).runTaskLaterAsynchronously(OraxenToggle.getInstance(), 15L);
    }

    private void taskSendPack(final Player player) {
        (new BukkitRunnable() {
            public void run() {
                String command = "/oraxen pack send " + player.getName();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }

        }).runTask(OraxenToggle.getInstance());
    }

}
