package me.meloni.melonos.Modules.Commands;

import me.meloni.melonos.MelonOS;
import me.meloni.melonos.Modules.Messaging.Messenger;
import me.meloni.melonos.Modules.Moderator.ModeratorLevel;
import me.meloni.melonos.Modules.Player.Online;
import me.meloni.melonos.Tools.TabAssistant;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class Sudo extends Command{
    private final Messenger messenger;
    public Sudo(MelonOS instance) {
        super(instance);
        this.messenger = instance.getMessenger();
    }

    @Override
    public List<String> getCommands() {
        return Collections.singletonList("sudo");
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        if(args.length >= 2) {
            Player player;
            String user = args[0];
            StringBuilder input = new StringBuilder();
            for(int i = 1; i < args.length; i++) {
                input.append(args[i]).append(" ");
            }
            input = new StringBuilder(input.toString().trim());
            if (args[0].equalsIgnoreCase("c")) {
                if (sender.hasPermission("melonos.command.sudo.console")) {
                    //say as console
                    if (input.toString().startsWith("/")){
                        input = new StringBuilder(input.substring(1));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), input.toString());
                        messenger.replyMessage(ChatColor.RED + "Der Server" + ChatColor.GRAY + " Wurde gezwungen [" + ChatColor.GREEN + "/" + input + ChatColor.GRAY + "] auszuführen.", sender);
                        Messenger.log("Der Befehl wurde von " + sender.getName() + " ausgeführt.");

                    } else {
                        Messenger.broadcast(input.toString());
                        messenger.replyMessage(ChatColor.RED + "Der Server" + ChatColor.GRAY + " Wurde gezwungen [" + ChatColor.GREEN + input + ChatColor.GRAY + "] zu sagen.", sender);
                        Messenger.log("Die Nachricht wurde von " + sender.getName() + " ausgeführt.");
                    }
                } else {
                    messenger.noPermission("sudo.console", sender);
                }
            } else {



                if (Online.isOnline(user)) {
                    player = Bukkit.getPlayer(user);
                    if (ModeratorLevel.isAllowed(sender, player)) {
                        if (input.toString().startsWith("/")) {
                            input = new StringBuilder(input.substring(1));
                            assert player != null;
                            Bukkit.dispatchCommand(player, input.toString());
                            messenger.replyMessage("[" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + "] Wurde gezwungen [" + ChatColor.GREEN + input + ChatColor.GRAY + "] auszuführen.", sender);
                            Messenger.log("[" + player.getName() + "] Wurde von " + sender.getName() + " gezwungen [" + input + "] auszuführen.");
                        } else {
                            assert player != null;
                            player.chat(input.toString());
                            messenger.replyMessage("[" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + "] Wurde gezwungen [" + ChatColor.GREEN + input + ChatColor.GRAY + "] zu sagen.", sender);
                            Messenger.log("[" + player.getName() + "] Wurde von " + sender.getName() + " gezwungen [" + input + "] zu sagen.");
                        }
                    } else {
                        messenger.deny("Du kannst diesen Spieler nicht zwingen [" + ChatColor.GRAY + input + ChatColor.RED + "] einzugeben.", sender);
                    }
                } else {
                    messenger.playerOffline(user, sender);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if(strings.length == 1) {
            TabAssistant tabAssistant = new TabAssistant(commandSender, strings[0]);
            tabAssistant.addAllowedTargets();
            tabAssistant.addIfHasPermission("c", "melonos.command.sudo.console");
            return tabAssistant.getArguments();
        } else {
            return null;
        }
    }
}
