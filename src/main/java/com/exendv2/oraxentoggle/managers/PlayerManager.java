package com.exendv2.oraxentoggle.managers;

import com.exendv2.oraxentoggle.OraxenToggle;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class PlayerManager {

    private final OraxenToggle plugin;

    public PlayerManager(OraxenToggle plugin){
        this.plugin = plugin;
    }

    public File getUserDataFile(Player player){
            return new File(plugin.getUserDataFolder(), player.getUniqueId() + ".yml");
    }

    public boolean isEnabled(Player player){
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(getUserDataFile(player));
        return (boolean) playerData.get("Enabled");
    }

    public void toggleAutoDownload(Player player){
        File data = getUserDataFile(player);
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(getUserDataFile(player));
        String prefix = plugin.prefix;
        String enabledMessage = prefix + plugin.enableMessage;
        String disableMessage = prefix + plugin.disableMessage;
        if(isEnabled(player)){
            playerData.set("Enabled", false);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', disableMessage));
        } else {
            playerData.set("Enabled", true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', enabledMessage));
        }

        try {
            playerData.save(data);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save the player data for " + player.getUniqueId(), e);
        }
    }

    public void loadUserDataFile(Player player){
        File data = getUserDataFile(player);
        if(!data.exists()){
            try {
                data.createNewFile();
                FileConfiguration newUserData = YamlConfiguration.loadConfiguration(data);
                newUserData.set("Enabled", true);
                newUserData.save(data);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not create userdata file for " + player.getUniqueId(), e);
            }
        }


    }

}
