package me.meloni.melonos.Tools;

import me.meloni.melonos.Modules.Moderator.ModeratorLevel;
import me.meloni.melonos.Modules.Player.Online;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AllowedPlayerTargets {
    public static List<Player> targets(CommandSender commandSender) {
        List<Player> onlinePlayers = Online.onlinePlayers();
        if (commandSender instanceof Player) {
            List<Player> List = new ArrayList<>();
            for (Player target : onlinePlayers) {
                if (ModeratorLevel.isAllowed(commandSender, target))
                    List.add(target);
            }
            return List;
        } else {
            return onlinePlayers;
        }
    }
}
