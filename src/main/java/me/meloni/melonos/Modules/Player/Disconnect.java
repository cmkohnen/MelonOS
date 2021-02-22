package me.meloni.melonos.Modules.Player;

import me.meloni.melonos.Modules.Messaging.Messenger;
import org.bukkit.entity.Player;

public class Disconnect {
    public static void kick(Player p, String s) {
        if(s.equalsIgnoreCase("shutdown")) {
            p.kickPlayer(Messenger.kickprefix + "Der Server wurde heruntergefahren.");
        } else{
            if (s.equals("")) {
                s = "Es wurde kein Grund angegeben.";
            }
            p.kickPlayer(Messenger.kickprefix + "Grund :" + s);
        }
    }
}
