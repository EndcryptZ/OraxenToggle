package com.exendv2.oraxentoggle.commands;

import com.exendv2.oraxentoggle.OraxenToggle;
import com.exendv2.oraxentoggle.data.PlayerData;
import com.exendv2.oraxentoggle.utils.ColorUtils;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OraxenToggleCommand {

    private final OraxenToggle plugin;
    public OraxenToggleCommand(OraxenToggle plugin) {
        this.plugin = plugin;
        registerCommands();
    }

    private void registerCommands() {
        new CommandAPICommand("oraxentoggle")
                .withAliases("ot")
                .withArguments(new PlayerArgument("player")
                        .setOptional(true)
                        .withPermission("oraxentoggle.toggle.others"))
                .executes(this::toggle)
                .register();

        new CommandAPICommand("oraxentogglereload")
                .withAliases("otr")
                .withPermission("oraxentoggle.reload")
                .executes(this::reload)
                .register();
    }


    private void toggle(CommandSender sender, CommandArguments args) {
        Player target = args.getUnchecked("player");
        PlayerData playerData;
        String prefix = plugin.getConfigManager().getPrefix();

        if(target != null) {
            playerData = plugin.getDatabaseManager().loadPlayerData(target.getUniqueId());
            if(playerData.isOraxenEnabled()) {
                playerData.setOraxenEnabled(false);
                target.sendMessage(ColorUtils.color(prefix + "<green>Your oraxen auto download has been disabled by " + sender.getName()));
            } else {
                playerData.setOraxenEnabled(true);
                target.sendMessage(ColorUtils.color(prefix + "<green>Your oraxen auto download has been enabled by " + sender.getName()));
            }
            plugin.getDatabaseManager().savePlayerData(playerData);
            return;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage(ColorUtils.color(prefix + "<red>You must be a player to use this command!"));
            return;
        }

        playerData = plugin.getDatabaseManager().loadPlayerData(((Player) sender).getUniqueId());
        if(playerData.isOraxenEnabled()) {
            playerData.setOraxenEnabled(false);
            sender.sendMessage(ColorUtils.color(prefix + "<green>Your oraxen auto download has been disabled!"));
        } else {
            playerData.setOraxenEnabled(true);
            sender.sendMessage(ColorUtils.color(prefix + "<green>Your oraxen auto download has been enabled!"));
        }
        plugin.getDatabaseManager().savePlayerData(playerData);

    }

    private void reload(CommandSender sender, CommandArguments args) {
        plugin.getConfigManager().reloadConfigs();

        String message = plugin.getConfigManager().getPrefix() + "<green>Plugin has been reloaded";
        sender.sendMessage(ColorUtils.color(message));
    }
}
