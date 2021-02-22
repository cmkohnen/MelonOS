package me.meloni.melonos.Modules.Messaging;

import me.meloni.melonos.MelonOS;
import me.meloni.melonos.Modules.Language.TranslationMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messenger {
    private final TranslationMap translation;
    public Messenger(MelonOS instance) {
        this.translation = new TranslationMap(instance);
    }

    public static final String prefix = ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "MelonOS" + ChatColor.GRAY + "] ";
    public static final String kickprefix = ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "MelonOS" + ChatColor.GRAY + "] \n\n" + ChatColor.WHITE;
    private static final String serverprefix = ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "Server" + ChatColor.GRAY + "] ";

    private void replySender(CommandSender cs, String s){
        cs.sendMessage(prefix + s);
    }

    public void messagePlayer(String s, Player p ) { p.sendMessage(prefix + ChatColor.GRAY + s);}

    public static void broadcast(String s) {
        Bukkit.broadcastMessage(serverprefix + s);
    }

    public static void log(String s) {
        Bukkit.getLogger().info(s);
    }



    public void replyMessage(String s, CommandSender cs) { replySender( cs,ChatColor.GRAY + s); }

    public void noPermission(String s, CommandSender cs) { replySender(cs, translation.get("missingpermission") + s); }

    public void error(CommandSender cs) { replySender(cs, translation.get("error")); }

    public void deny(String s, CommandSender cs) { replySender(cs, ChatColor.RED + s); }

    public void playerOffline(String p, @org.jetbrains.annotations.NotNull CommandSender cs) {cs.sendMessage(prefix + ChatColor.RED + "Der Spieler " + p + " ist offline!");}

    public void notEnoughArgs(CommandSender cs){ replySender(cs, translation.get("notenougharguments")); }




    public void noPlayer(){ log(translation.get("noplayer")); }
}
