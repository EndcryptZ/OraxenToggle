package com.exendv2.oraxentoggle;

import com.exendv2.oraxentoggle.commands.MainCommand;
import com.exendv2.oraxentoggle.commands.ReloadCommand;
import com.exendv2.oraxentoggle.listeners.PlayerEvents;
import com.exendv2.oraxentoggle.managers.PlayerManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class OraxenToggle extends JavaPlugin {

    private static OraxenToggle instance;
    private File userDataFolder;
    private File messagesFile;
    public String enableMessage;
    public String disableMessage;
    public String prefix;
    PlayerManager playerManager;

    @Override
    public void onEnable() {
        instance = this;

        // Register events
        getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);

        // Initialize messages and user data
        createMessages();
        createUserDataFolder();

        // Set command executors
        this.getCommand("autorp").setExecutor(new MainCommand(this));
        this.getCommand("oraxentogglereload").setExecutor(new ReloadCommand(this));

        // Initialize player manager
        playerManager = new PlayerManager(this);

        getLogger().info("Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }

    private void createMessages() {
        messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            getLogger().info("messages.yml file not found! Creating...");
            saveResource("messages.yml", false);
        }
        loadMessages();
    }

    private void createUserDataFolder() {
        userDataFolder = new File(getDataFolder(), "userdata");
        if (!userDataFolder.exists()) {
            getLogger().info("userdata folder not found! Creating...");
            userDataFolder.mkdirs();
        }
    }

    public File getUserDataFolder() {
        return userDataFolder;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void loadMessages() {
        FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);
        enableMessage = messages.getString("enable-message");
        disableMessage = messages.getString("disable-message");
        prefix = messages.getString("prefix");
        try {
            messages.save(messagesFile);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not save messages.yml", e);
        }
    }

    public static OraxenToggle getInstance() {
        return instance;
    }
}
