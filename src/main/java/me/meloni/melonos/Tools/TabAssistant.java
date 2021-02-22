package me.meloni.melonos.Tools;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabAssistant {
    private final CommandSender commandSender;
    private final String arg;
    List<String> arguments = new ArrayList<>();
    public TabAssistant(CommandSender sender, String arg) {
        this.commandSender = sender;
        this.arg = arg;
    }

    public void addAllowedTargets() {
        for (Player target : AllowedPlayerTargets.targets(commandSender)) {
            if (target.getName().startsWith(arg) && !arguments.contains(target.getName())) {
                arguments.add(target.getName());
            }
        }
    }

    public void addAllowedTargetsIfHasPermission(String permission) {
        if(commandSender.hasPermission(permission)) {
            addAllowedTargets();
        }
    }

    public void add(String argument) {
        if(argument.startsWith(arg) && !arguments.contains(argument)) {
            arguments.add(argument);
        }
    }

    public void addIfHasPermission(String argument, String permission) {
        if(commandSender.hasPermission(permission)) {
            add(argument);
        }
    }

    public void addMultiple(List<String> args) {
        for (String s : args) {
            add(s);
        }
    }

    public void addMultipleIfHasPermission(List<String> args, String permission) {
        if(commandSender.hasPermission(permission)) {
            addMultiple(args);
        }
    }

    public void addMultipleIfHasPermissions(List<String> args, List<String> permissions) {
        for (String permission : permissions) {
            addMultipleIfHasPermission(args, permission);
        }
    }

    public List<String> getArguments() {
        return arguments;
    }
}
