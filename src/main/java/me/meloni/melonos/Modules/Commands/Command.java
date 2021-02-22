package me.meloni.melonos.Modules.Commands;

import me.meloni.melonos.MelonOS;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Command implements CommandExecutor, TabCompleter {
    private final MelonOS instance;
    private String permission;
    public Command(MelonOS instance) {
        this.instance = instance;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return null;
    }

    public List<String> getCommands() {
        return new ArrayList<>();
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {return permission;}

    public MelonOS getInstance() {
        return instance;
    }
}
