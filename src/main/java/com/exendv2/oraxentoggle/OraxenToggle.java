package com.exendv2.oraxentoggle;

import com.exendv2.oraxentoggle.commands.CommandManager;
import com.exendv2.oraxentoggle.config.ConfigManager;
import com.exendv2.oraxentoggle.data.DatabaseManager;
import com.exendv2.oraxentoggle.data.PlayerDataListener;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class OraxenToggle extends JavaPlugin {

    ConfigManager configManager;
    DatabaseManager databaseManager;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this)
                .shouldHookPaperReload(true)
        );
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable();
        initializeInstances();
        registerListeners();
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }

    private void initializeInstances() {
        configManager = new ConfigManager(this);
        databaseManager = new DatabaseManager(this);
        new CommandManager(this);

    }

    private void registerListeners() {
        new PlayerDataListener(this);
    }

}
