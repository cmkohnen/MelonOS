package me.meloni.melonos.Modules.Player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Online {
    public static List<Player> onlinePlayers() {
        Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
        Bukkit.getServer().getOnlinePlayers().toArray(players);
        return Arrays.asList(players);
    }

    public static boolean isOnline(String player){
        return Bukkit.getPlayer(player) != null;
    }
}
