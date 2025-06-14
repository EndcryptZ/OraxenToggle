package com.exendv2.oraxentoggle.config;

import com.exendv2.oraxentoggle.OraxenToggle;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private final OraxenToggle plugin;
    private FileConfiguration messagesConfig;
    private File messagesFile;

    public ConfigManager(OraxenToggle plugin) {
        this.plugin = plugin;
        setupFiles();
    }

    private void setupFiles() {
        // Create plugin directory if it doesn't exist
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        messagesFile = new File(plugin.getDataFolder(), "messages.yml");

        if (!messagesFile.exists()) {
            plugin.saveResource("messages.yml", false);
        }

        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        setDefaults();
    }

    private void setDefaults() {
        // Messages defaults
        messagesConfig.addDefault("prefix", "<white>[<blue>OraxenToggle<white>]: ");
        messagesConfig.addDefault("disable-message", "<green>Disabled Auto-Download!");
        messagesConfig.addDefault("enable-message", "<green>Enabled Auto-Download!");

        messagesConfig.options().copyDefaults(true);
        saveMessages();
    }

    public void reloadConfigs() {
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public void saveMessages() {
        try {
            messagesConfig.save(messagesFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save messages.yml!");
            e.printStackTrace();
        }
    }

    // Message getters with MiniMessage support
    public String getPrefix() {
        return messagesConfig.getString("prefix", "<white>[<blue>OraxenToggle<white>]: ");
    }

    public String getDisableMessage() {
        return messagesConfig.getString("disable-message", "<green>Disabled Auto-Download!");
    }

    public String getEnableMessage() {
        return messagesConfig.getString("enable-message", "<green>Enabled Auto-Download!");
    }

    // Method to get raw message strings if needed
    public String getRawMessage(String path) {
        return messagesConfig.getString(path);
    }

    // Method to set a message
    public void setMessage(String path, String message) {
        messagesConfig.set(path, message);
        saveMessages();
    }

    // Get the FileConfiguration if needed
    public FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }
}
