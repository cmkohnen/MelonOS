package me.meloni.melonos.Modules.Moderator;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModeratorLevel {
    public static boolean isAllowed(CommandSender player, Player target) {
        return !(player instanceof Player) || ModeratorLevel.moderatorLevel((Player) player) >= ModeratorLevel.moderatorLevel(target);
    }

    public static boolean hasModeratorLevel(Player p)  {

        for(int i = 9; i>0; i--) {
            if (p.hasPermission("melonos.moderator.level." + i)) {
                return true;
            }
        }
        return false;
    }



    public static int moderatorLevel(Player p) {
        int maxLevel = 9;
        for(int i = maxLevel; i>0; i-- ) {
            if(p.hasPermission("melonos.moderator.level." + i)) {
                return i;
            }
        }
        return 0;
    }
}
