package me.meloni.melonos.Modules.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener extends Listener{
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(event.getMessage().toLowerCase().startsWith("/reload")){
            Bukkit.dispatchCommand(event.getPlayer(), "melonos reload");
            event.setCancelled(true);
        }
    }
}
