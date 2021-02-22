package me.meloni.melonos.Modules.ServerControl;

import org.bukkit.Bukkit;

public class ServerController {
    public static void reload() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"reload");
    }

    public static void shutdown() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"stop");
    }
}
