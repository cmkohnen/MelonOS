import Commands.Command;
import Commands.MainCommand;
import Commands.SampleCommand;
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
        commands.add(new MainCommand());

        commands.add(new SampleCommand());

        return commands;
    }

    public List<Command> commands() {
        return activeCommands();
    }

    public void registerCommands() {
        for (Command activeCommand : activeCommands()) {
            PluginCommand command = plugin.getCommand(activeCommand.getCommand());
            command.setExecutor(activeCommand);
            command.setTabCompleter(activeCommand);
        }
    }
}
