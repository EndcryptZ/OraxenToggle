package com.exendv2.oraxentoggle;

import com.exendv2.oraxentoggle.commands.MainCommand;
import com.exendv2.oraxentoggle.commands.ReloadCommand;
import com.exendv2.oraxentoggle.listeners.PlayerEvents;
import com.exendv2.oraxentoggle.managers.PlayerManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class OraxenToggle extends JavaPlugin {

    private File userDataFolder;
    private File messagesFile;
    public String enableMessage;
    public String disableMessage;
    public String prefix;
    PlayerManager playerManager;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
        getLogger().info("Plugin Enabled!");
        createMessages();
        createUserDataFolder();
        this.getCommand("autorp").setExecutor(new MainCommand(this));
        this.getCommand("oraxentogglereload").setExecutor(new ReloadCommand(this));
        playerManager = new PlayerManager(this);

    }


    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled!");
        // Plugin shutdown logic
    }


    private void createMessages() {
        messagesFile = new File(getDataFolder(), "messages.yml");
        if(!messagesFile.exists()){
            getLogger().info("messages.yml file not found!");
            getLogger().info("Creating the messages.yml file!");
            messagesFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

    }

    private void createUserDataFolder() {
        userDataFolder = new File(getDataFolder(), "userdata");
        if(!userDataFolder.exists()){
            getLogger().info("userdata folder not found!");
            getLogger().info("Creating the userdata folder!");
            userDataFolder.mkdirs();
        }
        loadMessages();
    }

    public File getUserDataFolder() {
        return userDataFolder;
    }

    public PlayerManager getPlayerManager(){
        return playerManager;
    }

    public void loadMessages(){
        FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);
        enableMessage = messages.getString("enable-message");
        disableMessage = messages.getString("disable-message");
        prefix = messages.getString("prefix");
    }

}
