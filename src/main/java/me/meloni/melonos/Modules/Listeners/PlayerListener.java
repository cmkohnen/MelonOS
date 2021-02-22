package me.meloni.melonos.Modules.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener extends Listener {
@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Willkommen auf Meloni's kleinem Homeserver!");
}
}
