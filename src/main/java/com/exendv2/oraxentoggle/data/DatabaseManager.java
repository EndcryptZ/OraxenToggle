package com.exendv2.oraxentoggle.data;

import com.exendv2.oraxentoggle.OraxenToggle;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseManager {
    private final OraxenToggle plugin;
    private final File databaseFile;
    private FileConfiguration database;

    public DatabaseManager(OraxenToggle plugin) {
        this.plugin = plugin;
        this.databaseFile = new File(plugin.getDataFolder(), "database.yml");
        this.loadDatabase();
    }

    private void loadDatabase() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        if (!databaseFile.exists()) {
            try {
                databaseFile.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create database.yml!");
                e.printStackTrace();
            }
        }

        database = YamlConfiguration.loadConfiguration(databaseFile);

        // Ensure the players section exists
        if (!database.contains("players")) {
            database.createSection("players");
            saveDatabase();
        }
    }

    public void saveDatabase() {
        try {
            database.save(databaseFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save database.yml!");
            e.printStackTrace();
        }
    }

    public void savePlayerData(PlayerData playerData) {
        String uuid = playerData.getPlayerUUID().toString();
        String path = "players." + uuid;

        database.set(path + ".name", Bukkit.getOfflinePlayer(playerData.getPlayerUUID()).getName());
        database.set(path + ".oraxenEnabled", playerData.isOraxenEnabled());
        database.set(path + ".lastUpdate", playerData.getLastUpdate());

        saveDatabase();
    }

    public PlayerData loadPlayerData(UUID uuid) {
        String path = "players." + uuid.toString();

        // If player doesn't exist in database, return new PlayerData with default values
        if (!database.contains(path)) {
            return new PlayerData(uuid);
        }

        PlayerData playerData = new PlayerData(uuid);
        playerData.setOraxenEnabled(database.getBoolean(path + ".oraxenEnabled", true));

        // Update last update time if it exists in database
        if (database.contains(path + ".lastUpdate")) {
            long lastUpdate = database.getLong(path + ".lastUpdate");
            playerData.setLastUpdate(lastUpdate);
        }

        return playerData;
    }

    public void removePlayerData(UUID uuid) {
        database.set("players." + uuid.toString(), null);
        saveDatabase();
    }

    public boolean hasPlayerData(UUID uuid) {
        return database.contains("players." + uuid.toString());
    }

    public List<UUID> getAllStoredPlayers() {
        List<UUID> players = new ArrayList<>();
        ConfigurationSection playersSection = database.getConfigurationSection("players");

        if (playersSection != null) {
            for (String uuid : playersSection.getKeys(false)) {
                try {
                    players.add(UUID.fromString(uuid));
                } catch (IllegalArgumentException ignored) {
                    plugin.getLogger().warning("Invalid UUID found in database: " + uuid);
                }
            }
        }

        return players;
    }

    public void cleanup(long maxAge) {
        long now = System.currentTimeMillis();
        List<UUID> toRemove = new ArrayList<>();

        for (UUID uuid : getAllStoredPlayers()) {
            String path = "players." + uuid.toString();
            long lastUpdate = database.getLong(path + ".lastUpdate", 0);

            if (now - lastUpdate > maxAge) {
                toRemove.add(uuid);
            }
        }

        for (UUID uuid : toRemove) {
            removePlayerData(uuid);
            plugin.getLogger().info("Cleaned up old data for player: " + uuid);
        }
    }
}
