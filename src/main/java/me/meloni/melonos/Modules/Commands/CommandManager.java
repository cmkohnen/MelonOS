package me.meloni.melonos.Modules.Commands;

import me.meloni.melonos.MelonOS;
import org.bukkit.command.PluginCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    MelonOS plugin;
    public CommandManager(MelonOS instance) {
        this.plugin = instance;
    }

    private List<Command> activeCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new MainCommand(plugin));

        commands.add(new Sudo(plugin));
        commands.add(new BroadcastSay(plugin));
        commands.add(new Sudo(plugin));
        commands.add(new GameMode(plugin));
        commands.add(new Kick(plugin));
        commands.add(new Kill(plugin));

        return commands;
    }

    public List<Command> commands() {
        return activeCommands();
    }

    public void registerCommands() {
        for (Command activeCommand : activeCommands()) {
            for (String command : activeCommand.getCommands()) {
                PluginCommand pluginCommand = plugin.getCommand(command);
                assert pluginCommand != null;
                pluginCommand.setExecutor(activeCommand);
                pluginCommand.setTabCompleter(activeCommand);
            }
        }
    }
}
