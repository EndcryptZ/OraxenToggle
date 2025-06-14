package com.exendv2.oraxentoggle.commands;

import com.exendv2.oraxentoggle.OraxenToggle;

public class CommandManager {

    private final OraxenToggle plugin;
    public CommandManager(OraxenToggle plugin) {
        this.plugin = plugin;
        registerCommands();
    }

    private void registerCommands() {
        new OraxenToggleCommand(plugin);
    }
}
