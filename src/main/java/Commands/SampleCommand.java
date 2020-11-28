package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SampleCommand extends Command {
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        Bukkit.getLogger().info("Test");
        return true;
    }

    @Override
    public String getCommand() {
        return "test";
    }
}
